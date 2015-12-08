package com.SGC.business;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.SGC.dao.BaixaDAO;
import com.SGC.dao.EntradaDAO;
import com.SGC.dao.GenericDAO;
import com.SGC.domain.Baixa;
import com.SGC.domain.Entrada;
import com.SGC.domain.Ordem;
import com.SGC.domain.Produto;
import com.SGC.domain.Relatorio;
import com.SGC.domain.Status;
import com.SGC.domain.TipoProduto;

public class EntradaBusiness {

	public static List<Entrada> Converter(HttpServletRequest request) {

		List<Entrada> entradas = new ArrayList<Entrada>();

		int IdOrdem = Integer.parseInt(request.getParameter("Ordem"));
		boolean fecha = Boolean.parseBoolean(request.getParameter("Fecha"));
		Ordem ordem = OrdemBusiness.Obter(IdOrdem);

		if (fecha) {
			ordem.setStatus(Status.Entregue);
			OrdemBusiness.Update(ordem); 
		}

		Enumeration<String> keys = request.getParameterNames();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			if (key.contains("Produto")) {
				String cod = key.substring(key.indexOf(".") + 1);

				Entrada entrada = new Entrada();

				String lote = request.getParameter("Lote." + cod);
				entrada.setNumeroLote(lote);

				int qtdLote = Integer.parseInt(request.getParameter("qtdLote."
						+ cod));
				entrada.setQtdLote(qtdLote);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dataEntrega;

				try {

					dataEntrega = sdf.parse(request.getParameter("Validade."
							+ cod));
					entrada.setValidade(dataEntrega);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				NumberFormat format = NumberFormat.getInstance(Locale
						.getDefault());
				Number number = 0;

				try {
					number = format.parse(request.getParameter("Valor." + cod));
				} catch (ParseException e) {

				}

				entrada.setValor(number.doubleValue());

				entrada.setData(new Date());

				Produto produto = ProdutoBusiness.Obter(Integer
						.parseInt(request.getParameter("Produto." + cod)));

				entrada.setProduto(produto);

				entradas.add(entrada);
			}
		}
		return entradas;
	}

	public static List<Relatorio> GerarRelatorio(int corte, Integer[] IdProdutos) {
		List<Relatorio> relatorios = null;
		try {
			relatorios = GerarRelatorio(corte, IdProdutos, null, null);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
		return relatorios;
	}

	@SuppressWarnings("unchecked")
	public static List<Relatorio> GerarRelatorio(int corte,
			Integer[] IdProdutos, Date Init, Date Fim) {
		List<Relatorio> map = new ArrayList<Relatorio>();

		try {

			EntradaDAO entradaDAO = EntradaDAO.getInstance();

			Criteria crid = entradaDAO.getSession()
					.createCriteria(Entrada.class, "e")
					.createCriteria("e.Produto", "p");

			// Projection count = Projections.rowCount();
			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.groupProperty("p.Nome"), "Label");
			projList.add(Projections.rowCount(), "Quantidade");
			// projList.add(count);

			if (Init != null && Fim != null)
				crid.add(Restrictions.between("e.Data", Init, Fim));

			if (IdProdutos != null)
				crid.add(Restrictions.in("p.Id", IdProdutos));

			switch (corte) {
			case 0:

				projList.add(Projections.sqlGroupProjection(
						"DAY(Data) as Coluna", "Data",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%d') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 1:

				projList.add(Projections.sqlGroupProjection(
						"WEEK(Data) as Coluna", "WEEK(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%V Semana') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));
				break;
			case 2:

				projList.add(Projections.sqlGroupProjection(
						"MONTH(Data) as Coluna", "MONTH(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%m/%y') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 3:

				projList.add(Projections.sqlGroupProjection(
						"QUARTER(Data) as Coluna", "QUARTER(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"CONCAT(QUARTER(Data), ' Trimestre') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 4:

				projList.add(Projections.sqlGroupProjection(
						"YEAR(Data) as Coluna", "YEAR(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%y') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 5:

				projList.add(Projections.sqlGroupProjection(
						"ROUND(month(Data) / 6 ) as Coluna",
						"ROUND(month(Data) / 6 )",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections
						.sqlProjection(
								"CONCAT(ROUND(month(Data) / 6 ), ' Semestre') as XLabel",
								new String[] { "XLabel" },
								new Type[] { StringType.INSTANCE }));

				break;
			}

			crid.setProjection(projList);
			crid.addOrder(Order.asc("e.Data"));

			map = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static void Add(Entrada entrada) {
		try {
			EntradaDAO entradaDao = EntradaDAO.getInstance();
			entradaDao.add(entrada);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
	}

	public static List<Entrada> ObterLista() {
		List<Entrada> Entradas = new ArrayList<Entrada>();
		try {
			EntradaDAO entradaDao = EntradaDAO.getInstance();
			Entradas = entradaDao.getLista();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
		return Entradas;
	}

	public static int Count() {
		int count = 0;

		try {
			EntradaDAO entradaDao = EntradaDAO.getInstance();
			Criteria crid = entradaDao.getSession().createCriteria(
					Entrada.class);

			count = ((Number) crid.setProjection(Projections.sum("QtdLote"))
					.uniqueResult()).intValue();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return count;
	}

	public static int TotalMedicamentos() {
		int count = 0;

		try {
			EntradaDAO entradaDao = EntradaDAO.getInstance();
			Criteria crid = entradaDao.getSession()
					.createCriteria(Entrada.class, "e")
					.createCriteria("e.Produto", "p");

			count = ((Number) crid
					.add(Restrictions.eq("p.TipoProduto",
							TipoProduto.Medicamento))
					.setProjection(Projections.sum("e.QtdLote")).uniqueResult())
					.intValue();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return count;
	}

	public static int TotalMaterias() {
		int count = 0;

		try {
			EntradaDAO entradaDao = EntradaDAO.getInstance();
			Criteria crid = entradaDao.getSession()
					.createCriteria(Entrada.class, "e")
					.createCriteria("e.Produto", "p");

			count = ((Number) crid
					.add(Restrictions.not(Restrictions.eq("p.TipoProduto",
							TipoProduto.Medicamento)))
					.setProjection(Projections.sum("e.QtdLote")).uniqueResult())
					.intValue();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return count;
	}

	public static int AVencer() {
		int count = 0;

		Date dInit = new Date();
		Date dfim = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(dfim);
		cal.add(Calendar.DATE, 10); // add 10 days

		dfim = cal.getTime();

		try {
			EntradaDAO entradaDao = EntradaDAO.getInstance();
			Criteria crid = entradaDao.getSession().createCriteria(
					Entrada.class);

			count = ((Number) crid
					.add(Restrictions.between("Validade", dInit, dfim))
					.setProjection(Projections.sum("QtdLote")).uniqueResult())
					.intValue();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	public static List<Entrada> ObterEntradasPorProduto(Produto produto) {
		List<Entrada> entradas = null;

		String sql = "SELECT e.* \n"
				+ "FROM sgc.entrada e \n"
				+ "inner join sgc.produto p on e.Produto_Id = p.Id \n"
				+ "where p.Id = "
				+ produto.getId()
				+ " \n"
				+ "group by e.Id having sum(e.Qtd_lote) > (select COALESCE(sum(i.Quantidade), 0) \n"
				+ "from sgc.itembaixa i where i.Entrada_Id = e.Id);";

		try {
			EntradaDAO dao = EntradaDAO.getInstance();
			SQLQuery query = dao.getSession().createSQLQuery(sql)
					.addEntity(Entrada.class);

			entradas = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
		return entradas;
	}

	@SuppressWarnings("unchecked")
	public static List<Produto> ObterProdutosEmEstoque() {
		List<Produto> produtos = null;

		String sql = "SELECT p.*, \n"
				+ "sum(e.Qtd_lote) as QtdLote, \n"
				+ "(select COALESCE(sum(i.Quantidade), 0) from sgc.itembaixa i where i.Produto_Id = e.Produto_Id) as QtdBaixa \n"
				+ "FROM sgc.produto p \n"
				+ "inner join sgc.entrada e on p.Id = e.Produto_Id \n"
				+ "group by p.Id having QtdLote > QtdBaixa;";

		try {
			EntradaDAO dao = EntradaDAO.getInstance();

			SQLQuery query = dao.getSession().createSQLQuery(sql)
					.addEntity(Produto.class);

			produtos = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return produtos;
	}

	public static Entrada Obter(int idEntrada) {
		// TODO Auto-generated method stub
		Entrada entrada = null;
		try {
			EntradaDAO dao = EntradaDAO.getInstance();
			entrada = dao.get(idEntrada);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return entrada;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<Relatorio> ObterSugestao() {
		// TODO Auto-generated method stub
		List<Relatorio> lista = new ArrayList<Relatorio>();

		String query = "select p.Nome as Label, \n"
        + "sum((select COALESCE(sum(e.Qtd_lote), 0) \n"
        + "from Entrada e where \n"
        + "e.Produto_Id = p.Id) - (select \n"
        + "  COALESCE(sum(i.Quantidade), 0) \n"  
        + " from itembaixa i where i.Produto_Id = p.Id)) as Coluna, \n"
        + " p.Qtd_minima as Quantidade \n"  
        + "from produto p \n"
        + "group by p.Nome \n" 
        + "having sum((select COALESCE(sum(e.Qtd_lote), 0) from Entrada e \n" 
        + "where e.Produto_Id = p.Id) - (select COALESCE(sum(i.Quantidade), 0) \n"  
        + "from itembaixa i where i.Produto_Id = p.Id)) < p.Qtd_minima \n"
        + "order by p.Nome asc;";

		try {
			GenericDAO<Relatorio> dao = new GenericDAO<Relatorio>();

			List<Object[]> rows = dao.getSession().createSQLQuery(query).list();

			for (Iterator<Object[]> iterator = rows.iterator(); iterator
					.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				Relatorio r = new Relatorio();
				r.setColuna(Long.parseLong(object[1].toString()));
				r.setLabel(object[0].toString());
				r.setQuantidade(Long.parseLong(object[2].toString()));
				lista.add(r);
			}

			// EntradaDAO dao = new EntradaDAO();
			// Criteria ctEntrada = dao.getSession()
			// .createCriteria(Entrada.class, "e")
			// .createCriteria("e.Produto", "p");
			//
			// ProjectionList projList = Projections.projectionList();
			//
			// projList.add(Projections.sum("e.QtdLote"), "Quantidade");
			// projList.add(Projections.sqlGroupProjection(
			// "p.Nome as Label", "p.Nome having sum(e.QtdLote) < p.QtdMinima",
			// new String[] { "Label" },
			// new Type[] { LongType.INSTANCE }));
			// projList.add(Projections.property("{alias}.QtdMinima"),
			// "Coluna");
			//
			// lista = (List<Relatorio>) ctEntrada
			// .setProjection(projList)
			// .setResultTransformer(
			// Transformers.aliasToBean(Relatorio.class))
			// .addOrder(Order.asc("p.Nome")).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public static List<Relatorio> GerarRelatorioValores(Integer corte,
			Integer[] produtos, Date init, Date fim) {
		List<Relatorio> map = new ArrayList<Relatorio>();

		try {

			EntradaDAO entradaDAO = EntradaDAO.getInstance();

			Criteria crid = entradaDAO.getSession()
					.createCriteria(Entrada.class, "e")
					.createCriteria("e.Produto", "p");

			// Projection count = Projections.rowCount();
			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.groupProperty("p.Nome"), "Label");
			projList.add(Projections.sum("e.Valor"), "Valor");

			if (init != null && fim != null)
				crid.add(Restrictions.between("e.Data", init, fim));

			if (produtos != null)
				crid.add(Restrictions.in("p.Id", produtos));

			switch (corte) {
			case 0:

				projList.add(Projections.sqlGroupProjection(
						"DAY(Data) as Coluna", "Data",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%d') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 1:

				projList.add(Projections.sqlGroupProjection(
						"WEEK(Data) as Coluna", "WEEK(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%V Semana') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));
				break;
			case 2:

				projList.add(Projections.sqlGroupProjection(
						"MONTH(Data) as Coluna", "MONTH(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%m/%y') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 3:

				projList.add(Projections.sqlGroupProjection(
						"QUARTER(Data) as Coluna", "QUARTER(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"CONCAT(QUARTER(Data), ' Trimestre') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 4:

				projList.add(Projections.sqlGroupProjection(
						"YEAR(Data) as Coluna", "YEAR(Data)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Data, '%y') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 5:

				projList.add(Projections.sqlGroupProjection(
						"ROUND(month(Data) / 6 ) as Coluna",
						"ROUND(month(Data) / 6 )",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections
						.sqlProjection(
								"CONCAT(ROUND(month(Data) / 6 ), ' Semestre') as XLabel",
								new String[] { "XLabel" },
								new Type[] { StringType.INSTANCE }));

				break;
			}

			crid.setProjection(projList);
			crid.addOrder(Order.asc("e.Data"));

			map = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static List<Relatorio> GerarRelatorioValores(Integer corte,
			Integer[] produtos) {
		List<Relatorio> relatorios = null;

		try {
			relatorios = GerarRelatorioValores(corte, produtos, null, null);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return relatorios;
	}

	public static void ListaAdd(List<Entrada> baixa) {
		// TODO Auto-generated method stub
		try {

			for (Iterator<Entrada> iterator = baixa.iterator(); iterator
					.hasNext();) {
				Entrada entrada = (Entrada) iterator.next();
				Add(entrada);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

	}

	public static List<Relatorio> GerarRelatorioValidade(Integer corte,
			Integer[] produtos) {

		List<Relatorio> relatorios = null;

		try {
			relatorios = GerarRelatorioValidade(corte, produtos, null, null);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return relatorios;
	}

	public static List<Relatorio> GerarRelatorioValidade(Integer corte,
			Integer[] produtos, Date init, Date fim) {

		List<Relatorio> map = new ArrayList<Relatorio>();

		try {

			EntradaDAO entradaDAO = EntradaDAO.getInstance();

			Criteria crid = entradaDAO.getSession()
					.createCriteria(Entrada.class, "e")
					.createCriteria("e.Produto", "p");

			// Projection count = Projections.rowCount();
			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.groupProperty("p.Nome"), "Label");
			projList.add(Projections.sum("e.QtdLote"), "Quantidade");
			// projList.add(count);
			
			Calendar prevDay = Calendar.getInstance();
			prevDay.set(Calendar.SECOND, 0);
			prevDay.set(Calendar.MINUTE, 0);
			prevDay.set(Calendar.HOUR_OF_DAY, 0);	
			
			if (fim != null)
				crid.add(Restrictions.between("e.Validade", prevDay.getTime(), fim));

			if (produtos != null)
				crid.add(Restrictions.in("p.Id", produtos));

			switch (corte) {
			case 0:

				projList.add(Projections.sqlGroupProjection(
						"DAY(Validade) as Coluna", "Validade",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Validade, '%d') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 1:

				projList.add(Projections.sqlGroupProjection(
						"WEEK(Validade) as Coluna", "WEEK(Validade)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Validade, '%V Semana') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));
				break;
			case 2:

				projList.add(Projections.sqlGroupProjection(
						"MONTH(Validade) as Coluna", "MONTH(Validade)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Validade, '%m/%y') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 3:

				projList.add(Projections.sqlGroupProjection(
						"QUARTER(Validade) as Coluna", "QUARTER(Validade)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"CONCAT(QUARTER(Validade), ' Trimestre') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 4:

				projList.add(Projections.sqlGroupProjection(
						"YEAR(Validade) as Coluna", "YEAR(Validade)",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(Validade, '%y') as XLabel",
						new String[] { "XLabel" },
						new Type[] { StringType.INSTANCE }));

				break;
			case 5:

				projList.add(Projections.sqlGroupProjection(
						"ROUND(month(Validade) / 6 ) as Coluna",
						"ROUND(month(Data) / 6 )",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections
						.sqlProjection(
								"CONCAT(ROUND(month(Validade) / 6 ), 'ª Semestre') as XLabel",
								new String[] { "XLabel" },
								new Type[] { StringType.INSTANCE }));

				break;
			}

			crid.setProjection(projList);
			crid.addOrder(Order.asc("e.Validade"));

			map = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}
}

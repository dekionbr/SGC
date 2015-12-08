package com.SGC.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import sun.awt.datatransfer.DataTransferer.DataFlavorComparator;

import com.SGC.dao.ItemOrdemDAO;
import com.SGC.dao.OrdemDAO;
import com.SGC.dao.ProdutoDAO;
import com.SGC.domain.Baixa;
import com.SGC.domain.Fornecedor;
import com.SGC.domain.Funcionario;
import com.SGC.domain.ItemBaixa;
import com.SGC.domain.ItemOrdem;
import com.SGC.domain.Ordem;
import com.SGC.domain.Produto;
import com.SGC.domain.Relatorio;
import com.SGC.domain.Status;

public class OrdemBusiness {

	public static List<Ordem> ObterLista() {
		List<Ordem> Ordens = null;

		OrdemDAO ordemDAO = OrdemDAO.getInstance();
		Ordens = ordemDAO.getLista(Ordem.class);

		if (Ordens == null)
			Ordens = new ArrayList<Ordem>();

		return Ordens;
	}

	public static List<Ordem> ObterLista(Date Init, Date Fim) {
		List<Ordem> Ordens = null;

		OrdemDAO ordemDAO = OrdemDAO.getInstance();
		Ordens = ordemDAO.getLista(Init, Fim);

		if (Ordens == null)
			Ordens = new ArrayList<Ordem>();

		return Ordens;
	}

	public static List<Ordem> ObterLista(Date Init, Date Fim,
			Funcionario funcionario) {
		List<Ordem> Ordens = null;

		OrdemDAO ordemDAO = OrdemDAO.getInstance();
		Ordens = ordemDAO.getLista(Init, Fim, funcionario);

		if (Ordens == null)
			Ordens = new ArrayList<Ordem>();

		return Ordens;
	}

	public static Ordem Converter(HttpServletRequest request) {
		Ordem ordem = new Ordem();

		int idFornecedor = Integer.parseInt(request.getParameter("Fornecedor"));

		Fornecedor fornecedor = FornecedorBusiness.Obter(idFornecedor);
		ordem.setFornecedor(fornecedor);

		boolean prioridade = Boolean.parseBoolean(request
				.getParameter("Prioridade"));
		ordem.setPrioridade(prioridade);
		ordem.setData(new Date());

		String justificativa = request.getParameter("justificativa");
		ordem.setJustificativa(justificativa);

		ordem.setStatus(Status.Pendente);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataEntrega;

		try {

			dataEntrega = sdf.parse(request.getParameter("dataEntrega"));
			ordem.setDataEntrega(dataEntrega);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Enumeration<String> keys = request.getParameterNames();

		List<ItemOrdem> itens = new ArrayList<ItemOrdem>();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			if (key.contains("Produto")) {
				ItemOrdem item = new ItemOrdem();
				int idProduto = Integer.parseInt(request.getParameter(key));
				Produto produto = ProdutoBusiness.Obter(idProduto);
				item.setProduto(produto);
				item.setOrdem(ordem);
				int qtd = Integer.parseInt(request.getParameter("qtd_"
						+ idProduto));
				item.setQuantidade(qtd);
				itens.add(item);
			}
		}

		// ordem.setUsuario(funcionario);
		ordem.setItensOrdem(itens);
		return ordem;
	}

	public static void Add(Ordem ordem) {
		try {

			OrdemDAO ordemDAO = OrdemDAO.getInstance();
			ordemDAO.add(ordem);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Relatorio> GerarRelatorioFornecedor(Integer corte,
			Integer[] fornecedorId) {
		List<Relatorio> relatorios = null;
		try {
			relatorios = GerarRelatorioFornecedor(corte, fornecedorId, null,
					null);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}

		return relatorios;
	}

	@SuppressWarnings("unchecked")
	public static List<Relatorio> GerarRelatorioFornecedor(int corte,
			Integer[] fornecedorId, Date init, Date fim) {
		List<Relatorio> map = new ArrayList<Relatorio>();

		try {
			OrdemDAO ordemDAO = OrdemDAO.getInstance();

			Criteria crid = ordemDAO.getSession()
					.createCriteria(Ordem.class, "o")
					.createCriteria("o.Fornecedor", "f");

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.groupProperty("f.NomeFantasia"), "Label");
			projList.add(Projections.rowCount(), "Quantidade");

			if (init != null && fim != null)
				crid.add(Restrictions.between("o.Data", init, fim));

			if (fornecedorId != null)
				crid.add(Restrictions.in("f.Id", fornecedorId));

			switch (corte) {
			case 0:

				projList.add(Projections.sqlGroupProjection(
						"DAY(o.Data) as Coluna", "o.Data",
						new String[] { "Coluna" },
						new Type[] { LongType.INSTANCE }));

				projList.add(Projections.sqlProjection(
						"DATE_FORMAT(o.Data, '%d') as XLabel",
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
						"DATE_FORMAT(o.Data, '%m/%y') as XLabel",
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
			crid.addOrder(Order.asc("o.Data"));

			map = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static int PedidosHoje() {
		int count = 0;

		try {
			OrdemDAO dao = OrdemDAO.getInstance();

			Criteria crid = dao.getSession().createCriteria(Ordem.class);
				
			Calendar prevDay = Calendar.getInstance();
			prevDay.set(Calendar.SECOND, 0);
			prevDay.set(Calendar.MINUTE, 0);
			prevDay.set(Calendar.HOUR_OF_DAY, 0);			
			
			Calendar lastDay = Calendar.getInstance();
			lastDay.set(Calendar.SECOND, 59);
			lastDay.set(Calendar.MINUTE, 59);
			lastDay.set(Calendar.HOUR_OF_DAY, 23);

			count = ((Number) crid.add(Restrictions.between("Data", prevDay.getTime(), lastDay.getTime()))
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return count;
	}

	public static List<Relatorio> GerarRelatorioStatus(Integer corte, String st) {
		// TODO Auto-generated method stub
		List<Relatorio> relatorios = null;

		try {
			relatorios = GerarRelatorioStatus(corte, st, null, null);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}
		return relatorios;
	}

	public static List<Relatorio> GerarRelatorioStatus(Integer corte,
			String st, Date init, Date fim) {
		// TODO Auto-generated method stub
		List<Relatorio> relatorios = null;
		try {
			OrdemDAO dao = new OrdemDAO();

			Criteria crid = dao.getSession().createCriteria(Ordem.class);

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.sqlGroupProjection("CAST(Status AS CHAR) as Label",
					"Status", new String[] { "Label" },
					new Type[] { StringType.INSTANCE }));
			
			projList.add(Projections.rowCount(), "Quantidade");

			if (init != null && fim != null)
				crid.add(Restrictions.between("Data", init, fim));

			if (st != null) {
				int idxStatus = Integer.parseInt(st);
				Status status = Status.values()[idxStatus];
				crid.add(Restrictions.eq("Status", status));
			}

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
			crid.addOrder(Order.asc("Data"));

			relatorios = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}

		return relatorios;
	}

	public static List<Ordem> ObterLista(Status status) {
		// TODO Auto-generated method stub
		List<Ordem> Ordens = null;

		OrdemDAO ordemDAO = OrdemDAO.getInstance();

		Ordens = ordemDAO.getSession().createCriteria(Ordem.class)
				.add(Restrictions.eq("Status", status)).list();

		if (Ordens == null)
			Ordens = new ArrayList<Ordem>();

		return Ordens;
	}

	public static List<ItemOrdem> ObterListaItens(int idOrdem) {
		List<ItemOrdem> Ordens = null;

		ItemOrdemDAO ordemDAO = ItemOrdemDAO.getInstance();

		Ordens = ordemDAO.getSession().createCriteria(ItemOrdem.class)
				.add(Restrictions.eq("Ordem.Id", idOrdem)).list();

		if (Ordens == null)
			Ordens = new ArrayList<ItemOrdem>();

		return Ordens;
	}

	public static Ordem Obter(int idOrdem) {
		Ordem ordem = null;
		try {
			OrdemDAO dao = OrdemDAO.getInstance();
			ordem = dao.get(idOrdem);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ordem;
	}

	public static void Update(Ordem ordem) {
		try {

			OrdemDAO dao = OrdemDAO.getInstance();
			dao.Update(ordem);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}

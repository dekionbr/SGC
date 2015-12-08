package com.SGC.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.SGC.dao.BaixaDAO;
import com.SGC.domain.Baixa;
import com.SGC.domain.Entrada;
import com.SGC.domain.ItemBaixa;
import com.SGC.domain.MotivoBaixa;
import com.SGC.domain.Produto;
import com.SGC.domain.Relatorio;


public class BaixaBusiness {

	public static Baixa Converter(HttpServletRequest request) {
		Baixa baixa = new Baixa();

		baixa.setData(new Date());

		baixa.setNomeSolicitante(request.getParameter("Nome"));

		baixa.setMatricula(request.getParameter("Matricula"));

		baixa.setSetor(request.getParameter("Setor"));

		int m = Integer.parseInt((String) request.getParameter("Motivo"));
		MotivoBaixa motivo = MotivoBaixa.values()[m];

		baixa.setMotivo(motivo);

		Enumeration<String> keys = request.getParameterNames();

		List<ItemBaixa> itens = new ArrayList<ItemBaixa>();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			if (key.contains("Entrada")) {

				int idxId = key.indexOf(".") + 1;
				int idEntrada = Integer.parseInt(request.getParameter(key));
				String stQtd = request.getParameter("Quantidade."
						+ key.substring(idxId));
				int qtd = Integer.parseInt(stQtd);

				ItemBaixa itemSaida = new ItemBaixa();

				Entrada entrada = EntradaBusiness.Obter(idEntrada);

				itemSaida.setProduto(entrada.getProduto());

				itemSaida.setQuantidade(qtd);

				itemSaida.setEntradas(entrada);

				itemSaida.setSaida(baixa);

				itens.add(itemSaida);

				List<ItemBaixa> baixas = entrada.getBaixas();

				baixas.add(itemSaida);

				entrada.setBaixas(baixas);
				// EntradaBusiness.DescontarProduto(produto.getId(), qtd);

			}
		}

		baixa.setItensBaixa(itens);

		return baixa;
	}

	public static void Add(Baixa baixa) {
		try {

			BaixaDAO baixaDAO = BaixaDAO.getInstance();
			baixaDAO.add(baixa);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Baixa> ObterLista() {
		List<Baixa> baixas = new ArrayList<Baixa>();

		try {

			BaixaDAO baixaDAO = BaixaDAO.getInstance();
			baixas = baixaDAO.getLista();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return baixas;
	}

	@SuppressWarnings("unchecked")
	public static List<String> ObterListaSetores() {
		List<String> setores = new ArrayList<String>();
		try {

			BaixaDAO baixaDAO = BaixaDAO.getInstance();
			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.distinct(Projections.property("Setor")));

			setores = baixaDAO.getSession().createCriteria(Baixa.class)
					.setProjection(projList).addOrder(Order.asc("Setor")).list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return setores;
	}

	public static List<Relatorio> GerarRelatorio(Integer corte) {
		List<Relatorio> map = new ArrayList<Relatorio>();

		try {
			map = GerarRelatorio(corte, null);

		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static List<Relatorio> GerarRelatorio(int corte, String[] setores) {

		List<Relatorio> map = new ArrayList<Relatorio>();

		try {

			map = GerarRelatorio(corte, setores, null);

		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static List<Relatorio> GerarRelatorio(int corte, String[] setores,
			Integer[] Produtos) {
		List<Relatorio> map = new ArrayList<Relatorio>();
		try {
			Date Init = null;
			Date Fim = null;
			map = GerarRelatorio(corte, setores, Produtos, Init, Fim);
		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static List<Relatorio> GerarRelatorio(int corte, String[] setores,
			Integer[] produtos, Date init, Date fim) {

		List<Relatorio> map = new ArrayList<Relatorio>();

		try {
			BaixaDAO baixaDAO = BaixaDAO.getInstance();

			Criteria crid = baixaDAO.getSession()
					.createCriteria(Baixa.class, "b")
					.createCriteria("b.ItensBaixa", "i");

			ProjectionList projList = Projections.projectionList();

			// projList.add(Projections.distinct(Projections.property("Setor")));
			projList.add(Projections.groupProperty("b.Setor"), "label");
			projList.add(Projections.rowCount(), "quantidade");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			if (init != null && fim != null)
				crid.add(Restrictions.between("b.Data", init, fim));

			if (setores != null) {
				crid.add(Restrictions.in("b.Setor", setores));
			}

			if (produtos != null) {
				crid.add(Restrictions.in("i.Produto.Id", produtos));
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
			crid.addOrder(Order.asc("b.Data"));

			map = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();

		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static List<Relatorio> GerarRelatorioPerdas(int corte,
			Integer[] Produtos) {
		List<Relatorio> map = new ArrayList<Relatorio>();
		try {
			Date Init = null;
			Date Fim = null;
			map = GerarRelatorioPerdas(corte, Produtos, Init, Fim);
		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static List<Relatorio> GerarRelatorioPerdas(int corte,
			Integer[] produtos, Date init, Date fim) {

		List<Relatorio> map = new ArrayList<Relatorio>();

		try {
			BaixaDAO baixaDAO = BaixaDAO.getInstance();

			Criteria crid = baixaDAO.getSession()
					.createCriteria(Baixa.class, "b")
					.createCriteria("b.ItensBaixa", "i");

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.sqlGroupProjection("(case Motivo when 1 then 'Perda' " +
																	 "when 2 then 'Vencimento de Validade' " +
																	 "when 3 then 'Danificado' " +
																	 "when 4 then 'Quebrado' end)" +
																	 "as Label", "Label",
					new String[] { "Label" },
					new Type[] { StringType.INSTANCE }));
			
			projList.add(Projections.rowCount(), "Quantidade");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			if (init != null && fim != null)
				crid.add(Restrictions.between("b.Data", init, fim));

			if (produtos != null) {
				crid.add(Restrictions.in("i.Produto.Id", produtos));
			}
			
			crid.add(Restrictions.not(Restrictions.eq("b.Motivo", MotivoBaixa.REGULAR)));
			
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
								"CONCAT(ROUND(month(Data) / 6 ) + 1, ' Semestre') as XLabel",
								new String[] { "XLabel" },
								new Type[] { StringType.INSTANCE }));

				break;
			}

			crid.setProjection(projList);
			crid.addOrder(Order.asc("b.Data"));

			map = (List<Relatorio>) crid.setResultTransformer(
					Transformers.aliasToBean(Relatorio.class)).list();

		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}

		return map;
	}

	public static int SetoresAtendidos() {
		int count = 0;

		try {
			BaixaDAO dao = BaixaDAO.getInstance();

			Criteria crid = dao.getSession().createCriteria(Baixa.class);
			
			Calendar prevDay = Calendar.getInstance();
			prevDay.set(Calendar.SECOND, 0);
			prevDay.set(Calendar.MINUTE, 0);
			prevDay.set(Calendar.HOUR_OF_DAY, 0);			
			
			Calendar lastDay = Calendar.getInstance();
			lastDay.set(Calendar.SECOND, 59);
			lastDay.set(Calendar.MINUTE, 59);
			lastDay.set(Calendar.HOUR_OF_DAY, 23);
	        
			count = ((Number) crid.add(Restrictions.between("Data", prevDay.getTime(), lastDay.getTime())).setProjection(
					Projections.countDistinct("Setor")).uniqueResult())
					.intValue();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public static List<Baixa> ObterBaixasPorProduto(Produto produto) {
		List<Baixa> baixas = null;
		try {
			BaixaDAO Dao = new BaixaDAO();
			Criteria crid = Dao.getSession().createCriteria(Baixa.class);

			baixas = (List<Baixa>) crid.add(
					Restrictions.eq("Produto.Id", produto.getId())).addOrder(Order.asc("Data")) .list();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro: " + e.getMessage());
		}
		return baixas;
	}
}

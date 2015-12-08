package com.SGC.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.org.mozilla.javascript.internal.Undefined;

import com.SGC.business.*;
import com.SGC.domain.*;
import com.SGC.util.Util;

public class GerarRelatorioServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date Init = null;
	private Date Fim = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Integer corte = 0;

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {

		super.processRequest(request, response);

		PrintWriter out = response.getWriter();
		String url[] = request.getServletPath().split("/");
		String userPath = url[1];
		String Page = "";
		String msg = "";

		corte = Integer.parseInt(request.getParameter("Corte"));

		List<Relatorio> map = null;

		switch (userPath) {
		case "GerarRelatoriosEntrada":
			msg = GerarRelatoriosEntrada(request);
			break;

		case "GerarRelatoriosBaixa":
			msg = GerarRelatorioBaixa(request);
			break;

		case "GerarRelatoriosOrdemDeCompra":
			msg = GerarRelatoriosOrdemDeCompra(request);
			break;
		case "GerarRelatoriosComparativo":
			msg = GerarRelatoriosComparativo(request);
			break;
		case "GerarRelatoriosFornecedor":
			msg = GerarRelatoriosFornecedor(request);
			break;
		case "GerarRelatoriosPerdas":
			msg = GerarRelatoriosPerdas(request);
			break;
		case "GerarRelatoriosValidade":
			msg = GerarRelatoriosValidade(request);
			break;
		// case "GerarRelatoriosSugestaoDeCompra":
		// msg = GerarRelatoriosSugestaoDeCompra(request);
		// break;
		}

		out.print(msg);
	}

	// private String GerarRelatoriosSugestaoDeCompra(HttpServletRequest
	// request) {
	// // TODO Auto-generated method stub
	// String[] produtosStr = null;
	// Integer[] produtos = null;
	//
	// try {
	//
	// if ((!request.getParameter("Init").isEmpty()
	// && !request.getParameter("Fim").isEmpty()) &&
	// (!request.getParameter("Init").equals("null")
	// && !request.getParameter("Fim").equals("null"))) {
	// Init = sdf.parse(request.getParameter("Init"));
	// Fim = sdf.parse(request.getParameter("Fim"));
	// }
	//
	// produtosStr = request.getParameterValues("Produtos[]");
	//
	// if (!produtosStr[0].equals("null"))
	// produtos = new Integer[produtosStr.length];
	//
	// for (int i = 0; i < produtosStr.length; i++) {
	// if (!produtosStr[i].equals("null"))
	// produtos[i] = Integer.parseInt(produtosStr[i]);
	// }
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	//
	// List<Relatorio> relatorios;
	// if (Init == null || Fim == null)
	// relatorios = EntradaBusiness.GerarRelatorioValores(corte, produtos);
	// else
	// relatorios = EntradaBusiness.GerarRelatorioValores(corte, produtos, Init,
	// Fim);
	//
	// return Serialize(relatorios);
	// }

	private String GerarRelatoriosValidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String[] produtosStr = null;
		Integer[] produtos = null;

		try {
			Init = null;

			if (request.getParameter("Fim") != null
					&& !request.getParameter("Fim").equals("null")) {
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			produtosStr = request.getParameterValues("Produtos[]");

			if (!produtosStr[0].equals("null"))
				produtos = new Integer[produtosStr.length];

			for (int i = 0; i < produtosStr.length; i++) {
				if (!produtosStr[i].equals("null"))
					produtos[i] = Integer.parseInt(produtosStr[i]);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		List<Relatorio> relatorios;
		if (Fim == null)
			relatorios = EntradaBusiness
					.GerarRelatorioValidade(corte, produtos);
		else
			relatorios = EntradaBusiness.GerarRelatorioValidade(corte,
					produtos, Init, Fim);

		return Serialize(relatorios);
	}

	private String GerarRelatoriosOrdemDeCompra(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String st = null;
		try {

			if ((!request.getParameter("Init").isEmpty() && !request
					.getParameter("Fim").isEmpty())
					&& (!request.getParameter("Init").equals("null") && !request
							.getParameter("Fim").equals("null"))) {
				Init = sdf.parse(request.getParameter("Init"));
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			st = request.getParameter("Status") == "" ? null : request
					.getParameter("Status");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
		}

		List<Relatorio> relatorios;
		if (Init == null || Fim == null)
			relatorios = OrdemBusiness.GerarRelatorioStatus(corte, st);
		else
			relatorios = OrdemBusiness.GerarRelatorioStatus(corte, st, Init,
					Fim);

		return SerializeStatus(relatorios);
	}

	private String GerarRelatoriosComparativo(HttpServletRequest request) {
		String[] produtosStr = null;
		Integer[] produtos = null;

		try {

			if ((!request.getParameter("Init").isEmpty() && !request
					.getParameter("Fim").isEmpty())
					&& (!request.getParameter("Init").equals("null") && !request
							.getParameter("Fim").equals("null"))) {
				Init = sdf.parse(request.getParameter("Init"));
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			produtosStr = request.getParameterValues("Produtos[]");

			if (!produtosStr[0].equals("null"))
				produtos = new Integer[produtosStr.length];

			for (int i = 0; i < produtosStr.length; i++) {
				if (!produtosStr[i].equals("null"))
					produtos[i] = Integer.parseInt(produtosStr[i]);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		List<Relatorio> relatorios;
		if (Init == null || Fim == null)
			relatorios = EntradaBusiness.GerarRelatorioValores(corte, produtos);
		else
			relatorios = EntradaBusiness.GerarRelatorioValores(corte, produtos,
					Init, Fim);

		return SerializeValor(relatorios);
	}

	private String GerarRelatoriosEntrada(HttpServletRequest request) {
		String[] produtosStr = null;
		Integer[] produtos = null;

		try {

			if ((!request.getParameter("Init").isEmpty() && !request
					.getParameter("Fim").isEmpty())
					&& (!request.getParameter("Init").equals("null") && !request
							.getParameter("Fim").equals("null"))) {
				Init = sdf.parse(request.getParameter("Init"));
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			produtosStr = request.getParameterValues("Produtos[]");

			if (!produtosStr[0].equals("null"))
				produtos = new Integer[produtosStr.length];

			for (int i = 0; i < produtosStr.length; i++) {
				if (!produtosStr[i].equals("null"))
					produtos[i] = Integer.parseInt(produtosStr[i]);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		List<Relatorio> relatorios;
		if (Init == null || Fim == null)
			relatorios = EntradaBusiness.GerarRelatorio(corte, produtos);
		else
			relatorios = EntradaBusiness.GerarRelatorio(corte, produtos, Init,
					Fim);

		return Serialize(relatorios);
	}

	private String GerarRelatoriosFornecedor(HttpServletRequest request) {

		String[] Foraux = null;
		Integer[] FornecedorId = null;
		try {

			if ((!request.getParameter("Init").isEmpty() && !request
					.getParameter("Fim").isEmpty())
					&& (!request.getParameter("Init").equals("null") && !request
							.getParameter("Fim").equals("null"))) {
				Init = sdf.parse(request.getParameter("Init"));
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			Foraux = request.getParameterValues("Fornecedores[]");

			if (!Foraux[0].equals("null")) {
				FornecedorId = new Integer[Foraux.length];

				for (int i = 0; i < Foraux.length; i++) {
					if (!Foraux[i].equals("null"))
						FornecedorId[i] = Integer.parseInt(Foraux[i]);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		List<Relatorio> relatorios;
		if (Init == null || Fim == null)
			relatorios = OrdemBusiness.GerarRelatorioFornecedor(corte,
					FornecedorId);
		else
			relatorios = OrdemBusiness.GerarRelatorioFornecedor(corte,
					FornecedorId, Init, Fim);
		return Serialize(relatorios);
	}

	private String GerarRelatoriosPerdas(HttpServletRequest request) {
		Integer[] Produtos = null;
		String[] Produtosaux = null;

		try {

			if ((!request.getParameter("Init").isEmpty() && !request
					.getParameter("Fim").isEmpty())
					&& (!request.getParameter("Init").equals("null") && !request
							.getParameter("Fim").equals("null"))) {
				Init = sdf.parse(request.getParameter("Init"));
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			Produtosaux = request.getParameterValues("Produtos[]");

			if (!Produtosaux[0].equals("null")) {
				Produtos = new Integer[Produtosaux.length];

				for (int i = 0; i < Produtosaux.length; i++) {
					if (!Produtosaux[i].equals("null"))
						Produtos[i] = Integer.parseInt(Produtosaux[i]
								.toString());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro: " + e.getMessage());
		}
		List<Relatorio> relatorios;
		if (Init == null || Fim == null)
			relatorios = BaixaBusiness.GerarRelatorioPerdas(corte, Produtos);
		else
			relatorios = BaixaBusiness.GerarRelatorioPerdas(corte, Produtos,
					Init, Fim);
		return Serialize(relatorios);
	}

	private String GerarRelatorioBaixa(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String[] Setores = null;
		String[] Setoresaux = null;
		Integer[] Produtos = null;
		String[] Produtosaux = null;

		try {

			if ((!request.getParameter("Init").isEmpty() && !request
					.getParameter("Fim").isEmpty())
					&& (!request.getParameter("Init").equals("null") && !request
							.getParameter("Fim").equals("null"))) {
				Init = sdf.parse(request.getParameter("Init"));
				Fim = sdf.parse(request.getParameter("Fim"));
			}

			Setoresaux = request.getParameterValues("Setores[]");

			if (!Setoresaux[0].equals("null")) {
				Setores = new String[Setoresaux.length];

				for (int i = 0; i < Setoresaux.length; i++) {
					if (!Setoresaux[i].equals("null"))
						Setores[i] = Setoresaux[i].toString();
				}
			}

			Produtosaux = request.getParameterValues("Produtos[]");

			if (!Produtosaux[0].equals("null")) {
				Produtos = new Integer[Produtosaux.length];

				for (int i = 0; i < Produtosaux.length; i++) {
					if (!Produtosaux[i].equals("null"))
						Produtos[i] = Integer.parseInt(Produtosaux[i]
								.toString());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro: " + e.getMessage());
		}
		List<Relatorio> relatorios;
		if (Init == null || Fim == null)
			relatorios = BaixaBusiness.GerarRelatorio(corte, Setores, Produtos);
		else
			relatorios = BaixaBusiness.GerarRelatorio(corte, Setores, Produtos,
					Init, Fim);
		return Serialize(relatorios);
	}

	private String Serialize(List<Relatorio> relatorios) {
		String msg = "";
		Map<String, List<Relatorio>> mapRel = new HashMap<String, List<Relatorio>>();
		Map<Long, String> xlabels = new HashMap<Long, String>();

		for (Relatorio i : relatorios) {
			xlabels.put(i.getColuna(), i.getXLabel());

			if (mapRel.get(i.getLabel()) != null) {
				mapRel.get(i.getLabel()).add(i);
			} else {
				List<Relatorio> rel = new ArrayList<Relatorio>();
				rel.add(i);
				mapRel.put(i.getLabel(), rel);
			}
		}

		msg += "{\"labels\":[";
		Iterator<Entry<Long, String>> iterator = xlabels.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Long, String> entry = (Map.Entry<Long, String>) iterator
					.next();
			msg += "\"" + entry.getValue() + "\"";

			if (iterator.hasNext())
				msg += ",";
		}

		msg += "],";

		msg += "\"datasets\": [";
		Iterator<Entry<String, List<Relatorio>>> iMap = mapRel.entrySet()
				.iterator();
		while (iMap.hasNext()) {
			Map.Entry<String, List<Relatorio>> entry = (Map.Entry<String, List<Relatorio>>) iMap
					.next();

			String nome = entry.getKey();

			msg += "{\"label\":\"" + nome + "\", \"data\":[";

			Iterator<Entry<Long, String>> order = xlabels.entrySet().iterator();

			while (order.hasNext()) {
				Map.Entry<Long, String> eorder = (Map.Entry<Long, String>) order
						.next();

				boolean encontrou = false;

				Iterator<Relatorio> iRelatorios = entry.getValue().iterator();

				while (iRelatorios.hasNext()) {
					Relatorio i = (Relatorio) iRelatorios.next();
					if (eorder.getKey() == i.getColuna()) {
						encontrou = true;
						msg += i.getQuantidade().toString();
					}
				}

				if (!encontrou)
					msg += 0;

				if (order.hasNext())
					msg += ",";
			}

			msg += "]}";

			if (iMap.hasNext()) {
				msg += ",";
			}
		}

		msg += "]}";

		return msg;
	}
	
	
	private String SerializeStatus(List<Relatorio> relatorios) {
		String msg = "";
		Map<String, List<Relatorio>> mapRel = new HashMap<String, List<Relatorio>>();
		Map<Long, String> xlabels = new HashMap<Long, String>();

		for (Relatorio i : relatorios) {
			xlabels.put(i.getColuna(), i.getXLabel());

			if (mapRel.get(i.getLabel()) != null) {
				mapRel.get(i.getLabel()).add(i);
			} else {
				List<Relatorio> rel = new ArrayList<Relatorio>();
				rel.add(i);
				mapRel.put(i.getLabel(), rel);
			}
		}

		msg += "{\"labels\":[";
		Iterator<Entry<Long, String>> iterator = xlabels.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Long, String> entry = (Map.Entry<Long, String>) iterator
					.next();
			msg += "\"" + entry.getValue() + "\"";

			if (iterator.hasNext())
				msg += ",";
		}

		msg += "],";

		msg += "\"datasets\": [";
		Iterator<Entry<String, List<Relatorio>>> iMap = mapRel.entrySet()
				.iterator();
		while (iMap.hasNext()) {
			Map.Entry<String, List<Relatorio>> entry = (Map.Entry<String, List<Relatorio>>) iMap
					.next();

			String nome = entry.getKey();
			int idx = Integer.parseInt(nome);
			Status st = Status.values()[idx];

			msg += "{\"label\":\"" + st.toString() + "\", \"data\":[";

			Iterator<Entry<Long, String>> order = xlabels.entrySet().iterator();

			while (order.hasNext()) {
				Map.Entry<Long, String> eorder = (Map.Entry<Long, String>) order
						.next();

				boolean encontrou = false;

				Iterator<Relatorio> iRelatorios = entry.getValue().iterator();

				while (iRelatorios.hasNext()) {
					Relatorio i = (Relatorio) iRelatorios.next();
					if (eorder.getKey() == i.getColuna()) {
						encontrou = true;
						msg += i.getQuantidade().toString();
					}
				}

				if (!encontrou)
					msg += 0;

				if (order.hasNext())
					msg += ",";
			}

			msg += "]}";

			if (iMap.hasNext()) {
				msg += ",";
			}
		}

		msg += "]}";

		return msg;
	}

//	private String SerializeStatus(List<Relatorio> map) {
//		List<Relatorio> aux;
//		String msg = "";
//		int count = 0;
//		Map<String, List<Relatorio>> mapRel = new HashMap<String, List<Relatorio>>();
//		Map<String, String> xlabels = new HashMap<String, String>();
//
//		
//
//		msg += "],";
//
//		msg += "\"data\": [";
//
//		for (Map.Entry<String, List<Relatorio>> entry : mapRel.entrySet()) {
//
//			aux = entry.getValue();
//			String nome = entry.getKey();
//			int idx = Integer.parseInt(nome);
//			Status st = Status.values()[idx];
//
//			msg += "{\"label\":\"" + st.toString() + "\", \"data\":[";
//
//			for (Relatorio i : aux) {
//				msg += "[" + i.getColuna().toString() + ", "
//						+ i.getQuantidade().toString() + "]";
//				if (aux.size() != aux.indexOf(i) + 1)
//					msg += ",";
//			}
//
//			msg += "]}";
//
//			if (count != (mapRel.size() - 1)) {
//				msg += ",";
//			}
//
//			count++;
//		}
//
//		msg += "]}";
//
//		return msg;
//
//	}

	
	private String SerializeValor(List<Relatorio> relatorios) {
		String msg = "";
		Map<String, List<Relatorio>> mapRel = new HashMap<String, List<Relatorio>>();
		Map<Long, String> xlabels = new HashMap<Long, String>();

		for (Relatorio i : relatorios) {
			xlabels.put(i.getColuna(), i.getXLabel());

			if (mapRel.get(i.getLabel()) != null) {
				mapRel.get(i.getLabel()).add(i);
			} else {
				List<Relatorio> rel = new ArrayList<Relatorio>();
				rel.add(i);
				mapRel.put(i.getLabel(), rel);
			}
		}

		msg += "{\"labels\":[";
		Iterator<Entry<Long, String>> iterator = xlabels.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Long, String> entry = (Map.Entry<Long, String>) iterator
					.next();
			msg += "\"" + entry.getValue() + "\"";

			if (iterator.hasNext())
				msg += ",";
		}

		msg += "],";

		msg += "\"datasets\": [";
		Iterator<Entry<String, List<Relatorio>>> iMap = mapRel.entrySet()
				.iterator();
		while (iMap.hasNext()) {
			Map.Entry<String, List<Relatorio>> entry = (Map.Entry<String, List<Relatorio>>) iMap
					.next();

			String nome = entry.getKey();
			
			msg += "{\"label\":\"" + nome + "\", \"data\":[";

			Iterator<Entry<Long, String>> order = xlabels.entrySet().iterator();

			while (order.hasNext()) {
				Map.Entry<Long, String> eorder = (Map.Entry<Long, String>) order
						.next();

				boolean encontrou = false;

				Iterator<Relatorio> iRelatorios = entry.getValue().iterator();

				while (iRelatorios.hasNext()) {
					Relatorio i = (Relatorio) iRelatorios.next();
					if (eorder.getKey() == i.getColuna()) {
						encontrou = true;
						msg += String.valueOf(i.getValor()); // i.getQuantidade().toString();
					}
				}

				if (!encontrou)
					msg += 0;

				if (order.hasNext())
					msg += ",";
			}

			msg += "]}";

			if (iMap.hasNext()) {
				msg += ",";
			}
		}

		msg += "]}";

		return msg;
	}
	
//	private String SerializeValor(List<Relatorio> map) {
//		List<Relatorio> aux;
//		String msg = "";
//		int count = 0;
//		Map<String, List<Relatorio>> mapRel = new HashMap<String, List<Relatorio>>();
//
//		for (Relatorio i : map) {
//			if (mapRel.get(i.getLabel()) != null
//					&& mapRel.get(i.getLabel()).size() > 0) {
//				aux = mapRel.get(i.getLabel());
//				aux.add(i);
//				mapRel.put(i.getLabel(), aux);
//			} else {
//				aux = new ArrayList<Relatorio>();
//				aux.add(i);
//				mapRel.put(i.getLabel(), aux);
//			}
//		}
//
//		Map<String, String> xlabels = new HashMap<String, String>();
//		for (Relatorio i : map) {
//			xlabels.put(i.getColuna().toString(), i.getXLabel());
//		}
//
//		msg += "{\"xlabels\":[";
//
//		Iterator<Entry<String, String>> iterator = xlabels.entrySet()
//				.iterator();
//		while (iterator.hasNext()) {
//			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator
//					.next();
//			msg += "\"" + entry.getValue() + "\"";
//
//			if (iterator.hasNext())
//				msg += ",";
//		}
//
//		msg += "],";
//
//		msg += "\"data\": [";
//
//		for (Map.Entry<String, List<Relatorio>> entry : mapRel.entrySet()) {
//
//			aux = entry.getValue();
//			String nome = entry.getKey();
//			msg += "{\"label\":\"" + nome + "\", \"data\":[";
//
//			for (Relatorio i : aux) {
//				msg += "[" + i.getColuna().toString() + ", "
//						+ String.valueOf(i.getValor()) + "]";
//				if (aux.size() != aux.indexOf(i) + 1)
//					msg += ",";
//			}
//
//			msg += "]}";
//
//			if (count < (mapRel.size() - 1)) {
//				msg += ",";
//			}
//
//			count++;
//		}
//
//		msg += "]}";
//
//		return msg;
//
//	}
}

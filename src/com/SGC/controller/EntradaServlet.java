package com.SGC.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SGC.business.BaixaBusiness;
import com.SGC.business.EntradaBusiness;
import com.SGC.business.OrdemBusiness;
import com.SGC.business.ProdutoBusiness;
import com.SGC.domain.Baixa;
import com.SGC.domain.Entrada;
import com.SGC.domain.ItemBaixa;
import com.SGC.domain.Ordem;
import com.SGC.domain.Produto;
import com.SGC.domain.Status;
import com.SGC.extend.Conteiner;
import com.SGC.extend.HashConteiner;

public class EntradaServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		switch (userPath) {
		case "Entrada":
			List<Produto> produtos = ProdutoBusiness.ObterLista(true);
			List<Ordem> Ordens = OrdemBusiness.ObterLista(Status.Pendente); 

			request.setAttribute("Produtos", produtos);
			request.setAttribute("Ordens", Ordens);

			Page = userPath + ".jsp";
			break;
		case "IncluirEntrada":
			try {
				List<Entrada> baixa = EntradaBusiness.Converter(request);

				EntradaBusiness.ListaAdd(baixa);

				msg = "{\"msg\" : \"Incluido com sucesso\", \"sucess\":true, \"url\": \"Entrada\"}";

			} catch (Exception e) {
				// TODO: handle exception
				msg = "{\"msg\" : \"Erro não identificado Erro: "
						+ e.getMessage() + "\", \"sucess\":false}";
			}

			break;

		case "ObterEntradas":

			int IdProduto = Integer.parseInt(request.getParameter("Id"));

			Produto produto = ProdutoBusiness.Obter(IdProduto);

			Conteiner<Entrada> entradas = new HashConteiner<Entrada>(
					EntradaBusiness.ObterEntradasPorProduto(produto));

			// long sumProduto = ProdutoBusiness.Estoque(produto);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			msg = "{\"Produto\" : \"" + produto.getNome() + "\", \"Id\" : \""
					+ produto.getId() + "\", \"Entradas\" : [";

			for (Iterator<Entrada> iterator = entradas.iterator(); iterator
					.hasNext();) {
				Entrada entrada = (Entrada) iterator.next();

				Conteiner<ItemBaixa> baixas = new HashConteiner<ItemBaixa>(
						entrada.getBaixas());
				double qtd = entrada.getQtdLote() - baixas.Sum("Quantidade");
				if (qtd > 0) {
					msg += "{\"Id\" : " + entrada.getId()
							+ ", \"Validade\" : \""
							+ sdf.format(entrada.getValidade())
							+ "\", \"Qtd\" : " + qtd + "}";

					if (entradas.indexOf(entrada) + 1 != entradas.size())
						msg += ",";
				}
			}

			msg += "]}";
			break;
		}

		if (Page != "") {
			try {
				if (IsLogin)
					request.getRequestDispatcher(Page).forward(request,
							response);
			} catch (Exception e) {
				throw e;
			}
		} else {
			out.println(msg);
		}
	}

}

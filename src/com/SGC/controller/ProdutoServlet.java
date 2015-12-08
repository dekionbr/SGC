package com.SGC.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SGC.business.FuncionarioBusiness;
import com.SGC.business.ProdutoBusiness;
import com.SGC.domain.Produto;

public class ProdutoServlet extends GenericServlet {

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

		Produto produto;
		List<Produto> produtos;

		int id = 0;
		if (request.getParameter("p") != null && !request.getParameter("p").isEmpty()) {
			id = Integer.parseInt(request.getParameter("p"));
		}
		
		boolean buscaAtivos = true;
		
		if (request.getParameter("ativo") != null && !request.getParameter("ativo").isEmpty()) {
			buscaAtivos = Boolean.parseBoolean(request.getParameter("ativo"));
		}

		String Page = "";
		String msg = "";

		switch (userPath) {
		case "Produtos":
			produtos = ProdutoBusiness.ObterLista(buscaAtivos);
			request.setAttribute("Produtos", produtos);
			Page = userPath + ".jsp";

			break;
		case "Produto":
			produto = ProdutoBusiness.Obter(id);
			produto = produto != null ? produto : new Produto();
			request.setAttribute("Produto", produto);
			Page = userPath + ".jsp";

			break;
		case "IncluirProduto":
			produto = ProdutoBusiness.Converter(request);

			try {

				if (produto.getId() == 0) {
					produto.setStatus(true);
					ProdutoBusiness.Add(produto);
					msg = "{\"msg\" : \"Incluido com sucesso\", \"sucess\":true, \"url\": \"Produtos\"}";
				} else {
					ProdutoBusiness.Update(produto);
					msg = "{\"msg\" : \"Editado com sucesso\", \"sucess\":true, \"url\": \"Produtos\"}"; // "Editado com sucesso";
				}

			} catch (Exception e) {
				msg = "{\"msg\" = \"houve um erro, tente novamente mais tarde\", \"sucess\":false}";
			}
			break;

		case "DesativarProduto":
			try {
				produto = ProdutoBusiness.Obter(id);
				produto.setStatus(false);
				ProdutoBusiness.Update(produto);
				msg = "{\"msg\" : \"Desativado com sucesso\", \"sucess\":true, \"url\": \"Produtos\"}"; // "Desativado com sucesso";

			} catch (Exception e) {
				msg = "{\"msg\" : \"houve um erro ao tentar desativar, tente novamente mais tarde\", \"sucess\":false}";
			}
			break;

		}

		if (Page != "") {
			try {
				if (IsLogin)
				request.getRequestDispatcher(Page).forward(request, response);
			} catch (Exception e) {
				throw e;
			}
		} else {
			out.println(msg);
		}
	}
}

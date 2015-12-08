package com.SGC.controller;

import java.util.List;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SGC.business.FornecedorBusiness;
import com.SGC.domain.Fornecedor;

public class FornecedorServlet extends GenericServlet {

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
		Fornecedor fornecedor = new Fornecedor();
		List<Fornecedor> lista;

		int id = 0;
		if (request.getParameter("f") != null && !request.getParameter("f").isEmpty()) {
			id = Integer.parseInt(request.getParameter("f"));
		}
		
		boolean buscaAtivos = true;
		
		if (request.getParameter("ativo") != null && !request.getParameter("ativo").isEmpty()) {
			buscaAtivos = Boolean.parseBoolean(request.getParameter("ativo"));
		}

		String msg = "";

		switch (userPath) {
		case "Fornecedores":
			lista = FornecedorBusiness.ObterLista(buscaAtivos);
			request.setAttribute("Fornecedores", lista);
			Page = userPath + ".jsp";
			break;
		case "Fornecedor":
			fornecedor = FornecedorBusiness.Obter(id);
			request.setAttribute("Fornecedor", fornecedor);
			Page = userPath + ".jsp";
			break;

		case "IncluirFornecedor":
			fornecedor = FornecedorBusiness.Converter(request);

			if (fornecedor.getId() == 0) {
				FornecedorBusiness.Add(fornecedor);
				msg = "{\"msg\" : \"Incluido com sucesso\", \"sucess\":true, \"url\": \"Fornecedores\"}";
			} else {
				FornecedorBusiness.Update(fornecedor);
				msg = "{\"msg\" : \"Editado com sucesso\", \"sucess\":true, \"url\": \"Fornecedores\"}";
			}
			break;

		case "DesligarFornecedor":
			try {
				fornecedor = FornecedorBusiness.Obter(id);
				fornecedor.setStatus(false);
				FornecedorBusiness.Update(fornecedor);
				msg = "{\"msg\" : \"Desativado com sucesso\", \"sucess\":true, \"url\": \"Fornecedores\"}";

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

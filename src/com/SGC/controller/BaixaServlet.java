package com.SGC.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SGC.business.BaixaBusiness;
import com.SGC.business.EntradaBusiness;
import com.SGC.business.ProdutoBusiness;
import com.SGC.domain.Baixa;
import com.SGC.domain.Produto;

public class BaixaServlet extends GenericServlet {

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
		case "Baixa":
			List<Produto> produtos = EntradaBusiness.ObterProdutosEmEstoque(); //ProdutoBusiness.ObterLista(true);
			
			request.setAttribute("Produtos", produtos);
			
			Page = userPath + ".jsp";
			break;
		case "IncluirBaixa":
			try {
				Baixa baixa = BaixaBusiness.Converter(request);
				
				BaixaBusiness.Add(baixa);
				

				msg = "{\"msg\" : \"Incluido com sucesso\", \"sucess\":true, \"url\": \"Baixa\"}";

			} catch (Exception e) {
				// TODO: handle exception
				msg = "{\"msg\" : \"Erro não identificado Erro: "
						+ e.getMessage()
						+ "\", \"sucess\":true, \"url\": \"Baixa\"}";
			}

			break;
		}

		if (Page != "") {
			try {
				if(IsLogin)
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

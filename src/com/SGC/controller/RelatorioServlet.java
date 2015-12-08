package com.SGC.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import com.SGC.business.*;
import com.SGC.domain.*;

public class RelatorioServlet extends GenericServlet {

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {

		super.processRequest(request, response);

		String url[] = request.getServletPath().split("/");
		String userPath = url[1];
		String Page = "";
		
		List<Produto> Produtos;
		List<Fornecedor> Fornecedores;
		List<String> Setores;
		
		switch (userPath) {		
		case "RelatoriosOrdemDeCompra":			
			Fornecedores = FornecedorBusiness.ObterLista();
			request.setAttribute("Fornecedores", Fornecedores);
			break;
		case "RelatoriosComparativo":
			Produtos = ProdutoBusiness.ObterLista();
			request.setAttribute("Produtos", Produtos);
			break;
		case "RelatoriosFornecedor":
			Fornecedores = FornecedorBusiness.ObterLista();
			request.setAttribute("Fornecedores", Fornecedores);
			break;
		case "RelatoriosBaixa":
			Setores = BaixaBusiness.ObterListaSetores();
			request.setAttribute("Setores", Setores);
			
			Produtos = ProdutoBusiness.ObterLista();			
			request.setAttribute("Produtos", Produtos);
			break;
		case "RelatoriosEntrada":
			Produtos = ProdutoBusiness.ObterLista();
			request.setAttribute("Produtos", Produtos);
			break;
		case "RelatoriosPerdas":
			Produtos = ProdutoBusiness.ObterLista();
			request.setAttribute("Produtos", Produtos);
			break;
		case "RelatoriosValidade":
			Produtos = ProdutoBusiness.ObterLista();
			request.setAttribute("Produtos", Produtos);
			break;
		case "RelatoriosSugestaoDeCompra":
			List<Relatorio> Relatorios = EntradaBusiness.ObterSugestao(); 
			request.setAttribute("sugestoes", Relatorios);
			break;
		}
		
		Page = userPath + ".jsp";

			try {
				if (IsLogin)
				request.getRequestDispatcher(Page).forward(request, response);
			} catch (Exception e) {
				response.encodeRedirectURL("Dashboard");
			}
		
	}
}

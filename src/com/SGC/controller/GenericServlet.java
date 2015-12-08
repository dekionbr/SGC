package com.SGC.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SGC.domain.Funcionario;

public class GenericServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean IsLogin = false;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {

		try {

			HttpSession session = request.getSession(true);

			String url[] = request.getServletPath().split("/");
			String userPath = url[1];

			if (session.getAttribute("usuario") == null) {
				response.sendRedirect("Login");
			}

			Funcionario user = (Funcionario) session.getAttribute("usuario");

			if (user.getTipoUsuario().getValor().equals("Gestor")) {
				if (!userPath.equals("/") && !userPath.equals("Funcionarios")
						&& !userPath.equals("Dashboard")
						&& !userPath.equals("Funcionario")
						&& !userPath.equals("IncluirFuncionario")
						&& !userPath.equals("DesativarFuncionario")
						&& !userPath.equals("Fornecedores")
						&& !userPath.equals("Fornecedor")
						&& !userPath.equals("IncluirFornecedor")
						&& !userPath.equals("DesligarFornecedor")
						&& !userPath.equals("Produtos")
						&& !userPath.equals("Produto")
						&& !userPath.equals("IncluirProduto")
						&& !userPath.equals("DesativarProduto")
						&& !userPath.equals("RelatoriosComparativo")
						&& !userPath.equals("RelatoriosFornecedor")
						&& !userPath.equals("RelatoriosBaixa")
						&& !userPath.equals("RelatoriosValidade")
						&& !userPath.equals("GerarRelatoriosComparativo")
						&& !userPath.equals("GerarRelatoriosFornecedor")
						&& !userPath.equals("GerarRelatoriosBaixa")
						&& !userPath.equals("GerarRelatoriosValidade"))
					response.sendRedirect("Login");

			} else if (user.getTipoUsuario().getValor().equals("Comprador")) {
				if (!userPath.equals("/") && !userPath.equals("Dashboard")
						&& !userPath.equals("OrdemDeCompra")
						&& !userPath.equals("Entrada")
						&& !userPath.equals("RelatoriosSugestaoDeCompra")
						&& !userPath.equals("RelatoriosOrdemDeCompra")
						&& !userPath.equals("RelatoriosEntrada")
						&& !userPath.equals("GerarRelatoriosSugestaoDeCompra")
						&& !userPath.equals("GerarRelatoriosOrdemDeCompra")
						&& !userPath.equals("GerarRelatoriosEntrada"))
					response.sendRedirect("Login");
			}else if (user.getTipoUsuario().getValor().equals("Atendente")) {
				if (!userPath.equals("/") && !userPath.equals("Dashboard")
						&& !userPath.equals("Baixa")
						&& !userPath.equals("RelatoriosPerdas")
						&& !userPath.equals("GerarRelatoriosPerdas"))
					response.sendRedirect("Login");
			}
			
			IsLogin = true;

		} catch (Exception e) {
			response.sendRedirect("Login");
			return;
		}

	}
}

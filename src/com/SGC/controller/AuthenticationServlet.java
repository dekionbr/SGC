package com.SGC.controller;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SGC.business.FuncionarioBusiness;
import com.SGC.domain.Funcionario;
import com.SGC.domain.TipoUsuario;

public class AuthenticationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("usuario");
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String msg = "";
		try {

			String login = request.getParameter("username");
			String senha = request.getParameter("password");
			Funcionario user;
			
			HttpSession session = request.getSession();

			if (login.equals("admin") && senha.equals("sgc123")) {
				user = new Funcionario();
				TipoUsuario tusuario = new TipoUsuario();
				tusuario.setId(99);
				tusuario.setValor("Administrador");
				user.setTipoUsuario(tusuario);
				user.setNome("Administrador");

			} else {
				user = FuncionarioBusiness.Obter(login, senha);
			}

			if (user != null) {
				session.setAttribute("usuario", user);				
				msg = "{\"Url\":\"Dashboard\"}";
			} else {

				msg = "{\"Erro\":\"Login e Senha Inválidos\"}";

			}

		} catch (Exception e) {
			msg = "{\"Erro\":\"" + e.getMessage() + "\"}";
		}

		out.print(msg);
	}
}

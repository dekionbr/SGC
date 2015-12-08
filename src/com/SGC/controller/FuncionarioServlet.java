package com.SGC.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.SGC.business.FuncionarioBusiness;
import com.SGC.business.TipoUsuarioBusiness;
import com.SGC.domain.Funcionario;
import com.SGC.domain.TipoUsuario;

public class FuncionarioServlet extends GenericServlet {

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
		Funcionario funcionario;
		List<Funcionario> lista;

		int id = 0;
		if (request.getParameter("id") != null
				&& !request.getParameter("id").isEmpty()) {
			id = Integer.parseInt((String) request.getParameter("id"));
		}

		boolean buscaAtivos = true;

		if (request.getParameter("ativo") != null
				&& !request.getParameter("ativo").isEmpty()) {
			buscaAtivos = Boolean.parseBoolean((String) request.getParameter("ativo"));
		}

		String Page = "";
		String msg = "";

		switch (userPath) {
		case "Funcionarios":
			lista = FuncionarioBusiness.ObterLista(buscaAtivos);
			request.setAttribute("Funcionarios", lista);
			Page = userPath + ".jsp";
			break;
		case "Funcionario":
			funcionario = FuncionarioBusiness.Obter(id);
			funcionario = funcionario != null ? funcionario : new Funcionario();
			request.setAttribute("Funcionario", funcionario);
			List<TipoUsuario> TipoUsuarios = TipoUsuarioBusiness.ObterLista();
			request.setAttribute("TipoUsuarios", TipoUsuarios);
			Page = userPath + ".jsp";
			break;

		case "IncluirFuncionario":
			funcionario = FuncionarioBusiness.Converter(request);
			try {

				if (funcionario.getId() == 0) {
					FuncionarioBusiness.Add(funcionario);
					msg = "{\"msg\" : \"Incluido com sucesso\", \"sucess\":true, \"url\": \"Funcionarios\"}";
				} else {
					FuncionarioBusiness.Update(funcionario);
					msg = "{\"msg\" : \"Editado com sucesso\", \"sucess\":true, \"url\": \"Funcionarios\"}"; // "Editado com sucesso";
				}

			} catch (Exception e) {
				msg = "{\"msg\" = \"houve um erro, tente novamente mais tarde\", \"sucess\":false}";
			}
			break;

		case "DesativarFuncionario":
			try {
				funcionario = FuncionarioBusiness.Obter(id);
				funcionario.setStatus(false);
				FuncionarioBusiness.Update(funcionario);
				msg = "{\"msg\" : \"Desativado com sucesso\", \"sucess\":true, \"url\": \"Funcionarios\"}"; // "Desativado com sucesso";

			} catch (Exception e) {
				msg = "{\"msg\" : \"houve um erro ao tentar desativar, tente novamente mais tarde\", \"sucess\":false, \"url\": \"Funcionarios\"}";
			}
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

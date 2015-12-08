package com.SGC.controller;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SGC.business.BaixaBusiness;
import com.SGC.business.ContatoBusiness;
import com.SGC.domain.Baixa;

public class ContatoServlet extends GenericServlet {

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
		String msg = "{\"msg\" : \"Erro 404\"";

		switch (userPath) {
		case "DeletarContato":
			try {
				
				int id = Integer.parseInt(request.getParameter("id"));
				ContatoBusiness.Delete(id);
				
				msg = "{\"msg\" : \"Excluido com sucesso\", \"sucess\":true}";

			} catch (Exception e) {
				// TODO: handle exception
				msg = "{\"msg\" : \"Erro identificado : "
						+ e.getMessage()
						+ "\", \"sucess\":false}";
			}
			break;
		}

		out.println(msg);
	}

}

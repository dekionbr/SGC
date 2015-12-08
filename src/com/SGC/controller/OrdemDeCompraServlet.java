package com.SGC.controller;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SGC.business.FornecedorBusiness;
import com.SGC.business.OrdemBusiness;
import com.SGC.business.ProdutoBusiness;
import com.SGC.domain.Fornecedor;
import com.SGC.domain.Funcionario;
import com.SGC.domain.ItemOrdem;
import com.SGC.domain.Ordem;
import com.SGC.domain.Produto;
import com.SGC.domain.TipoUsuario;

public class OrdemDeCompraServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {

		super.processRequest(request, response);

		HttpSession session = request.getSession(true);

		PrintWriter out = response.getWriter();
		String url[] = request.getServletPath().split("/");
		String userPath = url[1];
		String Page = "";
		String msg = "";

		switch (userPath) {
		case "OrdemDeCompra":
			List<Fornecedor> fornecedores = FornecedorBusiness.ObterLista(true);
			List<Produto> produtos = ProdutoBusiness.ObterLista(true);

			request.setAttribute("Fornecedores", fornecedores);
			request.setAttribute("Produtos", produtos);

			Page = userPath + ".jsp";
			break;
		case "IncluirOrdemDeCompra":
			try {
				Ordem ordem = OrdemBusiness.Converter(request);

				Funcionario funcionario = (Funcionario) session
						.getAttribute("usuario"); // Obter o Usuário que fez o
													// cadastro.

				if (funcionario.getTipoUsuario().getId() != 99)
					ordem.setUsuario(funcionario);

				OrdemBusiness.Add(ordem);

				// Util.Mail(Fornecedor.getEmail(),Fornecedor.getNomeRepresentante(),
				// template); implementar posteriormente;
				msg = "{\"msg\" : \"Incluido com sucesso\", \"sucess\":true, \"url\": \"OrdemDeCompra\"}";

			} catch (Exception e) {
				// TODO: handle exception
				msg = "{\"msg\" : \"Erro não identificado Erro: "
						+ e.getMessage()
						+ "\", \"sucess\":true, \"url\": \"OrdemDeCompra\"}";
			}

			break;

		case "ObterItensOrdem":

			int idOrdem = Integer.parseInt(request.getParameter("Id"));

			List<ItemOrdem> itens = OrdemBusiness.ObterListaItens(idOrdem);

			msg = "[";

			for (Iterator<ItemOrdem> iterator = itens.iterator(); iterator.hasNext();) {
				ItemOrdem itemOrdem = (ItemOrdem) iterator.next();
				msg += "{\"Id\":" + itemOrdem.getProduto().getId() + ","
						+ "\"Produto\":\"" + itemOrdem.getProduto().getNome() + "\","
						+ "\"Ordem\":" + itemOrdem.getId() + ", \"Qtd\":"
						+ itemOrdem.getQuantidade() + "}";
				if(itens.indexOf(itemOrdem) < (itens.size() - 1))
						msg += ",";
			}

			msg += "]";

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

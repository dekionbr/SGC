package com.SGC.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SGC.business.BaixaBusiness;
import com.SGC.business.EntradaBusiness;
import com.SGC.business.FornecedorBusiness;
import com.SGC.business.OrdemBusiness;

public class DashboardServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {

		super.processRequest(request, response);

		String url[] = request.getServletPath().split("/");
		String userPath = url[1] + ".jsp"; // Dashboard.jsp

		//int CountProduto = EntradaBusiness.Count();
		int TotalMedicamento = EntradaBusiness.TotalMedicamentos();
		int TotalMaterial = EntradaBusiness.TotalMaterias();
		int AVencer = EntradaBusiness.AVencer();
		int PedidosHoje = OrdemBusiness.PedidosHoje();
		int SetoresAtendidos = BaixaBusiness.SetoresAtendidos();
		int TotalFornecedores = FornecedorBusiness.TotalFornecedores();

		request.setAttribute("TotalMedicamento", TotalMedicamento);
		request.setAttribute("TotalMaterial", TotalMaterial);
		request.setAttribute("AVencer", AVencer);
		request.setAttribute("PedidosHoje", PedidosHoje);
		request.setAttribute("SetoresAtendidos", SetoresAtendidos);
		request.setAttribute("TotalFornecedores", TotalFornecedores);

		try {
			if (IsLogin)
				request.getRequestDispatcher(userPath).forward(request,
						response);
		} catch (Exception e) {
			throw e;
		}

	}
}

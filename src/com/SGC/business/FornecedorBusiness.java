package com.SGC.business;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.SGC.dao.FornecedorDAO;
import com.SGC.domain.Contato;
import com.SGC.domain.Fornecedor;
import com.SGC.domain.TipoContato;

public class FornecedorBusiness {
	public static List<Fornecedor> ObterLista(boolean buscaAtivos) {
		List<Fornecedor> fornecedores = null;
		try {
			FornecedorDAO forDao = FornecedorDAO.getInstance();
			fornecedores = forDao.getLista(buscaAtivos);

			if (fornecedores == null)
				fornecedores = new ArrayList<Fornecedor>();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return fornecedores;
	}

	public static List<Fornecedor> ObterLista() {
		List<Fornecedor> fornecedores = null;
		try {
			FornecedorDAO forDao = FornecedorDAO.getInstance();
			fornecedores = forDao.getSession()
								 .createCriteria(Fornecedor.class)
								 .addOrder(Order.asc("NomeFantasia")).list();

			if (fornecedores == null)
				fornecedores = new ArrayList<Fornecedor>();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return fornecedores;
	}

	public static Fornecedor Obter(int id) {
		Fornecedor fornecedor = null;
		try {
			FornecedorDAO forDao = FornecedorDAO.getInstance();
			fornecedor = forDao.get(id);

			if (fornecedor == null)
				fornecedor = new Fornecedor();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fornecedor;
	}

	public static void Add(Fornecedor fornecedor) {
		try {
			FornecedorDAO fornecedorDAO = FornecedorDAO.getInstance();
			fornecedorDAO.add(fornecedor);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Update(Fornecedor fornecedor) {
		try {

			FornecedorDAO fornecedorDAO = FornecedorDAO.getInstance();
			fornecedorDAO.Update(fornecedor);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static Fornecedor Converter(HttpServletRequest request) {

		Fornecedor Fornecedor = new Fornecedor();

		if (request.getParameter("Id") != ""
				&& request.getParameter("Id") != null)
			Fornecedor.setId(Integer.parseInt(request.getParameter("Id")));

		String Nome = request.getParameter("Nome");
		Fornecedor.setNomeFantasia(Nome);

		String RazaoSocial = request.getParameter("RazaoSocial");
		Fornecedor.setRazaoSocial(RazaoSocial);

		String CNPJ = request.getParameter("CNPJ").replaceAll("\\D", "");
		Fornecedor.setCNPJ(CNPJ);

		String endereco = request.getParameter("Endereco");
		Fornecedor.setEndereco(endereco);

		String numero = request.getParameter("Numero").replaceAll("\\D", "");
		Fornecedor.setNumero(numero);

		String cidade = request.getParameter("Cidade");
		Fornecedor.setCidade(cidade);

		String UF = request.getParameter("UF");
		Fornecedor.setUF(UF);

		String bairro = request.getParameter("Bairro");
		Fornecedor.setBairro(bairro);

		String CEP = request.getParameter("CEP").replaceAll("\\D", "");
		Fornecedor.setCEP(CEP);

		Boolean status = Boolean.parseBoolean(request.getParameter("status"));
		Fornecedor.setStatus(status);

		String representante = request.getParameter("representante");
		Fornecedor.setNomeRepresentante(representante);

		List<Contato> contatos = new ArrayList<Contato>();

		int indexOld = -1;

		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			if (key.contains("Contato")) {

				int char1 = key.indexOf("[") + 1;
				int char2 = key.indexOf("]");

				int index = Integer.parseInt(key.substring(char1, char2));

				if (indexOld != index) {
					Contato contato = new Contato();
					
					contato.setFornecedor(Fornecedor);
					
					TipoContato tipoContato = TipoContato.values()[Integer
							.parseInt(request.getParameter("Contato["+index+"].TipoContato"))];
					
					contato.setTipoContato(tipoContato);

					String valor = request.getParameter("Contato["+index+"].Valor");
					contato.setValor(valor);
					
					int id = Integer.parseInt(request.getParameter("Contato["+index+"].Id"));
					
					contato.setId(id);
					
					contatos.add(contato);
					
					indexOld = index;
				}
			}
		}

		Fornecedor.setContatos(contatos);

		return Fornecedor;
	}

	public static int TotalFornecedores() {
		int count = 0;
		try {
			FornecedorDAO dao = FornecedorDAO.getInstance();
			
			Criteria crid = dao.getSession().createCriteria(Fornecedor.class);
			
			count = ((Number) crid.add(Restrictions.eq("Status", true)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}
}

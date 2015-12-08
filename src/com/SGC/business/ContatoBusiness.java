package com.SGC.business;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.SGC.dao.ContatoDAO;
import com.SGC.domain.Contato;

public class ContatoBusiness {
	public static List<Contato> ObterLista() {
		List<Contato> Contatos = null;

		ContatoDAO forDao = ContatoDAO.getInstance();
		Contatos = forDao.getLista();

		return Contatos;
	}

	public static List<Contato> ObterListaDeFornecedor(int Id) throws Exception {
		List<Contato> Contatos = null;

		ContatoDAO forDao = ContatoDAO.getInstance();
		Contatos = forDao.getSession().createCriteria(Contato.class)
				.add(Restrictions.eq("Fornecedor_Id", Id)).list();

		return Contatos;
	}

	public static void Delete(int id) throws Exception {
		Contato contato;

		ContatoDAO contatoDao = ContatoDAO.getInstance();
		
		contato = contatoDao.get(id);
		
		if (contato != null) {			
			contatoDao.Delete(contato);	
		} else {
			throw new Exception("Contato Deletado anteriormente em outra ação");
		}

	}
}

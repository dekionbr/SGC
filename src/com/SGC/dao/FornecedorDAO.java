package com.SGC.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.SGC.domain.Fornecedor;

public class FornecedorDAO extends GenericDAO<Fornecedor> {

	private static FornecedorDAO instance = null;

	public void add(Fornecedor fornecedor) {
		try {
			super.add(fornecedor);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> getLista(boolean buscaAtivos) {

		try {

			Criteria c = super.getSession().createCriteria(Fornecedor.class);

			
			List<Fornecedor> users = c.add(Restrictions.eq("Status", buscaAtivos)).list();
								  

			if (users.size() > 0) {
				return users;
			} else {
				return null;
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public List<Fornecedor> getLista() {

		try {

			List<Fornecedor> list = getLista(Fornecedor.class);

			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public Fornecedor get(int id) {

		try {

			Fornecedor fornecedor = get(Fornecedor.class, id);			
			return fornecedor;

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public void Update(Fornecedor fornecedor) {
		try {
			super.Update(fornecedor);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static FornecedorDAO getInstance() {
		if (instance == null) {
			instance = new FornecedorDAO();
		}
		return instance;
	}
}

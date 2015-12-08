package com.SGC.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.SGC.domain.Funcionario;
import com.SGC.domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {
	private static ProdutoDAO instance = null;

	public void add(Produto produto) {
		try {
			super.add(produto);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<Produto> getLista() {

		try {

			List<Produto> list = getLista(Produto.class);

			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> getLista(boolean status) {
		List<Produto> list = new ArrayList<Produto>();
		
		try {
			
			Criteria c = super.getSession().createCriteria(Produto.class);			
			
			list = c.add(Restrictions.eq("Status", status)).list();
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		
		return list;
	}

	public Produto get(int id) {

		try {

			Produto produto = super.get(Produto.class, id);

			return produto;

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}
	
	public void Update(Produto produto) {
		try {
			 super.Update(produto);
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}
}

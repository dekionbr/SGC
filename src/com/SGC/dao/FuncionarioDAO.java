package com.SGC.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.SGC.domain.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

	private static FuncionarioDAO instance = null;

	public void add(Funcionario user) {
		try {
			super.add(user);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}
	

	public List<Funcionario> getLista() {

		try {

			List<Funcionario> list = super.getLista(Funcionario.class);
			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public Funcionario get(int id) {

		try {

			Funcionario user = super.get(Funcionario.class, id);

			return user;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Funcionario get(String login, String senha) {
		try {

			Criteria c = super.getSession().createCriteria(Funcionario.class);

			List<Funcionario> users = c.add(Restrictions.eq("Senha", senha))
								   .add(Restrictions.eq("Passe", login)).list();

			if (users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public void Update(Funcionario user) {
		try {
			super.Update(user);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static FuncionarioDAO getInstance() {
		if (instance == null) {
			instance = new FuncionarioDAO();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> getLista(boolean ativos) {
		List<Funcionario> users = new ArrayList<Funcionario>();
		try {

			Criteria c = super.getSession().createCriteria(Funcionario.class);
			
			users = c.add(Restrictions.eq("Status", ativos)).list();

			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return users;
	}
}
package com.SGC.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.SGC.domain.Contato;

public class ContatoDAO extends GenericDAO<Contato> {
	private static ContatoDAO instance = null;

	public void add(Contato contato) {
		try {
			super.add(contato);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<Contato> getLista() {

		try {

			List<Contato> list = super.getLista(Contato.class);
			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public Contato get(int id) {

		try {

			Contato contato = super.get(Contato.class, id);

			return contato;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public void Update(Contato contato) {
		try {
			super.Update(contato);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static ContatoDAO getInstance() {
		if (instance == null) {
			instance = new ContatoDAO();
		}
		return instance;
	}
}

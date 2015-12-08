package com.SGC.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.SGC.domain.Entrada;


public class EntradaDAO extends GenericDAO<Entrada> {
	private static EntradaDAO instance = null;

	public void add(Entrada entrada) {
		try {
			super.add(entrada);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<Entrada> getLista() {

		try {

			List<Entrada> list = super.getLista(Entrada.class);
			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public Entrada get(int id) {

		try {

			Entrada entrada = super.get(Entrada.class, id);
			
			return entrada;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public void Update(Entrada entrada) {
		try {
			super.Update(entrada);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static EntradaDAO getInstance() {
		if (instance == null) {
			instance = new EntradaDAO();
		}
		return instance;
	}
}

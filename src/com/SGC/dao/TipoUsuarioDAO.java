package com.SGC.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.SGC.domain.TipoUsuario;

public class TipoUsuarioDAO extends GenericDAO<TipoUsuario> {
	public void add(TipoUsuario tipoUsuario) {
		try {
			add(tipoUsuario);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<TipoUsuario> getLista() {

		try {

			List<TipoUsuario> list = getLista(TipoUsuario.class);

			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public TipoUsuario get(int id) {

		try {

			TipoUsuario tipoUsuario = get(TipoUsuario.class, id);

			return tipoUsuario;

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}
}

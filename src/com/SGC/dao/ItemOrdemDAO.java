package com.SGC.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.SGC.domain.ItemOrdem;

public class ItemOrdemDAO extends GenericDAO<ItemOrdem> {
	private static ItemOrdemDAO instance = null;

	public void add(ItemOrdem item) {
		try {
			super.add(item);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<ItemOrdem> getLista() {

		try {

			List<ItemOrdem> list = super.getLista(ItemOrdem.class);
			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public ItemOrdem get(int id) {

		try {

			ItemOrdem item = super.get(ItemOrdem.class, id);

			return item;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public void Update(ItemOrdem item) {
		try {
			super.Update(item);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static ItemOrdemDAO getInstance() {
		if (instance == null) {
			instance = new ItemOrdemDAO();
		}
		return instance;
	}
}

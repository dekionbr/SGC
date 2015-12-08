package com.SGC.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.SGC.domain.ItemBaixa;

public class ItemBaixaDAO extends GenericDAO<ItemBaixa> {
	private static ItemBaixaDAO instance = null;

	public void add(ItemBaixa item) {
		try {
			super.add(item);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<ItemBaixa> getLista() {

		try {

			List<ItemBaixa> list = super.getLista(ItemBaixa.class);
			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public ItemBaixa get(int id) {

		try {

			ItemBaixa item = super.get(ItemBaixa.class, id);

			return item;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}

	public void Update(ItemBaixa item) {
		try {
			super.Update(item);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static ItemBaixaDAO getInstance() {
		if (instance == null) {
			instance = new ItemBaixaDAO();
		}
		return instance;
	}
}

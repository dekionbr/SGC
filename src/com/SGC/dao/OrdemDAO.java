package com.SGC.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.SGC.domain.Funcionario;
import com.SGC.domain.Ordem;


public class OrdemDAO extends GenericDAO<Ordem> {
	private static OrdemDAO instance = null;

	public void add(Ordem ordem) {
		try {
			super.add(ordem);

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordem> getLista(Date init, Date fim) {
		
		try {

			List<Ordem> list = getSession().createCriteria(Ordem.class)
										   .add( Restrictions.ge("Data", init))
										   .add( Restrictions.le("Data",  fim))
										   .list();

			return list;
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println("error");
			
		}catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Ordem> getLista(Date init, Date fim, Funcionario funcionario) {

		try {

			List<Ordem> list = getSession().createCriteria(Ordem.class)
										   .add( Restrictions.between("Data", init, fim))
										   .add( Restrictions.eq("Usuario", funcionario))
										   .list();

			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public Ordem get(int id) {

		try {

			Ordem ordem = super.get(Ordem.class, id);

			return ordem;

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}
	
	public void Update(Ordem ordem) {
		try {
			 super.Update(ordem);
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public static OrdemDAO getInstance() {
		if (instance == null) {
			instance = new OrdemDAO();
		}
		return instance;
	}
}

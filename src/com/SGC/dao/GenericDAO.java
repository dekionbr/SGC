package com.SGC.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.SGC.util.HibernateUtil;

@SuppressWarnings("unused")
public class GenericDAO<T> {

	private Session session;
	
	
	public Session getSession() {

		if (session == null || !session.isOpen()) {
			session = openSession();
		}
		
		if(!session.isConnected()){
			session.flush();
		}

		return session;
	}

	private static Session openSession() {

//		Configuration configuration = new Configuration()
//				.configure();

		// 2. create sessionfactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  // configuration.buildSessionFactory();

		// 3. Get Session object
		return sessionFactory.openSession();
	}

	public void add(T object) {
		try {

			// 4. Starting Transaction
			Transaction transaction = getSession().beginTransaction();

			session.save(object);
			transaction.commit();

			System.out.println("\n\n Adicionado com sucesso \n");
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}		
		
	}

	@SuppressWarnings("unchecked")
	public List<T> getLista(final Class<T> type) {

		try {

			Criteria c = getSession().createCriteria(type);

			List<T> list = (List<T>) c.list();

			System.out.println("\n\n Obter Lista \n");

			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		
		return null;
	}

	public T get(final Class<T> type, int id) {
		try {

			T obj = getSession().get(type, id);

			return obj;

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		
		return null;
	}

	public void Delete(T object) {
		try {

			Transaction transaction = getSession().beginTransaction();
			
			getSession().delete(object);
			
			getSession().flush();
			getSession().clear();
			transaction.commit();
			getSession().close();
			
			System.out.println("\n\n Deletado com sucesso \n");
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		
		getSession().close();
	}

	public void Update(T object) {

		try {

			// 4. Starting Transaction
			Transaction transaction = getSession().beginTransaction();

			getSession().merge(object);

			transaction.commit();
			
			System.out.println("\n\n Adicionado com sucesso \n");
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		
		getSession().close();
	}
}

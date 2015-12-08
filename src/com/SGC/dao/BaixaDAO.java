package com.SGC.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.SGC.domain.Baixa;

public class BaixaDAO extends GenericDAO<Baixa> {
	private static BaixaDAO instance = null;
	
	public void add(Baixa baixa) {
		try {
			 super.add(baixa);
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public List<Baixa> getLista() {
		
		try {
			
			List<Baixa> list = super.getLista(Baixa.class);
			return list;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		return null;
	}

	public Baixa get(int id) {
		 
		try {		
			
			Baixa saida = super.get(Baixa.class, id);

			return saida;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}

		return null;
	}
	
	public void Update(Baixa saida) {
		try {
			 super.Update(saida);
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}
	
	public static BaixaDAO getInstance() {
		if(instance == null){
			instance = new BaixaDAO();
		}		
		return instance;
	}
}

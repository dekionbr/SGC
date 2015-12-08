package com.SGC.controller;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import com.SGC.util.HibernateUtil;

/**
 * Servlet Filter implementation class FiltroHibernate
 */
@WebFilter("/*")
public class FiltroHibernate implements Filter {
	private SessionFactory sf;


	
	
    public FiltroHibernate() {
        // TODO Auto-generated constructor stub
    }


    public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			
			sf.getCurrentSession().beginTransaction();
			chain.doFilter(request, response);
			sf.getCurrentSession().getTransaction().commit();
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			try {
				
				if(sf.getCurrentSession().getTransaction() != null){
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
	}
		
		
		
	}



	public void init(FilterConfig fConfig) throws ServletException {
		sf = HibernateUtil.getSessionFactory();
		System.out.println("SessionFactory Hibernate iniciada");
		
	}

}

package com.SGC.util;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Projection;
import org.hibernate.type.Type;

public class toStringProjections implements Projection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String[] getAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumnAliases(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumnAliases(String arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type[] getTypes(Criteria arg0, CriteriaQuery arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type[] getTypes(String arg0, Criteria arg1, CriteriaQuery arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGrouped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toGroupSqlString(Criteria arg0, CriteriaQuery arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toSqlString(Criteria arg0, int arg1, CriteriaQuery arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

}

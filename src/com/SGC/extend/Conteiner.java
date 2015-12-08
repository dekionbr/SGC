package com.SGC.extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Conteiner<T> {
	private List<T> lista;

	public Conteiner() {
		// TODO Auto-generated constructor stub
		this.lista = new ArrayList<T>();
	}
	
	public Conteiner(List<T> lista) {
		this.lista = lista;
	}

	public boolean add(T e) {
		// TODO Auto-generated method stub
		return lista.add(e);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public double Sum(String property) {
		double soma = 0;
		for (Iterator<T> iterator = lista.iterator(); iterator
				.hasNext();) {
			T T = (T) iterator.next();			
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
            Class clazz = T.getClass();
            Method method;
            
			try {
				method = clazz.getMethod(methodName, null);
				Object value = method.invoke(T, null);
				soma += Double.valueOf(value.toString());
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.getStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.getStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.getStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.getStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.getStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.getStackTrace();
			}
            
            
		}
		return soma;
	}

	public void clear() {
		// TODO Auto-generated method stub
		lista.clear();
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return lista.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return lista.containsAll(c);
	}

	public T get(int index) {
		// TODO Auto-generated method stub
		return lista.get(index);
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return lista.indexOf(o);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (lista != null)
			return lista.isEmpty();
		else
			return true;
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return lista.iterator();
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return lista.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return lista.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return lista.listIterator(index);
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return lista.remove(o);
	}

	public T remove(int index) {
		// TODO Auto-generated method stub
		return lista.remove(index);
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return lista.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return lista.retainAll(c);
	}

	public int size() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return lista.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return lista.toArray();
	}
	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return lista.toArray(a);
	}
	
	public void add(int arg0, T arg1) {
		// TODO Auto-generated method stub
		
	}

	public boolean addAll(Collection<? extends T> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public T set(int arg0, T arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}

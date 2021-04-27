package br.com.senior.proway.ferias.model;

import java.util.ArrayList;

public interface Icrud<T> {

	public ArrayList<T> getAll();
	
	public T get(int id);
	
	public boolean create(T objeto);
	
	public boolean update(int id, T objeto);
	
	public boolean delete(int id);
	
}

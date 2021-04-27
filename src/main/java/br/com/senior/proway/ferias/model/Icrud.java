package br.com.senior.proway.ferias.model;

import java.util.ArrayList;

public interface Icrud<T> {

	public ArrayList<T> getAll();
	
	public T get(int id);
	
	public void create(T objeto);
	
	public void update(int id, T objeto);
	
	public void delete(int id);
	
}

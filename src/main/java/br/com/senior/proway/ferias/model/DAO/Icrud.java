package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

public interface Icrud<T> {

	public ArrayList<T> pegarTodos();
	
	public T pegarPorID(int id);
	
	public boolean cadastrar(T objeto);
	
	public boolean alterar(int id, T objeto);
	
	public boolean deletar(int id);
	
}

package br.com.senior.proway.ferias.model.DAO.interfaces;

import java.util.List;

public interface Icrud<T> {

	public List<T> pegarTodos();

	public boolean cadastrar(T objeto);
	
	public boolean alterar(T objeto);
	
	public boolean deletar(T objeto);
	
}

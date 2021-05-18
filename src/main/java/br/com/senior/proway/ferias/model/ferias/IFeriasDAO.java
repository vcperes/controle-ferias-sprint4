package br.com.senior.proway.ferias.model.ferias;

import java.util.List;

public interface IFeriasDAO<T> {

	public List<T> pegarTodos();

	public boolean cadastrar(T objeto);
	
	public boolean alterar(T objeto);
	
	public boolean deletar(T objeto);
	
}

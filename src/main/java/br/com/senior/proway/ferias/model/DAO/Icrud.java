package br.com.senior.proway.ferias.model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface Icrud<T> {

	public List<T> pegarTodos();

	public boolean cadastrar(T objeto);
	
	public boolean alterar(T objeto);
	
	public boolean deletar(T objeto);
	
}

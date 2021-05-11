package br.com.senior.proway.ferias.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Icrud<T> {

	public ArrayList<T> pegarTodos();
	
	public T pegarFeriasPorID(int id);
	
	public boolean cadastrar(T objeto);
	
	public boolean alterar(int id, T objeto) throws SQLException;
	
	public boolean deletar(int id);
	
}

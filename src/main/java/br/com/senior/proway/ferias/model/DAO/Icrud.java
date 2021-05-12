package br.com.senior.proway.ferias.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Icrud<T> {

	public ArrayList<T> pegarTodos();
	
	public T pegarFeriasPorID(String nomeDaTabela, int id);
	
	public boolean cadastrar(T objeto);
	
	public boolean alterar(T objeto) throws SQLException;
	
	public boolean deletar(T objeto);
	
}

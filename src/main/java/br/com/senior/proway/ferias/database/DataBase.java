package br.com.senior.proway.ferias.database;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.SaldoFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public final class DataBase {
	private String nome = "praticandoGit";
	private String nome1 = "praticandoGit2";
	private static DataBase instance;
	public ArrayList<IFerias> ferias;
	public ArrayList<SaldoFerias> saldoDeFerias;
	public ArrayList<RequerimentoFerias> requerimentos;
	public ArrayList<RequerimentoFerias> historico;

	public DataBase() {
		ferias = new ArrayList<IFerias>();
		saldoDeFerias = new ArrayList<SaldoFerias>();
		requerimentos = new ArrayList<RequerimentoFerias>();
	}

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	public ArrayList<IFerias> getFerias() {
		return ferias;
	}

	public ArrayList<SaldoFerias> getSaldoDeFerias() {
		return saldoDeFerias;
	}

	public ArrayList<RequerimentoFerias> getRequerimento() {
		return requerimentos;
	}
	
	public void limparListaDeFerias() {
		for (int i = 0; i < DataBase.getInstance().getFerias().size(); i++) {
			DataBase.getInstance().ferias.remove(i);
		}
	}	

}

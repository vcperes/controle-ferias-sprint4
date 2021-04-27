package br.com.senior.proway.ferias.database;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.SaldoFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public final class DataBase {
	private static DataBase instance;
	public ArrayList<IFerias> ferias;
	public ArrayList<SaldoFerias> saldoDeFerias;
	public ArrayList<FeriasRequerimento> saldoDeRequerimento;

	public DataBase() {
		ferias = new ArrayList<IFerias>();
		saldoDeFerias = new ArrayList<SaldoFerias>();
		saldoDeRequerimento = new ArrayList<FeriasRequerimento>();
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

	public ArrayList<FeriasRequerimento> getSaldoDeRequerimento() {
		return saldoDeRequerimento;
	}

}
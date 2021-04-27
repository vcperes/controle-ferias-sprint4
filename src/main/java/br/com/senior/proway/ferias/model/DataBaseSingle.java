package br.com.senior.proway.ferias.model;

import java.util.ArrayList;

public class DataBaseSingle {
	
	private static DataBaseSingle instance;
	public String value;

	public ArrayList<FeriasRequerimento> historico;
	
	private DataBaseSingle(String value) {
		this.value = value;
		this.historico = new ArrayList<FeriasRequerimento>();
	}
	
	public static DataBaseSingle getInstance(String value) {
		if(instance == null) {
			instance = new DataBaseSingle(value);
		}
		
		return instance;
	}
	
}

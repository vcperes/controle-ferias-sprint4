package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.FeriasRequerimento;

public class RequerimentoFeriasDAO implements Icrud<FeriasRequerimento>{

	public ArrayList<FeriasRequerimento> getAll() {
		DataBase dbSingle = DataBase.getInstance();
		return dbSingle.historico;
	}

	public FeriasRequerimento get(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.historico.size() && id >= 0) {
			return dbSingle.historico.get(id);	
		}		
		return null;
	}

	public boolean create(FeriasRequerimento objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		for(int i = 0; i < dbSingle.historico.size(); i++) {
			if(dbSingle.historico.get(i).getIdentificadorUsuario() == objeto.getIdentificadorUsuario()) {
				return false;
			}
		}		
		dbSingle.historico.add(objeto);
		return true;
	}

	public boolean update(int id, FeriasRequerimento objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.historico.size() && id >= 0) {
			dbSingle.historico.set(id, objeto);
			return true;
		}		
		return false;
	}

	public boolean delete(int id) {		
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.historico.size() && id >= 0) {
			dbSingle.historico.remove(id);	
			return true;
		}		
		return false;
	}
	
}

package br.com.senior.proway.ferias.model;

import java.util.ArrayList;

public class FeriasRequerimentoDAO implements Icrud<FeriasRequerimento>{

	public ArrayList<FeriasRequerimento> getAll() {
		DataBaseSingle dbSingle = DataBaseSingle.getInstance("historico");
		return dbSingle.historico;
	}

	public FeriasRequerimento get(int id) {
		DataBaseSingle dbSingle = DataBaseSingle.getInstance("historico");
		
		if(id < dbSingle.historico.size() && id >= 0) {
			return dbSingle.historico.get(id);	
		}		
		return null;
	}

	public boolean create(FeriasRequerimento objeto) {
		DataBaseSingle dbSingle = DataBaseSingle.getInstance("historico");
		
		for(int i = 0; i < dbSingle.historico.size(); i++) {
			if(dbSingle.historico.get(i).getIdentificadorUsuario() == objeto.getIdentificadorUsuario()) {
				return false;
			}
		}		
		dbSingle.historico.add(objeto);
		return true;
	}

	public boolean update(int id, FeriasRequerimento objeto) {
		DataBaseSingle dbSingle = DataBaseSingle.getInstance("historico");
		
		if(id < dbSingle.historico.size() && id >= 0) {
			dbSingle.historico.set(id, objeto);
			return true;
		}		
		return false;
	}

	public boolean delete(int id) {		
		DataBaseSingle dbSingle = DataBaseSingle.getInstance("historico");
		
		if(id < dbSingle.historico.size() && id >= 0) {
			dbSingle.historico.remove(id);	
			return true;
		}		
		return false;
	}
	
}

package br.com.senior.proway.ferias.model.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

public class RequerimentoFeriasDAO implements Icrud<FeriasRequerimento>{

	public ArrayList<FeriasRequerimento> getAll() {
		DataBase dbSingle = DataBase.getInstance();
		return dbSingle.requerimentos;
	}

	public FeriasRequerimento get(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.requerimentos.size() && id >= 0) {
			return dbSingle.requerimentos.get(id);	
		}		
		return null;
	}

	public boolean create(FeriasRequerimento objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		for (FeriasRequerimento feriasReq : dbSingle.requerimentos) {
			if(feriasReq.getIdentificadorUsuario() == objeto.getIdentificadorUsuario()) {
				return false;
			}
		}
		dbSingle.requerimentos.add(objeto);
		return true;
	}

	public boolean update(int id, FeriasRequerimento objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.requerimentos.size() && id >= 0) {
			dbSingle.requerimentos.set(id, objeto);
			return true;
		}
		return false;
	}

	public boolean delete(int id) {		
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.requerimentos.size() && id >= 0) {
			dbSingle.requerimentos.remove(id);	
			return true;
		}	
		return false;
	}
	
	public ArrayList<FeriasRequerimento> getRequerimentoPorEstado(EstadosRequisicao estado) {
		DataBase dbSingle = DataBase.getInstance();
		ArrayList<FeriasRequerimento> listaDeRequerimentosEstado = new ArrayList<FeriasRequerimento>();
		
		for (FeriasRequerimento feriasRequerimento : dbSingle.requerimentos) {
			if(feriasRequerimento.getEstadoRequisicao() == estado) {
				listaDeRequerimentosEstado.add(feriasRequerimento);
			}
		}
		return listaDeRequerimentosEstado;
	}

	public ArrayList<FeriasRequerimento> getRequerimentoPorData(LocalDate dataParaPesquisa) {
		DataBase dbSingle = DataBase.getInstance();
		ArrayList<FeriasRequerimento> listaDeRequerimentosData = new ArrayList<FeriasRequerimento>();
		
		for (FeriasRequerimento feriasRequerimento : dbSingle.requerimentos) {
			if(feriasRequerimento.getDataSolicitacao().equals(dataParaPesquisa)) {
				listaDeRequerimentosData.add(feriasRequerimento);
			}
		}
		return listaDeRequerimentosData;
	}
}

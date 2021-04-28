package br.com.senior.proway.ferias.model.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public class RequerimentoFeriasDAO implements Icrud<RequerimentoFerias>{

	public ArrayList<RequerimentoFerias> getAll() {
		DataBase dbSingle = DataBase.getInstance();
		return dbSingle.requerimentos;
	}

	public RequerimentoFerias get(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.requerimentos.size() && id >= 0) {
			return dbSingle.requerimentos.get(id);	
		}		
		return null;
	}

	public boolean create(RequerimentoFerias objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		for (RequerimentoFerias feriasReq : dbSingle.requerimentos) {
			if(feriasReq.getIdentificadorUsuario() == objeto.getIdentificadorUsuario()) {
				return false;
			}
		}
		dbSingle.requerimentos.add(objeto);
		return true;
	}

	public boolean update(int id, RequerimentoFerias objeto) {
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
	
	public ArrayList<RequerimentoFerias> getRequerimentoPorEstado(EstadosRequerimentos estado) {
		DataBase dbSingle = DataBase.getInstance();
		ArrayList<RequerimentoFerias> listaDeRequerimentosEstado = new ArrayList<RequerimentoFerias>();
		
		for (RequerimentoFerias feriasRequerimento : dbSingle.requerimentos) {
			if(feriasRequerimento.getEstadoRequisicao() == estado) {
				listaDeRequerimentosEstado.add(feriasRequerimento);
			}
		}
		return listaDeRequerimentosEstado;
	}

	public ArrayList<RequerimentoFerias> getRequerimentoPorData(LocalDate dataParaPesquisa) {
		DataBase dbSingle = DataBase.getInstance();
		ArrayList<RequerimentoFerias> listaDeRequerimentosData = new ArrayList<RequerimentoFerias>();
		
		for (RequerimentoFerias feriasRequerimento : dbSingle.requerimentos) {
			if(feriasRequerimento.getDataSolicitacao().equals(dataParaPesquisa)) {
				listaDeRequerimentosData.add(feriasRequerimento);
			}
		}
		return listaDeRequerimentosData;
	}
}

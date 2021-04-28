package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.SaldoFerias;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public class SaldoFeriasDAO implements ISaldoFeriasDAO, Icrud<SaldoFerias>{

	public ArrayList<SaldoFerias> getAll() {
		DataBase dbSingle = DataBase.getInstance();
		return dbSingle.saldoDeFerias;
	}

	public SaldoFerias get(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.saldoDeFerias.size() && id >= 0) {
			return dbSingle.saldoDeFerias.get(id);	
		}		
		return null;
	}

	public boolean create(SaldoFerias objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		for (SaldoFerias saldoFerias : dbSingle.saldoDeFerias) {
			if(saldoFerias.getIdentificadorUsuario() == objeto.getIdentificadorUsuario()) {
				return false;
			}
		}
		dbSingle.saldoDeFerias.add(objeto);
		return true;
	}

	public boolean update(int id, SaldoFerias objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.saldoDeFerias.size() && id >= 0) {
			dbSingle.saldoDeFerias.set(id, objeto);
			return true;
		}
		return false;
	}

	public boolean delete(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.saldoDeFerias.size() && id >= 0) {
			dbSingle.saldoDeFerias.remove(id);	
			return true;
		}	
		return false;
	}

	public ArrayList<FeriasRequerimento> receberRequerimentosEmEstado(EstadosRequisicao tipoDesejado) {
		DataBase dbSingle = DataBase.getInstance();
		
		ArrayList<FeriasRequerimento> requerimentosPorEstado = new ArrayList<FeriasRequerimento>();		
		
		for(FeriasRequerimento requerimento : dbSingle.requerimentos) {
			if(requerimento.getEstadoRequisicao() == tipoDesejado) {
				requerimentosPorEstado.add(requerimento);
			}
		}
		return requerimentosPorEstado;
	}

	public ArrayList<Ferias> receberFeriasEmEstado(TiposFerias tipoDesejado) {
		DataBase dbSingle = DataBase.getInstance();
		
		ArrayList<Ferias> feriasPorTipoDesejado = new ArrayList<Ferias>();
		
		for(IFerias ferias : dbSingle.ferias) {
			if(ferias.getTipo() == tipoDesejado) {
				feriasPorTipoDesejado.add((Ferias) ferias);   // verificar metodo
			}
		}
		return feriasPorTipoDesejado;
	}

	public int verificaQuantiaRequerimentosDeTipo(EstadosRequisicao tipoDesejado) {
		ArrayList<FeriasRequerimento> reqPorTipoDesejado = receberRequerimentosEmEstado(tipoDesejado);
		return reqPorTipoDesejado.size();
	}

	public int verificaQuantiaFeriasDeTipoNoHistorico(TiposFerias tipoDesejado) {
		ArrayList<Ferias> feriasPorTipoDesejado = receberFeriasEmEstado(tipoDesejado);
		return feriasPorTipoDesejado.size();
	}
}

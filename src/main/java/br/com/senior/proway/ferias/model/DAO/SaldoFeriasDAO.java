package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.SaldoFerias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public class SaldoFeriasDAO implements ISaldoFeriasDAO, Icrud<SaldoFerias>{

	public ArrayList<SaldoFerias> pegarTodos() {
		DataBase dbSingle = DataBase.getInstance();
		return dbSingle.saldoDeFerias;
	}

	public SaldoFerias pegarPorID(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.saldoDeFerias.size() && id >= 0) {
			return dbSingle.saldoDeFerias.get(id);	
		}		
		return null;
	}

	public boolean cadastrar(SaldoFerias objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		for (SaldoFerias saldoFerias : dbSingle.saldoDeFerias) {
			if(saldoFerias.getIdentificadorUsuario() == objeto.getIdentificadorUsuario()) {
				return false;
			}
		}
		dbSingle.saldoDeFerias.add(objeto);
		return true;
	}

	public boolean alterar(int id, SaldoFerias objeto) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.saldoDeFerias.size() && id >= 0) {
			dbSingle.saldoDeFerias.set(id, objeto);
			return true;
		}
		return false;
	}

	public boolean deletar(int id) {
		DataBase dbSingle = DataBase.getInstance();
		
		if(id < dbSingle.saldoDeFerias.size() && id >= 0) {
			dbSingle.saldoDeFerias.remove(id);	
			return true;
		}	
		return false;
	}

	public ArrayList<RequerimentoFerias> receberRequerimentosEmEstado(EstadosRequerimentos tipoDesejado) {
		DataBase dbSingle = DataBase.getInstance();
		
		ArrayList<RequerimentoFerias> requerimentosPorEstado = new ArrayList<RequerimentoFerias>();		
		
		for(RequerimentoFerias requerimento : dbSingle.requerimentos) {
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

	public int verificaQuantiaRequerimentosDeTipo(EstadosRequerimentos tipoDesejado) {
		ArrayList<RequerimentoFerias> reqPorTipoDesejado = receberRequerimentosEmEstado(tipoDesejado);
		return reqPorTipoDesejado.size();
	}

	public int verificaQuantiaFeriasDeTipoNoHistorico(TiposFerias tipoDesejado) {
		ArrayList<Ferias> feriasPorTipoDesejado = receberFeriasEmEstado(tipoDesejado);
		return feriasPorTipoDesejado.size();
	}
}

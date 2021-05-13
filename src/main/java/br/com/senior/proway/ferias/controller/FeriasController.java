package br.com.senior.proway.ferias.controller;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public class FeriasController {
	FeriasDAO feriasDAO = new FeriasDAO();
	
	public ArrayList<IFerias> pegarTodos(){
		return feriasDAO.pegarTodos();
	}
	
	public IFerias pegarFeriasPorId(int id){
		return feriasDAO.pegarRequerimentoPorID(id);
	}
	
	public boolean cadastrar(IFerias ferias) {
		return feriasDAO.cadastrar(ferias);
	}
	
	public boolean alterar(int id, IFerias novaFerias) {
		return feriasDAO.alterar(id, novaFerias);
	}
	
	public boolean deletar(int id) {
		return feriasDAO.deletar(id);
	}
	
	public ArrayList<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo){
		return feriasDAO.pegarTodasAsFeriasPorTipo(tipo);
	}
}


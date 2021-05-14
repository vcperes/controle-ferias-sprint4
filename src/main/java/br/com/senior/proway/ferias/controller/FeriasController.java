package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.DBConnection;

public class FeriasController {
	
	private  FeriasDAO feriasDAO;
	Session session; 
	
	public FeriasController() {
		session = DBConnection.getSession();
		feriasDAO = FeriasDAO.getInstance(session);
	}
			
	public List<IFerias> pegarTodos(){
		return feriasDAO.pegarTodos();
	}
	
	public IFerias pegarFeriasPorId(int id){
		return feriasDAO.pegarFeriasPorID(id);
	}
	
	public boolean cadastrar(IFerias ferias) {
		return feriasDAO.cadastrar(ferias);
	}
	
	public boolean alterar(IFerias novaFerias) {
		return feriasDAO.alterar(novaFerias);
	}
	
	public boolean deletar(IFerias ferias) {
		return feriasDAO.deletar(ferias);
	}
	public List<IFerias> pegarTodasAsFeriasPorIDColaborador(int idUsuarioEntrada){
		return feriasDAO.pegarTodasAsFeriasPorIDColaborador(idUsuarioEntrada);
	}
	public List<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo){
		return feriasDAO.pegarTodasAsFeriasPorTipo(tipo);
	}
	public List<IFerias> pegarTodasAsFeriasPorDataInicio(LocalDate dataRecebida){
		return feriasDAO.pegarTodasAsFeriasPorDataInicio(dataRecebida);
	}
}


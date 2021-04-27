package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasRequerimento;

public interface ISaldoFerias{

	public String getIdentificadorUsuario();
	public void setIdentificadorUsuario(String identificadorUsuario);
	
	public LocalDate getProximasFerias();
	public void setProximasFerias(LocalDate proximasFerias);
	
	public short getDiasDisponiveisDeFerias();
	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias);
	
	public ArrayList<Ferias> getHistoricoFerias();
	public ArrayList<FeriasRequerimento>getHistoricoRequerimentos();
	
}

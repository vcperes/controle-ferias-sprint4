package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.interfaces.ISaldoBuilder;

public class SaldoFeriasBuilder implements ISaldoBuilder {
	
	private String identificadorUsuario; 
	private LocalDate proximasFerias;
	private short diasDisponiveisDeFerias;
	private ArrayList<Ferias> historicoFerias;
	private ArrayList<FeriasRequerimento> historicoRequerimentos;
	
	public SaldoFerias build(String identificadorFuncionario) {
		return new SaldoFerias(
			this.identificadorUsuario,// = identificadorFuncionario;
			this.proximasFerias,// = this.calcularProximasFerias();
			this.diasDisponiveisDeFerias,// = 0;
			this.historicoFerias,// = new ArrayList<Ferias>();
			this.historicoRequerimentos// = new ArrayList<FeriasRequerimento>();
		);
	}
	
	// Getters and Setters
	public String getIdentificadorUsuario() { return identificadorUsuario; }
	public void setIdentificadorUsuario(String identificadorUsuario) { this.identificadorUsuario = identificadorUsuario;	}

	public LocalDate getProximasFerias() {return proximasFerias;}
	public void setProximasFerias(LocalDate proximasFerias) { this.proximasFerias = proximasFerias; }

	public short getDiasDisponiveisDeFerias() { return diasDisponiveisDeFerias; }
	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias) {this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;}

	public ArrayList<Ferias> getHistoricoFerias() {	return historicoFerias; }
	public ArrayList<FeriasRequerimento>getHistoricoRequerimentos() {	return historicoRequerimentos; }
	
}

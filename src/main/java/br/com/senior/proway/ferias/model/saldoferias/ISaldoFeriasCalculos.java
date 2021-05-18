package br.com.senior.proway.ferias.model.saldoferias;

import java.time.LocalDate;

public interface ISaldoFeriasCalculos {
	
	public LocalDate calcularProximasFerias(ISaldoFerias saldo);
	
	public void atualizarProximasFerias(ISaldoFerias saldo);
	
	public short calcularDiasDeFerias(short faltas);
	
	public void atualizarDiasDeFerias(ISaldoFerias saldo, short faltas);
}

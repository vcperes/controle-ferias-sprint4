package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;

import br.com.senior.proway.ferias.controller.interfaces.ISaldoFeriasCalculos;
import br.com.senior.proway.ferias.model.interfaces.ISaldoFerias;

public class SaldoFeriasController implements ISaldoFeriasCalculos {
	
	public final short INTERVALO_ENTRE_FERIAS_EM_ANOS = 1;
	public final short DIAS_DISPONIVEIS_PARA_FERIAS = 30;

	public final short INTERVALO_FALTAS_1 = 6;
	public final short INTERVALO_FALTAS_2 = 15;
	public final short INTERVALO_FALTAS_3 = 24;
	public final short INTERVALO_FALTAS_4 = 33;

	public final short CREDITOS_FALTAS_1 = 24;
	public final short CREDITOS_FALTAS_2 = 18;
	public final short CREDITOS_FALTAS_3 = 12;
	public final short CREDITOS_FALTAS_4 = 0;
	
	// Interface ISaldoFeriasCalculos
	
	public LocalDate calcularProximasFerias(ISaldoFerias saldo) {
		if (saldo.getProximasFerias() == null) {
			// puxar do sistema de cadastro de funcionarios
			LocalDate admissao = LocalDate.now();
			return (admissao.plusYears(INTERVALO_ENTRE_FERIAS_EM_ANOS));
		} else {
			return (saldo.getProximasFerias().plusYears(INTERVALO_ENTRE_FERIAS_EM_ANOS));
		}
	}
	
	public void atualizarProximasFerias(ISaldoFerias saldo) {
		saldo.setProximasFerias(calcularProximasFerias(saldo));
	}
	

	public short calcularDiasDeFerias(short faltas) {
		short creditos = DIAS_DISPONIVEIS_PARA_FERIAS;

		if (faltas >= INTERVALO_FALTAS_1 && faltas < INTERVALO_FALTAS_2)
			creditos = CREDITOS_FALTAS_1;
		else if (faltas >= INTERVALO_FALTAS_2 && faltas < INTERVALO_FALTAS_3 )
			creditos = CREDITOS_FALTAS_2;
		else if (faltas >= INTERVALO_FALTAS_3 && faltas < INTERVALO_FALTAS_4 )
			creditos = CREDITOS_FALTAS_3;
		else if (faltas >= INTERVALO_FALTAS_4)
			creditos = CREDITOS_FALTAS_4;
		return creditos;
	}

	public void atualizarDiasDeFerias(ISaldoFerias saldo, short faltas) {
		saldo.setDiasDisponiveisDeFerias(calcularDiasDeFerias(faltas));
	}

}

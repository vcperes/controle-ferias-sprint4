package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.util.ArrayList;
//crtl+shift+O = AutoImport

import br.com.senior.proway.ferias.controller.interfaces.ISaldoFeriasValidacoes;
import br.com.senior.proway.ferias.model.interfaces.IHistoricoFerias;
import br.com.senior.proway.ferias.model.interfaces.IHistoricoRequerimentos;
import br.com.senior.proway.ferias.model.interfaces.ISaldoFerias;

/**
 * A classe que representa o saldo e historico de ferias.
 * 
 * Possui métodos para calcular saldo, bem como a verificação de saldo.
 * 
 * Creditar dias de férias e controle de faltas através de ponto.
 * 
 * A classe possui constantes para melhor entendimento e manutenção do
 * código.
 * 
 * @author SENIOR
 *
 */
public class SaldoFerias implements ISaldoFerias, IHistoricoFerias, IHistoricoRequerimentos, ISaldoFeriasValidacoes {

	private String identificadorUsuario; // FK Foreign Key do usuario - Decidir quais classes tem

	private LocalDate proximasFerias;
	private short diasDisponiveisDeFerias; // Vai ser preenchido na data "proximasFerias"
	private ArrayList<Ferias> historicoFerias;
	private ArrayList<RequerimentoFerias> historicoRequerimentos;

	public SaldoFerias(String identificadorUsuario, LocalDate proximasFerias, short diasDisponiveisDeFerias,
			ArrayList<Ferias> historicoFerias, ArrayList<RequerimentoFerias> historicoRequerimentos) {
		this.identificadorUsuario = identificadorUsuario;
		this.proximasFerias = proximasFerias;
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
		this.historicoFerias = historicoFerias;
		this.historicoRequerimentos = historicoRequerimentos;
	}

	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}

	public LocalDate getProximasFerias() {
		return proximasFerias;
	}

	public void setProximasFerias(LocalDate proximasFerias) {
		this.proximasFerias = proximasFerias;
	}

	public short getDiasDisponiveisDeFerias() {
		return diasDisponiveisDeFerias;
	}

	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias) {
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
	}

	public ArrayList<Ferias> getHistoricoFerias() {
		return historicoFerias;
	}

	public void setHistoricoFerias(ArrayList<Ferias> historicoFerias) {
		this.historicoFerias = historicoFerias;
	}

	public ArrayList<RequerimentoFerias> getHistoricoRequerimentos() {
		return historicoRequerimentos;
	}
	public void setHistoricoRequerimentos(ArrayList<RequerimentoFerias> historicoRequerimentos) {
		this.historicoRequerimentos = historicoRequerimentos;
	}

	/*
	 * Nao devemos substituir a lista, ela ja esta instanciada no construtor. Logo,
	 * não vamos fazer SET para as ArrayLists
	 */

	// Metodos para modificar as ArrayLists

	// HistoricoFerias
	public void adicionarHistoricoFerias(Ferias ferias) {
		this.historicoFerias.add(ferias);
	}

	public void removerHistoricoFerias(Ferias ferias) {
		this.historicoFerias.remove(ferias);
	}

	public int verificaQuantidadeHistoricoFerias() {
		return getHistoricoFerias().size();
	}

	// HistoricoRequerimentos
	public void adicionarHistoricoRequerimentos(RequerimentoFerias req) {
		this.historicoRequerimentos.add(req);
	}

	public void removerHistoricoRequerimentos(RequerimentoFerias req) {
		this.historicoRequerimentos.remove(req);
	}

	public int verificaQuantiaRequerimentos() {
		return getHistoricoRequerimentos().size();
	}

	// ISaldoFeriasValidacoes
	public boolean checarSaldoPositivo() {
		return this.diasDisponiveisDeFerias > 0 ? true : false;
	}
	
}

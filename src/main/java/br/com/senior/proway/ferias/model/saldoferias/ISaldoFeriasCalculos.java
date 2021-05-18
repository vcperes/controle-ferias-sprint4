package br.com.senior.proway.ferias.model.saldoferias;

import java.time.LocalDate;

public interface ISaldoFeriasCalculos {
	/*
	 * Funcão responsável por definir a data em que os dias disponíveis para férias
	 * são creditados para os funcionários Quando a estrutura é iniciada, é
	 * utilizada a informação de admissão do funcionário para calcular o dia em que
	 * o mesmo recebe suas primeiras férias Para as próximas férias, é utilizada a
	 * data do ultimo crédito de dias mais 365 anos;
	 * 
	 * @return Data dos próximos dias de férias disponíveis
	 */
	public LocalDate calcularProximasFerias(ISaldoFerias saldo);
	
	public void atualizarProximasFerias(ISaldoFerias saldo);

	/*
	 * Função responsável calcular os creditos de dias disponíveis para férias. Essa função
	 * é chamada na data definida pela variável "proximasFerias" e recebe a quantidade de faltas do funcionario
	 * 
	 */
	public short calcularDiasDeFerias(short faltas);
	
	public void atualizarDiasDeFerias(ISaldoFerias saldo, short faltas);
}

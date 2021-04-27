package br.com.senior.proway.ferias.controller.interfaces;

public interface ISaldoFeriasValidacoes {
	/**
	 * Verifica se possui saldo positivo.
	 * 
	 * Consulta o valor de Saldo de Ferias e verifica se ele � positivo.
	 * 
	 * @param saldoFerias, da estrutura de dados.
	 * @return
	 */
	public boolean checarSaldoPositivo();
}

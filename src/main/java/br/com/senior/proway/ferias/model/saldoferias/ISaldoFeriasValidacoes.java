package br.com.senior.proway.ferias.model.saldoferias;

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

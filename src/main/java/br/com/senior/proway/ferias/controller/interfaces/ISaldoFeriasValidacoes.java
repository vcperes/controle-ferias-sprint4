package br.com.senior.proway.ferias.controller.interfaces;

import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

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
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentos();
	
	/**
	 * Verificar se existem chamados do tipoDesejado.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentosDeTipo(EstadosRequisicao tipoDesejado);
	
	/**
	 * Verificar se existem ferias do tipoDesejado.
	 * 
	 *
	 * @return quantidade de ferias do tipoDesejado
	 */
	public int verificaQuantiaFeriasDeTipoNoHistorico(TiposFerias tipoDesejado);
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantidadeHistoricoFerias();
}

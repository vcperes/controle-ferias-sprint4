package br.com.senior.proway.ferias.model.ferias;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

public interface IHistoricoFerias {

	
	/**
	 * Adiciona um item de ferias na lista de HistoricoFerias
	 * @param ferias
	 */
	public void adicionarHistoricoFerias(Ferias ferias);
	
	/**
	 * Remove um item de ferias na lista de HistoricoFerias
	 * @param ferias
	 */
	public void removerHistoricoFerias(Ferias ferias);

	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantidadeHistoricoFerias();
}

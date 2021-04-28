package br.com.senior.proway.ferias.model.interfaces;

import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

public interface IHistoricoRequerimentos {
	/**
	 * Adiciona um item de ferias na lista de HistoricoRequerimentos
	 * @param ferias
	 */
	public void adicionarHistoricoRequerimentos(FeriasRequerimento req);
	
	/**
	 * Remove um item de ferias na lista de HistoricoFerias
	 * @param ferias
	 */
	public void removerHistoricoRequerimentos(FeriasRequerimento req);
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentos();
	
}

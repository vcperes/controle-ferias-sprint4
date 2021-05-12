package br.com.senior.proway.ferias.model.interfaces;

import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public interface IHistoricoRequerimentos {
	/**
	 * Adiciona um item de ferias na lista de HistoricoRequerimentos
	 * @param ferias
	 */
	public void adicionarHistoricoRequerimentos(Requerimento req);
	
	/**
	 * Remove um item de ferias na lista de HistoricoFerias
	 * @param ferias
	 */
	public void removerHistoricoRequerimentos(Requerimento req);
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentos();
	
}

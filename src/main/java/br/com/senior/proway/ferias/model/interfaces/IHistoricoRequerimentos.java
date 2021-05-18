package br.com.senior.proway.ferias.model.interfaces;

import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

public interface IHistoricoRequerimentos {
	/**
	 * Adiciona um item de ferias na lista de HistoricoRequerimentos
	 * @param ferias
	 */
	public void adicionarHistoricoRequerimentos(RequerimentoFerias req);
	
	/**
	 * Remove um item de ferias na lista de HistoricoFerias
	 * @param ferias
	 */
	public void removerHistoricoRequerimentos(RequerimentoFerias req);
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentos();
	
}

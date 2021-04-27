package br.com.senior.proway.ferias.model.interfaces;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public interface IHistoricoFerias {
	// apenas metodos
	public void adicionarHistoricoFerias(Ferias ferias);
	public void removerHistoricoFerias(Ferias ferias);
	
	
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantidadeHistoricoFerias();
}

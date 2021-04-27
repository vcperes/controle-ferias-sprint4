package br.com.senior.proway.ferias.model.interfaces;

import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

public interface IHistoricoRequerimentos {
	// apenas metodos
	public void adicionarHistoricoRequerimentos(FeriasRequerimento req);
	public void removerHistoricoRequerimentos(FeriasRequerimento req);
	
	/**
	 * Verificar se existem requerimentos.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentos();
	
}

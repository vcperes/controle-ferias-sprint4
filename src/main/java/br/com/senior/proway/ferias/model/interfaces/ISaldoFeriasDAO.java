package br.com.senior.proway.ferias.model.interfaces;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public interface ISaldoFeriasDAO {

	/**
	 * Pegar lista de chamados do tipoDesejado
	 * 
	 * tipoDesjado tem como opções os termos do enum EstadosRequisicao
	 * 
	 * Retorna uma lista de FeriasRequerimento contendo os requerimentos que possuem status desejado.
	 * 
	 * @param listarequerimentos
	 * @return
	 */
	public ArrayList<FeriasRequerimento> receberRequerimentosEmEstado(EstadosRequisicao tipoDesejado);
	
	/**
	 * Pegar lista de ferias do tipoDesejado
	 * 
	 * tipoDesjado tem como op��es os termos do enum TiposFerias
	 * 
	 * Retorna uma lista de Ferias contendo os chamados que possuem status desejado.
	 * 
	 * @param listaChamados
	 * @return
	 */
	public ArrayList<Ferias> receberFeriasEmEstado(TiposFerias tipoDesejado);
}

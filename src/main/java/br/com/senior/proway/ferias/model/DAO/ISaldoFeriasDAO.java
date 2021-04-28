package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.ISaldoFerias;

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
	public ArrayList<RequerimentoFerias> receberRequerimentosEmEstado(EstadosRequerimentos tipoDesejado);
	
	/**
	 * Verificar se existem chamados do tipoDesejado.
	 * 
	 *
	 * @return quantidade de requerimentos do tipoDesejado
	 */
	public int verificaQuantiaRequerimentosDeTipo(EstadosRequerimentos tipoDesejado);
	
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
	
	/**
	 * Verificar se existem ferias do tipoDesejado.
	 * 
	 *
	 * @return quantidade de ferias do tipoDesejado
	 */
	public int verificaQuantiaFeriasDeTipoNoHistorico(TiposFerias tipoDesejado);
}

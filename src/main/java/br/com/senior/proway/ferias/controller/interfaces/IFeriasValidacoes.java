package br.com.senior.proway.ferias.controller.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IFeriasValidacoes {
	/**
	 * Verifica se o objeto de ferias esta valido, se falhar as checagens o tipo das ferias vai ser
	 * alterado para INVALIDO.
	 * 
	 * uso : Ferias X = new Ferias(inicio, fim);
	 * boolean valido = X.checarValidade();
	 * 
	 * @return true/false
	 */
	public boolean checarValidade(IFerias ferias, int creditos);

	/**
	 * Classifica o tipo de ferias com base nos dias de ferias disponiveis ao
	 * funcionario.Realiza a comparacao entre os diasTotaisRequisitados para
	 * ferias e os dias em credito. 
	 * 
	 * Os tipos de ferias estao listados no ENUM TiposFerias
	 * 
	 * @param saldoDiasFerias - vem da classe SaldoFerias
	 * @return TiposFerias classifica��o
	 */
	public TiposFerias classificarFerias(IFerias ferias , int saldoDiasFerias);
	
	/** Verifica se a data de inicio de ferias vem antes da data de fim desejado.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return periodo valido/invalido
	 */
	public boolean periodoFeriasValido(LocalDate dataInicio, LocalDate dataFim);
}

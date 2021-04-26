package br.com.senior.proway.ferias;

import br.com.senior.proway.ferias.model.TiposFerias;

public interface IFeriasValidacoes {
	/**
	 * Verifica se o objeto de f�rias � valido, se falhar as checagens o tipo �
	 * alterado para INVALIDO.
	 * 
	 * uso : Ferias X = new Ferias(inicio, fim); boolean valido =
	 * X.checarValidade();
	 * 
	 * @return true/false
	 */
	public boolean verificarFerias();

	/**
	 * Classifica o tipo de f�rias com base nos dias de f�rias dispon�veis ao
	 * funcion�rio. � realizada uma compara��o entre os diasTotaisRequisitados para
	 * f�rias e os dias em cr�dito. Os tipos de ferias estao listados no ENUM
	 * TiposFerias
	 * 
	 * @param saldoDiasFerias - vem da classe SaldoFerias
	 * @return TiposFerias classifica��o
	 */
	public TiposFerias classificarFerias(short saldoDiasFerias);
}

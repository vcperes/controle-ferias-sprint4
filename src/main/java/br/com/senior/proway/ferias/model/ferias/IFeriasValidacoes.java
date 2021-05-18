package br.com.senior.proway.ferias.model.ferias;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

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
	
	/** Verifica se a data de inicio de ferias vem antes da data de fim desejado.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return periodo valido/invalido
	 */
	public boolean periodoFeriasValido(LocalDate dataInicio, LocalDate dataFim);
}

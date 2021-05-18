package br.com.senior.proway.ferias.model.ferias.builder;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

/**
 * Responsavel por definir os valores que o Builder carrega para cada caso de
 * instanciamento de Ferias.
 */
public class FeriasDirector {

	/**
	 * Instancia um objeto de Ferias classificado como Ferias TOTAL
	 * 
	 * @param builder    Objeto Builder responsavel por instanciar Ferias
	 * @param dataInicio LocalDate que representa a data de inicio das ferias
	 * @param dataFim    LocalDate que representa a data de fim das ferias
	 */
	public void createFeriasTotal(IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.TOTAL);
		builder.calcularPeriodoFerias();
		builder.setDiasVendidos((short) 0);
	}

	/**
	 * Instancia um objeto de Ferias classificado como Ferias FRACIONADA
	 * 
	 * @param builder    Objeto Builder responsavel por instanciar Ferias
	 * @param dataInicio LocalDate que representa a data de inicio das ferias
	 * @param dataFim    LocalDate que representa a data de fim das ferias
	 */
	public void createFeriasFracionada(IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.FRACIONADA);
		builder.calcularPeriodoFerias();
		builder.setDiasVendidos((short) 0);
	}

	/**
	 * Instancia um objeto de Ferias classificado como Ferias VENDIDA
	 * 
	 * @param builder       Objeto Builder responsavel por instanciar Ferias
	 * @param diasEmCredito Saldo disponivel de creditos para ferias
	 */
	public void createFeriasVendida(IFeriasBuilder builder, short diasEmCredito) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.calcularPeriodoFerias();
		builder.calcularDiasVendidos(diasEmCredito);
	}

	/**
	 * Variante que Instancia um objeto de Ferias classificado como Ferias VENDIDA
	 * 
	 * @param builder     Objeto Builder responsavel por instanciar Ferias
	 * @param diasAVender Quantos creditos devem ser vendidos
	 */
	public void createFeriasVendidaEspecifica(IFeriasBuilder builder, short diasAVender) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.calcularPeriodoFerias();
		builder.setDiasVendidos(diasAVender);
	}

	/**
	 * Instancia um objeto de Ferias classificado como Ferias FRACIONADA
	 * 
	 * @param builder       Objeto Builder responsavel por instanciar Ferias
	 * @param dataInicio    LocalDate que representa a data de inicio das ferias
	 * @param dataFim       LocalDate que representa a data de fim das ferias
	 * @param diasEmCredito Saldo disponivel de creditos para ferias
	 */
	public void createFeriasParcial(IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim,
			short diasEmCredito) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.PARCIAL);
		builder.calcularPeriodoFerias();
		builder.calcularDiasVendidos(diasEmCredito);
	}

// Na Ferias Parcial não é útil especificarmos os dias a vender, pois eles sao definidos pelo restante de creditos do saldo.

//	public void createFeriasParcialEspecifica(
//			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim, short diasAVender
//	) {
//		builder.setDataInicio(dataInicio);
//		builder.setDataFim(dataFim);
//		builder.setTipo(TiposFerias.PARCIAL);
//		builder.setDiasTotaisRequisitados(
//				builder.calcularPeriodoFerias(builder.getDataInicio(), builder.getDataFim())
//		);
//		builder.setDiasVendidos(diasAVender);
//	}

}

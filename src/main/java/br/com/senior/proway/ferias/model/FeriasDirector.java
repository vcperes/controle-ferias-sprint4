package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFeriasBuilder;

public class FeriasDirector {
	
	public void createFeriasTotal(
			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim
	) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.TOTAL);
		builder.setDiasTotaisRequisitados(
				builder.calcularPeriodoFerias(builder.getDataInicio(), builder.getDataFim())
		);
		builder.setDiasVendidos((short) 0);
	}
	
	public void createFeriasFracionada(
			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim
	) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.FRACIONADA);
		builder.setDiasTotaisRequisitados(
				builder.calcularPeriodoFerias(builder.getDataInicio(), builder.getDataFim())
		);
		builder.setDiasVendidos((short) 0);
	}
	
	public void createFeriasVendida(
			IFeriasBuilder builder, short diasEmCredito
	) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.setDiasTotaisRequisitados(
				builder.calcularPeriodoFerias(builder.getDataInicio(), builder.getDataFim())
		);
		builder.setDiasVendidos(builder.calcularDiasVendidos(builder, diasEmCredito));
	}
	
	public void createFeriasVendidaEspecifica(
			IFeriasBuilder builder, short diasAVender
	) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.setDiasTotaisRequisitados(
				builder.calcularPeriodoFerias(builder.getDataInicio(), builder.getDataFim())
		);
		builder.setDiasVendidos(diasAVender);
	}
	
	public void createFeriasParcial(
			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim, short diasEmCredito
	) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.PARCIAL);
		builder.setDiasTotaisRequisitados(
				builder.calcularPeriodoFerias(builder.getDataInicio(), builder.getDataFim())
		);
		builder.setDiasVendidos(builder.calcularDiasVendidos(builder, diasEmCredito));
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

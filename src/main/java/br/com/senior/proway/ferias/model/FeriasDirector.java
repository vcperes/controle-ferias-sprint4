package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.interfaces.IFeriasBuilder;

public class FeriasDirector {
	
	public void createFeriasTotal(
			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim
	) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.TOTAL);
		builder.setDiasTotaisRequisitados(); //Deve ser chamado depois de setar datas
		builder.setDiasVendidos((short) 0);
	}
	
	public void createFeriasFracionada(
			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim
	) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.FRACIONADA);
		builder.setDiasTotaisRequisitados();
		builder.setDiasVendidos((short) 0);
	}
	
	public void createFeriasVendida(
			IFeriasBuilder builder, short diasAVender
	) {
		builder.setDataInicio(null);
		builder.setDataFim(null);
		builder.setTipo(TiposFerias.VENDIDA);
		builder.setDiasTotaisRequisitados();
		builder.setDiasVendidos(diasAVender);
	}
	
	public void createFeriasParcial(
			IFeriasBuilder builder, LocalDate dataInicio, LocalDate dataFim, short diasAVender
	) {
		builder.setDataInicio(dataInicio);
		builder.setDataFim(dataFim);
		builder.setTipo(TiposFerias.PARCIAL);
		builder.setDiasTotaisRequisitados();
		builder.setDiasVendidos(diasAVender);
	}
	
	

	
}

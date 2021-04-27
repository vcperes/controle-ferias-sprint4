package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.interfaces.IFeriasBuilder;

public class FeriasTotalBuilder implements IFeriasBuilder{
	
	LocalDate dataInicio;
	LocalDate dataFim;
	short totaisReq;
	private TiposFerias type;

	public void setDataInicio(LocalDate dataInicio) {
		// tratar se data inicio é valido
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDate dataFim) {
		// tratar se data fim é valido
		this.dataFim = dataFim;
	}

	public void setDiasTotaisRequisitados(short periodo) {
		// tratar dias requisitados
		this.totaisReq = this.calcularPeriodoFerias(this.dataInicio, this.dataFim);
	}
	
	private short calcularPeriodoFerias(LocalDate dataInicio2, LocalDate dataFim2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public FeriasTotal build() {
		return new FeriasTotal(this.dataInicio, this.dataFim);
	}

	public void setType(TiposFerias type) {
		this.type = type;
		
	}

}

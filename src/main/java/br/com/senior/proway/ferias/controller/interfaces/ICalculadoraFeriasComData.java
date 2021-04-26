package br.com.senior.proway.ferias.controller.interfaces;

import java.time.LocalDate;

public interface ICalculadoraFeriasComData {
	
	public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias);

}

package br.com.senior.proway.ferias;

import java.time.LocalDate;

public interface IFeriasComDatas {

	public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias);

	public void setDiasTotaisRequisitados(short valor);

	public short getDiasTotaisRequisitados();
}

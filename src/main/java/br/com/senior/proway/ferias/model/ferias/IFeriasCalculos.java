package br.com.senior.proway.ferias.model.ferias;

import java.time.LocalDate;

public interface IFeriasCalculos {
	
	public void calcularPeriodoFerias();
		
	public void calcularDiasVendidos(int diasDisponiveisParaFerias);
}

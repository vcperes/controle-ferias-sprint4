package br.com.senior.proway.ferias;

import java.time.LocalDate;

public interface IFeriasComVendas {

	/**
	 * Calcula os dias a serem vendidos com base nos dias de f�rias dispon�veis ao
	 * funcion�rio e no tipo de f�rias; Apenas os tipos PARCIAL e VENDIDA v�o ter
	 * dias a serem vendidos.
	 * 
	 * @param saldoDiasFerias - vem da classe SaldoFerias
	 * @return short dias a serem vendidos
	 */
	public short calcularDiasVendidos(short saldoDiasFerias);

	/**
	 * Calcula o intervalo em dias entre os per�odos solicitados.
	 * 
	 * @param dataInicioFerias
	 * @param dataFimFerias
	 * @return intervalo em dias entre as datas, -1 se inv�lido;
	 */
	public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias);

	public void setDiasVendidos(short valor);

	public short getDiasVendidos();
}

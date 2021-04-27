package br.com.senior.proway.ferias.controller.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IFeriasCalculos {
	/**
	 * Calcula a quantidade de dias entre a data de inicio e fim das férias.
	 * Caso as férias nao tenham informação de data, retorna 0
	 *
	 * Deve ser chamado depois de setar datas.
	 */
	short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias);
	
	/**
	 * Calcula os dias a serem vendidos com base nos dias de f�rias dispon�veis ao funcion�rio e no
	 * tipo de f�rias; Apenas os tipos PARCIAL e VENDIDA v�o ter dias a serem vendidos.
	 * 
	 * @param diasDisponiveisParaFerias - vem da classe SaldoFerias
	 * @return short dias a serem vendidos
	 */
	public short calcularDiasVendidos(IFerias ferias, short diasDisponiveisParaFerias);
}

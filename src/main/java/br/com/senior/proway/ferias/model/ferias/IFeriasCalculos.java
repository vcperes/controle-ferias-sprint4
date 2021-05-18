package br.com.senior.proway.ferias.model.ferias;

import java.time.LocalDate;

public interface IFeriasCalculos {
	/**
	 * Calcula a quantidade de dias entre a data de inicio e fim das férias.
	 * Caso as férias nao tenham informação de data, retorna 0
	 *
	 * Deve ser chamado depois de setar datas.
	 */
	public void calcularPeriodoFerias();
	
	/**
	 * Calcula os dias a serem vendidos com base nos dias de f�rias disponiveis ao funcionario e no
	 * tipo de ferias; Apenas os tipos PARCIAL e VENDIDA vao ter dias a serem vendidos.
	 * 
	 * @param diasDisponiveisParaFerias - vem da classe SaldoFerias
	 * @return int dias a serem vendidos
	 */
	public void calcularDiasVendidos(int diasDisponiveisParaFerias);
}

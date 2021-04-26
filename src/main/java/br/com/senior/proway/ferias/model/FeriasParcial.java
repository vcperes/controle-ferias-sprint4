package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.IFeriasComDatas;
import br.com.senior.proway.ferias.IFeriasComVendas;
import br.com.senior.proway.ferias.IFeriasValidacoes;
import br.com.senior.proway.ferias.Tratativas;

public class FeriasParcial extends Ferias implements IFeriasComDatas, IFeriasValidacoes, IFeriasComVendas {

	protected LocalDate dataInicio;
	protected LocalDate dataFim;
	protected short diasVendidos;
	protected short diasDeFeriasATirar;

	public void setDataInicio(LocalDate data) {
		this.dataInicio = data;
	}

	public void setDataFim(LocalDate data) {
		this.dataFim = data;
	}

	public LocalDate getDataInicio() {
		return this.dataInicio;
	}

	public LocalDate getDataFim() {
		return this.dataFim;
	}

	public void setDiasVendidos(short valor) {
		this.diasVendidos = valor;
	}

	public short getDiasVendidos() {
		return this.diasVendidos;
	}

	public void setDiasTotaisRequisitados(short valor) {
		this.diasDeFeriasATirar = valor;
	}

	public short getDiasTotaisRequisitados() {
		return this.diasDeFeriasATirar;
	}

	public boolean verificarFerias() {
		if (this.getDiasVendidos() > 0 && this.getDiasTotaisRequisitados() > 0)
			return true;
		setTipoFerias(TiposFerias.INVALIDA);
		return false;
	}

	public short calcularDiasVendidos(short saldoDiasFerias) {
		return (short) (saldoDiasFerias - this.getDiasTotaisRequisitados());
	}

	public TiposFerias classificarFerias(short saldoDiasFerias) {
		if (saldoDiasFerias - this.getDiasTotaisRequisitados() <= CREDITOS_MINIMOS_FERIAS_FRACIONADAS) {
			return TiposFerias.PARCIAL;
		}
		return TiposFerias.INVALIDA;
	}

	public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias) {
		if (Tratativas.periodoFeriasValido(dataInicioFerias, dataFimFerias)) {
			return Tratativas.retornarIntervaloEmDiasEntreAsDatas(dataInicioFerias, dataFimFerias);
		} else {
			setTipoFerias(TiposFerias.INVALIDA);
			return 0;
		}
	}

}

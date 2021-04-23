package ferias;

import java.time.LocalDate;

public class FeriasTotal extends Ferias implements IFeriasValidacoes, IFeriasComDatas {

	protected LocalDate dataInicio;
	protected LocalDate dataFim;
	protected short diasDeFeriasATirar;

	public FeriasTotal(LocalDate dataInicio, LocalDate dataFim, short saldoDiasFerias) {
		setDataInicio(dataInicio);
		setDataFim(dataFim);

		setDiasTotaisRequisitados(calcularPeriodoFerias(dataInicio, dataFim));

		setTipoFerias(classificarFerias(saldoDiasFerias));
	}

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

	public void setDiasTotaisRequisitados(short valor) {
		this.diasDeFeriasATirar = valor;
	}

	public short getDiasTotaisRequisitados() {
		return this.diasDeFeriasATirar;
	}

	public boolean verificarFerias() {
		if (this.getDiasTotaisRequisitados() > 0)
			return true;
		setTipoFerias(TiposFerias.INVALIDA);
		return false;
	}

	public TiposFerias classificarFerias(short saldoDiasFerias) {
		if (this.getDiasTotaisRequisitados() == saldoDiasFerias) {
			return TiposFerias.TOTAL;
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

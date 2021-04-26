package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.IFeriasComVendas;
import br.com.senior.proway.ferias.IFeriasSemDatas;
import br.com.senior.proway.ferias.IFeriasValidacoes;
import br.com.senior.proway.ferias.Tratativas;

public class FeriasVendida extends Ferias implements IFeriasValidacoes, IFeriasSemDatas, IFeriasComVendas {

	protected final short DIAS_MAXIMOS_A_VENDER = 30;
	
	protected short diasVendidos;

	/**
	 * Instancia um objeto FeriasVendida, que n�o tem data de inicio e data de fim;
	 * 
	 * @author SPRINT 2
	 * @param diasAVender - dias em creditos para f�rias
	 */
	public FeriasVendida(short diasAVender) {
		this.setTipoFerias(TiposFerias.VENDIDA);

		short diasVendidos = (diasAVender > DIAS_MAXIMOS_A_VENDER) ? DIAS_MAXIMOS_A_VENDER : diasAVender;
		this.setDiasVendidos(diasVendidos);
	}

	public void setDiasVendidos(short valor) {
		this.diasVendidos = valor;
	}

	public short getDiasVendidos() {
		return this.diasVendidos;
	}

	// Das Interfaces:

	public boolean verificarFerias() {
		if (this.getDiasVendidos() > 0) {
			return true;
		}
		setTipoFerias(TiposFerias.INVALIDA);
		return false;
	}

	public short calcularDiasVendidos(short saldoDiasFerias) {
		return (short) (saldoDiasFerias);
	}

	public TiposFerias classificarFerias(short saldoDiasFerias) {
		if (saldoDiasFerias != 0) {
			return TiposFerias.VENDIDA;
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

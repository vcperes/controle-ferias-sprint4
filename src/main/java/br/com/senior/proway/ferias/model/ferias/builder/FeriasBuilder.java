package br.com.senior.proway.ferias.model.ferias.builder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.ferias.IFerias;

/**
 * Instancia um objeto de Ferias de acordo com os valores passados pelo
 * FeriasDirector
 */
public class FeriasBuilder implements IFeriasBuilder {
	protected final int CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 14;
	protected final int DIAS_MAXIMOS_A_VENDER = 32;

	private int id;
	private int identificadorUsuario;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int diasTotaisRequisitados;
	private int diasVendidos;
	private TiposFerias tipoFerias;

	/**
	 * Instancia e retorna um objeto de Ferias após realizar uma checagem nos
	 * valores recebidos pelo FeriasDirector
	 * 
	 * @param creditos Saldo disponivel de creditos para ferias
	 */
	public Ferias build(int creditos) {
		this.checarValidade(this, creditos);
		return new Ferias(this.dataInicio, this.dataFim, this.diasTotaisRequisitados, this.diasVendidos,
				this.tipoFerias);
	}

	// Getters & Setters
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public int getIdentificadorUsuario() {
		return this.identificadorUsuario;
	}

	public void setIdentificadorUsuario(int valor) {
		this.identificadorUsuario = valor;
	}

	public LocalDate getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(LocalDate data) {
		this.dataInicio = data;
	}

	public LocalDate getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(LocalDate data) {
		this.dataFim = data;
	}

	public int getDiasTotaisRequisitados() {
		return this.diasTotaisRequisitados;
	}

	public void setDiasTotaisRequisitados(int valor) {
		this.diasTotaisRequisitados = valor;
	}

	public int getDiasVendidos() {
		return this.diasVendidos;
	}

	public void setDiasVendidos(int valor) {
		this.diasVendidos = valor;
	}

	public TiposFerias getTipo() {
		return this.tipoFerias;
	}

	public void setTipo(TiposFerias tipo) {
		this.tipoFerias = tipo;
	}

	// Interface IFeriasValidacoes

	public boolean checarValidade(IFerias ferias, int creditos) {
		// Checagem de creditos
		if (ferias.getDiasTotaisRequisitados() + ferias.getDiasVendidos() > creditos) {
			ferias.setTipo(TiposFerias.INVALIDA);
			return false;
		}

		// Checagem especifica para ferias Vendida
		if (ferias.getTipo() == TiposFerias.VENDIDA) {
			if (ferias.getDiasVendidos() > 0 && ferias.getDiasTotaisRequisitados() == 0)
				return true;
		}
		// Checagem dos outros tipos
		else {
			if (periodoFeriasValido(dataInicio, dataFim)) {
				switch (ferias.getTipo()) {
				case TOTAL:
				case FRACIONADA:
					if (ferias.getDiasVendidos() == 0 && ferias.getDiasTotaisRequisitados() > 0)
						return true;
					break;
				case PARCIAL:
					if (ferias.getDiasVendidos() >= 0 && ferias.getDiasTotaisRequisitados() > 0)
						return true;
					break;
				default:
					break;
				}
			}
		}
		// Checagens falharam, retorna falso e invalida as f�rias;
		ferias.setTipo(TiposFerias.INVALIDA);
		return false;
	}

	public boolean periodoFeriasValido(LocalDate dataInicio, LocalDate dataFim) {
		boolean check = dataInicio.isBefore(dataFim) ? true : false;
		return check;
	}

	// IFeriasCalculos

	public void calcularPeriodoFerias() {
		if (this.dataInicio == null || this.dataFim == null) {
			this.diasTotaisRequisitados = 0;
		} else {
			if (periodoFeriasValido(this.dataInicio, this.dataFim)) {
				this.diasTotaisRequisitados = (int) this.dataInicio.until(this.dataFim, ChronoUnit.DAYS) + 1;
			} else {
				this.diasTotaisRequisitados = 0;
			}
		}
	}

	public void calcularDiasVendidos(int diasDisponiveisParaFerias) {
		if (getTipo() == TiposFerias.PARCIAL || getTipo() == TiposFerias.VENDIDA) {
			int diasAVender =  diasDisponiveisParaFerias - getDiasTotaisRequisitados();
			if (diasAVender > 0) {
				this.diasVendidos = (diasAVender >= DIAS_MAXIMOS_A_VENDER) ? DIAS_MAXIMOS_A_VENDER : diasAVender;
			}
		} else {
			this.diasVendidos = 0;
		}
	}

}
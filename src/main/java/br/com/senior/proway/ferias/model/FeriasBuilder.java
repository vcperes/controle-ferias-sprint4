package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.model.interfaces.IFeriasBuilder;

public class FeriasBuilder implements IFeriasBuilder {
	protected final short CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 14;
	
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private short diasTotaisRequisitados;
	private short diasVendidos;
	private TiposFerias tipoFerias;

	public Ferias build() {
		this.checarValidade(); // pode ou nao mudar o tipo ferias
		return new Ferias(
				this.dataInicio,
				this.dataFim,
				this.diasTotaisRequisitados, // passar para metodo?
				this.diasVendidos,
				this.tipoFerias
		);
		// No novo construtor nao vamos pedir os dias em creditos do funcionario
		// Essa verificacao de creditos disponiveis vai ser feita no requerimento ferias
	}
	
	// setters
	public void setDataInicio(LocalDate data) {
		this.dataInicio = data;
	}
	public void setDataFim(LocalDate data) {
		this.dataFim = data;
	}
	public void setDiasTotaisRequisitados(){//(short valor) {
		if(this.dataInicio == null || this.dataFim == null) this.diasTotaisRequisitados = 0;
		
		this.diasTotaisRequisitados = calcularPeriodoFerias(this.dataInicio, this.dataFim);//valor;
	}
	
	public void setDiasVendidos(short valor) {
		this.diasVendidos = valor;
	}
	
	public void setTipo(TiposFerias tipo) {
		this.tipoFerias = tipo;
	}
	
	// Getters
	public LocalDate getDataInicio() {return this.dataInicio;}
	
	public LocalDate getDataFim() {return this.dataFim;}
	
	public short getDiasTotaisRequisitados() {return this.diasTotaisRequisitados;}
	
	public short getDiasVendidos() {return this.diasVendidos;}
	
	public TiposFerias getTipo() {return this.tipoFerias;}
	
	// Metodos
	
	/**
	 * Calcula o intervalo em dias entre os per�odos solicitados.
	 * 
	 * @param dataInicioFerias
	 * @param dataFimFerias
	 * @return intervalo em dias entre as datas, -1 se inv�lido;
	 */
	public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias) {
		// Checando a classe FeriasVendida, que n�o tem dataInicio e dataFim
		if(dataInicioFerias == null || dataFimFerias == null) {return 0;}
		// Checando a classe principal;
		if (periodoFeriasValido(dataInicioFerias, dataFimFerias)) {
			return (short) dataInicioFerias.until(dataFimFerias, ChronoUnit.DAYS);
		}
		else {
			setTipo(TiposFerias.INVALIDA);
			return -1; // -1 para dexar claro que o periodo � inv�lido;
		}
	}

	/**
	 * Classifica o tipo de f�rias com base nos dias de f�rias dispon�veis ao funcion�rio.
	 * � realizada uma compara��o entre os diasTotaisRequisitados para f�rias e os dias em cr�dito.
	 * Os tipos de ferias estao listados no ENUM TiposFerias
	 * 
	 * @param diasDisponiveisParaFerias - vem da classe SaldoFerias
	 * @return TiposFerias classifica��o
	 */
	public TiposFerias classificarFerias(short diasDisponiveisParaFerias) {
		// Para avaliar o tipo de f�rias, � necess�rio mais do que 1 dia dispon�vel para f�rias
		if (diasDisponiveisParaFerias==0) {
			return TiposFerias.INVALIDA;
		}
		
		// Verificando a quantidade de dias de f�rias que foram requisitadas para classifica��o;
		if ( this.getDiasTotaisRequisitados() < diasDisponiveisParaFerias ) {
			return (
				diasDisponiveisParaFerias - this.getDiasTotaisRequisitados() <= CREDITOS_MINIMOS_FERIAS_FRACIONADAS
				? TiposFerias.PARCIAL : TiposFerias.FRACIONADA
			);
		}
		else if (this.getDiasTotaisRequisitados() == diasDisponiveisParaFerias) {
			return TiposFerias.TOTAL;
		}	
		else if (this.getDiasTotaisRequisitados() == 0) {
			return TiposFerias.VENDIDA;
		}
		else {
			return TiposFerias.INVALIDA;
		}
	}

	/**
	 * Calcula os dias a serem vendidos com base nos dias de f�rias dispon�veis ao funcion�rio e no
	 * tipo de f�rias; Apenas os tipos PARCIAL e VENDIDA v�o ter dias a serem vendidos.
	 * 
	 * @param diasDisponiveisParaFerias - vem da classe SaldoFerias
	 * @return short dias a serem vendidos
	 */
	public short calcularDiasVendidos(short diasDisponiveisParaFerias) {
		if (this.getTipo() == TiposFerias.PARCIAL || this.getTipo() == TiposFerias.VENDIDA) {
			return (short) (diasDisponiveisParaFerias - this.getDiasTotaisRequisitados());
		} else {
			return 0;
		}
	}
	
	/** Verifica se o objeto de f�rias � valido, se falhar as checagens o tipo � alterado para INVALIDO.
	 * 
	 * uso : 
	 * BetterFerias X = new BetterFerias(inicio, fim);
	 * boolean valido = X.checarValidade();
	 * 
	 * @return true/false
	 */
	public boolean checarValidade() {
		// Checagem espec�fica para ferias Vendida
		if(this.getTipo() == TiposFerias.VENDIDA)
		{
			if (this.getDiasVendidos()>0 && this.getDiasTotaisRequisitados()==0) return true;
		}
		// Checagem dos outros tipos
		else {
			if(periodoFeriasValido(dataInicio, dataFim)) {
				switch(this.getTipo()) {
				case TOTAL:
				case FRACIONADA:
					if(this.getDiasVendidos()==0 && this.getDiasTotaisRequisitados()>0) return true;
					break;
				case PARCIAL:
					if (this.getDiasVendidos()>0 && this.getDiasTotaisRequisitados()>0) return true;
					break;				
				default:
					break;
				}
			} 
		}
		// Checagens falharam, retorna falso e invalida as f�rias;
		setTipo(TiposFerias.INVALIDA);
		return false;	 
	 }
	
	/** Verifica se a data de inicio de f�rias vem antes da data de fim desejado.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return periodo valido/invalido
	 */
	public static boolean periodoFeriasValido(LocalDate dataInicio, LocalDate dataFim) {
		boolean check = dataInicio.isBefore(dataFim)? true : false;
		return check;
	}


}
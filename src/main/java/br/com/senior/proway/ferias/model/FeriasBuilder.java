package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.model.interfaces.IFeriasBuilder;

public class FeriasBuilder implements IFeriasBuilder {
	protected final short CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 14;
	protected final short DIAS_MAXIMOS_A_VENDER = 32;
	
	private String identificadorUsuario; 
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private short diasTotaisRequisitados;
	private short diasVendidos;
	private TiposFerias tipoFerias;

	public Ferias build(short creditos) {
		this.checarValidade(this, creditos);
		return new Ferias(
				this.dataInicio,
				this.dataFim,
				this.diasTotaisRequisitados,
				this.diasVendidos,
				this.tipoFerias
		);
	}
	
	//Getters & Setters
	public String getIdentificadorUsuario() { return this.identificadorUsuario; }
	public void setIdentificadorUsuario(String valor) { this.identificadorUsuario = valor; }
	
	public LocalDate getDataInicio() {return this.dataInicio;}
	public void setDataInicio(LocalDate data) { this.dataInicio = data; }
	
	public LocalDate getDataFim() {return this.dataFim;}
	public void setDataFim(LocalDate data) { this.dataFim = data; }
	
	public short getDiasTotaisRequisitados() {return this.diasTotaisRequisitados;}
	public void setDiasTotaisRequisitados(short valor) { this.diasTotaisRequisitados = valor; }
	
	public short getDiasVendidos() {return this.diasVendidos;}
	public void setDiasVendidos(short valor) { this.diasVendidos = valor; }
	
	public TiposFerias getTipo() {return this.tipoFerias;}
	public void setTipo(TiposFerias tipo) {	this.tipoFerias = tipo; }
	
	
	// Interface IFeriasValidacoes
	
	public boolean checarValidade(IFerias ferias, short creditos) {
		// Checagem de creditos
		if(ferias.getDiasTotaisRequisitados() + ferias.getDiasVendidos() > creditos) {
			ferias.setTipo(TiposFerias.INVALIDA);
			return false;
		}
		
		// Checagem especifica para ferias Vendida
		if(ferias.getTipo() == TiposFerias.VENDIDA)
		{
			if (ferias.getDiasVendidos()>0 && ferias.getDiasTotaisRequisitados()==0) return true;
		}
		// Checagem dos outros tipos
		else {
			if(periodoFeriasValido(dataInicio, dataFim)) {
				switch(ferias.getTipo()) {
				case TOTAL:
				case FRACIONADA:
					if(ferias.getDiasVendidos()==0 && ferias.getDiasTotaisRequisitados()>0) return true;
					break;
				case PARCIAL:
					if (ferias.getDiasVendidos()>0 && ferias.getDiasTotaisRequisitados()>0) return true;
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
	
	public TiposFerias classificarFerias(IFerias ferias, short diasDisponiveisParaFerias) {
		// Creditos de férias (diasDisponiveisParaFerias) deve ser maior 0
		if (diasDisponiveisParaFerias==0) {
			return TiposFerias.INVALIDA;
		}
		
		// Checagens para tipos de férias
		if ( ferias.getDiasTotaisRequisitados() < diasDisponiveisParaFerias ) {
			return (
				diasDisponiveisParaFerias - ferias.getDiasTotaisRequisitados() <= CREDITOS_MINIMOS_FERIAS_FRACIONADAS
				? TiposFerias.PARCIAL : TiposFerias.FRACIONADA
			);
		}
		else if (ferias.getDiasTotaisRequisitados() == diasDisponiveisParaFerias) {
			return TiposFerias.TOTAL;
		}	
		else if (ferias.getDiasTotaisRequisitados() == 0) {
			return TiposFerias.VENDIDA;
		}
		else {
			return TiposFerias.INVALIDA;
		}
	}
	
	public boolean periodoFeriasValido(LocalDate dataInicio, LocalDate dataFim) {
		boolean check = dataInicio.isBefore(dataFim)? true : false;
		return check;
	}
	
	// IFeriasCalculos
	
	public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias) {
		if(dataInicioFerias == null || dataFimFerias == null) {return 0;}
		
		if (periodoFeriasValido(dataInicioFerias, dataFimFerias)) {
			return (short) dataInicioFerias.until(dataFimFerias, ChronoUnit.DAYS);
		}
		else {
			return 0;
		}
	}
	
	public short calcularDiasVendidos(IFerias ferias ,short diasDisponiveisParaFerias) {
		if ( ferias.getTipo() == TiposFerias.PARCIAL 
				|| ferias.getTipo() == TiposFerias.VENDIDA) 
		{
			short diasAVender = (short) (diasDisponiveisParaFerias - ferias.getDiasTotaisRequisitados());
			if (diasAVender > 0) 
				return (diasAVender > DIAS_MAXIMOS_A_VENDER) 
					? DIAS_MAXIMOS_A_VENDER : diasAVender;
		}
		return 0;
	}

	
	// fix this
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	}
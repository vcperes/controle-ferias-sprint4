package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.interfaces.IFerias;

/**
 * Classe que representa a estrutura de dados de uma "unidade" de F�rias.
 * Para instanciar uma nova F�rias, � necess�rio informar as datas de in�cio e fim da mesma.
 * O construtor calcula o Per�odo em dias entre as datas, calcula dias a serem vendidos se necess�rio
 * e classifica as f�rias em um dos tipos dispon�veis no sistema.
 * 
 * @author Sprint2
 *
 */
public class Ferias implements IFerias {
	private String identificadorUsuario; 
	// FK Foreign Key do usu�rio - Decidir quais classes tem
	// setar depois
	
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private short diasTotaisRequisitados; // passar para private/protected
	private short diasVendidos;
	private TiposFerias tipoFerias;

	public Ferias(LocalDate dataInicio,
						LocalDate dataFim,
						short diasTotaisRequisitados,
						short diasVendidos,
						TiposFerias tipo) 
	{
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.diasTotaisRequisitados = diasTotaisRequisitados;
		this.diasVendidos = diasVendidos;
		this.tipoFerias = tipo;
	}
	
	public void setIdentificadorUsuario(String valor) { this.identificadorUsuario = valor; }
	public void setDataInicio(LocalDate data) {	this.dataInicio = data;	}
	public void setDataFim(LocalDate data) { this.dataFim = data; }
	public void setDiasTotaisRequisitados (short valor) {this.diasTotaisRequisitados = valor;}
	public void setDiasVendidos(short valor) { this.diasVendidos = valor;}
	public void setTipo(TiposFerias tipo) { this.tipoFerias = tipo; }
	
	// Getters
	public String getIdentificadorUsuario() { return this.identificadorUsuario; }
	public LocalDate getDataInicio() { return this.dataInicio; }
	public LocalDate getDataFim() { return this.dataFim; }
	public short getDiasTotaisRequisitados() {return this.diasTotaisRequisitados;}
	public short getDiasVendidos() { return this.diasVendidos; }
	public TiposFerias getTipo() { return this.tipoFerias; }


}

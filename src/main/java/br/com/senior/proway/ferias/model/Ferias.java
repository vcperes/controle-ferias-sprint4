package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

/**
 * Classe que representa a estrutura de dados de uma "unidade" de F�rias. Para
 * instanciar uma nova F�rias, � necess�rio informar as datas de in�cio
 * e fim da mesma. O construtor calcula o Per�odo em dias entre as datas,
 * calcula dias a serem vendidos se necess�rio e classifica as f�rias em um
 * dos tipos dispon�veis no sistema.
 * 
 * @author Sprint2
 *
 */
@Entity
public class Ferias implements IFerias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private int identificadorUsuario;

	@Basic
	private LocalDate dataInicio;
	
	@Basic
	private LocalDate dataFim;
	@Transient
	private int diasTotaisRequisitados;
	private int diasVendidos;
	@Enumerated(EnumType.ORDINAL)
	private TiposFerias tipoFerias;

	public Ferias() {

	}

	public Ferias(LocalDate dataInicio, LocalDate dataFim, int diasTotaisRequisitados, int diasVendidos,
			TiposFerias tipo) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.diasTotaisRequisitados = diasTotaisRequisitados;
		this.diasVendidos = diasVendidos;
		this.tipoFerias = tipo;
	}

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

}

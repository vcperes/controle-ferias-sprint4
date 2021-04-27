package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

/**
 * Classe que representa a estrutura de dados de uma "unidade" de F�rias.
 * Para instanciar uma nova F�rias, � necess�rio informar as datas de in�cio e fim da mesma.
 * O construtor calcula o Per�odo em dias entre as datas, calcula dias a serem vendidos se necess�rio
 * e classifica as f�rias em um dos tipos dispon�veis no sistema.
 * 
 * @author Sprint2
 *
 */
public class Ferias {
	private String identificadorUsuario; 
	// FK Foreign Key do usu�rio - Decidir quais classes tem
	// setar depois
	
	private LocalDate dataInicio;
	private LocalDate dataFim;
	public short diasTotaisRequisitados; // passar para private/protected
	public short diasVendidos;
	public TiposFerias tipoFerias;

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
}

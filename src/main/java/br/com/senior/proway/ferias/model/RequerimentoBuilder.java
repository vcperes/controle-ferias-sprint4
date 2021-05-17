package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoBuilder implements IRequerimentoFeriasBuilder {

	private int id;
	private Ferias feriasRequisitadas;
	private EstadosRequerimentos estadoRequisicao;
	private LocalDate dataSolicitacao;
	
	public Requerimento build() {
		if (this.feriasRequisitadas.getTipo() == TiposFerias.INVALIDA) {
			this.estadoRequisicao = EstadosRequerimentos.INVALIDO;
		}
		return new Requerimento(this.id, this.feriasRequisitadas,this.estadoRequisicao,this.dataSolicitacao);
	}
	
	public EstadosRequerimentos getEstadoRequisicao() {
		return estadoRequisicao;
	}

	public void setEstadoRequisicao(EstadosRequerimentos estadoRequisicao) {
		this.estadoRequisicao = estadoRequisicao;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
 
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public void setID(int id) {
		this.id = id;		
	}
	
	public int getID() {
		return id;
	}

	public void setFeriasRequisitadas(Ferias ferias) {
		this.feriasRequisitadas = ferias;		
	}
	
	public Ferias getFeriasRequisitadas() {
		return feriasRequisitadas;
	}	
}

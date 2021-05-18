package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoBuilder implements IRequerimentoFeriasBuilder {

	private int id;
	private Ferias feriasRequisitadas;
	private EstadoRequerimento estadoRequisicao;
	private LocalDate dataSolicitacao;
	
	public RequerimentoFerias build() {
		if (this.feriasRequisitadas.getTipo() == TiposFerias.INVALIDA) {
			this.estadoRequisicao = EstadoRequerimento.INVALIDO;
		}
		return new RequerimentoFerias(this.id, this.feriasRequisitadas,this.estadoRequisicao,this.dataSolicitacao);
	}
	
	public EstadoRequerimento getEstadoRequisicao() {
		return estadoRequisicao;
	}

	public void setEstadoRequisicao(EstadoRequerimento estadoRequisicao) {
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

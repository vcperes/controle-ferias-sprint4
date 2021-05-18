package br.com.senior.proway.ferias.model.requerimento.builder;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.requerimento.tipos.IRequerimentoFeriasBuilder;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFerias;

/**
 * @Deprecated O buider não está sendo utilizado por falta de necessidade, e portanto não está operacional
 * @author senior
 *
 */
public class RequerimentoBuilder implements IRequerimentoFeriasBuilder {

	private int id;
	private Ferias feriasRequisitadas;
	private EstadoRequerimento estadoRequisicao;
	private LocalDate dataSolicitacao;
	
	public RequerimentoFerias build() {
		if (this.feriasRequisitadas.getTipo() == TiposFerias.INVALIDA) {
			this.estadoRequisicao = EstadoRequerimento.INVALIDO;
		}
		//return new RequerimentoFerias(this.id, this.feriasRequisitadas,this.estadoRequisicao,this.dataSolicitacao);
		return null;
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

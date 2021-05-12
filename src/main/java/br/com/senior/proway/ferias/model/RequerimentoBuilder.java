package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoBuilder implements IRequerimentoFeriasBuilder {

	private int identificadorUsuario;
	private Ferias feriasRequisitadas;
	private EstadosRequerimentos estadoRequisicao;
	private LocalDate dataSolicitacao;
	
	public RequerimentoFerias build() {
		// checar as informacoes antes de montar o objeto
		if (this.feriasRequisitadas.getTipo()==TiposFerias.INVALIDA) {
			this.estadoRequisicao = EstadosRequerimentos.INVALIDO;
		}
		return new RequerimentoFerias(this.identificadorUsuario, this.feriasRequisitadas,this.estadoRequisicao,this.dataSolicitacao);
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

	public void setIdentificadorUsuario(int identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;		
	}
	
	public int getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setFeriasRequisitadas(Ferias ferias) {
		this.feriasRequisitadas = ferias;		
	}
	
	public Ferias getFeriasRequisitadas() {
		return feriasRequisitadas;
	}	
}

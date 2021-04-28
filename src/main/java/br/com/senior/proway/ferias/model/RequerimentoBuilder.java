package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoBuilder implements IRequerimentoFeriasBuilder {

	private String identificadorUsuario;
	private Ferias feriasRequisitadas;
	private EstadosRequisicao estadoRequisicao;
	private LocalDate dataSolicitacao;
	
	public FeriasRequerimento build() {
		// checar as informacoes antes de montar o objeto
		if (this.feriasRequisitadas.getTipo()==TiposFerias.INVALIDA) {
			this.estadoRequisicao = EstadosRequisicao.INVALIDO;
		}
		return new FeriasRequerimento(this.identificadorUsuario, this.feriasRequisitadas,this.estadoRequisicao,this.dataSolicitacao);
	}
	
	public EstadosRequisicao getEstadoRequisicao() {
		return estadoRequisicao;
	}

	public void setEstadoRequisicao(EstadosRequisicao estadoRequisicao) {
		this.estadoRequisicao = estadoRequisicao;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
 
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;		
	}
	
	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setFeriasRequisitadas(Ferias ferias) {
		this.feriasRequisitadas = ferias;		
	}
	
	public Ferias getFeriasRequisitadas() {
		return feriasRequisitadas;
	}	
}

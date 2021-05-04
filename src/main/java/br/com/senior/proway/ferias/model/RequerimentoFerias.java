package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.interfaces.IFeriasRequerimento;

/**
 * 
 * @author Jonata e Leonardo Pereira A classe FeriasRequerimento e responsavel
 *         por verificar quantos dias o funcionario solicitou suas ferias
 *         antes da data pretendida (nao menor que 10 dias). Esta
 *         verificacao e feita pelo metodo
 *         validacaoPrazoSolicitacaoDeFerias e o calculo e feito pelo metodo
 *         retornarIntervaloEmDiasEntreAsDatas. A classe tambem possui o
 *         metodo de trazer o estado da requisicao que podera ser
 *         EM_ANALISE, APROVADO, REPROVADO.
 */
public class RequerimentoFerias implements IFeriasRequerimento{
	private String id;
	private Ferias feriasRequisitada;
	private EstadosRequerimentos estadoRequisicao;
	private LocalDate dataSolicitacao;
	public static short PRAZO_MINIMO_SOLICITACAO_FERIAS = 10;

	public RequerimentoFerias(String id, Ferias feriasRequisitada, EstadosRequerimentos estadoRequisicao,LocalDate dataSolicitacao) {
		this.id = id;
		this.feriasRequisitada = feriasRequisitada;
		this.estadoRequisicao = estadoRequisicao;
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public RequerimentoFerias() {
	
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ferias getFeriasRequisitada() {
		return feriasRequisitada;
	}

	public void setFeriasRequisitada(Ferias feriasRequisitada) {
		this.feriasRequisitada = feriasRequisitada;
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
}

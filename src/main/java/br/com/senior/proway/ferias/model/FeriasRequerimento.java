package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.controller.interfaces.IFeriasRequerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

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
public class FeriasRequerimento implements IFeriasRequerimento{
	private String identificadorUsuario;

	private Ferias feriasRequisitada;
	private EstadosRequisicao estadoRequisicao;
	private LocalDate dataSolicitacao;
	public static short PRAZO_MINIMO_SOLICITACAO_FERIAS = 10;

	public FeriasRequerimento( String identificadorUsuario, Ferias feriasRequisitada, EstadosRequisicao estadoRequisicao,LocalDate dataSolicitacao) {
		this.identificadorUsuario = identificadorUsuario;
		this.feriasRequisitada = feriasRequisitada;
		this.estadoRequisicao = estadoRequisicao;
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}

	public Ferias getFeriasRequisitada() {
		return feriasRequisitada;
	}

	public void setFeriasRequisitada(Ferias feriasRequisitada) {
		this.feriasRequisitada = feriasRequisitada;
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
}

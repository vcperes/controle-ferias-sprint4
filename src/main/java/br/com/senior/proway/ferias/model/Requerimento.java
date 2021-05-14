package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Entity
@Table (name = "requerimento")
public class Requerimento implements IFeriasRequerimento{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Ferias feriasRequisitada;
	@Enumerated(EnumType.ORDINAL)
	private EstadosRequerimentos estadoRequisicao;
	
	private LocalDate dataSolicitacao;
	
	@Transient
	public static short PRAZO_MINIMO_SOLICITACAO_FERIAS = 10;
	
	public Requerimento() {}
	
	public Requerimento(int id, Ferias feriasRequisitada, EstadosRequerimentos estadoRequisicao,LocalDate dataSolicitacao) {
		this.id = id;
		this.feriasRequisitada = feriasRequisitada;
		this.estadoRequisicao = estadoRequisicao;
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public Requerimento(Ferias feriasRequisitada, EstadosRequerimentos estadoRequisicao,LocalDate dataSolicitacao) {
		this.feriasRequisitada = feriasRequisitada;
		this.estadoRequisicao = estadoRequisicao;
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

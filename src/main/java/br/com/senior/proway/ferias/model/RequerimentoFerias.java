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

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;
import br.com.senior.proway.ferias.model.interfaces.IRequerimento;

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
public class RequerimentoFerias extends RequerimentoFactory<Ferias> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRequerimentoFerias;
	
	@Transient
	public static short PRAZO_MINIMO_SOLICITACAO_FERIAS = 10;
	
	public RequerimentoFerias() {
		super();
	}

	public RequerimentoFerias(Ferias objetoRequerimento, EstadoRequerimento estadoRequerimento,
			int idVerificadorRequerimento, int idCriadorRequerimento, LocalDate prazoParaAnaliseRequerimento,
			NivelUrgencia nivelUrgencia, String mensagemRequerimento) {
		super(objetoRequerimento, estadoRequerimento, idVerificadorRequerimento, idCriadorRequerimento,
				prazoParaAnaliseRequerimento, nivelUrgencia, mensagemRequerimento);
	}

	public int getIdRequerimentoFerias() {
		return idRequerimentoFerias;
	}

	public void setIdRequerimentoFerias(int idRequerimentoFerias) {
		this.idRequerimentoFerias = idRequerimentoFerias;
	}
}

package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

public interface IFeriasRequerimento {

	public int getId();

	public void setId(int identificadorUsuario);

	public Ferias getFeriasRequisitada();

	public void setFeriasRequisitada(Ferias feriasRequisitada);

	public EstadoRequerimento getEstadoRequisicao() ;

	public void setEstadoRequisicao(EstadoRequerimento estadoRequisicao);

	public LocalDate getDataSolicitacao();

	public void setDataSolicitacao(LocalDate dataSolicitacao);
}

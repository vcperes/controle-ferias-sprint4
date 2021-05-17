package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public interface IRequerimento<T> {

	public int getId();

	public void setId(int id);

	public T getFeriasRequisitada();

	public void setFeriasRequisitada(T objeto);

	public EstadosRequerimentos getEstadoRequisicao();

	public void setEstadoRequisicao(EstadosRequerimentos estadoRequisicao);

	public LocalDate getDataSolicitacao();

	public void setDataSolicitacao(LocalDate dataSolicitacao);
}

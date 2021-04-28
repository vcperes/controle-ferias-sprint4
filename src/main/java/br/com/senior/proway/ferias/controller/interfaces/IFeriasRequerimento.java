package br.com.senior.proway.ferias.controller.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

public interface IFeriasRequerimento {

	public String getIdentificadorUsuario();

	public void setIdentificadorUsuario(String identificadorUsuario);

	public Ferias getFeriasRequisitada();

	public void setFeriasRequisitada(Ferias feriasRequisitada);

	public EstadosRequisicao getEstadoRequisicao() ;

	public void setEstadoRequisicao(EstadosRequisicao estadoRequisicao);

	public LocalDate getDataSolicitacao();

	public void setDataSolicitacao(LocalDate dataSolicitacao);
}

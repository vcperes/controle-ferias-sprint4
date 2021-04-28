package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public interface IFeriasRequerimento {

	public String getIdentificadorUsuario();

	public void setIdentificadorUsuario(String identificadorUsuario);

	public Ferias getFeriasRequisitada();

	public void setFeriasRequisitada(Ferias feriasRequisitada);

	public EstadosRequerimentos getEstadoRequisicao() ;

	public void setEstadoRequisicao(EstadosRequerimentos estadoRequisicao);

	public LocalDate getDataSolicitacao();

	public void setDataSolicitacao(LocalDate dataSolicitacao);
}

package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public interface IRequerimentoFeriasBuilder {

	public void setIdentificadorUsuario(String identificadorUsuario);
	
	public void setFeriasRequisitadas(Ferias ferias);

	public void setEstadoRequisicao(EstadosRequerimentos emAnalise);

	public void setDataSolicitacao(LocalDate now);

}

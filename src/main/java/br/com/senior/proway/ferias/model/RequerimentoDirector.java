package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoDirector {
	
//	public void createRequerimento(IRequerimentoFeriasBuilder builder, Ferias ferias, String identificadorUsuario) {
//		ferias.setIdentificadorUsuario(identificadorUsuario);
//		builder.setFeriasRequisitadas(ferias);
//		builder.setIdentificadorUsuario(identificadorUsuario);
//		
//	} 
	
	public void createRequerimento(IRequerimentoFeriasBuilder builder, Ferias ferias, int identificadorUsuario) {
		ferias.setIdentificadorUsuario(identificadorUsuario);
		builder.setFeriasRequisitadas(ferias);
		builder.setID(identificadorUsuario);
		builder.setEstadoRequisicao (EstadoRequerimento.EM_ANALISE);
		builder.setDataSolicitacao (LocalDate.now());
	}
	
}

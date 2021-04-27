package br.com.senior.proway.ferias.model;

import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoDirector {
	
	public void constructRequerimento(IRequerimentoFeriasBuilder builder, Ferias ferias, String identificadorUsuario) {
		ferias.setIdentificadorUsuario(identificadorUsuario);
		builder.setFeriasRequisitadas(ferias);
		builder.setIdentificadorUsuario(identificadorUsuario);
		
	}
}

package br.com.senior.proway.ferias.model;

import br.com.senior.proway.ferias.model.interfaces.IRequerimentoFeriasBuilder;

public class RequerimentoBuilder implements IRequerimentoFeriasBuilder {

	private String identificadorUsuario;
	private Ferias feriasRequisitadas;
		
	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;		
	}
	
	public void setFeriasRequisitadas(Ferias ferias) {
		this.feriasRequisitadas = ferias;		
	}

	public FeriasRequerimento getResult() {
		return new FeriasRequerimento(this.identificadorUsuario, this.feriasRequisitadas);
	}


}

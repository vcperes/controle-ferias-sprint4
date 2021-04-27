package br.com.senior.proway.ferias.model;

public class BuilderRequerimento implements Builder {

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

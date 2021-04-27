package br.com.senior.proway.ferias.model;



public class DirectorRequerimento {
	
	public void constructRequerimento(Builder builder, Ferias ferias) {
		builder.setIdentificadorUsuario("Thiago");
		builder.setFeriasRequisitadas(ferias);
		
	}
}

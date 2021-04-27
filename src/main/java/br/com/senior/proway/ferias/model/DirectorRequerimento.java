package br.com.senior.proway.ferias.model;



public class DirectorRequerimento {
	
	public void constructRequerimento(Builder builder, Ferias ferias, String identificadorUsuario) {
		builder.setFeriasRequisitadas(ferias);
		builder.setIdentificadorUsuario(identificadorUsuario);
		ferias.setIdenficadores(identificadorUsuario);
	}
}

package br.com.senior.proway.ferias.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AumentoDeSalario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int idAumentoSalario;
	
	double novoSalario;
	
	public AumentoDeSalario(double novoSalario) {
		this.novoSalario = novoSalario;
	}
	
	public AumentoDeSalario() {
		
	}
	
}

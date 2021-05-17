package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;

@Entity
public class RequerimentoSalario extends RequerimentoFactory <AumentoDeSalario>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int idRequerimentoSalario;

	public RequerimentoSalario() {
		super();
	}

	public RequerimentoSalario(AumentoDeSalario objetoRequerimento, EstadoRequerimento estadoRequerimento,
			int idVerificadorRequerimento, int idCriadorRequerimento, LocalDate prazoParaAnaliseRequerimento,
			NivelUrgencia nivelUrgencia, String mensagemRequerimento) {
		super(objetoRequerimento, estadoRequerimento, idVerificadorRequerimento, idCriadorRequerimento,
				prazoParaAnaliseRequerimento, nivelUrgencia, mensagemRequerimento);
	}
}

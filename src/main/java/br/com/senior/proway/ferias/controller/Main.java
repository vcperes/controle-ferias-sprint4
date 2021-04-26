package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.BetterFerias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;

public class Main {
	public void criarFerias() {
		FeriasDirector feriasDirector = new FeriasDirector();
		
		FeriasBuilder fBuilder = new FeriasBuilder();
		
		// deu a ordem e ferramentas para o builder
		feriasDirector.createFeriasTotal(fBuilder, LocalDate.now(), LocalDate.of(2021, 4, 28));
		
		
		// trabalhador comeca
		BetterFerias ft = fBuilder.build();
		
				
	}
}

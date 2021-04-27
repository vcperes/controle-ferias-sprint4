package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.interfaces.ISaldoBuilder;

public class SaldoFeriasDirector {
	public void createSaldoFerias(ISaldoBuilder builder, 
								  String identificadorUsuario, 
								  LocalDate dataAdmissao) 
	{
		builder.setIdentificadorUsuario(identificadorUsuario);
		builder.setProximasFerias(dataAdmissao.plusYears(1));
		builder.setDiasDisponiveisDeFerias((short) 0);
		builder.setHistoricoFerias(new ArrayList<Ferias>());
		builder.setHistoricoRequerimentos(new ArrayList<FeriasRequerimento>());
	}
}

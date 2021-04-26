package br.com.senior.proway.ferias;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Tratativas { //TODO: Classe estatica?
	
	public static short retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino) {
		short dias = (short) ChronoUnit.DAYS.between(inicio, termino);
		if(inicio.isBefore(termino)) {
			return dias;
		}
		return -1;
	}
	
	/** Verifica se a data de inicio de f�rias vem antes da data de fim desejado.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return periodo valido/invalido
	 */
	public static boolean periodoFeriasValido(LocalDate dataInicio, LocalDate dataFim) {
		boolean check = dataInicio.isBefore(dataFim)? true : false;
		return check;
	}
}

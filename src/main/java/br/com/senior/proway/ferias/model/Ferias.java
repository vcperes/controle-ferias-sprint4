package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que representa a estrutura de dados de uma "unidade" de F�rias.
 * Para instanciar uma nova F�rias, � necess�rio informar as datas de in�cio e fim da mesma.
 * O construtor calcula o Per�odo em dias entre as datas, calcula dias a serem vendidos se necess�rio
 * e classifica as f�rias em um dos tipos dispon�veis no sistema.
 * 
 * @author Sprint2
 *
 */
public class Ferias { // Generic?
	protected String identificadorUsuario; // FK Foreign Key do usu�rio - Decidir quais classes tem
	protected TiposFerias tipoFerias;

	protected final short CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 14;

	// Construtores Vazios
	public Ferias() {
		this.tipoFerias = TiposFerias.INVALIDA;
	}
	public Ferias(LocalDate dataInicio, LocalDate dataFim, short saldoDiasFerias) {
		this.tipoFerias = TiposFerias.INVALIDA;
	}
	
	
	/*TODO: Quando essas f�rias s�o aceitas, devemos atualizar o Saldo.diasDisponiveisParaFerias
	* Essa atualiza��o vai ocorrer na classe que gerencia as solicita��es;
	*/
	
	// Setters - Para fazer automaticamente, alt+shift+S

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}
	
	public void setTipoFerias(TiposFerias tipo) {
		this.tipoFerias = tipo;
	}
	
	// Getters
	public String getIdentificadorUsuario() {return identificadorUsuario;}
	
	public TiposFerias getTipo() {return this.tipoFerias;}
	
	

}

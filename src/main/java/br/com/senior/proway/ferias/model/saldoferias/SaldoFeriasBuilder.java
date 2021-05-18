package br.com.senior.proway.ferias.model.saldoferias;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFerias;

/** 
 * Instancia um objeto de SaldoFerias de acordo com os valores passados pelo 
 * SaldoFeriasDirector.
 */
public class SaldoFeriasBuilder implements ISaldoBuilder {
	
	private String identificadorUsuario; 
	private LocalDate proximasFerias;
	private short diasDisponiveisDeFerias;
	private ArrayList<Ferias> historicoFerias;
	private ArrayList<RequerimentoFerias> historicoRequerimentos;
		
	/** 
	 * Instancia e retorna um objeto de SaldoFerias apos realizar uma checagem nos
	 * valores recebidos.
	 * 
	 * @param creditos disponiveis de creditos para ferias
	 */
	
	public SaldoFerias build() {
		return new SaldoFerias(
			this.identificadorUsuario,
			this.proximasFerias,// Valor ja calculado para +1 ano apos contratacao
			this.diasDisponiveisDeFerias,
			this.historicoFerias,
			this.historicoRequerimentos);
	}
	
	// Getters and Setters
	public String getIdentificadorUsuario() { return identificadorUsuario; }
	public void setIdentificadorUsuario(String identificadorUsuario) { this.identificadorUsuario = identificadorUsuario;	}

	public LocalDate getProximasFerias() {return proximasFerias;}
	public void setProximasFerias(LocalDate proximasFerias) { this.proximasFerias = proximasFerias; }

	public short getDiasDisponiveisDeFerias() { return diasDisponiveisDeFerias; }
	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias) {this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;}

	public ArrayList<Ferias> getHistoricoFerias() {	return historicoFerias; }
	public ArrayList<RequerimentoFerias>getHistoricoRequerimentos() {	return historicoRequerimentos; }

	public void setHistoricoFerias(ArrayList<Ferias> arrayList) {
		this.historicoFerias = arrayList;
	}
	
	public void setHistoricoRequerimentos(ArrayList<RequerimentoFerias> arrayList) {
		this.historicoRequerimentos = arrayList;
	}
	
}

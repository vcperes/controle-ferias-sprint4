package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.util.ArrayList;
//crtl+shift+O = AutoImport

import br.com.senior.proway.ferias.controller.interfaces.ISaldoFeriasCalculos;
import br.com.senior.proway.ferias.controller.interfaces.ISaldoFeriasValidacoes;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IHistoricoFerias;
import br.com.senior.proway.ferias.model.interfaces.IHistoricoRequerimentos;
import br.com.senior.proway.ferias.model.interfaces.ISaldoFerias;


/**
 * A classe que representa o saldo e historico de ferias.
 * 
 * Possui métodos para calcular saldo, bem como a verificação de saldo.
 * 
 * Creditar dias de férias e controle de faltas através de ponto.
 * 
 * A classe possui constantes para melhor entendimento e manutenção do código.
 * 
 * @author SENIOR
 *
 */
public class SaldoFerias 
	implements  ISaldoFerias,
				IHistoricoFerias, 
				IHistoricoRequerimentos,
				ISaldoFeriasCalculos,
				ISaldoFeriasValidacoes
	{
	protected final short INTERVALO_ENTRE_FERIAS_EM_ANOS = 1;
	protected final short DIAS_DISPONIVEIS_PARA_FERIAS = 30;

	protected final short INTERVALO_FALTAS_1 = 6;
	protected final short INTERVALO_FALTAS_2 = 15;
	protected final short INTERVALO_FALTAS_3 = 24;
	protected final short INTERVALO_FALTAS_4 = 33;

	protected final short CREDITOS_FALTAS_1 = 24;
	protected final short CREDITOS_FALTAS_2 = 18;
	protected final short CREDITOS_FALTAS_3 = 12;
	protected final short CREDITOS_FALTAS_4 = 0;
	
	private String identificadorUsuario; // FK Foreign Key do usu�rio - Decidir quais classes tem
	
	private LocalDate proximasFerias;
	private short diasDisponiveisDeFerias; // Vai ser preenchido na data "proximasFerias"
	private ArrayList<Ferias> historicoFerias;
	private ArrayList<FeriasRequerimento> historicoRequerimentos;

	public SaldoFerias(
			String identificadorUsuario,
			LocalDate proximasFerias,
			short diasDisponiveisDeFerias,
			ArrayList<Ferias> historicoFerias,
			ArrayList<FeriasRequerimento> historicoRequerimentos
			)
	{	
		this.identificadorUsuario = identificadorUsuario;
		this.proximasFerias = proximasFerias;
		this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;
		this.historicoFerias = historicoFerias;
		this.historicoRequerimentos = historicoRequerimentos;
	}

	public String getIdentificadorUsuario() { return identificadorUsuario; }
	public void setIdentificadorUsuario(String identificadorUsuario) { this.identificadorUsuario = identificadorUsuario;	}

	public LocalDate getProximasFerias() {return proximasFerias;}
	public void setProximasFerias(LocalDate proximasFerias) { this.proximasFerias = proximasFerias; }

	public short getDiasDisponiveisDeFerias() { return diasDisponiveisDeFerias; }
	public void setDiasDisponiveisDeFerias(short diasDisponiveisDeFerias) {this.diasDisponiveisDeFerias = diasDisponiveisDeFerias;}

	public ArrayList<Ferias> getHistoricoFerias() {	return historicoFerias; }
	public ArrayList<FeriasRequerimento>getHistoricoRequerimentos() {	return historicoRequerimentos; }
	
	/* 
	 * Nao devemos substituir a lista, ela ja esta instanciada no construtor.
	*	Logo, não vamos fazer SET para as ArrayLists
	*/
	
	// Metodos para modificar as ArrayLists
	
	// HistoricoFerias
	public void adicionarHistoricoFerias(Ferias ferias) {
		this.historicoFerias.add(ferias);
	}
	public void removerHistoricoFerias(Ferias ferias) {
		this.historicoFerias.remove(ferias);
	}
	
	public int verificaQuantiaFeriasDeTipoNoHistorico(TiposFerias tipoDesejado) {
		ArrayList<Ferias> lista = receberFeriasEmEstado(tipoDesejado);
		return lista.size();
	}
	
	public int verificaQuantidadeHistoricoFerias() {
		return getHistoricoFerias().size();
	}
	
	// HistoricoRequerimentos
	public void adicionarHistoricoRequerimentos(FeriasRequerimento req) {
		this.historicoRequerimentos.add(req);
	}
	public void removerHistoricoRequerimentos(FeriasRequerimento req) {
		this.historicoRequerimentos.remove(req);
	}
	
	public int verificaQuantiaRequerimentos() {
		return getHistoricoRequerimentos().size();
	}
	
	public int verificaQuantiaRequerimentosDeTipo(EstadosRequisicao tipoDesejado) {
		ArrayList<FeriasRequerimento> lista = receberRequerimentosEmEstado(tipoDesejado);
		return lista.size();
	}

	// ISaldoFeriasValidacoes
	
	public boolean checarSaldoPositivo() {
		return this.diasDisponiveisDeFerias > 0 ? true : false;
	}
	
	
	// Coisas que vao pra outro lugar
	
	// Interface ISaldoFeriasCalculos
	
	public LocalDate calcularProximasFerias() {
		if (this.getProximasFerias() == null) {
			// puxar do sistema de cadastro de funcionarios
			LocalDate admissao = LocalDate.now();
			return (admissao.plusYears(INTERVALO_ENTRE_FERIAS_EM_ANOS));
		} else {
			return (this.getProximasFerias().plusYears(INTERVALO_ENTRE_FERIAS_EM_ANOS));
		}
	}

	
	public short creditarDiasDeFerias(short faltas) {
		short creditos = DIAS_DISPONIVEIS_PARA_FERIAS;

		if (faltas >= INTERVALO_FALTAS_1 && faltas < INTERVALO_FALTAS_2)
			creditos = CREDITOS_FALTAS_1;
		else if (faltas >= INTERVALO_FALTAS_2 && faltas < INTERVALO_FALTAS_3 )
			creditos = CREDITOS_FALTAS_2;
		else if (faltas >= INTERVALO_FALTAS_3 && faltas < INTERVALO_FALTAS_4 )
			creditos = CREDITOS_FALTAS_3;
		else if (faltas >= INTERVALO_FALTAS_4)
			creditos = CREDITOS_FALTAS_4;
		return creditos;
	}

	// ISaldoFeriasDAO
	public ArrayList<FeriasRequerimento> receberRequerimentosEmEstado(EstadosRequisicao tipoDesejado){
		ArrayList<FeriasRequerimento> pendentes = new ArrayList<FeriasRequerimento>();
		
		for(FeriasRequerimento reqFerias : getHistoricoRequerimentos()) {
			if (reqFerias.getEstadoRequisicao() == tipoDesejado) {
				pendentes.add(reqFerias);
			}
		}
		return pendentes;
	}
	
	public ArrayList<Ferias> receberFeriasEmEstado(TiposFerias tipoDesejado){
		ArrayList<Ferias> lista = new ArrayList<Ferias>();
		
		for(Ferias ferias : getHistoricoFerias()) {
			if (ferias.getTipo() == tipoDesejado) {
				lista.add(ferias);
			}
		}
		return lista;
	}
}

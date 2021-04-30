package ferias;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.controller.SaldoFeriasController;
import br.com.senior.proway.ferias.model.SaldoFerias;
import br.com.senior.proway.ferias.model.SaldoFeriasBuilder;
import br.com.senior.proway.ferias.model.SaldoFeriasDirector;

public class TesteSaldoFerias {

	@Test
	public void calculaProximasFeriasdoUsuario() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
	
		assertEquals(dataAdmissao.plusYears(1), saldo.getProximasFerias());
		assertEquals(userID, saldo.getIdentificadorUsuario());
	}
	
	@Test 
	public void atualizarDataProximasFerias() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
		// Ao criarmos o objeto saldo, a ProximasFerias ja foi atualizada para dataAdmissao+1
		
		GroundControl.atualizarProximasFerias(saldo);
		// Logo, devemos esperar que apos atualizar essa data novamente, teremos:
		
		assertEquals(dataAdmissao.plusYears(2), saldo.getProximasFerias());
	}
	
	@Test 
	public void CreditarDiasDasFeriasSemFaltas() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 0;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.DIAS_DISPONIVEIS_PARA_FERIAS, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas1() { //INTERVALO_FALTAS_1
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 8;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_1, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas2() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 18;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_2, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas3() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 28;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_3, saldo.getDiasDisponiveisDeFerias());	
	}
	
	@Test 
	public void CreditarDiasDasFeriasComFaltas4() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		short faltas = 38;
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder saldoFeriasBuilder = new SaldoFeriasBuilder();
		SaldoFeriasController GroundControl = new SaldoFeriasController();
		
		SaldoDirector.createSaldoFerias(saldoFeriasBuilder, userID, dataAdmissao);
		SaldoFerias saldo = saldoFeriasBuilder.build();
		
		GroundControl.atualizarDiasDeFerias(saldo, faltas);
		assertEquals(GroundControl.CREDITOS_FALTAS_4, saldo.getDiasDisponiveisDeFerias());	
	}
	
	// Realizar testes com ISaldoFeriasDAO (inserindo ferias/requerimentos nas arrays e usar os metodos do controller)

}

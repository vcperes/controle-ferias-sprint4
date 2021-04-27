package ferias;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.SaldoFerias;
import br.com.senior.proway.ferias.model.SaldoFeriasBuilder;
import br.com.senior.proway.ferias.model.SaldoFeriasDirector;

public class TesteSaldoFerias {

	@Test
	public void calculaProximasFeriasdoUsuario() {
		LocalDate dataAdmissao = LocalDate.now();
		String userID = "SK8H8R";
		
		SaldoFeriasDirector SaldoDirector = new SaldoFeriasDirector();
		SaldoFeriasBuilder Bob = new SaldoFeriasBuilder();
		
		SaldoDirector.createSaldoFerias(Bob, userID, dataAdmissao);
		SaldoFerias saldo = Bob.build();
	
		assertEquals(dataAdmissao.plusYears(1), saldo.getProximasFerias());
		assertEquals(userID, saldo.getIdentificadorUsuario());
	}
	
// Testes abaixo vao testar os CONTROLLERS ao creditar ferias, por exemplo
	
//	@Test 
//	public void creditoDeFerias() {
//	SaldoFerias creditoDias = new SaldoFerias ();
//	creditoDias.creditarDiasDeFerias();
//	assertEquals(24, creditoDias.creditarDiasDeFerias());
// }
//	
//	public void CreditarDiasDasFerias() {
//		SaldoFerias creditoferias = new SaldoFerias();
//		assertEquals(24, creditoferias.creditarDiasDeFerias());
//		
//		
//	}

}

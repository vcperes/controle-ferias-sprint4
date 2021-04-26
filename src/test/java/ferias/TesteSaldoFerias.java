package ferias;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.SaldoFerias;

public class TesteSaldoFerias {

	@Test
	public void calculaProximasFeriasdoUsuario() {
		SaldoFerias proximaFerias = new SaldoFerias(); //Aqui dentro, no consturtor proximasFerias � calculada
		assertEquals(LocalDate.now().plusYears(1), proximaFerias.getProximasFerias());
	}
	
	@Test 
	public void creditoDeFerias() {
	SaldoFerias creditoDias = new SaldoFerias ();
	creditoDias.creditarDiasDeFerias();
	assertEquals(24, creditoDias.creditarDiasDeFerias());
 }
	
	public void CreditarDiasDasFerias() {
		SaldoFerias creditoferias = new SaldoFerias();
		assertEquals(24, creditoferias.creditarDiasDeFerias());
		
		
	}

}

package ferias;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public class TesteFerias {

	@Test
	public void feriasTotal() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 15);
		LocalDate data2 = LocalDate.of(2021, 5, 15); // 30 dias
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasTotal(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(ferias.getTipo(), TiposFerias.TOTAL);
		assertEquals(0, ferias.getDiasVendidos());
	}
	
	@Test
	public void feriasParcial() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 15);
		LocalDate data2 = LocalDate.of(2021, 5, 5); // 20 dias de ferias
		// lembrando que CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 15; <- creditos que restariam
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasParcial(Bob, data1, data2, creditos);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(TiposFerias.PARCIAL, ferias.getTipo());
		assertEquals(10, ferias.getDiasVendidos());
	}
	
	@Test
	public void feriasFracionada() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 16);
		LocalDate data2 = LocalDate.of(2021, 5, 1); //15 dias 
		// lembrando que CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 15; 
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasFracionada(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);

		assertEquals(0, ferias.getDiasVendidos());
		assertEquals(TiposFerias.FRACIONADA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendida() {
		short creditos = 30;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasVendida(Bob, creditos);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(creditos, ferias.getDiasVendidos());
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendidaEspecifica() {
		short creditos = 30;
		short queroVender = 25;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasVendidaEspecifica(Bob, queroVender);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(queroVender, ferias.getDiasVendidos());
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendidaAcimaDoLimite() {
		short creditos = 45;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasVendida(Bob, creditos);
		Ferias ferias = Bob.build(creditos);

		assertNotEquals(creditos, ferias.getDiasVendidos());
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasInvalida_DataInicioPosteriorADataFim() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 6, 15);
		LocalDate data2 = LocalDate.of(2021, 5, 15);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasTotal(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(TiposFerias.INVALIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasInvalida_SemCreditosSuficientes() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 15);
		LocalDate data2 = LocalDate.of(2021, 12, 15);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasFracionada(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(TiposFerias.INVALIDA, ferias.getTipo());
	}
}

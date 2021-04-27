package ferias;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.FeriasVendida;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public class TesteFerias {
	

// Generalizar para a fun��o receber faltasDoControleDePonto
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
		assertTrue(ferias.getDiasVendidos() == 0);
	}
	
	@Test
	public void feriasParcial() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 15);
		LocalDate data2 = LocalDate.of(2021, 5, 5); // 20 dias de ferias
		// lembrando que CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 15;
		// Sobram menos do que 15 creditos, logo a ferias � parcial e os dias restantes ser�o vendidos;
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasParcial(Bob, data1, data2, creditos);
		Ferias ferias = Bob.build(creditos);
		
		assertEquals(TiposFerias.PARCIAL, ferias.getTipo());
		assertTrue(ferias.getDiasVendidos() > 0);
	}
	
	@Test
	public void feriasFracionada() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 16);
		LocalDate data2 = LocalDate.of(2021, 5, 1); //15 dias 
		// lembrando que CREDITOS_MINIMOS_FERIAS_FRACIONADAS = 15; <- creditos que restariam
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasFracionada(Bob, data1, data2);
		Ferias ferias = Bob.build(creditos);

		assertTrue(ferias.getDiasVendidos() == 0);
		assertEquals(TiposFerias.FRACIONADA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendida() {
		short creditos = 30;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasVendida(Bob, creditos);
		Ferias ferias = Bob.build(creditos);
		
		assertTrue(ferias.getDiasVendidos() == creditos);
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
		
		assertTrue(ferias.getDiasVendidos() == queroVender);
		assertEquals(TiposFerias.VENDIDA, ferias.getTipo());
	}
	
	@Test
	public void feriasVendidaAcimaDoLimite() {
		short creditos = 45;
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder Bob = new FeriasBuilder();
		feriasDirector.createFeriasVendida(Bob, creditos);
		Ferias ferias = Bob.build(creditos);

		assertTrue(ferias.getDiasVendidos() < creditos);
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

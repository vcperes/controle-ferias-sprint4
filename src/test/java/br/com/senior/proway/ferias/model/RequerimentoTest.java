package br.com.senior.proway.ferias.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public class RequerimentoTest {

	@Test
	public void testRequerimento() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequerimentoIntFeriasEstadosRequerimentosLocalDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequerimentoFeriasEstadosRequerimentosLocalDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetId() {
		Requerimento requerimento = new Requerimento();
		requerimento.setId(0);
		assertEquals(0, requerimento.getId());
	}

	@Test
	public void testGetSetFeriasRequisitada() {
		Requerimento requerimento = new Requerimento();
		Ferias ferias = new Ferias();
		requerimento.setFeriasRequisitada(ferias);
		assertEquals(0, requerimento.getFeriasRequisitada());
	}

	@Test
	public void testGetSetEstadoRequisicao() {
		Requerimento requerimento = new Requerimento();
		requerimento.setEstadoRequisicao(EstadosRequerimentos.REPROVADO);
		assertEquals(EstadosRequerimentos.REPROVADO, requerimento.getEstadoRequisicao());
	}

	@Test
	public void testGetSetDataSolicitacao() {
		Requerimento requerimento = new Requerimento();
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		assertEquals(LocalDate.of(2200, 10, 23), requerimento.getDataSolicitacao());
	}

	@Test
	public void testEquals() {
		Requerimento requerimento = new Requerimento();
		Requerimento requerimento2 = new Requerimento();
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		assertTrue(requerimento.equals(requerimento2));
	}
	
	@Test
	public void testEqualsIncorreto() {
		Requerimento requerimento = new Requerimento();
		Requerimento requerimento2 = new Requerimento();
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		requerimento2.setDataSolicitacao(LocalDate.of(2200, 10, 24));
		assertNotEquals(requerimento, requerimento2);
	}

}

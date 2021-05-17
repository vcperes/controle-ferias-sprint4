package br.com.senior.proway.ferias.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

public class RequerimentoTest {

	@Test
	public void testGetSetId() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		requerimento.setId(0);
		assertEquals(0, requerimento.getId());
	}

	@Test
	public void testGetSetFeriasRequisitada() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		Ferias ferias = new Ferias();
		requerimento.setFeriasRequisitada(ferias);
		assertEquals(0, requerimento.getFeriasRequisitada());
	}

	@Test
	public void testGetSetEstadoRequisicao() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		requerimento.setEstadoRequisicao(EstadoRequerimento.REPROVADO);
		assertEquals(EstadoRequerimento.REPROVADO, requerimento.getEstadoRequisicao());
	}

	@Test
	public void testGetSetDataSolicitacao() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		assertEquals(LocalDate.of(2200, 10, 23), requerimento.getDataSolicitacao());
	}

	@Test
	public void testEquals() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		RequerimentoFerias requerimento2 = new RequerimentoFerias();
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		assertTrue(requerimento.equals(requerimento2));
	}
	
	@Test
	public void testEqualsIncorreto() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		RequerimentoFerias requerimento2 = new RequerimentoFerias();
		requerimento.setDataSolicitacao(LocalDate.of(2200, 10, 23));
		requerimento2.setDataSolicitacao(LocalDate.of(2200, 10, 24));
		assertNotEquals(requerimento, requerimento2);
	}

}

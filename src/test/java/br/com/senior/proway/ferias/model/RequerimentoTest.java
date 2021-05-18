package br.com.senior.proway.ferias.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;

public class RequerimentoTest {

	@Test
	public void testGetSetId() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		requerimento.setIdRequerimentoFerias(0);
		assertEquals(0, requerimento.getIdRequerimentoFerias());
	}

	@Test
	public void testGetSetObjetoRequerimento() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		Ferias ferias = new Ferias();
		requerimento.setObjetoRequerimento(ferias);
		assertEquals(ferias, requerimento.getObjetoRequerimento());
	}

	@Test
	public void testGetSetEstadoRequisicao() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		requerimento.setEstadoRequerimento(EstadoRequerimento.REPROVADO);
		assertEquals(EstadoRequerimento.REPROVADO, requerimento.getEstadoRequerimento());
	}

	@Test
	public void testGetSetDataSolicitacao() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		requerimento.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		assertEquals(LocalDate.of(2200, 10, 23), requerimento.getDataCriacaoRequerimento());
	}

	@Test
	public void testEquals() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		RequerimentoFerias requerimento2 = new RequerimentoFerias();
		requerimento.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		requerimento2.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		assertTrue(requerimento.getDataCriacaoRequerimento().equals(requerimento2.getDataCriacaoRequerimento()));
	}
	
	@Test
	public void testEqualsIncorreto() {
		RequerimentoFerias requerimento = new RequerimentoFerias();
		RequerimentoFerias requerimento2 = new RequerimentoFerias();
		requerimento.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 23));
		requerimento2.setDataCriacaoRequerimento(LocalDate.of(2200, 10, 24));
		assertNotEquals(requerimento, requerimento2);
	}

}

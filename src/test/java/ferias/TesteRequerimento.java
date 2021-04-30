package ferias;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.com.senior.proway.ferias.controller.FeriasRequerimentoController;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public class TesteRequerimento {

	@Test
	public void testAtualizarEstadoRequisicao() {
		short creditos = 30;
		LocalDate data1 = LocalDate.of(2021, 4, 1);
		LocalDate data2 = LocalDate.of(2021, 4, 16);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDirector.createFeriasTotal(feriasBuilder, data1, data2);
		Ferias ferias = feriasBuilder.build(creditos);
		
		RequerimentoDirector requerimentoDirector = new RequerimentoDirector();
		RequerimentoBuilder requerimentoBuilder = new RequerimentoBuilder();
		requerimentoDirector.createRequerimento(requerimentoBuilder, ferias, "IdentificardorUsuario123");
		RequerimentoFerias feriasRequerimento = requerimentoBuilder.build();
		
		assertTrue(feriasRequerimento.getEstadoRequisicao() == EstadosRequerimentos.EM_ANALISE);
		
		FeriasRequerimentoController controllerReq = new FeriasRequerimentoController();
		controllerReq.atualizarEstadoRequisicao(EstadosRequerimentos.APROVADO, feriasRequerimento);
		
		assertTrue(feriasRequerimento.getEstadoRequisicao() == EstadosRequerimentos.APROVADO);
	}
	
	@Test
	public void testValidarDataSolicitacaoComDataInicioFerias() {
		short creditos = 30;
		
		LocalDate data1 = LocalDate.of(2021, 5, 07);
		LocalDate data2 = LocalDate.of(2021, 5, 16);
		
		FeriasDirector feriasDirector = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		
		feriasDirector.createFeriasTotal(feriasBuilder, data1, data2);
		Ferias ferias = feriasBuilder.build(creditos);
		
		RequerimentoDirector requerimentoDirector = new RequerimentoDirector();
		RequerimentoBuilder requerimentoBuilder = new RequerimentoBuilder();
		
		requerimentoDirector.createRequerimento(requerimentoBuilder, ferias, "IdentificardorUsuario123");
		RequerimentoFerias feriasRequerimento = requerimentoBuilder.build();
		
		FeriasRequerimentoController controllerReq = new FeriasRequerimentoController();
		
		assertFalse(controllerReq.validacaoPrazoSolicitacaoDeFerias(data1, feriasRequerimento));
		
	}
}

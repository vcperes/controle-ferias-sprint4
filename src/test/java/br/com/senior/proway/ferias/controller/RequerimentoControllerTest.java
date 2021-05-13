package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.postgresql.DBConnection;

public class RequerimentoControllerTest {

	private static RequerimentoDAO requerimentoDAO;
	private static Session session;
	private static RequerimentoController requerimentoController;
	private static FeriasDAO feriasDAO;		
	
	TiposFerias tipo = TiposFerias.PARCIAL;
	EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
	LocalDate inicio = LocalDate.of(2021, 04, 01);
	LocalDate fim = LocalDate.of(2021, 04, 28);
	short diasTotais = 29;
	short diasVendidos = 0;
	LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
	LocalDate localDateSolicitacao2 = LocalDate.of(2021, 06, 03);
	
	@Before
	public void limparBanco() throws SQLException {
		requerimentoDAO.limparTabela();}
		
	@BeforeClass
	public static void iniciarInstancias() {
		session = DBConnection.getSession();
		requerimentoDAO = RequerimentoDAO.getInstance(session);
		requerimentoController = RequerimentoController.getInstance(session);
		feriasDAO = FeriasDAO.getInstance(session);
	}
		
	@Test
	public void testGetAllRequerimentos() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);		
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		Requerimento requerimentoFerias2 = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao2);
		requerimentoDAO.cadastrar(requerimentoFerias);
		requerimentoDAO.cadastrar(requerimentoFerias2);
		assertEquals(requerimentoController.getAllRequerimentos().size(), 2);

							
	}

	@Test
	public void testGetRequerimentoPorId() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateRequerimento() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateRequerimentoPorId() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRequerimentoPorId() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarEstadoRequisicao() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetornarIntervaloEmDiasEntreAsDatas() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidacaoPrazoSolicitacaoDeFerias() {
		fail("Not yet implemented");
	}

}

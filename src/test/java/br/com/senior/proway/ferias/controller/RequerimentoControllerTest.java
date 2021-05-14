package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		assertEquals(requerimentoFerias, requerimentoController.
				getRequerimentoPorId(requerimentoController.getAllRequerimentos().
						get(0).getId()));
	}

	@Test
	public void testCreateRequerimento() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		requerimentoController.createRequerimento(requerimentoFerias);
		Requerimento requerimentoDB = (Requerimento) requerimentoController.
				getRequerimentoPorId(requerimentoController.getAllRequerimentos().
						get(0).getId());
		assertNotNull(requerimentoDB);
	}

	@Test
	public void testUpdateRequerimentoPorId() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		requerimentoFerias = requerimentoDAO.pegarRequerimentoPorID(requerimentoController.
				getAllRequerimentos().get(0).getId());
		requerimentoFerias.setDataSolicitacao(LocalDate.of(2022, 8, 5));
		requerimentoController.updateRequerimentoPorId(requerimentoFerias);
		assertEquals(LocalDate.of(2022, 8, 5), 
				requerimentoDAO.pegarRequerimentoPorID(requerimentoController.
						getAllRequerimentos().get(0).getId()).getDataSolicitacao());
	}

	@Test
	public void testDeleteRequerimento() {
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		requerimentoFerias = requerimentoDAO.pegarRequerimentoPorID(requerimentoController.
				getAllRequerimentos().get(0).getId());
		requerimentoFerias.setDataSolicitacao(LocalDate.of(2022, 8, 5));
		requerimentoController.deleteRequerimento(requerimentoFerias);
		assertEquals(0, requerimentoDAO.pegarTodos().size());
	}

	@Test
	public void testRetornarIntervaloEmDiasEntreAsDatas() {
		assertEquals(10, RequerimentoController.retornarIntervaloEmDiasEntreAsDatas(LocalDate.of(2022, 8, 5), LocalDate.of(2022, 8, 15)));
	}

	@Test
	public void testValidacaoPrazoSolicitacaoDeFerias() {
		Ferias ferias = new Ferias(LocalDate.of(2022, 8, 15), 
				fim, diasTotais, diasVendidos, tipo);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		requerimentoFerias.setDataSolicitacao(LocalDate.of(2022, 8, 4));
		assertTrue(requerimentoController.validacaoPrazoSolicitacaoDeFerias(requerimentoFerias));
	}
	
	@Test
	public void testValidacaoPrazoSolicitacaoDeFeriasInvalido() {
		Ferias ferias = new Ferias(LocalDate.of(2022, 8, 15), 
				fim, diasTotais, diasVendidos, tipo);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento,
				localDateSolicitacao);
		requerimentoFerias.setDataSolicitacao(LocalDate.of(2022, 8, 10));
		assertFalse(requerimentoController.validacaoPrazoSolicitacaoDeFerias(requerimentoFerias));
	}
}

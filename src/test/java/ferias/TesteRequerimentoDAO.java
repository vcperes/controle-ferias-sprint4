package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

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

public class TesteRequerimentoDAO {
	static RequerimentoDAO requerimentoDAO;
	static FeriasDAO feriasDAO;
	static Session session;

	@BeforeClass
	public static void createRequerimentoDAO() {
		session = DBConnection.getSession();
		requerimentoDAO = RequerimentoDAO.getInstance(session);
		feriasDAO = FeriasDAO.getInstance(session);
	}

	@Before
	public void limparBanco() {
		requerimentoDAO.limparTabela();
		feriasDAO.limparTabela();
	}

	@Test
	public void testeACreate() {

		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		assertTrue(requerimentoDAO.cadastrar(requerimentoFerias));
	}

	@Test
	public void testeBGetId() {
		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		Requerimento requerimento = requerimentoDAO.pegarRequerimentoPorID(requerimentoDAO.pegarTodos().get(0).getId());
		assertNotNull(requerimento);
	}

	@Test
	public void testeCGetAll() {
		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		Requerimento requerimentoFerias2 = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias2);
		requerimentoDAO.cadastrar(requerimentoFerias);
		List<Requerimento> requerimentos = requerimentoDAO.pegarTodos();
		assertEquals(2, requerimentos.size());
	}
	@Test
	public void testeDUpdate() {

		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.APROVADO;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, LocalDate.now());
		requerimentoDAO.cadastrar(requerimentoFerias);
		Requerimento requerimento = requerimentoDAO.pegarRequerimentoPorID(requerimentoDAO.pegarTodos().get(0).getId());
		requerimento.setDataSolicitacao(LocalDate.of(2022, 01, 01));
		requerimentoDAO.alterar(requerimentoFerias);
		assertEquals(requerimentoDAO.pegarRequerimentoPorID(requerimentoDAO.pegarTodos().get(0).getId()).getDataSolicitacao(), LocalDate.of(2022, 01, 01));
	}

	@Test
	public void testeFBuscaRequerimentoPorEstado() {
		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		List<Requerimento> requerimento = requerimentoDAO.getRequerimentoPorEstado(EstadosRequerimentos.EM_ANALISE);
		assertEquals(requerimento.get(0).getEstadoRequisicao(), EstadosRequerimentos.EM_ANALISE);
	}

	@Test
	public void testeGGetRequerimentoPorData() {
		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		List<Requerimento> requerimento = requerimentoDAO.getRequerimentoPorData(LocalDate.of(2021, 5, 3));
		assertEquals(requerimento.get(0).getDataSolicitacao(), LocalDate.of(2021, 5, 3));
	}

	@Test
	public void testeHRemove() {

		TiposFerias tipo = TiposFerias.PARCIAL;
		EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		short diasTotais = 29;
		short diasVendidos = 0;
		Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
		feriasDAO.cadastrar(ferias);
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		requerimentoDAO.cadastrar(requerimentoFerias);
		requerimentoDAO.deletar(requerimentoFerias);
		List<Requerimento> requerimentos = requerimentoDAO.pegarTodos();
		assertEquals(0, requerimentos.size());
	}

}

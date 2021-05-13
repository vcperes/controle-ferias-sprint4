package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.postgresql.DBConnection;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteRequerimentoDAO {
	static RequerimentoDAO requerimentoDAO;
	static FeriasDAO feriasDAO;

	@BeforeClass
	public static void createRequerimentoDAO() {
		Session session = DBConnection.getSession();
		requerimentoDAO = RequerimentoDAO.getInstance(session);
		feriasDAO = FeriasDAO.getInstance(session);
	}

	@Before
	public void limparBanco() throws SQLException {
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
		Requerimento requerimento = requerimentoDAO.pegarRequerimentoPorID(1);
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
		ArrayList<Requerimento> requerimentos = requerimentoDAO.pegarTodos();
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
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, LocalDate.now());
		requerimentoDAO.cadastrar(requerimentoFerias);
		Requerimento requerimento = requerimentoDAO.pegarRequerimentoPorID(1);
		requerimento.setDataSolicitacao(LocalDate.of(2022, 01, 01));
		requerimentoDAO.alterar(requerimentoFerias);
		assertEquals(requerimentoDAO.pegarRequerimentoPorID(1), LocalDate.of(2022, 01, 01));
	}

	@Ignore
	@Test
	public void testeECreateDuplicado() {
		short creditos = 30;

		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();

		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, creditos);
		Ferias ferias = feriasBuilder.build(creditos);

		RequerimentoDirector directorRequerimento = new RequerimentoDirector();
		RequerimentoBuilder builderRequerimento = new RequerimentoBuilder();

		directorRequerimento.createRequerimento(builderRequerimento, ferias, 123);
		Requerimento feriasRequerimento = builderRequerimento.build();

		RequerimentoDAO DAOFerias = new RequerimentoDAO();
		boolean teste = DAOFerias.cadastrar(feriasRequerimento);

		assertFalse(teste);
	}

	@Ignore
	@Test
	public void testeFBuscaRequerimentoPorEstado() {
		try {
			PostgresConector.conectar();
			Requerimento requerimentoFerias = new Requerimento(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.now());
			Requerimento requerimentoFerias2 = new Requerimento(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.now());
			requerimentoDAO.cadastrar(requerimentoFerias);
			requerimentoDAO.cadastrar(requerimentoFerias2);
			EstadosRequerimentos estado = EstadosRequerimentos.APROVADO;
			ArrayList<Requerimento> listaRequerimento = requerimentoDAO.getRequerimentoPorEstado(estado);
			assertTrue(listaRequerimento.size() == 2);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void testeGGetRequerimentoPorData() {
		try {
			PostgresConector.conectar();
			Requerimento requerimentoFerias = new Requerimento(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.of(2021, 05, 03));
			Requerimento requerimentoFerias2 = new Requerimento(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.of(2021, 05, 03));
			requerimentoDAO.cadastrar(requerimentoFerias);
			requerimentoDAO.cadastrar(requerimentoFerias2);
			LocalDate localdate = LocalDate.of(2021, 05, 03);
			ArrayList<Requerimento> listaRequerimento = requerimentoDAO.getRequerimentoPorData(localdate);

			assertEquals(2, listaRequerimento.size());

		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
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
		ArrayList<Requerimento> requerimentos = requerimentoDAO.pegarTodos();
		assertEquals(0, requerimentos.size());
	}

}

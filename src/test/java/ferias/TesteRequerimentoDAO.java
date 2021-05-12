package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
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
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.postgresql.DBConnection;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteRequerimentoDAO {
	static RequerimentoDAO requerimentoDAO;
	
	@BeforeClass
	public static void createRequerimentoDAO() {
		Session session = DBConnection.getSession();
		requerimentoDAO = RequerimentoDAO.getInstance(session);
	}

	@Before
	public void limparBanco() throws SQLException {
		requerimentoDAO.limparTabela();
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
		LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
		Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, localDateSolicitacao);
		assertTrue(requerimentoDAO.cadastrar(requerimentoFerias));
	}

	@Ignore
	@Test
	public void testeBGet() {

		try {

			String id = "";

			PostgresConector.conectar();

			Requerimento requerimentoFerias = requerimentoFeriasDAO.pegarFeriasPorID(1);

			String select = "SELECT * FROM requerimento WHERE id = 1;";
			ResultSet rs = PostgresConector.executarQuery(select);

			if (rs.next()) {

				id = rs.getString("id");

				assertEquals(requerimentoFerias.getId(), id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Ignore
	@Test
	public void testeCGetAll() {

		try {

			PostgresConector.conectar();
			PostgresConector.executarUpdateQuery(
					"insert into requerimento(idferias, idestadorequisicao, datasolicitacao) values (4, 2, '11/05/2021');");
			ArrayList<Requerimento> requerimentoFerias = requerimentoDAO.pegarTodos();
			assertTrue(requerimentoFerias.size() == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Ignore
	@Test
	public void testeDUpdate() {

		try {

			PostgresConector.conectar();
			TiposFerias tipo = TiposFerias.PARCIAL;
			EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.APROVADO;
			LocalDate inicio = LocalDate.of(2021, 04, 01);
			LocalDate fim = LocalDate.of(2021, 04, 28);
			short diasTotais = 29;
			short diasVendidos = 0;
			Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
			Requerimento requerimentoFerias = new Requerimento(0, ferias, estadoRequerimento, LocalDate.now());
			requerimentoDAO.cadastrar(requerimentoFerias);
			assertTrue(requerimentoDAO.alterar(1, requerimentoFerias));

		}

		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

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

	@Ignore
	@Test
	public void testeHRemove() {

		Requerimento requerimentoFerias = new Requerimento(new Ferias(), EstadosRequerimentos.APROVADO,
				LocalDate.of(2021, 05, 03));
		requerimentoDAO.cadastrar(requerimentoFerias);
		assertTrue(requerimentoDAO.deletar(1));

	}

	@Ignore
	@Test
	public void testeIRemovendoIdNaoExistente() {
		RequerimentoDAO DAOFerias = new RequerimentoDAO();

		assertFalse(DAOFerias.deletar(1));
	}

	@Ignore
	@Test
	public void testeJGetComIdInvalido() {
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
		DAOFerias.cadastrar(feriasRequerimento);

		assertNull(DAOFerias.pegarFeriasPorID(2));
	}

	@Ignore
	@Test
	public void testeJGetComIdNegativo() {
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
		DAOFerias.cadastrar(feriasRequerimento);

		assertNull(DAOFerias.pegarFeriasPorID(-1));
	}

	@Ignore
	@Test(expected = SQLException.class)
	public void testeDUpdatePorIdInvalido() throws SQLException {
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
		DAOFerias.alterar(3, feriasRequerimento);
	}
}

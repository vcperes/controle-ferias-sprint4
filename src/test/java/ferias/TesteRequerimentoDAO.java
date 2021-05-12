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

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteRequerimentoDAO {
	RequerimentoDAO requerimentoDAO = new RequerimentoDAO();

	@Before
	public void limparBanco() throws SQLException {
		requerimentoDAO.limparTabela();
	}

	@Test
	public void testeACreate() {

		try {
			TiposFerias tipo = TiposFerias.PARCIAL;
			EstadosRequerimentos estadoRequerimento = EstadosRequerimentos.EM_ANALISE;
			LocalDate inicio = LocalDate.of(2021, 04, 01);
			LocalDate fim = LocalDate.of(2021, 04, 28);
			short diasTotais = 29;
			short diasVendidos = 0;
			Ferias ferias = new Ferias(inicio, fim, diasTotais, diasVendidos, tipo);
			LocalDate localDateSolicitacao = LocalDate.of(2021, 05, 03);
			RequerimentoFerias requerimentoFerias = new RequerimentoFerias(0, ferias, estadoRequerimento,
					localDateSolicitacao);
			requerimentoDAO.cadastrar(requerimentoFerias);
			String select = "SELECT * FROM requerimento WHERE id = 4;";
			ResultSet rs = PostgresConector.executarQuery(select);

			if (rs.next()) {

				assertEquals(LocalDate.now().toString(), rs.getDate("datasolicitacao").toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testeBGet() {

		try {

			String id = "";

			PostgresConector.conectar();
			RequerimentoDAO requerimentoFeriasDAO = new RequerimentoDAO();
			RequerimentoFerias requerimentoFerias = requerimentoFeriasDAO.pegarFeriasPorID(1);

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

	@Test
	public void testeCGetAll() {

		try {

			PostgresConector.conectar();
			PostgresConector.executarUpdateQuery(
					"insert into requerimento(idferias, idestadorequisicao, datasolicitacao) values (4, 2, '11/05/2021');");
			ArrayList<RequerimentoFerias> requerimentoFerias = requerimentoDAO.pegarTodos();
			assertTrue(requerimentoFerias.size() == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

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
			RequerimentoFerias requerimentoFerias = new RequerimentoFerias(0, ferias, estadoRequerimento,
					LocalDate.now());
			requerimentoDAO.cadastrar(requerimentoFerias);
			assertTrue(requerimentoDAO.alterar(1, requerimentoFerias));

		}

		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

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
		RequerimentoFerias feriasRequerimento = builderRequerimento.build();

		RequerimentoDAO DAOFerias = new RequerimentoDAO();
		boolean teste = DAOFerias.cadastrar(feriasRequerimento);

		assertFalse(teste);
	}

	@Test
	public void testeFBuscaRequerimentoPorEstado() {
		try {
			PostgresConector.conectar();
			RequerimentoFerias requerimentoFerias = new RequerimentoFerias(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.now());
			RequerimentoFerias requerimentoFerias2 = new RequerimentoFerias(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.now());
			requerimentoDAO.cadastrar(requerimentoFerias);
			requerimentoDAO.cadastrar(requerimentoFerias2);
			EstadosRequerimentos estado = EstadosRequerimentos.APROVADO;
			ArrayList<RequerimentoFerias> listaRequerimento = requerimentoDAO.getRequerimentoPorEstado(estado);
			assertTrue(listaRequerimento.size() == 2);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testeGGetRequerimentoPorData() {
		try {
			PostgresConector.conectar();
			RequerimentoFerias requerimentoFerias = new RequerimentoFerias(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.of(2021, 05, 03));
			RequerimentoFerias requerimentoFerias2 = new RequerimentoFerias(new Ferias(), EstadosRequerimentos.APROVADO,
					LocalDate.of(2021, 05, 03));
			requerimentoDAO.cadastrar(requerimentoFerias);
			requerimentoDAO.cadastrar(requerimentoFerias2);
			LocalDate localdate = LocalDate.of(2021, 05, 03);
			ArrayList<RequerimentoFerias> listaRequerimento = requerimentoDAO.getRequerimentoPorData(localdate);

			assertEquals(2, listaRequerimento.size());

		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testeHRemove() {

		RequerimentoFerias requerimentoFerias = new RequerimentoFerias(new Ferias(), EstadosRequerimentos.APROVADO,
				LocalDate.of(2021, 05, 03));
		requerimentoDAO.cadastrar(requerimentoFerias);
		assertTrue(requerimentoDAO.deletar(1));

	}

	@Test
	public void testeIRemovendoIdNaoExistente() {
		RequerimentoDAO DAOFerias = new RequerimentoDAO();

		assertFalse(DAOFerias.deletar(1));
	}

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
		RequerimentoFerias feriasRequerimento = builderRequerimento.build();

		RequerimentoDAO DAOFerias = new RequerimentoDAO();
		DAOFerias.cadastrar(feriasRequerimento);

		assertNull(DAOFerias.pegarFeriasPorID(2));
	}

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
		RequerimentoFerias feriasRequerimento = builderRequerimento.build();

		RequerimentoDAO DAOFerias = new RequerimentoDAO();
		DAOFerias.cadastrar(feriasRequerimento);

		assertNull(DAOFerias.pegarFeriasPorID(-1));
	}

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
		RequerimentoFerias feriasRequerimento = builderRequerimento.build();

		RequerimentoDAO DAOFerias = new RequerimentoDAO();
		DAOFerias.alterar(3, feriasRequerimento);
	}
}

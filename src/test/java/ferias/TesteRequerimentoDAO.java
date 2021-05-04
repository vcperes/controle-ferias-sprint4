package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.DAO.RequerimentoFeriasDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteRequerimentoDAO {
	DataBase dbSingle = DataBase.getInstance();
	RequerimentoFeriasDAO requerimentoDAO = new RequerimentoFeriasDAO();

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
			RequerimentoFerias requerimentoFerias = new RequerimentoFerias("0", ferias, estadoRequerimento, LocalDate.now());
			requerimentoDAO.cadastrar(requerimentoFerias);
			String select = "SELECT * FROM esquemaferias.requerimento WHERE id = 4;";
			ResultSet rs = PostgresConector.executarQuery(select);

			if(rs.next()) {

				assertEquals("2021-05-04", rs.getDate("datasolicitacao").toString());
			}

		}catch(SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testeBGet() {

		try {

			String id = "";

			PostgresConector.conectar();
			RequerimentoFeriasDAO requerimentoFeriasDAO = new RequerimentoFeriasDAO();
			RequerimentoFerias requerimentoFerias = requerimentoFeriasDAO.pegarPorID(1);

			String select = "SELECT * FROM esquemaferias.requerimento WHERE id = 1;";
			ResultSet rs = PostgresConector.executarQuery(select);

			if (rs.next()) {			

				id = rs.getString("id");

				assertEquals(requerimentoFerias.getId(), id);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}


		//assertEquals(dbSingle.requerimentos.get(0).getIdentificadorUsuario(), "Roberto");
		//assertEquals(dbSingle.requerimentos.get(1).getIdentificadorUsuario(), "Joana");
	}

	@Test
	public void testeCGetAll() {

		try {

			PostgresConector.conectar();


			ArrayList<RequerimentoFerias> requerimentoFerias = requerimentoDAO.pegarTodos();

			assertTrue (requerimentoFerias.size()>=9);
		}
		catch (SQLException e) {
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

			RequerimentoFerias requerimentoFerias = new RequerimentoFerias("0", ferias, estadoRequerimento, LocalDate.now());
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

		directorRequerimento.createRequerimento(builderRequerimento, ferias, "Roberto");
		RequerimentoFerias feriasRequerimento = builderRequerimento.build();

		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		boolean teste = DAOFerias.cadastrar(feriasRequerimento);

		assertFalse(teste);
	}


	@Test
	public void testeFBuscaRequerimentoPorEstado() {
		try {
			EstadosRequerimentos estado = EstadosRequerimentos.APROVADO;
			ArrayList<RequerimentoFerias> listaRequerimento = requerimentoDAO.getRequerimentoPorEstado(estado);
			PostgresConector.conectar();
			assertTrue(listaRequerimento.size() == 2);


		}
		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}


	}

	@Test
	public void testeGGetRequerimentoPorData() {
		try {	
			PostgresConector.conectar();
			LocalDate localdate = LocalDate.of(2021, 05, 03);
			ArrayList<RequerimentoFerias> listaRequerimento = requerimentoDAO.getRequerimentoPorData(localdate);
		
			assertTrue(listaRequerimento.size() == 2);


		}
		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}


@Test
public void testeHRemove() {	

	try {
		PostgresConector.conectar();
		assertTrue(requerimentoDAO.deletar(2)); 		
	}

	catch (SQLException e) {
		e.printStackTrace();
		fail(e.getMessage());


	}

}

@Test
public void testeIRemovendoIdNaoExistente() {	
	RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();

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

	directorRequerimento.createRequerimento(builderRequerimento, ferias, "Joana");
	RequerimentoFerias feriasRequerimento = builderRequerimento.build();

	RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
	DAOFerias.cadastrar(feriasRequerimento);

	assertNull(DAOFerias.pegarPorID(2));
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

	directorRequerimento.createRequerimento(builderRequerimento, ferias, "Joana");
	RequerimentoFerias feriasRequerimento = builderRequerimento.build();

	RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
	DAOFerias.cadastrar(feriasRequerimento);

	assertNull(DAOFerias.pegarPorID(-1));
}


@Test
public void testeDUpdatePorIdInvalido() {
	short creditos = 30;

	LocalDate inicio = LocalDate.of(2021, 04, 01);
	LocalDate fim = LocalDate.of(2021, 04, 28);

	FeriasDirector feriasDiretor = new FeriasDirector();
	FeriasBuilder feriasBuilder = new FeriasBuilder();

	feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, creditos);
	Ferias ferias = feriasBuilder.build(creditos);

	RequerimentoDirector directorRequerimento = new RequerimentoDirector();
	RequerimentoBuilder builderRequerimento = new RequerimentoBuilder();

	directorRequerimento.createRequerimento(builderRequerimento, ferias, "Thiago");
	RequerimentoFerias feriasRequerimento = builderRequerimento.build();

	RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
	assertFalse(DAOFerias.alterar(3, feriasRequerimento)); 
}
}

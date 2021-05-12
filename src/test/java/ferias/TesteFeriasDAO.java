package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class TesteFeriasDAO {

	FeriasDAO feriasDAO = new FeriasDAO();
	ArrayList<Ferias> ferias = new ArrayList<Ferias>();
	
	@Before
	public void limparBanco() throws SQLException {
		feriasDAO.limparTabela();
	}

	@Test
	public void testPegarTodos() throws SQLException {

		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> ferias = feriasDAO.pegarTodos();
		assertTrue(ferias.size() >= 2);
	}

	@Test
	public void testPegarPorID() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/05/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT * FROM ferias WHERE idusuario = 0 AND datafim = '13/05/2021' AND idtipoferias = 3";
		ResultSet resultSet = PostgresConector.executarQuery(consultar);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		IFerias ferias = feriasDAO.pegarFeriasPorID(id);
		assertEquals(id, ferias.getId());
		assertEquals(3, ferias.getTipo().getValor());
	}

	@Test
	public void testCadastrar() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		boolean sucesso = feriasDAO.cadastrar(ferias);
		assertTrue(sucesso);
	}

	@Test
	public void testAlterar() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '15/05/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT * FROM ferias WHERE idusuario = 0 AND datafim = '15/05/2021'";
		ResultSet resultSet = PostgresConector.executarQuery(consultar);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		boolean sucesso = feriasDAO.alterar(id, ferias);

		assertTrue(sucesso);
	}

	@Test
	public void testDelete() throws SQLException {
		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '15/05/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT * FROM ferias WHERE idusuario = 0 AND datafim = '15/05/2021'";
		ResultSet resultSet = PostgresConector.executarQuery(consultar);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		boolean sucesso = feriasDAO.deletar(id);
		assertTrue(sucesso);
	}

	@Test
	public void testPegarFeriasPorIDColaborador() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/05/2021', 0, 4)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 2)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaFerias = feriasDAO.pegarTodasAsFeriasPorIDColaborador(0);
		
		assertTrue(listaFerias.size() == 2);
	}

	@Test
	public void testPegarTodasAsFeriasPorTipo() throws SQLException {

		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(3, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(4, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaTodasAsFeriasPorTipo = feriasDAO.pegarTodasAsFeriasPorTipo(TiposFerias.PARCIAL);
		for (IFerias iFerias2 : listaTodasAsFeriasPorTipo) {
			assertTrue(iFerias2.getTipo().getValor() == 3);
		}
	}
}

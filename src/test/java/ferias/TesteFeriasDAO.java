package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class TesteFeriasDAO {

	FeriasDAO feriasDAO = new FeriasDAO();
	ArrayList<Ferias> ferias = new ArrayList<Ferias>();

	@Test
	public void testPegarTodos() throws SQLException {

		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> ferias = feriasDAO.pegarTodos();
		assertTrue(ferias.size() >= 2);

		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 3";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 3";
		PostgresConector.executarUpdateQuery(deletarId2);
	}

	@Test
	public void testPegarPorID() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/05/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT * FROM ferias WHERE id_colaborador = 0 AND datafim = '13/05/2021' AND id_tipoferias = 3";
		ResultSet resultSet = PostgresConector.executarQuery(consultar);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		IFerias ferias = feriasDAO.pegarPorID(id);
		assertEquals(id, ferias.getId());
		assertEquals(3, ferias.getTipo().getValor());

		String deletar = "DELETE FROM ferias WHERE id = " + id;
		PostgresConector.executarUpdateQuery(deletar);
	}

	@Test
	public void testCadastrar() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario("0");
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		boolean sucesso = feriasDAO.cadastrar(ferias);
		assertTrue(sucesso);

		String pegarID = "SELECT * FROM ferias WHERE id_colaborador = 0 AND diasvendidos = 0 AND id_tipoferias = 3";
		ResultSet resultSet = PostgresConector.executarQuery(pegarID);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		String deletar = "DELETE FROM ferias WHERE id = " + id;
		PostgresConector.executarUpdateQuery(deletar);
	}

	@Test
	public void testAlterar() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '15/05/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT * FROM ferias WHERE id_colaborador = 0 AND datafim = '15/05/2021'";
		ResultSet resultSet = PostgresConector.executarQuery(consultar);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario("0");
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		boolean sucesso = feriasDAO.alterar(id, ferias);

		assertTrue(sucesso);

		String deletar = "DELETE FROM ferias WHERE id = " + id;
		PostgresConector.executarUpdateQuery(deletar);
	}

	@Test
	public void testDelete() throws SQLException {
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '15/05/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT * FROM ferias WHERE id_colaborador = 0 AND datafim = '15/05/2021'";
		ResultSet resultSet = PostgresConector.executarQuery(consultar);
		int id = 111;
		if (resultSet.next()) {
			id = resultSet.getInt("id");
		}

		boolean sucesso = feriasDAO.deletar(id);
		assertTrue(sucesso);
	}

	@Test
	public void testPegarTodasAsFeriasTotais() throws SQLException {
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 2)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 2)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaTodasAsFeriasTotais = feriasDAO.pegarTodasAsFeriasTotais();
		boolean ehFeriasTotal = true;
		for (IFerias iFerias : listaTodasAsFeriasTotais) {
			if (iFerias.getTipo().getValor() != 2) {
				ehFeriasTotal = false;
			}
			assertTrue(ehFeriasTotal);
		}

		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 2";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 2";
		PostgresConector.executarUpdateQuery(deletarId2);
	}

	@Test
	public void testPegarTodasAsFeriasInvalidas() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/04/2021', 0, 1)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/04/2021', 0, 1)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaTodasAsFeriasInvalidas = feriasDAO.pegarTodasAsFeriasInvalidas();
		boolean ehFeriasInvalida = true;
		for (IFerias iFerias : listaTodasAsFeriasInvalidas) {
			if (iFerias.getTipo().getValor() != 1) {
				ehFeriasInvalida = false;
			}
		}
		assertTrue(ehFeriasInvalida);

		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/04/2021' and diasvendidos = 0 and id_tipoferias = 1";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/04/2021' and diasvendidos = 0 and id_tipoferias = 1";
		PostgresConector.executarUpdateQuery(deletarId2);
	}

	@Test
	public void testPegarTodasAsFeriasParciais() throws SQLException {

		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(3, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(4, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaTodasAsFeriasParciais = feriasDAO.pegarTodasAsFeriasParciais();
		boolean ehFeriasParcial = true;
		for (IFerias iFerias2 : listaTodasAsFeriasParciais) {

			if (iFerias2.getTipo().getValor() != 3) {
				ehFeriasParcial = false;

			}
			assertTrue(ehFeriasParcial);
		}

		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 3 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 3";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 4 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 3";
		PostgresConector.executarUpdateQuery(deletarId2);
	}

}


	@Test
	public void testPegarTodasAsFeriasFracionadas() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/05/2021', 0, 4)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '14/05/2021', 0, 4)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaTodasAsFeriasFracionadas = feriasDAO.pegarTodasAsFeriasFracionadas();
		boolean ehFeriasFracionada = true;
		for (IFerias iFerias : listaTodasAsFeriasFracionadas) {
			if (iFerias.getTipo().getValor() != 4) {
				ehFeriasFracionada = false;
			}
			
		}
		assertTrue(ehFeriasFracionada);

		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/05/2021' and diasvendidos = 0 and id_tipoferias = 4";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '14/05/2021' and diasvendidos = 0 and id_tipoferias = 4";
		PostgresConector.executarUpdateQuery(deletarId2);
	}

	@Test
	public void testPegarTodasAsFeriasVendidas() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '01/01/0001', '01/01/0001', 10, 5)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '01/01/0001', '01/01/0001', 15, 5)";
		PostgresConector.executarUpdateQuery(inserir2);

		ArrayList<IFerias> listaTodasAsFeriasVendidas = feriasDAO.pegarTodasAsFeriasVendidas();
		boolean ehFeriasVendida = true;
		for (IFerias iFerias : listaTodasAsFeriasVendidas) {
			if (iFerias.getTipo().getValor() != 5) {
				ehFeriasVendida = false;
			}
			
		}
		assertTrue(ehFeriasVendida);
		
		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '01/01/0001' and datafim = '01/01/0001' and diasvendidos = 10 and id_tipoferias = 5";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '01/01/0001' and datafim = '01/01/0001' and diasvendidos = 15 and id_tipoferias = 5";
		PostgresConector.executarUpdateQuery(deletarId2);
	}

	@Test
	public void testPegarFeriasPorIDColaborador() throws SQLException {
		PostgresConector.conectar();
		String inserir = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/05/2021', 0, 4)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 2)";
		PostgresConector.executarUpdateQuery(inserir2);
		
		ArrayList<IFerias> listaFerias = feriasDAO.pegarFeriasPorIDColaborador(0);
		boolean ehDoColaborador = true;
		for (IFerias iFerias : listaFerias) {
			if (!iFerias.getIdentificadorUsuario().toString().equals("0")) {
				ehDoColaborador = false;
			}
			
		}
		assertTrue(ehDoColaborador);
		assertTrue(listaFerias.size() == 2);
		
		String deletarId1 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/05/2021' and diasvendidos = 0 and id_tipoferias = 4";
		PostgresConector.executarUpdateQuery(deletarId1);
		String deletarId2 = "DELETE FROM ferias WHERE id_colaborador = 0 and datainicio = '03/05/2021' and datafim = '13/06/2021' and diasvendidos = 0 and id_tipoferias = 2";
		PostgresConector.executarUpdateQuery(deletarId2);
	}
}


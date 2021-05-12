package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

public class FeriasControllerTest {
	
	FeriasController feriasController = new FeriasController();
	FeriasDAO feriasDAO = new FeriasDAO();
	
	@Before
	public void limparBanco () {
		feriasDAO.limparTabela();
	}

	@Test
	public void testPegarTodos() throws SQLException {
		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);
		String inserir2 = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir2);
		assertEquals(feriasController.pegarTodos().size(), 2);
	}

	@Test
	public void testPegarFeriasPorId() throws SQLException {
		String inserir = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES(0, '03/05/2021', '13/06/2021', 0, 3)";
		PostgresConector.executarUpdateQuery(inserir);
		
		assertEquals(feriasController.pegarFeriasPorId(1).getDataInicio(), LocalDate.of(2021, 05, 03) );
	}

	@Test
	public void testCadastrar() throws SQLException{
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.PARCIAL);

		feriasController.cadastrar(ferias);
		String select = "Select * from ferias;";
		ResultSet rs = PostgresConector.executarQuery(select);
		if(rs.next()) {
			assertEquals(0, rs.getInt(2));
		} else {
			fail("Não foi encontrado nenhum item no banco de dados");
		}
	}

	@Test
	public void testAlterar() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasController.cadastrar(ferias);
		
		IFerias ferias2 = new Ferias();
		ferias2.setIdentificadorUsuario(1);
		ferias2.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias2.setDataFim(LocalDate.of(2221, 04, 10));
		ferias2.setDiasVendidos(0);
		ferias2.setTipo(TiposFerias.PARCIAL);
		feriasController.alterar(1, ferias2);
		String select = "Select * from ferias;";
		ResultSet rs = PostgresConector.executarQuery(select);
		if(rs.next()) {
			assertEquals(1, rs.getInt(2));
		} else {
			fail("Não foi encontrado nenhum item no banco de dados");
		}
	}

	@Test
	public void testDeletar() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasController.cadastrar(ferias);
		
		feriasController.deletar(1);
		
		String select = "Select * from ferias;";
		ResultSet rs = PostgresConector.executarQuery(select);
		if(rs.next()) {
			fail("O banco não está vazio");
		} 
	}

	@Test
	public void testPegarTodasAsFeriasPorTiposIguais() {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.TOTAL);
		feriasController.cadastrar(ferias);
		
		IFerias ferias2 = new Ferias();
		ferias2.setIdentificadorUsuario(0);
		ferias2.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias2.setDataFim(LocalDate.of(2221, 04, 10));
		ferias2.setDiasVendidos(0);
		ferias2.setTipo(TiposFerias.TOTAL);
		feriasController.cadastrar(ferias);
		
		ArrayList<IFerias> feriases = feriasController.pegarTodasAsFeriasPorTipo(TiposFerias.TOTAL);
		assertEquals(feriases.size(), 2);
	}
	
	@Test
	public void testPegarTodasAsFeriasPorTiposDiferentes() {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.TOTAL);
		feriasController.cadastrar(ferias);
		
		IFerias ferias2 = new Ferias();
		ferias2.setIdentificadorUsuario(0);
		ferias2.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias2.setDataFim(LocalDate.of(2221, 04, 10));
		ferias2.setDiasVendidos(0);
		ferias2.setTipo(TiposFerias.PARCIAL);
		feriasController.cadastrar(ferias2);
		
		ArrayList<IFerias> feriases = feriasController.pegarTodasAsFeriasPorTipo(TiposFerias.TOTAL);
		assertEquals(feriases.size(), 1);
	}

}

package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeriasControllerTest {

	FeriasController feriasController = new FeriasController();

	@Test
	public void AtestCadastrar() throws SQLException {
		IFerias ferias = new Ferias();
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();

		director.createFeriasParcial(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 22), (short) 30);
		Ferias ferias2 = builder.build(30);
		feriasController.cadastrar(ferias2);
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.PARCIAL);

		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(1);
		assertEquals(1, feriasdb.getId());
	}

	@Test
	public void BtestAlterar() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(1);
		ferias.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasController.cadastrar(ferias);
		ferias.setDiasVendidos(2);
		feriasController.alterar(ferias);
		assertEquals(2, feriasController.pegarFeriasPorId(2).getDiasVendidos());
	}

	@Test
	public void CtestPegarTodos() throws SQLException {
		assertEquals(2, feriasController.pegarTodos().size());
	}

	@Test
	public void DtestPegarFeriasPorId() throws SQLException {
		Ferias ferias = new Ferias();
		ferias.setIdentificadorUsuario(1);
		ferias.setDataInicio(LocalDate.of(2221, 04, 01));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasController.cadastrar(ferias);
		assertEquals(ferias, feriasController.pegarFeriasPorId(3));
	}

	@Test
	public void EtestCPegarFeriasPorTipo() {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2220, 02, 9));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.TOTAL);
		feriasController.cadastrar(ferias);

		assertEquals(3, feriasController.pegarTodasAsFeriasPorTipo(TiposFerias.PARCIAL).size());
	}

	@Test
	public void FtestPegarFeriasPorData() {

		assertEquals(2, feriasController.pegarTodasAsFeriasPorDataInicio(LocalDate.of(2221, 04, 01)).size());
	}

	@Test
	public void GtestDeletar() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2220, 02, 9));
		ferias.setDataFim(LocalDate.of(2221, 04, 10));
		ferias.setDiasVendidos(0);
		ferias.setTipo(TiposFerias.TOTAL);
		feriasController.cadastrar(ferias);

		feriasController.deletar(ferias);
		assertEquals(4, feriasController.pegarTodos().size());
	}

}

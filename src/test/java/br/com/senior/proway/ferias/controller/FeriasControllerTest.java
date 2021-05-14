package br.com.senior.proway.ferias.controller;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class FeriasControllerTest {

	FeriasController feriasController = new FeriasController();

	@Test
	public void AtestCadastrar() throws SQLException {
		
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasParcial(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 22), (short) 30);
		Ferias ferias2 = builder.build(30);
		feriasController.cadastrar(ferias2);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(1);
		
		assertEquals(9, feriasdb.getDiasVendidos());
	}

	@Test
	public void BtestAlterar() throws SQLException {
		
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasParcial(builder, LocalDate.of(2221, 04, 01), LocalDate.of(2221, 04, 10), (short) 30);
		Ferias ferias2 = builder.build(30);
		feriasController.cadastrar(ferias2);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(2);
		feriasdb.setIdentificadorUsuario(7);
		feriasController.alterar(feriasdb);
		
		assertEquals(7, feriasController.pegarFeriasPorId(2).getIdentificadorUsuario());
	}

	@Test
	public void CtestPegarTodos() throws SQLException {
		assertEquals(2, feriasController.pegarTodos().size());
	}

	@Test
	public void DtestPegarFeriasPorId() throws SQLException {
		
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasParcial(builder, LocalDate.of(2221, 04, 01), LocalDate.of(2221, 04, 10), (short) 30);
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		
		assertEquals(ferias, feriasController.pegarFeriasPorId(3));
	}

	@Test
	public void EtestCPegarFeriasPorTipo() {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasTotal(builder, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 30));
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);

		assertEquals(3, feriasController.pegarTodasAsFeriasPorTipo(TiposFerias.PARCIAL).size());
	}

	@Test
	public void FtestPegarFeriasPorData() {

		assertEquals(2, feriasController.pegarTodasAsFeriasPorDataInicio(LocalDate.of(2221, 04, 01)).size());
	}

	@Test
	public void GtestDeletar() throws SQLException {
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasTotal(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 31));
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		feriasController.deletar(ferias);
		assertEquals(4, feriasController.pegarTodos().size());
	}
	@Test
	public void HtestCadastrarFracionada() throws SQLException {
		
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasFracionada(builder, LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 15));
		Ferias ferias = builder.build(30);
		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(6);
		assertEquals(14, feriasdb.getDiasTotaisRequisitados());
	}
	@Test
	public void ItestCadastrarVendida() throws SQLException {
		
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasVendida(builder, (short) 5);
		Ferias ferias = builder.build(5);
		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(7);
		assertEquals(5, feriasdb.getDiasVendidos());
	}
	@Test
	public void JtestCadastrarVendidaEspecifica() throws SQLException {
		
		FeriasDirector director = new FeriasDirector();
		FeriasBuilder builder = new FeriasBuilder();
		director.createFeriasVendidaEspecifica(builder, (short) 7);
		Ferias ferias = builder.build(7);
		feriasController.cadastrar(ferias);
		Ferias feriasdb = (Ferias) feriasController.pegarFeriasPorId(8);
		assertEquals(7, feriasdb.getDiasVendidos());
	}
	@Test
	public void KtestPegarFeriasPorIdColaborador() throws SQLException {

		assertEquals(6, feriasController.pegarTodasAsFeriasPorIDColaborador(0).size());
	}

}

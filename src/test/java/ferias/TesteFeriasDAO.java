package ferias;

import static org.junit.Assert.*;

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

public class TesteFeriasDAO {

	FeriasDAO feriasDAO = new FeriasDAO();
	ArrayList<Ferias> ferias = new ArrayList<Ferias>();

	@Test
	public void testGetAll() {
		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);
		// invalida
		short creditos4 = 0;
		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
		LocalDate fim4 = LocalDate.of(2021, 04, 01);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
		Ferias ferias4 = feriasBuilder.build(creditos4);
		DataBase.getInstance().ferias.add(ferias4);

		for (Ferias umaFerias : ferias) {
			DataBase.getInstance().ferias.add(umaFerias);

			ArrayList<IFerias> feriasRecebidas = feriasDAO.pegarTodos();
			assertEquals(feriasRecebidas.size(), DataBase.getInstance().ferias.size());
			DataBase.getInstance().limparListaDeFerias();
		}
	}

	@Test
	public void testGetAllVazio() {
		ArrayList<IFerias> feriasRecebidas = feriasDAO.pegarTodos();
		assertEquals(feriasRecebidas.size(), 0);
	}

	@Test
	public void testPegarPorID() {
		int id = 1;
		IFerias ferias = feriasDAO.pegarPorID(id);
		assertEquals(1, ferias.getId());
		assertEquals(3, ferias.getTipo().getValor());
	}

	@Test
	public void testCreate() {

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);
		// invalida
		short creditos4 = 0;
		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
		LocalDate fim4 = LocalDate.of(2021, 04, 01);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
		Ferias ferias4 = feriasBuilder.build(creditos4);
		DataBase.getInstance().ferias.add(ferias4);

		for (Ferias umaFerias : ferias) {
			DataBase.getInstance().ferias.add(umaFerias);

			ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
			assertEquals(4, ferias.size());
			DataBase.getInstance().limparListaDeFerias();
		}
	}

	@Test
	public void testUpdate() {

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);
		// invalida
		short creditos4 = 0;
		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
		LocalDate fim4 = LocalDate.of(2021, 04, 01);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
		Ferias ferias4 = feriasBuilder.build(creditos4);
		DataBase.getInstance().ferias.add(ferias4);

		for (Ferias umaFerias : ferias) {
			DataBase.getInstance().ferias.add(umaFerias);

			assertEquals(DataBase.getInstance().ferias.get(0).getTipo(), TiposFerias.PARCIAL);

			IFerias novaFerias = DataBase.getInstance().ferias.get(1);
			boolean sucesso = feriasDAO.alterar(0, novaFerias);

			assertTrue(sucesso);
			assertEquals(DataBase.getInstance().ferias.get(0).getTipo(), TiposFerias.TOTAL);
			DataBase.getInstance().limparListaDeFerias();
		}
	}

//	@Ignore
//	public void testDelete() {
// 		this.popularListaDeFerias();
// 		ArrayList<IFerias> ferias = DataBase.getInstance().ferias;
// falta implementar m�todo para inserir id no objeto no momento na cria��o
//	}

	@Test
	public void testPegarTodasAsFeriasTotais() {

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);
		// invalida
		short creditos4 = 0;
		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
		LocalDate fim4 = LocalDate.of(2021, 04, 01);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
		Ferias ferias4 = feriasBuilder.build(creditos4);
		DataBase.getInstance().ferias.add(ferias4);

		for (Ferias umaFerias : ferias) {
			DataBase.getInstance().ferias.add(umaFerias);

			ArrayList<IFerias> ferias = feriasDAO.pegarTodasAsFeriasTotais();
			assertEquals(2, ferias.size());
			DataBase.getInstance().limparListaDeFerias();
		}
	}

	@Test
	public void testPegarTodasAsFeriasInvalidas() {

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);
		// invalida
		short creditos4 = 0;
		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
		LocalDate fim4 = LocalDate.of(2021, 04, 01);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
		Ferias ferias4 = feriasBuilder.build(creditos4);
		DataBase.getInstance().ferias.add(ferias4);

		for (Ferias umaFerias : ferias) {
			DataBase.getInstance().ferias.add(umaFerias);

			ArrayList<IFerias> ferias = feriasDAO.pegarTodasAsFeriasInvalidas();
			assertEquals(1, ferias.size());
			DataBase.getInstance().limparListaDeFerias();
		}
	}

	@Test
	public void testPegarTodasAsFeriasParciais() {

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);
		// invalida
		short creditos4 = 0;
		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
		LocalDate fim4 = LocalDate.of(2021, 04, 01);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
		Ferias ferias4 = feriasBuilder.build(creditos4);
		DataBase.getInstance().ferias.add(ferias4);

		for (Ferias umaFerias : ferias) {
			DataBase.getInstance().ferias.add(umaFerias);

			ArrayList<IFerias> ferias = feriasDAO.pegarTodasAsFeriasParciais();
			assertEquals(2, ferias.size());
			DataBase.getInstance().limparListaDeFerias();
		}

	}

//	@Ignore
//	public void testPegarTodasAsFeriasFracionadas() {
//		fail("Not yet implemented");
//	}
//
//	@Ignore
//	public void testPegarTodasAsFeriasVendidas() {
//		fail("Not yet implemented");
//	}
//
//	@Ignore
//	public void testPegarFeriasPorIDColaborador() {
//		fail("Not yet implemented");
//	}
}
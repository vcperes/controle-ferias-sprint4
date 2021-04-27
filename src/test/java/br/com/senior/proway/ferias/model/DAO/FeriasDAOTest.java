package br.com.senior.proway.ferias.model.DAO;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public class FeriasDAOTest {

	FeriasDAO feriasDAO = new FeriasDAO();

	/*
	 * short creditos = 30; LocalDate inicio = LocalDate.of(2021, 04, 01); LocalDate
	 * fim = LocalDate.of(2021, 04, 28);
	 * feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, creditos);
	 * feriasBuilder.build(creditos);
	 */

	public void popularListaDeFerias() {
		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();

		ArrayList<Ferias> ferias = new ArrayList<Ferias>();

		// parcial
		short creditos0 = 29;
		LocalDate inicio0 = LocalDate.of(2021, 04, 01);
		LocalDate fim0 = LocalDate.of(2021, 04, 27);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio0, fim0, creditos0);
		Ferias ferias0 = feriasBuilder.build(creditos0);
		DataBase.getInstance().ferias.add(ferias0);
//
		// total
		short creditos1 = 30;
		LocalDate inicio1 = LocalDate.of(2021, 04, 01);
		LocalDate fim1 = LocalDate.of(2021, 04, 30);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio1, fim1);
		Ferias ferias1 = feriasBuilder.build(creditos1);
		DataBase.getInstance().ferias.add(ferias1);
//
		// parcial
		short creditos2 = 15;
		LocalDate inicio2 = LocalDate.of(2021, 04, 01);
		LocalDate fim2 = LocalDate.of(2021, 04, 10);
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio2, fim2, creditos2);
		Ferias ferias2 = feriasBuilder.build(creditos2);
		DataBase.getInstance().ferias.add(ferias2);
//
		// total
		short creditos3 = 20;
		LocalDate inicio3 = LocalDate.of(2021, 04, 01);
		LocalDate fim3 = LocalDate.of(2021, 04, 19);
		feriasDiretor.createFeriasTotal(feriasBuilder, inicio3, fim3);
		Ferias ferias3 = feriasBuilder.build(creditos3);
		DataBase.getInstance().ferias.add(ferias3);

		// invalida
//		short creditos4 = 0;
//		LocalDate inicio4 = LocalDate.of(2021, 04, 05);
//		LocalDate fim4 = LocalDate.of(2021, 04, 01);
//		feriasDiretor.createFeriasParcial(feriasBuilder, inicio4, fim4, creditos4);
//		Ferias ferias4 = feriasBuilder.build(creditos4);
//		DataBase.getInstance().ferias.add(ferias4);

//		for (Ferias umaFerias : ferias) {
//			DataBase.getInstance().ferias.add(umaFerias);
//		}
	}

	
	
	
	@Test
	public void testGetAll() {
		this.popularListaDeFerias();

		ArrayList<IFerias> feriasRecebidas = feriasDAO.getAll();

		assertEquals(feriasRecebidas.size(), DataBase.getInstance().ferias.size());
		DataBase.getInstance().limparListaDeFerias();
	}

	@Test
	public void testGetAllVazio() {
		ArrayList<IFerias> feriasRecebidas = feriasDAO.getAll();

		assertEquals(feriasRecebidas.size(), 0);
	}

	@Ignore
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		this.popularListaDeFerias();
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		assertEquals(4, ferias.size());
		DataBase.getInstance().limparListaDeFerias();
	}

//	@Ignore
//	public void testUpdate() {
//		this.popularListaDeFerias();
//
//		assertEquals(DataBase.getInstance().ferias.get(0).getTipo(), TiposFerias.PARCIAL);
//
//		IFerias novaFerias = DataBase.getInstance().ferias.get(1);
//		boolean sucesso = feriasDAO.update(0, novaFerias);
//
//		assertTrue(sucesso);
//		assertEquals(DataBase.getInstance().ferias.get(0).getTipo(), TiposFerias.TOTAL);
//		this.limparListaDeFerias();
//	}

	@Ignore
	public void testDelete() {
		//this.popularListaDeFerias();
	//	ArrayList<IFerias> ferias = DataBase.getInstance().ferias;
		// falta implementar método para inserir id no objeto no momento na criação
	}

	@Test
	public void testPegarTodasAsFeriasTotais() {
		this.popularListaDeFerias();
		ArrayList<IFerias> ferias = feriasDAO.pegarTodasAsFeriasTotais();
		assertEquals(2, ferias.size());
		DataBase.getInstance().limparListaDeFerias();
	}

//	@Ignore
//	public void testPegarTodasAsFeriasInvalidas() {
//		//this.popularListaDeFerias();
//		ArrayList<IFerias> ferias = feriasDAO.getAll();
//		for(IFerias feriasss : ferias) {
//			System.out.println(feriasss.getTipo());
//		}
//
//		ArrayList<IFerias> ferias = feriasDAO.pegarTodasAsFeriasInvalidas();
//		assertEquals(ferias.size(), 2);
//		this.limparListaDeFerias();
//	}

	@Ignore
	public void testPegarTodasAsFeriasParciais() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testPegarTodasAsFeriasFracionadas() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testPegarTodasAsFeriasVendidas() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testPegarFeriasPorIDColaborador() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testObject() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testClone() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testToString() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWait() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testFinalize() {
		fail("Not yet implemented");
	}

}

package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteRequerimentoDAO {
	DataBase dbSingle = DataBase.getInstance();

	@Test
	public void testeACreate() {
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
		boolean resultado = DAOFerias.cadastrar(feriasRequerimento);
		
		assertTrue(resultado); 
		assertEquals(dbSingle.requerimentos.size(), 1); 
		assertEquals(dbSingle.requerimentos.get(0).getIdentificadorUsuario(), "Roberto");	
	}
	
	@Test
	public void testeBGet() {
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
		
		assertSame(dbSingle.requerimentos.get(0), DAOFerias.pegarPorID(0));
		
		//assertEquals(dbSingle.requerimentos.get(0).getIdentificadorUsuario(), "Roberto");
		//assertEquals(dbSingle.requerimentos.get(1).getIdentificadorUsuario(), "Joana");
	}
	
	@Test
	public void testeCGetAll() {
		short creditos = 30;

		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();

		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, creditos);
		Ferias ferias = feriasBuilder.build(creditos);

		RequerimentoDirector directorRequerimento = new RequerimentoDirector();
		RequerimentoBuilder builderRequerimento = new RequerimentoBuilder();
		
		directorRequerimento.createRequerimento(builderRequerimento, ferias, "Godofredo");
		RequerimentoFerias feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.cadastrar(feriasRequerimento);
		
		// -----------------
		
		RequerimentoDirector directorRequerimento2 = new RequerimentoDirector();
		RequerimentoBuilder builderRequerimento2 = new RequerimentoBuilder();
		
		directorRequerimento2.createRequerimento(builderRequerimento2, ferias, "Tiburcio");
		RequerimentoFerias feriasRequerimento2 = builderRequerimento2.build();
		
		RequerimentoFeriasDAO DAOFerias2 = new RequerimentoFeriasDAO();
		
		// ----------------- Criacao de Testes
		
		assertSame(dbSingle.requerimentos, DAOFerias2.pegarTodos());
	}
	
	@Test
	public void testeDUpdate() {
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
		assertTrue(DAOFerias.alterar(2, feriasRequerimento)); 
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
		ArrayList<RequerimentoFerias> listaDeRequerimentosEstado = new ArrayList<RequerimentoFerias>();
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		
		listaDeRequerimentosEstado.addAll(DAOFerias.getRequerimentoPorEstado(EstadosRequerimentos.EM_ANALISE));
		
		assertEquals(listaDeRequerimentosEstado.get(0).getEstadoRequisicao(), EstadosRequerimentos.EM_ANALISE);
	}
	
	@Test
	public void testeGGetRequerimentoPorData() {
		ArrayList<RequerimentoFerias> listaDeRequerimentosData = new ArrayList<RequerimentoFerias>();
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		
		listaDeRequerimentosData.addAll(DAOFerias.getRequerimentoPorData(LocalDate.of(2021, 04, 28)));
		
		assertEquals(listaDeRequerimentosData.get(0).getDataSolicitacao(), LocalDate.of(2021, 04, 28));
		assertNotEquals(listaDeRequerimentosData.get(1).getDataSolicitacao(), LocalDate.of(2021, 04, 29));
	}
	
	@Test
	public void testeHRemove() {	
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.deletar(3); 
		DAOFerias.deletar(2); 

		assertTrue(DAOFerias.deletar(1));
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

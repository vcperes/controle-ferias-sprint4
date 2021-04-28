package testeBuildersFeriasRequerimento;

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
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.DAO.RequerimentoFeriasDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequisicao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteRequerimentoDB {
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();

		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		boolean resultado = DAOFerias.create(feriasRequerimento);
		
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.create(feriasRequerimento);
		
		assertSame(dbSingle.requerimentos.get(0), DAOFerias.get(0));
		
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.create(feriasRequerimento);
		
		// -----------------
		
		RequerimentoDirector directorRequerimento2 = new RequerimentoDirector();
		RequerimentoBuilder builderRequerimento2 = new RequerimentoBuilder();
		
		directorRequerimento2.createRequerimento(builderRequerimento2, ferias, "Tiburcio");
		FeriasRequerimento feriasRequerimento2 = builderRequerimento2.build();
		
		RequerimentoFeriasDAO DAOFerias2 = new RequerimentoFeriasDAO();
		
		// ----------------- Criacao de Testes
		
		assertSame(dbSingle.requerimentos, DAOFerias2.getAll());
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		assertTrue(DAOFerias.update(2, feriasRequerimento)); 
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();

		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		boolean teste = DAOFerias.create(feriasRequerimento);
		
		assertFalse(teste);
	}
	
	
	@Test
	public void testeFBuscaRequerimentoPorEstado() {
		ArrayList<FeriasRequerimento> listaDeRequerimentosEstado = new ArrayList<FeriasRequerimento>();
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		
		listaDeRequerimentosEstado.addAll(DAOFerias.getRequerimentoPorEstado(EstadosRequisicao.EM_ANALISE));
		
		assertEquals(listaDeRequerimentosEstado.get(0).getEstadoRequisicao(), EstadosRequisicao.EM_ANALISE);
	}
	
	@Test
	public void testeGGetRequerimentoPorData() {
		ArrayList<FeriasRequerimento> listaDeRequerimentosData = new ArrayList<FeriasRequerimento>();
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		
		listaDeRequerimentosData.addAll(DAOFerias.getRequerimentoPorData(LocalDate.of(2021, 04, 28)));
		
		assertEquals(listaDeRequerimentosData.get(0).getDataSolicitacao(), LocalDate.of(2021, 04, 28));
		assertNotEquals(listaDeRequerimentosData.get(1).getDataSolicitacao(), LocalDate.of(2021, 04, 29));
	}
	
	@Test
	public void testeHRemove() {	
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.delete(3); 
		DAOFerias.delete(2); 

		assertTrue(DAOFerias.delete(1));
	}
	
	@Test
	public void testeIRemovendoIdNãoExistente() {	
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();

		assertFalse(DAOFerias.delete(1));
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.create(feriasRequerimento);
		
		assertNull(DAOFerias.get(2));
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.create(feriasRequerimento);
		
		assertNull(DAOFerias.get(-1));
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
		FeriasRequerimento feriasRequerimento = builderRequerimento.build();
		
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		assertFalse(DAOFerias.update(3, feriasRequerimento)); 
	}
}

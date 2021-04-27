package testeBuildersFeriasRequerimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.RequerimentoBuilder;
import br.com.senior.proway.ferias.model.RequerimentoDirector;
import br.com.senior.proway.ferias.model.DAO.RequerimentoFeriasDAO;
import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.FeriasRequerimento;

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
		assertEquals(dbSingle.historico.size(), 1);
		assertEquals(dbSingle.historico.get(0).getIdentificadorUsuario(), "Roberto");		
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
		
		for(FeriasRequerimento item : dbSingle.historico) {
			System.out.println("itens de array get: "+item.getIdentificadorUsuario());
		}
		
		assertEquals(dbSingle.historico.get(0).getIdentificadorUsuario(), "Roberto");
		assertEquals(dbSingle.historico.get(1).getIdentificadorUsuario(), "Joana");
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
		DAOFerias2.create(feriasRequerimento2);
				
		assertEquals(DAOFerias.getAll(), dbSingle.historico);
		assertEquals(dbSingle.historico.get(3).getIdentificadorUsuario(), "Tiburcio");
		assertEquals(dbSingle.historico.size(), 4);
	}
	
	@Test
	public void testeDUpdate() {
		short creditos = 30;
		
		for(FeriasRequerimento item : dbSingle.historico) {
			System.out.println("Itens de array pre update: "+item.getIdentificadorUsuario());
		}
		
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
		
		boolean resultado = DAOFerias.update(3, feriasRequerimento);
		assertTrue(resultado);

	}
	
	@Test
	public void testeERemove() {	
		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		DAOFerias.delete(3); 
		DAOFerias.delete(2); 
		DAOFerias.delete(1); 
		
		assertTrue(dbSingle.historico.size() == 1);
		assertEquals(dbSingle.historico.get(0).getIdentificadorUsuario(), "Roberto");		
	}
	
	
	
	@Test
	public void testeFCreateDuplicado() {
		short creditos = 30;
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, creditos);
		Ferias ferias = feriasBuilder.build(creditos);

		// assertEquals(ferias.tipoFerias, TiposFerias.PARCIAL);
		// Criando Requerimento
		RequerimentoDirector directorRequerimento = new RequerimentoDirector();

		RequerimentoBuilder builderRequerimento = new RequerimentoBuilder();
		directorRequerimento.createRequerimento(builderRequerimento, ferias, "Roberto");
		

		FeriasRequerimento feriasRequerimento = builderRequerimento.build();

		RequerimentoFeriasDAO DAOFerias = new RequerimentoFeriasDAO();
		
		boolean teste = DAOFerias.create(feriasRequerimento);
		
		assertFalse(teste);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

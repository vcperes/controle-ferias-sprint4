package testeBuildersFeriasRequerimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.BuilderRequerimento;
import br.com.senior.proway.ferias.model.DataBaseSingle;
import br.com.senior.proway.ferias.model.DirectorRequerimento;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.FeriasRequerimentoDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteDb {
	DataBaseSingle dbSingle = DataBaseSingle.getInstance("historico");

	@Test
	public void testeACreate() {

		System.out.println("tamanho do ArryList TesteCreate: " + dbSingle.historico.size());
		
		// Criando Ferias
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, (short) 30);
		Ferias ferias = feriasBuilder.build();

		// assertEquals(ferias.tipoFerias, TiposFerias.PARCIAL);
		// Criando Requerimento
		DirectorRequerimento directorRequerimento = new DirectorRequerimento();

		BuilderRequerimento builderRequerimento = new BuilderRequerimento();
		directorRequerimento.constructRequerimento(builderRequerimento, ferias, "Roberto");
		

		FeriasRequerimento feriasRequerimento = builderRequerimento.getResult();

		FeriasRequerimentoDAO DAOFerias = new FeriasRequerimentoDAO();
		
		boolean resultado = DAOFerias.create(feriasRequerimento);
		
		assertTrue(resultado);
		assertEquals(dbSingle.historico.size(), 1);
		assertEquals(dbSingle.historico.get(0).getIdentificadorUsuario(), "Roberto");
		
		//System.out.println(dbSingle.historico.get(0).getFeriasRequisitada().tipoFerias);
		
	}
	
	@Test
	public void testeBGet() {
		
		System.out.println("tamanho do ArryList TesteGet: " + dbSingle.historico.size());
		
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		
		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, (short) 30);
		Ferias ferias = feriasBuilder.build();
		
		
		DirectorRequerimento directorRequerimento = new DirectorRequerimento();
		BuilderRequerimento builderRequerimento = new BuilderRequerimento();
		directorRequerimento.constructRequerimento(builderRequerimento, ferias, "Joana");
		
		
		FeriasRequerimento feriasRequerimento = builderRequerimento.getResult();
		FeriasRequerimentoDAO DAOFerias = new FeriasRequerimentoDAO();
		DAOFerias.create(feriasRequerimento);
		
		for(FeriasRequerimento item : dbSingle.historico) {
			System.out.println("itens de array get: "+item.getIdentificadorUsuario());
		}
		
		assertEquals(dbSingle.historico.get(0).getIdentificadorUsuario(), "Roberto");
		assertEquals(dbSingle.historico.get(1).getIdentificadorUsuario(), "Joana");
	}
	
	@Test
	public void testeCGetAll() {
		
		System.out.println("tamanho do ArryList testeGetAll: " + dbSingle.historico.size());

		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();

		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, (short) 30);
		Ferias ferias = feriasBuilder.build();


		DirectorRequerimento directorRequerimento = new DirectorRequerimento();
		BuilderRequerimento builderRequerimento = new BuilderRequerimento();
		directorRequerimento.constructRequerimento(builderRequerimento, ferias, "Godofredo");
		

		FeriasRequerimento feriasRequerimento = builderRequerimento.getResult();
		FeriasRequerimentoDAO DAOFerias = new FeriasRequerimentoDAO();
		DAOFerias.create(feriasRequerimento);
		
		// -----------------
		
		DirectorRequerimento directorRequerimento2 = new DirectorRequerimento();
		BuilderRequerimento builderRequerimento2 = new BuilderRequerimento();
		directorRequerimento2.constructRequerimento(builderRequerimento2, ferias, "Tiburcio");
		

		FeriasRequerimento feriasRequerimento2 = builderRequerimento2.getResult();
		FeriasRequerimentoDAO DAOFerias2 = new FeriasRequerimentoDAO();
		DAOFerias2.create(feriasRequerimento2);
		
		System.out.println("tamanho do ArryList testeGetAll apos criar: " + dbSingle.historico.size());
		
		assertEquals(DAOFerias.getAll(), dbSingle.historico);
		assertEquals(dbSingle.historico.get(3).getIdentificadorUsuario(), "Tiburcio");
		assertEquals(dbSingle.historico.size(), 4);
	}
	
	@Test
	public void testeDUpdate() {
		
		for(FeriasRequerimento item : dbSingle.historico) {
			System.out.println("Itens de array pre update: "+item.getIdentificadorUsuario());
		}
		
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);

		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();

		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, (short) 30);
		Ferias ferias = feriasBuilder.build();


		DirectorRequerimento directorRequerimento = new DirectorRequerimento();
		BuilderRequerimento builderRequerimento = new BuilderRequerimento();
		directorRequerimento.constructRequerimento(builderRequerimento, ferias, "Thiago");
		

		FeriasRequerimento feriasRequerimento = builderRequerimento.getResult();
		FeriasRequerimentoDAO DAOFerias = new FeriasRequerimentoDAO();
		
		boolean resultado = DAOFerias.update(3, feriasRequerimento);
//		DAOFerias.update(3, feriasRequerimento);
		assertTrue(resultado);
		
//		assertNotEquals(dbSingle.historico.get(3).getIdentificadorUsuario(), "Tiburcio");
		
//		for(FeriasRequerimento item : dbSingle.historico) {
//			System.out.println("Itens de array pos update: "+item.getIdentificadorUsuario());
//		}
	}
	
	@Test
	public void testeERemove() {
		
		System.out.println("tamanho do ArrayList antes de remover: "+ dbSingle.historico.size());
		
		FeriasRequerimentoDAO DAOFerias = new FeriasRequerimentoDAO();
		DAOFerias.delete(3); //tiburcio
		DAOFerias.delete(2); //godofredo
		DAOFerias.delete(1); //Joana
		
		assertTrue(dbSingle.historico.size() == 1);
		assertEquals(dbSingle.historico.get(0).getIdentificadorUsuario(), "Roberto");
		
		System.out.println("tamanho do ArrayList apos de remover: "+ dbSingle.historico.size());
	}
	
	
	
	@Test
	public void testeFCreateDuplicado() {
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		FeriasDirector feriasDiretor = new FeriasDirector();
		FeriasBuilder feriasBuilder = new FeriasBuilder();
		feriasDiretor.createFeriasParcial(feriasBuilder, inicio, fim, (short) 30);
		Ferias ferias = feriasBuilder.build();

		// assertEquals(ferias.tipoFerias, TiposFerias.PARCIAL);
		// Criando Requerimento
		DirectorRequerimento directorRequerimento = new DirectorRequerimento();

		BuilderRequerimento builderRequerimento = new BuilderRequerimento();
		directorRequerimento.constructRequerimento(builderRequerimento, ferias, "Roberto");
		

		FeriasRequerimento feriasRequerimento = builderRequerimento.getResult();

		FeriasRequerimentoDAO DAOFerias = new FeriasRequerimentoDAO();
		
		boolean teste = DAOFerias.create(feriasRequerimento);
		
		assertFalse(teste);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

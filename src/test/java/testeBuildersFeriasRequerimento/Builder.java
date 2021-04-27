package testeBuildersFeriasRequerimento;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.BuilderRequerimento;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.FeriasRequerimento;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.DirectorRequerimento;




public class Builder {

	@Test
	public void test() {
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 28);
		
		FeriasDirector fd = new FeriasDirector();
		FeriasBuilder fb = new FeriasBuilder();
		
		fd.createFeriasParcial(fb, inicio, fim, (short) 30);
		Ferias ferias = fb.build();
		
	
		
		assertEquals(ferias.tipoFerias, TiposFerias.PARCIAL);
		
		DirectorRequerimento dr = new DirectorRequerimento();
		BuilderRequerimento br = new BuilderRequerimento(ferias, "Kenji");
		
		
		FeriasRequerimento fr = br.getResult();
		
		assertEquals(fr.getFeriasRequisitada().tipoFerias, ferias.tipoFerias);
	}
	
	
	@Test
	public void test2() {
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 15);
		
		FeriasDirector fd = new FeriasDirector();
		FeriasBuilder fb = new FeriasBuilder();
		
		fd.createFeriasFracionada(fb, inicio, fim);
		Ferias ferias = fb.build();
		
	
		
		assertEquals(ferias.tipoFerias, TiposFerias.FRACIONADA);
		
		DirectorRequerimento dr = new DirectorRequerimento();
		BuilderRequerimento br = new BuilderRequerimento(ferias, "Thiago");
		
		
		FeriasRequerimento fr = br.getResult();
		
		assertEquals(fr.getFeriasRequisitada().tipoFerias, ferias.tipoFerias);
	}
	
	@Test
	public void test3() {
		LocalDate inicio = LocalDate.of(2021, 04, 01);
		LocalDate fim = LocalDate.of(2021, 04, 30);
		
		FeriasDirector fd = new FeriasDirector();
		FeriasBuilder fb = new FeriasBuilder();
		
		fd.createFeriasFracionada(fb, inicio, fim);
		Ferias ferias = fb.build();
		
	
		
		assertNotEquals(ferias.tipoFerias, TiposFerias.TOTAL);
		
		DirectorRequerimento dr = new DirectorRequerimento();
		BuilderRequerimento br = new BuilderRequerimento(ferias, "Lucas");
		
		
		FeriasRequerimento fr = br.getResult();
		
		assertEquals(fr.getFeriasRequisitada().tipoFerias, ferias.tipoFerias);
	}
	
	
	
	
}

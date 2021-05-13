package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.DBConnection;

public class TesteFeriasDAO {
	Session session = DBConnection.getSession();
	
	FeriasDAO feriasDAO = FeriasDAO.getInstance(session);
	ArrayList<Ferias> ferias = new ArrayList<Ferias>();
	
	@Ignore
	public void limparBanco() throws SQLException {
		feriasDAO.limparTabela();
	}

	@Test
	public void testLimparBanco() {
		feriasDAO.limparTabela();
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA); 
		feriasDAO.cadastrar(ferias);
		assertTrue(feriasDAO.pegarTodos().size()==1);
		
		
	}
	@Test
	public void testPegarTodos() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);
		
		List<Ferias> listaFerias = feriasDAO.pegarTodos();
		assertTrue(listaFerias.size() == 10);
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testPegarPorID() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);
		
		Ferias feriasRecebido = feriasDAO.pegarFeriasPorID(18);
		assertEquals(18, feriasRecebido.getId());
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testCadastrar() {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		boolean sucesso = feriasDAO.cadastrar(ferias);
		assertTrue(sucesso);
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testAlterar() throws SQLException {
		Ferias ferias = feriasDAO.pegarFeriasPorID(17);
		ferias.setDataFim(LocalDate.now());
		boolean sucesso = feriasDAO.alterar(ferias);

		assertTrue(sucesso);
	}

	@Test
	public void testDelete() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);
		feriasDAO.deletar(ferias);
		assertTrue(feriasDAO.pegarTodos().size()==9);
	}

	@Test
	public void testPegarFeriasPorIDColaborador() throws SQLException {
		
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(5);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasDAO.cadastrar(ferias);
		List<Ferias> listaFerias = feriasDAO.pegarTodasAsFeriasPorIDColaborador(0);
		
		assertTrue(listaFerias.size() == 4);
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testPegarTodasAsFeriasPorTipo() throws SQLException {

		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(5);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasDAO.cadastrar(ferias);
		List<Ferias> listaFerias = feriasDAO.pegarTodasAsFeriasPorTipo(TiposFerias.VENDIDA);
		
		assertTrue(listaFerias.size() == 4);
		feriasDAO.deletar(ferias);
		
	}
}

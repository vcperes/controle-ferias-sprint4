package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.DAO.FeriasDAO;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.DBConnection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteFeriasDAO {
	Session session = DBConnection.getSession();

	FeriasDAO feriasDAO = FeriasDAO.getInstance(session);

	@Ignore
	public void limparBanco() throws SQLException {
		feriasDAO.limparTabela();
	}

	@Test
	public void testALimparBanco() {
		feriasDAO.limparTabela();
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);
		assertTrue(feriasDAO.pegarTodos().size() == 1);
		feriasDAO.deletar(ferias);

	}

	@Test
	public void testBPegarTodos() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);

		List<Ferias> listaFerias = feriasDAO.pegarTodos();
		assertTrue(listaFerias.size() == 1);
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testCPegarPorID() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);

		Ferias feriasRecebido = feriasDAO.pegarFeriasPorID(3);
		assertEquals(3, feriasRecebido.getId());
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testDCadastrar() {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		boolean sucesso = feriasDAO.cadastrar(ferias);
		assertTrue(sucesso);
	}

	@Test
	public void testEAlterar() throws SQLException {
		Ferias ferias = feriasDAO.pegarFeriasPorID(4);
		ferias.setDataFim(LocalDate.now());
		boolean sucesso = feriasDAO.alterar(ferias);

		assertTrue(sucesso);
	}

	@Test
	public void testFDelete() throws SQLException {
		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(0);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 5);
		ferias.setTipo(TiposFerias.VENDIDA);
		feriasDAO.cadastrar(ferias);
		feriasDAO.deletar(ferias);
		assertTrue(feriasDAO.pegarTodos().size() == 1);
	}

	@Test
	public void testGPegarFeriasPorIDColaborador() throws SQLException {

		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(5);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasDAO.cadastrar(ferias);
		List<Ferias> listaFerias = feriasDAO.pegarTodasAsFeriasPorIDColaborador(0);

		assertTrue(listaFerias.size() == 1);
		feriasDAO.deletar(ferias);
	}

	@Test
	public void testHPegarTodasAsFeriasPorTipo() throws SQLException {

		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(5);
		ferias.setDataInicio(LocalDate.of(2021, 04, 01));
		ferias.setDataFim(LocalDate.of(2021, 04, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.PARCIAL);
		feriasDAO.cadastrar(ferias);
		List<Ferias> listaFerias = feriasDAO.pegarTodasAsFeriasPorTipo(TiposFerias.VENDIDA);

		assertTrue(listaFerias.size() == 1);
		feriasDAO.deletar(ferias);

	}

	@Test
	public void testIPegarTodasAsFeriasPorDataInicio() throws SQLException {

		IFerias ferias = new Ferias();
		ferias.setIdentificadorUsuario(5);
		ferias.setDataInicio(LocalDate.of(2021, 7, 10));
		ferias.setDataFim(LocalDate.of(2021, 8, 10));
		ferias.setDiasVendidos((short) 0);
		ferias.setTipo(TiposFerias.TOTAL);
		feriasDAO.cadastrar(ferias);
		List<Ferias> listaFerias = feriasDAO.pegarTodasAsFeriasPorDataInicio(LocalDate.of(2021, 7, 10));
		assertTrue(listaFerias.size() == 1);
	}
}

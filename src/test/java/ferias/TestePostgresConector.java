package ferias;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class TestePostgresConector {

	@Test
	public void testDbVersion() {
		String version = PostgresConector.dbVersion();
		assertEquals("PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit", version);
	}

	@Test
	public void testInserir() throws SQLException {
		PostgresConector.conectar();
		
		String stringParaTeste = "Testando Código";
		String limparTabela = "DELETE FROM colaborador WHERE descricao = '" + stringParaTeste + "'";
		PostgresConector.executarUpdateQuery(limparTabela);

		String inserir = "INSERT INTO colaborador (descricao) VALUES('" + stringParaTeste + "')";
		PostgresConector.executarUpdateQuery(inserir);

		String consultar = "SELECT descricao FROM colaborador WHERE descricao = '" + stringParaTeste + "'";
		String resultado = PostgresConector.pegarString(PostgresConector.executarQuery(consultar));

		assertEquals("Testando Código", resultado);
		PostgresConector.executarUpdateQuery(limparTabela);
		
		PostgresConector.desconectar();
		
	}

	@Test
	public void testAlterar() throws SQLException {
		PostgresConector.conectar();
		
		String stringAntes = "Testando Código: antes";
		String stringDepois = "Testando Código: depois";
		String limparTabela = "DELETE FROM colaborador WHERE descricao = '" + stringAntes + "'";
		PostgresConector.executarUpdateQuery(limparTabela);
		
		String inserir = "INSERT INTO colaborador (descricao) VALUES('" + stringAntes + "')";
		PostgresConector.executarUpdateQuery(inserir);
		
		String consultarId = "SELECT id FROM colaborador WHERE descricao = '" + stringAntes + "'";
		String id = PostgresConector.pegarString(PostgresConector.executarQuery(consultarId));
		
		String consultarDescricaoAntes = "SELECT descricao FROM colaborador WHERE id = " + id;
		String descricaoAntes = PostgresConector.pegarString(PostgresConector.executarQuery(consultarDescricaoAntes));
		assertEquals("Testando Código: antes", descricaoAntes);
		
		String alterar = "UPDATE colaborador SET descricao = '" + stringDepois + "' WHERE id = " + id;
		PostgresConector.executarUpdateQuery(alterar);
		
		String consultarDescricaoDepois = "SELECT descricao FROM colaborador WHERE id = " + id;
		String descricaoDepois = PostgresConector.pegarString(PostgresConector.executarQuery(consultarDescricaoDepois));
		assertEquals("Testando Código: depois", descricaoDepois);
	}
	
	@Test 
	public void testDeletar() throws SQLException {
		PostgresConector.conectar();
		
		String stringParaTeste = "Testando Código";
		String limparTabela = "DELETE FROM colaborador WHERE descricao = '" + stringParaTeste + "'";
		PostgresConector.executarUpdateQuery(limparTabela);

		String inserir = "INSERT INTO colaborador (descricao) VALUES('" + stringParaTeste + "')";
		PostgresConector.executarUpdateQuery(inserir);
		
		String consultarAntes = "SELECT descricao FROM colaborador WHERE descricao = '" + stringParaTeste + "'";
		String resultado = PostgresConector.pegarString(PostgresConector.executarQuery(consultarAntes));
		assertFalse(resultado.isEmpty());
		
		String deletar = "DELETE FROM colaborador WHERE descricao = '" + stringParaTeste + "'";
		PostgresConector.executarUpdateQuery(deletar);
		
		String consultarDepois = "SELECT descricao FROM colaborador WHERE descricao = '" + stringParaTeste + "'";
		ResultSet resultadoDepois = PostgresConector.executarQuery(consultarDepois);
		assertFalse(resultadoDepois.next());
	}
	

}

package ferias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class TestePostgresConector {
	
	@Before
	public void limparTabela() {
		PostgresConector.limparTabela("requerimento");
	}

	@Test
	public void testDbVersion() {
		String version = PostgresConector.dbVersion();
		assertEquals("PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit", version);
	}

	@Test
	public void testExecutarQueryUpdate() throws SQLException {
		PostgresConector.executarUpdateQuery(
				"insert into requerimento (idestadorequisicao, idferias, datasolicitacao) values(1, 2, '"
						+ LocalDate.now() + "');");
		ResultSet rs = PostgresConector.executarQuery("select * from requerimento");
		if(rs.next()) {
			assertEquals(rs.getInt(2), 1);
			assertEquals(rs.getInt(3), 2);
		} else {
			fail("Erro em testExecutarQueryUpdate");
		}

	}
}

package br.com.senior.proway.ferias.postgresql;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresConector {
	static String url = "jdbc:postgresql://localhost:5432/postgres";
	static String user = "postgres";
	static String password = "admin";
	static Connection conexao;

	public static void conectar() throws SQLException {
		conexao = DriverManager.getConnection(url, user, password);
	}

	public static void desconectar() throws SQLException {
		conexao.close();
	}

	public static ResultSet executarQuery(String query) throws SQLException {
		Statement statement = conexao.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
	}

	public static void executarUpdateQuery(String query) throws SQLException {
		Statement statement = conexao.createStatement();
		statement.executeUpdate(query);
	}

	public static String pegarString(ResultSet resultSet) throws SQLException {
		String resultado = null;
		if (resultSet.next()) {
			resultado = resultSet.getString(1);
		}
		return resultado;
	}

	public static String dbVersion() {
		try {
			if (conexao == null) {
				conectar();
			}
			String query = "SELECT VERSION()";
			ResultSet resultSet = executarQuery(query);
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

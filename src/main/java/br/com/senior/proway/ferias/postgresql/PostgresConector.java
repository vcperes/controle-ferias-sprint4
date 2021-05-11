package br.com.senior.proway.ferias.postgresql;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresConector {
	static String url = "jdbc:postgresql://localhost:5432/ferias";
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
		conectar();
		Statement statement = conexao.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		desconectar();
		return resultSet;
	}

	public static int executarUpdateQuery(String query) throws SQLException {
		conectar();
		Statement statement = conexao.createStatement();
		int i = statement.executeUpdate(query);
		desconectar();
		return i;
	}
	
	public static void limparTabela(String nomeDaTabela) {
		try {
			conectar();
			Statement statement = conexao.createStatement();
			statement.executeUpdate("Truncate " + nomeDaTabela + " restart identity;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

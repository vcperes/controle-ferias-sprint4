package br.com.senior.proway.ferias.model.DAO;

import static org.junit.Assert.fail;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class FeriasDAO implements Icrud<IFerias>, IConsultaDeFeriasPorTipoDAO, IConsultaPorColaboradorDAO {

	/**
	 * 
	 * Metodo que realiza a busca de todas as ferias existentes no banco de dados.
	 * 
	 * Posteriormente armazena as ferias em uma lista (listaFerias) retornando os
	 * dados obtidos.
	 * 
	 * @author Jonata Cardoso Caetano.
	 * @return listaFerias.
	 * 
	 */
	public ArrayList<IFerias> pegarTodos() {
		ArrayList<IFerias> listaFerias = new ArrayList<IFerias>();
		try {

			PostgresConector.conectar();
			String query = "SELECT *FROM ferias";
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				Ferias ferias = new Ferias();

				ferias.setId(resultSet.getInt("id"));

				ferias.setIdentificadorUsuario(resultSet.getString("idusuario"));

				LocalDate localDateInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias.setDataInicio(localDateInicio);

				LocalDate localDateFim = resultSet.getDate("dataFim").toLocalDate();
				ferias.setDataInicio(localDateFim);

				ferias.setTipo(TiposFerias.pegarPorValor(resultSet.getInt("idtipoferias")));

				listaFerias.add(ferias);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFerias;
	}

	/**
	 * Retorna um objeto do tipo IFerias de acordo com o id do objeto.
	 * 
	 * @author Janaina, Vitor, Bruna, Jonata, Daniella.
	 * @param id int Id do objeto a ser consultado.
	 * @return IFerias Um objeto do tipo IFerias.
	 * 
	 */
	public IFerias pegarFeriasPorID(int id) {
		IFerias ferias = new Ferias();

		try {
			PostgresConector.conectar();
			String query = "SELECT * FROM ferias WHERE id = " + id + ";";
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				ferias.setId(resultSet.getInt("id"));

				ferias.setIdentificadorUsuario(resultSet.getString("idusuario"));

				LocalDate localDateInicio = resultSet.getDate("datainicio").toLocalDate();
				ferias.setDataInicio(localDateInicio);

				LocalDate localDateFim = resultSet.getDate("datafim").toLocalDate();
				ferias.setDataFim(localDateFim);

				ferias.setDiasVendidos(resultSet.getShort("diasvendidos"));

				int tipoFerias = resultSet.getInt("idtipoferias");
				ferias.setTipo(TiposFerias.pegarPorValor(tipoFerias));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return ferias;
	}

	/**
	 * Cadastra um objeto do tipo IFerias.
	 * 
	 * @author Janaina
	 * @param ferias IFerias Objeto a ser cadastrado.
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 * 
	 */
	public boolean cadastrar(IFerias ferias) {
		boolean cadastrou = false;
		try {
			PostgresConector.conectar();

			String query = "INSERT INTO ferias (idusuario, datainicio, datafim, diasvendidos, idtipoferias) VALUES("
					+ ferias.getIdentificadorUsuario() + ", '" + Date.valueOf(ferias.getDataInicio()) + "', '"
					+ Date.valueOf(ferias.getDataFim()) + "', " + ferias.getDiasVendidos() + ", "
					+ ferias.getTipo().getValor() + ")";
			PostgresConector.executarUpdateQuery(query);
			cadastrou = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadastrou;
	}

	/**
	 * Atualiza um objeto do tipo IFerias atraves do id. Localiza o objeto através
	 * do id e altera seus dados.
	 * 
	 * @author Janaina
	 * @param id int Identificador do objeto a ser consultado.
	 * @return boolean Retorna se o método foi executado com sucesso.
	 * 
	 */
	public boolean alterar(int id, IFerias novaFerias) {
		boolean sucesso = false;
		try {
			String query = "UPDATE ferias SET idusuario= " + novaFerias.getIdentificadorUsuario()+ ", datainicio = '" + Date.valueOf(novaFerias.getDataInicio())
					+ "', datafim = '" + Date.valueOf(novaFerias.getDataFim()) + "', diasvendidos = "
					+ novaFerias.getDiasVendidos() + ", idtipoferias = " + novaFerias.getTipo().getValor()
					+ " WHERE id = " + id + ";";
			PostgresConector.executarUpdateQuery(query);
			sucesso = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	/**
	 * Deleta objeto do tipo IFerias atraves do id. Localiza objeto que possui
	 * identificador igual ao id passado no parâmetro, deletando-o.
	 * 
	 * @author Janaina
	 * @param int Id Id do objeto a ser deletado.
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 * 
	 */
	public boolean deletar(int id) {
		boolean sucesso = false;
		try {
			String query = "DELETE FROM ferias WHERE id = " + id;
			PostgresConector.conectar();
			PostgresConector.executarUpdateQuery(query);
			sucesso = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucesso;
	}

	/***
	 * Retorna uma lista de objetos do tipo IFerias com id_colaborador igual ao id
	 * passado no parametro.
	 * 
	 * @author Janaina
	 * @param idColaborador int Identificador do usuário.
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 * 
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasPorIDColaborador(int idColaborador) {
		ArrayList<IFerias> listaFerias = new ArrayList<IFerias>();

		try {
			String query = "SELECT * FROM ferias WHERE idusuario = " + idColaborador;
			PostgresConector.conectar();
			ResultSet resultSet = PostgresConector.executarQuery(query);
			while (resultSet.next()) {
				Ferias ferias = new Ferias();

				ferias.setId(resultSet.getInt("id"));
				ferias.setIdentificadorUsuario(resultSet.getString("idusuario"));

				LocalDate localDataInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias.setDataInicio(localDataInicio);

				LocalDate localDataFim = resultSet.getDate("dataFim").toLocalDate();
				ferias.setDataFim(localDataFim);

				ferias.setDiasVendidos((short) resultSet.getInt("diasvendidos"));

				ferias.setTipo(TiposFerias.pegarPorValor(resultSet.getInt("idtipoferias")));

				listaFerias.add(ferias);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFerias;
	}

	public void limparTabela() {
		PostgresConector.limparTabela("ferias");

	}

	public ArrayList<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo) {
		ArrayList<IFerias> listaTodasAsFeriasParciais = new ArrayList<IFerias>();

		try {
			PostgresConector.conectar();
			String query = "SELECT * FROM ferias WHERE idtipoferias = " + tipo.getValor() + ";";
			
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				Ferias ferias2 = new Ferias();

				ferias2.setId(resultSet.getInt("id"));
				ferias2.setIdentificadorUsuario(resultSet.getString("idusuario"));

				LocalDate localDataInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias2.setDataInicio(localDataInicio);

				LocalDate localDataFim = resultSet.getDate("dataFim").toLocalDate();
				ferias2.setDataFim(localDataFim);

				ferias2.setDiasVendidos((short) resultSet.getInt("diasvendidos"));

				ferias2.setTipo(TiposFerias.pegarPorValor(resultSet.getInt("idtipoferias")));

				listaTodasAsFeriasParciais.add(ferias2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTodasAsFeriasParciais;

	}
}

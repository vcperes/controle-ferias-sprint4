package br.com.senior.proway.ferias.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasBuilder;
import br.com.senior.proway.ferias.model.FeriasDirector;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class FeriasDAO implements Icrud<IFerias>, IConsultaDeFeriasPorTipoDAO, IConsultaPorColaboradorDAO {

	/***
	 * Retorna uma lista com objetos do tipo IFerias.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
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

				ferias.setIdentificadorUsuario(resultSet.getString("id_colaborador"));

				LocalDate localDateInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias.setDataInicio(localDateInicio);

				LocalDate localDateFim = resultSet.getDate("dataFim").toLocalDate();
				ferias.setDataInicio(localDateFim);

				int tipoFerias = resultSet.getInt("id_tipoferias");
				ferias.setTipo(TiposFerias.pegarPorValor(tipoFerias));

				listaFerias.add(ferias);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFerias;
	}

	/**
	 * Metodo que busca e retorna um objeto do tipo IFerias, atraves do id.
	 * 
	 * @param int id Id do objeto a ser consultado.
	 * @return IFerias - Um objeto do tipo IFerias.
	 * @author Janaina, Vitor, Bruna, Jonata, Daniella.
	 */

	public IFerias pegarPorID(int id) {
		IFerias ferias = new Ferias();

		try {
			PostgresConector.conectar();
			String query = "SELECT * FROM ferias WHERE id = " + id;
			ResultSet resultSet = PostgresConector.executarQuery(query);

			while (resultSet.next()) {
				ferias.setId(resultSet.getInt("id"));

				ferias.setIdentificadorUsuario(resultSet.getString("id_colaborador"));

				LocalDate localDateInicio = resultSet.getDate("dataInicio").toLocalDate();
				ferias.setDataInicio(localDateInicio);

				LocalDate localDateFim = resultSet.getDate("dataFim").toLocalDate();
				ferias.setDataFim(localDateFim);

				ferias.setDiasVendidos(resultSet.getShort("diasVendidos"));

				int tipoFerias = resultSet.getInt("id_tipoferias");
				ferias.setTipo(TiposFerias.pegarPorValor(tipoFerias));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ferias;
	}

	/**
	 * Metodo que cadastra um objeto do tipo IFerias.
	 * 
	 * @param IFerias ferias
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 * @author Janaina
	 */
	public boolean cadastrar(IFerias _ferias, int id_colaborador) {
		IFerias ferias = _ferias;

		try {
			String query = "INSERT INTO ferias (id_colaborador, datainicio, datafim, diasvendidos, id_tipoferias) VALUES(" + id_colaborador + ", " + ferias.getDataInicio() +", " + ferias.getDataFim() + ", " + ferias.getDiasVendidos() + ", " + ferias.getTipo().getValor() + ")";
			PostgresConector.conectar();
			PostgresConector.executarQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * Atualiza um objeto do tipo IFerias atraves do id. Busca dentro da lista
	 * ferias, um objeto do tipo IFerias atraves de um Id. Localizando faz a
	 * subscri��o.
	 * 
	 * @return boolean Retorna se o método foi executado com sucesso.
	 */
	public boolean alterar(int id, IFerias novaFerias) {
		boolean sucesso = false;
		ArrayList<IFerias> todasAsFerias = DataBase.getInstance().getFerias();
		for (IFerias umaFerias : todasAsFerias) {
			if (umaFerias.getId() == id) {
				umaFerias = novaFerias;
				sucesso = true;
				break;
			}
		}
		return sucesso;

	}

	/**
	 * Deleta objeto do tipo IFerias atraves do id. Busca dentro da lista ferias, um
	 * objeto do tipo IFerias atraves de um Id. Localizando faz a remocao.
	 * 
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 */
	public boolean deletar(int id) {
		boolean sucesso = false;
		ArrayList<IFerias> todasAsFerias = DataBase.getInstance().getFerias();
		for (IFerias ferias : todasAsFerias) {
			if (ferias.getId() == id) {
				todasAsFerias.remove(ferias);
				sucesso = true;
				break;
			}
		}
		return sucesso;
	}

	/***
	 * Retorna uma lista de Ferias Totais. Retorna uma lista com objetos do tipo
	 * IFerias onde o TipoDeFerias eh igual a TOTAL.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasTotais() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasTotais = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.TOTAL)) {
				feriasTotais.add(umaFerias);
			}
		}
		return feriasTotais;
	}

	/***
	 * Retorna uma lista de Ferias Invalidas. Retorna uma lista com objetos do tipo
	 * IFerias onde o TipoDeFerias eh igual a INVALIDA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasInvalidas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasInvalidas = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.INVALIDA)) {
				feriasInvalidas.add(umaFerias);
			}
		}
		return feriasInvalidas;
	}

	/***
	 * Retorna uma lista de Ferias Parciais. Retorna uma lista com objetos do tipo
	 * IFerias onde o TipoDeFerias eh igual a PARCIAL.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasParciais() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasParciais = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.PARCIAL)) {
				feriasParciais.add(umaFerias);
			}
		}
		return feriasParciais;
	}

	/***
	 * Retorna uma lista de Ferias Fracionadas. Retorna uma lista com objetos do
	 * tipo IFerias onde o TipoDeFerias eh igual a FRACIONADA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasFracionadas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasFracionadas = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.FRACIONADA)) {
				feriasFracionadas.add(umaFerias);
			}
		}
		return feriasFracionadas;
	}

	/***
	 * Retorna uma lista de Ferias Vendidas. Retorna uma lista com objetos do tipo
	 * IFerias onde o TipoDeFerias eh igual a VENDIDA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasVendidas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasVendidas = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getTipo().equals(TiposFerias.VENDIDA)) {
				feriasVendidas.add(umaFerias);
			}
		}
		return feriasVendidas;
	}

	/***
	 * Retorna uma lista de Ferias correspondente ao idUsuario. Retorna uma lista
	 * com objetos do tipo IFerias onde o idUsuario eh igual ao ao parametro
	 * idUsuario.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarFeriasPorIDColaborador(String idUsuario) {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasDoUsuario = new ArrayList<IFerias>();
		for (IFerias umaFerias : ferias) {
			if (umaFerias.getIdentificadorUsuario().equals(idUsuario)) {
				feriasDoUsuario.add(umaFerias);
			}
		}
		return feriasDoUsuario;
	}

}

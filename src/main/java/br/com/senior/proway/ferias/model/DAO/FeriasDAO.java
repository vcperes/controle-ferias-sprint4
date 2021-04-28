package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.model.interfaces.IFeriasBuilder;
import br.com.senior.proway.ferias.model.interfaces.IIdentificadorIdDAO;

public class FeriasDAO implements Icrud<IFerias>, IConsultaDeFeriasPorTipoDAO, IConsultaPorColaboradorDAO {

	/***
	 * Retorna uma lista com objetos do tipo IFerias.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> getAll() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		return ferias;
	}

	/**
	 * Metodo que busca e retorna um objeto do tipo IFerias, atraves do id.
	 * 
	 * @return IFerias - Um objeto do tipo IFerias.
	 */

	public IFerias get(int id) {
		IFerias ferias = null;
		ArrayList<IFerias> todasAsFerias = DataBase.getInstance().getFerias();
		for (IFerias umaFerias : todasAsFerias) {
			if (umaFerias.getId() == id) {
				ferias = umaFerias;
			}
		}
		return ferias;
	}

	/**
	 * Metodo que cadastra um objeto do tipo IFerias na lista database/DataBase/ferias.
	 * 
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 */
	public boolean create(IFerias objeto) { // Aqui o create na realidade eh cadastrar.
		DataBase.getInstance().getFerias().add(objeto);
		return true;
		//precisa implementar metodo que insere o id nesse objeto quando ele eh 
		//criado/cadastrado no banco de dados, pois quando criamos um objeto, apenas 
		//informamos o idUsuario
	}

	/**
	 * Atualiza um objeto do tipo IFerias atraves do id.
	 * Busca dentro da lista ferias, um objeto do tipo IFerias atraves de um Id. Localizando faz a subscri��o.
	 * 
	 * @return boolean Retorna se o método foi executado com sucesso.
	 */
	public boolean update(int id, IFerias novaFerias) {
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
	 * Deleta objeto do tipo IFerias atraves do id.
	 * Busca dentro da lista ferias, um objeto do tipo IFerias atraves de um Id. Localizando 
	 * faz a remocao.
	 * 
	 * @return boolean Retorna se o metodo foi executado com sucesso.
	 */
	public boolean delete(int id) {
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

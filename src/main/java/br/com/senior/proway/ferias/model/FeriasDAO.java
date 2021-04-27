package br.com.senior.proway.ferias.model;

import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.interfaces.IConsultaDeFeriasPorTipoDAO;
import br.com.senior.proway.ferias.model.interfaces.IConsultaPorColaboradorDAO;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

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

	public IFerias get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(IFerias objeto) {
		// TODO Auto-generated method stub
		
	}

	public void update(int id, IFerias objeto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	/***
	 * Retorna uma lista de Férias Totais.
	 * Retorna uma lista com objetos do tipo IFerias onde o TipoDeFerias eh igual a TOTAL.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasTotais() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasTotais = new ArrayList<IFerias>();
		for(IFerias umaFerias : ferias) {
			if(umaFerias.getTipo().equals(TiposFerias.TOTAL)) {
				feriasTotais.add(umaFerias);
			}
		}
		return feriasTotais;
	}
	
	/***
	 * Retorna uma lista de Ferias Invalidas.
	 * Retorna uma lista com objetos do tipo IFerias onde o TipoDeFerias eh igual a INVALIDA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasInvalidas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasInvalidas = new ArrayList<IFerias>();
		for(IFerias umaFerias : ferias) {
			if(umaFerias.getTipo().equals(TiposFerias.INVALIDA)) {
				feriasInvalidas.add(umaFerias);
			}
		}
		return feriasInvalidas;
	}
	
	/***
	 * Retorna uma lista de Ferias Parciais.
	 * Retorna uma lista com objetos do tipo IFerias onde o TipoDeFerias eh igual a PARCIAL.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasParciais() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasParciais = new ArrayList<IFerias>();
		for(IFerias umaFerias : ferias) {
			if(umaFerias.getTipo().equals(TiposFerias.PARCIAL)) {
				feriasParciais.add(umaFerias);
			}
		}
		return feriasParciais;
	}
	
	/***
	 * Retorna uma lista de Ferias Fracionadas.
	 * Retorna uma lista com objetos do tipo IFerias onde o TipoDeFerias eh igual a FRACIONADA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasFracionadas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasFracionadas = new ArrayList<IFerias>();
		for(IFerias umaFerias : ferias) {
			if(umaFerias.getTipo().equals(TiposFerias.FRACIONADA)) {
				feriasFracionadas.add(umaFerias);
			}
		}
		return feriasFracionadas;
	}
	
	/***
	 * Retorna uma lista de Ferias Vendidas.
	 * Retorna uma lista com objetos do tipo IFerias onde o TipoDeFerias eh igual a VENDIDA.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarTodasAsFeriasVendidas() {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasVendidas = new ArrayList<IFerias>();
		for(IFerias umaFerias : ferias) {
			if(umaFerias.getTipo().equals(TiposFerias.VENDIDA)) {
				feriasVendidas.add(umaFerias);
			}
		}
		return feriasVendidas;
	}


	/***
	 * Retorna uma lista de Ferias correspondente ao idUsuario.
	 * Retorna uma lista com objetos do tipo IFerias onde o idUsuario eh igual ao 
	 * ao parametro idUsuario.
	 * 
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 */
	public ArrayList<IFerias> pegarFeriasPorIDColaborador(String idUsuario) {
		ArrayList<IFerias> ferias = DataBase.getInstance().getFerias();
		ArrayList<IFerias> feriasDoUsuario = new ArrayList<IFerias>();
		for(IFerias umaFerias : ferias) {
			if(umaFerias.getIdentificadorUsuario().equals(idUsuario)) {
				feriasDoUsuario.add(umaFerias);
			}
		}
		return feriasDoUsuario;
	}


}

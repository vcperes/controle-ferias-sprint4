package br.com.senior.proway.ferias.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class FeriasDAO implements Icrud<IFerias>, IConsultaDeFeriasPorTipoDAO, IConsultaPorColaboradorDAO {

	private static FeriasDAO feriasDAO;
	private Session session;
	
	public static FeriasDAO getInstance(Session session) {
		if(feriasDAO == null) {
			feriasDAO = new FeriasDAO(session); 
		}
		return feriasDAO; 
	}
	
	private FeriasDAO(Session session) {
		this.session = session;
	}
	
	/**
	 * TODO
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
		
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		Ferias feriasRecebida = (Ferias) session.na(nomeTabela, id);
		session.getTransaction().commit();
		return listaFerias;
	}

	/**
	 * TODO
	 * Retorna um objeto do tipo IFerias de acordo com o id do objeto.
	 * 
	 * @author Janaina, Vitor, Bruna, Jonata, Daniella.
	 * @param id int Id do objeto a ser consultado.
	 * @return IFerias Um objeto do tipo IFerias.
	 * 
	 */
	public Ferias pegarFeriasPorID(String nomeTabela, int id) {
		
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		Ferias feriasRecebida = (Ferias) session.get(nomeTabela, id);
		session.getTransaction().commit();
		
		return feriasRecebida;
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
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		session.save(ferias);
		session.getTransaction().commit();
		return true;
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
	public boolean alterar(IFerias novaFerias) {
		
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(novaFerias);
		session.getTransaction().commit();
		return true;
		
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
	public boolean deletar(IFerias ferias) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		session.delete(ferias);
		session.getTransaction().commit();
		return true;
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
				ferias.setIdentificadorUsuario(resultSet.getInt("idusuario"));

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
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		String hql = String.format("delete from ferias");
	    NativeQuery nq = session.createNativeQuery(hql);
	    nq.executeUpdate();
	    session.getTransaction().commit();
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
				ferias2.setIdentificadorUsuario(resultSet.getInt("idusuario"));

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

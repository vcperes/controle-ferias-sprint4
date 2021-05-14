package br.com.senior.proway.ferias.model.DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public class FeriasDAO implements Icrud<IFerias>, IConsultaDeFeriasPorTipoDAO, IConsultaPorColaboradorDAO {

	private static FeriasDAO feriasDAO;
	private Session session;

	public static FeriasDAO getInstance(Session session) {
		if (feriasDAO == null) {
			feriasDAO = new FeriasDAO(session);
		}
		return feriasDAO;
	}

	private FeriasDAO(Session session) {
		this.session = session;
	}

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
	public List<IFerias> pegarTodos() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);
		Root<Ferias> root = criteria.from(Ferias.class);
		CriteriaQuery<Ferias> rootQuery = criteria.select(root);
		Query query = session.createQuery(rootQuery);
		List<IFerias> selectedFerias = query.getResultList();
		return selectedFerias;
		// return listaFerias;
	}

	/**
	 * TODO Retorna um objeto do tipo Ferias de acordo com o id do objeto.
	 * 
	 * @author Janaina, Vitor, Bruna, Jonata, Daniella.
	 * @param id int Id do objeto a ser consultado.
	 * @return IFerias Um objeto do tipo IFerias.
	 * 
	 */
	public Ferias pegarFeriasPorID(Integer idEntrada) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session.get(Ferias.class, idEntrada);

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
		try {
			session.save(ferias);
			session.getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.getMessage();
			return false;
		}
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

		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			session.update(novaFerias);
			session.getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.getMessage();
			return false;
		}
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
		try {
			session.delete(ferias);
			session.getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	/***
	 * TODO Retorna uma lista de objetos do tipo IFerias com id_colaborador igual ao
	 * id passado no parametro.
	 * 
	 * @author Janaina
	 * @param idColaborador int Identificador do usuário.
	 * @return ArrayList<IFerias> Lista de objetos do tipo IFerias.
	 * 
	 */
	public List<IFerias> pegarTodasAsFeriasPorIDColaborador(int idUsuarioEntrada) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);

		Root<Ferias> root = criteria.from(Ferias.class);

		criteria.select(root).where(builder.equal(root.get("identificadorUsuario"), idUsuarioEntrada));
		Query query = session.createQuery(criteria);
		List<IFerias> todasFerias = query.getResultList();
		return todasFerias;
	}

	public List<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipoEntrada) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);

		Root<Ferias> root = criteria.from(Ferias.class);

		criteria.select(root).where(builder.equal(root.get("tipoFerias"), tipoEntrada.getValor()));
		Query query = session.createQuery(criteria);
		List<IFerias> todasFerias = query.getResultList();
		return todasFerias;

	}

	public List<IFerias> pegarTodasAsFeriasPorDataInicio(LocalDate dataRecebida) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ferias> criteria = builder.createQuery(Ferias.class);

		Root<Ferias> root = criteria.from(Ferias.class);

		criteria.select(root).where(builder.equal(root.get("dataInicio"), dataRecebida));
		Query query = session.createQuery(criteria);
		List<IFerias> todasFerias = query.getResultList();
		return todasFerias;

	}

	public boolean limparTabela() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaDelete<Ferias> criteriaDelete = builder.createCriteriaDelete(Ferias.class);
			criteriaDelete.from(Ferias.class);
			Query query = session.createQuery(criteriaDelete);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
}

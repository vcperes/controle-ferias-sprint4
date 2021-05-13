package br.com.senior.proway.ferias.model.DAO;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class RequerimentoDAO implements Icrud<Requerimento> {
	
	
	private Session session;
	FeriasDAO feriasDao;
	private static RequerimentoDAO requerimentoDAO;
	
	private RequerimentoDAO(Session session) {
		this.feriasDao = FeriasDAO.getInstance(session);
		this.session = session;
	}
	
	public static RequerimentoDAO getInstance(Session session) {
		if(requerimentoDAO == null) {
			requerimentoDAO = new RequerimentoDAO(session);
		}
		return requerimentoDAO;
	}

	/**
	 * Lista todos os objetos de Requerimento.
	 * 
	 * Acessa e lista todos os objetos da tabela requerimento do banco de dados,
	 * podendo os armazenar em um array.
	 * 
	 * @return ArrayList com os objetos da tabela requerimento.
	 * 
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 */
	public ArrayList<Requerimento> pegarTodos() {
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<Requerimento> cq = cb.createQuery(Requerimento.class);
	    Root<Requerimento> rootEntry = cq.from(Requerimento.class);
	    CriteriaQuery<Requerimento> all = cq.select(rootEntry);


	    TypedQuery<Requerimento> allQuery = session.createQuery(all);
	    return  (ArrayList<Requerimento>) allQuery.getResultList();
	}

	/**
	 * 
	 * M�todo que pega um requerimento por id.
	 * 
	 * Pega um id da tabela requerimento do banco de dados e retorna o objeto,
	 * RequerimentoFerias.
	 * 
	 * @param int id, que � o id puxado do banco de dados.
	 * @return objeto RequerimentoFerias.
	 * 
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 */
	public Requerimento pegarRequerimentoPorID(Integer id) {
		
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		return session.get(Requerimento.class, id);
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 *         M�todo que cadastra objeto requerimento no banco de dados
	 * 
	 *         Recebe objeto RequerimentoFerias como parametro, separa por atributos
	 *         e insere por expressao SQL no banco de dados
	 * 
	 * @param Requerimento objeto
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */
	public boolean cadastrar(Requerimento objeto) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		session.save(objeto);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 *         M�todo que altera objeto requerimento no banco de dados
	 * 
	 *         Altera objeto RequerimentoFerias como parametro, separa por atributos
	 *         e insere por expressao SQL no banco de dados
	 * 
	 * @param int id, RequerimentoFerias objeto
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */

	public boolean alterar(Requerimento objeto) {
		
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		session.update(objeto);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 *         M�todo que deleta objeto requerimento no banco de dados
	 * 
	 *         Deleta objeto RequerimentoFerias atraves do id.
	 * 
	 * @param int id objeto
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */

	public boolean deletar(Requerimento objeto) {

		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
		session.delete(objeto);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 *         M�todo que cadastra objeto requerimento no banco de dados
	 * 
	 *         Pega todos os objetos RequerimentoFerias que tenham o mesmo estado de
	 *         requerimento recebido como parametro.
	 * 
	 * @param Estadosrequerimentos objeto
	 * 
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */

	public ArrayList<Requerimento> getRequerimentoPorEstado(EstadosRequerimentos estado) {

		ArrayList<Requerimento> listaRequerimento = new ArrayList<Requerimento>();

		try {

			PostgresConector.conectar();
			String select = "SELECT * FROM requerimento WHERE idestadorequisicao = " + estado.getValor() + ";";
			ResultSet rs = PostgresConector.executarQuery(select);

			while (rs.next()) {
				int id = rs.getInt("id");
				FeriasDAO feriasDao = new FeriasDAO();
				int idFerias = rs.getInt("idFerias");
				Ferias ferias = (Ferias) feriasDao.pegarRequerimentoPorID(idFerias);
				LocalDate localDate = rs.getDate("datasolicitacao").toLocalDate();
				Requerimento requerimento = new Requerimento(id, ferias, estado, localDate);
				listaRequerimento.add(requerimento);
			}

			return listaRequerimento;

		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return listaRequerimento;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 *         M�todo que busca o requerimento por data no banco de dados
	 * 
	 *         Pega todos os objetos RequerimentoFerias que tenham a mesma data de
	 *         requerimento recebido como parametro.
	 * 
	 * @param LocalDate, dataParaPesquisa
	 * 
	 * @return ArrayList<RequerimentoFerias>
	 */

	public ArrayList<Requerimento> getRequerimentoPorData(LocalDate dataParaPesquisa) {
		ArrayList<Requerimento> listaRequerimento = new ArrayList<Requerimento>();

		try {

			PostgresConector.conectar();
			String select = "SELECT * FROM requerimento WHERE datasolicitacao = '" + dataParaPesquisa + "';";
			ResultSet rs = PostgresConector.executarQuery(select);

			while (rs.next()) {
				FeriasDAO feriasDao = new FeriasDAO();
				int idFerias = rs.getInt("idFerias");
				Ferias ferias = (Ferias) feriasDao.pegarRequerimentoPorID(idFerias);
				EstadosRequerimentos estadorequerimento = EstadosRequerimentos
						.pegarPorValor(rs.getInt("idestadorequisicao"));
				LocalDate localDate = rs.getDate("datasolicitacao").toLocalDate();
				Requerimento requerimento = new Requerimento(rs.getInt("id"), ferias, estadorequerimento,
						localDate);
				listaRequerimento.add(requerimento);
			}

			return listaRequerimento;

		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());

		}
		return listaRequerimento;
	}

	public void limparTabela() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();             
		}
       	String hql = String.format("drop table requerimento");
	    session.createSQLQuery(hql).executeUpdate();
	    session.getTransaction().commit();
	}
}

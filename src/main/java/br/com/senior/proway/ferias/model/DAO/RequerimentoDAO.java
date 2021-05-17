package br.com.senior.proway.ferias.model.DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.Session;

import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.RequerimentoSalario;
import br.com.senior.proway.ferias.model.DAO.interfaces.IRequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.interfaces.IRequerimento;
import br.com.senior.proway.ferias.postgresql.DBConnection;

public class RequerimentoDAO implements IRequerimentoDAO {

    private static RequerimentoDAO requerimentoDAO;

    public static RequerimentoDAO getInstance() {
        if(requerimentoDAO == null) {
            requerimentoDAO = new RequerimentoDAO();
        }
        return requerimentoDAO;
    }

    private RequerimentoDAO() {

    }

    private Session session = DBConnection.getSession();

    public boolean criarRequerimento(IRequerimento requerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(requerimento);
        session.getTransaction().commit();
        return true;
    }

    public boolean deletarRequerimento(IRequerimento requerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.delete(requerimento);
        session.getTransaction().commit();
        return true;
    }

    public boolean atualizarRequerimento(IRequerimento requerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.update(requerimento);
        session.getTransaction().commit();
        return true;
    }

    public List<IRequerimento> buscarRequerimentos(IRequerimento requerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IRequerimento> cq = cb.createQuery(IRequerimento.class);
        return session.createQuery(cq.select(cq.from(IRequerimento.class))).getResultList();
    }
    
    public List<IRequerimento> buscarRequerimentos(Class<?> tipoRequerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IRequerimento> cq = (CriteriaQuery<IRequerimento>) cb.createQuery(tipoRequerimento);
        return session.createQuery(cq.select((Selection<? extends IRequerimento>) cq.from(tipoRequerimento))).getResultList();
    }
    
    public List<IRequerimento> buscarRequerimentos(EstadoRequerimento estadoRequerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IRequerimento> cq = cb.createQuery(IRequerimento.class);
        Root<IRequerimento> root = cq.from(IRequerimento.class);
        return session.createQuery(cq.select(cq.from(IRequerimento.class)).where(cb.equal(root.get("estadoRequisicao"), estadoRequerimento.getValor()))).getResultList();
    }
    
    public List<IRequerimento> buscarRequerimentos(LocalDate dataDeCriacao) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IRequerimento> cq = cb.createQuery(IRequerimento.class);
        Root<IRequerimento> root = cq.from(IRequerimento.class);
        return session.createQuery(cq.select(cq.from(IRequerimento.class)).where(cb.equal(root.get("dataCriacaoRequerimento"), dataDeCriacao))).getResultList();
    }
    
    public IRequerimento buscarRequerimento(Class<?> tipoRequerimento, int idRequerimento) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        return (IRequerimento) session.get(tipoRequerimento, idRequerimento);
    }
    
    public boolean limparTabela() {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<RequerimentoFerias> criteriaDelete = builder.createCriteriaDelete(RequerimentoFerias.class);
		criteriaDelete.from(RequerimentoFerias.class);
		session.createQuery(criteriaDelete).executeUpdate();
		CriteriaDelete<RequerimentoSalario> criteriaDelete2 = builder.createCriteriaDelete(RequerimentoSalario.class);
		criteriaDelete2.from(RequerimentoSalario.class);
		session.createQuery(criteriaDelete2).executeUpdate();
		session.getTransaction().commit();
		return true;
	}
}

package br.com.senior.proway.ferias.model.controledeacesso.permissao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.senior.proway.ferias.bancodedados.DBConnection;

public class PermissaoDAO {

    private static PermissaoDAO permissaoDAO;
    private Session session;

    public static PermissaoDAO getInstance() {
        if(permissaoDAO == null) {
            permissaoDAO = new PermissaoDAO();
        }
        return permissaoDAO;
    }

    private PermissaoDAO() {
        this.session = DBConnection.getSession();
    }

    public boolean criarPermissao(String mensagemPermissao){
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        if(checarSeAPermissaoExiste(mensagemPermissao.toLowerCase(Locale.ROOT))) {
            return false;
        }
        session.save(new Permissao(mensagemPermissao.toLowerCase(Locale.ROOT)));
        session.getTransaction().commit();
        return true;
    }

    public boolean checarSeAPermissaoExiste(String mensagem) {
        if(pegarPermissaoPorMensagem(mensagem) == null){
            return false;
        }
        return true;
    }

    public boolean deletarPermissao(IPermissao permissao) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.delete(permissao);
        session.getTransaction().commit();
        return true;
    }

    public ArrayList<IPermissao> pegarPermissoes() {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IPermissao> cq = cb.createQuery(IPermissao.class);
        List<IPermissao> permissoes = session.createQuery(cq.select(cq.from(Permissao.class))).getResultList();
        return (ArrayList<IPermissao>) permissoes;
    }

    public IPermissao pegarPermissaoPorMensagem(String mensagem) {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Permissao> cq = cb.createQuery(Permissao.class);
        Root<Permissao> origem = cq.from(Permissao.class);
        List<Permissao> permissoes =
                session.createQuery(cq.select(origem).where(cb.equal(origem.get("mensagemPermissao"), mensagem.toLowerCase(Locale.ROOT)))).getResultList();
        if(permissoes.isEmpty()) {
            return null;
        }
        return permissoes.get(0);
    }

    public void limparTabela() {
        if(!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<Permissao> criteriaDelete = builder.createCriteriaDelete(Permissao.class);
        criteriaDelete.from(Permissao.class);
        TypedQuery<Permissao> query = session.createQuery(criteriaDelete);
        query.executeUpdate();
        session.getTransaction().commit();
    }
}

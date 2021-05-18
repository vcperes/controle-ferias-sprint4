package br.com.senior.proway.ferias.model.controledeacesso.usuario;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.senior.proway.ferias.model.controledeacesso.permissao.IPermissao;
import br.com.senior.proway.ferias.model.controledeacesso.permissao.Permissao;

@Entity
public class Usuario implements IUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;
    @OneToMany
    private List<Permissao> permissoes;

    public Usuario(int id) {
        this.setIDUsuario(idUsuario);
    }

    public Usuario() {

    }

    public int getIDUsuario() {
        return this.idUsuario;
    }

    public void setIDUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean temPermissao(IPermissao permissao) {
        return permissoes.contains(permissao);
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

}

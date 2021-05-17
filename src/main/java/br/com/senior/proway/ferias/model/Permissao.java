package br.com.senior.proway.ferias.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.senior.proway.ferias.model.interfaces.IPermissao;

@Entity
public class Permissao implements IPermissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPermissao;
    private String mensagemPermissao;

    public Permissao(int idPermissao, String mensagemPermissao) {
        this.idPermissao = idPermissao;
        this.mensagemPermissao = mensagemPermissao;
    }

    public Permissao() {

    }

    public Permissao(String mensagemPermissao) {
        this.mensagemPermissao = mensagemPermissao;
    }

    /**
     * @return int return the idPermissao
     */
    public int getIDPermissao() {
        return idPermissao;
    }

    /**
     * @param idPermissao the idPermissao to set
     */
    public void setIDPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    /**
     * @return String return the mensagemPermissao
     */
    public String getMensagemPermissao() {
        return mensagemPermissao;
    }

    /**
     * @param mensagemPermissao the mensagemPermissao to set
     */
    public void setMensagemPermissao(String mensagemPermissao) {
        this.mensagemPermissao = mensagemPermissao;
    }

}

package br.com.senior.proway.ferias.model.controledeacesso.usuario;

import br.com.senior.proway.ferias.model.controledeacesso.permissao.IPermissao;

public interface IUsuario {
    
    public int getIDUsuario();
    public void setIDUsuario(int idUsuario);

    public boolean temPermissao(IPermissao permissao);

}

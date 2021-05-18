package br.com.senior.proway.ferias.model.interfaces;

public interface IUsuario {
    
    public int getIDUsuario();
    public void setIDUsuario(int idUsuario);

    public boolean temPermissao(IPermissao permissao);

}

package br.com.senior.proway.ferias.controller;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.DAO.PermissaoDAO;
import br.com.senior.proway.ferias.model.interfaces.IPermissao;
import exceptions.PermissionAlreadyExistsException;

public class PermissaoController {

    private static PermissaoController permissaoController;
    private static PermissaoDAO permissaoDAO;

    public PermissaoController getInstance() {
        if(permissaoController == null) {
            permissaoController = new PermissaoController();
        }
        permissaoDAO = PermissaoDAO.getInstance();
        return permissaoController;
    }

    public boolean criarPermissao(String mensagemPermissao) throws PermissionAlreadyExistsException {
        if(permissaoDAO.checarSeAPermissaoExiste(mensagemPermissao)){
            throw new PermissionAlreadyExistsException();
        }
        permissaoDAO.criarPermissao(mensagemPermissao);
        return true;
    }

    public boolean deletarPermissao(IPermissao permissao) {
        return permissaoDAO.deletarPermissao(permissao);
    }

    public ArrayList<IPermissao> pegarPermissoes() {
        return permissaoDAO.pegarPermissoes();
    }
}

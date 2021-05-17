package br.com.senior.proway.ferias.controller;
import java.util.List;

import br.com.senior.proway.ferias.model.Usuario;
import br.com.senior.proway.ferias.model.DAO.PermissaoDAO;
import br.com.senior.proway.ferias.model.interfaces.IControleDeAcesso;
import br.com.senior.proway.ferias.model.interfaces.IPermissao;
import br.com.senior.proway.ferias.model.interfaces.IUsuario;
import exceptions.PermissionIDAlreadyUsedException;
import exceptions.UserDoesNotHavePermissionException;

public class UsuarioController implements IControleDeAcesso {

    PermissaoDAO permissaoDAO = PermissaoDAO.getInstance();

    public boolean adicionarPermissao(IUsuario usuario, Usuario usuarioNovaPermissao, IPermissao permissao) throws PermissionIDAlreadyUsedException, UserDoesNotHavePermissionException{
        IPermissao atribuirPermissao = permissaoDAO.pegarPermissaoPorMensagem("atribuirPermissao");
        if(!usuario.temPermissao(atribuirPermissao)) {
            throw new UserDoesNotHavePermissionException();
        } else if(usuarioNovaPermissao.getPermissoes().contains(permissao)) {
            throw new PermissionIDAlreadyUsedException();
        } else {
            List<IPermissao> permissoes = usuarioNovaPermissao.getPermissoes();
            permissoes.add(permissao);
            usuarioNovaPermissao.setPermissoes(permissoes);
            return true;
        }
    }

    public boolean removerPermissao(IUsuario usuario, Usuario usuarioRemovePermissao, IPermissao permissao) throws UserDoesNotHavePermissionException{
        IPermissao removerPermissao = permissaoDAO.pegarPermissaoPorMensagem("removerPermissao");
        if (!usuario.temPermissao(removerPermissao)){
            throw new UserDoesNotHavePermissionException();
        }
        if(usuarioRemovePermissao.getPermissoes().contains(permissao)) {
            List<IPermissao> permissoes = usuarioRemovePermissao.getPermissoes();
            permissoes.add(permissao);
            usuarioRemovePermissao.setPermissoes(permissoes);
            return true;
        } else {
           return false;
        }
    }

	public boolean validarAcesso(IUsuario identificadorUsuario, IPermissao permissao) {
		if(identificadorUsuario.temPermissao(permissao)) {
			return true;
		}
		return false;
	}
}

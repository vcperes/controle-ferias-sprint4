package br.com.senior.proway.ferias.model.controledeacesso;

import br.com.senior.proway.ferias.model.controledeacesso.permissao.IPermissao;
import br.com.senior.proway.ferias.model.controledeacesso.usuario.IUsuario;

public class ControleDeAcesso implements IControleDeAcesso {

	public boolean validarAcesso(IUsuario identificadorUsuario, IPermissao permissao) {
		
		return true;
	}

}

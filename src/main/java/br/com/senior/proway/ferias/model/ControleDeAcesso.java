package br.com.senior.proway.ferias.model;

import br.com.senior.proway.ferias.model.interfaces.IControleDeAcesso;
import br.com.senior.proway.ferias.model.interfaces.IPermissao;
import br.com.senior.proway.ferias.model.interfaces.IUsuario;

public class ControleDeAcesso implements IControleDeAcesso {

	public boolean validarAcesso(IUsuario identificadorUsuario, IPermissao permissao) {
		
		return true;
	}

}

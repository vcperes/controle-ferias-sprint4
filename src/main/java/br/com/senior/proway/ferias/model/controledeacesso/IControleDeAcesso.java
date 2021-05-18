package br.com.senior.proway.ferias.model.controledeacesso;

import br.com.senior.proway.ferias.model.controledeacesso.permissao.IPermissao;
import br.com.senior.proway.ferias.model.controledeacesso.usuario.IUsuario;

public interface IControleDeAcesso {

		public boolean validarAcesso(IUsuario identificadorUsuario, IPermissao permissao);
}

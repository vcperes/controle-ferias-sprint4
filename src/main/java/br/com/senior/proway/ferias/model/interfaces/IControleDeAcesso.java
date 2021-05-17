package br.com.senior.proway.ferias.model.interfaces;

public interface IControleDeAcesso {

		public boolean validarAcesso(IUsuario identificadorUsuario, IPermissao permissao);
}

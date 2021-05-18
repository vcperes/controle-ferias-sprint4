package br.com.senior.proway.ferias.model.ferias;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

public interface IHistoricoFerias {
		
	public void adicionarHistoricoFerias(Ferias ferias);
		
	public void removerHistoricoFerias(Ferias ferias);
	
	public int verificaQuantidadeHistoricoFerias();
}

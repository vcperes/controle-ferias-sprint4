package br.com.senior.proway.ferias.model.requerimento;

import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFerias;

public interface IHistoricoRequerimentos {
	
	public void adicionarHistoricoRequerimentos(RequerimentoFerias req);
	
	public void removerHistoricoRequerimentos(RequerimentoFerias req);
		
	public int verificaQuantiaRequerimentos();
	
}

package br.com.senior.proway.ferias.model.ferias;

public interface IHistoricoFerias {
		
	public void adicionarHistoricoFerias(Ferias ferias);
		
	public void removerHistoricoFerias(Ferias ferias);
	
	public int verificaQuantidadeHistoricoFerias();
}

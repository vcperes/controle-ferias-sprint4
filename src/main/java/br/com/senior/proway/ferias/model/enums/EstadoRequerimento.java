package br.com.senior.proway.ferias.model.enums;
/**
 * 
 * @author Jonata e Leonardo Pereira
 *
 */
public enum EstadoRequerimento {
	EM_ANALISE(0),
	APROVADO(1),
	REPROVADO(2),
	INVALIDO(3);
	
	private int valor;
	
	EstadoRequerimento(int valor){
		this.valor = valor;
	}
	public int getValor() {
		return valor;
	}
	
	public static EstadoRequerimento pegarPorValor(int valor) {
		switch(valor) {
		case 0:
			return EstadoRequerimento.EM_ANALISE;
		case 1:
			return EstadoRequerimento.APROVADO;
		case 2:
			return EstadoRequerimento.REPROVADO;
		case 3:
			return EstadoRequerimento.INVALIDO;
		default:
			return null;

		}
	}
}

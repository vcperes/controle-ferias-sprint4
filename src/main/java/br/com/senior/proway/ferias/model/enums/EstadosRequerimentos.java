package br.com.senior.proway.ferias.model.enums;
/**
 * 
 * @author Jonata e Leonardo Pereira
 *
 */
public enum EstadosRequerimentos {
	EM_ANALISE(0),
	APROVADO(1),
	REPROVADO(2),
	INVALIDO(3);
	
	private int valor;
	
	EstadosRequerimentos(int valor){
		this.valor = valor;
	}
	public int getValor() {
		return valor;
	}
	
	public static EstadosRequerimentos pegarPorValor(int valor) {
		switch(valor) {
		case 0:
			return EstadosRequerimentos.EM_ANALISE;
		case 1:
			return EstadosRequerimentos.APROVADO;
		case 2:
			return EstadosRequerimentos.REPROVADO;
		case 3:
			return EstadosRequerimentos.INVALIDO;
		default:
			return null;

		}
	}
}

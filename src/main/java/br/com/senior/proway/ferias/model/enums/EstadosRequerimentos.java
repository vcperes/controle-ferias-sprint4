package br.com.senior.proway.ferias.model.enums;
/**
 * 
 * @author Jonata e Leonardo Pereira
 *
 */
public enum EstadosRequerimentos {
	EM_ANALISE(1),
	APROVADO(2),
	REPROVADO(3),
	INVALIDO(4);
	
	private int valor;
	
	EstadosRequerimentos(int valor){
		this.valor = valor;
	}
	public int getValor() {
		return valor;
	}
	
	public static EstadosRequerimentos pegarPorValor(int valor) {
		switch(valor) {
		case 1:
			return EstadosRequerimentos.EM_ANALISE;
		case 2:
			return EstadosRequerimentos.APROVADO;
		case 3:
			return EstadosRequerimentos.REPROVADO;
		case 4:
			return EstadosRequerimentos.INVALIDO;
		default:
			return null;

		}
	}
}

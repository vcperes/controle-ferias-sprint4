package br.com.senior.proway.ferias.model.enums;

public enum TiposFerias {
	INVALIDA(0), // Erro
	TOTAL(1), // Todos os dias são utilizados nas férias
	PARCIAL(2), // Férias com uma fração dos dias disponíveis, o restante é automaticamente
				// vendido
	FRACIONADA(3), // Ferias com uma fração dos dias disponiveis, o restante continua como credito
	VENDIDA(4); // Vende todos os dias disponíveis

	private int valor;

	TiposFerias(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TiposFerias pegarPorValor(int valor) {
		switch (valor) {
		case 0:
			return TiposFerias.INVALIDA;
		case 1:
			return TiposFerias.TOTAL;
		case 2:
			return TiposFerias.PARCIAL;
		case 3:
			return TiposFerias.FRACIONADA;
		case 4:
			return TiposFerias.VENDIDA;
		default:
			return null;
		}
	}

}

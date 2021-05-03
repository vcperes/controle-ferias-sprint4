package br.com.senior.proway.ferias.model.enums;

public enum TiposFerias {
	INVALIDA(1), // Erro
	TOTAL(2), // Todos os dias são utilizados nas férias
	PARCIAL(3), // Férias com uma fração dos dias disponíveis, o restante é automaticamente
				// vendido
	FRACIONADA(4), // Ferias com uma fração dos dias disponiveis, o restante continua como credito
	VENDIDA(5); // Vende todos os dias disponíveis

	private int valor;

	TiposFerias(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TiposFerias pegarPorValor(int valor) {
		switch (valor) {
		case 1:
			return TiposFerias.INVALIDA;
		case 2:
			return TiposFerias.TOTAL;
		case 3:
			return TiposFerias.PARCIAL;
		case 4:
			return TiposFerias.FRACIONADA;
		case 5:
			return TiposFerias.VENDIDA;
		default:
			return null;
		}
	}

}

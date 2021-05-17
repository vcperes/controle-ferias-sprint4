package br.com.senior.proway.ferias.model.enums;
public enum NivelUrgencia {
    
    URGENTE(0), ALTO(1), MEDIO(2), BAIXO(3);

    int valor;

    NivelUrgencia(int tipo) {
        this.valor = tipo;
    }

    public static NivelUrgencia getTipo(int i) {
        switch (i) {
            case 1: return NivelUrgencia.ALTO;
            case 2: return NivelUrgencia.MEDIO;
            case 3: return NivelUrgencia.BAIXO;
            default: return NivelUrgencia.URGENTE;
        }
    }
}

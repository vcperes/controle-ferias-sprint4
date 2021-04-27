package br.com.senior.proway.ferias.model.interfaces;

import java.util.ArrayList;

public interface IConsultaPorColaboradorDAO {
	public ArrayList<IFerias> pegarFeriasPorIDColaborador(String id);
}

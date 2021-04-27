package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IConsultaPorColaboradorDAO {
	public ArrayList<IFerias> pegarFeriasPorIDColaborador(String id);
}

package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IConsultaPorColaboradorDAO {
	public List<IFerias> pegarTodasAsFeriasPorIDColaborador(int id);
}

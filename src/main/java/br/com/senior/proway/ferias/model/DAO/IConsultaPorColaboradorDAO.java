package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

import br.com.senior.proway.ferias.model.Ferias;

public interface IConsultaPorColaboradorDAO {
	public List<Ferias> pegarTodasAsFeriasPorIDColaborador(int id);
}

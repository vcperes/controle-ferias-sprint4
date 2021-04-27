package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IConsultaDeFeriasPorTipoDAO {
	public ArrayList<IFerias> pegarTodasAsFeriasTotais();

	public ArrayList<IFerias> pegarTodasAsFeriasInvalidas();

	public ArrayList<IFerias> pegarTodasAsFeriasParciais();

	public ArrayList<IFerias> pegarTodasAsFeriasFracionadas();

	public ArrayList<IFerias> pegarTodasAsFeriasVendidas();
}

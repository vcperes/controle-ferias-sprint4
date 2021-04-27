package br.com.senior.proway.ferias.model.interfaces;

import java.util.ArrayList;

public interface IConsultaDeFeriasPorTipoDAO {
	public ArrayList<IFerias> pegarTodasAsFeriasTotais();

	public ArrayList<IFerias> pegarTodasAsFeriasInvalidas();

	public ArrayList<IFerias> pegarTodasAsFeriasParciais();

	public ArrayList<IFerias> pegarTodasAsFeriasFracionadas();

	public ArrayList<IFerias> pegarTodasAsFeriasVendidas();
}

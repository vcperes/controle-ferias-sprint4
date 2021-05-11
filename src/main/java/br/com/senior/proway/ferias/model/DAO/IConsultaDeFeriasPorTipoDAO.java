package br.com.senior.proway.ferias.model.DAO;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IConsultaDeFeriasPorTipoDAO {
	
	public ArrayList<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo);
}

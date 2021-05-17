package br.com.senior.proway.ferias.model.DAO.interfaces;

import java.util.List;

import br.com.senior.proway.ferias.model.enums.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public interface IConsultaDeFeriasPorTipoDAO {
	
	public List<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo);
}

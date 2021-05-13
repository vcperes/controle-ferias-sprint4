package br.com.senior.proway.ferias.model.DAO;

import java.util.List;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public interface IConsultaDeFeriasPorTipoDAO {
	
	public List<Ferias> pegarTodasAsFeriasPorTipo(TiposFerias tipo);
}

package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.TiposFerias;

public interface IFeriasBuilder {
	void setDataInicio(LocalDate dataInicio);
	void setDataFim(LocalDate dataFim);
	void setDiasTotaisRequisitados(); // calculado
	void setTipo(TiposFerias total);
	void setDiasVendidos(short i);
}

package br.com.senior.proway.ferias.model.saldoferias;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFerias;

public interface ISaldoBuilder extends ISaldoFerias {

	void setHistoricoFerias(ArrayList<Ferias> arrayList);

	void setHistoricoRequerimentos(ArrayList<RequerimentoFerias> arrayList);

}

package br.com.senior.proway.ferias.model.interfaces;

import java.util.ArrayList;

import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.FeriasRequerimento;

public interface ISaldoBuilder extends ISaldoFerias {

	void setHistoricoFerias(ArrayList<Ferias> arrayList);

	void setHistoricoRequerimentos(ArrayList<FeriasRequerimento> arrayList);

}

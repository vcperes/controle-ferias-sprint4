package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;

import br.com.senior.proway.ferias.controller.interfaces.IFeriasValidacoes;
import br.com.senior.proway.ferias.model.TiposFerias;
import br.com.senior.proway.ferias.model.interfaces.IFerias;

public class ControllerFeriasTotal implements IFeriasValidacoes  {

	// Da Interface IFeriasValidacoes
		public boolean verificarFerias( IFerias ferias) {
			if (ferias.getDiasTotaisRequisitados() > 0)
				return true;
			return false;
		}

		public TiposFerias classificarFerias(IFerias ferias, short saldoDiasFerias) {
			if (ferias.getDiasTotaisRequisitados() == saldoDiasFerias) {
				return TiposFerias.TOTAL;
			}
			return TiposFerias.INVALIDA;
		}

		public short calcularPeriodoFerias(LocalDate dataInicioFerias, LocalDate dataFimFerias) {
			if (Tratativas.periodoFeriasValido(dataInicioFerias, dataFimFerias)) {
				return Tratativas.retornarIntervaloEmDiasEntreAsDatas(dataInicioFerias, dataFimFerias);
			} else {
				return 0;
			}
		}
		
}

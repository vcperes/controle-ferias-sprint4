package br.com.senior.proway.ferias.model.requerimento.tipos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.requerimento.RequerimentoController;

public class RequerimentoFeriasController extends RequerimentoController {

	private static RequerimentoController requerimentoController;

	public static RequerimentoController getInstance() {
		if (requerimentoController == null) {
			requerimentoController = new RequerimentoController();
		}
		return requerimentoController;
	}
	/**
	 * Retorna quantidade de dias
	 * 
	 * Retorna a quantidade de dias em formato short, a partir das datas de inicio e
	 * termino informadas.
	 * 
	 * Diferente da funcao calcularPeriodoFerias da classe Ferias, essa foi definida
	 * como static para nao depender de uma instancia da classe e poder ser usada
	 * como "ferramenta";
	 * 
	 * @param inicio  (LocalDate)
	 * @param termino (LocalDate)
	 * 
	 */
	public static short retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino) {
		short dias = (short) ChronoUnit.DAYS.between(inicio, termino);
		if (inicio.isBefore(termino)) {
			return dias;
		}
		return -1;
	}

	/**
	 * Valida o Prazo da Solicitacao de Ferias.
	 * 
	 * Retorna um boolean obtido atraves da comparacao entre a data de inicio de
	 * ferias e a variavel PRAZO_MINIMO_SOLICITACAO_FERIAS. Para o boolean retornar
	 * true, o (intervalo) deve ser maior que a variavel
	 * PRAZO_MINIMO_SOLICITACAO_FERIAS, neste caso aplicamos o short 10. Se
	 * intervalor menor que 10, atualizamos Estados da Requisicao para INVALIDO.
	 * 
	 * @param dataInicioFerias
	 * @return True/False sucesso da validacao.
	 * 
	 */
	public static boolean validacaoPrazoSolicitacaoDeFerias(RequerimentoFerias feriasRequerimento) {
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(feriasRequerimento.getDataCriacaoRequerimento(),
				((Ferias) feriasRequerimento.getObjetoRequerimento()).getDataInicio());
		if (intervalo >= RequerimentoFerias.PRAZO_MINIMO_SOLICITACAO_FERIAS + 1) {
			return true;
		} else {
			feriasRequerimento.setEstadoRequerimento(EstadoRequerimento.INVALIDO);
			return false;
		}
	}

	public boolean defereRequerimento(RequerimentoFerias requerimento, EstadoRequerimento estado, Integer idUsuario) {
		requerimento.setEstadoRequerimento(estado);
		requerimentoController.atualizarRequerimentoPorId(requerimento);
		return true;
	}
}

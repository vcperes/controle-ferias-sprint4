package br.com.senior.proway.ferias.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FeriasRequerimentoController {

	public ArrayList<FeriasRequerimento> getAllRequerimentos(){
		FeriasRequerimentoDAO feriasRequerimentoDAO = new FeriasRequerimentoDAO();
		ArrayList<FeriasRequerimento> feriasRequerimento = feriasRequerimentoDAO.getAll();
		return feriasRequerimento;
	}
	
	public FeriasRequerimento getRequerimentoPorId(short id) {
		FeriasRequerimentoDAO feriasRequerimentoDAO = new FeriasRequerimentoDAO();
		FeriasRequerimento  feriasRequerimento = feriasRequerimentoDAO.get(id);
		return feriasRequerimento;
	}	
	
	public void createRequerimento(FeriasRequerimento requerimento) {
		FeriasRequerimentoDAO feriasRequerimentoDAO = new FeriasRequerimentoDAO();
		feriasRequerimentoDAO.create(requerimento);
	}
	
	public void update(short id, FeriasRequerimento feriasRequerimento) {
		FeriasRequerimentoDAO feriasRequerimentoDAO = new FeriasRequerimentoDAO();
		feriasRequerimentoDAO.update(id, feriasRequerimento);
	}
	
	public void delete(short id) {
		FeriasRequerimentoDAO feriasRequerimentoDAO = new FeriasRequerimentoDAO();
		feriasRequerimentoDAO.delete(id);
	}
	
	/*
	 * Atualiza o estado da requisi��o
	 * 
	 * Verifica se o novoEstado est� registrado nas ENUMS do sistema 
	 * Atualiza o estado do requerimento
	 * 
	 * @return true/false sucesso da opera��o.
	 */
	public void atualizarEstadoRequisicao(EstadosRequisicao novoEstado, FeriasRequerimento feriasRequerimento) {
		// Verificar os estados dentro do ENUM);
		// Futuramente fazer outras valida��es necess�rias aqui. Por enquanto o m�todo est� redundante com o setEstadoRequisicao;
		feriasRequerimento.setEstadoRequisicao(novoEstado);
	}
	
	/**
	 * Retorna quantidade de dias
	 * 
	 * Retorna a quantidade de dias em formato short, a partir das datas de in�cio e
	 * t�rmino informadas.
	 * 
	 * Diferente da funcao calcularPeriodoFerias da classe Ferias,
	 * essa foi definida como static para nao depender de uma instancia da classe e poder ser usada como "ferramenta";
	 * 
	 * @param inicio  Data de início.
	 * @param termino Data de término.
	 * 
	 */
	public static short retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino) {
		short dias = (short) ChronoUnit.DAYS.between(inicio, termino);
		if(inicio.isBefore(termino)) {
			return dias;
		}
		return -1;
	}
	
	/**
	 * Retorna um boolean obtido atraves da comparacao entre a data de inicio de
	 * ferias e a variavel PRAZO_MINIMO_SOLICITACAO_FERIAS. Para o boolean retornar
	 * true, o (intervalo) deve ser maior que a variavel
	 * PRAZO_MINIMO_SOLICITACAO_FERIAS, neste caso aplicamos o short 10.
	 * 
	 * @param dataInicioFerias
	 * 
	 */
	public boolean validacaoPrazoSolicitacaoDeFerias(LocalDate dataInicio, FeriasRequerimento feriasRequerimento) {
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(feriasRequerimento.getDataSolicitacao(), dataInicio);
		//System.out.println(intervalo);
		if (intervalo >= FeriasRequerimento.PRAZO_MINIMO_SOLICITACAO_FERIAS) {
			return true;
		} else {
			return false;
		}
	}
	
	
}

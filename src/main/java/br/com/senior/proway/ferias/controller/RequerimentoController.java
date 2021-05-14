package br.com.senior.proway.ferias.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public class RequerimentoController {

	private static RequerimentoDAO requerimentoDao;
	private static RequerimentoController requerimentoController;
	private static Session session;
		
	public static RequerimentoController getInstance(Session session) {
		requerimentoDao = RequerimentoDAO.getInstance(session);
		RequerimentoController.session = session;
		if(requerimentoController == null) {
			requerimentoController = new RequerimentoController();
		}
		return requerimentoController;
	}	
		
	/**
	 * Get All.
	 * 
	 * Controlller faz contato com o RequerimentoDAO, que retorna uma lista de
	 * todos os requerimentos de ferias.
	 * 
	 * @return ArrayList<FeriasRequerimento>
	 */
	public List<Requerimento> getAllRequerimentos() {
		List<Requerimento> feriasRequerimento = requerimentoDao.pegarTodos();
		return feriasRequerimento;
	}

	/**
	 * Get.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebendo um id que
	 * retornara um objeto do tipo Ferias Requerimento de acordo com o id informado.
	 * 
	 * @param short id
	 * @return FeriasRequerimento
	 */
	public Requerimento getRequerimentoPorId(Integer id) {
		Requerimento feriasRequerimento = requerimentoDao.pegarRequerimentoPorID(id);
		return feriasRequerimento;
	}

	/**
	 * Create.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe como parametro um
	 * objeto do tipo FeriasRequerimento, insere o mesmo em nossa Persistencia
	 * atrav�s do DAO.
	 * 
	 * @param Requerimento requerimento
	 */
	public void createRequerimento(Requerimento requerimento) {
		RequerimentoDAO feriasRequerimentoDAO = RequerimentoDAO.getInstance(session);

		feriasRequerimentoDAO.cadastrar(requerimento);
	}

	/**
	 * Update.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe um id e um objeto
	 * do tipo FeriasRequerimento, atraves destes dados atualiza, substituindo o
	 * objeto da Persistencia com os dados repassados atraves do DAO.
	 * 
	 * @param id                 (int)
	 * @param feriasRequerimento (FeriasRequerimento)
	 */
	public boolean updateRequerimentoPorId(Requerimento feriasRequerimento) {

		requerimentoDao.alterar(feriasRequerimento);
		return true;
	}

	/**
	 * Delete
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe um id e realiza a
	 * exclusao do objeto em nossa Persistencia atraves do DAO.
	 * 
	 * @param id (short)
	 */
	public void deleteRequerimento(Requerimento requerimento) {
		RequerimentoDAO feriasRequerimentoDAO = RequerimentoDAO.getInstance(session);
		feriasRequerimentoDAO.deletar(requerimento);
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
	public boolean validacaoPrazoSolicitacaoDeFerias(Requerimento feriasRequerimento) {
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(feriasRequerimento.
				getDataSolicitacao(), feriasRequerimento.getFeriasRequisitada().
				getDataInicio());
		if (intervalo >= Requerimento.PRAZO_MINIMO_SOLICITACAO_FERIAS + 1) {
			return true;
		} else {
			feriasRequerimento.setEstadoRequisicao(EstadosRequerimentos.INVALIDO);
			return false;
		}
	}

}

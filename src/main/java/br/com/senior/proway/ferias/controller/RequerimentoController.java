package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.Session;

import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.DAO.RequerimentoDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.interfaces.IControleDeAcesso;

public class RequerimentoController {
	private static RequerimentoDAO requerimentoDao;
	private static RequerimentoController requerimentoController;
	private static Session session;
	private IControleDeAcesso controleDeAcesso;

	public static RequerimentoController getInstance(Session session) {
		requerimentoDao = RequerimentoDAO.getInstance(session);
		RequerimentoController.session = session;
		if (requerimentoController == null) {
			requerimentoController = new RequerimentoController();
		}
		return requerimentoController;
	}

	/**
	 * Get All.
	 * 
	 * Controlller faz contato com o RequerimentoDAO, que retorna uma lista de todos
	 * os requerimentos de ferias.
	 * 
	 * @return ArrayList<FeriasRequerimento>
	 * @throws Exception
	 */
	public List<Requerimento> getAllRequerimentos(Integer idUsuario) throws Exception {
		if (controleDeAcesso.validarAcesso(idUsuario)) {

			List<Requerimento> feriasRequerimento = requerimentoDao.pegarTodos();
			return feriasRequerimento;
		} else {
			throw new Exception("usuario não tem acesso");
		}
	}

	/**
	 * Get.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebendo um id que
	 * retornara um objeto do tipo Ferias Requerimento de acordo com o id informado.
	 * 
	 * @param short id
	 * @return FeriasRequerimento
	 * @throws Exception
	 */
	public Requerimento getRequerimentoPorId(Integer id, Integer idUsuario) throws Exception {
		if (controleDeAcesso.validarAcesso(idUsuario)) {
			Requerimento feriasRequerimento = requerimentoDao.pegarRequerimentoPorID(id);
			return feriasRequerimento;
		} else {
			throw new Exception("usuario não tem acesso");
		}

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
	public boolean createRequerimento(Requerimento requerimento) {
		RequerimentoDAO feriasRequerimentoDAO = RequerimentoDAO.getInstance(session);
		feriasRequerimentoDAO.cadastrar(requerimento);

		return true;
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
	 * @throws Exception
	 */
	public boolean updateRequerimentoPorId(Requerimento feriasRequerimento, Integer idUsuario) throws Exception {
		if (controleDeAcesso.validarAcesso(idUsuario)) {
			requerimentoDao.alterar(feriasRequerimento);
			return true;
		} else {
			throw new Exception("usuario não tem acesso");
		}
	}

	/**
	 * Delete
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe um id e realiza a
	 * exclusao do objeto em nossa Persistencia atraves do DAO.
	 * 
	 * @param id (short)
	 * @throws Exception
	 */
	public void deleteRequerimento(Requerimento requerimento, Integer idUsuario) throws Exception {
		if (controleDeAcesso.validarAcesso(idUsuario)) {
			RequerimentoDAO feriasRequerimentoDAO = RequerimentoDAO.getInstance(session);
			feriasRequerimentoDAO.deletar(requerimento);
		} else {
			throw new Exception("usuario não tem acesso");
		}
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
	public short retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino) {
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
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(feriasRequerimento.getDataSolicitacao(),
				feriasRequerimento.getFeriasRequisitada().getDataInicio());
		if (intervalo >= Requerimento.PRAZO_MINIMO_SOLICITACAO_FERIAS + 1) {
			return true;
		} else {
			feriasRequerimento.setEstadoRequisicao(EstadosRequerimentos.INVALIDO);
			return false;
		}
	}

	public boolean defereRequerimento(Requerimento requerimento, EstadosRequerimentos estado, Integer idUsuario)
			throws Exception {
		if (controleDeAcesso.validarAcesso(idUsuario)) {
			requerimento.setEstadoRequisicao(estado);
			requerimentoController.updateRequerimentoPorId(requerimento, idUsuario);
			return true;
		}
		return false;
	}
}

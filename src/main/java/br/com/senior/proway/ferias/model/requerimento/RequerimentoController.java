package br.com.senior.proway.ferias.model.requerimento;

import java.util.List;

import br.com.senior.proway.ferias.model.controledeacesso.IControleDeAcesso;
import br.com.senior.proway.ferias.model.controledeacesso.permissao.Permissao;
import br.com.senior.proway.ferias.model.controledeacesso.usuario.IUsuario;

public class RequerimentoController {
	private static RequerimentoDAO requerimentoDAO;
	private static RequerimentoController requerimentoController;

	public static RequerimentoController getInstance() {
		requerimentoDAO = RequerimentoDAO.getInstance();
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
	public List<IRequerimento> buscarTodosOsRequerimentos(Class<?> classe) {
		List<IRequerimento> requerimentosFerias = requerimentoDAO.buscarRequerimentos(classe);
		return requerimentosFerias;
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
	public IRequerimento buscarRequerimentoPorId(Class<?> tipoRequerimento, Integer id) {
		IRequerimento requerimento = requerimentoDAO.buscarRequerimento(tipoRequerimento, id);
		return requerimento;
	}

	/**
	 * Create.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe como parametro um
	 * objeto do tipo FeriasRequerimento, insere o mesmo em nossa Persistencia
	 * atrav�s do DAO.
	 * 
	 * @param RequerimentoFerias requerimento
	 */
	public boolean createRequerimento(IRequerimento requerimento) {
		requerimentoDAO.criarRequerimento(requerimento);
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
	public boolean atualizarRequerimentoPorId(IRequerimento requerimento){
		requerimentoDAO.atualizarRequerimento(requerimento);
		return true;
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
	public void deleteRequerimento(IRequerimento requerimento) {
		requerimentoDAO.deletarRequerimento(requerimento);
	}

	
}

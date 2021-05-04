package br.com.senior.proway.ferias.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import br.com.senior.proway.ferias.database.DataBase;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.DAO.RequerimentoFeriasDAO;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;

public class FeriasRequerimentoController {
	
	/**
	 * Get All.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, que retorna 
	 * uma lista de todos os requerimentos de ferias.
	 * 
	 * @return ArrayList<FeriasRequerimento>
	 */
	public ArrayList<RequerimentoFerias> getAllRequerimentos(){
		RequerimentoFeriasDAO feriasRequerimentoDAO = new RequerimentoFeriasDAO();
		ArrayList<RequerimentoFerias> feriasRequerimento = feriasRequerimentoDAO.pegarTodos();
		return feriasRequerimento;
	}
	
	/**
	 * Get.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebendo um id
	 * que retornara um objeto do tipo Ferias Requerimento de acordo 
	 * com o id informado.
	 * 
	 * @param short id
	 * @return FeriasRequerimento
	 */
	public RequerimentoFerias getRequerimentoPorId(short id) {
		RequerimentoFeriasDAO feriasRequerimentoDAO = new RequerimentoFeriasDAO();
		RequerimentoFerias  feriasRequerimento = feriasRequerimentoDAO.pegarPorID(id);
		return feriasRequerimento;
	}	
	
	/**
	 * Create.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe como parametro um objeto
	 * do tipo FeriasRequerimento, insere o mesmo em nossa Persistencia atrav�s do DAO.
	 * 
	 * @param RequerimentoFerias requerimento
	 */
	public void createRequerimento(RequerimentoFerias requerimento) {
		RequerimentoFeriasDAO feriasRequerimentoDAO = new RequerimentoFeriasDAO();
		feriasRequerimentoDAO.cadastrar(requerimento);
	}
	
	/**
	 * Update.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe um id e um objeto do tipo
	 * FeriasRequerimento, atraves destes dados atualiza, substituindo o objeto da Persistencia com
	 * os dados repassados atraves do DAO.
	 * 
	 * @param id (short)
	 * @param feriasRequerimento (FeriasRequerimento)
	 */
	public void updateRequerimentoPorId(short id, RequerimentoFerias feriasRequerimento) {
		RequerimentoFeriasDAO feriasRequerimentoDAO = new RequerimentoFeriasDAO();
		feriasRequerimentoDAO.alterar(id, feriasRequerimento);
	}
	
	/**
	 * Delete
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO, recebe um id e realiza a 
	 * exclusao do objeto em nossa Persistencia atraves do DAO.
	 * 
	 * @param id (short)
	 */
	public void deleteRequerimentoPorId(short id) {
		RequerimentoFeriasDAO feriasRequerimentoDAO = new RequerimentoFeriasDAO();
		feriasRequerimentoDAO.deletar(id);
	}
	
	/**
	 * Get All Requerimentos por Usuario.
	 * 
	 * Controlller faz contato com o FeriasRequerimentoDAO recebendo um idUsuario retornando
	 * uma lista com todos os requerimentos de ferias do usuario informado.
	 * 
	 * @param idUsuario (short)
	 * @return ArrayList<FeriasRequerimento>
	 */
	public ArrayList<RequerimentoFerias> getAllRequerimentosPorIdUsuario(short idUsuario) {
		DataBase dbSingle = DataBase.getInstance();
		RequerimentoFeriasDAO feriasRequerimentoDAO = new RequerimentoFeriasDAO();
		ArrayList<RequerimentoFerias> requerimentoUsuarios = feriasRequerimentoDAO.pegarTodos();
		for (int i = 0; i < dbSingle.historico.size(); i++) {
			if(dbSingle.historico.get(i).getId().equals(idUsuario)) {
				requerimentoUsuarios.add(dbSingle.historico.get(i));
			}
		}
		return requerimentoUsuarios;
	}
	
	/*
	 * Atualiza o estado da requisicao
	 * 
	 * Verifica se o novoEstado esta registrado nas ENUMS do sistema 
	 * Atualiza o estado do requerimento
	 * 
	 * @return true/false sucesso da operacao.
	 */
	public void atualizarEstadoRequisicao(EstadosRequerimentos novoEstado, RequerimentoFerias feriasRequerimento) {
		// Verificar os estados dentro do ENUM);
		// Futuramente fazer outras validacoes necessarias aqui. Por enquanto o mmetodo esta redundante com o setEstadoRequisicao;
		feriasRequerimento.setEstadoRequisicao(novoEstado);
	}
	
	/**
	 * Retorna quantidade de dias
	 * 
	 * Retorna a quantidade de dias em formato short, a partir das datas de inicio e
	 * termino informadas.
	 * 
	 * Diferente da funcao calcularPeriodoFerias da classe Ferias,
	 * essa foi definida como static para nao depender de uma instancia da classe e poder ser usada como "ferramenta";
	 * 
	 * @param inicio  (LocalDate)
	 * @param termino (LocalDate)
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
	 * Valida o Prazo da Solicitacao de Ferias.
	 * 
	 * Retorna um boolean obtido atraves da comparacao entre a data de inicio de
	 * ferias e a variavel PRAZO_MINIMO_SOLICITACAO_FERIAS. Para o boolean retornar
	 * true, o (intervalo) deve ser maior que a variavel
	 * PRAZO_MINIMO_SOLICITACAO_FERIAS, neste caso aplicamos o short 10. Se intervalor menor que 
	 * 10, atualizamos Estados da Requisicao para INVALIDO.
	 * 
	 * @param dataInicioFerias
	 * @return True/False sucesso da validacao.
	 * 
	 */
	public boolean validacaoPrazoSolicitacaoDeFerias(LocalDate dataInicio, RequerimentoFerias feriasRequerimento) {
		int intervalo = retornarIntervaloEmDiasEntreAsDatas(feriasRequerimento.getDataSolicitacao(), dataInicio);
		if (intervalo >= RequerimentoFerias.PRAZO_MINIMO_SOLICITACAO_FERIAS) {
			return true;
		} else {
			feriasRequerimento.setEstadoRequisicao(EstadosRequerimentos.INVALIDO);
			return false;
		}
	}
	
}

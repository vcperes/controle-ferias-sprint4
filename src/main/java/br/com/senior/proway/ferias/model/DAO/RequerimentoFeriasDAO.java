package br.com.senior.proway.ferias.model.DAO;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import br.com.senior.proway.ferias.model.Ferias;
import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.postgresql.PostgresConector;

public class RequerimentoFeriasDAO implements Icrud<RequerimentoFerias> {
	
	FeriasDAO feriasDao = new FeriasDAO();
	/**
	 * Lista todos os objetos de Requerimento.
	 * 
	 * Acessa e lista todos os objetos da tabela requerimento do banco de dados, podendo os armazenar 
	 * em um array.
	 * 
	 * @return ArrayList com os objetos da tabela requerimento.
	 * 
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 */
	public ArrayList<RequerimentoFerias> pegarTodos() {

		ArrayList<RequerimentoFerias> requerimentosFerias = new ArrayList<RequerimentoFerias>(); 
		String select = "SELECT * FROM requerimento;";
		try {

			PostgresConector.conectar();
			ResultSet rs = PostgresConector.executarQuery(select);

			while(rs.next()) {
				
				RequerimentoFerias requerimentoFerias = new RequerimentoFerias();
				requerimentoFerias.setId(rs.getString("id"));
				Ferias ferias = (Ferias)feriasDao.pegarPorID(rs.getInt("idferias"));
				requerimentoFerias.setFeriasRequisitada(ferias);
				requerimentoFerias.setEstadoRequisicao(EstadosRequerimentos.pegarPorValor(rs.getInt("idestadorequisicao")));
				LocalDate dataSolicitacao = rs.getDate("datasolicitacao").toLocalDate();
				requerimentoFerias.setDataSolicitacao(dataSolicitacao);
				requerimentosFerias.add(requerimentoFerias);

			}


		}
		catch(SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		return requerimentosFerias;
	}

	/**
	 * 
	 * M�todo que pega um requerimento por id.
	 * 
	 * Pega um id da tabela requerimento do banco de dados e retorna o objeto, RequerimentoFerias.
	 * 
	 * @param int id, que � o id puxado do banco de dados.
	 * @return objeto RequerimentoFerias.
	 * 
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 */
	public RequerimentoFerias pegarPorID(int id) {
		try {

			PostgresConector.conectar();
			String select = "SELECT * FROM esquemaferias.requerimento WHERE id = "+ id +";";
			ResultSet rs = PostgresConector.executarQuery(select);	

			if(rs.next()) {

				String idRequerimento = rs.getString("id");
				int idFerias = rs.getInt("idferias");
				EstadosRequerimentos idEstadoRequisicao = EstadosRequerimentos.pegarPorValor(rs.getInt("idestadorequisicao"));
				LocalDate dataSolicitacao = rs.getDate("datasolicitacao").toLocalDate();

				FeriasDAO feriasDao = new FeriasDAO();
				Ferias ferias = (Ferias) feriasDao.pegarPorID(idFerias);

				RequerimentoFerias requerimentoFerias = new RequerimentoFerias(idRequerimento, ferias, idEstadoRequisicao, dataSolicitacao );

				return requerimentoFerias;
			}

		}
		catch(SQLException e ) {
			e.printStackTrace();
			fail(e.getMessage());

		}
		return null;


	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 * M�todo que cadastra objeto requerimento no banco de dados
	 * 
	 * Recebe objeto RequerimentoFerias como parametro, separa por atributos e insere por expressao SQL no 
	 * banco de dados
	 * 
	 * @param RequerimentoFerias objeto
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */
	public boolean cadastrar(RequerimentoFerias objeto) {
		String idFerias = ""+objeto.getFeriasRequisitada().getId();
		String idEstadoRequisicao =""+ objeto.getEstadoRequisicao().getValor();
		String dataSolicitacao =  ""+objeto.getDataSolicitacao();
		try {
			PostgresConector.conectar();
			String insert = "INSERT INTO requerimento(idferias, idestadorequisicao, datasolicitacao)"
					+ " VALUES("+idFerias+", "+idEstadoRequisicao+", '"+dataSolicitacao+"');";
			PostgresConector.executarUpdateQuery(insert);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return false;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 * M�todo que altera objeto requerimento no banco de dados
	 * 
	 * Altera objeto RequerimentoFerias como parametro, separa por atributos e insere por expressao SQL no 
	 * banco de dados
	 * 
	 * @param int id, RequerimentoFerias objeto
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */

	public boolean alterar(int id, RequerimentoFerias objeto) {

		boolean alterado = false;
		String query = "UPDATE requerimento SET idestadorequisicao = " 
				+ objeto.getEstadoRequisicao().getValor() +" WHERE id = "+id+";";

		try {
			PostgresConector.conectar();
			PostgresConector.executarUpdateQuery(query);
			alterado = true;
		}

		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return alterado;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 * M�todo que deleta objeto requerimento no banco de dados
	 * 
	 * Deleta objeto RequerimentoFerias atraves do id.
	 * 
	 * @param int id objeto
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */

	public boolean deletar(int id) {
		
		boolean deletado = false;
		String query = "DELETE FROM requerimento WHERE id ="+id+";";
		
		try {
			PostgresConector.conectar();
			PostgresConector.executarUpdateQuery(query);
			deletado = true;
		}

		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return deletado;

	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 * M�todo que cadastra objeto requerimento no banco de dados
	 * 
	 * Pega todos os objetos RequerimentoFerias que tenham o mesmo estado de requerimento recebido como parametro.

	 * @param Estadosrequerimentos objeto
	 * 
	 * @return boolean verdadeiro se cadastrado com sucesso.
	 * 
	 */

	public ArrayList<RequerimentoFerias> getRequerimentoPorEstado(EstadosRequerimentos estado) {

		ArrayList<RequerimentoFerias> listaRequerimento = new ArrayList<RequerimentoFerias>();

		try {

			PostgresConector.conectar();
			String select = "SELECT * FROM esquemaferias.requerimento WHERE idestadorequisicao = " + estado.getValor() +";";
			ResultSet rs = PostgresConector.executarQuery(select);

			while(rs.next()) {

				RequerimentoFerias requerimento = new RequerimentoFerias();
				requerimento.setId(rs.getString("id"));

				FeriasDAO feriasDao = new FeriasDAO();
				int idFerias = rs.getInt("idFerias");
				Ferias ferias = (Ferias) feriasDao.pegarPorID(idFerias);
				requerimento.setFeriasRequisitada(ferias);

				EstadosRequerimentos estadorequerimento = EstadosRequerimentos.pegarPorValor(rs.getInt("idestadorequisicao"));
				requerimento.setEstadoRequisicao(estadorequerimento);

				LocalDate localdate = rs.getDate("datasolicitacao").toLocalDate();
				requerimento.setDataSolicitacao(localdate);

				listaRequerimento.add(requerimento);
			}

			return listaRequerimento;

		}
		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return listaRequerimento;
	}

	/**
	 * @author Vitor Peres <vitor.peres@senior.com.br>
	 * @author Bruna Carvalho <sh4323202@gmail.com>
	 * @author Daniella Lira <dev.danilira@gmail.com>
	 * 
	 * M�todo que busca o requerimento por data no banco de dados
	 * 
	 * Pega todos os objetos RequerimentoFerias que tenham a mesma data de requerimento recebido como parametro.

	 * @param LocalDate, dataParaPesquisa
	 * 
	 * @return ArrayList<RequerimentoFerias>
	 */

	public ArrayList<RequerimentoFerias> getRequerimentoPorData(LocalDate dataParaPesquisa) {		
		ArrayList<RequerimentoFerias> listaRequerimento = new ArrayList<RequerimentoFerias>();

		try {

			PostgresConector.conectar();
			String select = "SELECT * FROM esquemaferias.requerimento WHERE datasolicitacao = '" + dataParaPesquisa +"';";
			ResultSet rs = PostgresConector.executarQuery(select);

			while(rs.next()) {

				RequerimentoFerias requerimento = new RequerimentoFerias();
				requerimento.setId(rs.getString("id"));

				FeriasDAO feriasDao = new FeriasDAO();
				int idFerias = rs.getInt("idFerias");
				Ferias ferias = (Ferias) feriasDao.pegarPorID(idFerias);
				requerimento.setFeriasRequisitada(ferias);

				EstadosRequerimentos estadorequerimento = EstadosRequerimentos.pegarPorValor(rs.getInt("idestadorequisicao"));
				requerimento.setEstadoRequisicao(estadorequerimento);

				LocalDate localdate = rs.getDate("datasolicitacao").toLocalDate();
				requerimento.setDataSolicitacao(localdate);

				listaRequerimento.add(requerimento);
			}

			return listaRequerimento;

		}
		catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());

		} 
		return listaRequerimento;
	}

	public void limparBanco() throws SQLException {

		String limpar = "delete from esquemaferias.setor";
		String removerIncremento = "ALTER SEQUENCE grupo2.setor_increment RESTART";
		PostgresConector.executarUpdateQuery(limpar);
		PostgresConector.executarUpdateQuery(removerIncremento);

	}

	//implementar, não possui teste 
	//	/**
	//	 * Get All Requerimentos por Usuario.
	//	 * 
	//	 * @param idUsuario (short)
	//	 * @return ArrayList<FeriasRequerimento>
	//	 */
	//	public ArrayList<RequerimentoFerias> getAllRequerimentosPorIdUsuario(short idUsuario) {
	//		ArrayList<RequerimentoFerias> lista = new ArrayList<RequerimentoFerias>();
	//		return lista;
	//	}
}

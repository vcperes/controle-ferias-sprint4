package br.com.senior.proway.ferias.model.saldoferias;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.senior.proway.ferias.model.ferias.Ferias;
import br.com.senior.proway.ferias.model.requerimento.tipos.RequerimentoFerias;

/** Responsavel por definir os valores que o Builder carrega para cada caso de instanciamento de SaldoFerias.
 */
public class SaldoFeriasDirector {
	
	/** Instancia um objeto de Ferias classificado como Ferias TOTAL
	 * @param builder Objeto Builder responsavel por instanciar Ferias
	 * @param identificadorUsuario String que representa o identificador de um funcionario no banco de dados
	 * @param dataAdmissao LocalDate que representa a data de contratacao do fucionario, vem do controle de funcionarios
	 */
	public void createSaldoFerias(ISaldoBuilder builder, 
								  String identificadorUsuario, 
								  LocalDate dataAdmissao) 
	{
		builder.setIdentificadorUsuario(identificadorUsuario);
		builder.setProximasFerias(dataAdmissao.plusYears(1));
		builder.setDiasDisponiveisDeFerias((short) 0);
		builder.setHistoricoFerias(new ArrayList<Ferias>());
		builder.setHistoricoRequerimentos(new ArrayList<RequerimentoFerias>());
	}
}

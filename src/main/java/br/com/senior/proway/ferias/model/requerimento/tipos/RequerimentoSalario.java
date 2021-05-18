package br.com.senior.proway.ferias.model.requerimento.tipos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.senior.proway.ferias.model.aumento.AumentoDeSalario;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;
import br.com.senior.proway.ferias.model.requerimento.RequerimentoFactory;

/**
 * Classe RequerimentoSalario.
 * 
 * Essa classe existe para que seja possivel o teste dos metodos das classes
 * RequerimentoController e RequerimentoDAO, visto que dois tipos diferentes de
 * requerimento sao necessarios para verificar os metodos especificos de busca.
 * 
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 *
 */
@Entity
public class RequerimentoSalario extends RequerimentoFactory<AumentoDeSalario> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int idRequerimentoSalario;

	/**
	 * Construtor vazio (para uso do Hibernate)
	 */
	public RequerimentoSalario() {
		super();
	}

	public RequerimentoSalario(AumentoDeSalario objetoRequerimento, EstadoRequerimento estadoRequerimento,
			int idVerificadorRequerimento, int idCriadorRequerimento, LocalDate prazoParaAnaliseRequerimento,
			NivelUrgencia nivelUrgencia, String mensagemRequerimento) {
		super(objetoRequerimento, estadoRequerimento, idVerificadorRequerimento, idCriadorRequerimento,
				prazoParaAnaliseRequerimento, nivelUrgencia, mensagemRequerimento);
	}
}

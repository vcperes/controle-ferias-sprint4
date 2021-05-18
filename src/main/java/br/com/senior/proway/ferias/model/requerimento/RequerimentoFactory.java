package br.com.senior.proway.ferias.model.requerimento;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;

/**
 * Classe RequerimentoFactory.
 * 
 * E utilizada como superclasse para todos os Requerimentos, de modo a padroniza-los. Todo e qualquer
 * novo requerimento deve extender essa classe e implementar ambos o construtor
 * completo e o construtor vazio (para o Hibernate), passando como tipo o objeto
 * do qual o requerimento se trata.
 * 
 * @author Senior
 * @author Vitor Nathan Goncalves <vitor.goncalves@senior.com.br>
 * @author Guilherme Eduardo Bom Guse <gbg_bg@hotmail.com>
 * @author Guilherme Ezequiel da Silva <ezequielguilherme002@gmail.com>
 * @author Marcelo Schaefer Filho <marceloschaeferfilho@gmail.com>
 * @author Vitor Cesar Peres <vitorperes1104@gmail.com>
 */
@MappedSuperclass
public class RequerimentoFactory<T> implements IRequerimento<T> {

	@OneToOne(cascade = CascadeType.ALL)
	private T objetoRequerimento;
	private LocalDate dataCriacaoRequerimento;
	private EstadoRequerimento estadoRequerimento;
	private int idVerificadorRequerimento;
	private int idCriadorRequerimento;
	private LocalDate prazoParaAnaliseRequerimento;
	private NivelUrgencia nivelUrgencia;
	@Type(type = "text")
	private String mensagemRequerimento;

	public RequerimentoFactory(T objetoRequerimento, EstadoRequerimento estadoRequerimento,
			int idVerificadorRequerimento, int idCriadorRequerimento, LocalDate prazoParaAnaliseRequerimento,
			NivelUrgencia nivelUrgencia, String mensagemRequerimento) {
		this.objetoRequerimento = objetoRequerimento;
		this.dataCriacaoRequerimento = LocalDate.now();
		this.estadoRequerimento = estadoRequerimento;
		this.idVerificadorRequerimento = idVerificadorRequerimento;
		this.idCriadorRequerimento = idCriadorRequerimento;
		this.prazoParaAnaliseRequerimento = prazoParaAnaliseRequerimento;
		this.nivelUrgencia = nivelUrgencia;
		this.mensagemRequerimento = mensagemRequerimento;
	}

	/**
	 * Construtor vazio.
	 * Deve existir para que as subclasses possam ter o construtor vazio (por exigencia do Hibernate)
	 */
	public RequerimentoFactory() {

	}

	/**
	 * @return T return the objetoRequerimento
	 */
	public T getObjetoRequerimento() {
		return objetoRequerimento;
	}

	/**
	 * @param objetoRequerimento the objetoRequerimento to set
	 */
	public void setObjetoRequerimento(T objetoRequerimento) {
		this.objetoRequerimento = objetoRequerimento;
	}

	/**
	 * @return LocalDate return the dataCriacaoRequerimento
	 */
	public LocalDate getDataCriacaoRequerimento() {
		return dataCriacaoRequerimento;
	}
	
	/**
	 * @param dataCriacaoRequerimento the dataCriacaoRequerimento to set
	 */
	public void setDataCriacaoRequerimento(LocalDate dataCriacaoRequerimento) {
		this.dataCriacaoRequerimento = dataCriacaoRequerimento;
	}

	/**
	 * @return EstadoRequerimento return the estadoRequerimento
	 */
	public EstadoRequerimento getEstadoRequerimento() {
		return estadoRequerimento;
	}

	/**
	 * @param estadoRequerimento the estadoRequerimento to set
	 */
	public void setEstadoRequerimento(EstadoRequerimento estadoRequerimento) {
		this.estadoRequerimento = estadoRequerimento;
	}

	/**
	 * @return int return the idVerificadorRequerimento
	 */
	public int getIDVerificadorRequerimento() {
		return idVerificadorRequerimento;
	}

	/**
	 * @param idVerificadorRequerimento the idVerificadorRequerimento to set
	 */
	public void setIDVerificadorRequerimento(int idVerificadorRequerimento) {
		this.idVerificadorRequerimento = idVerificadorRequerimento;
	}

	/**
	 * @return int return the idCriadorRequerimento
	 */
	public int getIDCriadorRequerimento() {
		return idCriadorRequerimento;
	}

	/**
	 * @param idCriadorRequerimento the idCriadorRequerimento to set
	 */
	public void setIDCriadorRequerimento(int idCriadorRequerimento) {
		this.idCriadorRequerimento = idCriadorRequerimento;
	}

	/**
	 * @return LocalDate return the prazoParaAnaliseRequerimento
	 */
	public LocalDate getPrazoParaAnaliseRequerimento() {
		return prazoParaAnaliseRequerimento;
	}

	/**
	 * @param prazoParaAnaliseRequerimento the prazoParaAnaliseRequerimento to set
	 */
	public void setPrazoParaAnaliseRequerimento(LocalDate prazoParaAnaliseRequerimento) {
		this.prazoParaAnaliseRequerimento = prazoParaAnaliseRequerimento;
	}

	/**
	 * @return NivelUrgencia return the nivelUrgencia
	 */
	public NivelUrgencia getNivelUrgencia() {
		return nivelUrgencia;
	}

	/**
	 * @param nivelUrgencia the nivelUrgencia to set
	 */
	public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
		this.nivelUrgencia = nivelUrgencia;
	}

	/**
	 * @return String return the mensagemRequerimento
	 */
	public String getMensagemRequerimento() {
		return mensagemRequerimento;
	}

	/**
	 * @param mensagemRequerimento the mensagemRequerimento to set
	 */
	public void setMensagemRequerimento(String mensagemRequerimento) {
		this.mensagemRequerimento = mensagemRequerimento;
	}

}

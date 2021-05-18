package br.com.senior.proway.ferias.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;
import br.com.senior.proway.ferias.model.interfaces.IRequerimento;

@MappedSuperclass
public class RequerimentoFactory<T> implements IRequerimento<T> {

    @OneToOne
    private T objetoRequerimento;
    private LocalDate dataCriacaoRequerimento;
    public void setDataCriacaoRequerimento(LocalDate dataCriacaoRequerimento) {
		this.dataCriacaoRequerimento = dataCriacaoRequerimento;
	}

	private EstadoRequerimento estadoRequerimento;
    private int idVerificadorRequerimento;
    private int idCriadorRequerimento;
    private LocalDate prazoParaAnaliseRequerimento;
    private NivelUrgencia nivelUrgencia;
    @Type(type="text")
    private String mensagemRequerimento;

    public RequerimentoFactory(T objetoRequerimento, EstadoRequerimento estadoRequerimento,
                               int idVerificadorRequerimento, int idCriadorRequerimento,
            LocalDate prazoParaAnaliseRequerimento, NivelUrgencia nivelUrgencia, String mensagemRequerimento) {
        this.objetoRequerimento = objetoRequerimento;
        this.dataCriacaoRequerimento = LocalDate.now();
        this.estadoRequerimento = estadoRequerimento;
        this.idVerificadorRequerimento = idVerificadorRequerimento;
        this.idCriadorRequerimento = idCriadorRequerimento;
        this.prazoParaAnaliseRequerimento = prazoParaAnaliseRequerimento;
        this.nivelUrgencia = nivelUrgencia;
        this.mensagemRequerimento = mensagemRequerimento;
    }

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

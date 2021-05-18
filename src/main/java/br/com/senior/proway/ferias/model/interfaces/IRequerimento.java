package br.com.senior.proway.ferias.model.interfaces;
import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.enums.NivelUrgencia;

public interface IRequerimento<T> {

    public T getObjetoRequerimento();
    public void setObjetoRequerimento(T objeto);

    public LocalDate getDataCriacaoRequerimento();

    public EstadoRequerimento getEstadoRequerimento();
    public void setEstadoRequerimento(EstadoRequerimento estadoRequerimento);

    public int getIDVerificadorRequerimento();
    public void setIDVerificadorRequerimento(int idVerificador);

    public int getIDCriadorRequerimento();
    public void setIDCriadorRequerimento(int idCriadorRequerimento);

    public LocalDate getPrazoParaAnaliseRequerimento();
    public void setPrazoParaAnaliseRequerimento(LocalDate dataLimite);
    
    public NivelUrgencia getNivelUrgencia();
    public void setNivelUrgencia(NivelUrgencia nivelUrgencia);

    public String getMensagemRequerimento();
    public void setMensagemRequerimento(String mensagemRequerimento);
}
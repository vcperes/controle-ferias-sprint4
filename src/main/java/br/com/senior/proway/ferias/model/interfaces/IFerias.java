package br.com.senior.proway.ferias.model.interfaces;

import java.time.LocalDate;

import br.com.senior.proway.ferias.model.enums.TiposFerias;

public interface IFerias {
	public void setId(int id);
	public int getId();
	
	public void setIdentificadorUsuario(int valor);
	public int getIdentificadorUsuario();
	
	public void setDataInicio(LocalDate dataInicio);
	public LocalDate getDataInicio();
	
	public void setDataFim(LocalDate dataFim);
	public LocalDate getDataFim();
	
	public void setDiasTotaisRequisitados(int valor);
	public int getDiasTotaisRequisitados();
	
	public void setDiasVendidos(int valor);
	public int getDiasVendidos();
	
	public TiposFerias getTipo();
	public void setTipo(TiposFerias total);
}

package br.com.senior.proway.ferias.controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.senior.proway.ferias.model.DAO.IDao;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.interfaces.IControleDeAcesso;
import br.com.senior.proway.ferias.model.interfaces.IRequerimento;

public interface IRequerimentoController<T> {

	 Dao<T> dao;

	IControleDeAcesso cotroleDeAcesso();

	public List<IRequerimento> getAllRequerimentos(Integer idUsuario);

	public IRequerimento getRequerimentoPorId(Integer id, Integer idUsuario);

	public boolean createRequerimento(IRequerimento requerimento);

	public boolean updateRequerimentoPorId(IRequerimento feriasRequerimento, Integer idUsuario);

	public void deleteRequerimento(IRequerimento requerimento, Integer idUsuario);

	public short retornarIntervaloEmDiasEntreAsDatas(LocalDate inicio, LocalDate termino);

	public boolean validacaoPrazoSolicitacaoDeFerias(IRequerimento feriasRequerimento);

	public boolean defereRequerimento(IRequerimento requerimento, EstadosRequerimentos estado, Integer idUsuario);
}

package br.com.senior.proway.ferias.model.DAO.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.senior.proway.ferias.model.RequerimentoFerias;
import br.com.senior.proway.ferias.model.enums.EstadoRequerimento;
import br.com.senior.proway.ferias.model.interfaces.IRequerimento;

public interface IDao {

	public List<IRequerimento> pegarTodos();

	public RequerimentoFerias pegarRequerimentoPorID(Integer id);

	public boolean cadastrar(IRequerimento objeto);

	public boolean alterar(IRequerimento objeto);

	public boolean deletar(IRequerimento objeto);

	public List<IRequerimento> getRequerimentoPorEstado(EstadoRequerimento estado);

	public List<IRequerimento> getRequerimentoPorData(LocalDate dataParaPesquisa);

	public boolean limparTabela();
}

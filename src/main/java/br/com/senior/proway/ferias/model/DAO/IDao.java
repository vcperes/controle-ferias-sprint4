package br.com.senior.proway.ferias.model.DAO;

import java.time.LocalDate;
import java.util.List;

import br.com.senior.proway.ferias.model.Requerimento;
import br.com.senior.proway.ferias.model.enums.EstadosRequerimentos;
import br.com.senior.proway.ferias.model.interfaces.IRequerimento;

public interface IDao {

	public List<IRequerimento> pegarTodos();

	public Requerimento pegarRequerimentoPorID(Integer id);

	public boolean cadastrar(IRequerimento objeto);

	public boolean alterar(IRequerimento objeto);

	public boolean deletar(IRequerimento objeto);

	public List<IRequerimento> getRequerimentoPorEstado(EstadosRequerimentos estado);

	public List<IRequerimento> getRequerimentoPorData(LocalDate dataParaPesquisa);

	public boolean limparTabela();
}

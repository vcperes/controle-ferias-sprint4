package br.com.senior.proway.ferias.model.DAO.interfaces;

import java.util.List;

import br.com.senior.proway.ferias.model.interfaces.IRequerimento;

public interface IRequerimentoDAO {

    boolean criarRequerimento(IRequerimento requerimento);
    boolean deletarRequerimento(IRequerimento requerimento);
    boolean atualizarRequerimento(IRequerimento requerimento);
    List<IRequerimento> buscarRequerimentos(IRequerimento requerimento);
}
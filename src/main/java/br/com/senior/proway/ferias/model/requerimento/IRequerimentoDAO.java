package br.com.senior.proway.ferias.model.requerimento;

import java.util.List;

public interface IRequerimentoDAO {

    boolean criarRequerimento(IRequerimento requerimento);
    boolean deletarRequerimento(IRequerimento requerimento);
    boolean atualizarRequerimento(IRequerimento requerimento);
    List<IRequerimento> buscarRequerimentos(IRequerimento requerimento);
}
package br.com.senior.proway.ferias.model.ferias;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import br.com.senior.proway.ferias.bancodedados.DBConnection;
import br.com.senior.proway.ferias.model.enums.TiposFerias;

public class FeriasController {

	private static FeriasController feriasController;

	public static FeriasController getInstance() {
		feriasDAO = FeriasDAO.getInstance();
		if (feriasController == null) {
			feriasController = new FeriasController();
		}
		return feriasController;
	}

	private static FeriasDAO feriasDAO;

	public FeriasController() {
		feriasDAO = FeriasDAO.getInstance();
	}

	public List<IFerias> pegarTodos() {
		return feriasDAO.pegarTodos();
	}

	public IFerias pegarFeriasPorId(int id) {

		return feriasDAO.pegarFeriasPorID(id);
	}

	public boolean cadastrar(IFerias ferias) {
		return feriasDAO.cadastrar(ferias);
	}

	public boolean alterar(IFerias novaFerias) {
		return feriasDAO.alterar(novaFerias);
	}

	public boolean deletar(IFerias ferias) {
		return feriasDAO.deletar(ferias);
	}

	public List<IFerias> pegarTodasAsFeriasPorIDColaborador(int idUsuarioEntrada) {
		return feriasDAO.pegarTodasAsFeriasPorIDColaborador(idUsuarioEntrada);
	}

	public List<IFerias> pegarTodasAsFeriasPorTipo(TiposFerias tipo) {
		return feriasDAO.pegarTodasAsFeriasPorTipo(tipo);
	}

	public List<IFerias> pegarTodasAsFeriasPorDataInicio(LocalDate dataRecebida) {
		return feriasDAO.pegarTodasAsFeriasPorDataInicio(dataRecebida);
	}

	public void limpartabela() {
		feriasDAO.limparTabela();
	}
}

package br.usjt.web.projetopi.services;

import br.usjt.web.projetopi.model.Atividade;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.AtividadeDao;

public class AtividadeService {
	AtividadeDao atividadeDao;

	public AtividadeService() {
		this.atividadeDao = new AtividadeDao();
	}

	public int cadastrar(Atividade atividade) {
		return this.atividadeDao.cadastrar(atividade);
	}

	public Atividade consultar(int id) {
		return this.atividadeDao.consultar(id);
	}

	public void excluir(int id) {
		this.atividadeDao.excluir(id);
	}

	public void alterar(Atividade novo) {
		this.atividadeDao.alterar(novo);
	}

	public ArrayList<Atividade> listarAtividades() {
		return this.atividadeDao.listarAtividades();
	}
}

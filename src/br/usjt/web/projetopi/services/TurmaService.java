package br.usjt.web.projetopi.services;

import br.usjt.web.projetopi.model.Turma;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.TurmaDao;

public class TurmaService {
	TurmaDao turmaDao;
	
	public TurmaService() {
		this.turmaDao = new TurmaDao();
	}
	
	public int cadastrar(Turma turma) {
		return this.turmaDao.cadastrar(turma);
	}
	
	public Turma consultar(int id) {
		return this.turmaDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.turmaDao.excluir(id);
	}
	
	public void alterar(Turma novo) {
		this.turmaDao.alterar(novo);
	}
	public ArrayList<Turma> listarTurmas()
	{
		return this.turmaDao.listarTurmas();
	}
}

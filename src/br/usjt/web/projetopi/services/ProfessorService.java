package br.usjt.web.projetopi.services;

import br.usjt.web.projetopi.model.Professor;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.ProfessorDao;

public class ProfessorService {
	ProfessorDao professorDao;
	
	public ProfessorService() {
		this.professorDao = new ProfessorDao();
	}
	
	public void cadastrar(Professor professor) {
		this.professorDao.cadastrar(professor);
	}
	
	public Professor consultar(int id) {
		return this.professorDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.professorDao.excluir(id);
	}
	
	public void alterar(Professor novo) {
		this.professorDao.alterar(novo);
	}
	public ArrayList<Professor> listarProfessor()
	{
		return this.professorDao.listarProfessor();
	}
}

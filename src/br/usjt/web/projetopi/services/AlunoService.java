package br.usjt.web.projetopi.services;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.AlunoDao;
import br.usjt.web.projetopi.model.Aluno;

public class AlunoService {
	AlunoDao alunoDao;
	
	public AlunoService() {
		this.alunoDao = new AlunoDao();
	}
	
	public void cadastrar(Aluno aluno) {
		this.alunoDao.cadastrar(aluno);
	}
	
	public Aluno consultar(int id)
	{
		return this.alunoDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.alunoDao.excluir(id);
	}
	
	public void alterar(Aluno novo) {
		this.alunoDao.alterar(novo);
	}
	public ArrayList<Aluno> listarAlunos()
	{
		return this.alunoDao.listarAlunos();
	}
	public void alocarAlunoaTurma(Aluno aluno)
	{
		this.alunoDao.alocarAlunoaTurma(aluno);
	}
	public ArrayList<Aluno> listarAlunosAlocados(int id)
	{
		return this.alunoDao.listarAlunosAlocado(id);
	}
}

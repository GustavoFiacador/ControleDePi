package br.usjt.web.projetopi.services;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.GrupoDao;
import br.usjt.web.projetopi.model.Grupo;

public class GrupoService {
	GrupoDao grupoDao;
	
	public GrupoService() {
		this.grupoDao = new GrupoDao();
	}
	
	public int cadastrar(Grupo grupo) {
		return this.grupoDao.cadastrar(grupo);
	}
	
	public Grupo consultar(int id) {
		return this.grupoDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.grupoDao.excluir(id);
	}
	
	public void alterar(Grupo novo) {
		this.grupoDao.alterar(novo);
	}
	public void alocarAluno(Grupo grupo)
	{
		this.grupoDao.alocarAlunoAGrupo(grupo);
	}
	public ArrayList<Grupo> listarGrupos()
	{
		return this.grupoDao.listarGrupo();
	}
}

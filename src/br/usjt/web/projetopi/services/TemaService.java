package br.usjt.web.projetopi.services;

import br.usjt.web.projetopi.model.Tema;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.TemaDao;

public class TemaService {
	TemaDao temaDao;
	
	public TemaService() {
		this.temaDao = new TemaDao();
	}
	
	public int cadastrar(Tema tema) {
		return this.temaDao.cadastrar(tema);
	}
	
	public Tema consultar(int id) {
		return this.temaDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.temaDao.excluir(id);
	}
	
	public void alterar(Tema novo) {
		this.temaDao.alterar(novo);
	}
	public ArrayList<Tema> listarTemas()
	{
		return this.temaDao.listarTemas();
	}
}

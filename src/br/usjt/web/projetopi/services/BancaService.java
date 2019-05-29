package br.usjt.web.projetopi.services;

import br.usjt.web.projetopi.model.Banca;
import br.usjt.web.projetopi.dao.BancaDao;

public class BancaService {
	BancaDao bancaDao;
	
	public BancaService() {
		this.bancaDao = new BancaDao();
	}
	
	public void cadastrar(Banca banca) {
		this.bancaDao.cadastrar(banca);
	}
	
	public void consultar(int id) {
		this.bancaDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.bancaDao.excluir(id);
	}
	
	public void alterar(Banca novo) {
		this.bancaDao.alterar(novo);
	}
}

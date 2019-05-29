package br.usjt.web.projetopi.services;

import br.usjt.web.projetopi.model.Entrega;
import br.usjt.web.projetopi.dao.EntregaDao;

public class EntregaService {
	EntregaDao entregaDao;
	
	public EntregaService() {
		this.entregaDao = new EntregaDao();
	}
	
	public void cadastrar(Entrega entrega) {
		this.entregaDao.cadastrar(entrega);
	}
	
	public void consultar(int id) {
		this.entregaDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.entregaDao.excluir(id);
	}
	
	public void alterar(Entrega novo) {
		this.entregaDao.alterar(novo);
	}
}

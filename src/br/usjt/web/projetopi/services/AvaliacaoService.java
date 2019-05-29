package br.usjt.web.projetopi.services;



import br.usjt.web.projetopi.model.Atividade;
import br.usjt.web.projetopi.model.Avaliacao;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.AvaliacaoDao;


public class AvaliacaoService {
	
	AvaliacaoDao avaliacaoDao;
	
	public AvaliacaoService() {
		this.avaliacaoDao = new AvaliacaoDao();
	}
	
	public int cadastrar(Avaliacao avaliacao) {
		return this.avaliacaoDao.cadastrar(avaliacao);
	}
	
	public Avaliacao consultar(int id) {
		return this.avaliacaoDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.avaliacaoDao.excluir(id);
	}
	
	public void alterar(Avaliacao novo) {
		this.avaliacaoDao.alterar(novo);
	}
	
	public ArrayList<Avaliacao> listarAvaliacoes(){
		return this.avaliacaoDao.listarAvaliacoes();
	}
}



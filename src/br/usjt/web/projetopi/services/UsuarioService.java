package br.usjt.web.projetopi.services;

import java.util.ArrayList;

import br.usjt.web.projetopi.dao.UsuarioDao;
import br.usjt.web.projetopi.model.Usuario;

public class UsuarioService {
	UsuarioDao usuarioDao;
	
	public UsuarioService() {
		this.usuarioDao = new UsuarioDao();
	}
	
	public int cadastrar(Usuario usuario) {
		return this.usuarioDao.cadastrar(usuario);
	}
	
	public Usuario consultar(int id) {
		return this.usuarioDao.consultar(id);
	}
	
	public void excluir(int id) {
		this.usuarioDao.excluir(id);
	}
	
	public void alterar(Usuario novo) {
		this.usuarioDao.alterar(novo);
	}
	public Usuario consultaLogin(Usuario usuario) {
		return this.usuarioDao.consultaLogin(usuario);
	}
	public ArrayList<Usuario> listarUsuarios(){
		return this.usuarioDao.listarUsuarios();
	}
}

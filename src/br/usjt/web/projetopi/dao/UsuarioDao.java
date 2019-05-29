package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.Usuario;
import br.usjt.web.projetopi.dao.ConnectionFactory;

public class UsuarioDao {

	Connection conexao;

	public int cadastrar(Usuario usuario) {
		String sql = "INSERT INTO usuario(nome,email,senha) VALUES (?,?,?)";
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
					int idGerado = rs.getInt(1);
					return idGerado;
				} 
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public Usuario consultar(int id) {

		try {
			String sql = "SELECT * FROM usuario WHERE id= ?";
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			this.conexao = ConnectionFactory.conectar();
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			} else
				return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM usuario WHERE id=?";
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlDelete);
			stm.setInt(1, id);
			stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void alterar(Usuario usuario) {
		String sqlUpdate = "UPDATE usuario SET email=?, nome=?, senha=? WHERE id=?";
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlUpdate);
			stm.setString(1, usuario.getEmail());
			stm.setString(2, usuario.getNome());
			stm.setString(3, usuario.getSenha());
			stm.setInt(4, usuario.getId());
			stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Usuario consultaLogin(Usuario usuario) {
		String sqlSelect = "SELECT * FROM usuario WHERE email = ?";
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			stm.setString(1, usuario.getEmail());
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				Usuario usuario2 = new Usuario();
				usuario2.setId(rs.getInt(1));
				usuario2.setNome(rs.getString(2));
				usuario2.setEmail(rs.getString(3));
				usuario2.setSenha(rs.getString(4));
				return usuario2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	public ArrayList<Usuario> listarUsuarios() {
		String sqlSelect = "SELECT * FROM usuario";
		ArrayList<Usuario> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
				lista.add(usuario);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.*;

public class ProfessorDao {
	Connection conexao;

	public ProfessorDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void cadastrar(Professor professor) {
		String sql = "INSERT INTO professor (professor_id, administrador, matricula) VALUES (?, ?, ?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setInt(1, professor.getId());
			ps.setBoolean(2, professor.getAdministrador());
			ps.setString(3, professor.getMatricula());

			ps.execute();

			

		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	public Professor consultar(int id) {
		String sql = "SELECT  usuario_id, matricula, administrador, nome, email FROM PROFESSOR p INNER JOIN usuario u ON p.usuario_id = u.id where p.usuario_id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Professor professor = new Professor();
					professor.setId(rs.getInt("usuario_id"));
					professor.setMatricula(rs.getString("matricula"));
					professor.setAdministrador(rs.getBoolean("administrador"));
					professor.setNome(rs.getString("nome"));
					professor.setEmail(rs.getString("email"));
					return professor;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return null;
	}

	public void excluir(int id) {
		String sql = "DELETE FROM professor WHERE id_professor = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
	}

	public void alterar (Professor professor) 
	{
		String sql = "UPDATE professor SET administrador = ? WHERE id = ?";
		
		try (PreparedStatement stm = conexao.prepareStatement(sql);)
		{
			stm.setBoolean(1, professor.getAdministrador());
			stm.setInt(2, professor.getId());
			stm.execute();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try 
	         {
					conexao.rollback();
				} 
	         catch (SQLException e1) 
	         {
					System.out.print(e1.getStackTrace());
				}
		}
	}
	public ArrayList<Professor> listarProfessor() {
		String sqlSelect = "SELECT  usuario_id, matricula, administrador, nome, email FROM PROFESSOR p INNER JOIN usuario u ON p.usuario_id = u.id";
		ArrayList<Professor> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Professor p = new Professor();
				p.setId(rs.getInt(1));
				p.setMatricula(rs.getString(2));
				p.setAdministrador(rs.getBoolean(3));
				p.setNome(rs.getString(4));
				p.setEmail(rs.getString(5));
				lista.add(p);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}	

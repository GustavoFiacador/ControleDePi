package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.Aluno;
import br.usjt.web.projetopi.model.Turma;



public class AlunoDao {

	Connection conexao;
	public AlunoDao()
	{
		try {
			this.conexao = ConnectionFactory.conectar();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	public void cadastrar(Aluno aluno) {
			String sql = "INSERT INTO aluno(usuario_id, ra) VALUES (?,?) ";

			try (PreparedStatement ps = this.conexao.prepareStatement(sql))
			{
			ps.setInt(1, aluno.getId());
			ps.setInt(2, aluno.getRa());

			ps.execute();
			ps.close();

			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public Aluno consultar(int id) {
		String sql = "SELECT  usuario_id, ra, nome, email FROM ALUNO a INNER JOIN usuario u ON a.usuario_id = u.id WHERE u.id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Aluno a = new Aluno();
					a.setId(rs.getInt("usuario_id"));
					a.setRa(rs.getInt("RA"));		
					a.setEmail(rs.getString("email"));
					a.setNome(rs.getString("nome"));
					return a;
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
		try {
			String sql = "DELETE FROM aluno WHERE usuario_id = ?";

			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void alterar(Aluno novo) {
		try {
			String sql = "UPDATE aluno SET nome = ?, email = ?, senha = ?, ra = ? WHERE id = ?";
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, novo.getId());
			ps.setString(2, novo.getNome());
			ps.setString(3, novo.getEmail());
			ps.setString(4, novo.getSenha());
			ps.setInt(5, novo.getRa());

			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public ArrayList<Aluno> listarAlunos() {
		String sqlSelect = "SELECT  usuario_id, ra, nome, email, turma_id, sigla FROM ALUNO a "
				+ "INNER JOIN usuario u ON a.usuario_id = u.id "
				+ "INNER JOIN aluno_turma alt ON alt.aluno_usuario_id = u.id "
				+ "INNER JOIN turma t ON alt.turma_id = t.id ";
		
		ArrayList<Aluno> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Aluno a = new Aluno();
				a.setId(rs.getInt(1));
				a.setRa(rs.getInt(2));
				a.setNome(rs.getString(3));
				a.setEmail(rs.getString(4));
				Turma t = new Turma();
				t.setId(rs.getInt(5));
				t.setSigla(rs.getString(6));
				a.setTurma(t);
				lista.add(a);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void alocarAlunoaTurma(Aluno aluno)
	{
		String sqlInsert = "INSERT INTO aluno_turma (aluno_usuario_id, turma_id) VALUES (?,?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sqlInsert))
		{
		ps.setInt(1, aluno.getId());
		ps.setInt(2, aluno.getTurma().getId());

		ps.execute();
		ps.close();

		
	} catch (SQLException ex) {
		ex.printStackTrace();
	}
	}
	public ArrayList<Aluno> listarAlunosAlocado(int id)
	{
	String sqlSelect = "SELECT nome, id FROM aluno_turma a INNER JOIN usuario u ON a.aluno_usuario_id = u.id WHERE a.turma_id = ?";
	ArrayList<Aluno> lista = new ArrayList<>();
	try (PreparedStatement ps = this.conexao.prepareStatement(sqlSelect)) 
	{
		ps.setInt(1, id);
		try (ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Aluno a = new Aluno();
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				lista.add(a);
				
			}
			return lista;
			}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	} 
	catch (Exception e1) {
		e1.printStackTrace();
	}

	return null;
	}
}

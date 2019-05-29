package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.*;
import br.usjt.web.projetopi.services.TemaService;

public class TurmaDao {
	Connection conexao;

	public TurmaDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int cadastrar(Turma turma)  {
		String sql = "INSERT INTO turma (semestreLetivo, anoLetivo, sigla, tema_id) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, turma.getSemestreLetivo());
			ps.setInt(2, turma.getAnoLetivo());
			ps.setString(3, turma.getSigla());
			ps.setInt(4, turma.getTema().getId());

			ps.execute();

			try (ResultSet rs = ps.getGeneratedKeys();) {
				if (rs.next()) {
					int idGerado = rs.getInt(1);
					return idGerado;
				} else {
					return -1;
				}
			} catch (SQLException e) {
				System.out.println(e.getStackTrace());
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getStackTrace());
			}
		}
		return -1;
	}

	public Turma consultar(int id)  {
		String sql = "SELECT * FROM turma WHERE id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Turma turma = new Turma();
					turma.setId(rs.getInt("id"));
					turma.setSemestreLetivo(rs.getInt("semestreLetivo"));
					turma.setAnoLetivo(rs.getInt("anoLetivo"));
					turma.setSigla(rs.getString("sigla"));

					Tema tema = new Tema();
					tema.setId(rs.getInt("tema_id"));

					turma.setTema(tema);

					return turma;
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

	public void excluir(int id)  {
		String sql = "DELETE FROM turma WHERE id = ?";

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

	public void alterar(Turma turma)  {
		String sql = "UPDATE turma SET sigla = ?, anoLetivo = ?, semestreLetivo = ?, tema_id = ? WHERE id = ?";

		try (PreparedStatement stm = conexao.prepareStatement(sql);) {
			stm.setString(1, turma.getSigla());
			stm.setInt(2, turma.getAnoLetivo());
			stm.setInt(3, turma.getSemestreLetivo());
			stm.setInt(4, turma.getTema().getId());
			stm.setInt(5, turma.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}
	}
	public ArrayList<Turma> listarTurmas() {
		String sqlSelect = "SELECT * from TURMA";
		ArrayList<Turma> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Turma t = new Turma();
				t.setId(rs.getInt(1));
				t.setSemestreLetivo(rs.getInt(2));
				t.setAnoLetivo(rs.getInt(3));
				t.setSigla(rs.getString(4));
				
			
				TemaService temaS = new TemaService();
				Tema tema = temaS.consultar(rs.getInt(5));
				t.setTema(tema);
				lista.add(t);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Aluno> listarAlunosAlocado(int id)
	{
	String sqlSelect = "SELECT nome, id FROM aluno_turma a INNER JOIN usuario u ON a.aluno_usuario_id = u.id WHERE a.turma_id = 1SELECT nome, id FROM aluno_turma a INNER JOIN usuario u ON a.aluno_usuario_id = u.id WHERE a.turma_id = ?";
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
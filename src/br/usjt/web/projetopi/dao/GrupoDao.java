package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.*;
import br.usjt.web.projetopi.services.ProfessorService;
import br.usjt.web.projetopi.services.TemaService;
import br.usjt.web.projetopi.services.TurmaService;

public class GrupoDao {
	Connection conexao;

	public GrupoDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int cadastrar(Grupo grupo)  {
		String sql = "INSERT INTO grupo (professor_usuario_id, numero, nome, turma_id) VALUES (?, ?,?, ?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, grupo.getOrientador().getId());
			ps.setInt(2, grupo.getNumero());
			ps.setString(3, grupo.getNome());
			ps.setInt(4, grupo.getTurma().getId());

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
	public void alocarAlunoAGrupo(Grupo grupo)  {
		String sql = "INSERT INTO aluno_grupo (grupo_id, aluno_usuario_id) VALUES (?, ?)";
		ArrayList<Aluno> lista = grupo.getAlunos();
		for (Aluno aluno : lista)
		{
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
		
				ps.setInt(1, grupo.getId());
				ps.setInt(2, aluno.getId());
			
			ps.execute();	

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getStackTrace());
			}
		}
		}
	
	}
	public Grupo consultar(int id)  {
		String sql = "SELECT * FROM grupo WHERE id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Grupo grupo = new Grupo();
					grupo.setId(rs.getInt("id"));
					Professor professor = new Professor();
					professor.setId(rs.getInt("professor_usuario_id"));
					grupo.setOrientador(professor);
					
					Turma turma = new Turma();
					turma.setId(rs.getInt("turma_id"));
					grupo.setTurma(turma);
					grupo.setNumero(rs.getInt("numero"));
					grupo.setNome(rs.getString("nome"));

					return grupo;
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
		String sql = "DELETE FROM grupo WHERE id = ?";

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

	public void alterar(Grupo grupo)  {
		String sql = "UPDATE grupo SET orientador_id = ?, numero = ?, nome = ? WHERE id = ?";

		try (PreparedStatement stm = conexao.prepareStatement(sql);) {
			stm.setInt(1, grupo.getOrientador().getId());
			stm.setInt(2, grupo.getNumero());
			stm.setString(3, grupo.getNome());
			stm.setInt(4, grupo.getId());
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
	public ArrayList<Grupo> listarGrupo() {
		String sqlSelect = "SELECT * from Grupo";
		ArrayList<Grupo> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Grupo g = new Grupo();
				g.setId(rs.getInt(1));
				g.setNumero(rs.getInt(2));
				g.setNome(rs.getString(3));
				
				
				
				Professor p = new Professor();
				ProfessorService pSer = new ProfessorService();
				p = pSer.consultar(rs.getInt(4));
				g.setOrientador(p);
				
			
				
				TurmaService tSer = new TurmaService();
				Turma t = new Turma();
				
				t = tSer.consultar(rs.getInt(5));
				
				g.setTurma(t);
				lista.add(g);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
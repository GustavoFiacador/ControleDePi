package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.*;

public class AtividadeDao {
	Connection conexao;

	public AtividadeDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int cadastrar(Atividade atividade) {
		String sql = "INSERT INTO atividade (numero, descricao, formatoEntrega, dtInicio, dtFim, tema_id) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, atividade.getNumero());
			ps.setString(2, atividade.getDescricao());
			ps.setString(3, atividade.getFormatoEntrega());
			ps.setString(4, atividade.getDtInicio());
			ps.setString(5, atividade.getDtFim());
			ps.setInt(6, atividade.getTemaId().getId());

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

	public Atividade consultar(int id) {
		String sql = "SELECT * FROM atividade WHERE id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Atividade atividade = new Atividade();
					Tema tema = new Tema();
					atividade.setId(rs.getInt("id"));
					atividade.setNumero(rs.getInt("numero"));
					atividade.setDescricao(rs.getString("descricao"));
					atividade.setFormatoEntrega(rs.getString("formatoEntrega"));
					atividade.setDtInicio(rs.getString("dtInicio"));
					atividade.setDtFim(rs.getString("dtFim"));
					tema.setId(rs.getInt("tema_id"));
					atividade.setTemaId(tema);

					return atividade;
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
		String sql = "DELETE FROM atividade WHERE id = ?";

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

	public void alterar(Atividade atividade) {
		String sql = "UPDATE atividade SET dtInicio = ?, dtFim = ?, formatoEntrega = ?  WHERE id = ?";

		try (PreparedStatement stm = conexao.prepareStatement(sql);) {
			stm.setString(1, atividade.getDtInicio());
			stm.setString(2, atividade.getDtFim());
			stm.setString(3, atividade.getFormatoEntrega());
			stm.setInt(4, atividade.getId());
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
	
	public ArrayList<Atividade> listarAtividades() {
		String sqlSelect = "SELECT * FROM Atividade";
		ArrayList<Atividade> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Atividade t = new Atividade();
				Tema tema = new Tema();
				
				t.setId(rs.getInt(1));
				t.setNumero(rs.getInt(2));
				t.setDescricao(rs.getString(3));
				t.setFormatoEntrega(rs.getString(4));
				t.setDtInicio(rs.getString(5));
				t.setDtFim(rs.getString(6));
				tema.setId(rs.getInt(7));
				t.setTemaId(tema);
				lista.add(t);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

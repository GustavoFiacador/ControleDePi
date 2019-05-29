package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.usjt.web.projetopi.model.*;

public class EntregaDao {
	Connection conexao;

	public EntregaDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int cadastrar(Entrega entrega) {
		String sql = "INSERT INTO entrega (grupo_id, atividade_id, dt_cadastro) VALUES (?, ?,?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, entrega.getGrupoId().getId());
			ps.setInt(2, entrega.getAtividadeId().getId());
			ps.setString(3, entrega.getDtCadastro());

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

	public Entrega consultar(int id) {
		String sql = "SELECT * FROM entrega WHERE id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Entrega entrega = new Entrega();
					entrega.setId(rs.getInt("id"));
					Grupo grupo = new Grupo();
					grupo.setId(rs.getInt("grupo_id"));
					entrega.setGrupoId(grupo);
					Atividade atividade = new Atividade();
					atividade.setId(rs.getInt("atividade_id"));
					entrega.setAtividadeId(atividade);

					entrega.setDtCadastro(rs.getString("dt_cadastro"));

					return entrega;
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
		String sql = "DELETE FROM entrega WHERE id = ?";

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

	public void alterar(Entrega entrega) {
		String sql = "UPDATE entrega SET grupo_id = ?, atividade_id = ?, dt_cadastro = ? WHERE id = ?";

		try (PreparedStatement stm = conexao.prepareStatement(sql);) {
			stm.setInt(1, entrega.getGrupoId().getId());
			stm.setInt(2, entrega.getAtividadeId().getId());
			stm.setString(3, entrega.getDtCadastro());
			stm.setInt(4, entrega.getId());
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

}
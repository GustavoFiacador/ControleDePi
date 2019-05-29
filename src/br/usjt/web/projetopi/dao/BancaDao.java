package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.usjt.web.projetopi.model.*;
 

public class BancaDao {
	Connection conexao;

	public BancaDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int cadastrar(Banca banca) {
		String sql = "INSERT INTO banca (grupo, dataCorrecao, sala) VALUES (?, ?,?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setInt(1, banca.getGrupoId().getId());
			ps.setString(2, banca.getData());
			ps.setString(3, banca.getSala());

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

	public Banca consultar(int id) {
		String sql = "SELECT * FROM banca WHERE id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Banca banca = new Banca();
					banca.setId(rs.getInt("id"));
					Grupo grupo = new Grupo();
					grupo.setId(rs.getInt("grupo_id"));
					banca.setGrupoId(grupo);
					banca.setData(rs.getString("data"));
					banca.setSala(rs.getString("sala"));

					return banca;
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
		String sql = "DELETE FROM banca WHERE id = ?";

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

	public void alterar(Banca banca) {
		String sql = "UPDATE banca SET grupo_id = ?, data = ?, sala = ?  WHERE id = ?";

		try (PreparedStatement stm = conexao.prepareStatement(sql);) {
			stm.setInt(1, banca.getGrupoId().getId());
			stm.setString(2, banca.getData());
			stm.setString(3, banca.getSala());
			stm.setInt(4, banca.getId());
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
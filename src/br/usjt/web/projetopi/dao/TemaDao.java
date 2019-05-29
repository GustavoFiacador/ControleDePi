package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.*;

public class TemaDao {
	Connection conexao;

	public TemaDao() {
		try {
			this.conexao = ConnectionFactory.conectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int cadastrar(Tema tema)  {
		String sql = "INSERT INTO tema (dataCadastro, titulo, requisitos, introducao) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, tema.getDtCadastro());
			ps.setString(2, tema.getTitulo());
			ps.setString(4, tema.getRequisitos());
			ps.setString(3, tema.getIntroducao());
	

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

	public Tema consultar(int id)  {
		String sql = "SELECT * FROM tema WHERE id = ?";

		try (PreparedStatement ps = this.conexao.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Tema tema = new Tema();
					tema.setId(rs.getInt("id"));
					tema.setDtCadastro(rs.getString("dataCadastro"));
					tema.setTitulo(rs.getString("titulo"));
					tema.setRequisitos(rs.getString("requisitos"));
					tema.setIntroducao(rs.getString("introducao"));
					return tema;	
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
		String sql = "DELETE FROM tema WHERE id = ?";

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

	public void alterar (Tema tema)  {
		String sql = "UPDATE tema SET titulo = ?, introducao = ?, requisitos = ? WHERE id = ?";

		try (PreparedStatement stm = conexao.prepareStatement(sql);) {
			stm.setString(1, tema.getTitulo());
			stm.setString(2, tema.getIntroducao());
			stm.setString(3, tema.getRequisitos());
			stm.setInt(4, tema.getId());
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
	public ArrayList<Tema> listarTemas() {
		String sqlSelect = "SELECT * from TEMA";
		ArrayList<Tema> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Tema t = new Tema();
				t.setId(rs.getInt(1));
				t.setDtCadastro(rs.getString(2));
				t.setTitulo(rs.getString(3));
				t.setRequisitos(rs.getString(4));
				t.setIntroducao(rs.getString(5));
				lista.add(t);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
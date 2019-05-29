package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.web.projetopi.model.Aluno;
import br.usjt.web.projetopi.model.Atividade;
import br.usjt.web.projetopi.model.Avaliacao;
import br.usjt.web.projetopi.model.Entrega;
import br.usjt.web.projetopi.model.Tema;

public class AvaliacaoDao {
	Connection conexao;
	
	public int cadastrar(Avaliacao avaliacao){

		try {
			this.conexao = ConnectionFactory.conectar();

			String sql = "INSERT INTO avaliacao(nota, data, comentario,avaliacao_usuario_id, entrega_id) VALUES (?,?,?,?)";
			
			PreparedStatement ps = this.conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setDouble(1, avaliacao.getNota());
			ps.setString(2, avaliacao.getDtAvaliacao());
			ps.setString(3, avaliacao.getComentarios());
			ps.setInt(4, avaliacao.getAluno().getId());
			ps.setInt(5, avaliacao.getEntrega().getId());

			ps.execute();
			ps.close();

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
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	public Avaliacao consultar(int id) {

		try {
			String sql = "SELECT * FROM avaliacao WHERE id= ?";
			PreparedStatement ps = this.conexao.prepareStatement(sql);

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id"));
				avaliacao.setNota(rs.getDouble("nota"));
				avaliacao.setDtAvaliacao(rs.getString("data"));
				avaliacao.setComentarios(rs.getString("comentario"));
				avaliacao.getAluno().setId(rs.getInt("aluno_usuario_id"));
				avaliacao.getEntrega().setId(rs.getInt("entregaId"));

				return avaliacao;
			} else
				return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM avaliacao WHERE id = ?";

			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void alterar(Avaliacao novo) {
		try {
			String sql = "UPDATE avaliacao SET nota = ?, data = ?, comentario = ? WHERE aluno_usuario_id = ? AND entrega_id =?";
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setDouble(1, novo.getNota());
			ps.setString(2, novo.getDtAvaliacao());
			ps.setString(3, novo.getComentarios());
			ps.setInt(4, novo.getAluno().getId());
			ps.setInt(5, novo.getEntrega().getId());

			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Avaliacao> listarAvaliacoes() {
		String sqlSelect = "SELECT * FROM avaliacao";
		ArrayList<Avaliacao> lista = new ArrayList<>();
		try {
			this.conexao = ConnectionFactory.conectar();
			PreparedStatement stm = conexao.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Avaliacao avaliacao = new Avaliacao();
				Aluno aluno = new Aluno();
				Entrega entrega = new Entrega();
				avaliacao.setId(rs.getInt(1));
				avaliacao.setNota(rs.getDouble(2));
				avaliacao.setDtAvaliacao(rs.getString(3));
				avaliacao.setComentarios(rs.getString(4));
				aluno.setId(rs.getInt(5));
				avaliacao.setAluno(aluno);
				entrega.setId(rs.getInt(6));
				avaliacao.setEntrega(entrega);
				lista.add(avaliacao);
			}
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package br.usjt.web.projetopi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static Connection conectar() throws SQLException {

		return DriverManager.getConnection("jdbc:mysql://localhost/projetoPi?useSSL=false&useTimezone=true&serverTimezone=UTC", "user", "user");
	}
}

/*package br.usjt.web.clienteDAO;

import java.sql.*;

public class ConnectionFactory {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection conectar() throws SQLException{
		
		return DriverManager.getConnection("jdbc:mysql://localhost/banco?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "root");
	
	}
}


*/
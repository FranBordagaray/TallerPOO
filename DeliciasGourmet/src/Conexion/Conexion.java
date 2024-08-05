package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection cx = null;

	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			cx = DriverManager.getConnection("jdbc:sqlite:DeliciasGourmet.db");
			System.out.println("Conectado correctamente");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cx;
	}

	public void Desconectar() {
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		Conexion con = new Conexion();
		
		con.conectar();
	}
}
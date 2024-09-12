package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection cx = null;

	// Funcion para conectarse a la base de datos
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
	
	// Funcion para desconectarse a la base de datos
	public void Desconectar() {
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
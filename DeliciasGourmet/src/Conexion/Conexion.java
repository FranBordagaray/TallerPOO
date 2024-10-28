package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos.
 * Esta clase se encarga de establecer y mantener la conexión
 * con la base de datos utilizada por la aplicación.
 */
public class Conexion {
	Connection cx = null;

	/**
	 * Establece una conexión con la base de datos SQLite "DeliciasGourmet.db".
	 * <p>
	 * Utiliza el controlador JDBC de SQLite para conectar con la base de datos. 
	 * Si la conexión es exitosa, imprime un mensaje de confirmación en la consola.
	 * Si ocurre una excepción, muestra el stack trace de error.
	 * 
	 * @return un objeto {@link Connection} que representa la conexión a la base de datos,
	 *         o {@code null} si ocurre un error en la conexión.
	 */
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
	
	
	/**
	 * Cierra la conexión activa con la base de datos.
	 * <p>
	 * Intenta cerrar la conexión almacenada en el objeto {@code cx}. 
	 * Si ocurre una excepción al cerrar la conexión, imprime el stack trace de error.
	 * 
	 */
	public void Desconectar() {
	    try {
	        if (cx != null && !cx.isClosed()) {
	            cx.close();
	            System.out.println("Desconexión exitosa");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
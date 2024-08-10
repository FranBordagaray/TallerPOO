package Controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.Cliente;
import Modelo.Sesion;

public class ClienteControlador {
	Conexion cx;
	private Connection connection;

	public ClienteControlador() {
		cx = new Conexion();
	}

	public boolean crearCuenta(Cliente cliente) {
		PreparedStatement ps = null;

		try {
			ps = cx.conectar().prepareStatement("INSERT INTO Cliente VALUES(null, ?,?,?,?,?,?,?)");
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setString(3, cliente.getDomicilio());
			ps.setString(4, cliente.getTelefono());
			ps.setString(5, cliente.getEmail());
			ps.setString(6, cliente.getUsuario());
			ps.setString(7, convertirSHA256(cliente.getContrasenia()));
			ps.executeUpdate();
			System.out.println("Cuenta creada con exito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al crear cuenta!");
			return false;
		}
	}
	
	public void actualizarCliente(Cliente cliente) throws SQLException {
	    String sql = "UPDATE Cliente SET email = ?, telefono = ? WHERE idCliente = ?";
	    PreparedStatement stmt = null;

	    try {
	        // Inicializar la conexión si no ha sido establecida
	        connection = cx.conectar();

	        stmt = connection.prepareStatement(sql);
	        stmt.setString(1, cliente.getEmail());
	        stmt.setString(2, cliente.getTelefono());
	        stmt.setInt(3, cliente.getIdCliente());  
	        stmt.executeUpdate();

	        System.out.println("Cliente actualizado exitosamente.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al actualizar el cliente.");
	    } finally {
	        // Cerrar el PreparedStatement y la conexión
	        if (stmt != null) stmt.close();
	        if (connection != null) connection.close();
	    }
	}
	

	// Funcion para cifrar contraseñas
	public String convertirSHA256(String contrasenia) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		byte[] hash = md.digest(contrasenia.getBytes());
		StringBuffer sb = new StringBuffer();

		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	// Funcion para iniciar sesion
	public boolean iniciarSesion(String usuario, String contrasenia) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = cx.conectar().prepareStatement(
					"SELECT idCliente, nombre, apellido, domicilio, telefono, email, usuario, contrasenia FROM Cliente WHERE usuario = ?");
			ps.setString(1, usuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				String contraseniaCifrada = rs.getString("contrasenia");
				String contraseniaIngresada = convertirSHA256(contrasenia);
				if (contraseniaCifrada.equals(contraseniaIngresada)) {
					Cliente cliente = new Cliente();
					cliente.setIdCliente(rs.getInt("idCliente"));
					cliente.setNombre(rs.getString("nombre"));
					cliente.setApellido(rs.getString("apellido"));
					cliente.setDomicilio(rs.getString("domicilio"));
					cliente.setTelefono(rs.getString("telefono"));
					cliente.setEmail(rs.getString("email"));
					cliente.setUsuario(rs.getString("usuario"));
					cliente.setContrasenia(contraseniaCifrada);

					Sesion.setClienteActual(cliente);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


}
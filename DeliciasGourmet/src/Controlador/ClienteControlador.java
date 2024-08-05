package Controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.Cliente;

public class ClienteControlador {
	Conexion cx;

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
}
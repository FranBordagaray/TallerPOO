package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;

import Modelo.Complementos.Comprobante;

public class ComprobanteControlador {

	Conexion cx;
	private Connection connection;

	public ComprobanteControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Funcion para cargar un Comprobante
	public boolean crearComprobante(Comprobante comprobante) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO Comprobante VALUES(null,?,?,?,?,?)");
			ps.setString(1, comprobante.getFecha());
			ps.setString(2, comprobante.getHora());
			ps.setFloat(3, comprobante.getImporte());
			ps.setInt(4, comprobante.getIdReserva());
			ps.setInt(5, comprobante.getIdTarjeta());
			ps.executeUpdate();
			System.out.println("Tarjeta ingresada con exito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al ingresar tarjeta!");
			return false;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Funcion que obtiene el id de comprobante que se creo, el ultimo en la tabla
	public int obtenerUltimoIdComprobante() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idComprobante = -1;
		try {
			ps = connection.prepareStatement("SELECT MAX(idComprobante) FROM Comprobante");
			rs = ps.executeQuery();
			if (rs.next()) {
				idComprobante = rs.getInt(1);
			}
			System.out.println("Último ID de tarjeta obtenido con éxito: " + idComprobante);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al obtener el último ID de tarjeta!");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idComprobante;
	}

}

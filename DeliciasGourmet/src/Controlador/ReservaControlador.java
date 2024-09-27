package Controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.HistorialReserva;
import Modelo.Reserva;


public class ReservaControlador {
	
	Conexion cx;
	private Connection connection;
	
  	public ReservaControlador() {
        cx = new Conexion();
    }
	
  	// Función para crear una Reserva
	public boolean crearReserva(Reserva reserva) throws SQLException {
		PreparedStatement ps = null;

		try {
			connection = cx.conectar();
			ps = connection.prepareStatement("INSERT INTO Reserva VALUES(null,?,?,?,?,?,?,?,?)");
			ps.setInt(1, reserva.getIdCliente()); 
			ps.setString(2, reserva.getFecha());
			ps.setString(3, reserva.getHora());
			ps.setInt(4, reserva.getIdMesa());
			ps.setString(5, reserva.getComentario());
			ps.setString(6, reserva.getDispocicionMesa());
			ps.setInt(7, reserva.getEstado());
			ps.setInt(8, reserva.getIdServicio());
			
			ps.executeUpdate();
			System.out.println("Reserva realizada con exito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al Reservar!");
			return false;
		} finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
	}
	
	// Función para verificar si una mesa está ocupada en una fecha y hora específicas
    public boolean verificarMesaOcupada(int idMesa, String fecha, String hora) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = cx.conectar();
            ps = connection.prepareStatement("SELECT COUNT(*) FROM Reserva WHERE idMesa = ? AND fecha = ? AND hora = ?");
            ps.setInt(1, idMesa);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al verificar la mesa ocupada!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    // Función para obtener el historial de Reservas de un cliente
    public List<HistorialReserva> obtenerHistorialPorCliente(int idCliente) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HistorialReserva> historial = new ArrayList<>();

        try {
            connection = cx.conectar();
            ps = connection.prepareStatement(
                "SELECT r.fecha, r.hora, r.idMesa, m.capacidad, m.ubicacion, r.comentario " +
                "FROM Reserva r " +
                "JOIN MesaPrecargada m ON r.idMesa = m.idMesa " +
                "WHERE r.idCliente = ?"
            );
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                HistorialReserva reserva = new HistorialReserva(
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getInt("idMesa"),
                    rs.getInt("capacidad"),
                    rs.getString("ubicacion"),
                    rs.getString("comentario")
                );
                historial.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el historial de reservas por cliente!");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }

        return historial;
    }

    // Funcion para cargar el combo solamente con las mesas que el ciente reservo
    public List<Integer> obtenerMesasReservadasPorCliente(int idCliente) throws SQLException {
        List<Integer> mesasReservadas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = cx.conectar();
            ps = connection.prepareStatement(
                "SELECT DISTINCT r.idMesa " +
                "FROM Reserva r " +
                "WHERE r.idCliente = ?"
            );
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                mesasReservadas.add(rs.getInt("idMesa"));
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }

        return mesasReservadas;
    }

}
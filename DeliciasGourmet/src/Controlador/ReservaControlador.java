package Controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Cliente.HistorialReserva;
import Modelo.Complementos.Reserva;
import Modelo.Empleado.Reportes;

public class ReservaControlador {
	
	Conexion cx;
	private Connection connection;
	
public ReservaControlador() {
        cx = new Conexion();
        connection = cx.conectar();
    }
	
  	// Función para crear una Reserva
	public boolean crearReserva(Reserva reserva){
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO Reserva VALUES(null,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, reserva.getIdCliente()); 
			ps.setString(2, reserva.getFecha());
			ps.setString(3, reserva.getHora());
			ps.setInt(4, reserva.getIdMesa());
			ps.setString(5, reserva.getComentario());
			ps.setString(6, reserva.getDispocicionMesa());
			ps.setInt(7, reserva.getEstado());
			ps.setInt(8, reserva.getIdServicio());
			ps.setString(9, reserva.getTemporada());
			
			ps.executeUpdate();
			System.out.println("Reserva realizada con exito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al Reservar!");
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
	
	// Función para verificar si una mesa está ocupada en una fecha y hora específicas
    public boolean verificarMesaOcupada(int idMesa, String fecha, String hora) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    // Función para obtener el historial de Reservas de un cliente
    public List<HistorialReserva> obtenerHistorialPorCliente(int idCliente){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HistorialReserva> historial = new ArrayList<>();
        try {
            ps = connection.prepareStatement(
                "SELECT r.fecha, r.hora, r.idMesa, m.capacidad, m.ubicacion, r.comentario, r.estado " +
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
                    rs.getString("comentario"),
                    rs.getInt("estado")
                );
                historial.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el historial de reservas por cliente!");
        }
        return historial;
    }

    // Funcion para cargar el combo solamente con las mesas que el ciente reservo
    public List<Integer> obtenerMesasReservadasPorCliente(int idCliente) throws SQLException {
        List<Integer> mesasReservadas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mesasReservadas;
    }
    
 	// Funcion para cargar el combo solamente con las mesas que el ciente reservo
    public List<Integer> obtenerEstadoReservasPorCliente(int idCliente) throws SQLException {
        List<Integer> estadoReservas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(
                "SELECT DISTINCT r.estado " +
                "FROM Reserva r " +
                "WHERE r.idCliente = ?"
            );
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
            	estadoReservas.add(rs.getInt("estado"));
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estadoReservas;
    }
    
    // Función para obtener el historial de Reservas de un cliente
    public List<Reportes> obtenerHistorialDeReservas(String dateDesde, String dateHasta){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reportes> reporte = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT c.nombre, c.apellido, fecha, hora, m.capacidad, m.ubicacion, comentario "
            		+ "FROM Reserva r JOIN Cliente c ON r.idCliente = c.idCliente JOIN MesaPrecargada m ON r.idMesa = m.idMesa WHERE r.fecha BETWEEN ? AND ?");
            ps.setString(1, dateDesde);
            ps.setString(2, dateHasta);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Reportes reportes = new Reportes(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getInt("capacidad"),
                        rs.getString("ubicacion"),
                        rs.getString("comentario") 
                );
                reporte.add(reportes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el historial de reservas por cliente!");
        }
        return reporte;
    }
    
    // Función para obtener el historial de comensales por temporada
    public List<Reportes> obtenerHistorialComensalesPorTemporada(String Temporada){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reportes> reporte = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT count(DISTINCT idReserva) AS totalReservas , sum(m.capacidad) AS totalCapacidad "
                + "FROM Reserva r JOIN MesaPrecargada m ON r.idMesa = m.idMesa WHERE Temporada = ?");
            ps.setString(1, Temporada);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Reportes reportes = new Reportes(
                        rs.getInt("totalReservas"),
                        rs.getInt("totalCapacidad") 
                );
                reporte.add(reportes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el historial de reservas por cliente!");
        }
        return reporte;
    }
	
}
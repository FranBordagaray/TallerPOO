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
			ps = connection.prepareStatement("INSERT INTO Reserva VALUES(null,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, reserva.getIdCliente()); 
			ps.setString(2, reserva.getFecha());
			ps.setString(3, reserva.getHora());
			ps.setInt(4, reserva.getIdMesa());
			ps.setString(5, reserva.getComentario());
			ps.setString(6, reserva.getDispocicionMesa());
			ps.setInt(7, reserva.getEstado());
			ps.setInt(8, reserva.getIdServicio());
			ps.setInt(9, reserva.getIdComprobante());
			ps.setString(10, reserva.getTemporada());
			
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
                "SELECT r.idReserva, r.fecha, r.hora, r.idMesa, m.capacidad, m.ubicacion, r.comentario, r.estado " +
                "FROM Reserva r " +
                "JOIN MesaPrecargada m ON r.idMesa = m.idMesa " +
                "WHERE r.idCliente = ?"
            );
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                HistorialReserva reserva = new HistorialReserva(
                	rs.getInt("idReserva"),
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
    
    //Funcion para obtener actualizar la reserva, y asignarle su comprobante
    public boolean actualizarComprobante(int idReserva, int idComprobante) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE Reserva SET idComprobante = ? WHERE idReserva = ?");
            ps.setInt(1, idComprobante); 
            ps.setInt(2, idReserva);     
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Comprobante actualizado con éxito!");
                return true;
            } else {
                System.out.println("No se encontró la reserva para actualizar el comprobante.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el comprobante!");
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
    
    //Funcion que buscar una reserva y retorna su id
    public int buscarIdReserva(Reserva reserva) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idReserva = -1;
        try {
            ps = connection.prepareStatement("SELECT idReserva FROM Reserva WHERE idCliente = ? AND fecha = ? AND hora = ? AND idMesa = ? AND temporada = ?");
            ps.setInt(1, reserva.getIdCliente());
            ps.setString(2, reserva.getFecha());
            ps.setString(3, reserva.getHora());
            ps.setInt(4, reserva.getIdMesa());
            ps.setString(5, reserva.getTemporada());

            rs = ps.executeQuery();

            if (rs.next()) {
                idReserva = rs.getInt("idReserva");  
            }
            System.out.println("Reserva encontrada con éxito, ID: " + idReserva);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al buscar la reserva!");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
         
        }

        return idReserva;
    }

    // Funcion para cancelar una Reserva
    public void cancelarReserva(int idSeleccionado) {
    	PreparedStatement ps = null;
    	try {
    		ps = connection.prepareStatement("UPDATE Reserva SET estado = 0 WHERE idReserva = ?");
    		ps.setInt(1, idSeleccionado);
    		ps.executeUpdate();
    	} catch (SQLException e) {
	        e.printStackTrace();
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
    
    // Funcion para eliminar la mesa de la reserva cancelada
    public void eliminarMesa(int idReserva) {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int idMesa;
    	int idServicio;
    	try {
    		ps = connection.prepareStatement("SELECT idMesa, idServicio FROM Reserva WHERE idReserva = ?");
    		ps.setInt(1, idReserva);
    		rs = ps.executeQuery();
    		
    		if (rs.next()) {
             idMesa = rs.getInt("idMesa");
             idServicio = rs.getInt("idServicio");
             
             
            ps = connection.prepareStatement("DELETE FROM Mesa WHERE idMesa = ? AND idServicio = ?");
            
            ps.setInt(1, idMesa);
            ps.setInt(2, idServicio);
            ps.executeUpdate();
             System.out.println("Hola");
    		}        
                
    	} catch (SQLException e) {
	        e.printStackTrace();
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
}
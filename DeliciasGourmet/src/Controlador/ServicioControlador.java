package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.Conexion;
import Modelo.Reserva;
import Modelo.Servicio;

public class ServicioControlador { 

    Conexion cx;
    private Connection connection;

    public ServicioControlador() {
        cx = new Conexion();
    }

    // Función para registrar un servicio con verificación previa
    public boolean registrarServicio(Servicio servicio) {
    	 if (verficarServicio(servicio)) {
             System.out.println("El servicio ya existe en esa fecha y hora. No se puede registrar.");
             return false;
         } else {
             int idServicio = crearServicio(servicio);
             if (idServicio != -1) {
                 System.out.println("Servicio registrado con éxito, ID: " + idServicio);
                 return true;
             } else {
                 System.out.println("Error al registrar el servicio.");
                 return false;
             }
         }
    }

    // Función para registrar un servicio en la base de datos
    public int crearServicio(Servicio servicio) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idServicio = -1;

        try {
            connection = cx.conectar();
            ps = connection.prepareStatement("INSERT INTO Servicio VALUES (null, ?, ?, ?, ?)");
            ps.setString(1, servicio.getFecha());
            ps.setString(2, servicio.getHoraInicio());
            ps.setString(3, servicio.getHoraFin());
            ps.setInt(4, servicio.getEventoPrivado());
            ps.executeUpdate();

            ps = connection.prepareStatement("SELECT last_insert_rowid()");
            rs = ps.executeQuery();
            if (rs.next()) {
                idServicio = rs.getInt(1);
            }
            
            System.out.println("Servicio registrado con éxito!");
            return idServicio;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el servicio!");
            return -1;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Función para verificar si un servicio ya existe en una fecha y hora específicas
    public boolean verficarServicio(Servicio servicio) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = cx.conectar();
            
            ps = connection.prepareStatement("SELECT COUNT(*) FROM Servicio WHERE fecha = ? AND horaInicio = ?");
            ps.setString(1, servicio.getFecha());
            ps.setString(2, servicio.getHoraInicio());
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al verificar el servicio!");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para buscar un servicio basado en una reserva
    public int buscarServicioPorReserva(Reserva reserva) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idServicio = -1;

        try {
            connection = cx.conectar();
            ps = connection.prepareStatement("SELECT idServicio FROM Servicio WHERE fecha = ? AND horaInicio = ?");
            ps.setString(1, reserva.getFecha()); 
            String horaInicio = reserva.getHora().split(" - ")[0];
            ps.setString(2, horaInicio); 
            rs = ps.executeQuery();

            if (rs.next()) {
                idServicio = rs.getInt("idServicio"); 
            } else {
                System.out.println("No se encontró ningún servicio para la reserva dada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al buscar el servicio para la reserva.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return idServicio;
    }

    

}
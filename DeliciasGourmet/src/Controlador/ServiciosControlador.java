package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.Conexion;
import Modelo.Servicio;

public class ServiciosControlador {

    private Conexion cx;

    public ServiciosControlador() {
        cx = new Conexion();
    }

    // Función para registrar un servicio (reserva de mesa en un horario específico)
    public boolean crearServicio(Servicio servicio) {
        // Primero verificar la disponibilidad antes de intentar crear el servicio
        if (verificarDisponibilidad(servicio.getIdMesa(), servicio.getFecha(), servicio.getHora())) {
            System.out.println("La mesa ya está reservada en la fecha y hora especificadas.");
            return false; // Si ya existe una reserva, no se puede crear otra
        }

        String sql = "INSERT INTO Servicios (idMesa, fecha, hora) VALUES (?, ?, ?)";
        try (Connection conn = cx.conectar(); // Obtener conexión
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, servicio.getIdMesa());
            ps.setString(2, servicio.getFecha());
            ps.setString(3, servicio.getHora());
            ps.executeUpdate();
            System.out.println("Servicio registrado con éxito!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el servicio!");
            return false;
        }
    }

    // Función para verificar si ya existe un servicio en la misma mesa, fecha y hora
    public boolean verificarDisponibilidad(int idMesa, String fecha, String hora) {
        String sql = "SELECT COUNT(*) FROM Servicios WHERE idMesa = ? AND fecha = ? AND hora = ?";
        try (Connection conn = cx.conectar(); // Obtener conexión
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMesa);
            ps.setString(2, fecha);
            ps.setString(3, hora);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.getInt(1) > 0; // Devuelve true si hay registros
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al verificar disponibilidad!");
            return false;
        }
    }
}
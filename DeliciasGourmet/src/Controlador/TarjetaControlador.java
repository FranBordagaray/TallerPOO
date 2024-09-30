package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.Tarjeta;

public class TarjetaControlador {
    Conexion cx;
    private Connection connection;

    public TarjetaControlador() {
        cx = new Conexion();
        connection = cx.conectar();
    }

    // Funcion para ingresar tarjetas para completar una reserva
    public boolean ingresarTarjeta(Tarjeta tarjeta) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Tarjeta VALUES(null,?,?,?,?)");
            ps.setString(1, tarjeta.getTitular());
            ps.setString(2, tarjeta.getEmisor());
            ps.setString(3, tarjeta.getNroTarjeta());
            ps.setInt(4, tarjeta.getCodVerificacion());
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
}
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Mesa;

public class MesaControlador {
    Conexion cx;
    private Connection connection;

    public MesaControlador() {
        cx = new Conexion();
    }

    public List<Mesa> buscarMesasPorUbicacion(String ubicacion) throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM Mesa WHERE ubicacion = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = cx.conectar();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, ubicacion);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idMesa = rs.getInt("idMesa");
                int capacidad = rs.getInt("capacidad");
                String estado = rs.getString("estado");
                String horaReservada = rs.getString("horaReservada");

                Mesa mesa = new Mesa(idMesa, capacidad, ubicacion, estado, horaReservada);
                mesas.add(mesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        
        return mesas;
    }
}
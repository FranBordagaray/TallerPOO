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
    
    //Funcion para cargar Mesa 
    public boolean crearMesa(Mesa mesa) {
    	PreparedStatement ps = null;
        try {
        	connection = cx.conectar();
        	ps = connection.prepareStatement("INSERT INTO Mesa  VALUES (null, ?, ?, ?, ?)");
            ps.setInt(1, mesa.getCapacidad());
            ps.setString(2, mesa.getUbicacion());
            ps.setString(3, mesa.getEstado().name());
            ps.setInt(4, mesa.getIdServicio());
            ps.executeUpdate();
            System.out.println("Servicio registrado con éxito!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el servicio!");
            return false;
        }
    }
    
    // Funcion para buscar mesas por Ubicacion
    public List<Mesa> buscarMesasPorUbicacion(String ubicacion) throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = cx.conectar();
            ps = connection.prepareStatement("SELECT * FROM MesaPrecargada WHERE ubicacion = ?");
            ps.setString(1, ubicacion);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Mesa mesa = new Mesa();
            	mesa.setIdMesa(rs.getInt("idMesa"));
            	mesa.setCapacidad(rs.getInt("capacidad"));
            	mesa.setUbicacion(rs.getString("ubicacion"));
                mesas.add(mesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        return mesas;
    }
}
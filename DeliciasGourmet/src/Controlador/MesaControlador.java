package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Conexion.Conexion;
import Modelo.Empleado;
import Modelo.EnumRoles;
import Modelo.Mesa;

public class MesaControlador {
    Conexion cx;
    private Connection connection;

    public MesaControlador() {
        cx = new Conexion();
    }
    
    /* Funcion para obtener todos los empleados
    public List<Mesa> obtenerMesas() {
        List<Mesa> mesas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = cx.conectar().prepareStatement("SELECT * FROM Mesa");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Mesa mesa = new Mesa();
                mesa.setIdMesa(rs.getInt("idMesa"));
                mesa.setCapacidad(rs.getInt("capacidad"));
                mesa.setUbicacion(rs.getString("ubicacion"));
                mesa.setEstado(rs.getString("estado"));
                
                mesas.add(mesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener mesas!");
        } 
        return mesas;
    }*/
    
    // Funcion para buscar mesas por Ubicacion
    public List<Mesa> buscarMesasPorUbicacion(String ubicacion) throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM Mesa WHERE ubicacion = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = cx.conectar();
            ps = connection.prepareStatement(sql);
            ps.setString(1, ubicacion);
            rs = ps.executeQuery();

            while (rs.next()) {
            	Mesa mesa = new Mesa();
            	mesa.setIdMesa(rs.getInt("idMesa"));
            	mesa.setCapacidad(rs.getInt("capacidad"));
            	mesa.setUbicacion(rs.getString("ubicacion"));
            	mesa.setEstado(rs.getString("estado"));
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
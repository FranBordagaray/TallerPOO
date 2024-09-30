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
        connection = cx.conectar();
    }
    
    //Funcion para cargar Mesa 
    public boolean crearMesa(Mesa mesa) {
    	PreparedStatement ps = null;
        try {
        	ps = connection.prepareStatement("INSERT INTO Mesa  VALUES (?, ?, ?, ?, ?)");
        	ps.setInt(1, mesa.getIdMesa());
            ps.setInt(2, mesa.getCapacidad());
            ps.setString(3, mesa.getUbicacion());
            ps.setString(4, mesa.getEstado().name());
            ps.setInt(5, mesa.getIdServicio());
            ps.executeUpdate();
            System.out.println("Servicio registrado con éxito!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar el servicio!");
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
    
    // Funcion para buscar mesas por Ubicacion
    public List<Mesa> buscarMesasPorUbicacion(String ubicacion){
        List<Mesa> mesasP = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM MesaPrecargada WHERE ubicacion = ?");
            ps.setString(1, ubicacion);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Mesa mesa = new Mesa();
            	mesa.setIdMesa(rs.getInt("idMesa"));
            	mesa.setCapacidad(rs.getInt("capacidad"));
            	mesa.setUbicacion(rs.getString("ubicacion"));
                mesasP.add(mesa);
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
        return mesasP;
    }
    
    // Funcion busca mesas con estado "Ocupado" en una fecha y hora
    	public List<Integer> buscarMesasOcupadasPorServicio(String fecha, String hora) {
    	    List<Integer> mesasOcupadasIds = new ArrayList<>();
    	    PreparedStatement ps = null;
    	    ResultSet rs = null;
    	    try {
    	        ps = connection.prepareStatement("SELECT idMesa FROM Reserva WHERE fecha=? AND hora=? AND estado=1");
    	        ps.setString(1,fecha);
    	        ps.setString(2,hora); 
    	        rs = ps.executeQuery();
    	        while (rs.next()) {
    	        	mesasOcupadasIds.add(rs.getInt("idMesa"));
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
    	    return mesasOcupadasIds; 
    	}
    	
        // Funcion para buscar mesas por Ubicacion
        public int filtrarCapacidad(int idMesa) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            int capacidad = 0;
            try {
                ps = connection.prepareStatement("SELECT capacidad FROM MesaPrecargada WHERE idMesa = ?");
                ps.setInt(1, idMesa);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                	capacidad = rs.getInt("capacidad");
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
            return capacidad;
      } 
}
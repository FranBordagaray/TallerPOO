package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.Cliente.Tarjeta;

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
    
    //Funcion que te retorna el id de la ultima tarjeta ingresada
    public int obtenerUltimoIdTarjeta() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idTarjeta = -1; 
        try {
            ps = connection.prepareStatement("SELECT MAX(idTarjeta) FROM Tarjeta");
            rs = ps.executeQuery();
            if (rs.next()) {
                idTarjeta = rs.getInt(1);
            }
            System.out.println("Último ID de tarjeta obtenido con éxito: " + idTarjeta);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el último ID de tarjeta!");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return idTarjeta;
    }
    
 // Función para obtener los datos de una tarjeta mediante el número de tarjeta como String
 	public Tarjeta obtenerDatosTarjetaConNumTarjeta(String nroTarjeta) {
 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		Tarjeta tarjeta = null;

 		try {
 			// Convertir el número de tarjeta a String si no lo es
 			String nroTarjetaStr = String.valueOf(nroTarjeta);

 			// Cambiar la consulta para usar el número de tarjeta
 			ps = connection.prepareStatement("SELECT * FROM Tarjeta WHERE nroTarjeta = ?");
 			ps.setString(1, nroTarjetaStr); // Establecer el número de tarjeta como parámetro
 			rs = ps.executeQuery();

 			if (rs.next()) {
 				tarjeta = new Tarjeta();
 				tarjeta.setTitular(rs.getString("titular"));
 				tarjeta.setEmisor(rs.getString("emisor"));
 				tarjeta.setNroTarjeta(rs.getString("nroTarjeta"));

 				// Obtener y establecer el código de verificación
 				tarjeta.setCodVerificacion(rs.getInt("codVerificacion"));

 				System.out.println("Tarjeta encontrada con éxito: " + nroTarjetaStr);
 			} else {
 				System.out.println("No se encontró una tarjeta para el número: " + nroTarjetaStr);
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 			System.out.println("Error al obtener la tarjeta");
 		} finally {
 			if (ps != null) {
 				try {
 					ps.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 			}
 		}
 		return tarjeta;
 	}

	// Funcion para obtener los datos de una tarjeta mediante el id
    public Tarjeta obtenerDatosTarjeta(int idTarjeta) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tarjeta tarjeta = null;
        
        try {
        	ps = connection.prepareStatement("SELECT * FROM Tarjeta WHERE idTarjeta = ?");
        	ps.setInt(1, idTarjeta);
        	rs = ps.executeQuery();
        	
        	if (rs.next()) {
	            tarjeta = new Tarjeta();
	            tarjeta.setTitular(rs.getString("titular"));
	            tarjeta.setEmisor(rs.getString("emisor"));
                tarjeta.setNroTarjeta(rs.getString("nroTarjeta"));
                
                System.out.println("Encontrado con éxito la Tarjeta: " + idTarjeta);
	        } else {
	            System.out.println("No se encontró una Tarjeta para la tarjeta ID: " + idTarjeta);
	        }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener un tarjeta");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tarjeta;
    }
    
 // Función para obtener los datos de una tarjeta mediante el id del comprobante
 	public Tarjeta obtenerTarjetaPorComprobante(int idComprobante) {
 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		Tarjeta tarjeta = null;

 		try {
 			// Primero, obtén el idTarjeta asociado al comprobante
 			ps = connection.prepareStatement("SELECT idTarjeta FROM Comprobante WHERE idComprobante = ?");
 			ps.setInt(1, idComprobante);
 			rs = ps.executeQuery();

 			if (rs.next()) {
 				int idTarjeta = rs.getInt("idTarjeta");

 				// Ahora, con el idTarjeta, obtén los detalles de la tarjeta
 				tarjeta = obtenerDatosTarjeta(idTarjeta);
 			} else {
 				System.out.println("No se encontró un comprobante con ID: " + idComprobante);
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 			System.out.println("Error al obtener la tarjeta asociada al comprobante ID: " + idComprobante);
 		} finally {
 			if (ps != null) {
 				try {
 					ps.close();
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 			}
 		}

 		return tarjeta;
 	}
}
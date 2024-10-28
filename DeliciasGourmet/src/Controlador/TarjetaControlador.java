package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.Cliente.Tarjeta;

/**
 * Controlador para gestionar las operaciones relacionadas con las tarjetas.
 * Proporciona métodos para ingresar tarjetas y realizar otras operaciones
 * en la base de datos.
 */
public class TarjetaControlador {
    Conexion cx;
    private Connection connection;

    /**
     * Ingresa una tarjeta en la base de datos para completar una reserva.
     *
     * Este método inserta un nuevo registro de tarjeta en la tabla Tarjeta
     * de la base de datos. Si la operación se realiza con éxito, se retorna 
     * true; de lo contrario, se retorna false.
     *
     * @param tarjeta El objeto Tarjeta que se desea ingresar.
     * @return true si la tarjeta se ingresó correctamente; false en caso contrario.
     */
    public TarjetaControlador() {
        cx = new Conexion();
        connection = cx.conectar();
    }
    
    /**
     * Ingresa una tarjeta en la base de datos.
     *
     * Este método inserta un nuevo registro de tarjeta en la tabla Tarjeta
     * de la base de datos. Si la operación se realiza con éxito, se retorna 
     * true; de lo contrario, se retorna false.
     *
     * @param tarjeta El objeto Tarjeta que se desea ingresar.
     * @return true si la tarjeta se ingresó correctamente; false en caso contrario.
     */
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
    
    /**
     * Función que retorna el ID de la última tarjeta ingresada en la base de datos.
     *
     * Este método consulta la tabla Tarjeta para obtener el valor máximo del campo
     * idTarjeta. Si no hay tarjetas registradas, retorna -1.
     *
     * @return El ID de la última tarjeta ingresada, o -1 si no se encontró ninguna.
     */
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    
    /**
     * Función para obtener los datos de una tarjeta mediante el número de tarjeta.
     *
     * Este método consulta la tabla Tarjeta utilizando el número de tarjeta como criterio
     * de búsqueda y retorna un objeto Tarjeta con los detalles correspondientes.
     * Si no se encuentra la tarjeta, se retorna null.
     *
     * @param nroTarjeta El número de tarjeta como String que se desea buscar.
     * @return Un objeto Tarjeta con los datos correspondientes, o null si no se encontró.
     */
    public Tarjeta obtenerDatosTarjetaConNumTarjeta(String nroTarjeta) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tarjeta tarjeta = null;

        try {
            String nroTarjetaStr = String.valueOf(nroTarjeta);

            ps = connection.prepareStatement("SELECT * FROM Tarjeta WHERE nroTarjeta = ?");
            ps.setString(1, nroTarjetaStr);
            rs = ps.executeQuery();

            if (rs.next()) {
                tarjeta = new Tarjeta();
                tarjeta.setTitular(rs.getString("titular"));
                tarjeta.setEmisor(rs.getString("emisor"));
                tarjeta.setNroTarjeta(rs.getString("nroTarjeta"));
                tarjeta.setCodVerificacion(rs.getInt("codVerificacion"));

                System.out.println("Tarjeta encontrada con éxito: " + nroTarjetaStr);
            } else {
                System.out.println("No se encontró una tarjeta para el número: " + nroTarjetaStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la tarjeta");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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


    /**
     * Función para obtener los datos de una tarjeta mediante su ID.
     *
     * Este método consulta la tabla Tarjeta utilizando el ID de la tarjeta como criterio
     * de búsqueda y retorna un objeto Tarjeta con los detalles correspondientes.
     * Si no se encuentra la tarjeta, se retorna null.
     *
     * @param idTarjeta El ID de la tarjeta que se desea buscar.
     * @return Un objeto Tarjeta con los datos correspondientes, o null si no se encontró.
     */
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
 	        if (rs != null) {
 	            try {
 	                rs.close();
 	            } catch (SQLException e) {
 	                e.printStackTrace();
 	            }
 	        }
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

    
 	/**
 	 * Función para obtener los datos de una tarjeta mediante el ID del comprobante.
 	 *
 	 * Este método consulta la tabla Comprobante para encontrar el ID de la tarjeta
 	 * asociada al comprobante dado. Si se encuentra, se llama al método
 	 * obtenerDatosTarjeta para recuperar los datos de la tarjeta.
 	 *
 	 * @param idComprobante El ID del comprobante para el cual se desea obtener la tarjeta.
 	 * @return Un objeto Tarjeta con los datos correspondientes, o null si no se encontró.
 	 */
    public Tarjeta obtenerTarjetaPorComprobante(int idComprobante) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tarjeta tarjeta = null;

        try {
            ps = connection.prepareStatement("SELECT idTarjeta FROM Comprobante WHERE idComprobante = ?");
            ps.setInt(1, idComprobante);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idTarjeta = rs.getInt("idTarjeta");
                tarjeta = obtenerDatosTarjeta(idTarjeta);
            } else {
                System.out.println("No se encontró un comprobante con ID: " + idComprobante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la tarjeta asociada al comprobante ID: " + idComprobante);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;

import Modelo.Complementos.Comprobante;

/**
 * Clase que gestiona las operaciones relacionadas con los comprobantes.
 * Esta clase se encarga de establecer la conexión con la base de datos
 * y proporcionar métodos para manejar comprobantes de reservas.
 */
public class ComprobanteControlador {

    /** Objeto de conexión para interactuar con la base de datos. */
    private Conexion cx;
    /** Conexión a la base de datos. */
    private Connection connection;

    /** 
     * Constructor de la clase ComprobanteControlador.
     * Inicializa una nueva instancia de Conexion y establece
     * una conexión con la base de datos.
     */
    public ComprobanteControlador() {
        cx = new Conexion();
        connection = cx.conectar();
    }

    /**
     * Crea un comprobante en la base de datos con la información proporcionada.
     * 
     * @param comprobante el objeto Comprobante que contiene la información a cargar en la base de datos.
     * @return true si el comprobante fue creado con éxito, false en caso contrario.
     */
    public boolean crearComprobante(Comprobante comprobante) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Comprobante VALUES(null,?,?,?,?,?)");
            ps.setString(1, comprobante.getFecha());
            ps.setString(2, comprobante.getHora());
            ps.setFloat(3, comprobante.getImporte());
            ps.setInt(4, comprobante.getIdReserva());
            ps.setInt(5, comprobante.getIdTarjeta());
            ps.executeUpdate();
            System.out.println("Comprobante ingresado con éxito!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al ingresar comprobante!");
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
     * Obtiene el último ID de comprobante insertado en la tabla Comprobante.
     * 
     * @return el último ID de comprobante como entero. Retorna -1 si ocurre un error.
     */
    public int obtenerUltimoIdComprobante() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idComprobante = -1;
        try {
            ps = connection.prepareStatement("SELECT MAX(idComprobante) FROM Comprobante");
            rs = ps.executeQuery();
            if (rs.next()) {
                idComprobante = rs.getInt(1);
            }
            System.out.println("Último ID de comprobante obtenido con éxito: " + idComprobante);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el último ID de comprobante!");
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
        return idComprobante;
    }

    /**
     * Obtiene el comprobante asociado a una reserva específica.
     * 
     * @param idReserva el ID de la reserva cuyo comprobante se desea obtener.
     * @return el comprobante asociado a la reserva, o null si no se encuentra un comprobante.
     */
    public Comprobante obtenerComprobantePorReserva(int idReserva) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comprobante comprobante = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM Comprobante WHERE idReserva = ?");
            ps.setInt(1, idReserva);
            rs = ps.executeQuery();

            if (rs.next()) {
                comprobante = new Comprobante();
                comprobante.setIdComprobante(rs.getInt("idComprobante"));
                comprobante.setFecha(rs.getString("fecha"));
                comprobante.setHora(rs.getString("hora"));
                comprobante.setImporte(rs.getFloat("importe"));
                comprobante.setIdReserva(rs.getInt("idReserva"));
                comprobante.setIdTarjeta(rs.getInt("idTarjeta"));
                System.out.println("Comprobante encontrado con éxito para la reserva ID: " + idReserva);
            } else {
                System.out.println("No se encontró un comprobante para la reserva ID: " + idReserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el comprobante para la reserva ID: " + idReserva);
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

        return comprobante; 
    }

    /**
     * Obtiene el ID del comprobante asociado a una reserva específica.
     * 
     * @param idReserva el ID de la reserva cuyo ID de comprobante se desea obtener.
     * @return el ID del comprobante asociado a la reserva. Retorna 0 si no se encuentra un comprobante.
     */
    public int obteneridComprobante(int idReserva) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int comprobante = 0;

        try {
            ps = connection.prepareStatement("SELECT * FROM Comprobante WHERE idReserva = ?");
            ps.setInt(1, idReserva);
            rs = ps.executeQuery();

            if (rs.next()) {
                comprobante = rs.getInt("idComprobante");
                System.out.println("ID de comprobante obtenido: " + comprobante);
            } else {
                System.out.println("No se encontró un comprobante.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el comprobante para la reserva ID: " + idReserva);
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

        return comprobante;
    }

}

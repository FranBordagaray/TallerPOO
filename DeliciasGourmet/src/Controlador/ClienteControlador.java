package Controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Cliente.SesionCliente;
import Modelo.Cliente.Cliente;

/**
 * Clase que controla las operaciones relacionadas con el cliente.
 * Esta clase se encarga de gestionar la conexión con la base de datos
 * y realizar operaciones específicas para los clientes.
 */
public class ClienteControlador {
    
    private Conexion cx;  // Objeto de conexión a la base de datos.
    private Connection connection;  // Conexión a la base de datos.

    /**
     * Constructor de la clase ClienteControlador.
     * Inicializa la conexión a la base de datos a través del objeto Conexion.
     */
    public ClienteControlador() {
        cx = new Conexion();
        connection = cx.conectar();
    }


	/**
	 * Crea una cuenta de cliente en la base de datos.
	 * <p>
	 * Inserta un nuevo registro en la tabla Cliente con los datos proporcionados.
	 * La contraseña se cifra antes de guardarla en la base de datos.
	 * 
	 * @param cliente el objeto {@link Cliente} que contiene los datos del nuevo cliente.
	 * @return {@code true} si la cuenta se creó con éxito, {@code false} si ocurrió un error.
	 * @throws SQLException si ocurre un error al intentar crear la cuenta en la base de datos.
	 */
	public boolean crearCuenta(Cliente cliente) throws SQLException {
	    PreparedStatement ps = null;

	    try {
	        ps = connection.prepareStatement(
	                "INSERT INTO Cliente (nombre, apellido, domicilio, telefono, email, contrasenia, codRecuperacion) VALUES (?, ?, ?, ?, ?, ?, ?)");
	        ps.setString(1, cliente.getNombre());
	        ps.setString(2, cliente.getApellido());
	        ps.setString(3, cliente.getDomicilio());
	        ps.setString(4, cliente.getTelefono());
	        ps.setString(5, cliente.getEmail());
	        ps.setString(6, convertirSHA256(cliente.getContrasenia()));
	        ps.setString(7, cliente.generarCodigoRecuperacion());
	        ps.executeUpdate();
	        System.out.println("Cuenta creada con éxito!");
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al crear cuenta!");
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
	 * Verifica si un correo electrónico ya está en uso en la base de datos.
	 * 
	 * @param email el correo electrónico a verificar.
	 * @return {@code true} si el correo ya está en uso, {@code false} si está disponible.	
	 */
	public boolean verificarEmailExistente(String email) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT COUNT(*) FROM Cliente WHERE email = ?");
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	    return false;
	}

	/**
	 * Actualiza los datos de un cliente en la base de datos.
	 * 
	 * @param cliente el objeto {@link Cliente} con los datos actualizados.	
	 */
	public void actualizarCliente(Cliente cliente) {
	    PreparedStatement ps = null;
	    try {
	        ps = connection.prepareStatement("UPDATE Cliente SET email = ?, telefono = ? WHERE idCliente = ?");
	        ps.setString(1, cliente.getEmail());
	        ps.setString(2, cliente.getTelefono());
	        ps.setInt(3, cliente.getIdCliente());
	        ps.executeUpdate();
	        System.out.println("Cliente actualizado exitosamente.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al actualizar el cliente.");
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
	 * Inicia sesión para un cliente comprobando sus credenciales.
	 * <p>
	 * Verifica si el correo y la contraseña coinciden con un registro en la base de datos.
	 * Si la verificación es exitosa, establece al cliente en sesión.
	 * 
	 * @param email el correo electrónico del cliente.
	 * @param contrasenia la contraseña del cliente sin cifrar.
	 * @return {@code true} si las credenciales son correctas y se inicia sesión; {@code false} en caso contrario.	 
	 */
	public boolean iniciarSesion(String email, String contrasenia) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement(
	                "SELECT idCliente, nombre, apellido, domicilio, telefono, email, contrasenia FROM Cliente WHERE email = ?");
	        ps.setString(1, email);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String contraseniaCifrada = rs.getString("contrasenia");
	            String contraseniaIngresada = convertirSHA256(contrasenia);
	            if (contraseniaCifrada.equals(contraseniaIngresada)) {
	                Cliente cliente = new Cliente();
	                cliente.setIdCliente(rs.getInt("idCliente"));
	                cliente.setNombre(rs.getString("nombre"));
	                cliente.setApellido(rs.getString("apellido"));
	                cliente.setDomicilio(rs.getString("domicilio"));
	                cliente.setTelefono(rs.getString("telefono"));
	                cliente.setEmail(rs.getString("email"));
	                cliente.setContrasenia(contraseniaCifrada);
	                SesionCliente.setClienteActual(cliente);
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	    return false;
	}


	/**
	 * Recupera la contraseña de un cliente según su correo electrónico registrado.
	 * Nota: Por razones de seguridad, no se recomienda devolver contraseñas directamente en texto plano.
	 *
	 * @param email El correo electrónico del cliente.
	 * @return La contraseña cifrada del cliente si se encuentra, o null si el correo no está registrado.
	 */
	public String recuperarClavePorCorreo(String email) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String contrasenia = null;
	    try {
	        ps = connection.prepareStatement("SELECT contrasenia FROM Cliente WHERE email = ?");
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            contrasenia = rs.getString("contrasenia");
	        } else {
	            System.out.println("Correo no encontrado.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	    return contrasenia;
	}

	/**
	 * Obtiene el código de recuperación asociado a un cliente según su correo electrónico.
	 *
	 * @param email El correo electrónico del cliente.
	 * @return El código de recuperación del cliente o null si no se encuentra el correo.
	 */
	public String obtenerCodigoRecuperacion(String email) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String codRecuperacion = null;
	    try {
	        ps = connection.prepareStatement("SELECT codRecuperacion FROM Cliente WHERE email = ?");
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            codRecuperacion = rs.getString("codRecuperacion");
	        } else {
	            System.out.println("Cliente no encontrado.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	    return codRecuperacion;
	}

	/**
	 * Actualiza la contraseña de un cliente después de ingresar el código de recuperación.
	 *
	 * @param email             El correo electrónico del cliente.
	 * @param nuevaContrasenia  La nueva contraseña en texto plano que será cifrada.
	 * @param codigoRecuperacion El código de recuperación que verifica la identidad del cliente.
	 * @return true si la contraseña fue actualizada exitosamente, false si el cliente no fue encontrado
	 *         o el código de recuperación es inválido.
	 */
	public boolean recuperarContrasena(String email, String nuevaContrasenia, String codigoRecuperacion) {
	    PreparedStatement ps = null;
	    try {
	        ps = connection.prepareStatement("UPDATE Cliente SET contrasenia = ?, codRecuperacion = NULL WHERE email = ?");
	        ps.setString(1, convertirSHA256(nuevaContrasenia));
	        ps.setString(2, email);

	        int filasActualizadas = ps.executeUpdate();
	        if (filasActualizadas > 0) {
	            System.out.println("Cliente actualizado exitosamente.");
	            return true;
	        } else {
	            System.out.println("No se encontró ningún cliente con ese email.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al actualizar el cliente.");
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return false;
	}

	/**
	 * Obtiene una lista con todos los clientes registrados en la base de datos.
	 *
	 * @return Una lista de objetos Cliente, vacía si no hay registros.
	 */
	public List<Cliente> obtenerTodosLosClientes() {
	    List<Cliente> listaClientes = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = connection.prepareStatement("SELECT * FROM Cliente");
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("idCliente"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setDomicilio(rs.getString("domicilio"));
	            cliente.setTelefono(rs.getString("telefono"));
	            cliente.setEmail(rs.getString("email"));
	            cliente.setContrasenia(rs.getString("contrasenia"));
	            listaClientes.add(cliente);
	        }
	        System.out.println("Clientes obtenidos con éxito!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener la lista de clientes.");
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
	    return listaClientes;
	}

	/**
	 * Cifra la contraseña proporcionada en SHA-256.
	 *
	 * @param contrasenia La contraseña en texto plano.
	 * @return La contraseña cifrada en SHA-256 o null si ocurre un error.
	 */
	public String convertirSHA256(String contrasenia) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-256");
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null;
	    }
	    byte[] hash = md.digest(contrasenia.getBytes());
	    StringBuffer sb = new StringBuffer();

	    for (byte b : hash) {
	        sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}

}
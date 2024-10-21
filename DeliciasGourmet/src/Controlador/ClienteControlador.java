package Controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.Cliente.SesionCliente;
import Modelo.Cliente.Cliente;

public class ClienteControlador {
	Conexion cx;
	private Connection connection;

	public ClienteControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Función para crear cuentas de clientes
    public boolean crearCuenta(Cliente cliente) {
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("INSERT INTO Cliente (nombre, apellido, domicilio, telefono, email, contrasenia, codRecuperacion) VALUES (?, ?, ?, ?, ?, ?, ?)");
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
	
	// Función que verifica si un email ya esta en uso
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
	
	// Función para actualizar cuentas de clientes
	public void actualizarCliente(Cliente cliente){
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

	// Funcion para cifrar contraseñas
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

	// Funcion para iniciar sesion
	public boolean iniciarSesion(String email, String contrasenia) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT idCliente, nombre, apellido, domicilio, telefono, email, contrasenia FROM Cliente WHERE email = ?");
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
	
	// Método para recuperar la contraseña en función del correo electrónico ingresado
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
    
    // Función para obtener el código de recuperación
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
    
    //Funcion para actualizar la contraseña despues de ingresar el codigo de recuperacion
    public boolean recuperarContraseña(String email, String nuevaContrasenia, String codigoRecuperacion) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE Cliente SET contrasenia = ?, codRecuperacion = ? WHERE email = ?");
            ps.setString(1, convertirSHA256(nuevaContrasenia));
            ps.setString(2, codigoRecuperacion);
            ps.setString(3, email);

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
}
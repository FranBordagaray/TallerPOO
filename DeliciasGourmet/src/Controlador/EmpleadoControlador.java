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
import Modelo.Empleado.Empleado;
import Modelo.Empleado.EnumRoles;
import Modelo.Empleado.SesionEmpleado;

public class EmpleadoControlador {
	Conexion cx;
	private Connection connection;

	public EmpleadoControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Funcion para crear cuentas a empleados
	public boolean crearCuenta(Empleado empleado) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO Empleado VALUES(null, ?,?,?,?,?,?,?,?)");
			ps.setString(1, empleado.getRol().name());
			ps.setString(2, empleado.getNombre());
			ps.setString(3, empleado.getApellido());
			ps.setString(4, empleado.getDomicilio());
			ps.setString(5, empleado.getTelefono());
			ps.setString(6, empleado.getEmail());
			ps.setString(7, empleado.getUsuario());
			ps.setString(8, convertirSHA256(empleado.getContrasenia()));
			ps.executeUpdate();
			System.out.println("Cuenta creada con exito!");
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

	// Funcion para obtener todos los empleados
	public List<Empleado> obtenerEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM Empleado");
			rs = ps.executeQuery();
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("idEmpleado"));
				empleado.setRol(EnumRoles.valueOf(rs.getString("rol")));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleado.setDomicilio(rs.getString("domicilio"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setEmail(rs.getString("email"));
				empleado.setUsuario(rs.getString("usuario"));
				empleado.setContrasenia(rs.getString("contrasenia"));
				empleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al obtener empleados!");
		} finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return empleados;
	}

	// Funcion para iniciar sesion
	public boolean iniciarSesion(String usuario, String contrasenia) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT idEmpleado, Rol, nombre, apellido, domicilio, telefono, email, usuario, contrasenia FROM Empleado WHERE usuario = ?");
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				String contraseniaCifrada = rs.getString("contrasenia");
				String contraseniaIngresada = convertirSHA256(contrasenia);
				if (contraseniaCifrada.equals(contraseniaIngresada)) {
					Empleado empleado = new Empleado();
					empleado.setIdEmpleado(rs.getInt("idEmpleado"));
					empleado.setRol(EnumRoles.valueOf(rs.getString("Rol")));
					empleado.setNombre(rs.getString("nombre"));
					empleado.setApellido(rs.getString("apellido"));
					empleado.setDomicilio(rs.getString("domicilio"));
					empleado.setTelefono(rs.getString("telefono"));
					empleado.setEmail(rs.getString("email"));
					empleado.setUsuario(rs.getString("usuario"));
					empleado.setContrasenia(contraseniaCifrada);
					SesionEmpleado.setEmpleadoActual(empleado);
					System.out.println("Inicio de sesión exitoso!");
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

	// Método para eliminar un empleado por su usuario
	public boolean eliminarEmpleado(String usuario) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("DELETE FROM Empleado WHERE usuario = ?");
			ps.setString(1, usuario);
			int filasEliminadas = ps.executeUpdate();

			if (filasEliminadas > 0) {
				System.out.println("Empleado eliminado con éxito.");
				return true;
			} else {
				System.out.println("No se encontró un empleado con ese usuario.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al eliminar empleado!");
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

	// Método para obtener el rol de un empleado según su usuario
	public String obtenerRol(String usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String rol = null;
		try {
			ps = connection.prepareStatement("SELECT rol FROM Empleado WHERE usuario = ?");
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				rol = rs.getString("rol");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al obtener el rol del empleado!");
		} finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return rol;
	}
}
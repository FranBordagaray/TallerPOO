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

/**
 * Controlador para gestionar las operaciones relacionadas con empleados.
 * Este controlador permite crear cuentas de empleados y manejar la conexión
 * a la base de datos.
 */
public class EmpleadoControlador {
	Conexion cx;
	private Connection connection;

	/**
	 * Constructor de la clase EmpleadoControlador.
	 *
	 * Este constructor inicializa una nueva instancia de la clase EmpleadoControlador,
	 * que se encarga de gestionar las operaciones relacionadas con los empleados en el sistema.
	 * Al instanciar esta clase, se establece la conexión con la base de datos, lo que permite
	 * realizar consultas y operaciones CRUD sobre la tabla de empleados.
	 */
	public EmpleadoControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Funcion para crear cuentas a empleados
    /**
     * Crea una cuenta de empleado en la base de datos.
     *
     * @param empleado el objeto Empleado que contiene los datos para la cuenta.
     * @return true si la cuenta se creó con éxito, false en caso de error.
     */
    public boolean crearCuenta(Empleado empleado) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Empleado VALUES(null, ?,?,?,?,?,?,?,?,?)");
            ps.setString(1, empleado.getRol().name());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getDomicilio());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getEmail());
            ps.setString(7, empleado.getUsuario());
            ps.setString(8, convertirSHA256(empleado.getContrasenia()));
            ps.setString(9, empleado.getEstado());
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
     * Verifica si un correo electrónico ya está registrado en la base de datos.
     *
     * @param email el correo electrónico que se va a verificar.
     * @return true si el correo electrónico ya está en uso, false en caso contrario.
     */
    public boolean emailEnUso(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean emailExiste = false;

        try {
            ps = connection.prepareStatement("SELECT COUNT(*) FROM Empleado WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    emailExiste = true;
                    System.out.println("El email ya está en uso.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al verificar el email!");
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
        return emailExiste;
    }

    /**
     * Verifica si un nombre de usuario ya está registrado en la base de datos.
     *
     * @param usuario el nombre de usuario que se va a verificar.
     * @return true si el nombre de usuario ya está en uso, false en caso contrario.
     */
    public boolean usuarioEnUso(String usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean usuarioExiste = false;

        try {
            ps = connection.prepareStatement("SELECT COUNT(*) FROM Empleado WHERE Usuario = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    usuarioExiste = true;
                    System.out.println("El nombre de usuario ya está en uso.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al verificar el nombre de usuario!");
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

        return usuarioExiste;
    }



    /**
     * Obtiene una lista de todos los empleados registrados en la base de datos.
     *
     * @return una lista de objetos Empleado que representa a todos los empleados en la base de datos.
     */
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
                empleado.setEstado(rs.getString("estado"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener empleados!");
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
        return empleados;
    }

    /**
     * Inicia sesión validando las credenciales de un empleado.
     *
     * @param usuario el nombre de usuario del empleado.
     * @param contrasenia la contraseña ingresada del empleado.
     * @return true si las credenciales son correctas y se inicia sesión exitosamente, false en caso contrario.
     */
    public boolean iniciarSesion(String usuario, String contrasenia) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(
                    "SELECT idEmpleado, Rol, nombre, apellido, domicilio, telefono, email, usuario, contrasenia FROM Empleado WHERE usuario = ?");
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
     * Elimina un empleado de la base de datos según su nombre de usuario.
     *
     * @param usuario el nombre de usuario del empleado a eliminar.
     * @return true si el empleado se eliminó con éxito, false en caso de error o si no se encontró al empleado.
     */
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

    /**
     * Obtiene el rol de un empleado según su nombre de usuario.
     *
     * @param usuario el nombre de usuario del empleado cuyo rol se desea obtener.
     * @return una cadena que representa el rol del empleado, o null si ocurre un error o no se encuentra el usuario.
     */
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
        return rol;
    }

    /**
     * Cifra una contraseña usando el algoritmo SHA-256.
     *
     * @param contrasenia la contraseña que se desea cifrar.
     * @return una cadena hexadecimal que representa la contraseña cifrada, o null si ocurre un error en el proceso.
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

	
    /**
     * Obtiene los datos de un empleado a partir de su ID.
     *
     * @param idEmpleado El ID del empleado a obtener.
     * @return Un objeto Empleado con los datos del empleado, o null si no se encuentra.
     */
    public Empleado obtenerEmpleadoPorId(int idEmpleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Empleado empleado = null;
        try {
            ps = connection.prepareStatement(
                    "SELECT e.idEmpleado, e.Rol, e.nombre, e.apellido, e.domicilio, e.telefono, e.email, e.usuario, e.estado "
                    + "FROM Empleado e "
                    + "WHERE e.idEmpleado = ?");
            ps.setInt(1, idEmpleado);
            rs = ps.executeQuery();
            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setRol(EnumRoles.valueOf(rs.getString("Rol")));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setDomicilio(rs.getString("domicilio"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setEmail(rs.getString("email"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setEstado(rs.getString("estado"));
            } else {
                System.out.println("No se encontró un empleado con el ID: " + idEmpleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
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
        return empleado;    
    }

    /**
     * Actualiza los datos de un empleado en la base de datos.
     *
     * @param empleado El objeto Empleado que contiene los datos actualizados.
     */
    public void actualizarEmpleado(Empleado empleado) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE Empleado SET Rol = ?, nombre = ?, apellido = ?, domicilio = ?, telefono = ?, "
                                            + "email = ?, usuario = ?, estado = ? WHERE idEmpleado = ?");
            ps.setString(1, empleado.getRol().name());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido());
            ps.setString(4, empleado.getDomicilio());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getEmail());
            ps.setString(7, empleado.getUsuario());
            ps.setString(8, empleado.getEstado());
            ps.setInt(9, empleado.getIdEmpleado());
            ps.executeUpdate();
            System.out.println("Empleado actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el empleado.");
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
     * Actualiza la contraseña de un empleado.
     *
     * @param contrasenia La nueva contraseña a establecer.
     * @param idEmpleado El ID del empleado cuya contraseña se actualizará.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarContrasenia(String contrasenia, int idEmpleado) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE Empleado SET contrasenia = ? WHERE idEmpleado = ?");
            ps.setString(1, convertirSHA256(contrasenia));
            ps.setInt(2, idEmpleado);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el empleado.");
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

}
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;

/**
 * Clase ServicioControlador.
 * 
 * Esta clase gestiona las operaciones relacionadas con los servicios en la base de datos,
 * incluyendo el registro de nuevos servicios y la verificación de solapamientos.
 */
public class ServicioControlador {
	Conexion cx;
	private Connection connection;

	/**
	 * Registra un nuevo servicio en la base de datos.
	 *
	 * Este método inserta un nuevo registro de servicio en la tabla correspondiente,
	 * después de verificar que no haya solapamientos de horarios con otros servicios
	 * existentes. Si el servicio se registra correctamente, se retorna true; de lo contrario,
	 * se retorna false.
	 *
	 * @param servicio el objeto Servicio que contiene los detalles del servicio a registrar.
	 * @return true si el servicio fue registrado exitosamente, false en caso contrario.
	 */
	public ServicioControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	/**
	 * Registra un nuevo servicio en la base de datos.
	 *
	 * Este método verifica si el servicio ya existe en la base de datos para la fecha y hora
	 * especificadas. Si no existe, procede a crear el nuevo servicio. Si el servicio se registra
	 * correctamente, se retorna true; de lo contrario, se retorna false.
	 *
	 * @param servicio el objeto Servicio que contiene los detalles del servicio a registrar.
	 * @return true si el servicio fue registrado exitosamente, false en caso contrario.
	 */
	public boolean registrarServicio(Servicio servicio) {
		if (verificarServicio(servicio)) {
			System.out.println("El servicio ya existe en esa fecha y hora. No se puede registrar.");
			return false;
		} else {
			int idServicio = crearServicio(servicio);
			if (idServicio != -1) {
				System.out.println("Servicio registrado con éxito, ID: " + idServicio);
				return true;
			} else {
				System.out.println("Error al registrar el servicio.");
				return false;
			}
		}
	}

	/**
	 * Registra un nuevo servicio en la base de datos.
	 *
	 * Este método inserta un nuevo registro de servicio en la tabla `Servicio`. Después de
	 * insertar el servicio, recupera el ID del último registro insertado para poder retornar
	 * dicho ID. En caso de error durante el proceso de inserción, se retorna -1.
	 *
	 * @param servicio el objeto Servicio que contiene los detalles del servicio a registrar.
	 * @return el ID del servicio registrado, o -1 si ocurrió un error durante la inserción.
	 */
	public int crearServicio(Servicio servicio) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int idServicio = -1;
	    try {
	        ps = connection.prepareStatement("INSERT INTO Servicio VALUES (null, ?, ?, ?, ?)");
	        ps.setString(1, servicio.getFecha());
	        ps.setString(2, servicio.getHoraInicio());
	        ps.setString(3, servicio.getHoraFin());
	        ps.setInt(4, servicio.getEventoPrivado());
	        ps.executeUpdate();

	        ps = connection.prepareStatement("SELECT last_insert_rowid()");
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            idServicio = rs.getInt(1);
	        }
	        System.out.println("Servicio registrado con éxito!");
	        return idServicio;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al registrar el servicio!");
	        return -1;
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
	}


	/**
	 * Verifica si un servicio ya existe en una fecha y hora específicas.
	 *
	 * Este método consulta la base de datos para determinar si ya hay un servicio registrado
	 * con la misma fecha y hora de inicio. Retorna true si existe al menos un servicio
	 * coincidente, y false en caso contrario.
	 *
	 * @param servicio el objeto Servicio que contiene la fecha y la hora de inicio a verificar.
	 * @return true si ya existe un servicio en la fecha y hora especificadas; false en caso contrario.
	 */
	public boolean verificarServicio(Servicio servicio) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT COUNT(*) FROM Servicio WHERE fecha = ? AND horaInicio = ?");
	        ps.setString(1, servicio.getFecha());
	        ps.setString(2, servicio.getHoraInicio());
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar el servicio!");
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
	 * Busca un servicio basado en una reserva específica.
	 *
	 * Este método consulta la base de datos para encontrar un servicio que coincida
	 * con la fecha y la hora de inicio de la reserva proporcionada. Si se encuentra
	 * un servicio, se retorna su ID; de lo contrario, se devuelve -1.
	 *
	 * @param reserva el objeto Reserva que contiene la fecha y hora de la reserva.
	 * @return el ID del servicio asociado a la reserva, o -1 si no se encuentra.
	 */
	public int buscarServicioPorReserva(Reserva reserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int idServicio = -1;
	    try {
	        ps = connection.prepareStatement("SELECT idServicio FROM Servicio WHERE fecha = ? AND horaInicio = ?");
	        ps.setString(1, reserva.getFecha());
	        String horaInicio = reserva.getHora().split(" - ")[0];
	        ps.setString(2, horaInicio);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            idServicio = rs.getInt("idServicio");
	        } else {
	            System.out.println("No se encontró ningún servicio para la reserva dada.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al buscar el servicio para la reserva.");
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
	    return idServicio;
	}

	/**
	 * Busca un servicio basado en una fecha y hora específicas.
	 *
	 * Este método consulta la base de datos para encontrar un servicio que coincida
	 * con la fecha y la hora de inicio proporcionadas. Además, solo busca servicios
	 * que tengan el estado de evento privado. Si se encuentra un servicio, se retorna
	 * su ID; de lo contrario, se devuelve -1.
	 *
	 * @param fecha la fecha del servicio que se está buscando.
	 * @param hora  el rango horario del servicio que se está buscando en formato "horaInicio - horaFin".
	 * @return el ID del servicio asociado a la fecha y hora especificadas, o -1 si no se encuentra.
	 */
	public int buscarServicioPorFecha(String fecha, String hora) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int idServicio = -1;
	    try {
	        // Modificación de la consulta para incluir el estado
	        ps = connection.prepareStatement("SELECT idServicio FROM Servicio WHERE fecha = ? AND horaInicio = ? AND eventoPrivado = ?");
	        ps.setString(1, fecha);
	        String horaInicio = hora.split(" - ")[0];
	        ps.setString(2, horaInicio);
	        ps.setInt(3, 1); 
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            idServicio = rs.getInt("idServicio");
	        } else {
	            System.out.println("No se encontró ningún servicio para la reserva dada con estado 1.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al buscar el servicio para la reserva.");
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
	    return idServicio;
	}
	
	/**
	 * Verifica si hay servicios solapados en una fecha y horario específicos.
	 *
	 * Este método consulta la base de datos para determinar si existe algún servicio
	 * que se solape con el servicio proporcionado, basándose en la fecha y las horas
	 * de inicio y fin. Se considera que hay solapamiento si las horas de inicio y fin
	 * de cualquier servicio existente se intersectan con las horas del servicio dado.
	 *
	 * @param servicio el servicio a verificar para solapamientos.
	 * @return true si hay servicios solapados; false en caso contrario.
	 */
	public boolean verificarSolapamientoServicio(Servicio servicio) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean solapado = false;

	    try {
	        ps = connection.prepareStatement("SELECT COUNT(*) FROM Servicio WHERE fecha = ? AND "
	                + "((horaInicio < ? AND horaFin > ?) OR (horaInicio < ? AND horaFin > ?))");
	        ps.setString(1, servicio.getFecha());
	        ps.setString(2, servicio.getHoraFin());
	        ps.setString(3, servicio.getHoraInicio());
	        ps.setString(4, servicio.getHoraInicio());
	        ps.setString(5, servicio.getHoraFin());

	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            if (count > 0) {
	                solapado = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar solapamiento de servicios!");
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
	    return solapado;
	}

	
	/**
	 * Busca un servicio en la base de datos por su ID.
	 *
	 * Este método consulta la base de datos para recuperar un objeto Servicio
	 * correspondiente al ID proporcionado. Si se encuentra el servicio, se
	 * crea un nuevo objeto Servicio y se llena con los datos recuperados.
	 *
	 * @param idServicio el ID del servicio a buscar.
	 * @return un objeto Servicio si se encuentra; null en caso contrario.
	 */
		public Servicio buscarServicioPorId(int idServicio) {
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    Servicio servicio = null;

		    try {
		        ps = connection.prepareStatement("SELECT * FROM Servicio WHERE idServicio = ?");
		        ps.setInt(1, idServicio);
		        rs = ps.executeQuery();

		        if (rs.next()) {
		            servicio = new Servicio();
		            servicio.setIdServicio(rs.getInt("idServicio"));
		            servicio.setFecha(rs.getString("fecha"));
		            servicio.setHoraInicio(rs.getString("horaInicio"));
		            servicio.setHoraFin(rs.getString("horaFin"));
		            servicio.setEventoPrivado(rs.getInt("eventoPrivado"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("Error al buscar el servicio por ID.");
		    } finally {
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		            if (ps != null) {
		                ps.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return servicio;
		}
		
		/**
		 * Busca todos los servicios que son eventos especiales.
		 *
		 * Este método consulta la base de datos para recuperar una lista de
		 * objetos Servicio que tienen el estado de evento privado (eventoPrivado)
		 * establecido en 1. Cada servicio encontrado se añade a una lista que
		 * se retorna al final.
		 *
		 * @return una lista de objetos Servicio que representan los eventos especiales.
		 */
		public List<Servicio> buscarServiciosConEventoEspecial() {
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    List<Servicio> servicios = new ArrayList<>();

		    try {
		        ps = connection.prepareStatement("SELECT * FROM Servicio WHERE eventoPrivado = 1");
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            Servicio servicio = new Servicio();
		            servicio.setIdServicio(rs.getInt("idServicio"));
		            servicio.setFecha(rs.getString("fecha"));
		            servicio.setHoraInicio(rs.getString("horaInicio"));
		            servicio.setHoraFin(rs.getString("horaFin"));
		            servicio.setEventoPrivado(rs.getInt("eventoPrivado"));

		            servicios.add(servicio);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("Error al buscar los servicios con evento especial.");
		    } finally {
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		            if (ps != null) {
		                ps.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return servicios;
		}



		/**
		 * Registra un servicio en la base de datos después de verificar que no se solape
		 * con otros servicios existentes.
		 *
		 * Este método primero comprueba si el servicio que se desea registrar
		 * se solapa con otro servicio en la misma fecha y hora. Si hay un solapamiento,
		 * se imprime un mensaje indicando que no se puede registrar el servicio.
		 * De lo contrario, se procede a registrar el servicio llamando al método 
		 * {@link #registrarServicio(Servicio)}.
		 *
		 * @param servicio El objeto Servicio que se desea registrar.
		 * @return true si el servicio fue registrado con éxito; false si se solapó con otro servicio.
		 */
		public boolean registrarServicioConSolapamiento(Servicio servicio) {
			if (verificarSolapamientoServicio(servicio)) {
				System.out.println("El servicio se solapa con otro existente. No se puede registrar.");
				return false;
			} else {
				return registrarServicio(servicio);
			}
		}
	
}

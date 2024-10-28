package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Cliente.HistorialReserva;
import Modelo.Complementos.Reserva;
import Modelo.Empleado.Reportes;

/**
 * Controlador para gestionar las operaciones relacionadas con reservas.
 * Este controlador permite crear reservas y manejar la conexión
 * a la base de datos.
 */
public class ReservaControlador {

	Conexion cx;
	private Connection connection;
	ServicioControlador servicioControlador;

	public ReservaControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	/**
	 * Función para crear una Reserva en la base de datos.
	 * 
	 * @param reserva Objeto de tipo Reserva que contiene los datos de la reserva.
	 * @return true si la reserva se creó exitosamente; false en caso contrario.
	 */
	public boolean crearReserva(Reserva reserva) {
	    PreparedStatement ps = null;
	    try {
	        ps = connection.prepareStatement("INSERT INTO Reserva VALUES(null,?,?,?,?,?,?,?,?,?,?)");
	        ps.setInt(1, reserva.getIdCliente());
	        ps.setString(2, reserva.getFecha());
	        ps.setString(3, reserva.getHora());
	        ps.setInt(4, reserva.getIdMesa());
	        ps.setString(5, reserva.getComentario());
	        ps.setString(6, reserva.getDispocicionMesa());
	        ps.setInt(7, reserva.getEstado());
	        ps.setInt(8, reserva.getIdServicio());
	        ps.setInt(9, reserva.getIdComprobante());
	        ps.setString(10, reserva.getTemporada());

	        ps.executeUpdate();
	        System.out.println("Reserva realizada con exito!");
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al Reservar!");
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
	 * Método para verificar si una mesa está disponible para un servicio específico.
	 * 
	 * @param idMesa ID de la mesa que se desea verificar.
	 * @param idServicio ID del servicio asociado a la mesa.
	 * @return true si la mesa está disponible; false en caso contrario.
	 */
	public boolean verificarDisponibilidadMesa(int idMesa, int idServicio) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT COUNT(*) FROM Reserva WHERE idMesa = ? AND idServicio = ?");
	        ps.setInt(1, idMesa);
	        ps.setInt(2, idServicio);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count == 0; 
	        }
	        return false;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
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
	 * Función para verificar si la Reserva ya existe en la base de datos.
	 * 
	 * @param Reserva Objeto de tipo Reserva que contiene los datos de la reserva a verificar.
	 * @return true si la reserva ya existe; false en caso contrario.
	 */
	public boolean verificarReserva(Reserva Reserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement(
	                "SELECT COUNT(*) FROM Reserva WHERE IdCliente = ? AND fecha = ? AND hora = ? AND IdMesa = ? AND IdServicio = ?");
	        ps.setInt(1, Reserva.getIdCliente());
	        ps.setString(2, Reserva.getFecha());
	        ps.setString(3, Reserva.getHora());
	        ps.setInt(4, Reserva.getIdMesa());
	        ps.setInt(5, Reserva.getIdServicio());

	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	        return false;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar la reserva!");
	        return false;
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
	 * Método para verificar si hay solapamientos en una reserva para un Evento Especial.
	 * 
	 * @param reserva Objeto de tipo Reserva que contiene los datos de la reserva a verificar.
	 * @return true si hay solapamientos de horario; false en caso contrario.
	 */
	public boolean verificarSolapamientoReserva(Reserva reserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ServicioControlador servicioControlador = new ServicioControlador();

	    try {
	        int idServicio = servicioControlador.buscarServicioPorReserva(reserva);

	        if (idServicio == -1) {
	            System.out.println("No se encontró un servicio para la reserva.");
	            return false;
	        }
	        ps = connection.prepareStatement("SELECT COUNT(*) FROM Reserva r "
	                + "JOIN Servicio s ON r.IdServicio = s.idServicio " + "WHERE r.IdMesa = ? AND r.IdServicio = ? "
	                + "AND ( ? < s.horaFin AND ? > s.horaInicio )");

	        ps.setInt(1, reserva.getIdMesa());
	        ps.setInt(2, idServicio);

	        String[] horas = reserva.getHora().split(" - ");
	        ps.setString(3, horas[0]);
	        ps.setString(4, horas[1]);

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            if (count > 0) {
	                return true;
	            }
	        }
	        return false;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar solapamiento de reserva!");
	        return false;
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


	/**
	 * Verifica si una mesa está ocupada en una fecha y hora específicas.
	 *
	 * @param idMesa El ID de la mesa a verificar.
	 * @param fecha La fecha en formato 'yyyy-MM-dd' para la cual se verifica la ocupación.
	 * @param hora La hora en formato 'HH:mm' para la cual se verifica la ocupación.
	 * @return true si la mesa está ocupada, false en caso contrario.
	 */
	public boolean verificarMesaOcupada(int idMesa, String fecha, String hora) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT COUNT(*) FROM Reserva WHERE idMesa = ? AND fecha = ? AND hora = ?");
	        ps.setInt(1, idMesa);
	        ps.setString(2, fecha);
	        ps.setString(3, hora);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;  
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar la mesa ocupada!");
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
	 * Obtiene el historial de reservas de un cliente.
	 *
	 * @param idCliente El ID del cliente cuyo historial se desea obtener.
	 * @return Una lista de objetos HistorialReserva que representan el historial de reservas del cliente.
	 */
	public List<HistorialReserva> obtenerHistorialPorCliente(int idCliente) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<HistorialReserva> historial = new ArrayList<>();
	    try {
	        ps = connection.prepareStatement(
	                "SELECT r.idReserva, r.fecha, r.hora, r.idMesa, m.capacidad, m.ubicacion, r.comentario, r.estado "
	                        + "FROM Reserva r " + "JOIN MesaPrecargada m ON r.idMesa = m.idMesa "
	                        + "WHERE r.idCliente = ?");
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            HistorialReserva reserva = new HistorialReserva(rs.getInt("idReserva"), rs.getString("fecha"),
	                    rs.getString("hora"), rs.getInt("idMesa"), rs.getInt("capacidad"), rs.getString("ubicacion"),
	                    rs.getString("comentario"), rs.getInt("estado"));
	            historial.add(reserva);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener el historial de reservas por cliente!");
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
	    return historial;
	}

	/**
	 * Carga el combo solamente con las mesas que el cliente reservó.
	 *
	 * @param idCliente El ID del cliente para el cual se desean obtener las mesas reservadas.
	 * @return Una lista de IDs de las mesas que el cliente ha reservado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Integer> obtenerMesasReservadasPorCliente(int idCliente) throws SQLException {
	    List<Integer> mesasReservadas = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT DISTINCT r.idMesa " + "FROM Reserva r " + "WHERE r.idCliente = ?");
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            mesasReservadas.add(rs.getInt("idMesa"));
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
	    return mesasReservadas;
	}

	/**
	 * Obtiene el estado de las reservas de un cliente.
	 *
	 * @param idCliente El ID del cliente para el cual se desean obtener los estados de las reservas.
	 * @return Una lista de estados de reservas asociadas al cliente.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Integer> obtenerEstadoReservasPorCliente(int idCliente) throws SQLException {
	    List<Integer> estadoReservas = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT DISTINCT r.estado " + "FROM Reserva r " + "WHERE r.idCliente = ?");
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            estadoReservas.add(rs.getInt("estado"));
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
	    return estadoReservas;
	}


	/**
	 * Obtiene el historial de reservas de un cliente en un rango de fechas específico.
	 *
	 * @param dateDesde La fecha de inicio del rango de búsqueda en formato 'yyyy-MM-dd'.
	 * @param dateHasta La fecha de fin del rango de búsqueda en formato 'yyyy-MM-dd'.
	 * @return Una lista de objetos {@link Reportes} que contiene la información de las reservas en el rango especificado.
	 */
	public List<Reportes> obtenerHistorialDeReservas(String dateDesde, String dateHasta) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Reportes> reporte = new ArrayList<>();
	    try {
	        ps = connection.prepareStatement("SELECT c.nombre, c.apellido, fecha, hora, m.capacidad, m.ubicacion, comentario "
	                + "FROM Reserva r JOIN Cliente c ON r.idCliente = c.idCliente JOIN MesaPrecargada m ON r.idMesa = m.idMesa WHERE r.fecha BETWEEN ? AND ?");
	        ps.setString(1, dateDesde);
	        ps.setString(2, dateHasta);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Reportes reportes = new Reportes(rs.getString("nombre"), rs.getString("apellido"),
	                    rs.getString("fecha"), rs.getString("hora"), rs.getInt("capacidad"), rs.getString("ubicacion"),
	                    rs.getString("comentario"));
	            reporte.add(reportes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener el historial de reservas por cliente!");
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
	    return reporte;
	}

	/**
	 * Obtiene el historial completo de reservas de un cliente dado su ID.
	 *
	 * @param idCliente El ID del cliente cuyo historial de reservas se desea obtener.
	 * @return Una lista de objetos {@link Reserva} que contiene la información de todas las reservas del cliente.
	 */
	public ArrayList<Reserva> obtenerHistorialDeReservasCompleta(int idCliente) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Reserva> reservas = new ArrayList<>();
	    try {
	        ps = connection.prepareStatement("SELECT r.*, c.nombre, c.apellido, m.capacidad, m.ubicacion "
	                + "FROM Reserva r JOIN Cliente c ON r.idCliente = c.idCliente "
	                + "JOIN MesaPrecargada m ON r.idMesa = m.idMesa " + "WHERE r.idCliente = ?");
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Reserva reserva = new Reserva();
	            reserva.setIdReserva(rs.getInt("idReserva"));
	            reserva.setIdCliente(rs.getInt("idCliente"));
	            reserva.setFecha(rs.getString("fecha"));
	            reserva.setHora(rs.getString("hora"));
	            reserva.setIdMesa(rs.getInt("idMesa"));
	            reserva.setComentario(rs.getString("comentario"));
	            reserva.setDispocicionMesa(rs.getString("dispocicionMesa"));
	            reserva.setEstado(rs.getInt("estado"));
	            reserva.setIdServicio(rs.getInt("idServicio"));
	            reserva.setIdComprobante(rs.getInt("idComprobante"));
	            reservas.add(reserva);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener el historial completo de reservas para el cliente con ID: " + idCliente);
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

	    return reservas;
	}

	/**
	 * Obtiene el historial de comensales por temporada.
	 *
	 * @param Temporada La temporada para la cual se desea obtener el historial de comensales.
	 * @return Una lista de objetos {@link Reportes} que contiene el total de reservas y la capacidad total para la temporada especificada.
	 */
	public List<Reportes> obtenerHistorialComensalesPorTemporada(String Temporada) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Reportes> reporte = new ArrayList<>();
	    try {
	        ps = connection.prepareStatement(
	                "SELECT count(DISTINCT idReserva) AS totalReservas , sum(m.capacidad) AS totalCapacidad "
	                        + "FROM Reserva r JOIN MesaPrecargada m ON r.idMesa = m.idMesa WHERE Temporada = ?");
	        ps.setString(1, Temporada);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Reportes reportes = new Reportes(rs.getInt("totalReservas"), rs.getInt("totalCapacidad"));
	            reporte.add(reportes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener el historial de reservas por cliente!");
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
	    return reporte;
	}


	/**
	 * Actualiza el comprobante asociado a una reserva existente en la base de datos.
	 *
	 * Este método busca una reserva por su ID y actualiza el campo
	 * `idComprobante` con el nuevo ID proporcionado.
	 *
	 * @param idReserva   El ID de la reserva cuya comprobante se va a actualizar.
	 * @param idComprobante El nuevo ID del comprobante que se asignará a la reserva.
	 * @return true si el comprobante se actualizó con éxito; false en caso contrario.
	 */
	public boolean actualizarComprobante(int idReserva, int idComprobante) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE Reserva SET idComprobante = ? WHERE idReserva = ?");
			ps.setInt(1, idComprobante);
			ps.setInt(2, idReserva);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Comprobante actualizado con éxito!");
				return true;
			} else {
				System.out.println("No se encontró la reserva para actualizar el comprobante.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al actualizar el comprobante!");
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
	 * Busca una reserva en la base de datos y retorna su ID.
	 *
	 * Este método busca una reserva específica utilizando los atributos del
	 * objeto Reserva proporcionado. Si se encuentra la reserva, se devuelve
	 * su ID; de lo contrario, se retorna -1.
	 *
	 * @param reserva El objeto Reserva que contiene los criterios de búsqueda
	 *                (idCliente, fecha, hora, idMesa, y temporada).
	 * @return El ID de la reserva encontrada, o -1 si no se encuentra.
	 */
	public int buscarIdReserva(Reserva reserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int idReserva = -1;
	    try {
	        ps = connection.prepareStatement(
	                "SELECT idReserva FROM Reserva WHERE idCliente = ? AND fecha = ? AND hora = ? AND idMesa = ? AND temporada = ?");
	        ps.setInt(1, reserva.getIdCliente());
	        ps.setString(2, reserva.getFecha());
	        ps.setString(3, reserva.getHora());
	        ps.setInt(4, reserva.getIdMesa());
	        ps.setString(5, reserva.getTemporada());

	        rs = ps.executeQuery();

	        if (rs.next()) {
	            idReserva = rs.getInt("idReserva");
	            System.out.println("Reserva encontrada con éxito, ID: " + idReserva);
	        } else {
	            System.out.println("No se encontró la reserva.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al buscar la reserva!");
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

	    return idReserva;
	}

	/**
	 * Cancela una reserva actualizando su estado en la base de datos.
	 *
	 * Este método establece el estado de una reserva a 0 (cancelada) en la
	 * tabla Reserva, utilizando el ID de la reserva proporcionado.
	 *
	 * @param idSeleccionado El ID de la reserva que se desea cancelar.
	 */
	public void cancelarReserva(int idSeleccionado) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("UPDATE Reserva SET estado = 0 WHERE idReserva = ?");
			ps.setInt(1, idSeleccionado);
			ps.executeUpdate();
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
	}

	/**
	 * Elimina la mesa asociada a una reserva cancelada de la base de datos.
	 *
	 * Este método busca la mesa y el servicio vinculados a una reserva específica
	 * mediante su ID, y elimina la mesa de la tabla Mesa.
	 *
	 * @param idReserva El ID de la reserva cuya mesa se desea eliminar.
	 */
	public void eliminarMesa(int idReserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int idMesa = 0;
	    int idServicio = 0;
	    try {
	        ps = connection.prepareStatement("SELECT idMesa, idServicio FROM Reserva WHERE idReserva = ?");
	        ps.setInt(1, idReserva);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            idMesa = rs.getInt("idMesa");
	            idServicio = rs.getInt("idServicio");

	            ps = connection.prepareStatement("DELETE FROM Mesa WHERE idMesa = ? AND idServicio = ?");
	            ps.setInt(1, idMesa);
	            ps.setInt(2, idServicio);
	            ps.executeUpdate();
	            System.out.println("Mesa eliminada con éxito.");
	        } else {
	            System.out.println("No se encontró la reserva con ID: " + idReserva);
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
	 }

	/**
	 * Carga los datos del historial de reservas de los clientes.
	 *
	 * Este método consulta la base de datos para obtener un historial de reservas
	 * futuras, incluyendo detalles del cliente, fecha, hora, mesa, capacidad, ubicación,
	 * estado y comentario. Los resultados son devueltos como una lista de objetos 
	 * HistorialReserva.
	 *
	 * @return una lista de objetos HistorialReserva que contiene el historial de reservas.
	 */
	 public List<HistorialReserva> obtenerHistorialReserva() {
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    List<HistorialReserva> historial = new ArrayList<>();
		    try {
		    	ps = connection.prepareStatement(
		    		    "SELECT r.idReserva, c.nombre, c.apellido, r.fecha, r.hora, r.idMesa, m.capacidad, m.ubicacion, r.estado, r.comentario " +
		    		    "FROM Reserva r " +
		    		    "JOIN MesaPrecargada m ON r.idMesa = m.idMesa " +
		    		    "JOIN Cliente c ON r.idCliente = c.idCliente " +
		    		    "WHERE DATE(substr(r.fecha, 7, 4) || '-' || substr(r.fecha, 4, 2) || '-' || substr(r.fecha, 1, 2)) >= DATE('now') " +
		    		    "ORDER BY DATE(substr(r.fecha, 7, 4) || '-' || substr(r.fecha, 4, 2) || '-' || substr(r.fecha, 1, 2)) ASC, r.hora ASC"
		    		);

		        rs = ps.executeQuery();
		        while (rs.next()) {
		            HistorialReserva reserva = new HistorialReserva(
		                rs.getInt("idReserva"),
		                rs.getString("nombre"),
		                rs.getString("apellido"),
		                rs.getString("fecha"),
		                rs.getString("hora"),
		                rs.getInt("idMesa"),
		                rs.getInt("capacidad"),
		                rs.getString("ubicacion"),
		                rs.getInt("estado"),
		                rs.getString("comentario")
		            );
		            historial.add(reserva);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("Error al obtener el historial de reservas por cliente!");
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (ps != null) ps.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return historial;
		}

	 /**
	  * Obtiene las reservas futuras de un cliente específico.
	  *
	  * Este método consulta la base de datos para recuperar todas las reservas
	  * futuras asociadas al cliente identificado por su ID. Se obtienen detalles
	  * como el nombre, apellido, fecha, hora, capacidad de la mesa, ubicación 
	  * y comentario de la reserva.
	  *
	  * @param idCliente el ID del cliente cuyas reservas futuras se desean obtener.
	  * @return una lista de objetos Reportes que representan las reservas futuras
	  *         del cliente.
	  */
	public List<Reportes> obtenerReservasFuturasPorCliente(int idCliente) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Reportes> reservasFuturas = new ArrayList<>();
	    try {
	        ps = connection.prepareStatement(
	            "SELECT c.nombre, c.apellido, r.fecha, r.hora, m.capacidad, m.ubicacion, r.comentario " +
	            "FROM Reserva r JOIN MesaPrecargada m ON r.idMesa = m.idMesa " +
	            "JOIN Cliente c ON r.idCliente = c.idCliente " +
	            "WHERE r.idCliente = ? AND r.fecha >= DATE('now')"
	        );
	        ps.setInt(1, idCliente);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Reportes reporte = new Reportes(
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("fecha"),
	                rs.getString("hora"),
	                rs.getInt("capacidad"),
	                rs.getString("ubicacion"),
	                rs.getString("comentario")
	            );
	            reservasFuturas.add(reporte);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener las reservas futuras para el cliente con id: " + idCliente);
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
	    return reservasFuturas;
	}

	/**
	 * Obtiene el cliente más frecuente en base al número de reservas realizadas.
	 *
	 * Este método consulta la base de datos para identificar al cliente que
	 * ha realizado la mayor cantidad de reservas. Se obtiene el nombre,
	 * apellido y la cantidad total de reservas del cliente más frecuente.
	 *
	 * @return un objeto Reportes que representa al cliente más frecuente, 
	 *         o null si no se encontraron reservas.
	 */
	public Reportes obtenerClienteMasFrecuente() {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Reportes reporte = null;
	    try {
	        ps = connection.prepareStatement("SELECT c.nombre, c.apellido, COUNT(r.idReserva) AS cantidadReservas "
	                + "FROM Reserva r " + "JOIN Cliente c ON r.idCliente = c.idCliente "
	                + "GROUP BY c.nombre, c.apellido " + "ORDER BY cantidadReservas DESC " + "LIMIT 1");
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            reporte = new Reportes(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("cantidadReservas"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener el cliente más frecuente");
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
	    return reporte;
	}

	/**
	 * Obtiene el comprobante asociado a una reserva específica.
	 *
	 * Este método consulta la base de datos para recuperar información
	 * sobre una reserva dada su ID. Retorna un objeto HistorialReserva
	 * que contiene detalles como la ubicación de la mesa, fecha, hora,
	 * capacidad, ID de la mesa y comentario de la reserva.
	 *
	 * @param idReserva el ID de la reserva para la cual se busca el comprobante.
	 * @return un objeto HistorialReserva que contiene la información del comprobante,
	 *         o null si no se encontró un comprobante para la reserva especificada.
	 */
	public HistorialReserva obtenerComprobantePorReserva(int idReserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    HistorialReserva reserva = null;
	    try {
	        ps = connection.prepareStatement("SELECT m.ubicacion, r.fecha, r.hora, m.capacidad, r.idMesa, r.comentario "
	                + "FROM Reserva r " + "JOIN MesaPrecargada m ON r.idMesa = m.idMesa " + "WHERE r.idReserva = ?");
	        ps.setInt(1, idReserva);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            reserva = new HistorialReserva(rs.getString("ubicacion"), rs.getString("fecha"), rs.getString("hora"),
	                    rs.getInt("capacidad"), rs.getInt("idMesa"), rs.getString("comentario"));
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
	    return reserva;
	}


	/**
	 * Obtiene los detalles de una reserva específica a partir de su ID.
	 *
	 * Este método consulta la base de datos para recuperar información
	 * sobre una reserva dada su ID. Retorna un objeto HistorialReserva
	 * que contiene detalles como la ubicación de la mesa, fecha, hora,
	 * capacidad, ID de la mesa y comentario de la reserva.
	 *
	 * @param idReserva el ID de la reserva para la cual se buscan los detalles.
	 * @return un objeto HistorialReserva que contiene la información de la reserva,
	 *         o null si no se encontró una reserva con el ID especificado.
	 */
	public HistorialReserva obtenerReservaPorId(int idReserva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    HistorialReserva reserva = null;
	    try {
	        ps = connection.prepareStatement(
	                "SELECT r.idReserva, m.ubicacion, r.fecha, r.hora, m.capacidad, r.idMesa, r.comentario "
	                        + "FROM Reserva r " + "JOIN MesaPrecargada m ON r.idMesa = m.idMesa "
	                        + "WHERE r.idReserva = ?");
	        ps.setInt(1, idReserva);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            reserva = new HistorialReserva(rs.getInt("idReserva"), rs.getString("fecha"), rs.getString("hora"),
	                    rs.getInt("idMesa"), rs.getInt("capacidad"), rs.getString("ubicacion"),
	                    rs.getString("comentario"));
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
	    return reserva;
	}


	/**
	 * Actualiza los datos de una reserva existente en la base de datos.
	 *
	 * Este método actualiza la información relacionada con una reserva específica,
	 * incluyendo la fecha, hora, ID de mesa, comentario, disposición de la mesa,
	 * ID de servicio, ID del comprobante y temporada. También actualiza la
	 * información correspondiente en la tabla de comprobantes.
	 *
	 * @param idReserva el ID de la reserva que se desea actualizar.
	 * @param reserva un objeto Reserva que contiene los nuevos datos para la reserva.
	 * @return true si la actualización fue exitosa; false si ocurrió un error.
	 */
	public boolean actualizarReserva(int idReserva, Reserva reserva) {
		PreparedStatement ps = null;
		ComprobanteControlador controladorc = new ComprobanteControlador();

		int idComprobante = controladorc.obteneridComprobante(idReserva);
		try {

			ps = connection.prepareStatement("UPDATE Comprobante SET fecha = ?, hora = ? WHERE idComprobante = ?");
			ps.setString(1, reserva.getFecha());
			ps.setString(2, reserva.getHora());
			ps.setInt(3, idComprobante);
			ps.executeUpdate();

			System.out.println("Paso comprobante");

			ps = connection.prepareStatement(
					"UPDATE Reserva SET fecha = ?, hora = ?, idMesa = ?, comentario = ?, dispocicionMesa = ?, idServicio = ?, idComprobante = ?, Temporada = ?  WHERE idReserva = ?");
			ps.setString(1, reserva.getFecha());
			ps.setString(2, reserva.getHora());
			ps.setInt(3, reserva.getIdMesa());
			ps.setString(4, reserva.getComentario());
			ps.setString(5, reserva.getDispocicionMesa());
			ps.setInt(6, reserva.getIdServicio());
			ps.setInt(7, idComprobante);
			ps.setString(8, reserva.getTemporada());
			ps.setInt(9, idReserva);
			ps.executeUpdate();

			System.out.println("Reserva actualiza con exito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al actualizar la Reserva!");
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
	 * Obtiene el email del cliente asociado a una reserva específica.
	 *
	 * Este método consulta la base de datos para recuperar el email del cliente
	 * que tiene una reserva con el ID proporcionado. Si se encuentra el email,
	 * se devuelve como una cadena. En caso contrario, se indica que no se encontró
	 * un cliente asociado.
	 *
	 * @param idReserva el ID de la reserva para la cual se desea obtener el email del cliente.
	 * @return el email del cliente asociado a la reserva, o null si no se encontró.
	 */

    public String obtenerEmailClientePorReserva(int idReserva) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String email = null;
        
        try {
            ps = connection.prepareStatement("SELECT Cliente.email FROM Reserva JOIN Cliente ON Reserva.idCliente = Cliente.idCliente WHERE Reserva.idReserva = ?");
            ps.setInt(1, idReserva);
    
            rs = ps.executeQuery();
            
            if (rs.next()) {
                email = rs.getString("email");
                System.out.println("Email del cliente encontrado: " + email);
            } else {
                System.out.println("No se encontró un cliente asociado a la reserva.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el email del cliente.");
        }finally {
	        if (ps != null) {
	            try {
	                ps.close();
	                } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
        
        return email;
    }
    
    /**
     * Actualiza el estado de una reserva específica en la base de datos.
     *
     * Este método permite cambiar el estado de una reserva identificada por su ID.
     * Se establece un nuevo estado en la columna correspondiente de la tabla Reserva.
     *
     * @param idSeleccionado el ID de la reserva cuyo estado se desea actualizar.
     * @param nuevoEstado el nuevo estado que se asignará a la reserva.
     */
    public void actualizarEstadoReserva(int idSeleccionado, int nuevoEstado) {
        PreparedStatement ps = null;
        try {   
            ps = connection.prepareStatement("UPDATE Reserva SET estado = ? WHERE idReserva = ?");
            ps.setInt(1, nuevoEstado);  
            ps.setInt(2, idSeleccionado);  
            ps.executeUpdate();
            
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
    }
    
    /**
     * Verifica si una reserva puede ser cancelada.
     *
     * Este método consulta el estado de una reserva específica en la base de datos
     * para determinar si puede ser cancelada. Se considera que una reserva puede
     * ser cancelada si su estado es igual a 1.
     *
     * @param idSeleccionado el ID de la reserva que se desea verificar.
     * @return true si la reserva puede ser cancelada, false en caso contrario.
     */
    public boolean verificarEstadoReserva(int idSeleccionado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean puedeCancelar = false;
        
        try {
            ps = connection.prepareStatement("SELECT estado = 1 AS puedeCancelar FROM Reserva WHERE idReserva = ?");
            ps.setInt(1, idSeleccionado);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                puedeCancelar = rs.getBoolean("puedeCancelar");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return puedeCancelar;
    }


}
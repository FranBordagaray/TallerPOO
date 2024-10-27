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

public class ReservaControlador {

	Conexion cx;
	private Connection connection;
	ServicioControlador servicioControlador;

	public ReservaControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Función para crear una Reserva
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

	// Método para verificar si una mesa está disponible para un servicio específico
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

	// Función para verificar si la Reserva ya existe
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
	        System.out.println("Error al verificar el reserva!");
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

	// Metodo para verificar si hay solapamientos en una reserva para Evento
	// Especial
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

	// Función para verificar si una mesa está ocupada en una fecha y hora específicas
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

	// Función para obtener el historial de Reservas de un cliente
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

	// Función para cargar el combo solamente con las mesas que el cliente reservó
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

	// Función para cargar el combo solamente con las mesas que el cliente reservó
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

	// Función para obtener el historial de Reservas de un cliente
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

	// Función para obtener el historial completo de Reservas de un cliente
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

	// Función para obtener el historial de comensales por temporada
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

	// Funcion para actualizar la reserva, y asignarle su comprobante
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

	// Funcion que buscar una reserva y retorna su id
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

	// Funcion para cancelar una Reserva
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

	// Funcion para eliminar la mesa de la reserva cancelada
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

	 //Funcion para cargar datos de la reserva Historial Empleado
    public List<HistorialReserva> obtenerHistorialReserva(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HistorialReserva> historial = new ArrayList<>();
        try {
            ps = connection.prepareStatement( "SELECT r.idReserva, c.nombre, c.apellido, r.fecha, r.hora, r.idMesa, m.capacidad, m.ubicacion, r.estado, r.comentario FROM Reserva r JOIN MesaPrecargada m ON r.idMesa = m.idMesa \r\n"
             + "JOIN Cliente c ON r.idCliente = c.idCliente"
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
        }
        return historial;
    }

	//Metodo para obtener las reservas futuras del cliente
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

	// Función para obtener el cliente más frecuente
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

	// Función para obtener el historial de Reservas de un cliente
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


	// Función para obtener el historial de Reservas de un cliente
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


	// Funcion que actualiza los datos de la reserva
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
	
	// Función para obtener el email del cliente asociado a una reserva
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
    
    // Funcion para cambiar estado de la reserva
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
    
    //Funcion para verificar el estado de una Reserva ante una cancelacion
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
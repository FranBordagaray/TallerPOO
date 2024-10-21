package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.Conexion;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;

public class ServicioControlador {
	Conexion cx;
	private Connection connection;

	public ServicioControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Función para registrar un servicio con verificación previa
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

	// Función para registrar un servicio en la base de datos
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
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Función para verificar si un servicio ya existe en una fecha y hora
	// específicas
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
				if (count > 0) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al verificar el servicio!");
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

	// Método para buscar un servicio basado en una reserva
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

// Método para verificar si hay un servicio solapado
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
	
	// Método para buscar un servicio por su ID
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


		// Método para registrar un servicio con verificación de solapamiento
		public boolean registrarServicioConSolapamiento(Servicio servicio) {
			if (verificarSolapamientoServicio(servicio)) {
				System.out.println("El servicio se solapa con otro existente. No se puede registrar.");
				return false;
			} else {
				return registrarServicio(servicio);
			}
		}
	
}

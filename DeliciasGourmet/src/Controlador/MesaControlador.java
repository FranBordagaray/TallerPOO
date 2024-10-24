package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Modelo.Complementos.EnumEstado;
import Modelo.Complementos.Mesa;

public class MesaControlador {
	Conexion cx;
	private Connection connection;

	public MesaControlador() {
		cx = new Conexion();
		connection = cx.conectar();
	}

	// Funcion para cargar Mesa
	public boolean crearMesa(Mesa mesa) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("INSERT INTO Mesa  VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, mesa.getIdMesa());
			ps.setInt(2, mesa.getCapacidad());
			ps.setString(3, mesa.getUbicacion());
			ps.setString(4, mesa.getEstado().name());
			ps.setInt(5, mesa.getIdServicio());
			ps.executeUpdate();
			System.out.println("Servicio registrado con éxito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al registrar el servicio!");
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

	// Función para buscar mesas por Ubicacion
	public List<Mesa> buscarMesasPorUbicacion(String ubicacion) {
	    List<Mesa> mesasP = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT * FROM MesaPrecargada WHERE ubicacion = ?");
	        ps.setString(1, ubicacion);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Mesa mesa = new Mesa();
	            mesa.setIdMesa(rs.getInt("idMesa"));
	            mesa.setCapacidad(rs.getInt("capacidad"));
	            mesa.setUbicacion(rs.getString("ubicacion"));
	            mesasP.add(mesa);
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
	    return mesasP;
	}


	//Metodo para verificar si hay solapamiento de servicios
	public boolean verificarMesaConServicio(Mesa mesa, String fecha, String horaInicioNueva, String horaFinNueva) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    PreparedStatement psServicio = null;
	    ResultSet rsServicio = null;
	    PreparedStatement psReserva = null;
	    ResultSet rsReserva = null;

	    try {
	        ps = connection.prepareStatement("SELECT idServicio FROM Mesa WHERE idMesa = ?");
	        ps.setInt(1, mesa.getIdMesa());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int idServicio = rs.getInt("idServicio");

	            psServicio = connection.prepareStatement("SELECT horaInicio, horaFin FROM Servicio WHERE idServicio = ?");
	            psServicio.setInt(1, idServicio);
	            rsServicio = psServicio.executeQuery();

	            if (rsServicio.next()) {
					String horaInicioServicio = rsServicio.getString("horaInicio");
	                String horaFinServicio = rsServicio.getString("horaFin");

	                psReserva = connection.prepareStatement(
	                    "SELECT COUNT(*) FROM Reserva r " +
	                    "WHERE r.idMesa = ? AND r.fecha = ? AND " +
	                    "(? < r.hora AND r.hora < ?)"
	                );
	                psReserva.setInt(1, mesa.getIdMesa());
	                psReserva.setString(2, fecha);
	                psReserva.setString(3, horaInicioNueva);
	                psReserva.setString(4, horaFinNueva);
	                rsReserva = psReserva.executeQuery();
	                return rsReserva.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rsReserva != null) {
	                rsReserva.close();
	            }
	            if (psReserva != null) {
	                psReserva.close();
	            }
	            if (rsServicio != null) {
	                rsServicio.close();
	            }
	            if (psServicio != null) {
	                psServicio.close();
	            }
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
	    return false;
	}


	// Función busca mesas con estado "Ocupado" en una fecha y hora
	public List<Integer> buscarMesasOcupadasPorServicio(String fecha, String hora) {
	    List<Integer> mesasOcupadasIds = new ArrayList<>();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        ps = connection.prepareStatement("SELECT idMesa FROM Reserva WHERE fecha=? AND hora=? AND estado=1");
	        ps.setString(1, fecha);
	        ps.setString(2, hora);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            mesasOcupadasIds.add(rs.getInt("idMesa"));
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
	    return mesasOcupadasIds;
	}


	// Función para buscar mesas por Ubicacion
	public int filtrarCapacidad(int idMesa) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int capacidad = 0;
	    try {
	        ps = connection.prepareStatement("SELECT capacidad FROM MesaPrecargada WHERE idMesa = ?");
	        ps.setInt(1, idMesa);

	        rs = ps.executeQuery();
	        while (rs.next()) {
	            capacidad = rs.getInt("capacidad");
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
	    return capacidad;
	}

	
	//Funcion para actualizar los datos de una mesa
	public boolean eliminarMesa(int idMesa, int idServicio) {
		PreparedStatement ps = null;
		boolean eliminado = false; 

		try {
		     ps = connection.prepareStatement("DELETE FROM Mesa WHERE idMesa = ? AND idServicio = ?");
		     ps.setInt(1, idMesa);
		     ps.setInt(2, idServicio);

		     int filasAfectadas = ps.executeUpdate(); 
		     eliminado = filasAfectadas > 0; 

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
		return eliminado; 
	}

	// Función para actualizar el idServicio de una Mesa
	public boolean actualizarIdServicio(int nuevoIdServicio, int idMesa, int idServicioAntiguo) {
		PreparedStatement ps = null;
		try {

			ps = connection.prepareStatement("UPDATE Mesa SET idServicio = ? WHERE idMesa = ? AND idServicio = ?");
			ps.setInt(1, nuevoIdServicio);
			ps.setInt(2, idMesa);
			ps.setInt(3, idServicioAntiguo);
			ps.executeUpdate();

			System.out.println("ID del servicio actualizado con éxito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al actualizar el ID del servicio!");
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
		
	// Método para buscar mesas por ID
	public ArrayList<Mesa> buscarMesaPorId(int idMesa) {
		ArrayList<Mesa> mesas = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("SELECT * FROM Mesa WHERE idMesa = ?");
			ps.setInt(1, idMesa);
			rs = ps.executeQuery();
		          
			while (rs.next()) {
				Mesa mesa = new Mesa();
				mesa.setIdMesa(rs.getInt("idMesa"));
				mesa.setCapacidad(rs.getInt("capacidad"));
				mesa.setEstado(EnumEstado.valueOf(rs.getString("estado"))); 
				mesa.setIdServicio(rs.getInt("idServicio"));
				mesas.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return mesas;
	 	}
}
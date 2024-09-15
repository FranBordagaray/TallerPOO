package Controlador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.Reserva;
import Conexion.Conexion;


public class ReservaControlador {
	
	Conexion cx;
  	public ReservaControlador() {
        cx = new Conexion();
    }
	
  	// Función para crear una Reserva
	public boolean crearReserva(Reserva reserva) {
		PreparedStatement ps = null;

		try {
			cx.conectar();
			ps = cx.conectar().prepareStatement("INSERT INTO Reserva VALUES(null,?,?,?,?,?,?)");
			ps.setString(1, reserva.getFecha());
			ps.setString(2, reserva.getHora());
			ps.setString(3, reserva.getComentario());
			ps.setString(4, reserva.getDispocionMesa());
			ps.setInt(5, reserva.getIdCliente());  
		    ps.setInt(6, reserva.getIdMesa());
			ps.executeUpdate();
			System.out.println("Reserva realizada con exito!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al Reservar!");
			return false;
		} 
	}
		
}

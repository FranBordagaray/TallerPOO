package Modelo.Complementos;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import Modelo.Cliente.HistorialReserva;

public class Reserva {

	// Variables de clase
	private int idReserva;
	private int idCliente;
	private String fecha;
	private String hora;
	private int idMesa;
	private String comentario;
	private String dispocicionMesa;
	private int estado;
	private int idServicio;
	private int idComprobante;
	private String temporada;

	// Constructor de clase
	public Reserva() {
	}

	// Getter de idReserva
	public int getIdReserva() {
		return idReserva;
	}

	// Setter de idReserva
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	// Getter de Fecha
	public String getFecha() {
		return fecha;
	}

	// Setter de Fecha
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	// Getter de Hora
	public String getHora() {
		return hora;
	}

	// Setter de Hora
	public void setHora(String hora) {
		this.hora = hora;
	}

	// Getter de Comentario
	public String getComentario() {
		return comentario;
	}

	// Setter de Comentario
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	// Getter de DispocionMesa
	public String getDispocicionMesa() {
		return dispocicionMesa;
	}

	// Setter de DisposicionMesa
	public void setDispocicionMesa(String dispocicionMesa) {
		this.dispocicionMesa = dispocicionMesa;
	}
	
	// Getter de idCliente
	public int getIdCliente() {
		return idCliente;
	}
	
	// Setter de idCliente
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	// Getter de idMesa
	public int getIdMesa() {
		return idMesa;
	}
	
	// Setter de idMesa
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	
	// Getter de Estado
	public int getEstado() {
		return estado;
	}
	
	// Setter de estap
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	// Getter de idServicio
    public int getIdServicio() {
    	return this.idServicio;
    }
    
    // Setter de idServicio
    public void setIdServicio(int idServicio) {
    	this.idServicio = idServicio;
    }
    
    // Getter de idComprobante
    public int getIdComprobante() {
    	return this.idComprobante;
    }
    
    // Setter de idComprobante
    public void setIdComprobante(int idComprobante) {
    	this.idComprobante = idComprobante;
    }
    
    // Getter de Temporada
    public String getTemporada() {
        return temporada;
    }

    // Setter de Temporada
    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
    
    // Método para asignar temporada en base a la fecha recibida
    public void asignarTemporada() {
        if (this.fecha == null || this.fecha.isEmpty()) {
            this.temporada = "FECHA NO VÁLIDA";
            return;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fechaActual = LocalDate.parse(this.fecha, formatter);
            
            MonthDay diaMesActual = MonthDay.from(fechaActual);
            MonthDay inicioPrimavera = MonthDay.of(9, 21);
            MonthDay finPrimavera = MonthDay.of(12, 20);
            MonthDay inicioVerano = MonthDay.of(12, 21);
            MonthDay finVerano = MonthDay.of(3, 20);
            MonthDay inicioOtono = MonthDay.of(3, 21);
            MonthDay finOtono = MonthDay.of(6, 20);
            MonthDay inicioInvierno = MonthDay.of(6, 21);
            MonthDay finInvierno = MonthDay.of(9, 20);
            
            if (estaDentroDeRango(diaMesActual, inicioPrimavera, finPrimavera)) {
                this.temporada = "PRIMAVERA";
            } else if (estaDentroDeRango(diaMesActual, inicioVerano, finVerano)) {
                this.temporada = "VERANO";
            } else if (estaDentroDeRango(diaMesActual, inicioOtono, finOtono)) {
                this.temporada = "OTOÑO";
            } else if (estaDentroDeRango(diaMesActual, inicioInvierno, finInvierno)) {
                this.temporada = "INVIERNO";
            } else {
                this.temporada = "FECHA NO VÁLIDA";
            }
        } catch (Exception e) {
            this.temporada = "FECHA NO VÁLIDA";
        }
    }

    // Método para verificar si la fecha está dentro del rango
    private boolean estaDentroDeRango(MonthDay fecha, MonthDay inicio, MonthDay fin) {
        if (!inicio.isAfter(fin)) {
            return (fecha.equals(inicio) || fecha.isAfter(inicio)) && (fecha.equals(fin) || fecha.isBefore(fin));
        }
        else {
            return fecha.equals(inicio) || fecha.isAfter(inicio) || fecha.equals(fin) || fecha.isBefore(fin);
        }
    }
    
    // Metodo que convierte un objeto de tipo HistorialReserva a Reserva
    public static Reserva convertirAReserva(HistorialReserva historial) {
    	Reserva reserva = new Reserva();
    	reserva.setFecha(historial.getFecha());
    	reserva.setHora(historial.getHora());
    	
        return reserva;
    }
}
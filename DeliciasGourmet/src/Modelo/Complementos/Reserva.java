package Modelo.Complementos;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import Modelo.Cliente.HistorialReserva;

/**
 * Representa una reserva en el sistema, incluyendo información sobre el cliente,
 * la mesa reservada, fecha, hora, comentarios y estado de la reserva.
 */
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

    /**
     * Constructor por defecto para crear una instancia de Reserva sin valores iniciales.
     */
    public Reserva() {
    }

    /**
     * Obtiene el ID de la reserva.
     * 
     * @return El ID de la reserva.
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Asigna un ID a la reserva.
     * 
     * @param idReserva El ID a asignar.
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Obtiene la fecha de la reserva.
     * 
     * @return La fecha de la reserva en formato "dd-MM-yyyy".
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Asigna una fecha a la reserva.
     * 
     * @param fecha La fecha a asignar.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora de la reserva.
     * 
     * @return La hora de la reserva.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Asigna una hora a la reserva.
     * 
     * @param hora La hora a asignar.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el comentario asociado a la reserva.
     * 
     * @return El comentario de la reserva.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Asigna un comentario a la reserva.
     * 
     * @param comentario El comentario a asignar.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Obtiene la disposición de la mesa.
     * 
     * @return La disposición de la mesa.
     */
    public String getDispocicionMesa() {
        return dispocicionMesa;
    }

    /**
     * Asigna una disposición a la mesa.
     * 
     * @param dispocicionMesa La disposición a asignar.
     */
    public void setDispocicionMesa(String dispocicionMesa) {
        this.dispocicionMesa = dispocicionMesa;
    }

    /**
     * Obtiene el ID del cliente asociado a la reserva.
     * 
     * @return El ID del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Asigna un ID de cliente a la reserva.
     * 
     * @param idCliente El ID a asignar.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el ID de la mesa reservada.
     * 
     * @return El ID de la mesa.
     */
    public int getIdMesa() {
        return idMesa;
    }

    /**
     * Asigna un ID de mesa a la reserva.
     * 
     * @param idMesa El ID a asignar.
     */
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * Obtiene el estado de la reserva.
     * 
     * @return El estado de la reserva.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Asigna un estado a la reserva.
     * 
     * @param estado El estado a asignar.
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el ID del servicio asociado a la reserva.
     * 
     * @return El ID del servicio.
     */
    public int getIdServicio() {
        return this.idServicio;
    }

    /**
     * Asigna un ID de servicio a la reserva.
     * 
     * @param idServicio El ID a asignar.
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Obtiene el ID del comprobante asociado a la reserva.
     * 
     * @return El ID del comprobante.
     */
    public int getIdComprobante() {
        return this.idComprobante;
    }

    /**
     * Asigna un ID de comprobante a la reserva.
     * 
     * @param idComprobante El ID a asignar.
     */
    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    /**
     * Obtiene la temporada asociada a la reserva.
     * 
     * @return La temporada de la reserva.
     */
    public String getTemporada() {
        return temporada;
    }

    /**
     * Asigna una temporada a la reserva.
     * 
     * @param temporada La temporada a asignar.
     */
    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    /**
     * Asigna la temporada a la reserva en base a la fecha proporcionada.
     * 
     * Si la fecha es nula o vacía, se asigna "FECHA NO VALIDA". 
     * Las temporadas se determinan en base a rangos de fechas.
     */
    public void asignarTemporada() {
        if (this.fecha == null || this.fecha.isEmpty()) {
            this.temporada = "FECHA NO VALIDA";
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
                this.temporada = "FECHA NO VALIDA";
            }
        } catch (Exception e) {
            this.temporada = "FECHA NO VALIDA";
        }
    }

    /**
     * Verifica si la fecha está dentro de un rango específico.
     * 
     * @param fecha La fecha a verificar.
     * @param inicio La fecha de inicio del rango.
     * @param fin La fecha de fin del rango.
     * @return true si la fecha está dentro del rango; false en caso contrario.
     */
    private boolean estaDentroDeRango(MonthDay fecha, MonthDay inicio, MonthDay fin) {
        if (!inicio.isAfter(fin)) {
            return (fecha.equals(inicio) || fecha.isAfter(inicio)) && (fecha.equals(fin) || fecha.isBefore(fin));
        } else {
            return fecha.equals(inicio) || fecha.isAfter(inicio) || fecha.equals(fin) || fecha.isBefore(fin);
        }
    }

    /**
     * Convierte un objeto de tipo HistorialReserva a Reserva.
     * 
     * @param historial Objeto de tipo HistorialReserva a convertir.
     * @return Un objeto Reserva con la información del historial.
     */
    public static Reserva convertirAReserva(HistorialReserva historial) {
        Reserva reserva = new Reserva();
        reserva.setFecha(historial.getFecha());
        reserva.setHora(historial.getHora());
        
        return reserva;
    }
}

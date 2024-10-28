package Modelo.Complementos;

/**
 * Clase que representa un servicio en el sistema de reservas.
 */
public class Servicio {

    /** Identificador único del servicio. */
    private int idServicio;

    /** Fecha en que se realizará el servicio. */
    private String fecha;

    /** Hora de inicio del servicio. */
    private String horaInicio;

    /** Hora de finalización del servicio. */
    private String horaFin;

    /** Indica si el servicio es para un evento privado (1 = sí, 0 = no). */
    private int eventoPrivado;

    /**
     * Constructor vacío de la clase Servicio.
     */
    public Servicio() {
    }

    /**
     * Constructor de la clase Servicio con parámetros.
     *
     * @param fecha2 Fecha en la que se realizará el servicio.
     * @param horaInicio2 Hora de inicio del servicio.
     * @param horaFin2 Hora de finalización del servicio.
     * @param i Indica si es un evento privado.
     */
    public Servicio(String fecha2, String horaInicio2, String horaFin2, int i) {
    }

    /**
     * Obtiene el identificador del servicio.
     *
     * @return idServicio Identificador del servicio.
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * Establece el identificador del servicio.
     *
     * @param idServicio Identificador del servicio a establecer.
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Obtiene la fecha del servicio.
     *
     * @return fecha Fecha en la que se realizará el servicio.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del servicio.
     *
     * @param fecha Fecha a establecer para el servicio.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora de inicio del servicio.
     *
     * @return horaInicio Hora de inicio del servicio.
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Establece la hora de inicio del servicio.
     *
     * @param horaInicio Hora de inicio a establecer para el servicio.
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtiene la hora de finalización del servicio.
     *
     * @return horaFin Hora de finalización del servicio.
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Establece la hora de finalización del servicio.
     *
     * @param horaFin Hora de finalización a establecer para el servicio.
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Obtiene el estado del evento privado.
     *
     * @return eventoPrivado Indica si el servicio es para un evento privado.
     */
    public int getEventoPrivado() {
        return eventoPrivado;
    }

    /**
     * Establece el estado del evento privado.
     *
     * @param evento Indica si el servicio es para un evento privado.
     */
    public void setEventoPrivado(int evento) {
        this.eventoPrivado = evento;
    }
}

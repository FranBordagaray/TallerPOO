package Modelo.Empleado;

/**
 * Clase que representa los reportes generados en el sistema, incluyendo
 * información sobre reservas, capacidad y clientes.
 */
public class Reportes {
    
    // Variables de clase
    private int totalReservas;
    private int totalCapacidad;
    private String nombre;
    private String apellido;
    private String fecha;
    private String hora;
    private int capacidad;
    private String ubicacion;
    private String comentario;

    /**
     * Constructor personalizado para crear un reporte de temporadas.
     *
     * @param totalReservas    Total de reservas en la temporada.
     * @param totalCapacidad   Capacidad total en la temporada.
     */
    public Reportes(int totalReservas, int totalCapacidad) {
        this.totalReservas = totalReservas;
        this.totalCapacidad = totalCapacidad;
    }

    /**
     * Constructor personalizado para crear un reporte del cliente más frecuente.
     *
     * @param nombre          Nombre del cliente.
     * @param apellido        Apellido del cliente.
     * @param totalReservas   Total de reservas del cliente.
     */
    public Reportes(String nombre, String apellido, int totalReservas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.totalReservas = totalReservas;
    }

    /**
     * Constructor de clase que inicializa todos los atributos de un reporte.
     *
     * @param nombre        Nombre del cliente.
     * @param apellido      Apellido del cliente.
     * @param fecha         Fecha de la reserva.
     * @param hora          Hora de la reserva.
     * @param capacidad     Capacidad de la mesa.
     * @param ubicacion     Ubicación de la mesa.
     * @param comentario    Comentarios adicionales sobre la reserva.
     */
    public Reportes(String nombre, String apellido, String fecha, String hora, int capacidad, String ubicacion, String comentario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
    }

    // Getters

    /**
     * Obtiene el total de reservas.
     *
     * @return Total de reservas.
     */
    public int getTotalReservas() {
        return totalReservas;
    }

    /**
     * Obtiene la capacidad total.
     *
     * @return Capacidad total.
     */
    public int getTotalCapacidad() {
        return totalCapacidad;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido del cliente.
     *
     * @return Apellido del cliente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene la fecha de la reserva.
     *
     * @return Fecha de la reserva.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene la hora de la reserva.
     *
     * @return Hora de la reserva.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Obtiene la capacidad de la mesa.
     *
     * @return Capacidad de la mesa.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Obtiene la ubicación de la mesa.
     *
     * @return Ubicación de la mesa.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Obtiene los comentarios adicionales sobre la reserva.
     *
     * @return Comentarios sobre la reserva.
     */
    public String getComentario() {
        return comentario;
    }
}
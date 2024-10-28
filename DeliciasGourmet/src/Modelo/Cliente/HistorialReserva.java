package Modelo.Cliente;

/**
 * Clase que representa el historial de una reserva realizada por un cliente,
 * almacenando información como fecha, hora, mesa, capacidad y comentarios.
 */
public class HistorialReserva {
    private int idReserva;
    private String nombre;
    private String apellido;
    private String fecha;
    private String hora;
    private int idMesa;
    private int capacidad;
    private String ubicacion;
    private String comentario;
    private int estado;

    /**
     * Constructor para crear un historial de reserva con todos los atributos.
     *
     * @param idReserva  Identificador de la reserva.
     * @param fecha      Fecha de la reserva en formato AAAA-MM-DD.
     * @param hora       Hora de la reserva en formato HH:MM.
     * @param idMesa     Identificador de la mesa reservada.
     * @param capacidad  Capacidad de la mesa.
     * @param ubicacion  Ubicación de la mesa en el restaurante.
     * @param comentario Comentario adicional sobre la reserva.
     * @param estado     Estado de la reserva (por ejemplo, 1 para activa, 0 para cancelada).
     */
    public HistorialReserva(int idReserva, String fecha, String hora, int idMesa, int capacidad, String ubicacion, String comentario, int estado) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
        this.estado = estado;
    }

    /**
     * Constructor para crear un historial de reserva sin el estado.
     *
     * @param idReserva  Identificador de la reserva.
     * @param fecha      Fecha de la reserva en formato AAAA-MM-DD.
     * @param hora       Hora de la reserva en formato HH:MM.
     * @param idMesa     Identificador de la mesa reservada.
     * @param capacidad  Capacidad de la mesa.
     * @param ubicacion  Ubicación de la mesa en el restaurante.
     * @param comentario Comentario adicional sobre la reserva.
     */
    public HistorialReserva(int idReserva, String fecha, String hora, int idMesa, int capacidad, String ubicacion, String comentario) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.comentario = comentario;
    }

    /**
     * Constructor que incluye nombre, apellido y estado, junto con los demás atributos.
     *
     * @param idReserva  Identificador de la reserva.
     * @param nombre     Nombre del cliente que realizó la reserva.
     * @param apellido   Apellido del cliente que realizó la reserva.
     * @param fecha      Fecha de la reserva en formato AAAA-MM-DD.
     * @param hora       Hora de la reserva en formato HH:MM.
     * @param idMesa     Identificador de la mesa reservada.
     * @param capacidad  Capacidad de la mesa.
     * @param ubicacion  Ubicación de la mesa en el restaurante.
     * @param estado     Estado de la reserva.
     * @param comentario Comentario adicional sobre la reserva.
     */
    public HistorialReserva(int idReserva, String nombre, String apellido, String fecha, String hora, int idMesa, int capacidad, String ubicacion, int estado, String comentario) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.hora = hora;
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.comentario = comentario;
    }

    /**
     * Constructor para crear un historial de reserva solo con ubicación, fecha, hora, capacidad, ID de mesa y comentario.
     *
     * @param ubicacion  Ubicación de la mesa en el restaurante.
     * @param fecha      Fecha de la reserva en formato AAAA-MM-DD.
     * @param hora       Hora de la reserva en formato HH:MM.
     * @param capacidad  Capacidad de la mesa.
     * @param idMesa     Identificador de la mesa reservada.
     * @param comentario Comentario adicional sobre la reserva.
     */
    public HistorialReserva(String ubicacion, String fecha, String hora, int capacidad, int idMesa, String comentario) {
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.idMesa = idMesa;
        this.comentario = comentario;
    }

    /**
     * Obtiene el identificador de la reserva.
     * 
     * @return idReserva Identificador de la reserva.
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return nombre Nombre del cliente.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene el apellido del cliente.
     * 
     * @return apellido Apellido del cliente.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * Obtiene la fecha de la reserva.
     * 
     * @return fecha Fecha de la reserva en formato AAAA-MM-DD.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene la hora de la reserva.
     * 
     * @return hora Hora de la reserva en formato HH:MM.
     */
    public String getHora() {
        return hora;
    }

    /**
     * Obtiene el identificador de la mesa reservada.
     * 
     * @return idMesa Identificador de la mesa reservada.
     */
    public int getIdMesa() {
        return idMesa;
    }

    /**
     * Obtiene la capacidad de la mesa reservada.
     * 
     * @return capacidad Capacidad de la mesa reservada.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Obtiene la ubicación de la mesa en el restaurante.
     * 
     * @return ubicacion Ubicación de la mesa.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Obtiene el comentario asociado a la reserva.
     * 
     * @return comentario Comentario de la reserva.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Obtiene el estado de la reserva.
     * 
     * @return estado Estado de la reserva (por ejemplo, 1 para activa, 0 para cancelada).
     */
    public int getEstado() {
        return estado;
    }
}
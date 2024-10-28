package Modelo.Empleado;

import Modelo.Complementos.Persona;

/**
 * Clase que representa un empleado en el sistema.
 * Hereda de la clase Persona.
 */
public class Empleado extends Persona {
    
    /** Identificador único del empleado. */
    private int idEmpleado;

    /** Rol del empleado, representado por la enumeración EnumRoles. */
    private EnumRoles rol;

    /** Estado del empleado (activo, inactivo, etc.). */
    private String estado;

    /**
     * Constructor vacío de la clase Empleado.
     */
    public Empleado() {
    }

    /**
     * Obtiene el rol del empleado.
     *
     * @return rol Rol del empleado.
     */
    public EnumRoles getRol() {
        return rol;
    }

    /**
     * Establece el rol del empleado.
     *
     * @param rol Rol a establecer para el empleado.
     */
    public void setRol(EnumRoles rol) {
        this.rol = rol;
    }
    
    /**
     * Obtiene el identificador del empleado.
     *
     * @return idEmpleado Identificador del empleado.
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param idEmpleado Identificador a establecer para el empleado.
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Obtiene el estado del empleado.
     *
     * @return estado Estado del empleado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del empleado.
     *
     * @param estado Estado a establecer para el empleado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

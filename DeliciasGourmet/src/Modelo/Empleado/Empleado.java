package Modelo.Empleado;

import Modelo.Complementos.Persona;

public class Empleado extends Persona{
	
	// Variables de clase
	private int idEmpleado;
	private EnumRoles rol;
	private String estado;
	
	// Constructor de clase
	public Empleado() {
	}
	// Getter y Setter de rol
    public EnumRoles getRol() {
        return rol;
    }
    public void setRol(EnumRoles rol) {
        this.rol = rol;
    }
    
    // Getter y Setter de idEmpleado
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
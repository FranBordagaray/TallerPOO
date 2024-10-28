package Modelo.Complementos;

/**
 * Representa una persona en el sistema, que puede ser un cliente o un empleado.
 * Contiene información personal como nombre, apellido, domicilio, teléfono,
 * email, usuario y contraseña.
 */
public class Persona {

    // Variables de clase
    private String nombre;
    private String apellido;
    private String domicilio;
    private String telefono;
    private String email;
    private String usuario;
    private String contrasenia;

    /**
     * Constructor por defecto para crear una instancia de Persona sin valores iniciales.
     */
    public Persona() {
    }

    /**
     * Obtiene el nombre de la persona.
     * 
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre a la persona.
     * 
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido de la persona.
     * 
     * @return El apellido de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna un apellido a la persona.
     * 
     * @param apellido El apellido a asignar.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el domicilio de la persona.
     * 
     * @return El domicilio de la persona.
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Asigna un domicilio a la persona.
     * 
     * @param domicilio El domicilio a asignar.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Obtiene el teléfono de la persona.
     * 
     * @return El teléfono de la persona.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna un teléfono a la persona.
     * 
     * @param telefono El teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el email de la persona.
     * 
     * @return El email de la persona.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna un email a la persona.
     * 
     * @param email El email a asignar.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el nombre de usuario de la persona.
     * 
     * @return El nombre de usuario de la persona.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Asigna un nombre de usuario a la persona.
     * 
     * @param usuario El nombre de usuario a asignar.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña de la persona.
     * 
     * @return La contraseña de la persona.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Asigna una contraseña a la persona.
     * 
     * @param contrasenia La contraseña a asignar.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
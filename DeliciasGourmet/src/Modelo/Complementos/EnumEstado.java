package Modelo.Complementos;

/**
 * Enumeración que representa los posibles estados de una mesa en el sistema de reservas.
 * 
 * <ul>
 *     <li>LIBRE: La mesa está disponible para nuevas reservas.</li>
 *     <li>OCUPADA: La mesa está actualmente en uso.</li>
 *     <li>BLOQUEADA: La mesa no está disponible temporalmente, posiblemente debido a mantenimiento o restricción.</li>
 * </ul>
 */
public enum EnumEstado {
    /** La mesa está disponible para reservas. */
    LIBRE,
    
    /** La mesa está ocupada y no puede reservarse. */
    OCUPADA,
    
    /** La mesa está bloqueada temporalmente y no disponible para reservas. */
    BLOQUEADA;
}
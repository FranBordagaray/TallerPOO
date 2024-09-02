package Vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

public class Reporte extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Reporte() {
		// Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));
        
        // Etiqueta de reporte
        JLabel lblReportes = new JLabel("REPORTES");
        lblReportes.setFont(new Font("Roboto Light", Font.BOLD, 32));
        lblReportes.setBounds(414, 15, 164, 38);
        add(lblReportes);
        
        // Etiqueta y separador de reservas futuras de cliente
        JLabel lblReservasFuturasCliente = new JLabel("Reservas Futuras por Cliente");
        lblReservasFuturasCliente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasFuturasCliente.setBounds(346, 90, 300, 27);
        add(lblReservasFuturasCliente);
        
        JSeparator separadorCliente = new JSeparator();
        separadorCliente.setForeground(Color.BLACK);
        separadorCliente.setBackground(Color.BLACK);
        separadorCliente.setBounds(0, 180, 992, 2);
        add(separadorCliente);
 
        // Etiqueta y separador de historia de reservas de cliente
        JLabel lblHistorialReservaCliente = new JLabel("Historial de Reservas del Cliente");
        lblHistorialReservaCliente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblHistorialReservaCliente.setBounds(328, 190, 335, 27);
        add(lblHistorialReservaCliente);
        
        JSeparator separadorHistorial = new JSeparator();
        separadorHistorial.setForeground(Color.BLACK);
        separadorHistorial.setBackground(Color.BLACK);
        separadorHistorial.setBounds(0, 280, 992, 2);
        add(separadorHistorial);
    
        // Etiqueta y separador de cliente mas frecuente
        JLabel lblClienteMasFrecuente = new JLabel("Cliente Más Frecuente");
        lblClienteMasFrecuente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClienteMasFrecuente.setBounds(378, 290, 235, 27);
        add(lblClienteMasFrecuente);
        
        JSeparator separadorClienteFrecuente = new JSeparator();
        separadorClienteFrecuente.setForeground(Color.BLACK);
        separadorClienteFrecuente.setBackground(Color.BLACK);
        separadorClienteFrecuente.setBounds(0, 380, 992, 2);
        add(separadorClienteFrecuente);
	 
        // Etiqueta y separador de clientes ausentes ultimo año
        JLabel lblClientesNoAsistentesUltimoAnio = new JLabel("Clientes No Asistentes del Último Año");
        lblClientesNoAsistentesUltimoAnio.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClientesNoAsistentesUltimoAnio.setBounds(300, 390, 391, 27);
        add(lblClientesNoAsistentesUltimoAnio);
        
        JSeparator separadorClienteAusente = new JSeparator();
        separadorClienteAusente.setForeground(Color.BLACK);
        separadorClienteAusente.setBackground(Color.BLACK);
        separadorClienteAusente.setBounds(0, 480, 992, 2);
        add(separadorClienteAusente);

        // Etiqueta y separador de reservas entre fechas
        JLabel lblReservasDetalladasEntreFechas = new JLabel("Reservas Detalladas Entre Fechas");
        lblReservasDetalladasEntreFechas.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasDetalladasEntreFechas.setBounds(319, 490, 354, 27);
        add(lblReservasDetalladasEntreFechas);
        
        JSeparator separadorReservasEntreFechas = new JSeparator();
        separadorReservasEntreFechas.setForeground(Color.BLACK);
        separadorReservasEntreFechas.setBackground(Color.BLACK);
        separadorReservasEntreFechas.setBounds(0, 580, 992, 2);
        add(separadorReservasEntreFechas);
        
        // Etiqueta de concurrencia por temporada
        JLabel lblConcurrenciaPorTemporada = new JLabel("Concurrencia por Temporada");
        lblConcurrenciaPorTemporada.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblConcurrenciaPorTemporada.setBounds(358, 590, 305, 27);
        add(lblConcurrenciaPorTemporada);
	}
}

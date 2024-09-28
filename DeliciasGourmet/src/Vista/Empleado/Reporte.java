package Vista.Empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

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

        // Etiquetas, seleccion de fecha, boton y separador de reservas entre fechas
        JLabel lblReservasDetalladasEntreFechas = new JLabel("Reservas Detalladas Entre Fechas");
        lblReservasDetalladasEntreFechas.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasDetalladasEntreFechas.setBounds(319, 490, 354, 27);
        add(lblReservasDetalladasEntreFechas);
        
        JLabel lblDesde = new JLabel("Desde:");
        lblDesde.setForeground(Color.BLACK);
        lblDesde.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesde.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblDesde.setBounds(57, 540, 100, 30);
        add(lblDesde);
        
        JDateChooser dateDesde = new JDateChooser();
        dateDesde.setBorder(null);
        dateDesde.setBackground(Color.WHITE);
        dateDesde.setForeground(Color.BLACK);
        dateDesde.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        dateDesde.setBounds(214, 540, 150, 30);
        add(dateDesde);
        
        JLabel lblHasta = new JLabel("Hasta:");
        lblHasta.setForeground(Color.BLACK);
        lblHasta.setHorizontalAlignment(SwingConstants.CENTER);
        lblHasta.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblHasta.setBounds(421, 540, 100, 30);
        add(lblHasta);
        
        JDateChooser dateHasta = new JDateChooser();
        dateHasta.setForeground(Color.BLACK);
        dateHasta.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        dateHasta.setBorder(null);
        dateHasta.setBackground(Color.WHITE);
        dateHasta.setBounds(578, 540, 150, 30);
        add(dateHasta);
        
        JButton btnBuscarReservas = new JButton("BUSCAR");
        btnBuscarReservas.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnBuscarReservas.setBackground(new Color(255, 0, 0));
        		btnBuscarReservas.setForeground(Color.WHITE);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnBuscarReservas.setBackground(Color.WHITE);
        		btnBuscarReservas.setForeground(Color.BLACK);
        	}
        });
        btnBuscarReservas.setForeground(Color.BLACK);
        btnBuscarReservas.setBackground(Color.WHITE);
        btnBuscarReservas.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBuscarReservas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscarReservas.setBorder(null);
        btnBuscarReservas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarReservas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnBuscarReservas.setBounds(785, 540, 150, 30);
        add(btnBuscarReservas);
        
        JSeparator separadorReservasEntreFechas = new JSeparator();
        separadorReservasEntreFechas.setForeground(Color.BLACK);
        separadorReservasEntreFechas.setBackground(Color.BLACK);
        separadorReservasEntreFechas.setBounds(0, 580, 992, 2);
        add(separadorReservasEntreFechas);
        
        // Etiqueta y botones para seleccion de estaciones de concurrencia
        JLabel lblConcurrenciaPorTemporada = new JLabel("Concurrencia por Temporada");
        lblConcurrenciaPorTemporada.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblConcurrenciaPorTemporada.setBounds(358, 590, 305, 27);
        add(lblConcurrenciaPorTemporada);
        
        JButton btnPrimavera = new JButton("PRIMAVERA");
        btnPrimavera.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnPrimavera.setBackground(new Color(144, 238, 144));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnPrimavera.setBackground(Color.WHITE);
        	}
        });
        btnPrimavera.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPrimavera.setForeground(Color.BLACK);
        btnPrimavera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPrimavera.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnPrimavera.setBorder(null);
        btnPrimavera.setBackground(Color.WHITE);
        btnPrimavera.setAlignmentX(0.5f);
        btnPrimavera.setBounds(78, 640, 150, 30);
        add(btnPrimavera);
        
        JButton btnVerano = new JButton("VERANO");
        btnVerano.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnVerano.setBackground(new Color(255, 255, 0));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnVerano.setBackground(Color.WHITE);
        	}
        });
        btnVerano.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVerano.setForeground(Color.BLACK);
        btnVerano.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVerano.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnVerano.setBorder(null);
        btnVerano.setBackground(Color.WHITE);
        btnVerano.setAlignmentX(0.5f);
        btnVerano.setBounds(306, 640, 150, 30);
        add(btnVerano);
        
        JButton btnOtonio = new JButton("OTOÑO");
        btnOtonio.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnOtonio.setBackground(new Color(139, 69, 19));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnOtonio.setBackground(Color.WHITE);
        	}
        });
        btnOtonio.setHorizontalTextPosition(SwingConstants.CENTER);
        btnOtonio.setForeground(Color.BLACK);
        btnOtonio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnOtonio.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnOtonio.setBorder(null);
        btnOtonio.setBackground(Color.WHITE);
        btnOtonio.setAlignmentX(0.5f);
        btnOtonio.setBounds(534, 640, 150, 30);
        add(btnOtonio);
        
        JButton btnInvierno = new JButton("INVIERNO");
        btnInvierno.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnInvierno.setBackground(new Color(173, 216, 230));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnInvierno.setBackground(Color.WHITE);
        	}
        });
        btnInvierno.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInvierno.setForeground(Color.BLACK);
        btnInvierno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnInvierno.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnInvierno.setBorder(null);
        btnInvierno.setBackground(Color.WHITE);
        btnInvierno.setAlignmentX(0.5f);
        btnInvierno.setBounds(762, 640, 150, 30);
        add(btnInvierno);
        
	}
}

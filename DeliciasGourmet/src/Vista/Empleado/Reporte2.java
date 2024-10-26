
package Vista.Empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import Controlador.ReservaControlador;
import Modelo.Empleado.Reportes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Reporte2 extends JPanel {
    private ReservaControlador controlador;
    private SeleccionarCliente seleccionarCliente;

    private static final long serialVersionUID = 1L;
    public Reporte2() {
        controlador = new ReservaControlador();
        // Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));

        // Etiqueta de reporte
        JLabel lblReportes = new JLabel("REPORTES");
        lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
        lblReportes.setFont(new Font("Roboto Light", Font.BOLD, 32));
        lblReportes.setBounds(397, 15, 197, 38);
        add(lblReportes);

        // Etiqueta y separador de reservas futuras de cliente
        JLabel lblReservasFuturasCliente = new JLabel("Reservas Futuras por Cliente");
        lblReservasFuturasCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblReservasFuturasCliente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasFuturasCliente.setBounds(1, 123, 494, 27);
        add(lblReservasFuturasCliente);

        JSeparator separadorCliente = new JSeparator();
        separadorCliente.setForeground(Color.BLACK);
        separadorCliente.setBackground(Color.BLACK);
        separadorCliente.setBounds(0, 270, 992, 2);
        add(separadorCliente);

        // Etiqueta y separador de historia de reservas de cliente
        JLabel lblHistorialReservaCliente = new JLabel("Historial de Reservas del Cliente");
        lblHistorialReservaCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorialReservaCliente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblHistorialReservaCliente.setBounds(496, 123, 494, 27);
        add(lblHistorialReservaCliente);

        JSeparator separadorHistorial = new JSeparator();
        separadorHistorial.setForeground(Color.BLACK);
        separadorHistorial.setBackground(Color.BLACK);
        separadorHistorial.setBounds(-2, 481, 992, 2);
        add(separadorHistorial);

        // Etiqueta, boton y separador de cliente más frecuente
        JLabel lblClienteMasFrecuente = new JLabel("Cliente Más Frecuente");
        lblClienteMasFrecuente.setHorizontalAlignment(SwingConstants.CENTER);
        lblClienteMasFrecuente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClienteMasFrecuente.setBounds(1, 331, 494, 30);
        add(lblClienteMasFrecuente);
        
        JButton btnClienteFrecuente = new JButton("BUSCAR");
        btnClienteFrecuente.setIcon(new ImageIcon(Reporte2.class.getResource("/Img/icono de buscar.png")));
        btnClienteFrecuente.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
        		Reportes clienteFrecuente = controlador.obtenerClienteMasFrecuente(); // Obtener el cliente más frecuente

        	    Document documento = new Document();
        	    String ruta = System.getProperty("user.home") + "\\Desktop\\Cliente_Mas_Frecuente.pdf";
        	    File archivo = new File(ruta);
        	    if (archivo.exists()) {
        	        String nuevoNombre = "Cliente_Mas_Frecuente_" + System.currentTimeMillis() + ".pdf";
        	        ruta = System.getProperty("user.home") + "\\Desktop\\" + nuevoNombre;
        	    }

        	    try {
        	        PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        	        documento.open();
        	        
        	        // Título del reporte
        	        documento.add(new Paragraph("Reporte del Cliente Más Frecuente", 
        	            FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
        	        
        	        documento.add(new Paragraph(" "));
        	        documento.add(new Paragraph("Información del cliente:"));
        	        documento.add(new Paragraph("Nombre y Apellido: " + clienteFrecuente.getNombre()+" " + clienteFrecuente.getApellido()));
        	        documento.add(new Paragraph("Reservas realizadas: " + clienteFrecuente.getTotalReservas()));
        	        documento.add(new Paragraph(" "));

        	        JOptionPane.showMessageDialog(null, "PDF generado con éxito en el escritorio: " + ruta);
        	    } catch (DocumentException | IOException ex) {
        	        ex.printStackTrace();
        	        JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + ex.getMessage());
        	    } finally {
        	        if (documento.isOpen()) {
        	            documento.close();
        	        }
        	    }
        	}
        });
        btnClienteFrecuente.addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e) {
        		btnClienteFrecuente.setBackground(new Color(255, 0, 0));
        		btnClienteFrecuente.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnClienteFrecuente.setBackground(Color.WHITE);
            	btnClienteFrecuente.setForeground(Color.BLACK);
            }
        });
        btnClienteFrecuente.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnClienteFrecuente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClienteFrecuente.setBorder(null);
        btnClienteFrecuente.setForeground(Color.BLACK);
        btnClienteFrecuente.setBackground(Color.WHITE);
        btnClienteFrecuente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnClienteFrecuente.setBounds(166, 404, 150, 30);
        add(btnClienteFrecuente);

        // Etiqueta y separador de clientes ausentes último año
        JLabel lblClientesNoAsistentesUltimoAnio = new JLabel("Clientes No Asistentes del Último Año");
        lblClientesNoAsistentesUltimoAnio.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientesNoAsistentesUltimoAnio.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClientesNoAsistentesUltimoAnio.setBounds(496, 331, 494, 30);
        add(lblClientesNoAsistentesUltimoAnio);

        JSeparator separadorClienteAusente = new JSeparator();
        separadorClienteAusente.setOrientation(SwingConstants.VERTICAL);
        separadorClienteAusente.setForeground(Color.BLACK);
        separadorClienteAusente.setBackground(Color.BLACK);
        separadorClienteAusente.setBounds(495, 50, 2, 635);
        add(separadorClienteAusente);

        // Etiquetas, selección de fecha, botón y separador de reservas entre fechas
        JLabel lblReservasDetalladasEntreFechas = new JLabel("Reservas Detalladas Entre Fechas");
        lblReservasDetalladasEntreFechas.setHorizontalAlignment(SwingConstants.CENTER);
        lblReservasDetalladasEntreFechas.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasDetalladasEntreFechas.setBounds(1, 532, 494, 30);
        add(lblReservasDetalladasEntreFechas);

        // Etiqueta y botones para selección de estaciones de concurrencia
        JLabel lblConcurrenciaPorTemporada = new JLabel("Concurrencia por Temporada");
        lblConcurrenciaPorTemporada.setHorizontalAlignment(SwingConstants.CENTER);
        lblConcurrenciaPorTemporada.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblConcurrenciaPorTemporada.setBounds(496, 532, 494, 30);
        add(lblConcurrenciaPorTemporada);
        
        JButton btnReservasFuturas = new JButton("BUSCAR");
        btnReservasFuturas.setIcon(new ImageIcon(Reporte2.class.getResource("/Img/icono de buscar.png")));
        btnReservasFuturas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		seleccionarCliente = new SeleccionarCliente("Reservas Futuras");
        		seleccionarCliente.setVisible(true);
        	}
        });
        btnReservasFuturas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReservasFuturas.setForeground(Color.BLACK);
        btnReservasFuturas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnReservasFuturas.setBorder(null);
        btnReservasFuturas.setBackground(Color.WHITE);
        btnReservasFuturas.setBounds(166, 191, 150, 30);
        add(btnReservasFuturas);
        
        JButton btnReservasHistorial = new JButton("BUSCAR");
        btnReservasHistorial.setIcon(new ImageIcon(Reporte2.class.getResource("/Img/icono de buscar.png")));
        btnReservasHistorial.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		seleccionarCliente = new SeleccionarCliente("Reserva Historial");
        		seleccionarCliente.setVisible(true);
        	}
        });
        btnReservasHistorial.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReservasHistorial.setForeground(Color.BLACK);
        btnReservasHistorial.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnReservasHistorial.setBorder(null);
        btnReservasHistorial.setBackground(Color.WHITE);
        btnReservasHistorial.setBounds(690, 191, 150, 30);
        add(btnReservasHistorial);
        
        JButton btnClienteFrecuente_1 = new JButton("BUSCAR");
        btnClienteFrecuente_1.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnClienteFrecuente_1.setForeground(Color.BLACK);
        btnClienteFrecuente_1.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnClienteFrecuente_1.setBorder(null);
        btnClienteFrecuente_1.setBackground(Color.WHITE);
        btnClienteFrecuente_1.setBounds(166, 603, 150, 30);
        add(btnClienteFrecuente_1);
        
        JButton btnClienteFrecuente_1_1 = new JButton("BUSCAR");
        btnClienteFrecuente_1_1.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnClienteFrecuente_1_1.setForeground(Color.BLACK);
        btnClienteFrecuente_1_1.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnClienteFrecuente_1_1.setBorder(null);
        btnClienteFrecuente_1_1.setBackground(Color.WHITE);
        btnClienteFrecuente_1_1.setBounds(690, 404, 150, 30);
        add(btnClienteFrecuente_1_1);
        
        JButton btnClienteFrecuente_1_2 = new JButton("BUSCAR");
        btnClienteFrecuente_1_2.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnClienteFrecuente_1_2.setForeground(Color.BLACK);
        btnClienteFrecuente_1_2.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnClienteFrecuente_1_2.setBorder(null);
        btnClienteFrecuente_1_2.setBackground(Color.WHITE);
        btnClienteFrecuente_1_2.setBounds(690, 603, 150, 30);
        add(btnClienteFrecuente_1_2);
        
        JSeparator separadorHistorial_1 = new JSeparator();
        separadorHistorial_1.setForeground(Color.BLACK);
        separadorHistorial_1.setBackground(Color.BLACK);
        separadorHistorial_1.setBounds(0, 50, 992, 2);
        add(separadorHistorial_1);
    }
}
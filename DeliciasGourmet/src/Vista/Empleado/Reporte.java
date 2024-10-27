
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
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import Controlador.ReservaControlador;
import Modelo.Empleado.Reportes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Reporte extends JPanel {
    private ReservaControlador controlador;
    private SeleccionarCliente seleccionarCliente;

    private static final long serialVersionUID = 1L;
    public Reporte() {
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
        
        JSeparator separador = new JSeparator();
        separador.setForeground(Color.BLACK);
        separador.setBackground(Color.BLACK);
        separador.setBounds(0, 50, 992, 2);
        add(separador);

        // Etiqueta y separador de reservas futuras de cliente
        JLabel lblReservasFuturasCliente = new JLabel("Reservas Futuras por Cliente");
        lblReservasFuturasCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblReservasFuturasCliente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasFuturasCliente.setBounds(1, 123, 494, 27);
        add(lblReservasFuturasCliente);
        
        JButton btnReservasFuturas = new JButton("BUSCAR");
        btnReservasFuturas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReservasFuturas.setIcon(new ImageIcon(Reporte.class.getResource("/Img/icono de buscar.png")));
        btnReservasFuturas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		seleccionarCliente = new SeleccionarCliente("Reservas Futuras");
        		seleccionarCliente.setVisible(true);
        	}
        });
        btnReservasFuturas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservasFuturas.setBackground(new Color(255, 0, 0));
				btnReservasFuturas.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReservasFuturas.setBackground(Color.WHITE);
				btnReservasFuturas.setForeground(Color.BLACK);
			}
		});
        btnReservasFuturas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReservasFuturas.setForeground(Color.BLACK);
        btnReservasFuturas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnReservasFuturas.setBorder(null);
        btnReservasFuturas.setBackground(Color.WHITE);
        btnReservasFuturas.setBounds(166, 191, 150, 30);
        add(btnReservasFuturas);

        // Etiqueta y separador de historia de reservas de cliente
        JLabel lblHistorialReservaCliente = new JLabel("Historial de Reservas del Cliente");
        lblHistorialReservaCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorialReservaCliente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblHistorialReservaCliente.setBounds(496, 123, 494, 27);
        add(lblHistorialReservaCliente);
        
        JButton btnReservasHistorial = new JButton("BUSCAR");
        btnReservasHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReservasHistorial.setIcon(new ImageIcon(Reporte.class.getResource("/Img/icono de buscar.png")));
        btnReservasHistorial.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		seleccionarCliente = new SeleccionarCliente("Reserva Historial");
        		seleccionarCliente.setVisible(true);
        	}
        });
        btnReservasHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservasHistorial.setBackground(new Color(255, 0, 0));
				btnReservasHistorial.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReservasHistorial.setBackground(Color.WHITE);
				btnReservasHistorial.setForeground(Color.BLACK);
			}
		});
        btnReservasHistorial.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReservasHistorial.setForeground(Color.BLACK);
        btnReservasHistorial.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnReservasHistorial.setBorder(null);
        btnReservasHistorial.setBackground(Color.WHITE);
        btnReservasHistorial.setBounds(690, 191, 150, 30);
        add(btnReservasHistorial);
        
        JSeparator separador1 = new JSeparator();
        separador1.setForeground(Color.BLACK);
        separador1.setBackground(Color.BLACK);
        separador1.setBounds(0, 270, 992, 2);
        add(separador1);

        // Etiqueta, boton y separador de cliente más frecuente
        JLabel lblClienteMasFrecuente = new JLabel("Cliente Más Frecuente");
        lblClienteMasFrecuente.setHorizontalAlignment(SwingConstants.CENTER);
        lblClienteMasFrecuente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClienteMasFrecuente.setBounds(1, 331, 494, 30);
        add(lblClienteMasFrecuente);
        
        JButton btnClienteFrecuente = new JButton("BUSCAR");
        btnClienteFrecuente.setIcon(new ImageIcon(Reporte.class.getResource("/Img/icono de buscar.png")));
        btnClienteFrecuente.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
        		Reportes clienteFrecuente = controlador.obtenerClienteMasFrecuente();
        	    Document documento = new Document();
        	    String ruta = "src\\ReportesPDF\\Cliente_Mas_Frecuente.pdf";
        	    File archivo = new File(ruta);
        	    if (archivo.exists()) {
        	        String nuevoNombre = "Cliente_Mas_Frecuente_" + System.currentTimeMillis() + ".pdf";
        	        ruta = "src\\ReportesPDF\\" + nuevoNombre;
        	    }
        	    try {
        	        PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        	        documento.open();
        	        
        	        documento.add(new Paragraph("Reporte del Cliente Más Frecuente", 
        	            FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
        	        
        	        documento.add(new Paragraph(" "));
        	        documento.add(new Paragraph("Información del cliente:"));
        	        documento.add(new Paragraph("Nombre y Apellido: " + clienteFrecuente.getNombre()+" " + clienteFrecuente.getApellido()));
        	        documento.add(new Paragraph("Reservas realizadas: " + clienteFrecuente.getTotalReservas()));
        	        documento.add(new Paragraph(" "));

        	        JOptionPane.showMessageDialog(null, "PDF generado con éxito en: " + ruta);
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
        
        JButton btnClienteAusente = new JButton("BUSCAR");
        btnClienteAusente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClienteAusente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnClienteAusente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClienteAusente.setBackground(new Color(255, 0, 0));
				btnClienteAusente.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnClienteAusente.setBackground(Color.WHITE);
				btnClienteAusente.setForeground(Color.BLACK);
			}
		});
        btnClienteAusente.setIcon(new ImageIcon(Reporte.class.getResource("/Img/icono de buscar.png")));
        btnClienteAusente.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnClienteAusente.setForeground(Color.BLACK);
        btnClienteAusente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnClienteAusente.setBorder(null);
        btnClienteAusente.setBackground(Color.WHITE);
        btnClienteAusente.setBounds(690, 404, 150, 30);
        add(btnClienteAusente);
        
        JSeparator separadorHistorial = new JSeparator();
        separadorHistorial.setForeground(Color.BLACK);
        separadorHistorial.setBackground(Color.BLACK);
        separadorHistorial.setBounds(-2, 481, 992, 2);
        add(separadorHistorial);

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
        
        JButton btnReservasEntreFechas = new JButton("BUSCAR");
        btnReservasEntreFechas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReservasEntreFechas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReportePorFecha reportes2 = new ReportePorFecha();
        		reportes2.setVisible(true);
        	}
        });
        btnReservasEntreFechas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservasEntreFechas.setBackground(new Color(255, 0, 0));
				btnReservasEntreFechas.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReservasEntreFechas.setBackground(Color.WHITE);
				btnReservasEntreFechas.setForeground(Color.BLACK);
			}
		});
        btnReservasEntreFechas.setIcon(new ImageIcon(Reporte.class.getResource("/Img/icono de buscar.png")));
        btnReservasEntreFechas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReservasEntreFechas.setForeground(Color.BLACK);
        btnReservasEntreFechas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnReservasEntreFechas.setBorder(null);
        btnReservasEntreFechas.setBackground(Color.WHITE);
        btnReservasEntreFechas.setBounds(166, 603, 150, 30);
        add(btnReservasEntreFechas);

        // Etiqueta y botones para selección de estaciones de concurrencia
        JLabel lblConcurrenciaPorTemporada = new JLabel("Concurrencia por Temporada");
        lblConcurrenciaPorTemporada.setHorizontalAlignment(SwingConstants.CENTER);
        lblConcurrenciaPorTemporada.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblConcurrenciaPorTemporada.setBounds(496, 532, 494, 30);
        add(lblConcurrenciaPorTemporada);
        
        JButton btnTemporadas = new JButton("BUSCAR");
        btnTemporadas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReportePorTemporada reportes = new ReportePorTemporada();
        		reportes.setVisible(true);
        	}
        });
        btnTemporadas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTemporadas.setBackground(new Color(255, 0, 0));
				btnTemporadas.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnTemporadas.setBackground(Color.WHITE);
				btnTemporadas.setForeground(Color.BLACK);
			}
		});
        btnTemporadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTemporadas.setIcon(new ImageIcon(Reporte.class.getResource("/Img/icono de buscar.png")));
        btnTemporadas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnTemporadas.setForeground(Color.BLACK);
        btnTemporadas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnTemporadas.setBorder(null);
        btnTemporadas.setBackground(Color.WHITE);
        btnTemporadas.setBounds(690, 603, 150, 30);
        add(btnTemporadas);
        
        
    }
}
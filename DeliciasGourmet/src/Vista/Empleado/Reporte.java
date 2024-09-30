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
import Modelo.Reportes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reporte extends JPanel {
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private ReservaControlador controlador;

    private static final long serialVersionUID = 1L;

    public Reporte() {
        controlador = new ReservaControlador();
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

        // Etiqueta y separador de cliente más frecuente
        JLabel lblClienteMasFrecuente = new JLabel("Cliente Más Frecuente");
        lblClienteMasFrecuente.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClienteMasFrecuente.setBounds(378, 290, 235, 27);
        add(lblClienteMasFrecuente);

        JSeparator separadorClienteFrecuente = new JSeparator();
        separadorClienteFrecuente.setForeground(Color.BLACK);
        separadorClienteFrecuente.setBackground(Color.BLACK);
        separadorClienteFrecuente.setBounds(0, 380, 992, 2);
        add(separadorClienteFrecuente);

        // Etiqueta y separador de clientes ausentes último año
        JLabel lblClientesNoAsistentesUltimoAnio = new JLabel("Clientes No Asistentes del Último Año");
        lblClientesNoAsistentesUltimoAnio.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblClientesNoAsistentesUltimoAnio.setBounds(300, 390, 391, 27);
        add(lblClientesNoAsistentesUltimoAnio);

        JSeparator separadorClienteAusente = new JSeparator();
        separadorClienteAusente.setForeground(Color.BLACK);
        separadorClienteAusente.setBackground(Color.BLACK);
        separadorClienteAusente.setBounds(0, 480, 992, 2);
        add(separadorClienteAusente);

        // Etiquetas, selección de fecha, botón y separador de reservas entre fechas
        JLabel lblReservasDetalladasEntreFechas = new JLabel("Reservas Detalladas Entre Fechas");
        lblReservasDetalladasEntreFechas.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblReservasDetalladasEntreFechas.setBounds(319, 490, 354, 27);
        add(lblReservasDetalladasEntreFechas);

        // Etiqueta y campo de texto de fecha "desde"
        JLabel lblDesde = new JLabel("Desde:");
        lblDesde.setForeground(Color.BLACK);
        lblDesde.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesde.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblDesde.setBounds(57, 540, 100, 30);
        add(lblDesde);

        dateDesde = new JDateChooser();
        dateDesde.setBorder(null);
        dateDesde.setBackground(Color.WHITE);
        dateDesde.setForeground(Color.BLACK);
        dateDesde.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        dateDesde.setBounds(214, 540, 150, 30);
        add(dateDesde);

        // Etiqueta y campo de texto de fecha "hasta"
        JLabel lblHasta = new JLabel("Hasta:");
        lblHasta.setForeground(Color.BLACK);
        lblHasta.setHorizontalAlignment(SwingConstants.CENTER);
        lblHasta.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        lblHasta.setBounds(421, 540, 100, 30);
        add(lblHasta);

        dateHasta = new JDateChooser();
        dateHasta.setForeground(Color.BLACK);
        dateHasta.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        dateHasta.setBorder(null);
        dateHasta.setBackground(Color.WHITE);
        dateHasta.setBounds(578, 540, 150, 30);
        add(dateHasta);

        // Boton para buscar reservas entre fechas
        JButton btnBuscarReservas = new JButton("BUSCAR");
        btnBuscarReservas.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate fechaDesde = dateDesde.getDate() != null
                        ? dateDesde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        : null;
                LocalDate fechaHasta = dateHasta.getDate() != null
                        ? dateHasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        : null;

                String desde = (fechaDesde != null) ? fechaDesde.format(formato) : null;
                String hasta = (fechaHasta != null) ? fechaHasta.format(formato) : null;

                if (desde == null || hasta == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.", "Advertencia",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Document documento = new Document();
                String ruta = System.getProperty("user.home") + "\\Desktop\\Reservas_entre_fechas.pdf";
                File archivo = new File(ruta);

                if (archivo.exists()) {
                    String nuevoNombre = "Reservas_entre_fechas_" + System.currentTimeMillis() + ".pdf";
                    ruta = System.getProperty("user.home") + "\\Desktop\\" + nuevoNombre;
                }

                try {
                    PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
                    documento.open();

                    documento.add(new Paragraph("Reporte de Reservas Entre Fechas", FontFactory.getFont("Roboto Light",
                            BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
                    documento.add(new Paragraph("Desde: " + desde));
                    documento.add(new Paragraph("Hasta: " + hasta));
                    documento.add(new Paragraph(" "));

                    List<Reportes> reservasEntreFechas = controlador.obtenerHistorialDeReservas(desde, hasta);

                    PdfPTable table = new PdfPTable(7);
                    table.setWidthPercentage(100);

                    table.addCell("Nombre");
                    table.addCell("Apellido");
                    table.addCell("Fecha");
                    table.addCell("Hora");
                    table.addCell("Capacidad de Mesa");
                    table.addCell("Ubicación");
                    table.addCell("Comentario");

                    for (Reportes reserva : reservasEntreFechas) {
                        table.addCell(reserva.getNombre());
                        table.addCell(reserva.getApellido());
                        table.addCell(reserva.getFecha());
                        table.addCell(reserva.getHora());
                        table.addCell(String.valueOf(reserva.getCapacidad()));
                        table.addCell(reserva.getUbicacion());
                        table.addCell(reserva.getComentario());
                    }
                    documento.add(table);
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

        // Etiqueta y botones para selección de estaciones de concurrencia
        JLabel lblConcurrenciaPorTemporada = new JLabel("Concurrencia por Temporada");
        lblConcurrenciaPorTemporada.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblConcurrenciaPorTemporada.setBounds(358, 590, 305, 27);
        add(lblConcurrenciaPorTemporada);

        JButton btnPrimavera = new JButton("PRIMAVERA");
        /*
         * btnPrimavera.addActionListener(new ActionListener() {
         * 
         * @SuppressWarnings("unused")
         * public void actionPerformed(ActionEvent e) {
         * String fechaDesde = "01-11-2024";
         * String fechaHasta = "30-11-2024";
         * 
         * List<Reportes> reporteReservas =
         * controlador.obtenerHistorialComensalesPorTemporada(fechaDesde, fechaHasta);
         * 
         * Document documento = new Document();
         * String ruta = System.getProperty("user.home") +
         * "\\Desktop\\Concurrencia_primavera.pdf";
         * File archivo = new File(ruta);
         * if (archivo.exists()) {
         * String nuevoNombre = "Concurrencia_primavera_" + System.currentTimeMillis() +
         * ".pdf";
         * ruta = System.getProperty("user.home") + "\\Desktop\\" + nuevoNombre;
         * }
         * try {
         * PdfWriter writer = PdfWriter.getInstance(documento, new
         * FileOutputStream(ruta));
         * documento.open();
         * documento.add(new Paragraph("Reporte de Reservas por Temporada",
         * FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
         * 16, Font.BOLD)));
         * documento.add(new Paragraph("Temporada: PRIMAVERA "));
         * documento.add(new Paragraph(" "));
         * 
         * List<Reportes> reservasEntreFechas =
         * controlador.obtenerHistorialComensalesPorTemporada(fechaDesde, fechaHasta);
         * 
         * PdfPTable table = new PdfPTable(2);
         * table.setWidthPercentage(100);
         * 
         * table.addCell("Total de reservas");
         * table.addCell("Cantidad de comensales");
         * 
         * for (Reportes reserva : reservasEntreFechas) {
         * table.addCell(String.valueOf(reserva.getTotalReservas()));
         * System.out.println(String.valueOf(reserva.getTotalReservas()));
         * table.addCell(String.valueOf(reserva.getTotalCapacidad()));
         * System.out.println(String.valueOf(reserva.getTotalCapacidad()));
         * }
         * documento.add(table);
         * JOptionPane.showMessageDialog(null,
         * "PDF generado con éxito en el escritorio: " + ruta);
         * } catch (DocumentException | IOException ex) {
         * ex.printStackTrace();
         * JOptionPane.showMessageDialog(null, "Error al generar el PDF: " +
         * ex.getMessage());
         * } finally {
         * if (documento.isOpen()) {
         * documento.close();
         * }
         * }
         * }
         * });
         */
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
        btnVerano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
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
        btnOtonio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
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
        btnInvierno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
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
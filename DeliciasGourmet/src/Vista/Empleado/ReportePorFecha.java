package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SwingConstants;

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

/**
 * Clase que representa una ventana para generar reportes de reservas por fecha.
 * Permite seleccionar una fecha y visualizar las reservas asociadas.
 */
public class ReportePorFecha extends JFrame {
    private static final long serialVersionUID = 1L; // Identificador único de la clase
    private JPanel contentPane; // Panel principal de la ventana
    private ReservaControlador controlador; // Controlador para gestionar reservas

    /**
     * Constructor de la clase ReportePorFecha.
     * Inicializa la ventana y sus componentes.
     */
	public ReportePorFecha() {
		controlador = new ReservaControlador();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 450, 326);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePorFecha.this.setVisible(false);
			}
		});

		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCerrar.setBackground(new Color(255, 0, 0));
				btnCerrar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCerrar.setForeground(Color.BLACK);
				btnCerrar.setBackground(new Color(195, 155, 107));
			}
		});
		btnCerrar.setBackground(new Color(195, 155, 107));
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.setBorder(null);
		btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnCerrar.setBounds(405, 0, 45, 30);
		contentPane.add(btnCerrar);
		
		JLabel lblReservasDetalladasEntreFechas = new JLabel("Reservas Detalladas Entre Fechas");
		lblReservasDetalladasEntreFechas.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservasDetalladasEntreFechas.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblReservasDetalladasEntreFechas.setBounds(0, 11, 450, 27);
		contentPane.add(lblReservasDetalladasEntreFechas);
		
		JDateChooser dateDesde = new JDateChooser();
		dateDesde.setForeground(Color.BLACK);
		dateDesde.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		dateDesde.setBorder(null);
		dateDesde.setBackground(Color.WHITE);
		dateDesde.setBounds(100, 110, 250, 30);
		contentPane.add(dateDesde);
		
		JDateChooser dateHasta = new JDateChooser();
		dateHasta.setForeground(Color.BLACK);
		dateHasta.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		dateHasta.setBorder(null);
		dateHasta.setBackground(Color.WHITE);
		dateHasta.setBounds(100, 210, 250, 30);
		contentPane.add(dateHasta);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesde.setForeground(Color.BLACK);
		lblDesde.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblDesde.setBounds(0, 62, 450, 30);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setHorizontalAlignment(SwingConstants.CENTER);
		lblHasta.setForeground(Color.BLACK);
		lblHasta.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblHasta.setBounds(0, 161, 450, 30);
		contentPane.add(lblHasta);
		
		/**
		 * Crea un botón para generar un reporte en formato PDF de las reservas
		 * realizadas entre dos fechas seleccionadas.
		 *
		 * Este método maneja el evento de acción del botón y genera un PDF
		 * con un listado de reservas entre las fechas especificadas. Si el PDF
		 * ya existe, se genera un nuevo archivo con un sufijo de timestamp.
		 */
		JButton btnBuscarReservas = new JButton("GENERAR");
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
                String ruta = "src\\ReportesPDF\\Reservas_entre_fechas.pdf";
                File archivo = new File(ruta);

                if (archivo.exists()) {
                    String nuevoNombre = "Reservas_entre_fechas_" + System.currentTimeMillis() + ".pdf";
                    ruta = "src\\ReportesPDF\\" + nuevoNombre;
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
                    dispose();
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
        btnBuscarReservas.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnBuscarReservas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscarReservas.setBorder(null);
        btnBuscarReservas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarReservas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnBuscarReservas.setBounds(785, 540, 150, 30);
        add(btnBuscarReservas);
		btnBuscarReservas.setIcon(new ImageIcon(ReportePorFecha.class.getResource("/Img/icono de reportes.png")));
		btnBuscarReservas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscarReservas.setForeground(Color.BLACK);
		btnBuscarReservas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnBuscarReservas.setBorder(null);
		btnBuscarReservas.setBackground(Color.WHITE);
		btnBuscarReservas.setAlignmentX(0.5f);
		btnBuscarReservas.setBounds(148, 285, 150, 30);
		contentPane.add(btnBuscarReservas);
	}
	
	/**
	 * Verifica si un correo electrónico tiene un formato válido.
	 *
	 * Este método utiliza una expresión regular para validar la estructura del
	 * correo electrónico. El formato aceptado incluye letras, números y ciertos
	 * caracteres especiales antes del símbolo '@', seguido de un dominio que
	 * puede incluir letras, números y puntos.
	 *
	 * @param correo el correo electrónico a validar
	 * @return true si el correo es válido; false en caso contrario
	 */
	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}
}

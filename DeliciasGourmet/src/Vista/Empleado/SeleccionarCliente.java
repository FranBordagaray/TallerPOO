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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente.Cliente;
import Modelo.Cliente.HistorialReserva;
import Modelo.Empleado.Reportes;
import Controlador.ClienteControlador;
import Controlador.ReservaControlador;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

/**
 * Clase que representa una ventana para seleccionar un cliente.
 * Permite buscar y seleccionar un cliente para generar reportes o realizar acciones relacionadas.
 */
public class SeleccionarCliente extends JFrame {
    private static final long serialVersionUID = 1L; // Identificador único de la clase
    private JPanel contentPane; // Panel principal de la ventana
    private JTextField txtBuscar; // Campo de texto para buscar clientes
    private JTable tblClientes; // Tabla para mostrar la lista de clientes
    private DefaultTableModel model; // Modelo de tabla para gestionar los datos de clientes
    private ClienteControlador clienteControlador; // Controlador para gestionar la lógica de clientes
    private ReservaControlador reservaControlador; // Controlador para gestionar la lógica de reservas

    /**
     * Constructor de la clase SeleccionarCliente.
     * Inicializa la ventana y sus componentes.
     *
     * @param seleccionReporte Indica el contexto de selección (ejemplo: reporte específico).
     */
	@SuppressWarnings("serial")
	public SeleccionarCliente(String SeleccionReporte) {
		clienteControlador = new ClienteControlador();
		reservaControlador = new ReservaControlador();
		
		// Configuración del panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 800, 400);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblTitulo = new JLabel("SELECCIONAR CLIENTE");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 21));
		lblTitulo.setBounds(0, 20, 800, 30);
		contentPane.add(lblTitulo);

		// Etiqueta y campo de correo electrónico
		JLabel lblBuscar = new JLabel("BUSCAR POR EMAIL");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		lblBuscar.setBounds(17, 94, 250, 30);
		contentPane.add(lblBuscar);

		// Boton para buscar cliente por su email
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailBuscado = txtBuscar.getText().trim();

				if (!emailBuscado.isEmpty()) {
					buscarClientesPorEmail(emailBuscado);
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese un correo electrónico para buscar.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setIcon(new ImageIcon(SeleccionarCliente.class.getResource("/Img/icono de buscar.png")));

		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(new Color(255, 0, 0));
				btnBuscar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(Color.WHITE);
				btnBuscar.setForeground(Color.BLACK);
			}
		});
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setAlignmentX(0.5f);
		btnBuscar.setBounds(34, 171, 200, 30);
		contentPane.add(btnBuscar);

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarCliente.this.setVisible(false);
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
		btnCerrar.setBounds(754, 1, 45, 30);
		contentPane.add(btnCerrar);

		txtBuscar = new JTextField();
		txtBuscar.setBackground(Color.WHITE);
		txtBuscar.setBorder(null);
		txtBuscar.setFont(new Font("Roboto Light", Font.BOLD, 13));
		txtBuscar.setBounds(17, 130, 250, 30);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		/**
		 * Crea un botón para generar un PDF de reservas basadas en la selección del usuario.
		 * Dependiendo del tipo de reporte seleccionado ("Reservas Futuras" o "Reserva Historial"),
		 * el botón obtiene la información necesaria del cliente seleccionado en la tabla y 
		 * llama a los métodos correspondientes para generar el PDF.
		 */
		JButton btnGenerar = new JButton("GENERAR");
		btnGenerar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerar.setIcon(new ImageIcon(SeleccionarCliente.class.getResource("/Img/icono de reportes.png")));
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (SeleccionReporte.equals("Reservas Futuras")) {
					int filaSeleccionada = tblClientes.getSelectedRow();
					if (filaSeleccionada != -1) {
						int idCliente = (int) tblClientes.getValueAt(filaSeleccionada, 0);
						String nombre = (String) tblClientes.getValueAt(filaSeleccionada, 1);
						String apellido = (String) tblClientes.getValueAt(filaSeleccionada, 2);

						List<Reportes> reportesCliente = filtrarReservasFuturas(
								reservaControlador.obtenerReservasFuturasPorCliente(idCliente));

						if (reportesCliente.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Este cliente no tiene reservas futuras.");
						} else {
							generarPDFReservasFuturas(reportesCliente, nombre, apellido);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un cliente de la tabla para generar el PDF.");
					}
				} else if(SeleccionReporte.equals("Reserva Historial")) {
					int filaSeleccionada = tblClientes.getSelectedRow();
					if (filaSeleccionada != -1) {
						int idCliente = (int) tblClientes.getValueAt(filaSeleccionada, 0);
						String nombre = (String) tblClientes.getValueAt(filaSeleccionada, 1);
						String apellido = (String) tblClientes.getValueAt(filaSeleccionada, 2);

						List<HistorialReserva> historialReserva = reservaControlador.obtenerHistorialPorCliente(idCliente);
						
						if (historialReserva.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Este cliente no tiene reservas.");
						} else {
							generarPDFHistorial(historialReserva, nombre, apellido);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un cliente de la tabla para generar el PDF.");
					}
				}
			}
		});
		btnGenerar.setForeground(Color.BLACK);
		btnGenerar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnGenerar.setBorder(null);
		btnGenerar.setBackground(Color.WHITE);
		btnGenerar.setAlignmentX(0.5f);
		btnGenerar.setBounds(34, 359, 200, 30);
		contentPane.add(btnGenerar);

		// Inicialización de la tabla
		tblClientes = new JTable();
		model = new DefaultTableModel(new Object[][] {},
				 new String[] { "ID", "Nombre", "Apellido", "Domicilio", "Teléfono", "Email" }) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		tblClientes.setModel(model);
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
			}
		});
		JScrollPane scrollPane = new JScrollPane(tblClientes);
		scrollPane.setBorder(null);
		scrollPane.setBounds(284, 94, 499, 295);
		contentPane.add(scrollPane);
		
		JButton btnActualizar = new JButton("VER TODOS");
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setIcon(new ImageIcon(SeleccionarCliente.class.getResource("/Img/icono de historial.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosClientes();
			}
		});
		btnActualizar.setForeground(Color.BLACK);
		btnActualizar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(Color.WHITE);
		btnActualizar.setAlignmentX(0.5f);
		btnActualizar.setBounds(34, 212, 200, 30);
		contentPane.add(btnActualizar);
		cargarDatosClientes();

	}

	/**
	 * Carga los datos de los clientes almacenados en la base de datos y los
	 * muestra en la tabla de clientes.
	 * 
	 * Esta función obtiene la lista de todos los clientes mediante el
	 * clienteControlador y actualiza el modelo de la tabla. Si ocurre
	 * un error durante la carga, se imprime un mensaje de error en la consola.
	 */
	private void cargarDatosClientes() {
		List<Cliente> clientes;
		try {
			clientes = clienteControlador.obtenerTodosLosClientes();
			model = (DefaultTableModel) tblClientes.getModel();
			model.setRowCount(0);

			for (Cliente cliente : clientes) {
				model.addRow(new Object[] { cliente.getIdCliente(), cliente.getNombre(), cliente.getApellido(),
						cliente.getDomicilio(), cliente.getTelefono(), cliente.getEmail() });
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al cargar los datos de los clientes.");
		}
	}

	/**
	 * Busca clientes por su dirección de correo electrónico y actualiza la
	 * tabla de clientes con los resultados filtrados.
	 *
	 * Este método recupera todos los clientes de la base de datos y
	 * verifica si el correo electrónico de cada cliente contiene la
	 * cadena de texto proporcionada. Si se encuentran coincidencias,
	 * se actualiza la tabla con los clientes filtrados.
	 *
	 * @param emailBuscado La dirección de correo electrónico que se desea buscar.
	 */
	private void buscarClientesPorEmail(String emailBuscado) {
		List<Cliente> clientesFiltrados = new ArrayList<>();
		try {
			List<Cliente> clientes = clienteControlador.obtenerTodosLosClientes();
			for (Cliente cliente : clientes) {
				if (cliente.getEmail().toLowerCase().contains(emailBuscado.toLowerCase())) {
					clientesFiltrados.add(cliente);
				}
			}
			if (!clientesFiltrados.isEmpty()) {
				model.setRowCount(0);
				for (Cliente cliente : clientesFiltrados) {
					model.addRow(new Object[] { cliente.getIdCliente(), cliente.getNombre(), cliente.getApellido(),
							cliente.getDomicilio(), cliente.getTelefono(), cliente.getEmail() });
				}
			} else {
				JOptionPane.showMessageDialog(null, "No se encontraron clientes con ese correo.", "Sin resultados",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al buscar los clientes.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Filtra y devuelve una lista de reportes que corresponden a reservas futuras.
	 *
	 * Este método recibe una lista de reportes y filtra aquellos que tienen una
	 * fecha igual o posterior a la fecha actual.
	 *
	 * @param listaReportes La lista de reportes que se desea filtrar.
	 * @return Una lista de reportes que representan reservas futuras.
	 */
	public List<Reportes> filtrarReservasFuturas(List<Reportes> listaReportes) {
		LocalDate fechaHoy = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		List<Reportes> reportesFuturos = listaReportes.stream().filter(reporte -> {
			LocalDate fechaReporte = LocalDate.parse(reporte.getFecha(), formatter);
			return !fechaReporte.isBefore(fechaHoy);
		}).collect(Collectors.toList());

		return reportesFuturos;
	}

	/**
	 * Genera un PDF con las reservas futuras de un cliente específico.
	 *
	 * Este método crea un documento PDF que incluye la información del cliente
	 * y una tabla con las reservas futuras, si las hay.
	 *
	 * @param reportesCliente La lista de reportes de reservas futuras del cliente.
	 * @param nombre El nombre del cliente.
	 * @param apellido El apellido del cliente.
	 */
	@SuppressWarnings("unused")
	public void generarPDFReservasFuturas(List<Reportes> reportesCliente, String nombre, String apellido) {
		Document documento = new Document();
		String ruta = "src\\ReportesPDF\\Reservas_Cliente.pdf";
		File archivo = new File(ruta);
		if (archivo.exists()) {
			String nuevoNombre = "Reservas_Cliente_" + System.currentTimeMillis() + ".pdf";
			ruta = "src\\ReportesPDF\\" + nuevoNombre;
		}
		
		LocalDate ahora = LocalDate.now();
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		for(Reportes r : reportesCliente) {
			LocalDate fechaReporte = LocalDate.parse(r.getFecha(),formatoFecha);
			if(fechaReporte.isBefore(ahora)) {
				reportesCliente.remove(r);
			}
		}

		try {
			PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));

			documento.open();
			documento.add(new Paragraph("Reporte de Reservas futuras del Cliente",
					FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph("Información del cliente:"));
			documento.add(new Paragraph("Nombre: " + nombre + " " + apellido));
			documento.add(new Paragraph(" "));

			if (reportesCliente != null && !reportesCliente.isEmpty()) {
				PdfPTable table = new PdfPTable(4);
				table.setWidthPercentage(100);
				table.setSpacingBefore(10f);
				table.setSpacingAfter(10f);

				table.addCell(
						new PdfPCell(new Paragraph("Fecha", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
				table.addCell(new PdfPCell(new Paragraph("Hora", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
				table.addCell(new PdfPCell(new Paragraph("Mesa", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
				table.addCell(
						new PdfPCell(new Paragraph("Ubicación", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));

				for (Reportes reporte : reportesCliente) {
					table.addCell(reporte.getFecha());
					table.addCell(reporte.getHora());
					table.addCell(String.valueOf(reporte.getCapacidad()));
					table.addCell(reporte.getUbicacion());
				}
				documento.add(table);
			} else {
				documento.add(new Paragraph("No hay reservas futuras para este cliente."));
			}

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

	/**
	 * Genera un PDF con el historial de reservas de un cliente específico.
	 *
	 * Este método crea un documento PDF que incluye la información del cliente
	 * y una tabla con el historial de reservas, si las hay.
	 *
	 * @param historialReservas La lista de reservas del historial del cliente.
	 * @param nombre El nombre del cliente.
	 * @param apellido El apellido del cliente.
	 */
	@SuppressWarnings("unused")
	public void generarPDFHistorial(List<HistorialReserva> historialReservas, String nombre, String apellido) {
	    Document documento = new Document();
	    String ruta = "src\\ReportesPDF\\Historial_Reservas_" + nombre + "_" + apellido + ".pdf";
	    File archivo = new File(ruta);
	    
	    if (archivo.exists()) {
	        String nuevoNombre = "Historial_Reservas_" + System.currentTimeMillis() + ".pdf";
	        ruta = "src\\ReportesPDF\\" + nuevoNombre;
	    }
	    try {
	        PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));

	        documento.open();
	        documento.add(new Paragraph("Historial de Reservas del Cliente",
	                FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
	        documento.add(new Paragraph(" "));
	        documento.add(new Paragraph("Información del cliente:"));
	        documento.add(new Paragraph("Nombre: " + nombre + " " + apellido));
	        documento.add(new Paragraph(" "));

	        if (historialReservas != null && !historialReservas.isEmpty()) {
	            PdfPTable table = new PdfPTable(5);
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10f);
	            table.setSpacingAfter(10f);

	            table.addCell(new PdfPCell(new Paragraph("ID Reserva", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Fecha", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Hora", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Mesa", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Ubicación", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));

	            for (HistorialReserva reserva : historialReservas) {
	                table.addCell(String.valueOf(reserva.getIdReserva()));
	                table.addCell(reserva.getFecha());
	                table.addCell(reserva.getHora());
	                table.addCell(String.valueOf(reserva.getIdMesa()));
	                table.addCell(reserva.getUbicacion());
	            }
	            documento.add(table);
	        } else {
	            documento.add(new Paragraph("No hay reservas en el historial para este cliente."));
	        }

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
}

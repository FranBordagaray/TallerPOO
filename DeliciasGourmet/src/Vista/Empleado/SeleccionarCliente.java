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
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente.Cliente;
import Modelo.Cliente.HistorialReserva;
import Modelo.Empleado.Reportes;
import Controlador.ClienteControlador;
import Controlador.ReservaControlador;
import Vista.Cliente.RecuperarCuenta;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SeleccionarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tblClientes;
	private DefaultTableModel model;
	private ClienteControlador clienteControlador;
	private ReservaControlador reservaControlador;

	public SeleccionarCliente(String SeleccionReporte) {
		clienteControlador = new ClienteControlador();
		reservaControlador = new ReservaControlador();
		

		// Configuración del panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 600, 326);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblTitulo = new JLabel("SELECCIONAR CLIENTE");
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 21));
		lblTitulo.setBounds(166, 20, 267, 22);
		contentPane.add(lblTitulo);

		// Etiqueta y campo de correo electrónico
		JLabel lblBuscar = new JLabel("BUSCAR POR EMAIL");
		lblBuscar.setFont(new Font("Roboto Light", Font.BOLD, 12));
		lblBuscar.setBounds(10, 42, 132, 30);
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
		btnBuscar.setBounds(10, 110, 160, 30);
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
		btnCerrar.setBounds(555, 0, 45, 30);
		contentPane.add(btnCerrar);

		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Roboto Light", Font.BOLD, 13));
		txtBuscar.setBounds(10, 70, 160, 30);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		// Boton para generar el PDF
		JButton btnGenerar = new JButton("GENERAR");
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
		btnGenerar.setBounds(10, 150, 160, 30);
		contentPane.add(btnGenerar);

		// Inicialización de la tabla
		tblClientes = new JTable();
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Apellido", "Domicilio", "Teléfono", "Email" });
		tblClientes.setModel(model);
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
			}
		});
		JScrollPane scrollPane = new JScrollPane(tblClientes);
		scrollPane.setBounds(192, 62, 398, 254);
		contentPane.add(scrollPane);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
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
		btnActualizar.setBounds(10, 275, 160, 30);
		contentPane.add(btnActualizar);
		cargarDatosClientes();

	}

	// Función para cargar la tabla con los datos de los clientes almacenados en la
	// base de datos
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

	// Metodo para busar clientes por su email
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

	// Metodo para que retorne solo las reservas futuras
	public List<Reportes> filtrarReservasFuturas(List<Reportes> listaReportes) {
		LocalDate fechaHoy = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		List<Reportes> reportesFuturos = listaReportes.stream().filter(reporte -> {
			LocalDate fechaReporte = LocalDate.parse(reporte.getFecha(), formatter);
			return !fechaReporte.isBefore(fechaHoy);
		}).collect(Collectors.toList());

		return reportesFuturos;
	}

	// Metodo para generar el PDF de reservas futuras
	public void generarPDFReservasFuturas(List<Reportes> reportesCliente, String nombre, String apellido) {
		Document documento = new Document();
		String ruta = System.getProperty("user.home") + "\\Desktop\\Reservas_Cliente.pdf";
		File archivo = new File(ruta);
		if (archivo.exists()) {
			String nuevoNombre = "Reservas_Cliente_" + System.currentTimeMillis() + ".pdf";
			ruta = System.getProperty("user.home") + "\\Desktop\\" + nuevoNombre;
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

	
	public void generarPDFHistorial(List<HistorialReserva> historialReservas, String nombre, String apellido) {
	    Document documento = new Document();
	    String ruta = System.getProperty("user.home") + "\\Desktop\\Historial_Reservas_" + nombre + "_" + apellido + ".pdf";
	    File archivo = new File(ruta);
	    
	    // Si el archivo ya existe, se crea uno nuevo con un timestamp
	    if (archivo.exists()) {
	        String nuevoNombre = "Historial_Reservas_" + System.currentTimeMillis() + ".pdf";
	        ruta = System.getProperty("user.home") + "\\Desktop\\" + nuevoNombre;
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
	            PdfPTable table = new PdfPTable(5); // Cambiar el número de columnas según los datos
	            table.setWidthPercentage(100);
	            table.setSpacingBefore(10f);
	            table.setSpacingAfter(10f);

	            // Encabezados de la tabla
	            table.addCell(new PdfPCell(new Paragraph("ID Reserva", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Fecha", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Hora", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Mesa", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
	            table.addCell(new PdfPCell(new Paragraph("Ubicación", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));

	            // Agregar cada reserva a la tabla
	            for (HistorialReserva reserva : historialReservas) {
	                table.addCell(String.valueOf(reserva.getIdReserva())); // ID Reserva
	                table.addCell(reserva.getFecha());                   // Fecha
	                table.addCell(reserva.getHora());                    // Hora
	                table.addCell(String.valueOf(reserva.getIdMesa()));  // ID Mesa (puedes cambiar a capacidad si deseas)
	                table.addCell(reserva.getUbicacion());               // Ubicación
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

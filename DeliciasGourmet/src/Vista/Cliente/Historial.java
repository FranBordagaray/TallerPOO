package Vista.Cliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import Controlador.ComprobanteControlador;
import Controlador.ReservaControlador;
import Controlador.TarjetaControlador;
import Modelo.Cliente.SesionCliente;
import Modelo.Cliente.Tarjeta;
import Modelo.Complementos.Comprobante;
import Modelo.Cliente.EnviarMail;
import Modelo.Cliente.HistorialReserva;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("static-access")
public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<String> comboBoxMesa;
	private JTable tblBHistorial;
	private SesionCliente s;
	private ReservaControlador reservaControlador;
	private ComprobanteControlador comprobanteControlador;
	private TarjetaControlador tarjetaControlador;
	private int idReservaSeleccionada; 
	private String fechaReservaSeleccionada; 
	private String horaReservaSeleccionada; 
	private String mesaSeleccionada;
	private String capacidadSeleccionada;
	private String ubicacionSeleccionada;
	private String comentarioSeleccionado;
	private String ruta;

	@SuppressWarnings("serial")
	public Historial() {
		
		s = new SesionCliente();
		reservaControlador = new ReservaControlador();
		comprobanteControlador = new ComprobanteControlador();
		tarjetaControlador = new TarjetaControlador();
		
		// Configuracion del panel historial
		setBorder(null);
		setBackground(new Color(222, 184, 135));
		setPreferredSize(new Dimension(992, 679));
		setLayout(null);

		// Panel Superior
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(195, 155, 107));
		pnlHeader.setBounds(271, 0, 450, 62);
		add(pnlHeader);
		pnlHeader.setLayout(null);

		// Titulo Historial
		JLabel lblHistorial = new JLabel("HISTORIAL DE RESERVAS");
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblHistorial.setBounds(75, 18, 300, 25);
		pnlHeader.add(lblHistorial);

		// Filtro Estado
		JLabel lblFiltroEstados = new JLabel("Filtrar estados:");
		lblFiltroEstados.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroEstados.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblFiltroEstados.setBounds(0, 90, 150, 25);
		add(lblFiltroEstados);

		// Selección de Filtro de Estados
		String[] estados = { "Todos" };
		comboBoxEstado = new JComboBox<>(estados);
		comboBoxEstado.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboBoxEstado.setBackground(Color.WHITE);
		comboBoxEstado.setBorder(null);
		comboBoxEstado.setBounds(150, 90, 100, 25);
		add(comboBoxEstado);
		comboBoxEstado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String estado = (String) comboBoxEstado.getSelectedItem();
				filtrarEstados(estado);
			}
		});

		// Filtro Mesas
		JLabel lblFiltroMesas = new JLabel("Filtrar mesas:");
		lblFiltroMesas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroMesas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblFiltroMesas.setBounds(300, 90, 150, 25);
		add(lblFiltroMesas);

		// Selección de Filtro de Mesas
		String[] mesas = { "Todas" };
		comboBoxMesa = new JComboBox<>(mesas);
		comboBoxMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboBoxMesa.setBackground(Color.WHITE);
		comboBoxMesa.setBorder(null);
		comboBoxMesa.setBounds(450, 90, 100, 25);
		add(comboBoxMesa);

		comboBoxMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mesa = (String) comboBoxMesa.getSelectedItem();
				filtrarMesas(mesa);
			}
		});

		// scroll para utilizar la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		scrollPane.setBounds(16, 150, 960, 518);
		add(scrollPane);

		// Tabla para el historial personal de un cliente
		tblBHistorial = new JTable();
		tblBHistorial.setGridColor(Color.DARK_GRAY);
		tblBHistorial.setBackground(Color.WHITE);
		tblBHistorial.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		tblBHistorial.setBorder(null);
		tblBHistorial.setForeground(Color.BLACK);
		tblBHistorial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblBHistorial.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"RESERVA", "FECHA", "HORA", "MESA", "COMENSALES", "UBICACION", "COMENTARIO", "ESTADO"
				}) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		});
		tblBHistorial.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = tblBHistorial.rowAtPoint(e.getPoint());
		        int column = tblBHistorial.columnAtPoint(e.getPoint());

		        if (column == 6 && row >= 0) {
		            String comentario = (String) tblBHistorial.getValueAt(row, column);
		            String comentarioFormateado = formatearComentario(comentario, 100); // Cambia 50 por el número de caracteres deseado
		            
		            JOptionPane.showMessageDialog(tblBHistorial, comentarioFormateado, "Comentario Completo", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});
		
		TableCellRenderer reservaRenderer = new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		        setText("-");
		        setHorizontalAlignment(SwingConstants.CENTER);
		        return this;
		    }
		};
		
		tblBHistorial.getColumnModel().getColumn(0).setCellRenderer(reservaRenderer);

		TableColumnModel columnModel = tblBHistorial.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(90);
     	columnModel.getColumn(1).setPreferredWidth(100);
     	columnModel.getColumn(2).setPreferredWidth(100);
     	columnModel.getColumn(3).setPreferredWidth(60);
     	columnModel.getColumn(4).setPreferredWidth(90);
     	columnModel.getColumn(5).setPreferredWidth(170);
     	columnModel.getColumn(6).setPreferredWidth(220);
     	columnModel.getColumn(7).setPreferredWidth(125);
     	
     	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
     	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
     	tblBHistorial.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
     	tblBHistorial.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
     	tblBHistorial.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		tblBHistorial.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    
		public void valueChanged(ListSelectionEvent event) {
	        if (!event.getValueIsAdjusting()) {
	            int selectedRow = tblBHistorial.getSelectedRow();
	            if (selectedRow != -1) {
	                idReservaSeleccionada = Integer.parseInt(tblBHistorial.getValueAt(selectedRow, 0).toString());
	                fechaReservaSeleccionada = tblBHistorial.getValueAt(selectedRow, 1).toString();
	            	horaReservaSeleccionada = tblBHistorial.getValueAt(selectedRow, 2).toString();
	            	mesaSeleccionada = tblBHistorial.getValueAt(selectedRow, 3).toString();
	            	capacidadSeleccionada = tblBHistorial.getValueAt(selectedRow, 4).toString();
	            	ubicacionSeleccionada = tblBHistorial.getValueAt(selectedRow, 5).toString();
	            	comentarioSeleccionado = tblBHistorial.getValueAt(selectedRow, 6).toString();
	                System.out.println("ID seleccionado: " + idReservaSeleccionada);
	            } else {
	                System.out.println("No se ha seleccionado ninguna fila.");
	            }
	        }
	    }
		});
		scrollPane.setViewportView(tblBHistorial);
		
		// Boton cancelar reserva
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocalDateTime ahora = LocalDateTime.now();
				DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				String horaSinRango = horaReservaSeleccionada.split(" - ")[0];
				String fechaHoraSeleccionadaStr = fechaReservaSeleccionada + " " + horaSinRango;
				LocalDateTime fechaHoraReservaSeleccionada = LocalDateTime.parse(fechaHoraSeleccionadaStr, formatoFecha);
				long horasDiferencia = ChronoUnit.HOURS.between(ahora, fechaHoraReservaSeleccionada);
		        
		        try {
		        	if(horasDiferencia >= 24) {
		        		  reservaControlador.actualizarEstadoReserva(idReservaSeleccionada, 0);
		        		  reservaControlador.eliminarMesa(idReservaSeleccionada);
				          JOptionPane.showMessageDialog(null,"La cancelación se ha realizado con éxito" , "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        	}else {
		        		generarComprobanteMail(comprobanteControlador.obtenerComprobantePorReserva(idReservaSeleccionada));
		        		enviarMailComprobante();
		        		reservaControlador.actualizarEstadoReserva(idReservaSeleccionada, 0);
		        		reservaControlador.eliminarMesa(idReservaSeleccionada);
		        		JOptionPane.showMessageDialog(null,"La cancelación se ha realizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        	}
		        } catch (Exception e2) {
		            e2.printStackTrace();
		            System.out.println("Error al cancelar la Reserva.");
		        }

					}
				});
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnCancelar.setBounds(826, 115, 150, 25);
		add(btnCancelar);
		
		// Boton para modificar
        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ModificarReserva reserva = new ModificarReserva(reservaControlador.obtenerReservaPorId(idReservaSeleccionada));
                    reserva.setVisible(true);
                } catch (Exception e2) {

                }
            }
            });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnModificar.setBorder(null);
        btnModificar.setBackground(Color.WHITE);
        btnModificar.setBounds(656, 115, 150, 25);
        add(btnModificar);
        
		// Utiliza la funcion para llenar la tabla con historial de reservas
		cargarDatos(s.getClienteActual().getIdCliente());
		// Utiliza la funcion para llenar el combo con mesas unicamente reservadas por
		// el cliente
		cargarComboMesas(s.getClienteActual().getIdCliente());
		cargarComboEstados(s.getClienteActual().getIdCliente());
	}
	
	

	// Función para cargar tabla con datos almacenados en la base de datos
	private void cargarDatos(int idCliente) {
		List<HistorialReserva> historial;
		try {
			historial = reservaControlador.obtenerHistorialPorCliente(idCliente);
			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
			model.setRowCount(0);

			for (HistorialReserva reserva : historial) {
				model.addRow(new Object[] {
						reserva.getIdReserva(),
						reserva.getFecha(),
						reserva.getHora(),
						reserva.getIdMesa(),
						reserva.getCapacidad(),
						reserva.getUbicacion(),
						reserva.getComentario()
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Función para cargar tabla con datos filtrados por mesas
	private void filtrarMesas(String filtro) {
		List<HistorialReserva> historial;
		try {
			historial = reservaControlador.obtenerHistorialPorCliente(s.getClienteActual().getIdCliente());
			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
			model.setRowCount(0);

			if (filtro == null || filtro.equals("Todas")) {
				for (HistorialReserva reserva : historial) {
					model.addRow(new Object[] {
							reserva.getIdReserva(),
							reserva.getFecha(),
							reserva.getHora(),
							reserva.getIdMesa(),
							reserva.getCapacidad(),
							reserva.getUbicacion(),
							reserva.getComentario(),
							reserva.getEstado()
					});
				}
			} else {
				for (HistorialReserva reserva : historial) {
					if (("Mesa " + reserva.getIdMesa()).equals(filtro)) {
						model.addRow(new Object[] {
								reserva.getIdReserva(),
								reserva.getFecha(),
								reserva.getHora(),
								reserva.getIdMesa(),
								reserva.getCapacidad(),
								reserva.getUbicacion(),
								reserva.getComentario(),
								reserva.getEstado()
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Metodo para tener combobox mesas unicamente con mesas reservadas por el
	// cliente
	private void cargarComboMesas(int idCliente) {
		try {
			List<Integer> mesasReservadas = reservaControlador.obtenerMesasReservadasPorCliente(idCliente);
			comboBoxMesa.removeAllItems();

			comboBoxMesa.addItem("Todas");
			for (Integer idMesa : mesasReservadas) {
				comboBoxMesa.addItem("Mesa " + idMesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Función para cargar tabla con datos filtrados por estados
	private void filtrarEstados(String filtro) {
		List<HistorialReserva> historial;
		try {
			historial = reservaControlador.obtenerHistorialPorCliente(s.getClienteActual().getIdCliente());
			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
			model.setRowCount(0);
			Integer filtroEstado = null;
			if (filtro != null && !filtro.equals("Todos")) {
				filtroEstado = filtro.equals("VIGENTE") ? 1 : 0;
			}
			for (HistorialReserva reserva : historial) {
				String estado;
 				switch (reserva.getEstado()) {
 				    case 0:
 				        estado = "CANCELADA";
 				        break;
 				    case 1:
 				        estado = "VIGENTE";
 				        break;
 				    case 2:
 				        estado = "COMPLETADA";
 				        break;
 				    case 3:
 				        estado = "NO ASISTIÓ";
 				        break;
 				    default:
 				        estado = "ESTADO DESCONOCIDO"; 
 				        break;
 				}
				if (filtroEstado == null || reserva.getEstado() == filtroEstado) {
					model.addRow(new Object[] {
							reserva.getIdReserva(),
							reserva.getFecha(),
							reserva.getHora(),
							reserva.getIdMesa(),
							reserva.getCapacidad(),
							reserva.getUbicacion(),
							reserva.getComentario(),
							estado
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Método para cargar el combo box con los estados disponibles según las reservas del cliente
	private void cargarComboEstados(int idCliente) {
		try {
			List<Integer> estadosReservados = reservaControlador.obtenerEstadoReservasPorCliente(idCliente);
			comboBoxEstado.removeAllItems();
			comboBoxEstado.addItem("Todos");
			for (Integer estado : estadosReservados) {
				if (estado == 1) {
					comboBoxEstado.addItem("VIGENTE");
				} else if (estado == 0) {
					comboBoxEstado.addItem("CANCELADA");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 // Metodo para enviar mail
 	
 	public void enviarMailComprobante() {
 		
 		s = new SesionCliente();
 		String destinatario = s.getClienteActual().getEmail();
 		String asunto = "Comprobante de Pago - Multa por Cancelación o No Concurrencia - Delicias Gourmet";
 		String mensaje = String.format(
 			    "Estimado/a cliente,\n\n" +
 			    "Le informamos que se ha generado un comprobante de pago por la multa correspondiente a su reserva.\n\n" +
 			    "Detalle Reserva:\n"+
 			    "   - Id de la Reserva: %s\n"+		
 			    "   - Número de Mesa: %s\n" +
 			    "   - Ubicación: %s\n" +
 			    "   - Capacidad de la Mesa: %s personas\n" +
 			    "   - Fecha de la Reserva: %s\n" +
 			    "   - Hora de la Reserva: %s\n" +
 			    "   - Comentarios adicionales: %s\n\n" +
 			    "Agradecemos su comprensión y le recordamos que estamos a su disposición para cualquier consulta\n\n" +
 			    "Saludos cordiales,\n" +
 			    "Restaurante %s",
 			    idReservaSeleccionada,
 			    mesaSeleccionada,
 			    ubicacionSeleccionada,
 			    capacidadSeleccionada,
 			    fechaReservaSeleccionada,
 			    horaReservaSeleccionada,
 			    comentarioSeleccionado,
 			    "Delicias Gourmet"
 			);
 		 EnviarMail.enviarCorreoComprobante(destinatario, asunto, mensaje,ruta);
 	}
 	
 	//Metodo para generar pdf del comprobante
    @SuppressWarnings("unused")
	private void generarComprobanteMail(Comprobante  comprobante) {
    	Tarjeta tarjeta = new Tarjeta();
    	tarjeta = tarjetaControlador.obtenerDatosTarjeta(comprobante.getIdTarjeta());
       
        Document documento = new Document();
        String projectPath  = "src/Comprobantes";
        String nombrePDF = "Comprobante_" + comprobante.getIdComprobante() + "_" + System.currentTimeMillis() + ".pdf";
        ruta = projectPath + "/" + nombrePDF;
        File archivo = new File(ruta);
        
        if (archivo.exists()) {
            String nuevoNombre = "ComprobanteD" + comprobante.getIdComprobante() + "_" + System.currentTimeMillis() + ".pdf";
            ruta = projectPath + "/" + nuevoNombre;
        }

        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();
            
            
            documento.add(new Paragraph("-------------------------------------------------------------"));
            documento.add(new Paragraph("Delicias Gourmet", FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
            documento.add(new Paragraph("Comprobante de Pago", FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
            documento.add(new Paragraph("-------------------------------------------------------------"));
            
            
            documento.add(new Paragraph("ID Comprobante: " + comprobante.getIdComprobante()));
            documento.add(new Paragraph("Fecha: " + comprobante.getFecha()));
            documento.add(new Paragraph("Hora: " + comprobante.getHora()));
            documento.add(new Paragraph("Importe: $" + String.format("%.2f", comprobante.getImporte())));
            documento.add(new Paragraph("ID Reserva: " + comprobante.getIdReserva()));
            
           
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Detalles de la Tarjeta:"));
            documento.add(new Paragraph("Titular: " + tarjeta.getTitular()));
            documento.add(new Paragraph("Emisor: " + tarjeta.getEmisor()));
            documento.add(new Paragraph("Número de Tarjeta: **** **** **** " + tarjeta.getNroTarjeta().substring(tarjeta.getNroTarjeta().length() - 4)));
            
            
            documento.add(new Paragraph("-------------------------------------------------------------"));
            documento.add(new Paragraph("Descripción del Servicio:"));
            documento.add(new Paragraph("Multa por cancelación fuera del tiempo establecido o por no concurrir."));
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + ex.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }
    
    // Método para formatear el comentario
	private String formatearComentario(String comentario, int limiteCaracteres) {
	    StringBuilder resultado = new StringBuilder();
	    String[] palabras = comentario.split(" "); // Dividir el comentario en palabras
	    int longitudActual = 0;

	    for (String palabra : palabras) {
	        // Si la longitud actual más la longitud de la nueva palabra supera el límite
	        if (longitudActual + palabra.length() > limiteCaracteres) {
	            resultado.append("\n"); // Agregar un salto de línea
	            longitudActual = 0; // Reiniciar la longitud actual
	        }
	        
	        resultado.append(palabra).append(" "); // Agregar la palabra al resultado
	        longitudActual += palabra.length() + 1; // Actualizar la longitud actual (+1 por el espacio)
	    }

	    return resultado.toString().trim(); // Retornar el resultado sin espacios al final
	}
 	
}
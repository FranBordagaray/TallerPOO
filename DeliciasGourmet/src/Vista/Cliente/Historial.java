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
import Vista.Empleado.GestionEmpleados;
import Modelo.Cliente.EnviarMail;
import Modelo.Cliente.HistorialReserva;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

/**
 * La clase {@code Historial} extiende {@link JPanel} y se encarga de mostrar
 * el historial de acciones o eventos en la interfaz de usuario. Esta clase
 * permite visualizar una lista de elementos que representan actividades
 * anteriores, facilitando el seguimiento de eventos relevantes para el usuario.
 *
 * <p>
 * Se puede personalizar la visualización y el manejo de los elementos del
 * historial, así como agregar nuevas funcionalidades según sea necesario.
 * </p>
 */
@SuppressWarnings("static-access")

public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * ComboBox que permite seleccionar el estado de la reserva.
	 */
	private JComboBox<String> comboBoxEstado;

	/**
	 * ComboBox que permite seleccionar la mesa disponible.
	 */
	private JComboBox<String> comboBoxMesa;

	/**
	 * Tabla que muestra el historial de reservas.
	 */
	private JTable tblBHistorial;

	/**
	 * Objeto que representa la sesión del cliente.
	 */
	private SesionCliente s;

	/**
	 * Controlador para manejar las reservas.
	 */
	private ReservaControlador reservaControlador;

	/**
	 * Controlador para gestionar los comprobantes.
	 */
	private ComprobanteControlador comprobanteControlador;

	/**
	 * Controlador para manejar la información de las tarjetas.
	 */
	private TarjetaControlador tarjetaControlador;

	/**
	 * ID de la reserva seleccionada actualmente.
	 */
	private int idReservaSeleccionada; 

	/**
	 * Fecha de la reserva seleccionada actualmente.
	 */
	private String fechaReservaSeleccionada; 

	/**
	 * Hora de la reserva seleccionada actualmente.
	 */
	private String horaReservaSeleccionada; 

	/**
	 * Mesa seleccionada actualmente.
	 */
	private String mesaSeleccionada;

	/**
	 * Capacidad de la mesa seleccionada.
	 */
	private String capacidadSeleccionada;

	/**
	 * Ubicación de la mesa seleccionada.
	 */
	private String ubicacionSeleccionada;

	/**
	 * Comentario asociado a la reserva seleccionada.
	 */
	private String comentarioSeleccionado;

	/**
	 * Ruta para almacenar o acceder a información relacionada.
	 */
	private String ruta;

	/**
	 * Constructor de la clase Historial.
	 *
	 * Este constructor inicializa una nueva instancia de la clase Historial,
	 * que se utiliza para gestionar y almacenar información sobre el historial
	 * de reservas, eventos o actividades del sistema.
	 * 
	 * La clase Historial puede incluir detalles como fechas, horas y otros
	 * datos relevantes relacionados con las acciones realizadas por los
	 * usuarios en el sistema.
	 */
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
		lblFiltroEstados.setBounds(0, 80, 150, 25);
		add(lblFiltroEstados);

		// Selección de Filtro de Estados
		String[] estados = { "Todos" };
		comboBoxEstado = new JComboBox<>(estados);
		comboBoxEstado.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboBoxEstado.setBackground(Color.WHITE);
		comboBoxEstado.setBorder(null);
		comboBoxEstado.setBounds(150, 80, 100, 25);
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
		lblFiltroMesas.setBounds(0, 115, 150, 25);
		add(lblFiltroMesas);

		// Selección de Filtro de Mesas
		String[] mesas = { "Todas" };
		comboBoxMesa = new JComboBox<>(mesas);
		comboBoxMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboBoxMesa.setBackground(Color.WHITE);
		comboBoxMesa.setBorder(null);
		comboBoxMesa.setBounds(150, 115, 100, 25);
		add(comboBoxMesa);

		comboBoxMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mesa = (String) comboBoxMesa.getSelectedItem();
				filtrarMesas(mesa);
			}
		});

		// Scroll para utilizar la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		scrollPane.setBounds(16, 150, 960, 518);
		add(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {   
		        if (tblBHistorial.rowAtPoint(e.getPoint()) == -1) {
		            tblBHistorial.clearSelection();
		            idReservaSeleccionada = 0;
		            fechaReservaSeleccionada = "";
		            horaReservaSeleccionada = "";
		            mesaSeleccionada = "";
		            capacidadSeleccionada = "";
		            ubicacionSeleccionada = "";
		            comentarioSeleccionado = "";
		        }
		    }
		});


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
		
		/**
		 * Configura el botón "CANCELAR" para permitir la cancelación de la reserva seleccionada.
		 * 
		 * <p>
		 * Al hacer clic en el botón, se verifica si hay una reserva seleccionada. Si es así, 
		 * se muestra un cuadro de confirmación preguntando si el usuario está seguro de cancelar 
		 * la reserva. Si el usuario confirma la acción, se procede a verificar el estado de la 
		 * reserva y la diferencia de tiempo entre la fecha actual y la fecha de la reserva. 
		 * Dependiendo de si la cancelación se realiza con más de 24 horas de antelación, se 
		 * envía un comprobante por correo electrónico y se actualiza el estado de la reserva.
		 * </p>
		 * 
		 * <p>
		 * También se configuran los estilos visuales del botón, incluyendo el cambio de color 
		 * al pasar el ratón por encima.
		 * </p>
		 */
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon(Historial.class.getResource("/Img/icono cancelar.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idReservaSeleccionada != 0) {
						LocalDateTime ahora = LocalDateTime.now();
						DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
						String horaSinRango = horaReservaSeleccionada.split(" - ")[0];
						String fechaHoraSeleccionadaStr = fechaReservaSeleccionada + " " + horaSinRango;
						LocalDateTime fechaHoraReservaSeleccionada = LocalDateTime.parse(fechaHoraSeleccionadaStr, formatoFecha);
						long horasDiferencia = ChronoUnit.HOURS.between(ahora, fechaHoraReservaSeleccionada);
						
						int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar la reserva?", "Confirmación de Cancelación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				        
				        if (respuesta == JOptionPane.YES_OPTION) {
				        		if( reservaControlador.verificarEstadoReserva(idReservaSeleccionada)) {
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
				        		}else {
				        			JOptionPane.showMessageDialog(null,"Cancelacion de reserva invalida", "Error", JOptionPane.ERROR_MESSAGE);
				        		}
				        } else {
				            
				            System.out.println("La reserva no se ha cancelado.");
				        }
				        
				        cargarDatos(s.getClienteActual().getIdCliente());
			  }else {
				  JOptionPane.showMessageDialog(Historial.this,"No selecciono ninguna reserva","Error",JOptionPane.ERROR_MESSAGE);
			  }
		        
		  }
		});
		btnCancelar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnCancelar.setBackground(new Color(255, 0, 0));
        		btnCancelar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnCancelar.setBackground(Color.WHITE);
            	btnCancelar.setForeground(Color.BLACK);
            }
		});
		btnCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnCancelar.setBounds(826, 115, 150, 25);
		add(btnCancelar);
		
		/**
		 * Configura los botones y componentes de la interfaz en el panel de historial.
		 * 
		 * <p>
		 * Se crean dos botones: 
		 * <ul>
		 *   <li><strong>MODIFICAR</strong>: Permite modificar la reserva seleccionada.
		 *       Al hacer clic, verifica si se ha seleccionado una reserva. Si es así,
		 *       abre la ventana de modificación; de lo contrario, muestra un mensaje de error.</li>
		 *   <li><strong>ACTUALIZAR TABLA</strong>: Actualiza la información mostrada en la tabla
		 *       y los combo boxes de mesas y estados, recargando los datos del cliente actual.</li>
		 * </ul>
		 * Además, se configuran estilos visuales para los botones y se inicializan los datos
		 * en la tabla y los combo boxes al cargar el panel.
		 * </p>
		 */
		// Boton para modificar
        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setIcon(new ImageIcon(Historial.class.getResource("/Img/icono modificar.png")));
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	if(idReservaSeleccionada != 0) {
	                    ModificarReserva reserva = new ModificarReserva(reservaControlador.obtenerReservaPorId(idReservaSeleccionada));
	                    reserva.setVisible(true);
	                    cargarDatos(s.getClienteActual().getIdCliente());
                	}else {
                		JOptionPane.showMessageDialog(Historial.this,"No selecciono ninguna reserva","Error",JOptionPane.ERROR_MESSAGE);
                	}
                } catch (Exception e2) {
                	System.out.println("Error al modificar la Reserva.");
                }
            }
            });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	  btnModificar.setBackground(new Color(40, 180, 99));
                  btnModificar.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	  btnModificar.setBackground(Color.WHITE);
                  btnModificar.setForeground(Color.BLACK);
            }
        });
        btnModificar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnModificar.setBorder(null);
        btnModificar.setBackground(Color.WHITE);
        btnModificar.setBounds(656, 115, 150, 25);
        add(btnModificar);
        
        JButton btnActualizarTabla = new JButton("ACTUALIZAR TABLA");
        btnActualizarTabla.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cargarDatos(s.getClienteActual().getIdCliente());
        		cargarComboMesas(s.getClienteActual().getIdCliente());
        		cargarComboEstados(s.getClienteActual().getIdCliente());
        	}
        });
        btnActualizarTabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActualizarTabla.setBackground(new Color(255, 0, 0));
				btnActualizarTabla.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnActualizarTabla.setForeground(Color.BLACK);
				btnActualizarTabla.setBackground(Color.WHITE);
			}
		});
        btnActualizarTabla.setIcon(new ImageIcon(GestionEmpleados.class.getResource("/Img/icono verificado.png")));
        btnActualizarTabla.setForeground(Color.BLACK);
        btnActualizarTabla.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnActualizarTabla.setBorder(null);
        btnActualizarTabla.setBackground(Color.WHITE);
        btnActualizarTabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizarTabla.setAlignmentX(0.5f);
        btnActualizarTabla.setBounds(435, 115, 200, 25);
        add(btnActualizarTabla);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(90, 90, 5, 5);
        add(tabbedPane);
        
		// Utiliza la funcion para llenar la tabla con historial de reservas
		cargarDatos(s.getClienteActual().getIdCliente());
		// Utiliza la funcion para llenar el combo con mesas unicamente reservadas por
		// el cliente
		cargarComboMesas(s.getClienteActual().getIdCliente());
		cargarComboEstados(s.getClienteActual().getIdCliente());
	}
	
	/**
	 * Carga los datos de reservas almacenados en la base de datos y los muestra en la tabla.
	 *
	 * Este método consulta el historial de reservas del cliente especificado mediante el 
	 * `idCliente` a través del controlador `reservaControlador`. Los datos obtenidos se 
	 * utilizan para llenar la tabla `tblBHistorial`.
	 * 
	 * Primero, se establece el modelo de la tabla a vacío mediante `model.setRowCount(0)` 
	 * para asegurarse de que no haya datos anteriores. Luego, se recorre cada reserva del 
	 * historial y se determina su estado mediante un `switch` basado en el valor numérico 
	 * correspondiente. Los estados posibles son:
	 * - 0: CANCELADA
	 * - 1: VIGENTE
	 * - 2: COMPLETADA
	 * - 3: NO ASISTIÓ
	 * 
	 * Para cada reserva, se añade una nueva fila a la tabla con los detalles relevantes: 
	 * ID de reserva, fecha, hora, ID de mesa, capacidad, ubicación, comentarios y estado.
	 * 
	 * Si ocurre una excepción durante la obtención del historial de reservas, se imprime la 
	 * traza de la excepción para su análisis.
	 */
	public void cargarDatos(int idCliente) {
		List<HistorialReserva> historial;
		try {
			historial = reservaControlador.obtenerHistorialPorCliente(idCliente);
			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
			model.setRowCount(0);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Filtra los datos de reservas y carga la tabla con los registros correspondientes a las mesas.
	 *
	 * Este método consulta el historial de reservas del cliente actual a través del controlador 
	 * `reservaControlador` y carga los datos en la tabla `tblBHistorial`.
	 * 
	 * Si el `filtro` es nulo o tiene el valor "Todas", se cargan todas las reservas del historial 
	 * en la tabla. Para cada reserva, se determina el estado a partir de su valor numérico y se 
	 * añade una fila a la tabla con los detalles de la reserva.
	 * 
	 * Si se proporciona un filtro específico (por ejemplo, el nombre de una mesa), solo se añaden 
	 * las reservas que coinciden con ese filtro. En este caso, se compara el nombre de la mesa 
	 * (formato "Mesa [idMesa]") con el filtro y, si coinciden, se añade la fila correspondiente.
	 * 
	 * Si ocurre una excepción durante la obtención del historial de reservas, se imprime la traza 
	 * de la excepción para su análisis.
	 */
	private void filtrarMesas(String filtro) {
		List<HistorialReserva> historial;
		try {
			historial = reservaControlador.obtenerHistorialPorCliente(s.getClienteActual().getIdCliente());
			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
			model.setRowCount(0);

			if (filtro == null || filtro.equals("Todas")) {
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
			} else {
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
					if (("Mesa " + reserva.getIdMesa()).equals(filtro)) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga el combo box con las mesas reservadas por el cliente actual.
	 *
	 * Este método consulta el controlador `reservaControlador` para obtener una lista de las mesas 
	 * que han sido reservadas por el cliente identificado por el `idCliente` proporcionado.
	 * 
	 * Primero, limpia el combo box `comboBoxMesa` de cualquier elemento existente. 
	 * Luego, añade una opción "Todas" para permitir la selección de todas las mesas.
	 * A continuación, itera sobre la lista de mesas reservadas, añadiendo cada mesa al combo box 
	 * en el formato "Mesa [idMesa]".
	 * 
	 * Si ocurre una excepción durante la consulta a la base de datos, se imprime la traza de la 
	 * excepción para su análisis.
	 */
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

	/**
	 * Filtra y carga la tabla con datos de reservas del cliente según el estado seleccionado.
	 *
	 * Este método recibe un filtro en forma de cadena que representa el estado de las reservas. 
	 * Consulta el historial de reservas del cliente actual a través del controlador `reservaControlador`.
	 * 
	 * Limpia el modelo de la tabla antes de agregar nuevos datos. Dependiendo del estado filtrado, 
	 * se determinan los estados específicos a mostrar. Los estados posibles son "CANCELADA", "VIGENTE", 
	 * "COMPLETADA", "NO ASISTIÓ", y "ESTADO DESCONOCIDO" para cualquier valor no manejado.
	 * 
	 * Finalmente, si el estado de una reserva coincide con el filtro o si no hay filtro, 
	 * se agrega una nueva fila a la tabla con la información de la reserva.
	 * 
	 * En caso de error durante el proceso, se imprime la traza de la excepción.
	 */
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

	/**
	 * Carga el combo box con los estados disponibles de las reservas del cliente.
	 *
	 * Este método recibe el ID del cliente y consulta los estados de sus reservas 
	 * a través del controlador `reservaControlador`. Limpia el combo box y agrega 
	 * la opción "Todos", seguida de los estados específicos ("VIGENTE" y "CANCELADA")
	 * en función de los datos obtenidos. 
	 *
	 * Si ocurre un error durante la consulta, se imprime la traza de la excepción.
	 */
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
				}else if (estado == 2) {
					comboBoxEstado.addItem("COMPLETADA");
				}else if (estado == 3) {
					comboBoxEstado.addItem("NO ASISTIÓ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Envía un correo electrónico con el comprobante de pago a la dirección del cliente.
	 *
	 * Este método recopila la información relevante de la reserva y la mesa, 
	 * y compone un mensaje de correo que incluye los detalles de la multa por 
	 * cancelación o no concurrencia. El mensaje es enviado a la dirección de 
	 * correo electrónico del cliente actual. 
	 * 
	 * Se utiliza la clase `EnviarMail` para gestionar el envío del correo, 
	 * incluyendo el archivo del comprobante en formato PDF como un adjunto.
	 */
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
 	
 	/**
 	 * Genera un comprobante en formato PDF y lo guarda en un archivo.
 	 *
 	 * Este método crea un documento PDF utilizando la biblioteca iText, en el que se incluyen
 	 * detalles del comprobante de pago y la información de la tarjeta utilizada. El PDF se
 	 * guarda en el directorio "src/Comprobantes" con un nombre que incluye el ID del 
 	 * comprobante y una marca de tiempo para garantizar que sea único.
 	 * 
 	 * Si el archivo ya existe, se genera un nuevo nombre para evitar sobrescribirlo. 
 	 * Se añaden varios párrafos al documento, incluyendo el título del comprobante, 
 	 * información del comprobante, y detalles de la tarjeta (ocultando parte del número de tarjeta).
 	 *
 	 * @param comprobante El objeto Comprobante que contiene la información a incluir en el PDF.
 	 */
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
    
    /**
     * Formatea un comentario dividiéndolo en líneas de longitud máxima especificada.
     *
     * Este método toma un comentario como entrada y lo divide en palabras. Luego, 
     * construye un nuevo comentario asegurándose de que cada línea no exceda 
     * el límite de caracteres especificado. Si una palabra adicional haría 
     * que la longitud actual sobrepase el límite, se inicia una nueva línea.
     * 
     * @param comentario El comentario a formatear.
     * @param limiteCaracteres El límite máximo de caracteres por línea.
     * @return Un String con el comentario formateado, con saltos de línea donde sea necesario.
     */
	private String formatearComentario(String comentario, int limiteCaracteres) {
	    StringBuilder resultado = new StringBuilder();
	    String[] palabras = comentario.split(" "); 
	    int longitudActual = 0;
	    for (String palabra : palabras) {
	        if (longitudActual + palabra.length() > limiteCaracteres) {
	            resultado.append("\n"); 
	            longitudActual = 0; 
	        }
	        resultado.append(palabra).append(" "); 
	        longitudActual += palabra.length() + 1; 
	    }
	    return resultado.toString().trim(); 
	}
}
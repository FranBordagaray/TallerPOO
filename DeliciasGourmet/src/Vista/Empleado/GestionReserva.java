package Vista.Empleado;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import Modelo.Cliente.EnviarMail;
import Modelo.Cliente.HistorialReserva;
import Modelo.Cliente.Tarjeta;
import Modelo.Complementos.Comprobante;
import Vista.Cliente.ModificarReserva;
import javax.swing.ImageIcon;

/**
 * Clase que representa el panel de gestión de reservas.
 * Permite a los recepcionistas buscar, visualizar y gestionar las reservas de los clientes.
 */
public class GestionReserva extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tblRecepcionista; // Tabla para mostrar las reservas
    private JTextField txtBuscarCliente; // Campo de texto para buscar reservas por cliente
    private JLabel lblFechaHora; // Etiqueta para mostrar la fecha y hora de la reserva
    private ReservaControlador reservaControlador; // Controlador para gestionar reservas
    private ComprobanteControlador comprobanteControlador; // Controlador para gestionar comprobantes
    private TarjetaControlador tarjetaControlador; // Controlador para gestionar tarjetas
    private int idReservaSeleccionada; // ID de la reserva seleccionada
    private String fechaReservaSeleccionada; // Fecha de la reserva seleccionada
    private String horaReservaSeleccionada; // Hora de la reserva seleccionada
    private String mesaSeleccionada; // Mesa seleccionada para la reserva
    private String capacidadSeleccionada; // Capacidad de la mesa seleccionada
    private String ubicacionSeleccionada; // Ubicación de la mesa seleccionada
    private String ruta; // Ruta para almacenar información adicional
    private String email; // Email asociado a la reserva

    /**
     * Constructor de la clase GestionReserva.
     * Inicializa el panel y sus componentes.
     */
    @SuppressWarnings("serial")
	public GestionReserva() {
		
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
		
		// Etiqueta fecha y hora
        lblFechaHora = new JLabel();
        lblFechaHora.setBorder(null);
        lblFechaHora.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaHora.setForeground(Color.BLACK);
        lblFechaHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFechaHora.setBounds(792, 12, 200, 25);
        add(lblFechaHora);

		// Titulo Historial
		JLabel lblGestionReservas= new JLabel("Gestion Reservas");
		lblGestionReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionReservas.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblGestionReservas.setBounds(75, 18, 300, 25);
		pnlHeader.add(lblGestionReservas);

		// scroll para utilizar la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		scrollPane.setBounds(16, 150, 960, 518);
		add(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {   
		        if (tblRecepcionista.rowAtPoint(e.getPoint()) == -1) {
		        	tblRecepcionista.clearSelection();
		            idReservaSeleccionada = 0;
		            fechaReservaSeleccionada = "";
		            horaReservaSeleccionada = "";
		            mesaSeleccionada = "";
		            capacidadSeleccionada = "";
		            ubicacionSeleccionada = "";
		        }
		    }
		});

	    // Tabla para el Recepcionista
 		tblRecepcionista = new JTable();
 		tblRecepcionista.setGridColor(Color.DARK_GRAY);
 		tblRecepcionista.setBackground(Color.WHITE);
 		tblRecepcionista.setFont(new Font("Roboto Light", Font.PLAIN, 16));
 		tblRecepcionista.setBorder(null);
 		tblRecepcionista.setForeground(Color.BLACK);
 		tblRecepcionista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 		tblRecepcionista.setModel(new DefaultTableModel(
 				new Object[][] {
 				},
 				new String[] {
 						"RESERVA","NOMBRE","APELLIDO", "FECHA", "HORA", "MESA", "COMENSALES","UBICACION", "ESTADO"
 				}) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
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
		
		tblRecepcionista.getColumnModel().getColumn(0).setCellRenderer(reservaRenderer);
		
 		TableColumnModel columnModel = tblRecepcionista.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(80);
     	columnModel.getColumn(1).setPreferredWidth(110);
     	columnModel.getColumn(2).setPreferredWidth(110);
     	columnModel.getColumn(3).setPreferredWidth(100);
     	columnModel.getColumn(4).setPreferredWidth(100);
     	columnModel.getColumn(5).setPreferredWidth(60);
     	columnModel.getColumn(6).setPreferredWidth(90);
     	columnModel.getColumn(7).setPreferredWidth(175);
     	columnModel.getColumn(8).setPreferredWidth(130);
 	
 	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
 	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
 	tblRecepcionista.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
 	tblRecepcionista.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
 	tblRecepcionista.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    
 	public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tblRecepcionista.getSelectedRow();
            if (selectedRow != -1) {
                idReservaSeleccionada = Integer.parseInt(tblRecepcionista.getValueAt(selectedRow, 0).toString());
                fechaReservaSeleccionada = tblRecepcionista.getValueAt(selectedRow, 3).toString();
            	horaReservaSeleccionada = tblRecepcionista.getValueAt(selectedRow, 4).toString();
            	mesaSeleccionada = tblRecepcionista.getValueAt(selectedRow, 5).toString();
            	capacidadSeleccionada = tblRecepcionista.getValueAt(selectedRow, 6).toString();
            	ubicacionSeleccionada = tblRecepcionista.getValueAt(selectedRow, 7).toString();
            	
                System.out.println("ID seleccionado: " + idReservaSeleccionada);
            } else {
                System.out.println("No se ha seleccionado ninguna fila.");
            }
        }
    }
	});
    scrollPane.setViewportView(tblRecepcionista);

    // Etiqueta y campo de texto para buscar reservas de clientes
    JLabel lblBuscarCliente = new JLabel("BUSCAR CLIENTE");
    lblBuscarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblBuscarCliente.setForeground(Color.BLACK);
    lblBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
    lblBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
    lblBuscarCliente.setBounds(10, 115, 140, 25);
    add(lblBuscarCliente);

    txtBuscarCliente = new JTextField();
    txtBuscarCliente.setForeground(Color.BLACK);
    txtBuscarCliente.setBackground(Color.WHITE);
    txtBuscarCliente.setBorder(null);
    txtBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
    txtBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
    txtBuscarCliente.setBounds(147, 115, 150, 25);
    add(txtBuscarCliente);
    txtBuscarCliente.setColumns(10);
 
    // Boton para buscar reservas de clientes
    JButton btnBuscar = new JButton("BUSCAR");
    btnBuscar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		buscarPorApellido(txtBuscarCliente.getText());
    	}
    });
    btnBuscar.setIcon(new ImageIcon(GestionReserva.class.getResource("/Img/icono de buscar.png")));
    btnBuscar.setForeground(Color.BLACK);
    btnBuscar.setBackground(Color.WHITE);
    btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btnBuscar.setBorder(null);
    btnBuscar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
    btnBuscar.setBounds(307, 115, 120, 25);
    btnBuscar.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseEntered(MouseEvent e) {
    		btnBuscar.setBackground(new Color(64, 224, 208));
    		btnBuscar.setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        	btnBuscar.setBackground(Color.WHITE);
        	btnBuscar.setForeground(Color.BLACK);
        }
	});
    add(btnBuscar);
		
	// Boton cancelar reserva
	JButton btnCancelar = new JButton("CANCELAR");
	btnCancelar.setIcon(new ImageIcon(GestionReserva.class.getResource("/Img/icono cancelar.png")));
	btnCancelar.addActionListener(new ActionListener() {
		/**
	     * Acción a realizar cuando se presiona el botón cancelar.
	     * 
	     * Este método verifica si hay una reserva seleccionada y, de ser así, 
	     * solicita una confirmación al usuario. Si el usuario confirma, se 
	     * procede a cancelar la reserva si cumple con los criterios de tiempo.
	     *
	     * Si la cancelación se realiza con éxito, se muestra un mensaje 
	     * de éxito al usuario; si no, se maneja el error adecuadamente.
	     *
	     * @param e El evento de acción que ocurre al presionar el botón.
	     */
		public void actionPerformed(ActionEvent e) {
		    if (idReservaSeleccionada != 0) {
		        ConfirmacionDeCancelacion confirmacionDialog = new ConfirmacionDeCancelacion();
		        boolean confirmacion = confirmacionDialog.mostrarConfirmacion();
		        
		        if (confirmacion) {
		            LocalDateTime ahora = LocalDateTime.now();
		            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		            String horaSinRango = horaReservaSeleccionada.split(" - ")[0];
		            String fechaHoraSeleccionadaStr = fechaReservaSeleccionada + " " + horaSinRango;
		            LocalDateTime fechaHoraReservaSeleccionada = LocalDateTime.parse(fechaHoraSeleccionadaStr, formatoFecha);
		            long horasDiferencia = ChronoUnit.HOURS.between(ahora, fechaHoraReservaSeleccionada);

		            try {
		                if (horasDiferencia >= 24) {
		                    reservaControlador.actualizarEstadoReserva(idReservaSeleccionada, 0);
		                    reservaControlador.eliminarMesa(idReservaSeleccionada);
		                    JOptionPane.showMessageDialog(null, "La cancelación se ha realizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    generarComprobanteMail(comprobanteControlador.obtenerComprobantePorReserva(idReservaSeleccionada));
		                    enviarMailComprobante();
		                    reservaControlador.actualizarEstadoReserva(idReservaSeleccionada, 0);
		                    reservaControlador.eliminarMesa(idReservaSeleccionada);
		                    JOptionPane.showMessageDialog(null, "La cancelación se ha realizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                }
		            } catch (Exception e2) {
		                e2.printStackTrace();
		                System.out.println("Error al cancelar la Reserva.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "La cancelación fue cancelada por el usuario", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(GestionReserva.this, "No seleccionó ninguna reserva", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		    cargarDatos();	    
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
	btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnCancelar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
	btnCancelar.setBounds(826, 115, 150, 25);
	add(btnCancelar);
	
	JButton btnModificar = new JButton("MODIFICAR");
	btnModificar.setIcon(new ImageIcon(GestionReserva.class.getResource("/Img/icono modificar.png")));
	btnModificar.addActionListener(new ActionListener() {
		/**
	     * Acción a realizar cuando se presiona el botón modificar.
	     * 
	     * Este método verifica si hay una reserva seleccionada. Si es así, 
	     * se abre la ventana de modificación de la reserva con los datos 
	     * de la reserva seleccionada. Si no hay reserva seleccionada, 
	     * se muestra un mensaje de error al usuario.
	     *
	     * @param e El evento de acción que ocurre al presionar el botón.
	     */
		public void actionPerformed(ActionEvent e) {
			if(idReservaSeleccionada != 0) {
				ModificarReserva reserva = new ModificarReserva(reservaControlador.obtenerReservaPorId(idReservaSeleccionada));
            	reserva.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(GestionReserva.this,"No selecciono ninguna reserva","Error",JOptionPane.ERROR_MESSAGE);
			}
			cargarDatos(); 	
		}
	});
	btnModificar.setHorizontalTextPosition(SwingConstants.RIGHT);
	btnModificar.setForeground(Color.BLACK);
	btnModificar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
	btnModificar.setBorder(null);
	btnModificar.setBackground(Color.WHITE);
	btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnModificar.setBounds(826, 80, 150, 25);
	btnModificar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        	btnModificar.setBackground(new Color(255, 255, 100));
        	btnModificar.setForeground(Color.BLACK);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        	btnModificar.setBackground(Color.WHITE);
        	btnModificar.setForeground(Color.BLACK);
        }
    });
	add(btnModificar);
	
	JButton enviarRecordatorio = new JButton("ENVIAR RECORDATORIO");
	enviarRecordatorio.setIcon(new ImageIcon(GestionReserva.class.getResource("/Img/icono de eventos especiales.png")));
	enviarRecordatorio.addActionListener(new ActionListener() {
		/**
	     * Acción a realizar cuando se presiona el botón de enviar recordatorio.
	     * 
	     * Este método verifica si hay una reserva seleccionada. Si es así, 
	     * obtiene el correo electrónico del cliente asociado a la reserva y 
	     * envía un recordatorio. Si no hay reserva seleccionada, se muestra 
	     * un mensaje de error al usuario.
	     *
	     * @param e El evento de acción que ocurre al presionar el botón.
	     */
		public void actionPerformed(ActionEvent e) {
			
			if(idReservaSeleccionada != 0) {
				email = reservaControlador.obtenerEmailClientePorReserva(idReservaSeleccionada);
				enviarRecordatorio();
				JOptionPane.showMessageDialog(GestionReserva.this,"El recordatorio se envio con exito","Éxito",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(GestionReserva.this,"No selecciono ninguna reserva","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	});
	enviarRecordatorio.setHorizontalTextPosition(SwingConstants.RIGHT);
	enviarRecordatorio.setForeground(Color.BLACK);
	enviarRecordatorio.setFont(new Font("Roboto Light", Font.PLAIN, 16));
	enviarRecordatorio.setBorder(null);
	enviarRecordatorio.setBackground(Color.WHITE);
	enviarRecordatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	enviarRecordatorio.setBounds(437, 115, 210, 25);
	enviarRecordatorio.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        	enviarRecordatorio.setBackground(new Color(33, 150, 243));
        	enviarRecordatorio.setForeground(Color.BLACK);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        	enviarRecordatorio.setBackground(Color.WHITE);
        	enviarRecordatorio.setForeground(Color.BLACK);
        }
    });
	add(enviarRecordatorio);
	
	JButton btnNoConcurrio = new JButton("INASISTENCIA");
	btnNoConcurrio.setIcon(new ImageIcon(GestionReserva.class.getResource("/Img/icono inasistido.png")));
	btnNoConcurrio.addActionListener(new ActionListener() {
		/**
	     * Acción a realizar cuando se presiona el botón de inasistencia.
	     *
	     * Este método verifica si hay una reserva seleccionada. Si es así, 
	     * actualiza el estado de la reserva a "inasistencia" (estado 3) 
	     * y genera un comprobante que se envía al cliente por correo. 
	     * Si no hay reserva seleccionada, se muestra un mensaje de error al usuario.
	     *
	     * @param e El evento de acción que ocurre al presionar el botón.
	     */
		public void actionPerformed(ActionEvent e) {
			if(idReservaSeleccionada != 0) {
				reservaControlador.actualizarEstadoReserva(idReservaSeleccionada, 3);
				generarComprobanteMail(comprobanteControlador.obtenerComprobantePorReserva(idReservaSeleccionada));
				enviarMailComprobante();
			 JOptionPane.showMessageDialog(GestionReserva.this,"Inasistencia Cargada con exito","Éxito",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(GestionReserva.this,"No selecciono ninguna reserva","Error",JOptionPane.ERROR_MESSAGE);
			}
			cargarDatos();
		}
	});
	btnNoConcurrio.setHorizontalTextPosition(SwingConstants.RIGHT);
	btnNoConcurrio.setForeground(Color.BLACK);
	btnNoConcurrio.setFont(new Font("Roboto Light", Font.PLAIN, 16));
	btnNoConcurrio.setBorder(null);
	btnNoConcurrio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnNoConcurrio.setBackground(Color.WHITE);
	btnNoConcurrio.setBounds(669, 80, 150, 25);
	btnNoConcurrio.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        	btnNoConcurrio.setBackground(new Color(220, 20, 60));
        	btnNoConcurrio.setForeground(Color.WHITE);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        	btnNoConcurrio.setBackground(Color.WHITE);
        	btnNoConcurrio.setForeground(Color.BLACK);
        }
    });
	add(btnNoConcurrio);
	
	JButton btnConcurrio = new JButton("CONCURRIO");
	btnConcurrio.setIcon(new ImageIcon(GestionReserva.class.getResource("/Img/icono verificado.png")));
	btnConcurrio.addActionListener(new ActionListener() {
		 /**
	     * Acción a realizar cuando se presiona el botón de asistencia.
	     *
	     * Este método verifica si hay una reserva seleccionada. Si es así, 
	     * actualiza el estado de la reserva a "asistido" (estado 2). 
	     * Si no hay reserva seleccionada, se muestra un mensaje de error al usuario.
	     *
	     * @param e El evento de acción que ocurre al presionar el botón.
	     */
		public void actionPerformed(ActionEvent e) {
			if(idReservaSeleccionada != 0) {
				reservaControlador.actualizarEstadoReserva(idReservaSeleccionada, 2);
			}else {
				JOptionPane.showMessageDialog(GestionReserva.this,"No selecciono ninguna reserva","Error",JOptionPane.ERROR_MESSAGE);
			}
			cargarDatos();
		}
	});
	btnConcurrio.setHorizontalTextPosition(SwingConstants.RIGHT);
	btnConcurrio.setForeground(Color.BLACK);
	btnConcurrio.setFont(new Font("Roboto Light", Font.PLAIN, 16));
	btnConcurrio.setBorder(null);
	btnConcurrio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnConcurrio.setBackground(Color.WHITE);
	btnConcurrio.setBounds(669, 115, 150, 25);
	btnConcurrio.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        		btnConcurrio.setBackground(new Color(0, 200, 100));
        		btnConcurrio.setForeground(Color.WHITE);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        		btnConcurrio.setBackground(Color.WHITE);
        		btnConcurrio.setForeground(Color.BLACK);
        }
    });
	add(btnConcurrio);
	
	// Iniciar el Timer para actualizar la fecha y hora
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            actualizarFechaHora();
        }
    }, 0, 1000);
	
	cargarDatos();
	
	}
	
	// Método para actualizar la fecha y hora
    private void actualizarFechaHora() {
    	/**
         * Actualiza la etiqueta de fecha y hora con la fecha y hora actual.
         *
         * Este método obtiene la fecha y hora actuales, las formatea en 
         * el patrón "dd/MM/yyyy HH:mm" y actualiza la etiqueta correspondiente
         * en la interfaz de usuario. Se utiliza SwingUtilities.invokeLater
         * para garantizar que la actualización se realice en el hilo de eventos
         * de Swing, evitando problemas de concurrencia.
         */
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaHoraFormateada = fechaHoraActual.format(formato);
        SwingUtilities.invokeLater(() -> lblFechaHora.setText(fechaHoraFormateada));
    }

	// Función para cargar tabla con datos almacenados en la base de datos
 	private void cargarDatos() {
 		/**
 	     * Carga los datos del historial de reservas en la tabla de recepción.
 	     *
 	     * Este método recupera el historial de reservas a través del controlador 
 	     * de reservas y lo muestra en la tabla de recepción. Se ignoran las 
 	     * reservas con estado CANCELADA (estado 0). Para cada reserva, se evalúa 
 	     * su estado y se asigna una representación textual adecuada (como "VIGENTE", 
 	     * "COMPLETADA", etc.). Los datos se añaden a un modelo de tabla 
 	     * (DefaultTableModel) que representa la tabla de recepción.
 	     *
 	     * En caso de error al obtener el historial, se imprime la traza de la excepción.
 	     */
 		List<HistorialReserva> historial;
 		try {
 			historial = reservaControlador.obtenerHistorialReserva();
 			DefaultTableModel model = (DefaultTableModel) tblRecepcionista.getModel();
 			model.setRowCount(0);
 		
 			for (HistorialReserva reserva : historial) {
 				if (reserva.getEstado() == 0) {
 				    continue;  
 				}
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
 						reserva.getNombre(),
 						reserva.getApellido(),
 						reserva.getFecha(),
 						reserva.getHora(),
 						reserva.getIdMesa(),
 						reserva.getCapacidad(),
 						reserva.getUbicacion(),
 						estado
 				});
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
	
 	// Función para filtrar la busqueda por apellido
   	private void buscarPorApellido(String apellido) {
   		/**
   	     * Filtra y carga las reservas en la tabla según el apellido proporcionado.
   	     *
   	     * Este método recupera el historial de reservas a través del controlador 
   	     * de reservas y filtra las reservas basándose en el apellido del cliente. 
   	     * Si el apellido de una reserva contiene el texto especificado (sin 
   	     * distinguir mayúsculas de minúsculas) y el estado de la reserva no 
   	     * es CANCELADA (estado 0), se añade a la tabla de recepción. 
   	     * Se asigna una representación textual adecuada al estado de cada 
   	     * reserva antes de agregarla a la tabla.
   	     *
   	     * En caso de error al obtener el historial, se imprime la traza de la 
   	     * excepción.
   	     *
   	     * @param apellido El apellido a buscar en el historial de reservas.
   	     */
   		List<HistorialReserva> historial;
   		try {
   			historial = reservaControlador.obtenerHistorialReserva();
   			DefaultTableModel model = (DefaultTableModel) tblRecepcionista.getModel();
   			model.setRowCount(0);
   		
   			for (HistorialReserva reserva : historial) {
   				if (reserva.getApellido().toLowerCase().contains(apellido.toLowerCase())) {
   					if (reserva.getEstado() == 0) {
   	 				    continue;  
   	 				}
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
   	  						reserva.getNombre(),
   	  						reserva.getApellido(),
   	  						reserva.getFecha(),
   	  						reserva.getHora(),
   	  						reserva.getIdMesa(),
   	  						reserva.getCapacidad(),
   	  						reserva.getUbicacion(),
   	  						estado,
   	  						reserva.getComentario()
   	  				});
 				}
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   	}
   	
	// Metodo para enviar mail con Comprobante
 	public void enviarMailComprobante() {
 		/**
 	     * Envía un correo electrónico con el comprobante de pago al cliente.
 	     *
 	     * Este método genera un asunto y un mensaje formateado que incluye los
 	     * detalles de la reserva correspondiente, incluyendo el ID de la reserva, 
 	     * número de mesa, ubicación, capacidad, fecha y hora de la reserva. 
 	     * Se utiliza la clase EnviarMail para enviar el correo al cliente.
 	     * 
 	     * Se espera que el atributo 'email' contenga la dirección de correo 
 	     * del cliente y 'ruta' la ubicación del comprobante, que se adjunta al 
 	     * correo.
 	     *
 	     * @throws Exception Si ocurre un error al enviar el correo.
 	     */
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
 			    "Agradecemos su comprensión y le recordamos que estamos a su disposición para cualquier consulta\n\n" +
 			    "Saludos cordiales,\n" +
 			    "Restaurante %s",
 			    idReservaSeleccionada,
 			    mesaSeleccionada,
 			    ubicacionSeleccionada,
 			    capacidadSeleccionada,
 			    fechaReservaSeleccionada,
 			    horaReservaSeleccionada,
 			    "Delicias Gourmet"
 			);
 		
 		 EnviarMail.enviarCorreoComprobante(email, asunto, mensaje, ruta);
 	}
 	
 	//Metodo para generar pdf del comprobante
    @SuppressWarnings("unused")
	private void generarComprobanteMail(Comprobante  comprobante) {
    	/**
         * Genera un archivo PDF que contiene el comprobante de pago.
         *
         * Este método recibe un objeto Comprobante y utiliza la información de
         * la tarjeta asociada para crear un documento PDF que incluye detalles
         * como el ID del comprobante, fecha, hora, importe, ID de reserva y
         * detalles de la tarjeta del cliente.
         * 
         * El PDF se guarda en la carpeta "Comprobantes" dentro de la ruta del
         * proyecto, y el nombre del archivo incluye el ID del comprobante y
         * un timestamp para garantizar que sea único. Si el archivo ya existe,
         * se genera un nuevo nombre para evitar conflictos.
         *
         * @param comprobante El objeto Comprobante que contiene la información
         *                    necesaria para generar el PDF.
         * @throws IOException Si ocurre un error al crear el archivo PDF.
         * @throws DocumentException Si ocurre un error al manipular el documento PDF.
         */
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
	     * Envía un correo electrónico de recordatorio al cliente sobre su reserva próxima.
	     *
	     * Este método genera un mensaje que incluye los detalles de la reserva,
	     * como el número de mesa, ubicación, capacidad, fecha y hora. El asunto
	     * del correo es "Recordatorio de Reserva - Delicias Gourmet".
	     * 
	     * Se utiliza el método `EnviarMail.enviarCorreo` para enviar el mensaje
	     * al correo electrónico del cliente.
	     *
	     * @throws NullPointerException Si alguna de las variables que contienen
	     *                              los detalles de la reserva no están inicializadas.
	     */
 	public void enviarRecordatorio() { 	 
 		String asunto =  "Recordatorio de Reserva - Delicias Gourmet";
 		String mensaje = String.format(
 				"Estimado/a cliente,\n\n" +
		        "Este es un recordatorio de su reserva próxima en nuestro restaurante Delicias Gourmet. Aquí tiene los detalles de su reserva:\n\n" +
		        "   - Número de Mesa: %s\n" +
		        "   - Ubicación: %s\n" +
		        "   - Capacidad de la Mesa: %s personas\n" +
		        "   - Fecha de la Reserva: %s\n" +
		        "   - Hora de la Reserva: %s\n" +
		        "Le recordamos que su reserva está programada para la fecha indicada, y le estaremos esperando.\n" +
		        "Por favor, no dude en contactarnos si desea realizar algún cambio o tiene alguna consulta.\n\n" +
		        "¡Gracias por confiar en nosotros! Le deseamos una experiencia inolvidable en Delicias Gourmet.\n\n" +
		        "Saludos cordiales,\n" +
		        "Restaurante %s",
		        mesaSeleccionada,
		        ubicacionSeleccionada,
		        capacidadSeleccionada,
		        fechaReservaSeleccionada,
		        horaReservaSeleccionada,
		        "Delicias Gourmet"
 			);
 		 EnviarMail.enviarCorreo(email, asunto, mensaje);
 	}
 	
 	
}

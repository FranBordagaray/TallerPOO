package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Vista.Cliente.Dashboard;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import Controlador.MesaControlador;
import Controlador.ReservaControlador;
import Controlador.ServicioControlador;
import Modelo.Cliente.HistorialReserva;
import Modelo.Complementos.Servicio;
import Modelo.Empleado.SesionEmpleado;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;

/**
 * Clase que representa el panel del dashboard para empleados.
 * Proporciona la interfaz para gestionar las reservas y eventos especiales.
 */
public class DashboardEmpleado extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel lblFechaHora;
    private JTable tblBHistorial;
    private JTable EventosEspeciales;
    private JTextField txtBuscarCliente;
    private ServicioControlador servicioControlador;
    private ReservaControlador controladorReserva;
    private MesaControlador mesaControlador;
    

    /**
     * Constructor por defecto de la clase DashboardEmpleado.
     * Inicializa los componentes de la interfaz y los controladores necesarios.
     */
    @SuppressWarnings({ "static-access", "serial" })
    public DashboardEmpleado() {
    	
    	servicioControlador = new ServicioControlador();
    	controladorReserva = new ReservaControlador();
    	mesaControlador = new MesaControlador();
        // Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));

        // Panel de Bienvenida
        JPanel pnlBienvenido = new JPanel();
        pnlBienvenido.setBackground(new Color(195, 155, 107));
        pnlBienvenido.setBounds(0, 0, 992, 50);
        add(pnlBienvenido);
        pnlBienvenido.setLayout(null);

        // Etiqueta de Bienvenida
        JLabel lblBienvenido = new JLabel("Bienvenido, ");
        lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenido.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgHome.png")));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenido.setForeground(Color.BLACK);
        lblBienvenido.setFont(new Font("Roboto Light", Font.BOLD, 16));
        lblBienvenido.setBounds(0, 0, 170, 50);
        pnlBienvenido.add(lblBienvenido);

        // Etiqueta Nombre de Usuario
        SesionEmpleado s1 = new SesionEmpleado();
        JLabel lblNombreUsuario = new JLabel(s1.getEmpleadoActual().getNombre() + " "
                + s1.getEmpleadoActual().getApellido() + " - " + s1.getEmpleadoActual().getRol());
        lblNombreUsuario.setForeground(Color.BLACK);
        lblNombreUsuario.setFont(new Font("Roboto Light", Font.BOLD, 16));
        lblNombreUsuario.setBounds(170, 0, 330, 50);
        pnlBienvenido.add(lblNombreUsuario);

        // Etiqueta fecha y hora
        lblFechaHora = new JLabel();
        lblFechaHora.setBorder(null);
        lblFechaHora.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaHora.setForeground(Color.BLACK);
        lblFechaHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFechaHora.setBounds(792, 12, 200, 25);
        pnlBienvenido.add(lblFechaHora);
        
        //Contenedor tabla Historial
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 97, 972, 300);
        add(scrollPane);
        
        //Contenedor tabla Eventos Especiales
        JScrollPane scrollPane_EveEspecial = new JScrollPane();
        scrollPane_EveEspecial.setBounds(10, 453, 400, 215);
        add(scrollPane_EveEspecial);
        
        //Titulo tabla Eventos Especiales
        JLabel lblNewLabel = new JLabel("EVENTOS ESPECIALES");
        lblNewLabel.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 422, 400, 30);
        add(lblNewLabel);
        
        //Separador
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBackground(Color.BLACK);
        separator.setBounds(0, 408, 992, 2);
        add(separator);
        
        //Tabla de eventos especiales
        EventosEspeciales = new JTable();
        EventosEspeciales.setGridColor(Color.DARK_GRAY);
        EventosEspeciales.setBackground(Color.WHITE);
        EventosEspeciales.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        EventosEspeciales.setBorder(null);
        EventosEspeciales.setForeground(Color.BLACK);
        EventosEspeciales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        EventosEspeciales.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"UBICACION", "FECHA", "HORA"
        		
        	}) {
        	@Override
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
        });
        
        TableColumnModel columnModelEvento = EventosEspeciales.getColumnModel();
        columnModelEvento.getColumn(0).setPreferredWidth(135);
        columnModelEvento.getColumn(1).setPreferredWidth(132);
        columnModelEvento.getColumn(2).setPreferredWidth(130);
        EventosEspeciales.setFont(new Font("Roboto Light", Font.PLAIN, 14));
        scrollPane_EveEspecial.setViewportView(EventosEspeciales);

        // Tabla para el historial personal de un cliente
 		tblBHistorial = new JTable();
 		tblBHistorial.setGridColor(Color.DARK_GRAY);
 		tblBHistorial.setBackground(Color.WHITE);
 	 	tblBHistorial.setFont(new Font("Roboto Light", Font.PLAIN, 16));
 		tblBHistorial.setBorder(null);
 		tblBHistorial.setForeground(Color.BLACK);
 		tblBHistorial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 		tblBHistorial.setModel(new DefaultTableModel(
 			new Object[][] {},
 			new String[] { "RESERVA","NOMBRE","APELLIDO", "FECHA", "HORA", "MESA", "COMENSALES", "UBICACION", "ESTADO" , "COMEN."}) {
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

 	        if (column == 9 && row >= 0) {
 	            String comentario = (String) tblBHistorial.getValueAt(row, column);
 	            String comentarioFormateado = formatearComentario(comentario, 100);
 	            
 	            JOptionPane.showMessageDialog(tblBHistorial, comentarioFormateado, "Comentario Completo", JOptionPane.INFORMATION_MESSAGE);
 	        }
 	    }
 		});
 		
 		TableCellRenderer comentarioRenderer = new DefaultTableCellRenderer() {
 		    @Override
 		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
 		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 		        
 		        setText("-");
 		       setHorizontalAlignment(SwingConstants.CENTER);
 		        return this;
 		    }
 		};
 		
 		tblBHistorial.getColumnModel().getColumn(9).setCellRenderer(comentarioRenderer);
 		tblBHistorial.getColumnModel().getColumn(0).setCellRenderer(comentarioRenderer);

 		TableColumnModel columnModelHistorial = tblBHistorial.getColumnModel();
 		columnModelHistorial.getColumn(0).setPreferredWidth(65);
 		columnModelHistorial.getColumn(1).setPreferredWidth(110);
 		columnModelHistorial.getColumn(2).setPreferredWidth(110);
 		columnModelHistorial.getColumn(3).setPreferredWidth(100);
 		columnModelHistorial.getColumn(4).setPreferredWidth(100);
 		columnModelHistorial.getColumn(5).setPreferredWidth(60);
 		columnModelHistorial.getColumn(6).setPreferredWidth(90);
 		columnModelHistorial.getColumn(7).setPreferredWidth(178);
 	 	columnModelHistorial.getColumn(8).setPreferredWidth(100);
 	 	columnModelHistorial.getColumn(9).setPreferredWidth(55);
      	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
      	tblBHistorial.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
      	tblBHistorial.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        scrollPane.setViewportView(tblBHistorial);

        // Etiqueta y campo de texto para buscar reservas de clientes
        JLabel lblBuscarCliente = new JLabel("BUSCAR CLIENTE");
        lblBuscarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBuscarCliente.setForeground(Color.BLACK);
        lblBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscarCliente.setBounds(210, 61, 200, 25);
        add(lblBuscarCliente);

        txtBuscarCliente = new JTextField();
        txtBuscarCliente.setForeground(Color.BLACK);
        txtBuscarCliente.setBackground(Color.WHITE);
        txtBuscarCliente.setBorder(null);
        txtBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        txtBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        txtBuscarCliente.setBounds(420, 61, 200, 25);
        add(txtBuscarCliente);
        txtBuscarCliente.setColumns(10);

        // Boton para buscar reservas de clientes
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarPorApellido(txtBuscarCliente.getText());
        	}
        });
        btnBuscar.setIcon(new ImageIcon(DashboardEmpleado.class.getResource("/Img/icono de buscar.png")));
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscar.setBorder(null);
        btnBuscar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnBuscar.setBounds(630, 61, 150, 25);
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
        
        JButton btnActualizarTabla = new JButton("ACTUALIZAR TABLAS");
        btnActualizarTabla.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cargarDatos();
        		cargarServicioEspecial();
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
        btnActualizarTabla.setBounds(10, 62, 200, 25);
        add(btnActualizarTabla);

        // Iniciar el Timer para actualizar la fecha y hora
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                actualizarFechaHora();
            }
        }, 0, 1000);
        
        cargarDatos();
        cargarServicioEspecial();
    }

    // Método para actualizar la fecha y hora
    private void actualizarFechaHora() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaHoraFormateada = fechaHoraActual.format(formato);
        SwingUtilities.invokeLater(() -> lblFechaHora.setText(fechaHoraFormateada));
    }
    
    // Función para cargar tabla con datos almacenados en la base de datos
 	private void cargarDatos() {
 		List<HistorialReserva> historial;
 		try {
 			historial = controladorReserva.obtenerHistorialReserva();
 			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
 			model.setRowCount(0);
 		
 			for (HistorialReserva reserva : historial) {
 				if (reserva.getEstado() == 0 || reserva.getEstado() == 2 || reserva.getEstado() == 3) {
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
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	/**
 	 * Carga los datos de los servicios especiales desde la base de datos 
 	 * y los muestra en la tabla de eventos especiales. 
 	 * Obtiene una lista de servicios con evento especial y llena el modelo de la tabla 
 	 * con la ubicación, fecha y hora de inicio de cada servicio.
 	 * 
 	 * Si ocurre una excepción durante la carga, se imprime el seguimiento de la pila.
 	 */
 		private void cargarServicioEspecial() {
 	 		List<Servicio> servicios;
 	 		try {
 	 			servicios = servicioControlador.buscarServiciosConEventoEspecial(); 	        		
 	 			
 	 			DefaultTableModel model = (DefaultTableModel) EventosEspeciales.getModel();
 	 			model.setRowCount(0);
 	 		
 	 			for (Servicio servicio : servicios) {
 					model.addRow(new Object[] {
 							mesaControlador.buscarUbicacionPorIdServicio(servicio.getIdServicio()),
 	 						servicio.getFecha(),
 	 						servicio.getHoraInicio()
 	 				});
 	 			}
 	 		} catch (Exception e) {
 	 			e.printStackTrace();
 	 		}
 	 	}
 	
 		/**
 		 * Filtra y busca reservas en el historial según el apellido proporcionado.
 		 * Obtiene el historial de reservas y llena el modelo de la tabla 
 		 * solo con aquellas reservas cuyo apellido contenga la cadena de búsqueda (insensible a mayúsculas y minúsculas).
 		 * 
 		 * Las reservas con estados "CANCELADA", "COMPLETADA", o "NO ASISTIÓ" son ignoradas en el filtrado.
 		 * El método asigna un estado descriptivo basado en el código del estado de la reserva 
 		 * y agrega la información relevante a la tabla.
 		 * 
 		 * Si ocurre una excepción durante la búsqueda, se imprime el seguimiento de la pila.
 		 */
  	private void buscarPorApellido(String apellido) {
  		List<HistorialReserva> historial;
  		try {
  			historial = controladorReserva.obtenerHistorialReserva();
  			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
  			model.setRowCount(0);
  		
  			for (HistorialReserva reserva : historial) {
  				if (reserva.getEstado() == 0 || reserva.getEstado() == 2 || reserva.getEstado() == 3) {
 				    continue;  
 				}
  				if (reserva.getApellido().toLowerCase().contains(apellido.toLowerCase())) {
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
 	
  	/**
  	 * Formatea un comentario dividiéndolo en varias líneas según un límite de caracteres especificado.
  	 * 
  	 * Este método toma un comentario como entrada y lo separa en palabras. Si la longitud actual de la línea 
  	 * más la longitud de la siguiente palabra excede el límite de caracteres, se agrega un salto de línea antes 
  	 * de continuar con la próxima palabra. El resultado es un String con el comentario original formateado 
  	 * de manera que ninguna línea supere el límite especificado.
  	 * 
  	 * @param comentario el comentario a formatear
  	 * @param limiteCaracteres el número máximo de caracteres permitidos en una línea
  	 * @return el comentario formateado, con saltos de línea donde sea necesario
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
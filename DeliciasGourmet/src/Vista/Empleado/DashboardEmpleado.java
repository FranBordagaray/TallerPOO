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
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Controlador.ReservaControlador;
import Modelo.Cliente.HistorialReserva;
import Modelo.Empleado.SesionEmpleado;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;

public class DashboardEmpleado extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel lblFechaHora;
    private JTable tblBHistorial;
    private JTextField txtBuscarCliente;
    private ReservaControlador controladorReserva;
    

    @SuppressWarnings({ "static-access", "serial" })
    public DashboardEmpleado() {
    	
    	controladorReserva = new ReservaControlador();
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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 369, 972, 300);
        add(scrollPane);

        // Tabla para el historial personal de un cliente
 		tblBHistorial = new JTable();
 		tblBHistorial.setGridColor(Color.DARK_GRAY);
 		tblBHistorial.setBackground(Color.WHITE);
 	 	tblBHistorial.setFont(new Font("Roboto Light", Font.PLAIN, 16));
 		tblBHistorial.setBorder(null);
 		tblBHistorial.setForeground(Color.BLACK);
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
 	            String comentarioFormateado = formatearComentario(comentario, 100); // Cambia 50 por el número de caracteres deseado
 	            
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
 		
 		
 	
 		TableColumnModel columnModel = tblBHistorial.getColumnModel();
 	 	columnModel.getColumn(0).setPreferredWidth(65);
 	 	columnModel.getColumn(1).setPreferredWidth(110);
 	 	columnModel.getColumn(2).setPreferredWidth(110);
 	 	columnModel.getColumn(3).setPreferredWidth(100);
 	 	columnModel.getColumn(4).setPreferredWidth(100);
 	 	columnModel.getColumn(5).setPreferredWidth(60);
 	 	columnModel.getColumn(6).setPreferredWidth(90);
 	 	columnModel.getColumn(7).setPreferredWidth(160);
 	 	columnModel.getColumn(8).setPreferredWidth(100);
 	 	columnModel.getColumn(9).setPreferredWidth(55);
 	 	
      	
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
        lblBuscarCliente.setBounds(150, 333, 200, 25);
        add(lblBuscarCliente);

        txtBuscarCliente = new JTextField();
        txtBuscarCliente.setForeground(Color.BLACK);
        txtBuscarCliente.setBackground(Color.WHITE);
        txtBuscarCliente.setBorder(null);
        txtBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        txtBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        txtBuscarCliente.setBounds(350, 333, 200, 25);
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
        btnBuscar.setBounds(560, 333, 150, 25);
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
 	
 	// Función para filtrar la busqueda por apellido
  	private void buscarPorApellido(String apellido) {
  		List<HistorialReserva> historial;
  		try {
  			historial = controladorReserva.obtenerHistorialReserva();
  			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
  			model.setRowCount(0);
  		
  			for (HistorialReserva reserva : historial) {
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
 	
 	// Método para formatear el comentario
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
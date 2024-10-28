package Vista.Empleado;

import java.awt.Color;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controlador.MesaControlador;
import Controlador.ReservaControlador;
import Controlador.ClienteControlador;
import Controlador.ComprobanteControlador;
import Controlador.ServicioControlador;
import Controlador.TarjetaControlador;
import Modelo.Cliente.EnviarMail;
import Modelo.Cliente.SesionCliente;
import Modelo.Complementos.Comprobante;
import Modelo.Complementos.EnumEstado;
import Modelo.Complementos.Mesa;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;
import Modelo.Empleado.SesionEmpleado;
import Vista.Cliente.DetalleReservaCliente;

/**
 * Clase que representa la ventana de detalle de reserva para un empleado.
 * Extiende de JFrame y contiene la información de la reserva, mesa,
 * comprobante y servicio relacionados.
 */
@SuppressWarnings("unused")
public class DetalleReservaEmpleado extends JFrame {

    private static final long serialVersionUID = 1L; // SerialVersionUID para la serialización
    private JPanel contentPane; // Panel principal de la ventana que contiene todos los componentes visuales
    private Reserva reserva; // Objeto que representa la reserva actual del cliente
    private Mesa mesa; // Objeto que representa la mesa reservada por el cliente
    private String[] mesasSeleccionadas; // Array de cadenas que contiene las mesas seleccionadas para la reserva
    private Comprobante comprobante; // Objeto que representa el comprobante de pago generado para la reserva
    private Servicio servicio; // Objeto que representa el servicio asociado a la reserva (ej. comida, bebida)
    private ServicioControlador servicioControlador; // Controlador para manejar la lógica de los servicios
    private ReservaControlador reservaControlador; // Controlador para manejar la lógica de las reservas
    private MesaControlador mesaControlador; // Controlador para manejar la lógica de las mesas
    private ComprobanteControlador comprobanteControlador; // Controlador para manejar la lógica de los comprobantes
    private TarjetaControlador tarjetaControlador; // Controlador para manejar la lógica de las tarjetas de pago
    private SesionCliente s; // Objeto que representa la sesión actual del cliente en el sistema
    private SesionEmpleado v; // Objeto que representa la sesión actual del empleado en el sistema
    private String rangoHoras; // String que define el rango de horas disponible para la reserva
    private String destinatario; // String que representa el destinatario de la confirmación de reserva
    private String ubicacion; // String que indica la ubicación de la reserva
    private String fecha; // String que representa la fecha de la reserva
    private String horaInicio; // String que representa la hora de inicio de la reserva
    private String horaFin; // String que representa la hora de fin de la reserva
    private String comentario; // String que contiene un comentario adicional sobre la reserva
    private String[] mesasSeleccionadasEvento; // Array de cadenas que contiene las mesas seleccionadas para un evento especial
    private VistaReservaEmpleado reservaCliente; // Vista que representa la interfaz de reserva para los empleados

    /**
     * Constructor de la clase DetalleReservaEmpleado.
     *
     * @param reserva Objeto Reserva que contiene la información de la reserva.
     * @param mesa Objeto Mesa que representa la mesa reservada.
     * @param servicio Objeto Servicio que contiene detalles sobre el servicio.
     * @param comprobante Objeto Comprobante que contiene información de pago.
     * @param mesasSeleccionadasEvento Array de cadenas con las mesas seleccionadas para el evento.
     * @param reservaCliente Objeto VistaReservaEmpleado que representa la vista de reserva para empleados.
     */
	public DetalleReservaEmpleado(Reserva reserva, Mesa mesa, Servicio servicio, Comprobante comprobante, 
		String[] mesasSeleccionadasEvento, VistaReservaEmpleado reservaCliente) {
		
		this.reserva = reserva;
		this.mesa = mesa;
		this.servicio = servicio;
		this.comprobante = comprobante;
		this.mesasSeleccionadasEvento = mesasSeleccionadasEvento;
		this.reservaCliente = reservaCliente;


		this.servicioControlador = new ServicioControlador();
		this.reservaControlador = new ReservaControlador();
		this.mesaControlador = new MesaControlador();
		this.comprobanteControlador = new ComprobanteControlador();
		this.tarjetaControlador = new TarjetaControlador();

		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 562);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setResizable(false);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel Fondo Contenedor
		JPanel pnlContenedor = new JPanel();
		pnlContenedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlContenedor.setBorder(null);
		pnlContenedor.setBackground(new Color(222, 184, 135));
		pnlContenedor.setBounds(0, 0, 450, 562);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta Detalle de Reserva
		JLabel lblDetalle = new JLabel("Detalle Reserva de Evento");
		lblDetalle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetalle.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalle.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblDetalle.setBounds(36, 10, 364, 33);
		pnlContenedor.add(lblDetalle);

		// Etiqueta Ubicacion
		JLabel lblUbicacion = new JLabel("UBICACION: ");
		lblUbicacion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUbicacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUbicacion.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblUbicacion.setAlignmentX(1.0f);
		lblUbicacion.setBounds(-29, 80, 200, 25);
		pnlContenedor.add(lblUbicacion);

		// Etiqueta Fecha
		JLabel lblFecha = new JLabel("FECHA: ");
		lblFecha.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblFecha.setAlignmentX(1.0f);
		lblFecha.setBounds(-41, 140, 200, 25);
		pnlContenedor.add(lblFecha);

		// Etiqueta Hora
		JLabel lblHora = new JLabel("HORARIO: ");
		lblHora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblHora.setAlignmentX(1.0f);
		lblHora.setBounds(-41, 200, 200, 25);
		pnlContenedor.add(lblHora);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("MESAS: ");
		lblMesa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblMesa.setAlignmentX(1.0f);
		lblMesa.setBounds(-54, 245, 200, 25);
		pnlContenedor.add(lblMesa);

		// Etiqueta Comentario
		JLabel lblComentario = new JLabel("COMENTARIO: ");
		lblComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComentario.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblComentario.setAlignmentX(1.0f);
		lblComentario.setBounds(-54, 366, 200, 25);
		pnlContenedor.add(lblComentario);

		ubicacion = mesa.getUbicacion();
		fecha = servicio.getFecha();
		rangoHoras = servicio.getHoraInicio() + " - " + servicio.getHoraFin();
		comentario = reserva.getComentario();

		// Etiqueta de Ubicacion de la reserva
		JLabel lblUbiSeleccionada = new JLabel(ubicacion);
		lblUbiSeleccionada.setForeground(Color.BLACK);
		lblUbiSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbiSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblUbiSeleccionada.setBackground(new Color(222, 184, 135));
		lblUbiSeleccionada.setBounds(181, 80, 139, 25);
		pnlContenedor.add(lblUbiSeleccionada);
		pnlContenedor.revalidate();
		pnlContenedor.repaint();

		// Etiqueta de Fecha de la reserva
		JLabel lblFecSeleccionada = new JLabel(fecha);
		lblFecSeleccionada.setForeground(Color.BLACK);
		lblFecSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblFecSeleccionada.setBackground(new Color(222, 184, 135));
		lblFecSeleccionada.setBounds(150, 140, 111, 25);
		pnlContenedor.add(lblFecSeleccionada);

		// Etiqueta de el rango de Horas de la reserva
		JLabel lblHoraSeleccionada = new JLabel(rangoHoras);
		lblHoraSeleccionada.setForeground(Color.BLACK);
		lblHoraSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblHoraSeleccionada.setBackground(new Color(222, 184, 135));
		lblHoraSeleccionada.setBounds(119, 200, 187, 25);
		pnlContenedor.add(lblHoraSeleccionada);
		
		JTextArea textAreaMesas = new JTextArea(concatenarIdsMesas(mesasSeleccionadasEvento));
		textAreaMesas.setLineWrap(true);
		textAreaMesas.setForeground(Color.BLACK);
		textAreaMesas.setFont(new Font("Roboto Light", Font.BOLD, 14));
		textAreaMesas.setEditable(false);
		textAreaMesas.setBorder(null);
		textAreaMesas.setBackground(new Color(222, 184, 135));
		textAreaMesas.setBounds(169, 250, 271, 100);
		pnlContenedor.add(textAreaMesas);

		// Etiqueta de Comentarios de la reserva
		JTextArea textArea = new JTextArea(comentario);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setBorder(null);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Roboto Light", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBackground(new Color(222, 184, 135));
		textArea.setBounds(150, 371, 250, 100);
		pnlContenedor.add(textArea);

		// Botón de Confirmar
		JButton btnEnviarEmail = new JButton("Enviar Email");
		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnviarEmail enviar = new EnviarEmail(DetalleReservaEmpleado.this);
				enviar.setVisible(true);
				reservaCliente.resetearCampos();
                dispose();
				
			}
		});
		btnEnviarEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEnviarEmail.setBackground(new Color(126, 211, 33));
				btnEnviarEmail.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEnviarEmail.setBackground(Color.WHITE);
				btnEnviarEmail.setForeground(Color.BLACK);
			}
		});
		btnEnviarEmail.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnEnviarEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnviarEmail.setBackground(Color.WHITE);
		btnEnviarEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEnviarEmail.setBorder(null);
		btnEnviarEmail.setForeground(Color.BLACK);
		btnEnviarEmail.setBounds(280, 500, 120, 30);
		pnlContenedor.add(btnEnviarEmail);

		// Boton para Cancelar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservaCliente.resetearCampos();
                dispose();

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
				btnCerrar.setBackground(Color.WHITE);
				btnCerrar.setForeground(Color.BLACK);
			}
		});
		btnCerrar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrar.setBorder(null);
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setBounds(50, 500, 120, 30);
		pnlContenedor.add(btnCerrar);
				

	}
	
	/**
	 * Concatenar los IDs de las mesas seleccionadas en una sola cadena, separadas por comas.
	 *
	 * Este método recibe un array de IDs de mesas seleccionadas para un evento y construye
	 * una representación en cadena de estos IDs, agregando cada ID seguido de una coma.
	 * Se elimina la coma final antes de devolver la cadena resultante.
	 *
	 * @param mesasSeleccionadasEvento Un array de IDs de mesas seleccionadas.
	 * @return Una cadena que contiene los IDs concatenados de las mesas, separados por comas.
	 */
	public String concatenarIdsMesas(String[] mesasSeleccionadasEvento) {
		StringBuilder sb = new StringBuilder();
		for (String mesa : mesasSeleccionadasEvento) {
			sb.append(mesa).append(", ");
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
		
	}

	/**
	 * Verifica la disponibilidad de las mesas para la reserva solicitada.
	 *
	 * Este método busca todas las mesas que tienen el mismo ID y comprueba
	 * si hay solapamientos en las fechas y horas con servicios existentes.
	 * Si se encuentra un solapamiento, el método retorna false; de lo contrario,
	 * retorna true, indicando que las mesas están disponibles.
	 *
	 * @return true si las mesas están disponibles, false si hay solapamientos o si ocurre un error.
	 */
	public boolean verificarDisponibilidadMesas() {
	    try {
	        // 1. Obtener todas las mesas con el mismo idMesa
	        ArrayList<Mesa> mesasConMismoId = mesaControlador.buscarMesaPorId(mesa.getIdMesa());
	        
	        if (mesasConMismoId.isEmpty()) {
	            System.out.println("No se encontraron mesas con ese id.");
	            return true;
	        }

	        // 2. Verificar solapamiento de fechas y horas para cada mesa
	        for (Mesa m : mesasConMismoId) {
	            Servicio servicioExistente = servicioControlador.buscarServicioPorId(m.getIdServicio());

	            if (servicioExistente != null) {
	                // Mostrar los detalles del servicio existente
	                System.out.println("Servicio existente: " + servicioExistente.getIdServicio() 
	                    + ", Fecha: " + servicioExistente.getFecha() 
	                    + ", HoraInicio: " + servicioExistente.getHoraInicio() 
	                    + ", HoraFin: " + servicioExistente.getHoraFin());

	                // 3. Verificar solapamiento
	                boolean solapamiento = verificarSolapamiento(
	                    servicioExistente.getFecha(),
	                    servicioExistente.getHoraInicio(),
	                    servicioExistente.getHoraFin(),
	                    servicio.getFecha(),
	                    servicio.getHoraInicio(),
	                    servicio.getHoraFin()
	                );

	                if (solapamiento) {
	                    System.out.println("Solapamiento en la mesa: " + m.getIdMesa());
	                    return false;
	                }
	            }
	        }
	        return true;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/**
	 * Crea un nuevo servicio y retorna su ID.
	 *
	 * Este método invoca al controlador de servicios para crear un nuevo servicio.
	 * Si la creación es exitosa, se devuelve el ID del servicio creado. 
	 * Si hay un error durante la creación, se devuelve -1 y se imprime un mensaje
	 * de error en la consola.
	 *
	 * @param servicio El objeto Servicio que contiene los detalles del servicio a crear.
	 * @return El ID del servicio creado o -1 si hubo un error en la creación.
	 */
	public int crearServicioYRetornarId(Servicio servicio) {
	    int idServicio = -1;
	    try {
	        idServicio = servicioControlador.crearServicio(servicio);
	        if (idServicio == -1) {
	            System.out.println("Error al crear el servicio.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return idServicio;
	}
	
	
	/**
	 * Crea una mesa y la asocia a un servicio existente.
	 *
	 * Este método establece el ID del servicio y el estado de la mesa a "OCUPADA".
	 * Luego, intenta registrar la mesa a través del controlador de mesas. Si el registro
	 * es exitoso, se cierra la ventana actual. En caso de error, se imprime un mensaje
	 * de error en la consola.
	 *
	 * @param idServicio El ID del servicio al que se asociará la mesa.
	 */
	public void crearMesas(int idServicio) {
	    try {
	        mesa.setIdServicio(idServicio);
	        mesa.setEstado(EnumEstado.OCUPADA);

	        if (mesaControlador.crearMesa(mesa)) {	            
	            dispose();
	        } else {
	            System.out.println("Error al registrar la mesa.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Constructor para crear un objeto DetalleReservaEmpleado que solo maneja bloqueos de mesas.
	 *
	 * Este constructor inicializa la mesa y el servicio asociados al objeto.
	 * También crea instancias de los controladores necesarios para gestionar servicios y mesas.
	 *
	 * @param mesa La mesa que se va a bloquear.
	 * @param servicio El servicio asociado a la mesa que se va a bloquear.
	 */
	public DetalleReservaEmpleado(Mesa mesa, Servicio servicio) {
		this.mesa = mesa;
		this.servicio = servicio;
		
		this.servicioControlador = new ServicioControlador();
		this.mesaControlador = new MesaControlador();
		
	}
	
	
	/**
	 * Método para bloquear mesas en el sistema.
	 *
	 * Este método crea un servicio asociado a la mesa que se va a bloquear y
	 * actualiza el estado de la mesa a "BLOQUEADA". Si la creación del servicio
	 * o de la mesa falla, se imprime un mensaje correspondiente en la consola.
	 */
    public void BloquearMesas() {
        try {
            int idServicio = servicioControlador.crearServicio(servicio);
            
            if (idServicio != -1) {
                mesa.setIdServicio(idServicio);
                mesa.setEstado(EnumEstado.BLOQUEADA);
                if (mesaControlador.crearMesa(mesa)) {
                    System.out.println("Mesa bloqueada correctamente.");
                } else {
                    System.out.println("La reserva ya existe o no se pudo bloquear.");
                }
            } else {
                System.out.println("Error al bloquear la mesa.");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		
    /**
     * Método para verificar el solapamiento entre dos servicios basándose en la fecha
     * y el rango de horas de inicio y fin.
     *
     * @param fecha1      La fecha del primer servicio.
     * @param horaInicio1 La hora de inicio del primer servicio.
     * @param horaFin1    La hora de fin del primer servicio.
     * @param fecha2      La fecha del segundo servicio.
     * @param horaInicio2 La hora de inicio del segundo servicio.
     * @param horaFin2    La hora de fin del segundo servicio.
     * @return true si hay solapamiento entre los dos servicios; false en caso contrario.
     */
		private boolean verificarSolapamiento(String fecha1, String horaInicio1, String horaFin1, String fecha2,
				String horaInicio2, String horaFin2) {
					
		    if (!fecha1.equals(fecha2)) {
		        return false;
		    }
		    	
		    LocalTime inicio1 = LocalTime.parse(horaInicio1);
		    LocalTime fin1 = LocalTime.parse(horaFin1);
		    LocalTime inicio2 = LocalTime.parse(horaInicio2);
		    LocalTime fin2 = LocalTime.parse(horaFin2);
		 
		    if (fin1.isBefore(inicio1)) {	        
		        return (inicio1.isBefore(fin2) || fin2.equals(fin1)) || (inicio2.isBefore(fin1) || fin1.equals(fin2));
		    } else {
		        return (inicio1.isBefore(fin2) && fin1.isAfter(inicio2));
		    }
		}

		/**
		 * Método para enviar un correo de confirmación de un evento especial.
		 *
		 * @param destinatario La dirección de correo electrónico del destinatario.
		 */
	@SuppressWarnings("static-access")
	public void enviarDetalles(String destinatario) {
	    if (destinatario == null || destinatario.isEmpty()) {
	        System.out.println("El destinatario proporcionado no es válido.");
	        return; 
	    }

		String asunto = "Confirmacion de Evento Especial - Delicias Gourmet";
		String mensaje = String.format(
				"Estimado/a cliente,\n\n"
						+ "Nos complace confirmar su Evento en nuestro restaurante con los siguientes detalles:\n\n"
						+ "   - Número de Mesas: %s\n" + "   - Ubicación: %s\n" + "   - Fecha de la Reserva: %s\n"
						+ "   - Horario de la Reserva: %s\n" + "   - Comentarios adicionales: %s\n\n"
						+ "Agradecemos su preferencia y le recordamos que estaremos encantados de recibirle.\n\n"
						+ "Saludos cordiales,\n" + "Restaurante %s",
				concatenarIdsMesas(mesasSeleccionadasEvento), mesa.getUbicacion(), reserva.getFecha(), rangoHoras,
				reserva.getComentario(), "Delicias Gourmet");
		EnviarMail.enviarCorreo(destinatario, asunto, mensaje);
	}
}

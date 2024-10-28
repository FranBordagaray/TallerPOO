package Vista.Cliente;

import java.awt.Color;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.List;

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
import javax.swing.ImageIcon;

@SuppressWarnings("unused")
/**
 * Clase que representa la interfaz gráfica para detallar la reserva de un cliente.
 * Extiende JFrame y contiene toda la lógica necesaria para mostrar y gestionar
 * la información de una reserva específica, incluyendo detalles sobre mesas, 
 * comprobantes y servicios relacionados.
 */
public class DetalleReservaCliente extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Panel principal que contiene todos los componentes de la interfaz gráfica.
	 */
	private JPanel contentPane;

	/**
	 * Objeto que representa una reserva realizada por un cliente.
	 */
	private Reserva reserva;

	/**
	 * Objeto que representa una mesa en el sistema.
	 */
	private Mesa mesa;

	/**
	 * Objeto que representa un comprobante de la reserva.
	 */
	private Comprobante comprobante;

	/**
	 * Objeto que representa un servicio disponible en el sistema.
	 */
	private Servicio servicio;

	/**
	 * Controlador encargado de gestionar las operaciones relacionadas con los servicios.
	 */
	private ServicioControlador servicioControlador;

	/**
	 * Controlador encargado de gestionar las operaciones relacionadas con las reservas.
	 */
	private ReservaControlador reservaControlador;

	/**
	 * Controlador encargado de gestionar las operaciones relacionadas con las mesas.
	 */
	private MesaControlador mesaControlador;

	/**
	 * Controlador encargado de gestionar las operaciones relacionadas con los comprobantes.
	 */
	private ComprobanteControlador comprobanteControlador;

	/**
	 * Controlador encargado de gestionar las operaciones relacionadas con las tarjetas.
	 */
	private TarjetaControlador tarjetaControlador;

	/**
	 * Objeto que representa la sesión actual del cliente.
	 */
	private SesionCliente s;

	/**
	 * Lista que contiene mesas con el mismo ID, utilizada para gestionar reservas duplicadas.
	 */
	private ArrayList<Mesa> mesasConMismoId;

	/**
	 * Vista asociada a la reserva del cliente, utilizada para mostrar información relevante.
	 */
	private VistaReservaCliente reservaCliente;
	/**
	 * Constructor de la clase DetalleReservaCliente.
	 *
	 * Este constructor inicializa una nueva instancia de la clase DetalleReservaCliente,
	 * que representa la interfaz para mostrar y gestionar los detalles de una reserva específica.
	 * Se requiere una reserva, una mesa, un servicio y un comprobante asociados a la reserva,
	 * además de una referencia a la vista principal de reserva del cliente.
	 *
	 * @param reserva La reserva asociada a los detalles que se van a mostrar.
	 * @param mesa La mesa correspondiente a la reserva.
	 * @param servicio El servicio asociado a la reserva.
	 * @param comprobante El comprobante generado para la reserva.
	 * @param reservaCliente La vista principal de reserva del cliente, que permite interactuar con la interfaz.
	 */
	public DetalleReservaCliente(Reserva reserva, Mesa mesa, Servicio servicio, Comprobante comprobante, VistaReservaCliente reservaCliente) {
		this.reserva = reserva;
		this.mesa = mesa;
		this.servicio = servicio;
		this.comprobante = comprobante;
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
		JLabel lblDetalle = new JLabel("Detalle Reserva");
		lblDetalle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetalle.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalle.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblDetalle.setBounds(101, 10, 248, 33);
		pnlContenedor.add(lblDetalle);

		// Etiqueta Ubicacion
		JLabel lblUbicacion = new JLabel("UBICACION: ");
		lblUbicacion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUbicacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUbicacion.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblUbicacion.setAlignmentX(1.0f);
		lblUbicacion.setBounds(0, 80, 200, 25);
		pnlContenedor.add(lblUbicacion);

		// Etiqueta Fecha
		JLabel lblFecha = new JLabel("FECHA: ");
		lblFecha.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblFecha.setAlignmentX(1.0f);
		lblFecha.setBounds(0, 140, 200, 25);
		pnlContenedor.add(lblFecha);

		// Etiqueta Hora
		JLabel lblHora = new JLabel("HORA: ");
		lblHora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblHora.setAlignmentX(1.0f);
		lblHora.setBounds(0, 200, 200, 25);
		pnlContenedor.add(lblHora);

		// Etiqueta Capacidad
		JLabel lblCapacidad = new JLabel("CAPACIDAD: ");
		lblCapacidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapacidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblCapacidad.setAlignmentX(1.0f);
		lblCapacidad.setBounds(0, 260, 200, 25);
		pnlContenedor.add(lblCapacidad);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("MESA: ");
		lblMesa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblMesa.setAlignmentX(1.0f);
		lblMesa.setBounds(0, 320, 200, 25);
		pnlContenedor.add(lblMesa);

		// Etiqueta Comentario
		JLabel lblComentario = new JLabel("COMENTARIO: ");
		lblComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComentario.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblComentario.setAlignmentX(1.0f);
		lblComentario.setBounds(0, 380, 200, 25);
		pnlContenedor.add(lblComentario);

		JLabel lblUbiSeleccionada = new JLabel(mesa.getUbicacion());
		lblUbiSeleccionada.setForeground(Color.BLACK);
		lblUbiSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbiSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblUbiSeleccionada.setBackground(new Color(222, 184, 135));
		lblUbiSeleccionada.setBounds(200, 80, 250, 25);
		pnlContenedor.add(lblUbiSeleccionada);

		JLabel lblFecSeleccionada = new JLabel(reserva.getFecha());
		lblFecSeleccionada.setForeground(Color.BLACK);
		lblFecSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblFecSeleccionada.setBackground(new Color(222, 184, 135));
		lblFecSeleccionada.setBounds(200, 140, 250, 25);
		pnlContenedor.add(lblFecSeleccionada);

		JLabel lblHoraSeleccionada = new JLabel(reserva.getHora());
		lblHoraSeleccionada.setForeground(Color.BLACK);
		lblHoraSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblHoraSeleccionada.setBackground(new Color(222, 184, 135));
		lblHoraSeleccionada.setBounds(200, 200, 250, 25);
		pnlContenedor.add(lblHoraSeleccionada);

		JLabel lblCapaSeleccionada = new JLabel(String.valueOf(mesa.getCapacidad()));
		lblCapaSeleccionada.setForeground(Color.BLACK);
		lblCapaSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblCapaSeleccionada.setBackground(new Color(222, 184, 135));
		lblCapaSeleccionada.setBounds(200, 260, 250, 25);
		pnlContenedor.add(lblCapaSeleccionada);

		JLabel lblMesaSeleccionada = new JLabel(String.valueOf(reserva.getIdMesa()));
		lblMesaSeleccionada.setForeground(Color.BLACK);
		lblMesaSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesaSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblMesaSeleccionada.setBackground(new Color(222, 184, 135));
		lblMesaSeleccionada.setBounds(200, 320, 250, 25);
		pnlContenedor.add(lblMesaSeleccionada);

		JTextArea textArea = new JTextArea(reserva.getComentario());
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setBorder(null);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Roboto Light", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBackground(new Color(222, 184, 135));
		textArea.setBounds(200, 384, 250, 100);
		pnlContenedor.add(textArea);

		/**
		 * Crea un botón para confirmar la reserva del cliente.
		 *
		 * Al presionar este botón, se verifica si ya existe un servicio o reserva
		 * para la mesa seleccionada en el horario indicado. Si no hay solapamiento,
		 * se crea un nuevo servicio y se registra la reserva junto con la mesa correspondiente.
		 * Si la creación es exitosa, se envían los detalles de la reserva al cliente por correo electrónico.
		 * El botón cambia de color al pasar el mouse sobre él para indicar que es interactivo.
		 */
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(DetalleReservaCliente.class.getResource("/Img/icono verificado.png")));
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int idServicio;
		        int idReserva;

		        try {
		            System.out.println("Obteniendo mesas con el mismo idMesa: " + mesa.getIdMesa());
		            mesasConMismoId = mesaControlador.buscarMesaPorId(mesa.getIdMesa());
		            
		            if (mesasConMismoId.isEmpty()) {
		                System.out.println("No se encontraron mesas con ese id.");
		            } else {
		                System.out.println("Mesas encontradas: " + mesasConMismoId.size());
		            }

		            for (Mesa m : mesasConMismoId) {
		                System.out.println("Verificando mesa con idMesa: " + m.getIdMesa() + " y idServicio: " + m.getIdServicio());

		                Servicio servicioExistente = servicioControlador.buscarServicioPorId(m.getIdServicio());

		                if (servicioExistente != null) {
		                    System.out.println("Servicio existente encontrado: ID = " + servicioExistente.getIdServicio() 
		                        + ", Fecha = " + servicioExistente.getFecha() 
		                        + ", HoraInicio = " + servicioExistente.getHoraInicio() 
		                        + ", HoraFin = " + servicioExistente.getHoraFin());

		                    boolean solapamiento = verificarSolapamiento(
		                        servicioExistente.getFecha(),
		                        servicioExistente.getHoraInicio(),
		                        servicioExistente.getHoraFin(),
		                        servicio.getFecha(),
		                        servicio.getHoraInicio(),
		                        servicio.getHoraFin()
		                    );

		                    if (solapamiento) {
		                        System.out.println("Solapamiento encontrado para la mesa con idMesa: " + m.getIdMesa());
		                        JOptionPane.showMessageDialog(DetalleReservaCliente.this,
		                            "Ya existe un servicio o reserva para esta mesa en ese horario.", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                        return;
		                    } else {
		                        System.out.println("No hay solapamiento para la mesa con idMesa: " + m.getIdMesa());
		                    }
		                } else {
		                    System.out.println("No se encontró un servicio para la mesa con idMesa: " + m.getIdMesa());
		                }
		            }

		            System.out.println("No se encontró solapamiento. Creando nuevo servicio.");
		            idServicio = servicioControlador.crearServicio(servicio);
		            
		            if (idServicio != -1) {
		                System.out.println("Servicio creado con éxito, ID: " + idServicio);
		            } else {
		                System.out.println("Error al crear el servicio.");
		                JOptionPane.showMessageDialog(DetalleReservaCliente.this, "Error al registrar el servicio",
		                    "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            reserva.setIdServicio(idServicio);
		            reserva.setEstado(1);
		            mesa.setIdServicio(idServicio);
		            mesa.setEstado(EnumEstado.OCUPADA);

		            if (mesaControlador.crearMesa(mesa)) {
		                System.out.println("Mesa registrada con éxito.");
		                if (reservaControlador.verificarReserva(reserva)) {
		                    JOptionPane.showMessageDialog(DetalleReservaCliente.this, "La reserva ya existe", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                } else {
		                    if (reservaControlador.crearReserva(reserva)) {
		                        JOptionPane.showMessageDialog(DetalleReservaCliente.this,
		                                "Reserva registrada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                        idReserva = reservaControlador.buscarIdReserva(reserva);
		                        comprobante.setIdTarjeta(tarjetaControlador.obtenerUltimoIdTarjeta());
		                        comprobante.setIdReserva(idReserva);

		                        if (comprobanteControlador.crearComprobante(comprobante)) {
		                            System.out.println("Comprobante cargado con éxito");
		                            if (reservaControlador.actualizarComprobante(idReserva,
		                                    comprobanteControlador.obtenerUltimoIdComprobante())) {
		                                System.out.println("Reserva actualizada con éxito");
		                                enviarDetalles();
		                                reservaCliente.resetearCampos();
		                                reservaCliente.habilitarBoton(false);
		                                dispose();
		                            } else {
		                                System.out.println("Ocurrió un error al actualizar la reserva");
		                            }
		                        } else {
		                            System.out.println("Ocurrió un error al registrar el comprobante");
		                        }
		                    } else {
		                        JOptionPane.showMessageDialog(DetalleReservaCliente.this,
		                                "Ocurrió un error al registrar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            } else {
		                System.out.println("Ocurrió un error al registrar la mesa");
		            }

		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(DetalleReservaCliente.this,
		                "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            e2.printStackTrace();
		        }
		    }
		});


		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirmar.setBackground(new Color(126, 211, 33));
				btnConfirmar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmar.setBackground(Color.WHITE);
				btnConfirmar.setForeground(Color.BLACK);
			}
		});
		btnConfirmar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnConfirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirmar.setBorder(null);
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setBounds(279, 494, 120, 30);
		pnlContenedor.add(btnConfirmar);

		/**
		 * Crea un botón para cancelar la acción actual y regresar a la vista de reservas del cliente.
		 *
		 * Este botón, al ser presionado, cierra la ventana actual de detalles de reserva
		 * y abre la vista de reservas del cliente. Además, cambia de color al pasar el mouse
		 * sobre él para indicar que es interactivo.
		 */
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(DetalleReservaCliente.class.getResource("/Img/icono cancelar.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VistaReservaCliente reserva = new VistaReservaCliente();
				reserva.setVisible(true);
				DetalleReservaCliente.this.dispose();

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
		btnCancelar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancelar.setBorder(null);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(52, 494, 120, 30);
		pnlContenedor.add(btnCancelar);

	}

	/**
	 * Verifica si hay solapamiento entre dos servicios en función de sus fechas y horas.
	 *
	 * Este método comprueba si dos servicios ocurren en la misma fecha y si sus horas de inicio y fin
	 * se superponen. Si las fechas son diferentes, se considera que no hay solapamiento.
	 *
	 * @param fecha1     La fecha del primer servicio en formato "yyyy-MM-dd".
	 * @param horaInicio1 La hora de inicio del primer servicio en formato "HH:mm".
	 * @param horaFin1   La hora de fin del primer servicio en formato "HH:mm".
	 * @param fecha2     La fecha del segundo servicio en formato "yyyy-MM-dd".
	 * @param horaInicio2 La hora de inicio del segundo servicio en formato "HH:mm".
	 * @param horaFin2   La hora de fin del segundo servicio en formato "HH:mm".
	 * @return true si hay solapamiento entre los dos servicios, false en caso contrario.
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
	 * Envía un correo electrónico de confirmación de reserva al cliente actual.
	 *
	 * Este método obtiene la dirección de correo electrónico del cliente actual y
	 * genera un mensaje de confirmación con los detalles de la reserva, incluyendo
	 * el número de mesa, ubicación, capacidad, fecha, hora y comentarios adicionales.
	 * Luego, utiliza la clase `EnviarMail` para enviar el correo al destinatario.
	 *
	 * Si no se puede obtener una dirección de correo válida del cliente, se imprime un mensaje
	 * en la consola y se termina la ejecución del método.
	 */
	@SuppressWarnings("static-access")
	public void enviarDetalles() {
	    SesionCliente s = new SesionCliente();
	    String destinatario = null;

	    if (s.getClienteActual() != null && s.getClienteActual().getEmail() != null) {
	        destinatario = s.getClienteActual().getEmail();
	    } else {
	        System.out.println("No se pudo obtener un email válido del cliente.");
	        return; 
	    }

	    String asunto = "Confirmación de reserva - Delicias Gourmet";
	    String mensaje = String.format(
	            "Estimado/a cliente,\n\n"
	                    + "Nos complace confirmar su reserva en nuestro restaurante con los siguientes detalles:\n\n"
	                    + "   - Número de Mesa: %d\n" 
	                    + "   - Ubicación: %s\n"
	                    + "   - Capacidad de la Mesa: %d personas\n" 
	                    + "   - Fecha de la Reserva: %s\n"
	                    + "   - Hora de la Reserva: %s\n" 
	                    + "   - Comentarios adicionales: %s\n\n"
	                    + "Agradecemos su preferencia y le recordamos que estaremos encantados de recibirle.\n\n"
	                    + "Saludos cordiales,\n" 
	                    + "Restaurante %s",
	            mesa.getIdMesa(), mesa.getUbicacion(), mesa.getCapacidad(), reserva.getFecha(), reserva.getHora(),
	            reserva.getComentario(), "Delicias Gourmet");

	    EnviarMail.enviarCorreo(destinatario, asunto, mensaje);
	}	
}

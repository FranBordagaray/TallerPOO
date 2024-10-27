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

@SuppressWarnings("unused")
public class DetalleReservaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Reserva reserva;
	private Mesa mesa;
	private String[] mesasSeleccionadas;
	private Comprobante comprobante;
	private Servicio servicio;
	private ServicioControlador servicioControlador;
	private ReservaControlador reservaControlador;
	private MesaControlador mesaControlador;
	private ComprobanteControlador comprobanteControlador;
	private TarjetaControlador tarjetaControlador;
	private SesionCliente s;
	private SesionEmpleado v;
	private String rangoHoras;
	private String destinatario;
	private String ubicacion;
	private String fecha;
	private String horaInicio;
	private String horaFin;
	private String comentario;
	private String[] mesasSeleccionadasEvento;
	private VistaReservaEmpleado reservaCliente;

	/**
	 * @wbp.parser.constructor
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
	
	// Metodo para concatenar las mesas
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

	//Metodo para verificar disponibilidad de las Mesas
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
	
	
	// Método para crear las mesas y las reservas
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
	
	//Otro constructor para solo hacer Bloqueos de Mesas
	public DetalleReservaEmpleado(Mesa mesa, Servicio servicio) {
		this.mesa = mesa;
		this.servicio = servicio;
		
		this.servicioControlador = new ServicioControlador();
		this.mesaControlador = new MesaControlador();
		
	}
	
	
	// Método para bloquear mesas
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
		
	// Método para verificar solapamiento entre dos servicios
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

	// Metodo para enviar mail
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

package Vista.Empleado;

import java.awt.Color;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	public DetalleReservaEmpleado(Reserva reserva, Mesa mesa, Servicio servicio, Comprobante comprobante,
			String[] mesasSeleccionadas) {
		
		this.reserva = reserva;
		this.mesa = mesa;
		this.servicio = servicio;
		this.comprobante = comprobante;
		this.mesasSeleccionadas = mesasSeleccionadas;

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
		JLabel lblHora = new JLabel("HORARIO: ");
		lblHora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblHora.setAlignmentX(1.0f);
		lblHora.setBounds(0, 200, 200, 25);
		pnlContenedor.add(lblHora);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("MESAS: ");
		lblMesa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblMesa.setAlignmentX(1.0f);
		lblMesa.setBounds(0, 248, 200, 25);
		pnlContenedor.add(lblMesa);

		// Etiqueta Comentario
		JLabel lblComentario = new JLabel("COMENTARIO: ");
		lblComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComentario.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblComentario.setAlignmentX(1.0f);
		lblComentario.setBounds(0, 293, 200, 25);
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
		lblUbiSeleccionada.setBounds(200, 80, 250, 25);
		pnlContenedor.add(lblUbiSeleccionada);
		pnlContenedor.revalidate();
		pnlContenedor.repaint();

		// Etiqueta de Fecha de la reserva
		JLabel lblFecSeleccionada = new JLabel(fecha);
		lblFecSeleccionada.setForeground(Color.BLACK);
		lblFecSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblFecSeleccionada.setBackground(new Color(222, 184, 135));
		lblFecSeleccionada.setBounds(200, 140, 250, 25);
		pnlContenedor.add(lblFecSeleccionada);

		// Etiqueta de el rango de Horas de la reserva
		JLabel lblHoraSeleccionada = new JLabel(rangoHoras);
		lblHoraSeleccionada.setForeground(Color.BLACK);
		lblHoraSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblHoraSeleccionada.setBackground(new Color(222, 184, 135));
		lblHoraSeleccionada.setBounds(200, 200, 250, 25);
		pnlContenedor.add(lblHoraSeleccionada);

		// Etiqueta de Mesas Seleccionadas de la reserva
		JLabel lblMesaSeleccionada = new JLabel(concatenarIdsMesas(mesasSeleccionadas));
		lblMesaSeleccionada.setForeground(Color.BLACK);
		lblMesaSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesaSeleccionada.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblMesaSeleccionada.setBackground(new Color(222, 184, 135));
		lblMesaSeleccionada.setBounds(210, 248, 250, 25);
		pnlContenedor.add(lblMesaSeleccionada);

		// Etiqueta de Comentarios de la reserva
		JTextArea textArea = new JTextArea(comentario);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setBorder(null);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Roboto Light", Font.BOLD, 14));
		textArea.setEditable(false);
		textArea.setBackground(new Color(222, 184, 135));
		textArea.setBounds(150, 337, 250, 100);
		pnlContenedor.add(textArea);

		// Botón de Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		btnConfirmar.setBounds(280, 500, 120, 30);
		pnlContenedor.add(btnConfirmar);

		// Boton para Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VistaReservaEmpleado reserva = new VistaReservaEmpleado();
				reserva.setVisible(true);
				DetalleReservaEmpleado.this.dispose();

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
		btnCancelar.setBounds(50, 500, 120, 30);
		pnlContenedor.add(btnCancelar);

	}

	// Metodo para concatenar las mesas
	public String concatenarIdsMesas(String[] mesas) {
		StringBuilder sb = new StringBuilder();
		for (String mesa : mesas) {
			sb.append(mesa).append(",");
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
		
	}

	public void procesarReserva() {
		int idServicio;
		int idReserva;

		try {
			// Crear un nuevo servicio
			idServicio = servicioControlador.crearServicio(servicio);
			System.out.println("Nuevo servicio creado: " + idServicio);

			if (idServicio != -1) {
				System.out.println("Servicio ingresado con éxito, ID: " + idServicio);
			} else {
				JOptionPane.showMessageDialog(DetalleReservaEmpleado.this, "Error al registrar el servicio", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Asignar el ID del servicio a la reserva
			reserva.setIdServicio(idServicio);
			reserva.setEstado(1);
			mesa.setIdServicio(idServicio);
			mesa.setEstado(EnumEstado.OCUPADA);

			// Registrar la mesa
			if (mesaControlador.crearMesa(mesa)) {
				System.out.println("Mesa registrada con éxito.");

				// Crear la nueva reserva directamente
				if (reservaControlador.crearReserva(reserva)) {
					idReserva = reservaControlador.buscarIdReserva(reserva);
					comprobante.setIdTarjeta(tarjetaControlador.obtenerUltimoIdTarjeta());
					comprobante.setIdReserva(idReserva);

					// Crear el comprobante
					if (comprobanteControlador.crearComprobante(comprobante)) {
						System.out.print("Comprobante cargado con éxito");

						// Actualizar la reserva con el ID del comprobante
						if (reservaControlador.actualizarComprobante(idReserva,
								comprobanteControlador.obtenerUltimoIdComprobante())) {
							System.out.print("Reserva se actualizó con éxito");
							dispose();
						} else {
							System.out.print("Ocurrió un error al actualizar la reserva");
						}
					} else {
						System.out.print("Ocurrió un error al registrar el comprobante");
					}
				} else {
					JOptionPane.showMessageDialog(DetalleReservaEmpleado.this,
							"Ocurrió un error al registrar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				System.out.print("Ocurrió un error al registrar la mesa");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(DetalleReservaEmpleado.this, "Error inesperado: " + e2.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
	}

	// Metodo para verificar si las mesas no se solapan
	public boolean verificarMesaDisponible(Mesa mesa, Servicio servicio) {
		if (mesaControlador.verificarMesaConServicio(mesa, servicio.getFecha(), servicio.getHoraInicio(),
				servicio.getHoraFin())) {
			return false;
		}
		return true;
	}

	// Metodo para enviar mail
	@SuppressWarnings("static-access")
	public void enviarDetalles() {
		s = new SesionCliente();
		v = new SesionEmpleado();
		if (s.getClienteActual() != null) {
			destinatario = s.getClienteActual().getEmail();
		} else {
			destinatario = v.getEmpleadoActual().getEmail();
		}

		String asunto = "Confirmacion de Evento Especial - Delicias Gourmet";
		String mensaje = String.format(
				"Estimado/a cliente,\n\n"
						+ "Nos complace confirmar su Evento en nuestro restaurante con los siguientes detalles:\n\n"
						+ "   - Número de Mesas: %s\n" + "   - Ubicación: %s\n" + "   - Fecha de la Reserva: %s\n"
						+ "   - Horario de la Reserva: %s\n" + "   - Comentarios adicionales: %s\n\n"
						+ "Agradecemos su preferencia y le recordamos que estaremos encantados de recibirle.\n\n"
						+ "Saludos cordiales,\n" + "Restaurante %s",
				concatenarIdsMesas(mesasSeleccionadas), mesa.getUbicacion(), reserva.getFecha(), rangoHoras,
				reserva.getComentario(), "Delicias Gourmet");
		EnviarMail.enviarCorreo(destinatario, asunto, mensaje);
	}
}

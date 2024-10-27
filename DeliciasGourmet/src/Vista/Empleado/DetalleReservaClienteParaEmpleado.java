package Vista.Empleado;

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
public class DetalleReservaClienteParaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Reserva reserva;
	private Mesa mesa;
	private Comprobante comprobante;
	private Servicio servicio;
	private ServicioControlador servicioControlador;
	private ReservaControlador reservaControlador;
	private MesaControlador mesaControlador;
	private ComprobanteControlador comprobanteControlador;
	private TarjetaControlador tarjetaControlador;
	private SesionCliente s;
	private ArrayList<Mesa> mesasConMismoId;
	private VistaReservaEmpleado reservaCliente;

	public DetalleReservaClienteParaEmpleado(Reserva reserva, Mesa mesa, Servicio servicio, Comprobante comprobante, VistaReservaEmpleado reservaCliente) {
		this.reserva = reserva;
		this.mesa = mesa;
		this.servicio = servicio;
		this.comprobante = comprobante;

		this.servicioControlador = new ServicioControlador();
		this.reservaControlador = new ReservaControlador();
		this.mesaControlador = new MesaControlador();
		this.comprobanteControlador = new ComprobanteControlador();
		this.tarjetaControlador = new TarjetaControlador();
		this.reservaCliente = reservaCliente;


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

		// Boton de Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(DetalleReservaClienteParaEmpleado.class.getResource("/Img/icono verificado.png")));
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int idServicio;
		        int idReserva;

		        try {
		            // 1. Obtener todas las mesas con el mismo idMesa
		            System.out.println("Obteniendo mesas con el mismo idMesa: " + mesa.getIdMesa());
		            mesasConMismoId = mesaControlador.buscarMesaPorId(mesa.getIdMesa());

		            // Verificar si hay mesas obtenidas
		            if (mesasConMismoId.isEmpty()) {
		                System.out.println("No se encontraron mesas con ese id.");
		            } else {
		                System.out.println("Mesas encontradas: " + mesasConMismoId.size());
		            }

		            // 2. Verificar solapamiento de fechas y horas para cada mesa
		            for (Mesa m : mesasConMismoId) {
		                System.out.println("Verificando mesa con idMesa: " + m.getIdMesa() + " y idServicio: " + m.getIdServicio());

		                // Obtener el servicio de la mesa
		                Servicio servicioExistente = servicioControlador.buscarServicioPorId(m.getIdServicio());

		                if (servicioExistente != null) {
		                    // Mostrar los detalles del servicio existente
		                    System.out.println("Servicio existente encontrado: ID = " + servicioExistente.getIdServicio() 
		                        + ", Fecha = " + servicioExistente.getFecha() 
		                        + ", HoraInicio = " + servicioExistente.getHoraInicio() 
		                        + ", HoraFin = " + servicioExistente.getHoraFin());

		                    // 3. Comprobar si la fecha y las horas se solapan
		                    boolean solapamiento = verificarSolapamiento(
		                        servicioExistente.getFecha(),
		                        servicioExistente.getHoraInicio(),
		                        servicioExistente.getHoraFin(),
		                        servicio.getFecha(),
		                        servicio.getHoraInicio(),
		                        servicio.getHoraFin()
		                    );

		                    if (solapamiento) {
		                        // Mostrar que se encontró un solapamiento
		                        System.out.println("Solapamiento encontrado para la mesa con idMesa: " + m.getIdMesa());
		                        JOptionPane.showMessageDialog(DetalleReservaClienteParaEmpleado.this,
		                            "Ya existe un servicio o reserva para esta mesa en ese horario.", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                        return; // Detener aquí, no se continúa con la creación del servicio/reserva.
		                    } else {
		                        // Indicar que no hubo solapamiento para esta mesa
		                        System.out.println("No hay solapamiento para la mesa con idMesa: " + m.getIdMesa());
		                    }
		                } else {
		                    // Indicar que no se encontró un servicio para la mesa
		                    System.out.println("No se encontró un servicio para la mesa con idMesa: " + m.getIdMesa());
		                }
		            }

		            // 5. Si no hubo solapamiento en ninguna mesa, continuar con la creación del servicio
		            System.out.println("No se encontró solapamiento. Creando nuevo servicio.");
		            idServicio = servicioControlador.crearServicio(servicio);
		            
		            // Verificar si el servicio fue creado correctamente
		            if (idServicio != -1) {
		                System.out.println("Servicio creado con éxito, ID: " + idServicio);
		            } else {
		                System.out.println("Error al crear el servicio.");
		                JOptionPane.showMessageDialog(DetalleReservaClienteParaEmpleado.this, "Error al registrar el servicio",
		                    "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Actualizar la reserva y la mesa con el idServicio generado
		            reserva.setIdServicio(idServicio);
		            reserva.setEstado(1);
		            mesa.setIdServicio(idServicio);
		            mesa.setEstado(EnumEstado.OCUPADA);

		            // Registrar la mesa y la reserva
		            if (mesaControlador.crearMesa(mesa)) {
		                System.out.println("Mesa registrada con éxito.");
		                if (reservaControlador.verificarReserva(reserva)) {
		                    JOptionPane.showMessageDialog(DetalleReservaClienteParaEmpleado.this, "La reserva ya existe", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                } else {
		                    if (reservaControlador.crearReserva(reserva)) {
		                        JOptionPane.showMessageDialog(DetalleReservaClienteParaEmpleado.this,
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
		                                dispose();
		                                

		                            } else {
		                                System.out.println("Ocurrió un error al actualizar la reserva");
		                            }
		                        } else {
		                            System.out.println("Ocurrió un error al registrar el comprobante");
		                        }
		                    } else {
		                        JOptionPane.showMessageDialog(DetalleReservaClienteParaEmpleado.this,
		                                "Ocurrió un error al registrar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            } else {
		                System.out.println("Ocurrió un error al registrar la mesa");
		            }

		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(DetalleReservaClienteParaEmpleado.this,
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

		// Boton para Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(DetalleReservaClienteParaEmpleado.class.getResource("/Img/icono cancelar.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				DetalleReservaClienteParaEmpleado.this.dispose();
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

	
	// Método para enviar mail
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

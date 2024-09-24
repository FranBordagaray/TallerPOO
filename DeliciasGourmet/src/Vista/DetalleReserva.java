package Vista;

import java.awt.Color;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import Controlador.ServicioControlador;
import Modelo.Mesa;
import Modelo.Reserva;
import Modelo.Servicio;

@SuppressWarnings("unused")
public class DetalleReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Reserva reserva;
	private Mesa mesa;
	private Servicio servicio;
	private ServicioControlador servicioControlador;
	private ReservaControlador reservaControlador;
	private MesaControlador mesaControlador;
	
	public DetalleReserva(Reserva reserva, Mesa mesa, Servicio servicio) {
		this.reserva = reserva;
		this.mesa = mesa;
		this.servicio = servicio;
		this.servicioControlador = new ServicioControlador();
		this.reservaControlador = new ReservaControlador();
		 
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
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            if (servicioControlador.verficarServicio(servicio)) {
		                JOptionPane.showMessageDialog(DetalleReserva.this, "Ya existe un servicio en ese horario", "Error", JOptionPane.WARNING_MESSAGE);
		            } else {
		                int idServicio = servicioControlador.crearServicio(servicio);
		                if (idServicio != -1) {
		                    System.out.println("Servicio ingresado con éxito");
		                    reserva.setIdServicio(idServicio);
		                    if (reservaControlador.crearReserva(reserva)) {
		                        JOptionPane.showMessageDialog(DetalleReserva.this, "Reserva registrada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                        dispose();
		                    } else {
		                        JOptionPane.showMessageDialog(DetalleReserva.this, "Ocurrió un error al registrar su reserva", "Error", JOptionPane.ERROR_MESSAGE);
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(DetalleReserva.this, "Error al registrar el servicio", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(DetalleReserva.this, "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            System.out.print(e2.getMessage());
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
		btnConfirmar.setBounds(280, 500, 120, 30);
		pnlContenedor.add(btnConfirmar);

		// Boton para Cancelar
		JButton btnCancelar = new JButton("Cancelar");

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VistaReserva reserva = new VistaReserva();
				reserva.setVisible(true);
				DetalleReserva.this.dispose();
				
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
}	
    

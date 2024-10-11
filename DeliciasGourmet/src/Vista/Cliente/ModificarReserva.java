package Vista.Cliente;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ModificarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUbicacion;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtCapacidad;
	private JTextField txtMesa;
	private JTextArea txtComentario;

	public ModificarReserva() {

		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 562);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);

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
		JLabel lblDetalle = new JLabel("Modificar Reserva");
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

		// Campo Ubicacion (dinámico)
		txtUbicacion = new JTextField();
		txtUbicacion.setForeground(Color.BLACK);
		txtUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtUbicacion.setFont(new Font("Roboto Light", Font.BOLD, 16));
		txtUbicacion.setBackground(new Color(222, 184, 135));
		txtUbicacion.setBounds(200, 80, 250, 25);
		pnlContenedor.add(txtUbicacion);

		// Etiqueta Fecha
		JLabel lblFecha = new JLabel("FECHA: ");
		lblFecha.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblFecha.setAlignmentX(1.0f);
		lblFecha.setBounds(0, 140, 200, 25);
		pnlContenedor.add(lblFecha);

		// Campo Fecha (dinámico)
		txtFecha = new JTextField();
		txtFecha.setForeground(Color.BLACK);
		txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtFecha.setFont(new Font("Roboto Light", Font.BOLD, 16));
		txtFecha.setBackground(new Color(222, 184, 135));
		txtFecha.setBounds(200, 140, 250, 25);
		pnlContenedor.add(txtFecha);

		// Etiqueta Hora
		JLabel lblHora = new JLabel("HORA: ");
		lblHora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblHora.setAlignmentX(1.0f);
		lblHora.setBounds(0, 200, 200, 25);
		pnlContenedor.add(lblHora);

		// Campo Hora (dinámico)
		txtHora = new JTextField();
		txtHora.setForeground(Color.BLACK);
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setFont(new Font("Roboto Light", Font.BOLD, 16));
		txtHora.setBackground(new Color(222, 184, 135));
		txtHora.setBounds(200, 200, 250, 25);
		pnlContenedor.add(txtHora);

		// Etiqueta Capacidad
		JLabel lblCapacidad = new JLabel("CAPACIDAD: ");
		lblCapacidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapacidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblCapacidad.setAlignmentX(1.0f);
		lblCapacidad.setBounds(0, 260, 200, 25);
		pnlContenedor.add(lblCapacidad);

		// Campo Capacidad (dinámico)
		txtCapacidad = new JTextField();
		txtCapacidad.setForeground(Color.BLACK);
		txtCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCapacidad.setFont(new Font("Roboto Light", Font.BOLD, 16));
		txtCapacidad.setBackground(new Color(222, 184, 135));
		txtCapacidad.setBounds(200, 260, 250, 25);
		pnlContenedor.add(txtCapacidad);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("MESA: ");
		lblMesa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblMesa.setAlignmentX(1.0f);
		lblMesa.setBounds(0, 320, 200, 25);
		pnlContenedor.add(lblMesa);

		// Campo Mesa (dinámico)
		txtMesa = new JTextField();
		txtMesa.setForeground(Color.BLACK);
		txtMesa.setHorizontalAlignment(SwingConstants.CENTER);
		txtMesa.setFont(new Font("Roboto Light", Font.BOLD, 16));
		txtMesa.setBackground(new Color(222, 184, 135));
		txtMesa.setBounds(200, 320, 250, 25);
		pnlContenedor.add(txtMesa);

		// Etiqueta Comentario
		JLabel lblComentario = new JLabel("COMENTARIO: ");
		lblComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComentario.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblComentario.setAlignmentX(1.0f);
		lblComentario.setBounds(0, 380, 200, 25);
		pnlContenedor.add(lblComentario);

		// Campo Comentario (dinámico)
		txtComentario = new JTextArea();
		txtComentario.setForeground(Color.BLACK);
		txtComentario.setFont(new Font("Roboto Light", Font.BOLD, 14));
		txtComentario.setBackground(new Color(222, 184, 135));
		txtComentario.setBounds(200, 384, 250, 100);
		pnlContenedor.add(txtComentario);

		// Botón de Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
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
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setBounds(280, 500, 120, 30);
		pnlContenedor.add(btnConfirmar);

		// Botón para Cancelar
		JButton btnCancelar = new JButton("Cancelar");
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
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(50, 500, 120, 30);
		pnlContenedor.add(btnCancelar);
	}
}

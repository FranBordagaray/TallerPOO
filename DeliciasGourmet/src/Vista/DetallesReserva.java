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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DetallesReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCapacidad;
	private JTextField txtMesa;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextArea txtAreaComentario;
	private JTextField textUbicacion;

	public DetallesReserva() {
		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 562);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setResizable(false);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel contenedor de formulario
		JPanel pnlContenedor = new JPanel();
		pnlContenedor.setBorder(null);
		pnlContenedor.setBackground(new Color(222, 184, 135));
		pnlContenedor.setBounds(0, 0, 712, 562);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta Detalle de Reserva
		JLabel lblDetalle = new JLabel("Detalle Reserva");
		lblDetalle.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblDetalle.setBounds(263, 10, 203, 33);
		pnlContenedor.add(lblDetalle);

		// Etiqueta Ubicacion
		JLabel lblUbicacion = new JLabel("UBICACION:");
		lblUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbicacion.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblUbicacion.setAlignmentX(1.0f);
		lblUbicacion.setBounds(53, 60, 248, 27);
		pnlContenedor.add(lblUbicacion);

		textUbicacion = new JTextField();
		textUbicacion.setEditable(false);
		textUbicacion.setBorder(null);
		textUbicacion.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		textUbicacion.setForeground(Color.BLACK);
		textUbicacion.setBackground(Color.WHITE);
		textUbicacion.setBounds(300, 60, 300, 30);
		pnlContenedor.add(textUbicacion);
		textUbicacion.setColumns(10);

		// Etiqueta Fecha
		JLabel lblFecha = new JLabel("FECHA:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblFecha.setAlignmentX(1.0f);
		lblFecha.setBounds(53, 120, 248, 27);
		pnlContenedor.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		txtFecha.setColumns(10);
		txtFecha.setBorder(null);
		txtFecha.setBounds(300, 120, 300, 30);
		pnlContenedor.add(txtFecha);

		// Etiqueta Hora
		JLabel lblHora = new JLabel("HORA: ");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblHora.setAlignmentX(1.0f);
		lblHora.setBounds(53, 180, 248, 27);
		pnlContenedor.add(lblHora);

		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		txtHora.setColumns(10);
		txtHora.setBorder(null);
		txtHora.setBounds(300, 180, 300, 30);
		pnlContenedor.add(txtHora);

		// Etiqueta Capacidad
		JLabel lblCapacidad = new JLabel("CAPACIDAD: ");
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblCapacidad.setAlignmentX(1.0f);
		lblCapacidad.setBounds(53, 240, 248, 27);
		pnlContenedor.add(lblCapacidad);

		txtCapacidad = new JTextField();
		txtCapacidad.setEditable(false);
		txtCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		txtCapacidad.setColumns(10);
		txtCapacidad.setBorder(null);
		txtCapacidad.setBounds(300, 240, 300, 30);
		pnlContenedor.add(txtCapacidad);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("MESA:");
		lblMesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblMesa.setAlignmentX(1.0f);
		lblMesa.setBounds(53, 300, 248, 27);
		pnlContenedor.add(lblMesa);

		txtMesa = new JTextField();
		txtMesa.setEditable(false);
		txtMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		txtMesa.setColumns(10);
		txtMesa.setBorder(null);
		txtMesa.setBounds(300, 300, 300, 30);
		pnlContenedor.add(txtMesa);

		// Etiqueta Comentario
		JLabel lblComentario = new JLabel("COMENTARIO: ");
		lblComentario.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentario.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblComentario.setAlignmentX(1.0f);
		lblComentario.setBounds(53, 360, 248, 27);
		pnlContenedor.add(lblComentario);

		txtAreaComentario = new JTextArea();
		txtAreaComentario.setFont(new Font("Roboto Light", Font.PLAIN, 13));
		txtAreaComentario.setBounds(300, 360, 300, 130);
		pnlContenedor.add(txtAreaComentario);

		// Boton de Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});

		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirmar.setBackground(new Color(255, 0, 0));
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
		btnConfirmar.setBounds(440, 510, 160, 30);
		pnlContenedor.add(btnConfirmar);

		// Boton para ir Atras
		JButton btnAtras = new JButton("Atras");

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});

		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(255, 0, 0));
				btnAtras.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.WHITE);
				btnAtras.setForeground(Color.BLACK);
			}
		});
		btnAtras.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAtras.setBorder(null);
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setBounds(100, 510, 160, 30);
		pnlContenedor.add(btnAtras);

		//

	}

}
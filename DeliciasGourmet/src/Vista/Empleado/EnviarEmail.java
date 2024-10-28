package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Vista.Cliente.RecuperarCuenta;

/**
 * Clase que representa la ventana para enviar un correo electrónico.
 * Extiende de JFrame y permite al usuario ingresar una dirección de
 * correo electrónico para enviar notificaciones relacionadas con la
 * reserva.
 */
public class EnviarEmail extends JFrame {

    private static final long serialVersionUID = 1L; // SerialVersionUID para la serialización
    private JPanel contentPane; // Panel principal de la ventana que contiene todos los componentes visuales
    private JTextField txtEmail; // Campo de texto donde el usuario ingresa la dirección de correo electrónico

    /**
     * Constructor de la clase EnviarEmail.
     *
     * @param empleado Objeto DetalleReservaEmpleado que representa la información del empleado
     *                 que está enviando el correo. Se puede usar para obtener detalles de la reserva
     *                 o información adicional relacionada.
     */

	public EnviarEmail(DetalleReservaEmpleado empleado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 600, 326);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblTitulo = new JLabel("ENVIAR EMAIL DEL EVENTO");
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 21));
		lblTitulo.setBounds(160, 20, 280, 22);
		contentPane.add(lblTitulo);

		// Texto de instrucciones para recuperar la contraseña
		JTextPane intrucciones = new JTextPane();
		intrucciones.setForeground(Color.BLACK);
		intrucciones.setBackground(new Color(195, 155, 107));
		intrucciones.setEditable(false);
		intrucciones.setFont(new Font("Roboto Light", Font.PLAIN, 15));
		intrucciones.setText("Escribir el correo a donde se enviara el comprobante del Evento");
		intrucciones.setBounds(156, 73, 307, 64);
		contentPane.add(intrucciones);

		// Etiqueta y campo de correo electrónico
		JLabel lblEmail = new JLabel("CORREO");
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 19));
		lblEmail.setBounds(255, 150, 90, 30);
		contentPane.add(lblEmail);

		// Boton para enviar correo de recuperación
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String email = txtEmail.getText();
					
					if(esCorreoValido(email)) {
						empleado.enviarDetalles(email);
						JOptionPane.showMessageDialog(null, "Correo enviado con éxito.");
						empleado.setVisible(false);
						EnviarEmail.this.setVisible(false);
			
					} else {
						JOptionPane.showMessageDialog(null, "Correo no ingresado o Correo Invalido.");
					}
					
					
				
			}

		});
		btnEnviar.setIcon(new ImageIcon(RecuperarCuenta.class.getResource("/Img/icono enviar.png")));

		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEnviar.setBackground(new Color(255, 0, 0));
				btnEnviar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEnviar.setBackground(Color.WHITE);
				btnEnviar.setForeground(Color.BLACK);
			}
		});
		btnEnviar.setForeground(Color.BLACK);
		btnEnviar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnEnviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnviar.setBorder(null);
		btnEnviar.setBackground(Color.WHITE);
		btnEnviar.setAlignmentX(0.5f);
		btnEnviar.setBounds(215, 243, 160, 30);
		contentPane.add(btnEnviar);

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnviarEmail.this.setVisible(false);
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
				btnCerrar.setForeground(Color.BLACK);
				btnCerrar.setBackground(new Color(195, 155, 107));
			}
		});
		btnCerrar.setBackground(new Color(195, 155, 107));
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.setBorder(null);
		btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnCerrar.setBounds(555, 0, 45, 30);
		contentPane.add(btnCerrar);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto Light", Font.BOLD, 13));
		txtEmail.setBounds(80, 190, 451, 30);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

	}

	/**
	 * Método para verificar si una dirección de correo electrónico es válida.
	 *
	 * @param correo La dirección de correo electrónico a validar.
	 * @return true si el correo es válido; false en caso contrario.
	 */
	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}
}

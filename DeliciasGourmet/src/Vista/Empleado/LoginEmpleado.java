package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controlador.EmpleadoControlador;

/**
 * Clase que representa la ventana de inicio de sesión para empleados del sistema.
 * Permite a los empleados ingresar sus credenciales para acceder a la aplicación.
 */
public class LoginEmpleado extends JFrame {

    private static final long serialVersionUID = 1L; // Identificador único de la clase
    private JPanel contentPane; // Panel principal de la ventana
    private JTextField txtUsuario; // Campo de texto para el nombre de usuario
    private JPasswordField txtContrasenia; // Campo de texto para la contraseña

    // Controlador para la gestión de empleados
    EmpleadoControlador controlador = new EmpleadoControlador();

    /**
     * Constructor de la clase LoginEmpleado.
     * Inicializa la ventana con los componentes necesarios y la configuración básica.
     */
	public LoginEmpleado() {
		// Configuración de la ventana principal
		setTitle("Login empleado");
		ImageIcon icon = new ImageIcon(getClass().getResource("/Img/icono general.png"));
        setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 679);
		setLocationRelativeTo(null);
		setResizable(false);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel contenedor de formulario
		JPanel pnlContenedor = new JPanel();
		pnlContenedor.setBackground(new Color(195, 155, 107));
		pnlContenedor.setBounds(0, 0, 696, 640);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta de iniciar sesión
		JLabel lblIniciarSesion = new JLabel("INICIAR SESIÓN");
		lblIniciarSesion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblIniciarSesion.setBounds(198, 100, 300, 30);
		pnlContenedor.add(lblIniciarSesion);

		// Etiqueta y campo de texto de usuario
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setBorder(null);
		lblUsuario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblUsuario.setBounds(198, 200, 300, 30);
		pnlContenedor.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(198, 240, 300, 30);
		pnlContenedor.add(txtUsuario);
		txtUsuario.setColumns(10);

		// Etiqueta y campo de texto de contrasenia
		JLabel lblContrasenia = new JLabel("CONTRASEÑA");
		lblContrasenia.setHorizontalTextPosition(SwingConstants.CENTER);
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblContrasenia.setBounds(198, 310, 300, 30);
		pnlContenedor.add(lblContrasenia);

		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtContrasenia.setBorder(null);
		txtContrasenia.setBounds(198, 350, 300, 30);
		pnlContenedor.add(txtContrasenia);

		// Boton para iniciar sesion
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setIcon(new ImageIcon(LoginEmpleado.class.getResource("/Img/icono de inicio de sesion.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampos()) {
						return;
					} else if (controlador.iniciarSesion(txtUsuario.getText(),
							String.valueOf(txtContrasenia.getPassword()))) {

						String rol = controlador.obtenerRol(txtUsuario.getText());

						JOptionPane.showMessageDialog(LoginEmpleado.this, "Bienvenido", "Exito",
								JOptionPane.INFORMATION_MESSAGE);

						InicioEmpleado inicio = new InicioEmpleado(rol);

						inicio.setVisible(true);

						LoginEmpleado.this.setVisible(false);
					} else {

						JOptionPane.showMessageDialog(LoginEmpleado.this,
								"Error al iniciar sesion, verifique sus datos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(LoginEmpleado.this, "Error inesperado: " + e2.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(255, 0, 0));
				btnLogin.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(Color.WHITE);
				btnLogin.setForeground(Color.BLACK);
			}
		});
		btnLogin.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setBorder(null);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(248, 491, 200, 30);
		pnlContenedor.add(btnLogin);
	}

	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String usuario = txtUsuario.getText();
		String contrasenia = String.valueOf(txtContrasenia.getPassword());

		if (usuario.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
}
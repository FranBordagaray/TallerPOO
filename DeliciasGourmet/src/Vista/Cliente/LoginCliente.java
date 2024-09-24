package Vista.Cliente;

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

import Controlador.ClienteControlador;

public class LoginCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtContrasenia;
	ClienteControlador controlador = new ClienteControlador();
	//franbordagaray57@gmail.com
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCliente frame = new LoginCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginCliente() {
		// Configuración de la ventana principal
		setTitle("Delicias Gourmet");
		ImageIcon icon = new ImageIcon(getClass().getResource("/Img/icono general.png"));
        setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel para fondo de pantalla
		JPanel pnlFondo = new JPanel();
		pnlFondo.setBackground(Color.GRAY);
		pnlFondo.setBounds(1, 1, 550, 679);
		contentPane.add(pnlFondo);
		pnlFondo.setLayout(null);

		// Imagen de fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(LoginCliente.class.getResource("/Img/ImgFondo.png")));
		lblFondo.setBounds(0, 0, 550, 679);
		pnlFondo.add(lblFondo);

		// Panel contenedor de formulario
		JPanel pnlContenedor = new JPanel();
		pnlContenedor.setBackground(new Color(195, 155, 107));
		pnlContenedor.setBounds(550, 1, 712, 679);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta de iniciar sesión
		JLabel lblIniciarSesion = new JLabel("INICIAR SESIÓN");
		lblIniciarSesion.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblIniciarSesion.setBounds(252, 100, 234, 33);
		pnlContenedor.add(lblIniciarSesion);

		// Etiqueta y campo de texto de usuario
		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblEmail.setBounds(53, 250, 248, 30);
		pnlContenedor.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtEmail.setBorder(null);
		txtEmail.setBounds(300, 250, 300, 30);
		pnlContenedor.add(txtEmail);
		txtEmail.setColumns(10);

		// Etiqueta y campo de texto de contrasenia
		JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblContrasenia.setBounds(53, 300, 248, 27);
		pnlContenedor.add(lblContrasenia);

		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtContrasenia.setBorder(null);
		txtContrasenia.setBounds(300, 300, 300, 30);
		pnlContenedor.add(txtContrasenia);

		// Boton para iniciar sesion
		JButton btnLogin = new JButton("Iniciar sesión");
		btnLogin.setIcon(new ImageIcon(LoginCliente.class.getResource("/Img/icono de inicio de sesion.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampos()) {
						return;
					} else if (controlador.iniciarSesion(txtEmail.getText(),
							String.valueOf(txtContrasenia.getPassword()))) {
						JOptionPane.showMessageDialog(LoginCliente.this, "Bienvenido", "Exito",
								JOptionPane.INFORMATION_MESSAGE);
						InicioCliente inicio = new InicioCliente();
						inicio.setVisible(true);
						LoginCliente.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(LoginCliente.this, "Error al iniciar sesion, verifique sus datos",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(LoginCliente.this, "Error inesperado: " + e2.getMessage(), "Error",
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
		btnLogin.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setBorder(null);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(276, 383, 160, 30);
		pnlContenedor.add(btnLogin);

		// Etiqueta de registro
		JLabel lblRecuperarClave = new JLabel("¿Aun no tenes cuenta?");
		lblRecuperarClave.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblRecuperarClave.setBounds(277, 450, 158, 30);
		pnlContenedor.add(lblRecuperarClave);

		// Boton para registrarse
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.setIcon(new ImageIcon(LoginCliente.class.getResource("/Img/icono de registrarse.png")));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro registro = new Registro();
				registro.setVisible(true);
				LoginCliente.this.setVisible(false);
			}
		});
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(255, 0, 0));
				btnRegistro.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(Color.WHITE);
				btnRegistro.setForeground(Color.BLACK);
			}
		});
		btnRegistro.setForeground(Color.BLACK);
		btnRegistro.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistro.setBorder(null);
		btnRegistro.setBackground(Color.WHITE);
		btnRegistro.setAlignmentX(0.5f);
		btnRegistro.setBounds(276, 480, 160, 30);
		pnlContenedor.add(btnRegistro);

		// Etiqueta de olvidaste contrasenia
		JLabel lblNewLabel = new JLabel("¿Olvidaste tu contraseña?");
		lblNewLabel.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblNewLabel.setBounds(189, 643, 181, 30);
		pnlContenedor.add(lblNewLabel);

		// Boton para recuperar clave
		JButton btnRecuperarClave = new JButton("Recuperar");
		btnRecuperarClave.setIcon(new ImageIcon(LoginCliente.class.getResource("/Img/icono de recuperar cuenta.png")));
		btnRecuperarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecuperarCuenta recuperar = new RecuperarCuenta();
				recuperar.setVisible(true);
				LoginCliente.this.setVisible(false);
			}
		});
		btnRecuperarClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRecuperarClave.setBackground(new Color(255, 0, 0));
				btnRecuperarClave.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRecuperarClave.setBackground(Color.WHITE);
				btnRecuperarClave.setForeground(Color.BLACK);
			}
		});
		btnRecuperarClave.setForeground(Color.BLACK);
		btnRecuperarClave.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnRecuperarClave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperarClave.setBorder(null);
		btnRecuperarClave.setBackground(Color.WHITE);
		btnRecuperarClave.setAlignmentX(0.5f);
		btnRecuperarClave.setBounds(380, 643, 160, 30);
		pnlContenedor.add(btnRecuperarClave);
	}

	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String email = txtEmail.getText();
		String contrasenia = String.valueOf(txtContrasenia.getPassword());

		if (email.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
}
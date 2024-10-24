package Vista.Cliente;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controlador.ClienteControlador;
import Modelo.Cliente.Cliente;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDomicilio;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JPasswordField txtContrasenia;
	private JPasswordField txtRepetirContrasenia;
	ClienteControlador controlador = new ClienteControlador();

	public Registro() {
		// Configuración de la ventana principal
		setTitle("Registro");
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
		pnlContenedor.setBounds(551, 1, 712, 679);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta de iniciar sesión
		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblRegistro.setBounds(287, 25, 138, 33);
		pnlContenedor.add(lblRegistro);

		// Etiqueta y campo de texto de nombre
		JLabel lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblNombre.setAlignmentX(1.0f);
		lblNombre.setBounds(53, 120, 248, 27);
		pnlContenedor.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBounds(300, 120, 300, 30);
		pnlContenedor.add(txtNombre);

		// Etiqueta y campo de texto de apellido
		JLabel lblApellido = new JLabel("APELLIDO: ");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblApellido.setAlignmentX(1.0f);
		lblApellido.setBounds(53, 180, 248, 27);
		pnlContenedor.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtApellido.setColumns(10);
		txtApellido.setBorder(null);
		txtApellido.setBounds(300, 180, 300, 30);
		pnlContenedor.add(txtApellido);

		// Etiqueta y campo de texto de domicilio
		JLabel lblDomicilio = new JLabel("DOMICILIO: ");
		lblDomicilio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomicilio.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblDomicilio.setAlignmentX(1.0f);
		lblDomicilio.setBounds(53, 240, 248, 27);
		pnlContenedor.add(lblDomicilio);

		txtDomicilio = new JTextField();
		txtDomicilio.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtDomicilio.setColumns(10);
		txtDomicilio.setBorder(null);
		txtDomicilio.setBounds(300, 240, 300, 30);
		pnlContenedor.add(txtDomicilio);

		// Etiqueta y campo de texto de telefono
		JLabel lblTelefono = new JLabel("TELEFONO: ");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblTelefono.setAlignmentX(1.0f);
		lblTelefono.setBounds(53, 300, 248, 27);
		pnlContenedor.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(null);
		txtTelefono.setBounds(300, 300, 300, 30);
		pnlContenedor.add(txtTelefono);

		// Etiqueta y campo de texto de email
		JLabel lblEmail = new JLabel("EMAIL: ");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblEmail.setAlignmentX(1.0f);
		lblEmail.setBounds(53, 360, 248, 27);
		pnlContenedor.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		txtEmail.setBounds(300, 360, 300, 30);
		pnlContenedor.add(txtEmail);

		// Etiqueta y campo de texto de contrasenia
		JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblContrasenia.setBounds(53, 426, 248, 27);
		pnlContenedor.add(lblContrasenia);

		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtContrasenia.setBorder(null);
		txtContrasenia.setBounds(300, 425, 300, 30);
		pnlContenedor.add(txtContrasenia);

		// Etiqueta y campo de texto de repetir contrasenia
		JLabel lblRepetirContrasea = new JLabel("REPETIR CONTRASEÑA:");
		lblRepetirContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirContrasea.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblRepetirContrasea.setAlignmentX(0.5f);
		lblRepetirContrasea.setBounds(53, 492, 248, 27);
		pnlContenedor.add(lblRepetirContrasea);

		txtRepetirContrasenia = new JPasswordField();
		txtRepetirContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtRepetirContrasenia.setBorder(null);
		txtRepetirContrasenia.setBounds(300, 491, 300, 30);
		pnlContenedor.add(txtRepetirContrasenia);

		// Boton para crear cuenta
		JButton btnRegistro = new JButton("Crear cuenta");
		btnRegistro.setIcon(new ImageIcon(Registro.class.getResource("/Img/icono de crear cuenta.png")));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampos()) {
						return;
					} else {
						String email = txtEmail.getText();

						if (controlador.verificarEmailExistente(email)) {
							JOptionPane.showMessageDialog(Registro.this, "El email ya está en uso.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (!esCorreoValido(email)) {
							JOptionPane.showMessageDialog(null, "Correo no ingresado o Correo Invalido.");
							return;
						}
						Cliente cliente = new Cliente();
						cliente.setNombre(txtNombre.getText());
						cliente.setApellido(txtApellido.getText());
						cliente.setDomicilio(txtDomicilio.getText());
						cliente.setTelefono(txtTelefono.getText());
						cliente.setEmail(email);
						cliente.setContrasenia(String.valueOf(txtContrasenia.getPassword()));

						if (controlador.crearCuenta(cliente)) {
							JOptionPane.showMessageDialog(Registro.this, "Registro exitoso!", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							System.out.println("Registro exitoso!");
							LoginCliente login = new LoginCliente();
							login.setVisible(true);
							Registro.this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(Registro.this, "Error al registrar cliente.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(Registro.this, "Error inesperado: " + e2.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
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
		btnRegistro.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistro.setBackground(Color.WHITE);
		btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRegistro.setBorder(null);
		btnRegistro.setForeground(Color.BLACK);
		btnRegistro.setBounds(276, 600, 160, 30);
		pnlContenedor.add(btnRegistro);

	}

	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String domicilio = txtDomicilio.getText();
		String telefono = txtTelefono.getText();
		String mail = txtEmail.getText();
		String contrasenia = String.valueOf(txtContrasenia.getPassword());
		String repetirContrasenia = String.valueOf(txtRepetirContrasenia.getPassword());

		if (nombre.isEmpty() || apellido.isEmpty() || domicilio.isEmpty() || telefono.isEmpty() || mail.isEmpty()
				|| contrasenia.isEmpty() || repetirContrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.ERROR_MESSAGE);
			return true;
		} else if (!contrasenia.equals(repetirContrasenia)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		} else if (contrasenia.length() < 8) {
			JOptionPane.showMessageDialog(this, "Las contraseñas debe contener minimo 8 caracteres", "Error",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	// Metodo para verificar si esta bien el email
	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}
}
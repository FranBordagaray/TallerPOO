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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controlador.EmpleadoControlador;
import Modelo.Empleado.Empleado;
import Modelo.Empleado.EnumRoles;


/**
 * Clase que representa una ventana para registrar un nuevo empleado en el sistema.
 * Proporciona campos para ingresar información personal y de contacto del empleado.
 */
public class RegistroEmpleado extends JFrame {

    private static final long serialVersionUID = 1L; // Identificador único de la clase
    private JPanel contentPane; // Panel principal de la ventana
    private JTextField txtDomicilio; // Campo de texto para la dirección del empleado
    private JTextField txtTelefono; // Campo de texto para el número de teléfono del empleado
    private JTextField txtEmail; // Campo de texto para el correo electrónico del empleado
    private JTextField txtNombre; // Campo de texto para el nombre del empleado
    private JTextField txtApellido; // Campo de texto para el apellido del empleado
    private JTextField txtUsuario; // Campo de texto para el nombre de usuario del empleado
    private JPasswordField txtContrasenia; // Campo de texto para la contraseña del empleado
    private JPasswordField txtRepetirContrasenia; // Campo de texto para repetir la contraseña
    private EmpleadoControlador controlador = new EmpleadoControlador(); // Controlador para la gestión de empleados

    /**
     * Constructor de la clase RegistroEmpleado.
     * Inicializa la ventana con campos para ingresar los datos del nuevo empleado.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public RegistroEmpleado() {
		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 720);
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
		pnlContenedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlContenedor.setBackground(new Color(222, 184, 135));
		pnlContenedor.setBounds(0, 0, 712, 720);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta de iniciar sesión
		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblRegistro.setBounds(287, 10, 138, 33);
		pnlContenedor.add(lblRegistro);

		// Etiqueta y combobox de roles
		JLabel lblRol = new JLabel("ROL:");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblRol.setAlignmentX(1.0f);
		lblRol.setBounds(53, 60, 248, 27);
		pnlContenedor.add(lblRol);

		JComboBox<EnumRoles> roles = new JComboBox<>(EnumRoles.values());
		roles.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		roles.setBorder(null);
		roles.setBackground(Color.WHITE);
		roles.setBounds(300, 60, 137, 30);
		pnlContenedor.add(roles);

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

		// Etiqueta y campo de texto de usuario
		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblUsuario.setBounds(53, 420, 248, 27);
		pnlContenedor.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(300, 420, 300, 30);
		pnlContenedor.add(txtUsuario);

		// Etiqueta y campo de texto de contrasenia
		JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblContrasenia.setBounds(53, 480, 248, 27);
		pnlContenedor.add(lblContrasenia);

		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtContrasenia.setBorder(null);
		txtContrasenia.setBounds(300, 480, 300, 30);
		pnlContenedor.add(txtContrasenia);

		// Etiqueta y campo de texto de repetir contrasenia
		JLabel lblRepetirContrasea = new JLabel("REPETIR CONTRASEÑA:");
		lblRepetirContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirContrasea.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblRepetirContrasea.setAlignmentX(0.5f);
		lblRepetirContrasea.setBounds(53, 540, 248, 27);
		pnlContenedor.add(lblRepetirContrasea);

		txtRepetirContrasenia = new JPasswordField();
		txtRepetirContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtRepetirContrasenia.setBorder(null);
		txtRepetirContrasenia.setBounds(300, 540, 300, 30);
		pnlContenedor.add(txtRepetirContrasenia);
		
		// Combobox estado del empleado
		JLabel lblEstado = new JLabel("ESTADO:");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblEstado.setAlignmentX(0.5f);
		lblEstado.setBounds(53, 600, 248, 27);
		pnlContenedor.add(lblEstado);
		
		JComboBox estado = new JComboBox();
		estado.setModel(new DefaultComboBoxModel(new String[] {"ALTA", "BAJA"}));
		estado.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		estado.setBorder(null);
		estado.setBackground(Color.WHITE);
		estado.setBounds(300, 600, 137, 30);
		pnlContenedor.add(estado);

		// Botón para crear cuenta
		JButton btnRegistro = new JButton("Crear cuenta");
		btnRegistro.setIcon(new ImageIcon(RegistroEmpleado.class.getResource("/Img/icono de crear cuenta.png")));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 /**
		         * Maneja el evento de acción para crear una cuenta de empleado.
		         *
		         * Este método verifica los campos de entrada, comprueba si el correo electrónico
		         * y el nombre de usuario ya están en uso, y valida la dirección de correo electrónico.
		         * Si todos los datos son válidos, se intenta crear un nuevo empleado y se maneja 
		         * el resultado del registro, mostrando mensajes apropiados al usuario.
		         *
		         * @param e El evento de acción que activa este método.
		         */
				try {
					if (verificarCampos()) {
						return;
					} else {

						String email = txtEmail.getText();
						String usuario = txtUsuario.getText();

						// Verificar si el email ya está en uso
						if (controlador.emailEnUso(email)) {
							JOptionPane.showMessageDialog(RegistroEmpleado.this,
									"El email ya está en uso.Por favor utilice  otro.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (controlador.usuarioEnUso(usuario)) {
							JOptionPane.showMessageDialog(RegistroEmpleado.this,
									"El Usuario ya está en uso.Por favor utilice otro.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;

						}

						if (!esCorreoValido(email)) {
							JOptionPane.showMessageDialog(null, "Correo no ingresado o Correo Invalido.");
							return;
						}

						// Crear el empleado
						Empleado empleado = new Empleado();
						empleado.setRol((EnumRoles) roles.getSelectedItem());
						empleado.setNombre(txtNombre.getText());
						empleado.setApellido(txtApellido.getText());
						empleado.setDomicilio(txtDomicilio.getText());
						empleado.setTelefono(txtTelefono.getText());
						empleado.setEmail(email); // Usar el email verificado
						empleado.setUsuario(txtUsuario.getText());
						empleado.setContrasenia(String.valueOf(txtContrasenia.getPassword()));
						empleado.setEstado((String)estado.getSelectedItem());;

						// Intentar registrar al empleado
						if (controlador.crearCuenta(empleado)) {
							JOptionPane.showMessageDialog(RegistroEmpleado.this, "Registro exitoso!", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							System.out.println("Registro exitoso!");
							GestionEmpleados gestion = new GestionEmpleados();
							gestion.setVisible(true);
							RegistroEmpleado.this.dispose();
						} else {
							JOptionPane.showMessageDialog(RegistroEmpleado.this, "Error al registrar empleado.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(RegistroEmpleado.this, "Error inesperado: " + e2.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
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
		btnRegistro.setBounds(276, 650, 160, 30);
		pnlContenedor.add(btnRegistro);
		// Boton cerrar

		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				btnCerrar.setForeground(Color.BLACK);
				btnCerrar.setBackground(new Color(195, 155, 107));
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
		btnCerrar.setBackground(new Color(222, 184, 135));
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.setBorder(null);
		btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnCerrar.setBounds(666, 1, 45, 30);
		pnlContenedor.add(btnCerrar);
	}

    /**
     * Verifica que todos los campos de entrada estén completos y cumplan con las validaciones requeridas.
     *
     * Este método comprueba que los campos de texto no estén vacíos, que las contraseñas ingresadas coincidan 
     * y que la longitud de la contraseña sea de al menos 8 caracteres. Si alguna de estas condiciones no se 
     * cumple, se muestra un mensaje de advertencia o error al usuario y se retorna `true`. Si todos los 
     * campos son válidos, se retorna `false`.
     *
     * @return `true` si hay campos vacíos o errores en las contraseñas; `false` si todos los campos son válidos.
     */
	private boolean verificarCampos() {
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String domicilio = txtDomicilio.getText();
		String telefono = txtTelefono.getText();
		String mail = txtEmail.getText();
		String usuario = txtUsuario.getText();
		String contrasenia = String.valueOf(txtContrasenia.getPassword());
		String repetirContrasenia = String.valueOf(txtRepetirContrasenia.getPassword());

		if (nombre.isEmpty() || apellido.isEmpty() || domicilio.isEmpty() || telefono.isEmpty() || mail.isEmpty()
				|| usuario.isEmpty() || contrasenia.isEmpty() || repetirContrasenia.isEmpty()) {
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

	/**
	 * Verifica si una dirección de correo electrónico es válida.
	 *
	 * Este método utiliza una expresión regular para comprobar si el formato del correo electrónico
	 * cumple con los estándares típicos. Un correo electrónico válido debe contener caracteres alfanuméricos,
	 * un símbolo de '@', y un dominio que incluye al menos un punto (.) seguido de una extensión.
	 *
	 * @param correo La dirección de correo electrónico a verificar.
	 * @return `true` si el correo electrónico tiene un formato válido; `false` en caso contrario.
	 */
	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}

}
package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import Modelo.Empleado;
import Modelo.EnumRoles;

public class RegistroEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDomicilio;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private JPasswordField txtRepetirContrasenia;
	EmpleadoControlador controlador = new EmpleadoControlador();

	public RegistroEmpleado() {
		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 679);
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
		pnlContenedor.setBounds(0, 0, 712, 679);
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

		// Boton para crear cuenta
		JButton btnRegistro = new JButton("Crear cuenta");
		btnRegistro.setIcon(new ImageIcon(RegistroEmpleado.class.getResource("/Img/icono de crear cuenta.png")));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampos()) {
						return;
					} else {
						Empleado empleado = new Empleado();
						empleado.setRol((EnumRoles) roles.getSelectedItem());
						empleado.setNombre(txtNombre.getText());
						empleado.setApellido(txtApellido.getText());
						empleado.setDomicilio(txtDomicilio.getText());
						empleado.setTelefono(txtTelefono.getText());
						empleado.setEmail(txtEmail.getText());
						empleado.setUsuario(txtUsuario.getText());
						empleado.setContrasenia(String.valueOf(txtContrasenia.getPassword()));
						if (controlador.crearCuenta(empleado)) {
							JOptionPane.showMessageDialog(RegistroEmpleado.this, "Registro exitoso!", "Exito", 
									JOptionPane.INFORMATION_MESSAGE);
							System.out.println("Registro exitoso!");
							GestionEmpleados gestion = new GestionEmpleados();
							gestion.setVisible(true);
							RegistroEmpleado.this.dispose();
						} else {
							JOptionPane.showMessageDialog(RegistroEmpleado.this, "Error al registrar cliente.", "Error", 
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(RegistroEmpleado.this, "Error inesperado: " + e2.getMessage(), "Error", 
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
		//Boton cerrar
		
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

	
	// Funcion para verificar campos vacios
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
}
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
import javax.swing.DefaultComboBoxModel;

public class ModificarEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDomicilio;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	EmpleadoControlador controlador;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModificarEmpleado(Empleado empleado) {
		
		controlador = new EmpleadoControlador();
		
		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 660);
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
		pnlContenedor.setBounds(0, 0, 712, 660);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta de iniciar sesión
		JLabel lblModificar = new JLabel("MODIFICAR");
		lblModificar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificar.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblModificar.setBounds(0, 10, 712, 33);
		pnlContenedor.add(lblModificar);

		// Etiqueta y combobox de roles
		JLabel lblRol = new JLabel("ROL:");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblRol.setAlignmentX(1.0f);
		lblRol.setBounds(53, 60, 248, 27);
		pnlContenedor.add(lblRol);

		JComboBox<EnumRoles> roles = new JComboBox<>(EnumRoles.values());
		roles.setSelectedItem(empleado.getRol());
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

		txtNombre = new JTextField(empleado.getNombre());
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

		txtApellido = new JTextField(empleado.getApellido());
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

		txtDomicilio = new JTextField(empleado.getDomicilio());
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

		txtTelefono = new JTextField(empleado.getTelefono());
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

		txtEmail = new JTextField(empleado.getEmail());
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

		txtUsuario = new JTextField(empleado.getUsuario());
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

		txtContrasenia = new JPasswordField("*******");
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		txtContrasenia.setBorder(null);
		txtContrasenia.setBounds(300, 480, 300, 30);
		pnlContenedor.add(txtContrasenia);
		
		// Combobox estado del empleado
		JLabel lblEstado = new JLabel("ESTADO:");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		lblEstado.setAlignmentX(0.5f);
		lblEstado.setBounds(53, 540, 248, 27);
		pnlContenedor.add(lblEstado);
		
		JComboBox estado = new JComboBox();
		estado.setModel(new DefaultComboBoxModel(new String[] {"ALTA", "BAJA"}));
		estado.setSelectedItem(empleado.getEstado());
		estado.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		estado.setBorder(null);
		estado.setBackground(Color.WHITE);
		estado.setBounds(300, 540, 137, 30);
		pnlContenedor.add(estado);

		// Botón para modificar empleado
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setIcon(new ImageIcon(ModificarEmpleado.class.getResource("/Img/icono de crear cuenta.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empleado.setRol((EnumRoles) roles.getSelectedItem());
				empleado.setNombre(txtNombre.getText());
				empleado.setApellido(txtApellido.getText());
				empleado.setDomicilio(txtDomicilio.getText());
				empleado.setTelefono(txtTelefono.getText());
				empleado.setEmail(txtEmail.getText());
				empleado.setUsuario(txtUsuario.getText());
				//empleado.setContrasenia(String.valueOf(txtContrasenia.getPassword()));
				empleado.setEstado((String)estado.getSelectedItem());
				try {
					if (verificarCampos()) {
						return;
					} else {
		            controlador.actualizarEmpleado(empleado);
		            JOptionPane.showMessageDialog(null, "Cambios realizados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            dispose();
		            GestionEmpleados gestion = new GestionEmpleados();
		            gestion.cargarDatos();
					}
		        } catch (Exception e2) {
		            e2.printStackTrace();
		            System.out.println("Error al actualizar: " + e2.getMessage());
		        }
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnModificar.setBackground(new Color(255, 0, 0));
				btnModificar.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnModificar.setBackground(Color.WHITE);
				btnModificar.setForeground(Color.BLACK);
			}
		});
		btnModificar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnModificar.setBorder(null);
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setBounds(271, 600, 160, 30);
		pnlContenedor.add(btnModificar);
		
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

	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String domicilio = txtDomicilio.getText();
		String telefono = txtTelefono.getText();
		String mail = txtEmail.getText();
		String usuario = txtUsuario.getText();
		String contrasenia = String.valueOf(txtContrasenia.getPassword());

		if (nombre.isEmpty() || apellido.isEmpty() || domicilio.isEmpty() || telefono.isEmpty() || mail.isEmpty()
				|| usuario.isEmpty() || contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.ERROR_MESSAGE);
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
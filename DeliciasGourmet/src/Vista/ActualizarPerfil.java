package Vista;

import Modelo.Cliente;
import Modelo.Sesion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.ClienteControlador;
import vista.Sesion;
import javax.swing.JSeparator;

public class ActualizarPerfil extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtContrasenia;
	private JTextField txtDomicilio;
	private JTextField txtEmail;
	private JTextField txtTelefono;

	/**
	 * Create the panel.
	 */
	public ActualizarPerfil() {
		
		Sesion s1 = new Sesion();
		// Configuracion del panel
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));

		// Etiquera de perfil
		JLabel lblRegistro = new JLabel("PERFIL");
		lblRegistro.setFont(new Font("Roboto Light", Font.BOLD, 32));
		lblRegistro.setBounds(427, 10, 138, 33);
		add(lblRegistro);

		// Etiquera y campo de texto de nombre
		JLabel lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblNombre.setAlignmentX(1.0f);
		lblNombre.setBounds(-51, 307, 248, 27);
		add(lblNombre);
		txtNombre = new JTextField(s1.getClienteActual().getNombre());
		txtNombre.setEditable(false);
		txtNombre.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtNombre.setColumns(10);
		txtNombre.setBounds(141, 301, 203, 39);
		add(txtNombre);

		// Etiquera y campo de texto de apellido
		JLabel lblApellido = new JLabel("APELLIDO: ");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblApellido.setAlignmentX(1.0f);
		lblApellido.setBounds(-51, 390, 248, 27);
		add(lblApellido);
		txtApellido = new JTextField(s1.getClienteActual().getApellido());
		txtApellido.setEditable(false);
		txtApellido.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtApellido.setColumns(10);
		txtApellido.setBounds(141, 384, 203, 39);
		add(txtApellido);

		// Etiquera y campo de texto de domicilio
		JLabel lblDomicilio = new JLabel("DOMICILIO: ");
		lblDomicilio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomicilio.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblDomicilio.setAlignmentX(1.0f);
		lblDomicilio.setBounds(493, 390, 248, 27);
		add(lblDomicilio);

		// Etiquera y campo de texto de telefono
		JLabel lblTelefono = new JLabel("TELEFONO: ");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblTelefono.setAlignmentX(1.0f);
		lblTelefono.setBounds(509, 504, 248, 27);
		add(lblTelefono);

		// Etiquera y campo de texto de email
		JLabel lblEmail = new JLabel("EMAIL: ");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblEmail.setAlignmentX(1.0f);
		lblEmail.setBounds(-51, 504, 248, 27);
		add(lblEmail);

		// Etiquera y campo de texto de usuario
		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblUsuario.setAlignmentX(1.0f);
		lblUsuario.setBounds(203, 223, 248, 27);
		add(lblUsuario);

		// Etiquera y campo de texto de contrasenia
		JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblContrasenia.setAlignmentX(0.5f);
		lblContrasenia.setBounds(509, 307, 248, 27);
		add(lblContrasenia);

		
		
		// Label de Nombre 
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/Img/ImgPerfil (1).jpg")));
		lblNewLabel.setBounds(405, 38, 160, 176);
		add(lblNewLabel);
				
		txtUsuario = new JTextField(s1.getClienteActual().getUsuario());
		txtUsuario.setEditable(false);
		txtUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtUsuario.setBounds(394, 217, 203, 39);
		add(txtUsuario);
		txtUsuario.setColumns(10);
				
		
				
		
				
		txtContrasenia = new JTextField("**********");
		txtContrasenia.setEditable(false);
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(731, 301, 203, 39);
		add(txtContrasenia);
				
		txtDomicilio = new JTextField(s1.getClienteActual().getDomicilio());
		txtDomicilio.setEditable(false);
		txtDomicilio.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(731, 384, 203, 39);
		add(txtDomicilio);
				
		txtEmail = new JTextField(s1.getClienteActual().getEmail());
		txtEmail.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtEmail.setColumns(10);
		txtEmail.setBounds(141, 498, 203, 39);
		add(txtEmail);
		
		// 
		txtTelefono = new JTextField(s1.getClienteActual().getTelefono());
		txtTelefono.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(731, 498, 203, 39);
		add(txtTelefono);
			
		// Separador 
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 453, 972, 18);
		add(separator);
				
		// Boton para actualizar informacion de contacto
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/Img/ImgGuardar.jpg")));
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
		public void mouseEntered(MouseEvent e) {
			btnGuardar.setBackground(new Color(255, 0, 0));
			btnGuardar.setForeground(Color.WHITE);
					}

			@Override
		public void mouseExited(MouseEvent e) {
			btnGuardar.setBackground(Color.WHITE);
			btnGuardar.setForeground(Color.BLACK);
				   }

				});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el cliente actual de la sesión
		        Cliente clienteActual = s1.getClienteActual();

		        // Actualizar los datos del cliente con los valores del formulario
		        clienteActual.setEmail(txtEmail.getText());
		        clienteActual.setTelefono(txtTelefono.getText());

		        // Crear una instancia del controlador
		        ClienteControlador controlador = new ClienteControlador();

		        // Llamar al método actualizarCliente para guardar los cambios en la base de datos
		        try {
		            controlador.actualizarCliente(clienteActual);
		            JOptionPane.showMessageDialog(null, "Cambios realizados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            System.out.println("Error al actualizar los datos del cliente.");
		        }

					}
				});
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setAlignmentX(0.5f);
		btnGuardar.setBounds(394, 601, 160, 30);
		add(btnGuardar);
	}
}
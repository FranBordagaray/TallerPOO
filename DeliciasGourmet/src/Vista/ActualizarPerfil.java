package Vista;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class ActualizarPerfil extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDomicilio;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;

	/**
	 * Create the panel.
	 */
	public ActualizarPerfil() {
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));
		
		JLabel lblRegistro = new JLabel("PERFIL");
		lblRegistro.setFont(new Font("Dialog", Font.BOLD, 28));
		lblRegistro.setBounds(427, 23, 138, 33);
		add(lblRegistro);
		
		JLabel lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNombre.setAlignmentX(1.0f);
		lblNombre.setBounds(181, 90, 248, 27);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		txtNombre.setBounds(428, 90, 300, 30);
		add(txtNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO: ");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblApellido.setAlignmentX(1.0f);
		lblApellido.setBounds(181, 150, 248, 27);
		add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtApellido.setColumns(10);
		txtApellido.setBorder(null);
		txtApellido.setBounds(428, 150, 300, 30);
		add(txtApellido);
		
		JLabel lblDomicilio = new JLabel("DOMICILIO: ");
		lblDomicilio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomicilio.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblDomicilio.setAlignmentX(1.0f);
		lblDomicilio.setBounds(181, 210, 248, 27);
		add(lblDomicilio);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setEditable(false);
		txtDomicilio.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtDomicilio.setColumns(10);
		txtDomicilio.setBorder(null);
		txtDomicilio.setBounds(428, 210, 300, 30);
		add(txtDomicilio);
		
		JLabel lblTelefono = new JLabel("TELEFONO: ");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTelefono.setAlignmentX(1.0f);
		lblTelefono.setBounds(181, 270, 248, 27);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(null);
		txtTelefono.setBounds(428, 270, 300, 30);
		add(txtTelefono);
		
		JLabel lblEmail = new JLabel("EMAIL: ");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblEmail.setAlignmentX(1.0f);
		lblEmail.setBounds(181, 330, 248, 27);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		txtEmail.setBounds(428, 330, 300, 30);
		add(txtEmail);
		
		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblUsuario.setAlignmentX(1.0f);
		lblUsuario.setBounds(181, 390, 248, 27);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(428, 390, 300, 30);
		add(txtUsuario);
		
		JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblContrasenia.setAlignmentX(0.5f);
		lblContrasenia.setBounds(181, 450, 248, 27);
		add(lblContrasenia);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setEditable(false);
		txtContrasenia.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtContrasenia.setBorder(null);
		txtContrasenia.setBounds(428, 450, 300, 30);
		add(txtContrasenia);
		
		JButton btnRegistro = new JButton("Guardar");
		btnRegistro.setForeground(Color.BLACK);
		btnRegistro.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnRegistro.setBorder(null);
		btnRegistro.setBackground(Color.WHITE);
		btnRegistro.setAlignmentX(0.5f);
		btnRegistro.setBounds(416, 563, 160, 30);
		add(btnRegistro);

	}
}

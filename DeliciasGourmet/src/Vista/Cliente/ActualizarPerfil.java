package Vista.Cliente;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.awt.Image;

import Controlador.ClienteControlador;
import Modelo.Cliente;
import Modelo.Sesion;


public class ActualizarPerfil extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtContrasenia;
	private JTextField txtDomicilio;
	private JTextField txtEmail;
	private JTextField txtTelefono;

	@SuppressWarnings("static-access")
	public ActualizarPerfil() {
		
		Sesion s1 = new Sesion();
		
		// Configuracion del panel
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));

		// Etiqueta de perfil
		JLabel lblRegistro = new JLabel("PERFIL");
		lblRegistro.setFont(new Font("Roboto Light", Font.BOLD, 32));
		lblRegistro.setBounds(427, 10, 138, 33);
		add(lblRegistro);

		// Etiqueta y campo de texto de nombre
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
		txtNombre.setBounds(141, 301, 298, 39);
		add(txtNombre);

		// Etiqueta y campo de texto de apellido
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
		txtApellido.setBounds(141, 384, 298, 39);
		add(txtApellido);

		// Etiqueta y campo de texto de domicilio
		JLabel lblDomicilio = new JLabel("DOMICILIO: ");
		lblDomicilio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomicilio.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblDomicilio.setAlignmentX(1.0f);
		lblDomicilio.setBounds(462, 390, 248, 27);
		add(lblDomicilio);
		txtDomicilio = new JTextField(s1.getClienteActual().getDomicilio());
		txtDomicilio.setEditable(false);
		txtDomicilio.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(687, 384, 295, 39);
		add(txtDomicilio);

		// Etiqueta y campo de texto de telefono
		JLabel lblTelefono = new JLabel("TELÉFONO: ");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblTelefono.setAlignmentX(1.0f);
		lblTelefono.setBounds(462, 504, 248, 27);
		add(lblTelefono);
		txtTelefono = new JTextField(s1.getClienteActual().getTelefono());
		txtTelefono.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(687, 498, 295, 39);
		add(txtTelefono);
			

		// Etiqueta y campo de texto de email
		JLabel lblEmail = new JLabel("EMAIL: ");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblEmail.setAlignmentX(1.0f);
		lblEmail.setBounds(-51, 504, 248, 27);
		add(lblEmail);
		txtEmail = new JTextField(s1.getClienteActual().getEmail());
		txtEmail.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtEmail.setColumns(10);
		txtEmail.setBounds(141, 498, 298, 39);
		add(txtEmail);

		// Etiqueta y campo de texto de contrasenia
		JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
		lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasenia.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblContrasenia.setAlignmentX(0.5f);
		lblContrasenia.setBounds(446, 307, 248, 27);
		add(lblContrasenia);
		txtContrasenia = new JTextField("**********");
		txtContrasenia.setEditable(false);
		txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(687, 301, 295, 39);
		add(txtContrasenia);
		
		// Imagen del perfil
		JLabel lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/Img/ImgPerfil (1).jpg")));
		lblFotoPerfil.setBounds(405, 38, 160, 176);
		add(lblFotoPerfil);

		// Botón para subir la foto
		JButton btnCargarFoto = new JButton("Cargar foto");
		btnCargarFoto.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser selectorArchivos = new JFileChooser();
		        FileNameExtensionFilter filtroImagenes = new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg");
		        selectorArchivos.setFileFilter(filtroImagenes);
		        
		        int seleccion = selectorArchivos.showOpenDialog(null);
		        if (seleccion == JFileChooser.APPROVE_OPTION) {
		            File archivoSeleccionado = selectorArchivos.getSelectedFile();
		            try {
		                Image imagen = ImageIO.read(archivoSeleccionado);
		                Image imagenRedimensionada = imagen.getScaledInstance(lblFotoPerfil.getWidth(), lblFotoPerfil.getHeight(), Image.SCALE_SMOOTH);
		                lblFotoPerfil.setIcon(new ImageIcon(imagenRedimensionada));
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Error al cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		btnCargarFoto.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
				btnCargarFoto.setBackground(new Color(255, 0, 0));
				btnCargarFoto.setForeground(Color.WHITE);
			}
		@Override
		public void mouseExited(MouseEvent e) {
				btnCargarFoto.setBackground(Color.WHITE);
				btnCargarFoto.setForeground(Color.BLACK);
			}
		});
		btnCargarFoto.setBounds(405, 220, 160, 30);
		btnCargarFoto.setForeground(Color.BLACK);
		btnCargarFoto.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnCargarFoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCargarFoto.setBorder(null);
		btnCargarFoto.setBackground(Color.WHITE);
		add(btnCargarFoto);
				
		// Separador 
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 453, 972, 18);
		add(separator);
				
		// Boton para actualizar informacion de contacto
		JButton btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/Img/icono de guardar.png")));
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
		        Cliente clienteActual = s1.getClienteActual();
		        clienteActual.setEmail(txtEmail.getText());
		        clienteActual.setTelefono(txtTelefono.getText());
		        ClienteControlador controlador = new ClienteControlador();
		        try {
		            controlador.actualizarCliente(clienteActual);
		            JOptionPane.showMessageDialog(null, "Cambios realizados con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        } catch (Exception e2) {
		            e2.printStackTrace();
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
		btnGuardar.setBounds(394, 601, 171, 30);
		add(btnGuardar);
	}
}
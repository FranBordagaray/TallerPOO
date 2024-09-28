package Vista.Cliente;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Controlador.ClienteControlador;
import Modelo.RecuperarClave;

public class RecuperarCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ClienteControlador controlador;
	private JTextField txtEmail;
	private String email;

	public RecuperarCuenta() {
		controlador = new ClienteControlador();
		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblRecuperar = new JLabel("RECUPERAR CONTRASEÑA");
		lblRecuperar.setFont(new Font("Roboto Light", Font.BOLD, 18));
		lblRecuperar.setBounds(185, 43, 229, 22);
		contentPane.add(lblRecuperar);

		// Texto de instrucciones para recuperar la contraseña
		JTextPane intrucciones = new JTextPane();
		intrucciones.setForeground(Color.BLACK);
		intrucciones.setBackground(new Color(195, 155, 107));
		intrucciones.setEditable(false);
		intrucciones.setFont(new Font("Roboto Light", Font.PLAIN, 15));
		intrucciones.setText("Para recuperar su contraseña, ingrese el mail con el que se registro en el siguiente campo");
		intrucciones.setBounds(146, 76, 307, 49);
		contentPane.add(intrucciones);

		// Etiqueta y campo de correo electrónico
		JLabel lblEmail = new JLabel("CORREO");
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 19));
		lblEmail.setBounds(255, 150, 90, 30);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		txtEmail.setBorder(null);
		txtEmail.setBounds(125, 190, 350, 30);
		contentPane.add(txtEmail);

		// Boton para enviar correo de recuperación
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.setIcon(new ImageIcon(RecuperarCuenta.class.getResource("/Img/icono enviar.png")));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampos()) {
						return;
					} else {
						email = txtEmail.getText();
						if (controlador.verificarEmailExistente(email)) {
							String destinatario = email;
							String asunto = "Codigo de recuperación";
						    String cuerpo = "Ingrese en la ventana emergente el siguiente codigo: " + controlador.obtenerCodigoRecuperacion(email);
						    RecuperarClave.enviarCorreo(destinatario, asunto, cuerpo);
							JOptionPane.showMessageDialog(RecuperarCuenta.this, "Por favor, revise su correo electronico", "Recuperar", JOptionPane.INFORMATION_MESSAGE);
							IngresoCodVerificacion verificar = new IngresoCodVerificacion(email);
							verificar.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(RecuperarCuenta.this, "No existe cliente con ese mail", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(RecuperarCuenta.this, "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
		btnEnviar.setBounds(220, 277, 160, 30);
		contentPane.add(btnEnviar);

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginCliente login = new LoginCliente();
				login.setVisible(true);
				RecuperarCuenta.this.setVisible(false);
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
	}
	
	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String mail = txtEmail.getText();
		if (mail.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese un mail para recuperar su contraseña", "Advertencia", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
}
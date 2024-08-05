package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class RecuperarCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public RecuperarCuenta() {
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
		contentPane.setBackground(Color.LIGHT_GRAY);
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
		intrucciones.setBackground(Color.LIGHT_GRAY);
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

		JTextField txtEmail = new JTextField();
		txtEmail.setBorder(null);
		txtEmail.setBounds(125, 190, 350, 30);
		contentPane.add(txtEmail);
		
		// Boton para enviar correo de recuperación
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// envia el correo con la clave del usuario
				JOptionPane.showMessageDialog(RecuperarCuenta.this, "Revise su correo electronico", "Recuperar", JOptionPane.INFORMATION_MESSAGE);
				Login login = new Login();
				login.setVisible(true);
				RecuperarCuenta.this.setVisible(false);
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
	}
}
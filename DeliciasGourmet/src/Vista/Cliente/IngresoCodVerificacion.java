package Vista.Cliente;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ClienteControlador;
import Modelo.Cliente.RecuperarClave;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@SuppressWarnings("unused")
public class IngresoCodVerificacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodVerificacion;
	private ClienteControlador controlador;
	private String email;

	public static void main(String[] args) {
		try {
			IngresoCodVerificacion dialog = new IngresoCodVerificacion(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IngresoCodVerificacion(String email) {
		controlador = new ClienteControlador();
		this.email = email;
		// Configuracion del dialog
		setResizable(false);
		setUndecorated(true);
		setBounds(1, 1, 600, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(195, 155, 107));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// Intrucciones de pantalla
		JLabel lblIntrucciones = new JLabel("Por favor, ingrese el código que recibió en su bandeja de correos");
		lblIntrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntrucciones.setFont(new Font("Roboto Light", Font.BOLD, 19));
		lblIntrucciones.setBounds(0, 54, 600, 30);
		contentPanel.add(lblIntrucciones);
		
		// Etiqueta y campo de texto de codigo de verificacion
		JLabel lblCodVerificacion = new JLabel("CODIGO DE VERIFICACION");
		lblCodVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodVerificacion.setForeground(Color.BLACK);
		lblCodVerificacion.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		lblCodVerificacion.setBounds(100, 140, 400, 30);
		contentPanel.add(lblCodVerificacion);
		
		txtCodVerificacion = new JTextField();
		txtCodVerificacion.setBorder(null);
		txtCodVerificacion.setBackground(Color.WHITE);
		txtCodVerificacion.setForeground(Color.BLACK);
		txtCodVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodVerificacion.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		txtCodVerificacion.setBounds(100, 190, 400, 30);
		contentPanel.add(txtCodVerificacion);
		txtCodVerificacion.setColumns(10);
		
		// Boton para verificar el codigo ingresado
		JButton btnVerificar = new JButton("VERIFICAR");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (verificarCampos()) {
						return;
					} else {
						String codigoIngresado = txtCodVerificacion.getText();
		                String codigoRecuperacion = controlador.obtenerCodigoRecuperacion(email);

		                if (codigoRecuperacion.equals(codigoIngresado)) {
		                    ReestablecerContrasenia reestablecer = new ReestablecerContrasenia(email);
		                    reestablecer.setVisible(true);
		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(IngresoCodVerificacion.this, "El código ingresado no es correcto!", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(IngresoCodVerificacion.this, "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnVerificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVerificar.setBackground(new Color(255, 0, 0));
				btnVerificar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnVerificar.setBackground(Color.WHITE);
				btnVerificar.setForeground(Color.BLACK);
			}
		});
		btnVerificar.setBorder(null);
		btnVerificar.setForeground(Color.BLACK);
		btnVerificar.setBackground(Color.WHITE);
		btnVerificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerificar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnVerificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVerificar.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		btnVerificar.setBounds(175, 285, 250, 30);
		contentPanel.add(btnVerificar);
	}
	
	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String codigo = txtCodVerificacion.getText();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese el código que recibió en su bandeja de correos", "Advertencia", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
}
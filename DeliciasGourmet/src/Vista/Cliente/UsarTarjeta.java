package Vista.Cliente;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;

import Controlador.TarjetaControlador;
import Modelo.Cliente.Tarjeta;

public class UsarTarjeta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTitular;
	private JTextField txtEmisor;
	private JTextField txtNroTarjeta;
	private JTextField txtCodVerificacion;
	TarjetaControlador controlador = new TarjetaControlador();
	Tarjeta tarjeta = new Tarjeta();



	public UsarTarjeta(VistaReserva vistareserva) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBackground(new Color(222, 184, 135));
		getContentPane().setLayout(null);

		// Panel contenedor
		JPanel panelContenedor = new JPanel();
		panelContenedor.setBackground(new Color(222, 184, 135));
		panelContenedor.setBounds(0, 0, 450, 350);
		getContentPane().add(panelContenedor);
		panelContenedor.setLayout(null);

		// Etiqueta para mostrar leyenda en pantalla
		JLabel lblLeyenda = new JLabel("Por favor, ingrese una tarjeta válida para confirmar su reserva");
		lblLeyenda.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLeyenda.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLeyenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeyenda.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		lblLeyenda.setBounds(25, 40, 400, 40);
		panelContenedor.add(lblLeyenda);

		// Texto Titular
		txtTitular = new JTextField();
		txtTitular.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		txtTitular.setColumns(10);
		txtTitular.setBackground(new Color(255, 255, 255));
		txtTitular.setBounds(169, 110, 213, 25);
		txtTitular.setBorder(null);
		panelContenedor.add(txtTitular);

		// Texto Emisor
		txtEmisor = new JTextField();
		txtEmisor.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		txtEmisor.setColumns(10);
		txtEmisor.setBackground(new Color(255, 255, 255));
		txtEmisor.setBounds(169, 150, 213, 25);
		txtEmisor.setBorder(null);
		panelContenedor.add(txtEmisor);

		// Texto Tarjeta
		txtNroTarjeta = new JTextField();
		txtNroTarjeta.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		txtNroTarjeta.setColumns(10);
		txtNroTarjeta.setBackground(new Color(255, 255, 255));
		txtNroTarjeta.setBounds(169, 190, 213, 25);
		txtNroTarjeta.setBorder(null);
		panelContenedor.add(txtNroTarjeta);

		// Texto Codigo verificacion
		txtCodVerificacion = new JTextField();
		txtCodVerificacion.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		txtCodVerificacion.setColumns(10);
		txtCodVerificacion.setBackground(new Color(255, 255, 255));
		txtCodVerificacion.setBounds(169, 230, 100, 25);
		txtCodVerificacion.setBorder(null);
		panelContenedor.add(txtCodVerificacion);

		// Etiqueta Tarjeta
		JLabel lblTarjeta = new JLabel("INGRESAR TARJETA");
		lblTarjeta.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblTarjeta.setBounds(100, 10, 250, 25);
		panelContenedor.add(lblTarjeta);

		// Etiqueta Titular
		JLabel lblTitular = new JLabel("TITULAR");
		lblTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitular.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitular.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitular.setFont(new Font("Roboto Light", Font.BOLD, 12));
		lblTitular.setBounds(45, 110, 120, 25);
		panelContenedor.add(lblTitular);

		// Etiqueta Emisor
		JLabel lblEmisor = new JLabel("EMISOR");
		lblEmisor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmisor.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmisor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEmisor.setFont(new Font("Roboto Light", Font.BOLD, 12));
		lblEmisor.setBounds(45, 150, 120, 25);
		panelContenedor.add(lblEmisor);
		
		// Etiqueta Numero Tarjeta
		JLabel lblNroTarjeta = new JLabel("NRO TARJETA");
		lblNroTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNroTarjeta.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNroTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNroTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 12));
		lblNroTarjeta.setBounds(45, 190, 120, 25);
		panelContenedor.add(lblNroTarjeta);
		
		// Etiqueta Codigo Verificacion
		JLabel lblCodVerificacion = new JLabel("COD VERIFICACION");
		lblCodVerificacion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCodVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodVerificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCodVerificacion.setFont(new Font("Roboto Light", Font.BOLD, 12));
		lblCodVerificacion.setBounds(45, 230, 120, 25);
		panelContenedor.add(lblCodVerificacion);
		
		// Boton Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirmar.setBackground(new Color(126, 211, 33));
				btnConfirmar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmar.setBackground(Color.WHITE);
				btnConfirmar.setForeground(Color.BLACK);
			}
		});
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(verificarCampos()) {
						return;
					} else {
						tarjeta.setTitular(txtTitular.getText());
						tarjeta.setEmisor(txtEmisor.getText());
						tarjeta.setNroTarjeta(txtNroTarjeta.getText());
						tarjeta.setCodVerificacion(Integer.parseInt(txtCodVerificacion.getText()));
						if (controlador.ingresarTarjeta(tarjeta)) {
							JOptionPane.showMessageDialog(UsarTarjeta.this, "Tarjeta ingresada con exito!", "Exito",JOptionPane.INFORMATION_MESSAGE);
							vistareserva.habilitarBoton();
							System.out.println("Tarjeta ingresada con exito!");
							dispose();
						} else {
							JOptionPane.showMessageDialog(UsarTarjeta.this, "Error al ingresar", "Error",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(UsarTarjeta.this, "Error inesperado: " + e2.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.print(e2.getMessage());
				}
			} 
		});
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setBorder(null);
		btnConfirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirmar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnConfirmar.setBounds(250, 300, 150, 25);
		panelContenedor.add(btnConfirmar);
		
		// Boton para volver a la pantalla reservas
		JButton btnAtras = new JButton("Volver");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaReserva reserva = new VistaReserva();
				reserva.setVisible(true);
				UsarTarjeta.this.dispose();
			}
		});
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(255, 0, 0));
				btnAtras.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.WHITE);
				btnAtras.setForeground(Color.BLACK);
			}
		});
		btnAtras.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnAtras.setBorder(null);
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setAlignmentX(0.5f);
		btnAtras.setBounds(50, 300, 150, 25);
		panelContenedor.add(btnAtras);
	}
	
		// Funcion para verificar campos vacios
		private boolean verificarCampos() {
			String titular = txtTitular.getText();
			String emisor = txtEmisor.getText();
			String nroTarjeta = txtNroTarjeta.getText();
			String codVerificacion = txtCodVerificacion.getText();

			if (titular.isEmpty() || emisor.isEmpty() || nroTarjeta.isEmpty() || codVerificacion.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.ERROR_MESSAGE);
				return true;
			} else if (nroTarjeta.length() != 16) {
				JOptionPane.showMessageDialog(this, "El numero de tarjeta, debe ser de 16 digitos!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return true;
				
			}else if(codVerificacion.length()!=3){
				JOptionPane.showMessageDialog(this, "El codigo de verificacion debe ser de 3 digitos!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return true;
				
			}
			return false;
		}
		
}
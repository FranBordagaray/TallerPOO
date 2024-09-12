package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaInicial frame = new PantallaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PantallaInicial() {
		setTitle("Bienvenido");
		setLocationByPlatform(true);
		setResizable(false);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// Etiqueta de bienvenido
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBienvenido.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblBienvenido.setBounds(232, 30, 120, 30);
		contentPane.add(lblBienvenido);
		
		// Leyenda de pantalla
		JLabel lblLeyenda = new JLabel("Desde aquí puede seleccionar a qué inicio de sesión desea acceder");
		lblLeyenda.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		lblLeyenda.setBounds(3, 70, 577, 50);
		contentPane.add(lblLeyenda);
		
		// Boton para acceder al login de cliente
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				PantallaInicial.this.setVisible(false);
			}
		});
		btnCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCliente.setBackground(new Color(126, 211, 33));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCliente.setBackground(Color.WHITE);
			}
		});
		btnCliente.setBackground(Color.WHITE);
		btnCliente.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCliente.setBorder(null);
		btnCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliente.setFont(new Font("Roboto Light", Font.BOLD, 30));
		btnCliente.setBounds(50, 180, 200, 150);
		contentPane.add(btnCliente);
		
		// Boton para acceder al login de empleado
		JButton btnEmpleado = new JButton("Empleado");
		btnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginEmpleado login = new LoginEmpleado();
				login.setVisible(true);
				PantallaInicial.this.setVisible(false);
			}
		});
		btnEmpleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEmpleado.setBackground(new Color(74, 144, 226));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEmpleado.setBackground(Color.WHITE);
			}
		});
		btnEmpleado.setForeground(Color.BLACK);
		btnEmpleado.setBackground(Color.WHITE);
		btnEmpleado.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEmpleado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEmpleado.setBorder(null);
		btnEmpleado.setFont(new Font("Roboto Light", Font.BOLD, 30));
		btnEmpleado.setBounds(326, 180, 200, 150);
		contentPane.add(btnEmpleado);
	}
}

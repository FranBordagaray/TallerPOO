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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Vista.Cliente.RecuperarCuenta;
import javax.swing.SwingConstants;

public class ReportePorTemporada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ReportePorTemporada(DetalleReservaEmpleado empleado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 450, 326);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblTitulo = new JLabel("CONCURRENCIA POR TEMPORTADA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 21));
		lblTitulo.setBounds(0, 10, 450, 22);
		contentPane.add(lblTitulo);

		

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePorTemporada.this.setVisible(false);
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
		btnCerrar.setBounds(405, 0, 45, 30);
		contentPane.add(btnCerrar);
		
		JButton btnPrimavera = new JButton("PRIMAVERA");
		btnPrimavera.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPrimavera.setForeground(Color.BLACK);
		btnPrimavera.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnPrimavera.setBorder(null);
		btnPrimavera.setBackground(Color.WHITE);
		btnPrimavera.setAlignmentX(0.5f);
		btnPrimavera.setBounds(150, 56, 150, 30);
		contentPane.add(btnPrimavera);
		
		JButton btnVerano = new JButton("VERANO");
		btnVerano.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVerano.setForeground(Color.BLACK);
		btnVerano.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnVerano.setBorder(null);
		btnVerano.setBackground(Color.WHITE);
		btnVerano.setAlignmentX(0.5f);
		btnVerano.setBounds(150, 110, 150, 30);
		contentPane.add(btnVerano);
		
		JButton btnOtonio = new JButton("OTOÑO");
		btnOtonio.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOtonio.setForeground(Color.BLACK);
		btnOtonio.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnOtonio.setBorder(null);
		btnOtonio.setBackground(Color.WHITE);
		btnOtonio.setAlignmentX(0.5f);
		btnOtonio.setBounds(150, 164, 150, 30);
		contentPane.add(btnOtonio);
		
		JButton btnInvierno = new JButton("INVIERNO");
		btnInvierno.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInvierno.setForeground(Color.BLACK);
		btnInvierno.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnInvierno.setBorder(null);
		btnInvierno.setBackground(Color.WHITE);
		btnInvierno.setAlignmentX(0.5f);
		btnInvierno.setBounds(150, 218, 150, 30);
		contentPane.add(btnInvierno);
		
		JButton btnTodas = new JButton("TODAS LAS TEMPORADAS");
		btnTodas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTodas.setForeground(Color.BLACK);
		btnTodas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnTodas.setBorder(null);
		btnTodas.setBackground(Color.WHITE);
		btnTodas.setAlignmentX(0.5f);
		btnTodas.setBounds(69, 272, 312, 30);
		contentPane.add(btnTodas);

	}

	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}
}

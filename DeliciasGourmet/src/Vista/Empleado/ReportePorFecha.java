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
import com.toedter.calendar.JDateChooser;

public class ReportePorFecha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ReportePorFecha(DetalleReservaEmpleado empleado) {
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

		

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePorFecha.this.setVisible(false);
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
		
		JLabel lblReservasDetalladasEntreFechas = new JLabel("Reservas Detalladas Entre Fechas");
		lblReservasDetalladasEntreFechas.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservasDetalladasEntreFechas.setFont(new Font("Roboto Light", Font.BOLD, 22));
		lblReservasDetalladasEntreFechas.setBounds(0, 11, 450, 27);
		contentPane.add(lblReservasDetalladasEntreFechas);
		
		JDateChooser dateDesde = new JDateChooser();
		dateDesde.setForeground(Color.BLACK);
		dateDesde.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		dateDesde.setBorder(null);
		dateDesde.setBackground(Color.WHITE);
		dateDesde.setBounds(100, 110, 250, 30);
		contentPane.add(dateDesde);
		
		JDateChooser dateHasta = new JDateChooser();
		dateHasta.setForeground(Color.BLACK);
		dateHasta.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		dateHasta.setBorder(null);
		dateHasta.setBackground(Color.WHITE);
		dateHasta.setBounds(100, 210, 250, 30);
		contentPane.add(dateHasta);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesde.setForeground(Color.BLACK);
		lblDesde.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblDesde.setBounds(0, 62, 450, 30);
		contentPane.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setHorizontalAlignment(SwingConstants.CENTER);
		lblHasta.setForeground(Color.BLACK);
		lblHasta.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblHasta.setBounds(0, 161, 450, 30);
		contentPane.add(lblHasta);
		
		JButton btnBuscarReservas = new JButton("GENERAR");
		btnBuscarReservas.setIcon(new ImageIcon(ReportePorFecha.class.getResource("/Img/icono de reportes.png")));
		btnBuscarReservas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscarReservas.setForeground(Color.BLACK);
		btnBuscarReservas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnBuscarReservas.setBorder(null);
		btnBuscarReservas.setBackground(Color.WHITE);
		btnBuscarReservas.setAlignmentX(0.5f);
		btnBuscarReservas.setBounds(148, 285, 150, 30);
		contentPane.add(btnBuscarReservas);

	}
	

	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}
}

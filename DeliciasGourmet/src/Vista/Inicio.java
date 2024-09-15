package Vista;

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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Inicio() {
		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setResizable(false);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Panel para menu de opciones
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(195, 155, 107));
		panelMenu.setBounds(1, 1, 271, 679);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		// Panel contenedor de pantallas
		JPanel panelContenedor = new JPanel();
		panelContenedor.setBorder(null);
		panelContenedor.setBackground(new Color(195, 155, 107));
		panelContenedor.setBounds(271, 1, 992, 679);
		contentPane.add(panelContenedor);
		panelContenedor.setLayout(null);

		// Panel contenedor para paneles de pantallas
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, -25, 992, 704);
		panelContenedor.add(tabbedPane);

		// Boton para ver pantalla inicio
		JButton btnInicio = new JButton("INICIO");
		btnInicio.setIcon(new ImageIcon(Inicio.class.getResource("/Img/icono de inicio.png")));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInicio.setBackground(new Color(255, 0, 0));
				btnInicio.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnInicio.setBackground(Color.WHITE);
				btnInicio.setForeground(Color.BLACK);
			}
		});
		btnInicio.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInicio.setBackground(Color.WHITE);
		btnInicio.setBorder(null);
		btnInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnInicio.setBounds(0, 100, 271, 35);
		panelMenu.add(btnInicio);

		// Boton para ver pantalla reservas
		JButton btnReservas = new JButton("RESERVAS");
		btnReservas.setIcon(new ImageIcon(Inicio.class.getResource("/Img/icono de reservas.png")));
		btnReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservas.setBackground(new Color(255, 0, 0));
				btnReservas.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReservas.setBackground(Color.WHITE);
				btnReservas.setForeground(Color.BLACK);
			}
		});
		btnReservas.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnReservas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReservas.setBorder(null);
		btnReservas.setBackground(Color.WHITE);
		btnReservas.setAlignmentX(0.5f);
		btnReservas.setBounds(0, 150, 271, 35);
		panelMenu.add(btnReservas);

		// Boton para ver pantalla historial
		JButton btnHistorial = new JButton("HISTORIAL");
		btnHistorial.setIcon(new ImageIcon(Inicio.class.getResource("/Img/icono de historial.png")));
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHistorial.setBackground(new Color(255, 0, 0));
				btnHistorial.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHistorial.setBackground(Color.WHITE);
				btnHistorial.setForeground(Color.BLACK);
			}
		});
		btnHistorial.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHistorial.setBorder(null);
		btnHistorial.setBackground(Color.WHITE);
		btnHistorial.setAlignmentX(0.5f);
		btnHistorial.setBounds(0, 200, 271, 35);
		panelMenu.add(btnHistorial);

		// Boton para ver pantalla perfil
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setIcon(new ImageIcon(Inicio.class.getResource("/Img/icono de perfil.png")));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPerfil.setBackground(new Color(255, 0, 0));
				btnPerfil.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnPerfil.setBackground(Color.WHITE);
				btnPerfil.setForeground(Color.BLACK);
			}
		});
		btnPerfil.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPerfil.setBorder(null);
		btnPerfil.setBackground(Color.WHITE);
		btnPerfil.setAlignmentX(0.5f);
		btnPerfil.setBounds(0, 250, 271, 35);
		panelMenu.add(btnPerfil);

		// Boton para cerrar sesion
		JButton btnCerrarSesion = new JButton("CERRAR SESION");
		btnCerrarSesion.setIcon(new ImageIcon(Inicio.class.getResource("/Img/icono de cerrar sesion.png")));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				Inicio.this.setVisible(false);
			}
		});
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCerrarSesion.setBackground(new Color(255, 0, 0));
				btnCerrarSesion.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCerrarSesion.setBackground(Color.WHITE);
				btnCerrarSesion.setForeground(Color.BLACK);
			}
		});
		btnCerrarSesion.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setAlignmentX(0.5f);
		btnCerrarSesion.setBounds(0, 600, 271, 35);
		panelMenu.add(btnCerrarSesion);

		// Panel inicio
		Dashboard dashboard = new Dashboard();
		tabbedPane.addTab("Inicio", dashboard);
		dashboard.setBorder(null);

		// Panel reservas
		VistaReserva Reserva = new VistaReserva();
		tabbedPane.addTab("Reserva", Reserva);
		dashboard.setBorder(null);

		// Panel historial
		Historial historial = new Historial();
		tabbedPane.addTab("Historial", historial);
		historial.setLayout(null);

		// Panel perfil
		ActualizarPerfil perfil = new ActualizarPerfil();
		tabbedPane.addTab("Perfil", perfil);
		perfil.setLayout(null);

	}
}
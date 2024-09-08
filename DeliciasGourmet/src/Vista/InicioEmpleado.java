package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class InicioEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioEmpleado frame = new InicioEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioEmpleado() {
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
		panelContenedor.setBounds(271, -29, 992, 709);
		contentPane.add(panelContenedor);
		panelContenedor.setLayout(null);

		// Panel contenedor para paneles de pantallas
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 5, 992, 704);
		panelContenedor.add(tabbedPane);

		// Boton para ver pantalla inicio
		JButton btnInicio = new JButton("INICIO");
		btnInicio.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de inicio.png")));
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

		// Boton para ver pantalla gestion de empleados
		JButton btnGestionEmpleados = new JButton("GESTION DE EMPLEADOS");
		btnGestionEmpleados.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono empleado.png")));
		btnGestionEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnGestionEmpleados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestionEmpleados.setBackground(new Color(255, 0, 0));
				btnGestionEmpleados.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGestionEmpleados.setBackground(Color.WHITE);
				btnGestionEmpleados.setForeground(Color.BLACK);
			}
		});
		btnGestionEmpleados.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnGestionEmpleados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionEmpleados.setBorder(null);
		btnGestionEmpleados.setBackground(Color.WHITE);
		btnGestionEmpleados.setAlignmentX(0.5f);
		btnGestionEmpleados.setBounds(0, 150, 271, 35);
		panelMenu.add(btnGestionEmpleados);
		
		// Boton para ver pantalla gestion de mesas
		JButton btnGestionDeMesas = new JButton("GESTION DE MESAS");
		btnGestionDeMesas.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de mesas.png")));
		btnGestionDeMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnGestionDeMesas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestionDeMesas.setBackground(new Color(255, 0, 0));
				btnGestionDeMesas.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGestionDeMesas.setBackground(Color.WHITE);
				btnGestionDeMesas.setForeground(Color.BLACK);
			}
		});
		btnGestionDeMesas.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnGestionDeMesas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionDeMesas.setBorder(null);
		btnGestionDeMesas.setBackground(Color.WHITE);
		btnGestionDeMesas.setAlignmentX(0.5f);
		btnGestionDeMesas.setBounds(0, 200, 271, 35);
		panelMenu.add(btnGestionDeMesas);
		
		// Boton para ver pantalla eventos especiales
		JButton btnEventosEspeciales = new JButton("EVENTOS ESPECIALES");
		btnEventosEspeciales.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de eventos especiales.png")));
		btnEventosEspeciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnEventosEspeciales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEventosEspeciales.setBackground(new Color(255, 0, 0));
				btnEventosEspeciales.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEventosEspeciales.setBackground(Color.WHITE);
				btnEventosEspeciales.setForeground(Color.BLACK);
			}
		});
		btnEventosEspeciales.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnEventosEspeciales.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventosEspeciales.setBorder(null);
		btnEventosEspeciales.setBackground(Color.WHITE);
		btnEventosEspeciales.setAlignmentX(0.5f);
		btnEventosEspeciales.setBounds(0, 250, 271, 35);
		panelMenu.add(btnEventosEspeciales);
		
		// Boton para pantalla reportes
		JButton btnReportes = new JButton("REPORTES");
		btnReportes.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de reportes.png")));
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReportes.setBackground(new Color(255, 0, 0));
				btnReportes.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReportes.setBackground(Color.WHITE);
				btnReportes.setForeground(Color.BLACK);
			}
		});
		btnReportes.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReportes.setBorder(null);
		btnReportes.setBackground(Color.WHITE);
		btnReportes.setAlignmentX(0.5f);
		btnReportes.setBounds(0, 300, 271, 35);
		panelMenu.add(btnReportes);

		// Boton para ver pantalla perfil
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de perfil.png")));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
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
		btnPerfil.setBounds(0, 350, 271, 35);
		panelMenu.add(btnPerfil);

		// Boton para cerrar sesion
		JButton btnCerrarSesion = new JButton("CERRAR SESION");
		btnCerrarSesion.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de cerrar sesion.png")));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		// Panel para inicio
		JPanel inicio = new JPanel();
		inicio.setBorder(null);
		tabbedPane.addTab("INICIO", null, inicio, null);
		inicio.setLayout(null);
		
		JLabel lblComingSoonInicio = new JLabel("COMING SOON INICIO");
		lblComingSoonInicio.setBounds(340, 302, 307, 36);
		lblComingSoonInicio.setFont(new Font("Roboto Light", Font.BOLD, 30));
		inicio.add(lblComingSoonInicio);
		
		// Panel para gestion de empleados
		GestionEmpleados gestionEmpleado = new GestionEmpleados();
		tabbedPane.addTab("EMPLEADOS", gestionEmpleado);
		gestionEmpleado.setLayout(null);
		
		// Panel para gestion de mesas
		JPanel gestionMesas = new JPanel();
		tabbedPane.addTab("MESAS", null, gestionMesas, null);
		gestionMesas.setLayout(null);
		
		JLabel lblComingSoonGestionMesas = new JLabel("COMING SOON GESTION MESAS");
		lblComingSoonGestionMesas.setFont(new Font("Roboto Light", Font.BOLD, 30));
		lblComingSoonGestionMesas.setBounds(265, 302, 457, 36);
		gestionMesas.add(lblComingSoonGestionMesas);
		
		// Panel para eventos especiales
		JPanel eventosEspeciales = new JPanel();
		tabbedPane.addTab("EVENTOS ESPECIALES", null, eventosEspeciales, null);
		eventosEspeciales.setLayout(null);
		
		JLabel lblComingSoonEventEspecial = new JLabel("COMING SOON EVENTO ESPECIAL");
		lblComingSoonEventEspecial.setFont(new Font("Roboto Light", Font.BOLD, 30));
		lblComingSoonEventEspecial.setBounds(252, 302, 482, 36);
		eventosEspeciales.add(lblComingSoonEventEspecial);
		
		// Panel para reportes
		Reporte reporte = new Reporte();
		tabbedPane.addTab("REPORTES",reporte);
		reporte.setLayout(null);
		
		// Panel para perfil
		PerfilEmpleado perfil = new PerfilEmpleado();
		tabbedPane.addTab("PERFIL", perfil);
		perfil.setLayout(null);
	}
}
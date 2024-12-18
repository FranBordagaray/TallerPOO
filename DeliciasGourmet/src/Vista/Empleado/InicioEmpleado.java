package Vista.Empleado;

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

/**
 * Clase que representa la ventana principal para los empleados del sistema.
 * Proporciona acceso a las funcionalidades de gestión de reservas y mesas.
 */
public class InicioEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane; // Panel principal de la ventana
    private VistaReservaEmpleado gestionMesas; // Vista para la gestión de mesas

    /**
     * Constructor de la clase InicioEmpleado.
     * Inicializa la ventana con los componentes necesarios y la configuración básica.
     *
     * @param rol El rol del empleado que accede a la aplicación (ej. recepcionista, gerente).
     */
	public InicioEmpleado(String rol) {

		// Configuración de la ventana principal
		setTitle("Empleado");
		ImageIcon icon = new ImageIcon(getClass().getResource("/Img/Icono pantalla empleado.png"));
		setIconImage(icon.getImage());
		setLocationByPlatform(true);
		setResizable(false);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				gestionMesas.resetearCampos();
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
				gestionMesas.resetearCampos();
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
		btnGestionDeMesas.setBounds(0, 195, 271, 35);
		panelMenu.add(btnGestionDeMesas);
		
		//Boton para ver pantalla gestion de reservas
		JButton btnGestionReserva = new JButton("GESTION DE RESERVAS");
		btnGestionReserva.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de eventos especiales.png")));
		btnGestionReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					gestionMesas.resetearCampos();
					tabbedPane.setSelectedIndex(3);
					
				}
		});
		btnGestionReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGestionReserva.setBackground(new Color(255, 0, 0));
				btnGestionReserva.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGestionReserva.setBackground(Color.WHITE);
				btnGestionReserva.setForeground(Color.BLACK);
			}
		});
		btnGestionReserva.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnGestionReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionReserva.setBorder(null);
		btnGestionReserva.setBackground(Color.WHITE);
		btnGestionReserva.setAlignmentX(0.5f);
		btnGestionReserva.setBounds(0, 240, 271, 35);
		panelMenu.add(btnGestionReserva);

		// Boton para pantalla reportes
		JButton btnReportes = new JButton("REPORTES");
		btnReportes.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de reportes.png")));
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionMesas.resetearCampos();
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
		btnReportes.setBounds(0, 285, 271, 35);
		panelMenu.add(btnReportes);

		// Boton para cerrar sesion
		JButton btnCerrarSesion = new JButton("CERRAR SESION");
		btnCerrarSesion.setIcon(new ImageIcon(InicioEmpleado.class.getResource("/Img/icono de cerrar sesion.png")));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginEmpleado login = new LoginEmpleado();
				login.setVisible(true);
				InicioEmpleado.this.setVisible(false);
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

		// Deshabilitar botones según el rol
		if (rol.equals("RECEPCION")) {
			btnGestionEmpleados.setEnabled(false);
			btnReportes.setEnabled(false);
		} else if (rol.equals("MESERO")) {
			btnGestionEmpleados.setEnabled(false);
			btnGestionReserva.setEnabled(false);
			btnGestionDeMesas.setEnabled(false);
			btnReportes.setEnabled(false);
		} else if (rol.equals("MAITRE")) {
			btnGestionEmpleados.setEnabled(false);
			btnGestionReserva.setEnabled(false);
			btnGestionDeMesas.setEnabled(false);
			btnReportes.setEnabled(false);
		}

		// Panel para inicio
		DashboardEmpleado dashboard = new DashboardEmpleado();
		tabbedPane.addTab("INICIO", dashboard);
		dashboard.setLayout(null);

		// Panel para gestion de empleados
		GestionEmpleados gestionEmpleado = new GestionEmpleados();
		tabbedPane.addTab("EMPLEADOS", gestionEmpleado);
		gestionEmpleado.setLayout(null);

		// Panel para gestion de mesas
		gestionMesas = new VistaReservaEmpleado();
		tabbedPane.addTab("MESAS", gestionMesas);
		gestionMesas.setLayout(null);

		//Panel para gestion Reserva
		GestionReserva gestionReserva = new GestionReserva();
		tabbedPane.addTab("GESTIONRESERVA", gestionReserva);
		gestionReserva.setLayout(null);

		// Panel para reportes
		Reporte reporte = new Reporte();
		tabbedPane.addTab("REPORTES", reporte);
		reporte.setLayout(null);

	}
}
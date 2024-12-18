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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import Modelo.Cliente.SesionCliente;
/**
 * La clase {@code InicioCliente} representa la ventana principal de la
 * aplicación para el cliente, donde se pueden gestionar reservas y
 * acceder a diferentes funcionalidades relacionadas con el servicio.
 * 
 * <p>Esta clase extiende {@link JFrame} y contiene varios paneles
 * y botones que permiten a los usuarios interactuar con la aplicación.
 * Incluye acceso a la pantalla de historial de reservas, un dashboard
 * que muestra información relevante del cliente, y una vista para
 * gestionar reservas.</p>
 */
public class InicioCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	/** Panel principal de la ventana. */
	private JPanel contentPane;

	/** Maneja el historial de reservas. */
	private Historial historial;

	/** Muestra información del cliente. */
	private Dashboard dashboard;

	/** Almacena la sesión actual del cliente. */
	private SesionCliente s;

	/** Gestiona las reservas del cliente. */
	private VistaReservaCliente Reserva;
	
	
	/**
	 * Constructor de la clase InicioCliente.
	 *
	 * Este constructor inicializa una nueva instancia de la clase 
	 * InicioCliente, que representa la interfaz principal para los 
	 * clientes después de iniciar sesión en el sistema. Esta vista 
	 * proporciona acceso a las funcionalidades principales, como 
	 * realizar reservas, ver el historial de reservas, actualizar 
	 * su perfil, y más, permitiendo a los usuarios interactuar 
	 * fácilmente con el sistema.
	 */
	@SuppressWarnings("static-access")
	public InicioCliente() {
		
		s = new SesionCliente();
		
		// Configuración de la ventana principal
		setTitle("Cliente");
		ImageIcon icon = new ImageIcon(getClass().getResource("/Img/icono de inicio.png"));
        setIconImage(icon.getImage());
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

		/**
		 * Crea un botón que permite al usuario acceder a la pantalla de inicio.
		 * Al hacer clic en este botón, se selecciona la pestaña correspondiente
		 * al inicio en el panel de pestañas y se cargan los datos del cliente actual.
		 * 
		 * <p>El botón incluye un icono representativo y cambia su color de fondo
		 * y texto cuando el cursor se coloca sobre él, mejorando la experiencia
		 * del usuario.</p>
		 * 
		 * <p>Detalles del botón:</p>
		 * <ul>
		 *   <li>Texto del botón: "INICIO"</li>
		 *   <li>Icono: se carga desde la ruta especificada en los recursos del proyecto.</li>
		 *   <li>Acción: al hacer clic, se selecciona la pestaña de inicio en el {@link JTabbedPane},
		 *       se cargan los datos del cliente actual, y se restablecen los campos de reserva.</li>
		 *   <li>Estilo: fuente en negrita, color de fondo y color del texto se ajustan para
		 *       mejorar la apariencia.</li>
		 *   <li>Posicionamiento: el botón se ubica en el panel del menú en una posición
		 *       específica.</li>
		 * </ul>
		 */
		JButton btnInicio = new JButton("INICIO");
		btnInicio.setIcon(new ImageIcon(InicioCliente.class.getResource("/Img/icono de inicio.png")));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				dashboard.cargarDatos(s.getClienteActual().getIdCliente());
				Reserva.resetearCampos();
				Reserva.habilitarBoton(false);
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

		/**
		 * Crea un botón que permite al usuario acceder a la pantalla de reservas.
		 * Al hacer clic en este botón, se selecciona la pestaña correspondiente
		 * a reservas en el panel de pestañas.
		 * 
		 * <p>El botón incluye un icono representativo y cambia su color de fondo
		 * y texto cuando el cursor se coloca sobre él, mejorando la experiencia
		 * del usuario.</p>
		 * 
		 * <p>Detalles del botón:</p>
		 * <ul>
		 *   <li>Texto del botón: "RESERVAS"</li>
		 *   <li>Icono: se carga desde la ruta especificada en los recursos del proyecto.</li>
		 *   <li>Acción: al hacer clic, se selecciona la pestaña de reservas en el {@link JTabbedPane}.</li>
		 *   <li>Estilo: fuente en negrita, color de fondo y color del texto se ajustan para
		 *       mejorar la apariencia.</li>
		 *   <li>Posicionamiento: el botón se ubica en el panel del menú en una posición
		 *       específica.</li>
		 * </ul>
		 */
		JButton btnReservas = new JButton("RESERVAS");
		btnReservas.setIcon(new ImageIcon(InicioCliente.class.getResource("/Img/icono de reservas.png")));
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

		/**
		 * Crea un botón que permite al usuario acceder a la pantalla de historial.
		 * Al hacer clic en este botón, se selecciona la pestaña correspondiente
		 * al historial en el panel de pestañas y se cargan los datos del cliente actual.
		 * 
		 * <p>El botón incluye un icono representativo y cambia su color de fondo
		 * y texto cuando el cursor se coloca sobre él, mejorando la experiencia
		 * del usuario.</p>
		 * 
		 * <p>Detalles del botón:</p>
		 * <ul>
		 *   <li>Texto del botón: "HISTORIAL"</li>
		 *   <li>Icono: se carga desde la ruta especificada en los recursos del proyecto.</li>
		 *   <li>Acción: al hacer clic, se selecciona la pestaña de historial en el {@link JTabbedPane}
		 *       y se cargan los datos del cliente actual.</li>
		 *   <li>Estilo: fuente en negrita, color de fondo y color del texto se ajustan para
		 *       mejorar la apariencia.</li>
		 *   <li>Posicionamiento: el botón se ubica en el panel del menú en una posición
		 *       específica.</li>
		 * </ul>
		 */
		JButton btnHistorial = new JButton("HISTORIAL");
		btnHistorial.setIcon(new ImageIcon(InicioCliente.class.getResource("/Img/icono de historial.png")));
		btnHistorial.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				historial.cargarDatos(s.getClienteActual().getIdCliente());
				Reserva.resetearCampos();
				Reserva.habilitarBoton(false);
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

		/**
		 * Crea un botón que permite al usuario acceder a la pantalla de perfil.
		 * Al hacer clic en este botón, se reinician los campos de la reserva,
		 * se deshabilita el botón correspondiente, y se selecciona la pestaña
		 * correspondiente al perfil en el panel de pestañas.
		 * 
		 * <p>El botón incluye un icono representativo y cambia su color de fondo
		 * y texto cuando el cursor se coloca sobre él, mejorando así la
		 * experiencia del usuario.</p>
		 * 
		 * <p>Detalles del botón:</p>
		 * <ul>
		 *   <li>Texto del botón: "PERFIL"</li>
		 *   <li>Icono: se carga desde la ruta especificada en los recursos del proyecto.</li>
		 *   <li>Acción: al hacer clic, se llaman a los métodos {@link Reserva#resetearCampos()}
		 *       y {@link Reserva#habilitarBoton(boolean)} y se selecciona la pestaña de perfil.</li>
		 *   <li>Estilo: fuente en negrita, color de fondo y color del texto se ajustan para
		 *       mejorar la apariencia.</li>
		 *   <li>Posicionamiento: el botón se ubica en el panel del menú en una posición
		 *       específica.</li>
		 * </ul>
		 */
		JButton btnPerfil = new JButton("PERFIL");
		btnPerfil.setIcon(new ImageIcon(InicioCliente.class.getResource("/Img/icono de perfil.png")));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva.resetearCampos();
				Reserva.habilitarBoton(false);
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

		/**
		 * Crea un botón que permite al usuario cerrar sesión en el sistema.
		 * Al hacer clic en este botón, se abre la ventana de inicio de sesión
		 * y se oculta la ventana actual del cliente.
		 * 
		 * <p>El botón incluye un icono representativo y cambia su color de fondo
		 * y texto cuando el cursor se coloca sobre él, mejorando así la
		 * experiencia del usuario.</p>
		 * 
		 * <p>Detalles del botón:</p>
		 * <ul>
		 *   <li>Texto del botón: "CERRAR SESION"</li>
		 *   <li>Icono: se carga desde la ruta especificada en los recursos del proyecto.</li>
		 *   <li>Acción: al hacer clic, se instancia un objeto de la clase {@link LoginCliente}.</li>
		 *   <li>Estilo: fuente en negrita, color de fondo y color del texto se ajustan para
		 *       mejorar la apariencia.</li>
		 *   <li>Posicionamiento: el botón se ubica en el panel del menú en una posición
		 *       específica.</li>
		 * </ul>
		 */
		JButton btnCerrarSesion = new JButton("CERRAR SESION");
		btnCerrarSesion.setIcon(new ImageIcon(InicioCliente.class.getResource("/Img/icono de cerrar sesion.png")));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginCliente login = new LoginCliente();
				login.setVisible(true);
				InicioCliente.this.setVisible(false);
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
		dashboard = new Dashboard();
		tabbedPane.addTab("Inicio", dashboard);
		dashboard.setBorder(null);

		// Panel reservas
		Reserva = new VistaReservaCliente();
		tabbedPane.addTab("Reserva", Reserva);
		dashboard.setBorder(null);

		// Panel Historial
		historial = new Historial();
		tabbedPane.addTab("Historial", historial);
		historial.setLayout(null);

		// Panel perfil
		ActualizarPerfil perfil = new ActualizarPerfil();
		tabbedPane.addTab("Perfil", perfil);
		perfil.setLayout(null);

	}
}
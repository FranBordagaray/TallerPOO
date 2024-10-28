package Vista.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.MesaControlador;
import Controlador.ReservaControlador;
import Controlador.ServicioControlador;
import Modelo.Cliente.SesionCliente;
import Modelo.Complementos.Servicio;
import Modelo.Cliente.HistorialReserva;

import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Clase que representa el panel principal del dashboard, donde se muestran las reservas y notificaciones.
 */
public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

    /**
     * Tabla que muestra un resumen de las reservas realizadas.
     */
    private JTable tblResumenReserva;

    /**
     * Etiqueta que muestra la fecha y hora actual.
     */
    private JLabel lblFechaHora;

    /**
     * Controlador encargado de gestionar las operaciones relacionadas con los servicios.
     */
    private ServicioControlador servicioControlador;

    /**
     * Controlador encargado de gestionar las operaciones relacionadas con las mesas.
     */
    private MesaControlador mesaControlador;

    /**
     * Panel que contiene las notificaciones relevantes para el usuario.
     */
    private JPanel panelNotificaciones;
	
    /**
     * Constructor de la clase Dashboard.
     *
     * Este constructor inicializa una nueva instancia de la clase Dashboard,
     * que representa la interfaz principal de la aplicación. El Dashboard proporciona 
     * acceso a las funcionalidades principales del sistema, como la gestión de reservas,
     * la visualización de informes y la administración de servicios. 
     * Al invocar este constructor, se configuran los componentes visuales 
     * y se establecen las propiedades iniciales de la interfaz.
     */
	@SuppressWarnings({ "static-access", "serial" })
	public Dashboard() {
		mesaControlador = new MesaControlador();
		servicioControlador = new ServicioControlador();
		
		// Configuracion del panel
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));

		// Panel de Bienvenida
		JPanel pnlBienvenido = new JPanel();
		pnlBienvenido.setBackground(new Color(195, 155, 107));
		pnlBienvenido.setBounds(0, 0, 992, 50);
		add(pnlBienvenido);
		pnlBienvenido.setLayout(null);

		// Label de Bienvenida
		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBienvenido.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgHome.png")));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setForeground(Color.BLACK);
		lblBienvenido.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblBienvenido.setBounds(0, 0, 170, 50);
		pnlBienvenido.add(lblBienvenido);

		// Label Nombre de Usuario
		SesionCliente s1 = new SesionCliente();
		JLabel lblNombreUsuario = new JLabel(s1.getClienteActual().getNombre() + " " + s1.getClienteActual().getApellido());
		lblNombreUsuario.setForeground(Color.BLACK);
		lblNombreUsuario.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblNombreUsuario.setBounds(170, 0, 330, 50);
		pnlBienvenido.add(lblNombreUsuario);
		
		// Etiqueta fecha y hora
        lblFechaHora = new JLabel();
        lblFechaHora.setBorder(null);
        lblFechaHora.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaHora.setForeground(Color.BLACK);
        lblFechaHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFechaHora.setBounds(792, 12, 200, 25);
        pnlBienvenido.add(lblFechaHora);

		// Panel de Notificaciones
		panelNotificaciones = new JPanel();
		panelNotificaciones.setBorder(null);
		panelNotificaciones.setBounds(549, 110, 433, 150);
		add(panelNotificaciones);

		// Label Mis Reservas
		JLabel lblMisReservas = new JLabel("Mis Reservas");
		lblMisReservas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMisReservas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisReservas.setForeground(Color.BLACK);
		lblMisReservas.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblMisReservas.setBounds(165, 75, 150, 25);
		add(lblMisReservas);

		// Panel Oferta Imagenes
		ImgOfertas Ofertas = new ImgOfertas();
		Ofertas.setLocation(696, 298);
		JPanel pnlOfertas = new JPanel();
		pnlOfertas.setBorder(null);
		pnlOfertas.setBounds(696, 298, 286, 370);
		add(Ofertas);

		//Panel  Comida
		ImgOfertas menuComida = new ImgOfertas();
		menuComida.setBounds(353, 298, 286, 370);
		add(menuComida);
		menuComida.setLayout(null);
		
		//JLabel Comida
		JLabel lblComida = new JLabel("");
		lblComida.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/MenuComida.png")));
		lblComida.setBounds(0, 0, 286, 370);
		menuComida.add(lblComida);
		
		//Panel Menu Bebidas
		ImgOfertas menuBebidas = new ImgOfertas();
		menuBebidas.setBounds(10, 298, 286, 370);
		add(menuBebidas);
		menuBebidas.setLayout(null);
		
		//JLabel Bebidas
		JLabel lblBebidas = new JLabel("");
		lblBebidas.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/MenuBebida.png")));
		lblBebidas.setBounds(0, 0, 286, 370);
		menuBebidas.add(lblBebidas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		scrollPane.setBounds(10, 110, 490, 150);
		add(scrollPane);

		tblResumenReserva = new JTable();
		tblResumenReserva.setGridColor(Color.DARK_GRAY);
		tblResumenReserva.setBackground(Color.WHITE);
		tblResumenReserva.setFont(new Font("Roboto Light", Font.PLAIN, 13));
		tblResumenReserva.setBorder(null);
		tblResumenReserva.setForeground(Color.BLACK);
		tblResumenReserva.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblResumenReserva.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "MESA", "FECHA", "HORA", "UBICACION" }) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		
		TableColumnModel columnModel = tblResumenReserva.getColumnModel();
     	columnModel.getColumn(0).setPreferredWidth(60);
     	columnModel.getColumn(1).setPreferredWidth(100);
     	columnModel.getColumn(2).setPreferredWidth(100);
     	columnModel.getColumn(3).setPreferredWidth(225);
     	
     	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
     	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
     	tblResumenReserva.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		scrollPane.setViewportView(tblResumenReserva);

		// Panel Header Notificaciones
		JPanel pnlHeaderNot = new JPanel();
		pnlHeaderNot.setBounds(549, 63, 433, 47);
		add(pnlHeaderNot);
		pnlHeaderNot.setBorder(null);
		pnlHeaderNot.setBackground(new Color(195, 155, 107));
		pnlHeaderNot.setLayout(null);

		// Label Titulo Notificaciones
		JLabel lblTituloNotificaciones = new JLabel("Notificaciones");
		lblTituloNotificaciones.setForeground(Color.BLACK);
		lblTituloNotificaciones.setBounds(155, 7, 119, 32);
		lblTituloNotificaciones.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgNotificacion.png")));
		pnlHeaderNot.add(lblTituloNotificaciones);
		lblTituloNotificaciones.setFont(new Font("Roboto Light", Font.BOLD, 12));
		
		// JScrollPane para poder deslizar las notificaciones
		JScrollPane scrollPaneNotificaciones = new JScrollPane(panelNotificaciones);
		scrollPaneNotificaciones.setBounds(549, 110, 433, 150);
		add(scrollPaneNotificaciones);
		scrollPaneNotificaciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneNotificaciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPaneNotificaciones, BorderLayout.CENTER);

		// Centrar el contenido de la primera columna
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);
		List<Servicio> servicios = filtrarServiciosPorFecha(servicioControlador.buscarServiciosConEventoEspecial());
		cargarNotificacionesVisual(servicios);
		
		cargarDatos(s1.getClienteActual().getIdCliente());	
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                actualizarFechaHora();
            }
        }, 0, 1000);
	}
	
	/**
	 * Carga los datos del historial de reservas del cliente en la tabla de resumen de reservas.
	 *
	 * Este método utiliza el identificador del cliente para obtener su historial de reservas
	 * desde la base de datos a través del controlador de reservas. Luego, llena la tabla
	 * `tblResumenReserva` con los datos obtenidos, incluyendo el ID de la mesa, la fecha,
	 * la hora y la ubicación de cada reserva.
	 *
	 * @param idCliente el identificador único del cliente cuyo historial de reservas se va a cargar.	 
	 */
	public void cargarDatos(int idCliente) {
		ReservaControlador controlador = new ReservaControlador();
		List<HistorialReserva> historial;
		try {
			historial = controlador.obtenerHistorialPorCliente(idCliente);
			DefaultTableModel model = (DefaultTableModel) tblResumenReserva.getModel();
			model.setRowCount(0);

			for (HistorialReserva reserva : historial) {
				model.addRow(new Object[] { reserva.getIdMesa(), reserva.getFecha(), reserva.getHora(),
						reserva.getUbicacion() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Filtra los servicios de acuerdo a la fecha, devolviendo solo aquellos que 
	 * son iguales o posteriores a la fecha actual.
	 *
	 * Este método toma una lista de servicios y verifica la fecha de cada uno 
	 * utilizando el formato "dd-MM-yyyy". Si la fecha de un servicio es 
	 * igual o posterior a la fecha actual, se añade a la lista de servicios 
	 * filtrados.
	 *
	 * @param servicios la lista de servicios a filtrar.
	 * @return una lista de servicios que ocurren desde la fecha actual en adelante.
	 * @throws DateTimeParseException si no se puede analizar la fecha de un servicio
	 *         debido a un formato incorrecto.
	 */
	//Metodo para filtrar servicio de la fecha de hoy en adelante
	public List<Servicio> filtrarServiciosPorFecha(List<Servicio> servicios) {
	    List<Servicio> serviciosFiltrados = new ArrayList<>();
	    LocalDate fechaActual = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    for (Servicio servicio : servicios) {
	        try {
	            LocalDate fechaServicio = LocalDate.parse(servicio.getFecha(), formatter);
	            	            if (!fechaServicio.isBefore(fechaActual)) {
	                serviciosFiltrados.add(servicio);
	            }
	        } catch (DateTimeParseException e) {
	            e.printStackTrace();
	            System.out.println("Error al analizar la fecha: " + servicio.getFecha());
	        }
	    }
	    return serviciosFiltrados;
	}
	
	/**
	 * Carga y visualiza notificaciones de eventos especiales en el panel de notificaciones.
	 *
	 * Este método toma una lista de servicios y crea un panel para cada servicio que contiene
	 * información relevante como el icono del evento, título, fecha, hora y ubicación.
	 * Los paneles de servicio se organizan utilizando un {@code GridBagLayout} para un 
	 * diseño flexible y responsivo.
	 *
	 * @param servicios la lista de servicios que representan eventos especiales a mostrar.
	 * @throws NullPointerException si la lista de servicios es {@code null} o si se produce 
	 *         un error al buscar la ubicación del servicio.
	 */
	private void cargarNotificacionesVisual(List<Servicio> servicios) {
	    panelNotificaciones.removeAll();
	    panelNotificaciones.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = GridBagConstraints.RELATIVE;
	    gbc.weightx = 1.0;
	    gbc.insets = new Insets(5, 5, 5, 5);

	    ImageIcon iconoNotificacion = cargarIcono("/Img/icono de reservas.png"); 

	    for (Servicio servicio : servicios) {
	        JPanel panelServicio = new JPanel();
	        panelServicio.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        panelServicio.setLayout(new BoxLayout(panelServicio, BoxLayout.Y_AXIS));

	        JLabel lblIcono = new JLabel(iconoNotificacion);
	        lblIcono.setAlignmentX(Component.LEFT_ALIGNMENT);

	        JLabel lblTitulo = new JLabel("Evento Especial");
	        lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 14));
	        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

	        JLabel lblFecha = new JLabel("Fecha: " + servicio.getFecha());
	        lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

	        JLabel lblHora = new JLabel("Hora: " + servicio.getHoraInicio() + " - " + servicio.getHoraFin());
	        lblHora.setAlignmentX(Component.LEFT_ALIGNMENT);

	        JLabel lblUbicacion = new JLabel("Ubicación: " + mesaControlador.buscarUbicacionPorIdServicio(servicio.getIdServicio()));
	        lblUbicacion.setAlignmentX(Component.LEFT_ALIGNMENT);

	        panelServicio.add(lblIcono); 
	        panelServicio.add(lblTitulo); 
	        panelServicio.add(Box.createRigidArea(new Dimension(0, 5)));
	        panelServicio.add(lblFecha);
	        panelServicio.add(lblHora);
	        panelServicio.add(lblUbicacion);

	        panelNotificaciones.add(panelServicio, gbc);
	    }
	    panelNotificaciones.revalidate();
	    panelNotificaciones.repaint();
	}

	/**
	 * Carga un icono de imagen desde la ruta especificada en los recursos de la clase.
	 *
	 * Este método utiliza {@code getClass().getResource(ruta)} para localizar 
	 * la imagen en el classpath y devuelve un {@code ImageIcon} que puede ser 
	 * utilizado en componentes de la interfaz gráfica de usuario (GUI).
	 *
	 * @param ruta la ruta del recurso de la imagen (por ejemplo, "/Img/icono.png").
	 * @return un objeto {@code ImageIcon} que representa la imagen cargada.
	 * @throws NullPointerException si la ruta especificada no se encuentra en los recursos.
	 */
	private ImageIcon cargarIcono(String ruta) {
	    return new ImageIcon(getClass().getResource(ruta));
	}
	
	/**
	 * Actualiza la etiqueta de fecha y hora en la interfaz gráfica.
	 * 
	 * Este método obtiene la fecha y hora actuales del sistema, las formatea
	 * en el patrón "dd/MM/yyyy HH:mm" y actualiza la etiqueta {@code lblFechaHora}
	 * para mostrar el resultado.
	 * 
	 * La actualización de la etiqueta se realiza en el hilo de despacho de eventos
	 * de Swing mediante {@code SwingUtilities.invokeLater}, asegurando que la 
	 * interfaz gráfica se mantenga responsiva y actualizada correctamente.
	 */

    private void actualizarFechaHora() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaHoraFormateada = fechaHoraActual.format(formato);
        SwingUtilities.invokeLater(() -> lblFechaHora.setText(fechaHoraFormateada));
    }
}

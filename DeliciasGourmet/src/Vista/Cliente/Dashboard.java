package Vista.Cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import Controlador.ReservaControlador;
import Modelo.HistorialReserva;
import Modelo.Sesion;
import java.awt.Component;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblResumenReserva;

	@SuppressWarnings({ "static-access", "serial" })
	public Dashboard() {

		// Configuracion del panel
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));

		// Panel de Bienvenida
		JPanel pnlBienvenido = new JPanel();
		pnlBienvenido.setBackground(new Color(195, 155, 107));
		pnlBienvenido.setBounds(0, 0, 500, 66);
		add(pnlBienvenido);
		pnlBienvenido.setLayout(null);

		// Label de Bienvenida
		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBienvenido.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgHome.png")));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setForeground(Color.BLACK);
		lblBienvenido.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblBienvenido.setBounds(0, 8, 170, 50);
		pnlBienvenido.add(lblBienvenido);

		// Label Nombre de Usuario
		Sesion s1 = new Sesion();
		JLabel lblNombreUsuario = new JLabel(
		s1.getClienteActual().getNombre() + " " + s1.getClienteActual().getApellido());
		lblNombreUsuario.setForeground(Color.BLACK);
		lblNombreUsuario.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblNombreUsuario.setBounds(170, 8, 330, 50);
		pnlBienvenido.add(lblNombreUsuario);

		// Panel de Notificaciones
		JPanel pnlNotificaciones = new JPanel();
		pnlNotificaciones.setBorder(null);
		pnlNotificaciones.setBounds(549, 58, 433, 150);
		pnlNotificaciones.setLayout(null);
		add(pnlNotificaciones);

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

		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().setBackground(new Color(195, 155, 107));
		calendar.setBounds(10, 447, 309, 221);
		add(calendar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		scrollPane.setBounds(10, 110, 490, 150);
		add(scrollPane);

		tblResumenReserva = new JTable();
		tblResumenReserva.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		tblResumenReserva.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblResumenReserva.setBorder(null);
		tblResumenReserva.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "MESA", "FECHA", "HORA", "UBICACION" }) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		scrollPane.setViewportView(tblResumenReserva);

		// Panel Header Notificaciones
		JPanel pnlHeaderNot = new JPanel();
		pnlHeaderNot.setBounds(549, 10, 433, 47);
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
		cargarDatos(s1.getClienteActual().getIdCliente());
	}

	// Función para cargar tabla con datos almacenados en la base de datos
	private void cargarDatos(int idCliente) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
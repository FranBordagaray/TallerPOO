package Vista.Empleado;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Controlador.EmpleadoControlador;
import Controlador.MesaControlador;
import Modelo.Complementos.Comprobante;
import Modelo.Complementos.EnumEstado;
import Modelo.Complementos.Mesa;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;
import Modelo.Empleado.SesionEmpleado;
import Vista.Cliente.DetalleReservaCliente;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JList;

/**
 * Clase que representa la vista para la reserva de mesas por parte de un empleado.
 * Permite seleccionar fechas, horas, mesas y realizar reservas o bloquear mesas.
 */
public class VistaReservaEmpleado extends JPanel {
    private static final long serialVersionUID = 1L; // Identificador único de la clase

    private JPanel Mapas; // Panel principal para la interfaz
    private JDateChooser calendario2; // Selector de fecha
    private JComboBox<String> horarioComboBox; // ComboBox para seleccionar horarios
    private JComboBox<String> horaFinComboBox; // ComboBox para seleccionar la hora de fin
    private JComboBox<String> horaInicioComboBox; // ComboBox para seleccionar la hora de inicio
    private JButton btnReservar; // Botón para realizar una reserva
    private JButton btnBloquear; // Botón para bloquear una mesa
    private JButton btnCrearEventoEspecial; // Botón para crear un evento especial
    private JButton btnReservarLugar; // Botón para reservar un lugar
    private LocalDate hoy; // Fecha de hoy
    private LocalDate unAnoFuturo; // Fecha un año en el futuro
    private JComboBox<String> comboUbicaciones; // ComboBox para seleccionar ubicaciones
    private JComboBox<String> comboMesa; // ComboBox para seleccionar mesas
    private JComboBox<Integer> comboCapacidad; // ComboBox para seleccionar capacidad
    private MesaControlador mesaControlador; // Controlador para gestionar mesas
    private EmpleadoControlador empleadoControlador; // Controlador para gestion mesas
    private String SeleccionarUbicacion; // Ubicación seleccionada
    private int capacidadSeleccionada; // Capacidad seleccionada
    private String fechaFormateada; // Fecha formateada para reservas
    private String horaSeleccionada; // Hora seleccionada para la reserva
    private String horaInicio; // Hora de inicio de la reserva
    private String horaFinal; // Hora de finalización de la reserva
    private JList<String> listaMesas; // Lista para mostrar mesas disponibles
    private DefaultListModel<String> listModel; // Modelo de la lista de mesas
    private JTextArea CampoComentario; // Campo de texto para comentarios de la reserva
    private String[] ubicaciones; // Array de ubicaciones
    private String[] mesasSeleccionadas; // Array de mesas seleccionadas
    private Reserva reserva; // Objeto reserva
    private Mesa mesa; // Objeto mesa
    private int idMesaSeleccionada; // ID de la mesa seleccionada
    private int idMesaSeleccionadaEvento; // ID de la mesa seleccionada para evento
    private int servicioCreado; // ID del servicio creado
    private Comprobante comprobante; // Comprobante asociado a la reserva
    private Servicio servicio; // Objeto servicio
    private DetalleReservaEmpleado detalle_2; // Detalle de la reserva para empleados
    private DetalleReservaClienteParaEmpleado detalles_1; // Detalle de la reserva para clientes
   

    /**
     * Constructor de la clase VistaReservaEmpleado.
     * Inicializa la vista y sus componentes.
     */
	public VistaReservaEmpleado() {

		// Configuracion del panel
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));

		mesaControlador = new MesaControlador();
		empleadoControlador = new EmpleadoControlador();

		// Inicializa fechas de restricción
		hoy = LocalDate.now();
		unAnoFuturo = hoy.plusYears(1);

		// Panel Vertical
		JPanel pnlVertical = new JPanel();
		pnlVertical.setBorder(null);
		pnlVertical.setBackground(new Color(222, 184, 135));
		pnlVertical.setBounds(0, 0, 232, 679);
		add(pnlVertical);
		pnlVertical.setLayout(null);

		// Etiqueta de Ubicaciones
		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbicacion.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblUbicacion.setBounds(16, 33, 200, 20);
		pnlVertical.add(lblUbicacion);

		// Desplegable Ubicaciones
		ubicaciones = new String[] { "COMEDOR PRINCIPAL", "TERRAZA", "BAR", "SALA PRIVADA", "PATIO" };
		comboUbicaciones = new JComboBox<>(ubicaciones);
		comboUbicaciones.setSelectedItem("Comedor Principal");
		comboUbicaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SeleccionarUbicacion = (String) comboUbicaciones.getSelectedItem();
				cambioPanel(SeleccionarUbicacion);
				actualizarMesas();
				System.out.println("UBICACION SELECCIONADA: " + SeleccionarUbicacion);
				listModel.clear();
				
			}
		});
		comboUbicaciones.setForeground(Color.BLACK);
		comboUbicaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboUbicaciones.setBackground(Color.WHITE);
		comboUbicaciones.setBorder(null);
		comboUbicaciones.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		comboUbicaciones.setToolTipText("");
		comboUbicaciones.setBounds(16, 63, 200, 25);
		pnlVertical.add(comboUbicaciones);

		// Etiqueta Fecha
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setBackground(Color.WHITE);
		lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblFecha.setBounds(16, 94, 200, 20);
		pnlVertical.add(lblFecha);

		//DefaultListModel para el listaMesas
		listModel = new DefaultListModel<>();

		//JList para las mesas
		listaMesas = new JList<>(listModel);
		listaMesas.setBounds(113, 386, 103, 70);
		pnlVertical.add(listaMesas);

		// Boton para quitar mesas
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = listaMesas.getSelectedIndex();

				if (selectedIndex != -1) {
					listModel.remove(selectedIndex);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una mesa para quitar.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnQuitar.setFont(new Font("Roboto Light", Font.BOLD, 10));
		btnQuitar.setEnabled(false);
		btnQuitar.setBounds(16, 396, 87, 25);
		pnlVertical.add(btnQuitar);

		// Crear el botón "Agregar"
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Roboto Light", Font.BOLD, 10));
		btnAgregar.setBounds(16, 358, 87, 25);
		pnlVertical.add(btnAgregar);
		btnAgregar.setEnabled(false);

		// Acción para el botón "Agregar"
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String seleccionMesa = (String) comboMesa.getSelectedItem();

				if (seleccionMesa != null && !seleccionMesa.isEmpty()) {
					if (!listModel.contains(seleccionMesa)) {
						listModel.addElement(seleccionMesa);
					} else {
						JOptionPane.showMessageDialog(null, "La mesa ya ha sido añadida.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona una mesa.");
				}
			}
		});

		// JCheckBox para habilitar eventos especiales
		JCheckBox eventoEspecialCheckBox = new JCheckBox("Evento Especial");
		eventoEspecialCheckBox.setBounds(11, 6, 150, 21);
		pnlVertical.add(eventoEspecialCheckBox);
		eventoEspecialCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (eventoEspecialCheckBox.isSelected()) {
					horaInicioComboBox.setEnabled(true);
					horaFinComboBox.setEnabled(true);
					horarioComboBox.setEnabled(false);
					btnAgregar.setEnabled(true);
					btnQuitar.setEnabled(true);
					btnReservar.setEnabled(false);
					btnCrearEventoEspecial.setEnabled(true);
					btnBloquear.setEnabled(true);
					btnReservarLugar.setEnabled(true);

				} else {
					horaInicioComboBox.setEnabled(false);
					horaFinComboBox.setEnabled(false);
					horarioComboBox.setEnabled(true);
					btnAgregar.setEnabled(false);
					btnQuitar.setEnabled(false);
					btnReservar.setEnabled(true);
					btnCrearEventoEspecial.setEnabled(false);
					listaMesas.clearSelection();
					btnBloquear.setEnabled(false);
					btnReservarLugar.setEnabled(false);
					((DefaultListModel<String>) listaMesas.getModel()).clear();
				}
			}
		});

		// JCalendar para la fecha
		calendario2 = new JDateChooser();
		calendario2.setBounds(16, 124, 200, 25);
		pnlVertical.add(calendario2);

		calendario2.setMinSelectableDate(java.sql.Date.valueOf(hoy));
		calendario2.setMaxSelectableDate(java.sql.Date.valueOf(unAnoFuturo));
		calendario2.getDateEditor().addPropertyChangeListener("date", evt -> {
			Date fechaSeleccionadaDate = calendario2.getDate();
			ZonedDateTime zonedDateTime = fechaSeleccionadaDate.toInstant().atZone(ZoneId.systemDefault());
			LocalDateTime fechaSeleccionada = zonedDateTime.toLocalDateTime();

			if (eventoEspecialCheckBox.isSelected()) {
				actualizarHorasInicioDisponibles(fechaSeleccionada);
			} else {
				actualizarHorasDisponibles(fechaSeleccionada);
			}

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			fechaFormateada = fechaSeleccionada.format(formato);
		});

		// Etiqueta de Hora
		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.BLACK);
		lblHora.setBackground(Color.WHITE);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblHora.setBounds(16, 159, 200, 20);
		pnlVertical.add(lblHora);

		// Desplegable Hora
		horarioComboBox = new JComboBox<String>();
		horarioComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		horarioComboBox.setBorder(null);
		horarioComboBox.setForeground(Color.BLACK);
		horarioComboBox.setBackground(Color.WHITE);
		horarioComboBox.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		horarioComboBox.setBounds(74, 189, 87, 25);
		horarioComboBox.addItem("08:00 - 10:00");
		horarioComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarMesas();
			}
		});
		pnlVertical.add(horarioComboBox);

		// Etiqueta de Capacidad
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setForeground(Color.BLACK);
		lblCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblCapacidad.setBackground(Color.WHITE);
		lblCapacidad.setBounds(16, 261, 200, 20);
		pnlVertical.add(lblCapacidad);

		// Desplegable Capacidad
		Integer[] capacidades = { 0, 2, 4, 6 };
		comboCapacidad = new JComboBox<>(capacidades);
		comboCapacidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboCapacidad.setForeground(Color.BLACK);
		comboCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboCapacidad.setBorder(null);
		comboCapacidad.setBackground(Color.WHITE);
		comboCapacidad.setBounds(41, 291, 150, 25);
		pnlVertical.add(comboCapacidad);
		comboCapacidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				capacidadSeleccionada = (int) comboCapacidad.getSelectedItem();
				filtrarCapacidad(capacidadSeleccionada);
			}
		});

		// ComboBox de horaInicio
		horaInicioComboBox = new JComboBox<String>();
		horaInicioComboBox.setForeground(Color.BLACK);
		horaInicioComboBox.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		horaInicioComboBox.setBorder(null);
		horaInicioComboBox.setBackground(Color.WHITE);
		horaInicioComboBox.setBounds(11, 226, 87, 25);
		horaInicioComboBox.setEnabled(false);
		horaInicioComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				actualizarHorasFinDisponibles();
			}
		});

		// ComboBox de horaFin
		horaFinComboBox = new JComboBox<String>();
		horaFinComboBox.setForeground(Color.BLACK);
		horaFinComboBox.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		horaFinComboBox.setEnabled(false);
		horaFinComboBox.setBorder(null);
		horaFinComboBox.setBackground(Color.WHITE);
		horaFinComboBox.setBounds(129, 224, 87, 25);
		horaFinComboBox.setEnabled(false);
		pnlVertical.add(horaFinComboBox);
		pnlVertical.add(horaInicioComboBox);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("Mesas");
		lblMesa.setBorder(null);
		lblMesa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesa.setForeground(Color.BLACK);
		lblMesa.setBackground(Color.WHITE);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblMesa.setBounds(16, 326, 200, 20);
		pnlVertical.add(lblMesa);

		// Desplegable Mesa
		comboMesa = new JComboBox<>();
		comboMesa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboMesa.setForeground(Color.BLACK);
		comboMesa.setBackground(Color.WHITE);
		comboMesa.setBorder(null);
		comboMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboMesa.setBounds(113, 356, 87, 25);
		pnlVertical.add(comboMesa);
		comboMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String seleccionMesa = (String) comboMesa.getSelectedItem();
				if (seleccionMesa != null && !seleccionMesa.isEmpty()) {
					idMesaSeleccionada = obtenerIdMesa(seleccionMesa);
				}
			}
		});

		// Etiqueta Comentario de la Reserva
		JLabel TituloComentario = new JLabel("Comentario");
		TituloComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		TituloComentario.setHorizontalAlignment(SwingConstants.CENTER);
		TituloComentario.setBackground(Color.WHITE);
		TituloComentario.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		TituloComentario.setBounds(16, 466, 200, 20);
		pnlVertical.add(TituloComentario);

		// Campo de Texto Comentario de la Reserva
		CampoComentario = new JTextArea();
		CampoComentario.setFont(new Font("Roboto Light", Font.PLAIN, 13));
		CampoComentario.setForeground(Color.BLACK);
		CampoComentario.setBorder(null);
		CampoComentario.setBackground(Color.WHITE);
		CampoComentario.setBounds(11, 496, 210, 70);
		CampoComentario.setLineWrap(true);
		CampoComentario.setWrapStyleWord(true);
		pnlVertical.add(CampoComentario);

		// Boton para hacer una Reserva Normal
		btnReservar = new JButton("Reservar");
		btnReservar.setIcon(new ImageIcon(VistaReservaEmpleado.class.getResource("/Img/icono de mesas.png")));
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comprobante = new Comprobante();
				mesa = new Mesa();
				reserva = new Reserva();
				servicio = new Servicio();
				recopilarDatosReservaNormal(reserva);
				recopilarDatosMesaNormal(mesa);
				recopilarDatosServicioNormal(servicio);
				recopilarDatosComprobante(comprobante);
				detalles_1 = new DetalleReservaClienteParaEmpleado(reserva, mesa, servicio, comprobante, VistaReservaEmpleado.this);
				detalles_1.setVisible(true);

			}

		});

		btnReservar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReservar.setBackground(new Color(255, 0, 0));
				btnReservar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReservar.setBackground(Color.WHITE);
				btnReservar.setForeground(Color.BLACK);
			}
		});
		btnReservar.setEnabled(true);
		btnReservar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnReservar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReservar.setBorder(null);
		btnReservar.setForeground(Color.BLACK);
		btnReservar.setBackground(Color.WHITE);
		btnReservar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnReservar.setBounds(16, 609, 150, 25);
		pnlVertical.add(btnReservar);

		// Boton para bloquear Mesas
		btnBloquear = new JButton("Bloquear");
		btnBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Primero validar las horas seleccionadas
				if (validarHoras()) {
					horaInicio = (String) horaInicioComboBox.getSelectedItem();
					horaFinal = (String) horaFinComboBox.getSelectedItem();
					System.out.println("Hora de inicio: " + horaInicio);
					System.out.println("Hora de fin: " + horaFinal);

					// Luego, verificar si hay mesas seleccionadas
					mesasSeleccionadas = convertirListModelAArray(listModel);
					if (mesasSeleccionadas.length == 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una mesa para bloquearla.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					System.out.println("Mesas seleccionadas: " + Arrays.toString(mesasSeleccionadas));
					
					// Crear el objeto Servicio para el bloqueo de las mesas
					Servicio servicioBloqueo = new Servicio();
					servicioBloqueo.setFecha(fechaFormateada);
					servicioBloqueo.setHoraInicio(horaInicio);
					servicioBloqueo.setHoraFin(horaFinal);
					servicioBloqueo.setEventoPrivado(0);
					System.out.println("Servicio de bloqueo creado con fecha: " + fechaFormateada + ", Hora inicio: "
							+ horaInicio + ", Hora fin: " + horaFinal);


					boolean mesasBloqueadasCorrectamente = true;

					for (String seleccionMesa : mesasSeleccionadas) {
						int idMesaSeleccionada = obtenerIdMesa(seleccionMesa);
						Mesa mesa1 = recopilarDatosMesaPorNumero(mesasSeleccionadas, idMesaSeleccionada);
						System.out.println("ID de mesa seleccionada para bloqueo: " + idMesaSeleccionada);

						// Verificar si la mesa ya está bloqueada en ese horario
						DetalleReservaEmpleado detalleBloqueo = new DetalleReservaEmpleado(mesa1, servicioBloqueo);

						if (!detalleBloqueo.verificarDisponibilidadMesas()) {
							mesasBloqueadasCorrectamente = false;
							JOptionPane.showMessageDialog(null, "La mesa " + idMesaSeleccionada
									+ " ya está bloqueada o reservada en este horario.");
							break;
						}
						
						mesa1.setIdServicio(servicioBloqueo.getIdServicio());
						mesa1.setEstado(EnumEstado.BLOQUEADA);						
						System.out.println("Mesa bloqueada: " + mesa1.toString());
						
						detalleBloqueo.BloquearMesas();
					}

					if (mesasBloqueadasCorrectamente) {
						JOptionPane.showMessageDialog(null, "Mesas bloqueadas con éxito.");
						System.out.println("Mesas bloqueadas con éxito.");
					}

				} else {
					System.out.println("Las horas seleccionadas no son válidas.");
				}
			}
		});

		btnBloquear.setFont(new Font("Roboto Light", Font.BOLD, 10));
		btnBloquear.setEnabled(true);
		btnBloquear.setBounds(16, 431, 87, 25);
		btnBloquear.setEnabled(false);
		pnlVertical.add(btnBloquear);

		// Boton para Reservar Lugar
		btnReservarLugar = new JButton("Reservar Lugar");
		btnReservarLugar.setIcon(new ImageIcon(VistaReservaEmpleado.class.getResource("/Img/icono lugar.png")));
		btnReservarLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ubicacion = (String) comboUbicaciones.getSelectedItem();
				List<Mesa> mesasReserva = new ArrayList<Mesa>();
				mesasReserva = mesaControlador.buscarMesasPorUbicacion(ubicacion);
				
				if (validarHoras()) {
					horaInicio = (String) horaInicioComboBox.getSelectedItem();
					horaFinal = (String) horaFinComboBox.getSelectedItem();
					System.out.println("Hora de inicio: " + horaInicio);
					System.out.println("Hora de fin: " + horaFinal);

					mesasSeleccionadas = convertirListAMesArray(mesasReserva);
					
					if (mesasSeleccionadas.length == 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una mesa para hacer la reserva.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					System.out.println("Mesas seleccionadas: " + Arrays.toString(mesasSeleccionadas));

					for (String seleccionMesa : mesasSeleccionadas) {
						idMesaSeleccionadaEvento = obtenerIdMesa(seleccionMesa);
						System.out.println("Verificando disponibilidad de la mesa con ID: " + idMesaSeleccionadaEvento);

						comprobante = new Comprobante();
						mesa = new Mesa();
						reserva = new Reserva();
						servicio = new Servicio();

						recopilarDatosMesaEventoEspecial(mesa);
						recopilarDatosServicioEventoEspecial(servicio);
						recopilarDatosComprobante(comprobante);
						recopilarDatosReservaEventoEspecial(reserva, horaInicio, horaFinal);

						detalle_2 = new DetalleReservaEmpleado(reserva, mesa, servicio, comprobante,
								mesasSeleccionadas, VistaReservaEmpleado.this);

						if (!detalle_2.verificarDisponibilidadMesas()) {
							JOptionPane.showMessageDialog(null,
									"No se puede crear la reserva para la mesa " + idMesaSeleccionadaEvento
											+ " debido a un conflicto de horarios.",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					servicioCreado = detalle_2.crearServicioYRetornarId(servicio);

					for (String seleccionMesa : mesasSeleccionadas) {
						idMesaSeleccionadaEvento = obtenerIdMesa(seleccionMesa);
						System.out.println(
								"Creando la reserva para la mesa seleccionada con ID: " + idMesaSeleccionadaEvento);

						comprobante = new Comprobante();
						mesa = new Mesa();
						reserva = new Reserva();
						servicio = new Servicio();

						recopilarDatosMesaEventoEspecial(mesa);
						recopilarDatosServicioEventoEspecial(servicio);
						recopilarDatosComprobante(comprobante);
						recopilarDatosReservaEventoEspecial(reserva, horaInicio, horaFinal);

						detalle_2 = new DetalleReservaEmpleado(reserva, mesa, servicio, comprobante,
								mesasSeleccionadas, VistaReservaEmpleado.this);

						detalle_2.crearMesas(servicioCreado);
					}
					
					JOptionPane.showMessageDialog(null, "Evento creado con éxito.");
					detalle_2.setVisible(true);

				} else {
					System.out.println("Las horas seleccionadas no son válidas.");
				}
			}
		});
		btnReservarLugar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnReservarLugar.setForeground(Color.BLACK);
		btnReservarLugar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnReservarLugar.setBorder(null);
		btnReservarLugar.setBackground(Color.WHITE);
		btnReservarLugar.setBounds(16, 577, 150, 25);
		btnReservarLugar.setEnabled(false);
		pnlVertical.add(btnReservarLugar);

		btnCrearEventoEspecial = new JButton("Evento Especial");
		btnCrearEventoEspecial.setIcon(new ImageIcon(VistaReservaEmpleado.class.getResource("/Img/icono de eventos especiales.png")));
		btnCrearEventoEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarHoras()) {
					horaInicio = (String) horaInicioComboBox.getSelectedItem();
					horaFinal = (String) horaFinComboBox.getSelectedItem();
					System.out.println("Hora de inicio: " + horaInicio);
					System.out.println("Hora de fin: " + horaFinal);

					mesasSeleccionadas = convertirListModelAArray(listModel);
					if (mesasSeleccionadas.length == 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una mesa para hacer la reserva.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					System.out.println("Mesas seleccionadas: " + Arrays.toString(mesasSeleccionadas));

					for (String seleccionMesa : mesasSeleccionadas) {
						idMesaSeleccionadaEvento = obtenerIdMesa(seleccionMesa);
						System.out.println("Verificando disponibilidad de la mesa con ID: " + idMesaSeleccionadaEvento);

						comprobante = new Comprobante();
						mesa = new Mesa();
						reserva = new Reserva();
						servicio = new Servicio();

						recopilarDatosMesaEventoEspecial(mesa);
						recopilarDatosServicioEventoEspecial(servicio);
						recopilarDatosComprobante(comprobante);
						recopilarDatosReservaEventoEspecial(reserva, horaInicio, horaFinal);

						detalle_2 = new DetalleReservaEmpleado(reserva, mesa, servicio, comprobante,
								mesasSeleccionadas, VistaReservaEmpleado.this);

						if (!detalle_2.verificarDisponibilidadMesas()) {
							JOptionPane.showMessageDialog(null,
									"No se puede crear la reserva para la mesa " + idMesaSeleccionadaEvento
											+ " debido a un conflicto de horarios.",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					servicioCreado = detalle_2.crearServicioYRetornarId(servicio);

					for (String seleccionMesa : mesasSeleccionadas) {
						idMesaSeleccionadaEvento = obtenerIdMesa(seleccionMesa);
						System.out.println(
								"Creando la reserva para la mesa seleccionada con ID: " + idMesaSeleccionadaEvento);

						comprobante = new Comprobante();
						mesa = new Mesa();
						reserva = new Reserva();
						servicio = new Servicio();

						recopilarDatosMesaEventoEspecial(mesa);
						recopilarDatosServicioEventoEspecial(servicio);
						recopilarDatosComprobante(comprobante);
						recopilarDatosReservaEventoEspecial(reserva, horaInicio, horaFinal);

						detalle_2 = new DetalleReservaEmpleado(reserva, mesa, servicio, comprobante,
								mesasSeleccionadas, VistaReservaEmpleado.this);

						detalle_2.crearMesas(servicioCreado);
					}
					
					JOptionPane.showMessageDialog(null, "Evento creado con éxito.");
					detalle_2.setVisible(true);

				} else {
					System.out.println("Las horas seleccionadas no son válidas.");
				}
			}
		});

		btnCrearEventoEspecial.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCrearEventoEspecial.setForeground(Color.BLACK);
		btnCrearEventoEspecial.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnCrearEventoEspecial.setEnabled(true);
		btnCrearEventoEspecial.setBorder(null);
		btnCrearEventoEspecial.setBackground(Color.WHITE);
		btnCrearEventoEspecial.setBounds(16, 644, 150, 25);
		btnCrearEventoEspecial.setEnabled(false);
		pnlVertical.add(btnCrearEventoEspecial);

		// Panel Mapas
		Mapas = new JPanel(new CardLayout());
		Mapas.setBounds(232, 0, 760, 679);
		add(Mapas);

		// Panel Comedor Principal
		JPanel panelComedor = new JPanel();
		Mapas.add(panelComedor, "COMEDOR PRINCIPAL");

		// Imagen Comedor Principal
		JLabel ImagenComedor = new JLabel();
		ImagenComedor.setIcon(new ImageIcon(Reserva.class.getResource("/Img/COMEDOR PRINCIPAL.png")));
		ImagenComedor.setBounds(189, 0, 587, 473);
		panelComedor.add(ImagenComedor);

		// Panel Terraza
		JPanel panelTerraza = new JPanel();
		Mapas.add(panelTerraza, "TERRAZA");

		// Imagen Terraza
		JLabel ImagenTerraza = new JLabel();
		ImagenTerraza.setIcon(new ImageIcon(Reserva.class.getResource("/Img/TERRAZA.jpg")));
		ImagenTerraza.setBounds(-80, 0, 682, 455);
		panelTerraza.add(ImagenTerraza);

		// Panel Bar
		JPanel panelBar = new JPanel();
		Mapas.add(panelBar, "BAR");

		// Imagen Bar
		JLabel ImagenBar = new JLabel();
		ImagenBar.setIcon(new ImageIcon(Reserva.class.getResource("/Img/BAR.jpg")));
		ImagenBar.setBounds(189, 0, 587, 473);
		panelBar.add(ImagenBar);

		// Panel Sala Privada
		JPanel panelSalaPrivada = new JPanel();
		Mapas.add(panelSalaPrivada, "SALA PRIVADA");

		// Imagen Sala Privada
		JLabel ImagenSalaPrivada = new JLabel();
		ImagenSalaPrivada.setIcon(new ImageIcon(Reserva.class.getResource("/Img/SALA PRIVADA.png")));
		ImagenSalaPrivada.setBounds(189, 0, 587, 473);
		panelSalaPrivada.add(ImagenSalaPrivada);

		// Panel Patio
		JPanel panelPatio = new JPanel();
		Mapas.add(panelPatio, "PATIO");

		// Imagen Patio
		JLabel ImagenPatio = new JLabel();
		ImagenPatio.setIcon(new ImageIcon(Reserva.class.getResource("/Img/PATIO.png")));
		ImagenPatio.setBounds(189, 0, 587, 473);
		panelPatio.add(ImagenPatio);

		cambioPanel("COMEDOR PRINCIPAL");
		SeleccionarUbicacion = "COMEDOR PRINCIPAL";
		
		SesionEmpleado s = new SesionEmpleado();
		String rol = empleadoControlador.obtenerRol(s.getEmpleadoActual().getUsuario());
		
		// Deshabilitar botones según el rol
		if (rol.equals("RECEPCION")) {
			eventoEspecialCheckBox.setEnabled(false);
		}
				
	}

	/**
	 * Cambia el panel visible en el contenedor Mapas.
	 *
	 * Este método utiliza un CardLayout para mostrar el panel especificado.
	 *
	 * @param panel El nombre del panel que se desea mostrar.
	 */
	private void cambioPanel(String panel) {
		CardLayout cl = (CardLayout) (Mapas.getLayout());
		cl.show(Mapas, panel);
	}

	/**
	 * Actualiza las horas disponibles en el combo box según la fecha seleccionada.
	 *
	 * Si la fecha seleccionada es hoy, solo se agregarán las horas que son 
	 * mayores o iguales a la hora actual. Si no hay horas disponibles, 
	 * se mostrará un mensaje indicando que no hay servicios disponibles.
	 *
	 * @param fechaSeleccionada La fecha para la que se desean actualizar las horas disponibles.
	 */
	private void actualizarHorasDisponibles(LocalDateTime fechaSeleccionada) {

		horarioComboBox.removeAllItems();
		LocalDate fecha = fechaSeleccionada.toLocalDate();
		LocalDate hoy = LocalDate.now();
		String[] horasDisponibles = new String[] { "08:00 - 10:00", "10:00 - 12:00", "12:00 - 14:00", "20:00 - 22:00",
				"22:00 - 00:00", "00:00 - 02:00" };

		if (fecha.equals(hoy)) {
			LocalTime ahora = LocalTime.now();
			boolean horasAgregadas = false;

			for (String hora : horasDisponibles) {
				String[] partes = hora.split(" - ");
				LocalTime horaInicio = LocalTime.parse(partes[0], DateTimeFormatter.ofPattern("HH:mm"));
				if (horaInicio.isAfter(ahora) || horaInicio.equals(ahora)) {
					horarioComboBox.addItem(hora);
					horasAgregadas = true;
				}
			}

			if (!horasAgregadas) {
				horarioComboBox.addItem("No hay servicios disponibles hoy.");
			}
		} else {

			for (String hora : horasDisponibles) {
				horarioComboBox.addItem(hora);
			}
		}
	}

	/**
	 * Actualiza las horas de inicio disponibles en el combo box según la fecha seleccionada.
	 *
	 * Si la fecha seleccionada es hoy, solo se agregarán las horas que son mayores o iguales a 
	 * la hora actual, incluyendo la medianoche y las dos de la mañana. Si no hay horas disponibles, 
	 * se mostrará un mensaje indicando que no hay horas de inicio disponibles.
	 *
	 * @param fechaSeleccionada La fecha para la que se desean actualizar las horas de inicio disponibles.
	 */
	private void actualizarHorasInicioDisponibles(LocalDateTime fechaSeleccionada) {
	    horaInicioComboBox.removeAllItems();
	    LocalDate fecha = fechaSeleccionada.toLocalDate();
	    LocalDate hoy = LocalDate.now();
	    String[] horasDisponibles = new String[] { "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00",
	                                                "22:00", "00:00", "02:00" };

	    if (fecha.equals(hoy)) {
	        LocalTime ahora = LocalTime.now();
	        boolean horasAgregadas = false;

	        for (String hora : horasDisponibles) {
	            LocalTime horaInicio = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
	            
	            if (horaInicio.isAfter(ahora) || horaInicio.equals(ahora) || horaInicio.equals(LocalTime.MIDNIGHT) || horaInicio.equals(LocalTime.of(2, 0))) {
	                horaInicioComboBox.addItem(hora);
	                horasAgregadas = true;
	            }
	        }

	        if (!horasAgregadas) {
	            horaInicioComboBox.addItem("No hay horas de inicio disponibles hoy.");
	        }
	    } else {
	        for (String hora : horasDisponibles) {
	            horaInicioComboBox.addItem(hora);
	        }
	    }
	}



	/**
	 * Actualiza las horas de fin disponibles en el combo box.
	 * 
	 * Este método llena el combo box de horas de fin con un conjunto predefinido de horas
	 * disponibles. No se aplican restricciones basadas en la fecha o la hora actual,
	 * por lo que se agregarán todas las horas especificadas en la lista.
	 */
	private void actualizarHorasFinDisponibles() {
		horaFinComboBox.removeAllItems();
		String[] horasDisponibles = new String[] { "08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00",
				"22:00", "00:00", "02:00" };

		for (String hora : horasDisponibles) {
			horaFinComboBox.addItem(hora);
		}
	}

	/**
	 * Valida las horas de inicio y fin seleccionadas.
	 * 
	 * Este método verifica que las horas de inicio y fin seleccionadas sean válidas
	 * y cumplan con las reglas establecidas. Si alguna validación falla, se muestra
	 * un mensaje de error y el método retorna false. Si las horas son válidas,
	 * retorna true.
	 * 
	 * @return true si las horas son válidas, false en caso contrario.
	 */
	private boolean validarHoras() {
		String horaInicioSeleccionada = (String) horaInicioComboBox.getSelectedItem();
		String horaFinSeleccionada = (String) horaFinComboBox.getSelectedItem();

		if (horaInicioSeleccionada == null || horaFinSeleccionada == null) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar ambas horas.");
			return false;
		}

		LocalTime horaInicio = LocalTime.parse(horaInicioSeleccionada, DateTimeFormatter.ofPattern("HH:mm"));
		LocalTime horaFin = LocalTime.parse(horaFinSeleccionada, DateTimeFormatter.ofPattern("HH:mm"));

		if (horaFin.equals(horaInicio)) {
			JOptionPane.showMessageDialog(null, "La hora de fin no puede ser igual a la hora de inicio.");
			return false;
		}

		if ((horaFin.equals(LocalTime.parse("00:00")) || horaFin.equals(LocalTime.parse("02:00")))
				&& (horaInicio.isAfter(LocalTime.parse("08:00").minusSeconds(1))
						&& horaInicio.isBefore(LocalTime.parse("22:00").plusSeconds(1)))) {
			return true;
		}

		if ((horaInicio.equals(LocalTime.parse("00:00")) || horaInicio.equals(LocalTime.parse("02:00")))
				&& horaFin.isAfter(LocalTime.parse("02:00"))) {
			JOptionPane.showMessageDialog(null,
					"Si la hora de inicio es 00:00 o 02:00, la hora de fin no puede ser después de las 02:00.");
			return false;
		}

		if (horaFin.isBefore(horaInicio)) {
			JOptionPane.showMessageDialog(null, "La hora de fin no puede ser anterior a la hora de inicio.");
			return false;
		}

		return true;
	}

	/**
	 * Actualiza la lista de mesas disponibles según la ubicación seleccionada.
	 * 
	 * Este método verifica la ubicación seleccionada en el combo box y busca
	 * las mesas disponibles en esa ubicación. Luego, excluye las mesas que
	 * están ocupadas por un servicio en la fecha y horario seleccionados,
	 * y actualiza el combo box de mesas con las mesas disponibles.
	 * 
	 * @return una lista de mesas disponibles en la ubicación seleccionada.
	 */
	private List<Mesa> actualizarMesas() {
		String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();
		List<Mesa> mesasP = new ArrayList<Mesa>();
		if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
			try {

				mesasP = mesaControlador.buscarMesasPorUbicacion(ubicacionSeleccionada);
				List<Integer> mesasO = mesaControlador.buscarMesasOcupadasPorServicio(fechaFormateada,
						(String) horarioComboBox.getSelectedItem());

				comboMesa.removeAllItems();

				for (Mesa mesa : mesasP) {
					if (!mesasO.contains(mesa.getIdMesa())) {
						comboMesa.addItem("Mesa " + mesa.getIdMesa());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al cargar las mesas.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return mesasP;
	}

	/**
	 * Filtra las mesas disponibles según la capacidad seleccionada.
	 * 
	 * Este método verifica la ubicación seleccionada y actualiza la lista
	 * de mesas disponibles en función de la capacidad. Si la capacidad
	 * seleccionada es cero, se mostrarán todas las mesas disponibles en
	 * la ubicación seleccionada. De lo contrario, solo se mostrarán las
	 * mesas que coincidan con la capacidad especificada.
	 * 
	 * @param capacidadSeleccionada la capacidad seleccionada para filtrar las mesas.
	 */
	private void filtrarCapacidad(int capacidadSeleccionada) {
		String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();
		List<Mesa> mesasP;

		if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
			mesasP = actualizarMesas();
			comboMesa.removeAllItems();
			for (Mesa mesa : mesasP) {
				if (capacidadSeleccionada == 0 || mesa.getCapacidad() == capacidadSeleccionada) {
					String item = "Mesa " + mesa.getIdMesa();
					comboMesa.addItem(item);

				}
			}
		}
	}

	/**
	 * Busca el path de la imagen del mapa de la mesa según la ubicación seleccionada.
	 * 
	 * Este método recibe un array de ubicaciones y una ubicación seleccionada,
	 * y retorna el path correspondiente a la imagen de la ubicación elegida. 
	 * Si la ubicación no está reconocida, se lanza una excepción.
	 * 
	 * @param ubicaciones un array de ubicaciones disponibles (no se usa en la lógica actual).
	 * @param SeleccionarUbicacion la ubicación seleccionada por el usuario.
	 * @return el path de la imagen correspondiente a la ubicación seleccionada.	 
	 */
	public String BuscarPath(String[] ubicaciones, String SeleccionarUbicacion) {
		switch (SeleccionarUbicacion) {
		case "COMEDOR PRINCIPAL":
			return "/Img/COMEDOR PRINCIPAL.png";
		case "TERRAZA":
			return "/Img/TERRAZA.jpg";
		case "BAR":
			return "/Img/BAR.jpg";
		case "SALA PRIVADA":
			return "/Img/SALA PRIVADA.png";
		case "PATIO":
			return "/Img/PATIO.png";
		default:
			throw new IllegalArgumentException("Unexpected value: " + SeleccionarUbicacion);
		}
	}

	/**
	 * Obtiene el ID de la mesa a partir de la selección del usuario.
	 * 
	 * Este método toma la cadena de texto que representa la selección de la mesa, 
	 * la divide en partes y extrae el ID de la mesa. Si ocurre un error en 
	 * el formato de la cadena, se maneja la excepción y se muestra un mensaje 
	 * de error al usuario.
	 * 
	 * @param seleccionMesa la cadena que representa la selección de la mesa, 
	 *                      formateada como "Mesa X", donde X es el ID de la mesa.
	 * @return el ID de la mesa como un entero, o -1 si el formato es inválido.
	 */
	private int obtenerIdMesa(String seleccionMesa) {
		try {
			String[] partes = seleccionMesa.split(" ");
			return Integer.parseInt(partes[1]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Formato de mesa inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

	/**
	 * Almacena los datos seleccionados para el objeto de Reserva para un evento especial.
	 * 
	 * Este método recopila la información necesaria para crear o actualizar una reserva 
	 * asociada a un evento especial. Utiliza información de la sesión actual del empleado 
	 * y los datos ingresados en los campos de la interfaz de usuario.
	 * 
	 * @param reserva el objeto Reserva que se desea llenar con los datos.
	 * @param horaInicio la hora de inicio del evento especial.
	 * @param horaFin la hora de fin del evento especial.
	 * @return el objeto Reserva con los datos actualizados.
	 */
	@SuppressWarnings("static-access")
	public Reserva recopilarDatosReservaEventoEspecial(Reserva reserva, String horaInicio, String horaFin) {
		SesionEmpleado s1 = new SesionEmpleado();
		reserva.setIdCliente(s1.getEmpleadoActual().getIdEmpleado());
		reserva.setFecha(fechaFormateada);
		reserva.setHora(horaInicio + " - " + horaFin);
		reserva.setIdMesa(idMesaSeleccionadaEvento);
		reserva.setComentario(CampoComentario.getText());
		reserva.setDispocicionMesa(BuscarPath(ubicaciones, (String) comboUbicaciones.getSelectedItem()));
		reserva.setEstado(0);
		reserva.asignarTemporada();
		return reserva;
	}

	/**
	 * Almacena los datos seleccionados para el objeto de Reserva para una reserva normal.
	 * 
	 * Este método recopila la información necesaria para crear o actualizar una reserva 
	 * asociada a un evento normal. Utiliza información de la sesión actual del empleado 
	 * y los datos ingresados en los campos de la interfaz de usuario.
	 * 
	 * @param reserva el objeto Reserva que se desea llenar con los datos.
	 * @return el objeto Reserva con los datos actualizados.
	 */
	@SuppressWarnings("static-access")
	public Reserva recopilarDatosReservaNormal(Reserva reserva) {

		SesionEmpleado s1 = new SesionEmpleado();
		reserva.setIdCliente(s1.getEmpleadoActual().getIdEmpleado());
		reserva.setFecha(fechaFormateada);
		reserva.setHora((String) horarioComboBox.getSelectedItem());
		reserva.setIdMesa(idMesaSeleccionada);
		reserva.setComentario(CampoComentario.getText());
		reserva.setDispocicionMesa(BuscarPath(ubicaciones, (String) comboUbicaciones.getSelectedItem()));
		reserva.setEstado(0);
		reserva.asignarTemporada();
		return reserva;
	}

	/**
	 * Almacena los datos seleccionados para el objeto de Servicio para un servicio normal.
	 * 
	 * Este método recopila la información necesaria para crear o actualizar un servicio 
	 * asociado a una reserva normal. Obtiene la hora seleccionada de la interfaz de usuario 
	 * y la descompone en horas de inicio y fin.
	 * 
	 * @param servicio el objeto Servicio que se desea llenar con los datos.
	 * @return el objeto Servicio con los datos actualizados.
	 */
	public Servicio recopilarDatosServicioNormal(Servicio servicio) {

		horaSeleccionada = (String) horarioComboBox.getSelectedItem();
		String[] partes = horaSeleccionada.split(" - ");
		String horaI = partes[0];
		String horaF = partes[1];
		servicio.setFecha(fechaFormateada);
		servicio.setHoraInicio(horaI);
		servicio.setHoraFin(horaF);
		servicio.setEventoPrivado(0);
		return servicio;
	}

	/**
	 * Almacena los datos seleccionados para el objeto de Servicio para un servicio de evento especial.
	 * 
	 * Este método recopila la información necesaria para crear o actualizar un servicio 
	 * asociado a una reserva de evento especial. Se establece la fecha, la hora de inicio 
	 * y la hora de fin, así como el estado de evento privado.
	 * 
	 * @param servicio el objeto Servicio que se desea llenar con los datos.
	 * @return el objeto Servicio con los datos actualizados.
	 */
	public Servicio recopilarDatosServicioEventoEspecial(Servicio servicio) {
		servicio.setFecha(fechaFormateada);
		servicio.setHoraInicio(horaInicio);
		servicio.setHoraFin(horaFinal);
		servicio.setEventoPrivado(1);
		return servicio;
	}

	/**
	 * Almacena los datos seleccionados para el objeto de Comprobante.
	 * 
	 * Este método recopila la información necesaria para crear un comprobante, 
	 * estableciendo la fecha y hora actuales, y asignando un importe fijo.
	 * 
	 * @param comprobante el objeto Comprobante que se desea llenar con los datos.
	 * @return el objeto Comprobante con los datos actualizados.
	 */
	public Comprobante recopilarDatosComprobante(Comprobante comprobante) {
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaActualFormateada = fechaActual.format(formatoFecha);
		LocalTime horaActual = LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaActualFormateada = horaActual.format(formatoHora);

		comprobante.setFecha(fechaActualFormateada);
		comprobante.setHora(horaActualFormateada);
		comprobante.setImporte(500);
		return comprobante;
	}

	/**
	 * Almacena los datos seleccionados para el objeto de Mesa.
	 * 
	 * Este método actualiza el objeto Mesa con la identificación, 
	 * ubicación y capacidad seleccionada. Si no se ha especificado 
	 * una capacidad, se busca la capacidad asociada a la mesa a través
	 * del controlador correspondiente.
	 *
	 * @param mesa el objeto Mesa que se desea llenar con los datos.
	 * @return el objeto Mesa con los datos actualizados.
	 */
	public Mesa recopilarDatosMesaNormal(Mesa mesa) {
		mesa.setIdMesa(idMesaSeleccionada);
		mesa.setUbicacion(SeleccionarUbicacion);

		if (capacidadSeleccionada == 0) {
			mesa.setCapacidad(mesaControlador.filtrarCapacidad(idMesaSeleccionada));
		} else {
			mesa.setCapacidad(capacidadSeleccionada);
		}
		return mesa;
	}
	
	/**
	 * Almacena los datos seleccionados para el objeto de Mesa relacionado con un evento especial.
	 * 
	 * Este método actualiza el objeto Mesa con la identificación, 
	 * ubicación y capacidad seleccionada. Si no se ha especificado 
	 * una capacidad, se busca la capacidad asociada a la mesa a través 
	 * del controlador correspondiente.
	 *
	 * @param mesa el objeto Mesa que se desea llenar con los datos.
	 * @return el objeto Mesa con los datos actualizados.
	 */
		public Mesa recopilarDatosMesaEventoEspecial(Mesa mesa) {
			mesa.setIdMesa(idMesaSeleccionadaEvento);
			mesa.setUbicacion(SeleccionarUbicacion);

			if (capacidadSeleccionada == 0) {
				mesa.setCapacidad(mesaControlador.filtrarCapacidad(idMesaSeleccionada));
			} else {
				mesa.setCapacidad(capacidadSeleccionada);
			}
			return mesa;
		}

		/**
		 * Almacena los datos seleccionados para el objeto de Mesa basado en el número de mesa proporcionado.
		 * 
		 * Este método actualiza el objeto Mesa con la identificación, 
		 * ubicación y capacidad seleccionada. Si no se ha especificado 
		 * una capacidad, se busca la capacidad asociada a la mesa a través 
		 * del controlador correspondiente.
		 *
		 * @param mesasSeleccionadas un arreglo de cadenas que contiene las mesas seleccionadas.
		 * @param id el identificador de la mesa que se desea almacenar.
		 * @return el objeto Mesa con los datos actualizados.
		 */

	public Mesa recopilarDatosMesaPorNumero(String mesasSeleccionadas[], int id) {
		Mesa mesa = new Mesa();

		mesa.setIdMesa(id);
		mesa.setUbicacion(SeleccionarUbicacion);

		if (capacidadSeleccionada == 0) {
			mesa.setCapacidad(mesaControlador.filtrarCapacidad(id));
		} else {
			mesa.setCapacidad(capacidadSeleccionada);
		}

		return mesa;
	}

	/**
	 * Convierte un objeto DefaultListModel a un arreglo de String.
	 * 
	 * Este método recorre el modelo de lista proporcionado y almacena
	 * cada elemento en un arreglo de tipo String, que luego se devuelve.
	 *
	 * @param listModel el DefaultListModel que se desea convertir.
	 * @return un arreglo de String que contiene los elementos del DefaultListModel.
	 */
	public String[] convertirListModelAArray(DefaultListModel<String> listModel) {
		String[] arrayMesas = new String[listModel.getSize()];

		for (int i = 0; i < listModel.getSize(); i++) {
			arrayMesas[i] = listModel.getElementAt(i);
		}
		return arrayMesas;
	}
	
	/**
	 * Convierte una lista de objetos Mesa a un arreglo de String.
	 *
	 * Este método recorre la lista de mesas proporcionada y construye
	 * un arreglo de String donde cada elemento representa una mesa
	 * en el formato "Mesa X", donde X es el identificador de la mesa.
	 *
	 * @param listaMesas la lista de objetos Mesa a convertir.
	 * @return un arreglo de String que contiene los identificadores de las mesas.
	 */
	public String[] convertirListAMesArray(List<Mesa> listaMesas) {
	    String[] arrayMesas = new String[listaMesas.size()];

	    for (int i = 0; i < listaMesas.size(); i++) {
	        arrayMesas[i] = "Mesa " + String.valueOf(listaMesas.get(i).getIdMesa());
	    }
	    return arrayMesas;
	}


	/**
	 * Resetea todos los campos del formulario a sus valores iniciales.
	 *
	 * Este método restablece los campos del formulario, incluyendo
	 * el JDateChooser, JComboBoxes y el campo de texto de comentarios.
	 * También limpia la selección de la JList y establece el
	 * identificador de la mesa seleccionada a -1.
	 */
		public void resetearCampos() {
		    if (calendario2 != null) {
		        calendario2.setDate(new Date());
		        calendario2.repaint();
		    } else {
		        System.out.println("El JDateChooser 'calendario2' no está inicializado.");
		    }

		    if (horarioComboBox != null && horarioComboBox.getItemCount() > 0) {
		        horarioComboBox.setSelectedIndex(0);
		        horarioComboBox.repaint();
		    } else {
		        System.out.println("El JComboBox 'comboHora' no está inicializado o no tiene elementos.");
		    }

		    if (comboUbicaciones != null && comboUbicaciones.getItemCount() > 0) {
		        comboUbicaciones.setSelectedIndex(0);
		        comboUbicaciones.repaint();
		    } else {
		        System.out.println("El JComboBox 'comboUbicaciones' no está inicializado o no tiene elementos.");
		    }

		    if (comboCapacidad != null) {
		        comboCapacidad.setSelectedIndex(0);
		        comboCapacidad.repaint();
		    } else {
		        System.out.println("El JComboBox 'comboCapacidad' no está inicializado.");
		    }

		    if (CampoComentario != null) {
		        CampoComentario.setText("");
		        CampoComentario.repaint();
		    } else {
		        System.out.println("El campo de texto 'CampoComentario' no está inicializado.");
		    }

		    if (horaInicioComboBox != null && horaInicioComboBox.getItemCount() > 0) {
		        horaInicioComboBox.setSelectedIndex(0);
		        horaInicioComboBox.repaint();
		    } else {
		        System.out.println("El JComboBox 'horaInicioComboBox' no está inicializado o no tiene elementos.");
		    }

		    if (listModel != null) {
		        listModel.clear();
		    }
		    if (listaMesas != null) {
		        listaMesas.clearSelection();
		        listaMesas.repaint();
		    } else {
		        System.out.println("La JList 'listaMesas' no está inicializada.");
		    }

		    idMesaSeleccionada = -1;
		}
}

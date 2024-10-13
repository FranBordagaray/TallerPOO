package Vista.Empleado;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import Controlador.MesaControlador;
import Modelo.Complementos.Comprobante;
import Modelo.Complementos.Mesa;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;
import Modelo.Empleado.SesionEmpleado;
import Vista.Cliente.DetalleReserva;
import Vista.Cliente.UsarTarjeta;

import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class VistaReservaEmpleado extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel Mapas;
	private JDateChooser calendario2;
	private JComboBox<String> comboHora;
	JButton btnSiguiente;
	private LocalDate hoy;
	private LocalDate unAnoFuturo;
	private JComboBox<String> comboUbicaciones;
	private JComboBox<String> comboMesa;
	private JComboBox<Integer> comboCapacidad;
	private MesaControlador mesaControlador;
	private String SeleccionarUbicacion;
	private int capacidadSeleccionada;
	private String fechaFormateada;
	private String horaSeleccionada;
	private JTextArea CampoComentario;
	private String[] ubicaciones;
	private Reserva reserva;
	private Mesa mesa;
	private int idMesaSeleccionada;
	private Comprobante comprobante;
	private Servicio servicio;
	private DetalleReserva detalle;

	public VistaReservaEmpleado() {

		// Configuracion del panel
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));

		mesaControlador = new MesaControlador();

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
			actualizarHorasDisponibles(fechaSeleccionada);

			// Formatear la fecha
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
		comboHora = new JComboBox<String>();
		comboHora.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboHora.setBorder(null);
		comboHora.setForeground(Color.BLACK);
		comboHora.setBackground(Color.WHITE);
		comboHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboHora.setBounds(10, 189, 87, 25);
		comboHora.addItem("08:00 - 10:00");
		comboHora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarMesas();
			}
		});
		pnlVertical.add(comboHora);

		// Etiqueta de Capacidad
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setForeground(Color.BLACK);
		lblCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblCapacidad.setBackground(Color.WHITE);
		lblCapacidad.setBounds(16, 224, 200, 20);
		pnlVertical.add(lblCapacidad);

		// Desplegable Capacidad
		Integer[] capacidades = { 0, 2, 4, 6 };
		comboCapacidad = new JComboBox<>(capacidades);
		comboCapacidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboCapacidad.setForeground(Color.BLACK);
		comboCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboCapacidad.setBorder(null);
		comboCapacidad.setBackground(Color.WHITE);
		comboCapacidad.setBounds(41, 243, 150, 25);
		pnlVertical.add(comboCapacidad);
		comboCapacidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				capacidadSeleccionada = (int) comboCapacidad.getSelectedItem();
				filtrarCapacidad(capacidadSeleccionada);
			}
		});

		// ComboBox hora para eventos especiales
		JComboBox<String> comboHora_1 = new JComboBox<String>();
		comboHora_1.setForeground(Color.BLACK);
		comboHora_1.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboHora_1.setBorder(null);
		comboHora_1.setBackground(Color.WHITE);
		comboHora_1.setBounds(129, 189, 87, 25);
		comboHora_1.setEnabled(false);
		pnlVertical.add(comboHora_1);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();

		// Crear el JList
		JList<String> listMesas = new JList<>(listModel);
		listMesas.setBounds(113, 316, 103, 100); // Ajusta la posición y tamaño según sea necesario
		pnlVertical.add(listMesas);

		// Crear el botón "Agregar"
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Roboto Light", Font.BOLD, 10));
		btnAgregar.setBounds(16, 351, 87, 25); // Ajusta la posición y tamaño según sea necesario
		pnlVertical.add(btnAgregar);
		btnAgregar.setEnabled(false);

		// Acción para el botón "Agregar"
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String seleccionMesa = (String) comboMesa.getSelectedItem();
		        
		        // Verificar si la mesa ya fue añadida
		        if (seleccionMesa != null && !seleccionMesa.isEmpty()) {
		            if (!listModel.contains(seleccionMesa)) {
		                // Si no está en la lista, agregar la mesa seleccionada
		                listModel.addElement(seleccionMesa);
		            } else {
		                // Si ya está en la lista, mostrar un mensaje
		                JOptionPane.showMessageDialog(null, "La mesa ya ha sido añadida.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona una mesa.");
		        }
		    }});

		// JCheckBox para habilitar eventos especiales
		JCheckBox chckbxNewCheckBox = new JCheckBox("Evento Especial");
		chckbxNewCheckBox.setBounds(11, 6, 150, 21);
		pnlVertical.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					comboHora_1.setEnabled(true);
					btnAgregar.setEnabled(true);

				} else {
					comboHora_1.setEnabled(false);
					btnAgregar.setEnabled(false);
				}
			}
		});

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("Mesas");
		lblMesa.setBorder(null);
		lblMesa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesa.setForeground(Color.BLACK);
		lblMesa.setBackground(Color.WHITE);
		lblMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblMesa.setBounds(16, 286, 200, 20);
		pnlVertical.add(lblMesa);

		// Desplegable Mesa
		comboMesa = new JComboBox<>();
		comboMesa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboMesa.setForeground(Color.BLACK);
		comboMesa.setBackground(Color.WHITE);
		comboMesa.setBorder(null);
		comboMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboMesa.setBounds(16, 316, 87, 25);
		pnlVertical.add(comboMesa);

		// Inicializa el modelo y el JList para mostrar mesas seleccionadas
		DefaultListModel mesasModel = new DefaultListModel<>();

		comboMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String seleccionMesa = (String) comboMesa.getSelectedItem();
				if (seleccionMesa != null && !seleccionMesa.isEmpty()) {
					if (chckbxNewCheckBox.isSelected()) {
						if (!mesasModel.contains(seleccionMesa)) {
							mesasModel.addElement(seleccionMesa);
						}
					}
				}
			}
		});

		// Etiqueta Comentario de la Reserva
		JLabel TituloComentario = new JLabel("Comentario");
		TituloComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		TituloComentario.setHorizontalAlignment(SwingConstants.CENTER);
		TituloComentario.setBackground(Color.WHITE);
		TituloComentario.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		TituloComentario.setBounds(16, 421, 200, 20);
		pnlVertical.add(TituloComentario);

		// Campo de Texto Comentario de la Reserva
		CampoComentario = new JTextArea();
		CampoComentario.setFont(new Font("Roboto Light", Font.PLAIN, 13));
		CampoComentario.setForeground(Color.BLACK);
		CampoComentario.setBorder(null);
		CampoComentario.setBackground(Color.WHITE);
		CampoComentario.setBounds(10, 451, 210, 113);
		CampoComentario.setLineWrap(true);
		CampoComentario.setWrapStyleWord(true);
		pnlVertical.add(CampoComentario);

		// Boton Siguiente Paso
		btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                mesa = new Mesa();
                reserva = new Reserva();
                servicio = new Servicio();
                recopilarDatosReserva(reserva);
                recopilarDatosMesa(mesa);
                recopilarDatosServicio(servicio);
                detalle = new DetalleReserva(reserva, mesa, servicio, null);
                detalle.setVisible(true);
            }
        });

        btnSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSiguiente.setBackground(new Color(255, 0, 0));
                btnSiguiente.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSiguiente.setBackground(Color.WHITE);
                btnSiguiente.setForeground(Color.BLACK);
            }
        });
        btnSiguiente.setEnabled(true);
        btnSiguiente.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSiguiente.setBorder(null);
        btnSiguiente.setForeground(Color.BLACK);
        btnSiguiente.setBackground(Color.WHITE);
        btnSiguiente.setFont(new Font("Roboto Light", Font.BOLD, 16));
        btnSiguiente.setBounds(41, 630, 150, 25);
        pnlVertical.add(btnSiguiente);
        
        JButton btnBloquear = new JButton("Bloquear");
        btnBloquear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnBloquear.setFont(new Font("Roboto Light", Font.BOLD, 10));
        btnBloquear.setEnabled(true);
        btnBloquear.setBounds(16, 391, 87, 25);
        pnlVertical.add(btnBloquear);

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

		// Asegura que se inicie con el panel de "Comedor Principal"
		cambioPanel("COMEDOR PRINCIPAL");
		// Si no se selecciona ninguna ubicacion setea comedor principal
		SeleccionarUbicacion = "COMEDOR PRINCIPAL";

		// Llama a la funcion actualizarMesas para actualizar filtros de capacidad
		// actualizarMesas();

	}

	// Cambia el panel visible
	private void cambioPanel(String panel) {
		CardLayout cl = (CardLayout) (Mapas.getLayout());
		cl.show(Mapas, panel);
	}

	// Funcion actualiza horas disponibles
	private void actualizarHorasDisponibles(LocalDateTime fechaSeleccionada) {

		comboHora.removeAllItems();
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
					comboHora.addItem(hora);
					horasAgregadas = true;
				}
			}

			if (!horasAgregadas) {
				comboHora.addItem("No hay servicios disponibles hoy.");
			}
		} else {

			for (String hora : horasDisponibles) {
				comboHora.addItem(hora);
			}
		}
	}

	// Funcion para actualizar Mesas
	private List<Mesa> actualizarMesas() {
		String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();
		List<Mesa> mesasP = new ArrayList<Mesa>();
		if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
			try {

				mesasP = mesaControlador.buscarMesasPorUbicacion(ubicacionSeleccionada);
				List<Integer> mesasO = mesaControlador.buscarMesasOcupadasPorServicio(fechaFormateada,
						(String) comboHora.getSelectedItem());

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

	// Funcion para filtrar por Capacidad
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

	// Metodo encontrar el path del mapa de la mesa
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

	// Metodo para obtener la mesa
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

	// Metodo que Almacena los datos seleccionados para el objeto de Reserva
	@SuppressWarnings("static-access")
	public Reserva recopilarDatosReserva(Reserva reserva) {

		SesionEmpleado s1 = new SesionEmpleado();
		reserva.setIdCliente(s1.getEmpleadoActual().getIdEmpleado());
		reserva.setFecha(fechaFormateada);
		reserva.setHora((String) comboHora.getSelectedItem());
		reserva.setIdMesa(idMesaSeleccionada);
		reserva.setComentario(CampoComentario.getText());
		reserva.setDispocicionMesa(BuscarPath(ubicaciones, (String) comboUbicaciones.getSelectedItem()));
		reserva.setEstado(0);
		reserva.asignarTemporada();
		return reserva;
	}

	// Metodo que Almacena los datos seleccionados para el objeto de Servicio
	public Servicio recopilarDatosServicio(Servicio servicio) {

		horaSeleccionada = (String) comboHora.getSelectedItem();
		String[] partes = horaSeleccionada.split(" - ");
		String horaI = partes[0];
		String horaF = partes[1];
		servicio.setFecha(fechaFormateada);
		servicio.setHoraInicio(horaI);
		servicio.setHoraFin(horaF);
		servicio.setEventoPrivado(0);
		return servicio;
	}
	
	// Metodo que Almacena los datos seleccionados para el objeto de Comprobante
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

	// Metodo que Almacena los datos seleccionados para el objeto de Mesa
	public Mesa recopilarDatosMesa(Mesa mesa) {

		mesa.setIdMesa(idMesaSeleccionada);
		mesa.setUbicacion(SeleccionarUbicacion);
		if (capacidadSeleccionada == 0) {
			mesa.setCapacidad(mesaControlador.filtrarCapacidad(idMesaSeleccionada));
		} else {
			mesa.setCapacidad(capacidadSeleccionada);
		}
		return mesa;
	}
}
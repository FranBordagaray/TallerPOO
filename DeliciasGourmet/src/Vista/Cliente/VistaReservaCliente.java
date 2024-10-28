package Vista.Cliente;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import Controlador.MesaControlador;
import Controlador.ServicioControlador;
import Modelo.Cliente.SesionCliente;
import Modelo.Complementos.Comprobante;
import Modelo.Complementos.Mesa;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;

import com.toedter.calendar.JDateChooser;
/**
 * La clase VistaReservaCliente es un JPanel que representa la 
 * interfaz de usuario para la gestión de reservas de clientes. 
 * Incluye componentes como un calendario para seleccionar fechas, 
 * combo boxes para elegir la hora, ubicación, mesa y capacidad, 
 * así como un área de texto para comentarios. Además, contiene 
 * controladores para gestionar las mesas y servicios disponibles, 
 * y variables para almacenar información relacionada con la reserva, 
 * como la fecha, hora, y comprobante. 
 * 
 * La clase permite al usuario seleccionar opciones de reserva 
 * y proceder con el proceso de confirmación.
 */
public class VistaReservaCliente extends JPanel {

    private static final long serialVersionUID = 1L;
   
    /**
     * Panel que contiene los mapas y la interfaz para la selección de reservas.
     */
    private JPanel Mapas;

    /**
     * Selector de fecha que permite al usuario elegir una fecha para la reserva.
     */
    private JDateChooser calendario2;

    /**
     * ComboBox que permite seleccionar la hora de la reserva.
     */
    private JComboBox<String> comboHora;

    /**
     * Botón que avanza a la siguiente etapa del proceso de reserva.
     */
    JButton btnSiguiente;

    /**
     * Fecha actual, utilizada para validar reservas y mostrar información relevante.
     */
    private LocalDate hoy;

    /**
     * Fecha que representa un año en el futuro, utilizada para restricciones de reserva.
     */
    private LocalDate unAnoFuturo;

    /**
     * ComboBox que permite seleccionar la ubicación de la reserva.
     */
    private JComboBox<String> comboUbicaciones;

    /**
     * ComboBox que permite seleccionar la mesa disponible para la reserva.
     */
    private JComboBox<String> comboMesa;

    /**
     * ComboBox que permite seleccionar la capacidad de la mesa reservada.
     */
    private JComboBox<Integer> comboCapacidad;

    /**
     * Controlador encargado de gestionar las operaciones relacionadas con las mesas.
     */
    private MesaControlador mesaControlador;

    /**
     * Controlador encargado de gestionar las operaciones relacionadas con los servicios.
     */
    private ServicioControlador servicioControlador;

    /**
     * Variable que almacena la ubicación seleccionada por el usuario.
     */
    private String SeleccionarUbicacion;

    /**
     * Capacidad seleccionada por el usuario para la mesa reservada.
     */
    private int capacidadSeleccionada;

    /**
     * Fecha formateada como cadena, utilizada para mostrar o procesar reservas.
     */
    private String fechaFormateada;

    /**
     * Hora seleccionada por el usuario para la reserva.
     */
    private String horaSeleccionada;

    /**
     * Area de texto donde el usuario puede ingresar comentarios adicionales sobre la reserva.
     */
    private JTextArea CampoComentario;

    /**
     * Array que contiene las ubicaciones disponibles para las reservas.
     */
    private String[] ubicaciones;

    /**
     * Objeto que representa una reserva realizada por un cliente.
     */
    private Reserva reserva;

    /**
     * Objeto que representa una mesa en el sistema.
     */
    private Mesa mesa;

    /**
     * Objeto que representa un comprobante de la reserva.
     */
    private Comprobante comprobante;

    /**
     * ID de la mesa seleccionada por el usuario para la reserva.
     */
    private int idMesaSeleccionada;

    /**
     * Objeto que representa un servicio disponible en el sistema.
     */
    private Servicio servicio;

    /**
     * Objeto que contiene los detalles de la reserva del cliente.
     */
    private DetalleReservaCliente detalle;
   
    /**
     * Constructor de la clase VistaReservaCliente.
     *
     * Este constructor inicializa una nueva instancia de la clase 
     * VistaReservaCliente, que representa la interfaz gráfica para 
     * gestionar las reservas de un cliente. Esta vista permite al 
     * usuario ver, modificar y cancelar sus reservas, así como 
     * acceder a información adicional relacionada con su cuenta 
     * y las reservas realizadas.
     */
    public VistaReservaCliente() {

        // Configuracion del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));

        mesaControlador = new MesaControlador();
        servicioControlador = new ServicioControlador();

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
        lblUbicacion.setBounds(16, 25, 200, 20);
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
        comboUbicaciones.setBounds(16, 45, 200, 25);
        pnlVertical.add(comboUbicaciones);

        // Etiqueta Fecha
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setBackground(Color.WHITE);
        lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFecha.setBounds(16, 85, 200, 20);
        pnlVertical.add(lblFecha);

        // JCalendar para la fecha
        calendario2 = new JDateChooser();
        calendario2.setBounds(16, 105, 200, 25);
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
        lblHora.setBounds(16, 145, 200, 20);
        pnlVertical.add(lblHora);

        // Desplegable Hora
        comboHora = new JComboBox<String>();
        comboHora.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboHora.setBorder(null);
        comboHora.setForeground(Color.BLACK);
        comboHora.setBackground(Color.WHITE);
        comboHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        comboHora.setBounds(41, 165, 150, 25);
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
        lblCapacidad.setBounds(16, 205, 200, 20);
        pnlVertical.add(lblCapacidad);

        // Desplegable Capacidad
        Integer[] capacidades = { 0, 2, 4, 6 };
        comboCapacidad = new JComboBox<>(capacidades);
        comboCapacidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboCapacidad.setForeground(Color.BLACK);
        comboCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        comboCapacidad.setBorder(null);
        comboCapacidad.setBackground(Color.WHITE);
        comboCapacidad.setBounds(41, 225, 150, 25);
        pnlVertical.add(comboCapacidad);
        comboCapacidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capacidadSeleccionada = (int) comboCapacidad.getSelectedItem();
                filtrarCapacidad(capacidadSeleccionada);
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
        lblMesa.setBounds(16, 265, 200, 20);
        pnlVertical.add(lblMesa);

        // Desplegable Mesa
        comboMesa = new JComboBox<>();
        comboMesa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboMesa.setForeground(Color.BLACK);
        comboMesa.setBackground(Color.WHITE);
        comboMesa.setBorder(null);
        comboMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        comboMesa.setBounds(41, 285, 150, 25);
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
        TituloComentario.setBounds(16, 330, 200, 20);
        pnlVertical.add(TituloComentario);

        // Campo de Texto Comentario de la Reserva
        CampoComentario = new JTextArea();
        CampoComentario.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        CampoComentario.setForeground(Color.BLACK);
        CampoComentario.setBorder(null);
        CampoComentario.setBackground(Color.WHITE);
        CampoComentario.setBounds(11, 350, 210, 180);
        CampoComentario.setLineWrap(true);
        CampoComentario.setWrapStyleWord(true);
        pnlVertical.add(CampoComentario);

        // Boton agregar Tarjeta
        JButton btnAgregarTarjeta = new JButton("Agregar Tarjeta");
        btnAgregarTarjeta.setIcon(new ImageIcon(VistaReservaCliente.class.getResource("/Img/icono tarjeta.png")));
        btnAgregarTarjeta.setSelectedIcon(new ImageIcon(VistaReservaCliente.class.getResource("/Img/icono tarjeta.png")));
        btnAgregarTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                UsarTarjeta tarjeta = new UsarTarjeta(VistaReservaCliente.this);
                tarjeta.setVisible(true);

            }
        });
        btnAgregarTarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAgregarTarjeta.setBackground(new Color(255, 0, 0));
                btnAgregarTarjeta.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAgregarTarjeta.setBackground(Color.WHITE);
                btnAgregarTarjeta.setForeground(Color.BLACK);
            }
        });
        btnAgregarTarjeta.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAgregarTarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAgregarTarjeta.setBorder(null);
        btnAgregarTarjeta.setForeground(Color.BLACK);
        btnAgregarTarjeta.setBackground(Color.WHITE);
        btnAgregarTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 16));
        btnAgregarTarjeta.setBounds(41, 590, 150, 25);
        pnlVertical.add(btnAgregarTarjeta);

        // Boton Siguiente P
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setIcon(new ImageIcon(VistaReservaCliente.class.getResource("/Img/icono de siguiente.png")));
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	comprobante = new Comprobante();
                mesa = new Mesa();
                reserva = new Reserva();
                servicio = new Servicio();
                recopilarDatosReserva(reserva);
                recopilarDatosMesa(mesa);
                recopilarDatosServicio(servicio);
                recopilarDatosComprobante(comprobante);
                detalle = new DetalleReservaCliente(reserva, mesa, servicio, comprobante, VistaReservaCliente.this);
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
        btnSiguiente.setEnabled(false);
        btnSiguiente.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSiguiente.setBorder(null);
        btnSiguiente.setForeground(Color.BLACK);
        btnSiguiente.setBackground(Color.WHITE);
        btnSiguiente.setFont(new Font("Roboto Light", Font.BOLD, 16));
        btnSiguiente.setBounds(41, 630, 150, 25);
        pnlVertical.add(btnSiguiente);

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

    }

    /**
     * Cambia el panel visible dentro del contenedor de Mapas.
     *
     * @param panel Nombre del panel a mostrar.
     */
    private void cambioPanel(String panel) {
        CardLayout cl = (CardLayout) (Mapas.getLayout());
        cl.show(Mapas, panel);
    }

    /**
     * Actualiza el JComboBox de horas disponibles según la fecha seleccionada.
     *
     * @param fechaSeleccionada Fecha para la cual se desea actualizar las horas disponibles.
     */
    private void actualizarHorasDisponibles(LocalDateTime fechaSeleccionada) {

        comboHora.removeAllItems();
        LocalDate fecha = fechaSeleccionada.toLocalDate();
        LocalDate hoy = LocalDate.now();
        String[] horasDisponibles = new String[] {
                "08:00 - 10:00",
                "10:00 - 12:00",
                "12:00 - 14:00",
                "20:00 - 22:00",
                "22:00 - 00:00",
                "00:00 - 02:00"
        };

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

    /**
     * Actualiza la lista de mesas disponibles según la ubicación seleccionada y la disponibilidad.
     *
     * @return Lista de mesas que están disponibles en la ubicación seleccionada.
     */
    private List<Mesa> actualizarMesas() {
        String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();
        List<Mesa> mesasP = new ArrayList<Mesa>();
        if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
            try {
            	List<Mesa> mesasB = verificarDisponibilidadMesas(mesasP);
            	
                mesasP = mesaControlador.buscarMesasPorUbicacion(ubicacionSeleccionada);
                List<Integer> mesasO = mesaControlador.buscarMesasOcupadasPorServicio(fechaFormateada, (String) comboHora.getSelectedItem());
                
                
                //List<Integer> mesasB 
                comboMesa.removeAllItems();

                for (Mesa mesa : mesasB) {
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
     * Filtra las mesas disponibles según la capacidad seleccionada y la ubicación elegida.
     *
     * @param capacidadSeleccionada Capacidad de la mesa seleccionada por el usuario.
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
     * Encuentra la ruta del mapa de la mesa según la ubicación seleccionada.
     *
     * @param ubicaciones Un arreglo de ubicaciones (actualmente no se utiliza).
     * @param seleccionarUbicacion La ubicación seleccionada por el usuario.
     * @return La ruta de la imagen correspondiente a la ubicación.
     * @throws IllegalArgumentException Si la ubicación seleccionada no es válida.
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
     * Obtiene el ID de la mesa a partir de una cadena de texto que representa la selección de la mesa.
     *
     * @param seleccionMesa La cadena de texto que contiene información sobre la mesa seleccionada.
     * @return El ID de la mesa si se extrae correctamente, o -1 si ocurre un error.
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
     * Almacena los datos seleccionados para el objeto de Reserva.
     *
     * @param reserva El objeto Reserva que se va a actualizar con los datos seleccionados.
     * @return El objeto Reserva con los datos actualizados.
     */
    @SuppressWarnings("static-access")
    public Reserva recopilarDatosReserva(Reserva reserva) {

        SesionCliente s1 = new SesionCliente();
        reserva.setIdCliente(s1.getClienteActual().getIdCliente());
        reserva.setFecha(fechaFormateada);
        reserva.setHora((String) comboHora.getSelectedItem());
        reserva.setIdMesa(idMesaSeleccionada);
        reserva.setComentario(CampoComentario.getText());
        reserva.setDispocicionMesa(BuscarPath(ubicaciones, (String) comboUbicaciones.getSelectedItem()));
        reserva.setEstado(0);
        reserva.asignarTemporada();
        return reserva;
    }

    /**
     * Almacena los datos seleccionados para el objeto de Servicio.
     *
     * @param servicio El objeto Servicio que se va a actualizar con los datos seleccionados.
     * @return El objeto Servicio con los datos actualizados.
     */
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
    
    /**
     * Almacena los datos seleccionados para el objeto de Comprobante.
     *
     * @param comprobante El objeto Comprobante que se va a actualizar con los datos seleccionados.
     * @return El objeto Comprobante con los datos actualizados.
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
        comprobante.setImporte(50);
        return comprobante;
    }

    /**
     * Almacena los datos seleccionados para el objeto de Mesa.
     *
     * @param mesa El objeto Mesa que se va a actualizar con los datos seleccionados.
     * @return El objeto Mesa con los datos actualizados.
     */
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
    
    /**
     * Habilita el botón 'btnSiguiente' según el valor proporcionado.
     *
     * @param valor true para habilitar el botón, false para deshabilitarlo.
     */
    public void habilitarBoton(boolean valor) {
    	btnSiguiente.setEnabled(valor); 
    }
    
    /**
     * Resetea todos los campos de entrada a sus valores predeterminados.
     */
    public void resetearCampos() {
    	System.out.println("Este metodo se esta ejecutando");
        if (calendario2 != null) {
            calendario2.setDate(new Date());
            calendario2.repaint(); 
        } else {
            System.out.println("El JDateChooser 'calendario2' no está inicializado.");
        }

        if (comboHora != null && comboHora.getItemCount() > 0) {
            comboHora.setSelectedIndex(0);
            comboHora.repaint();
        } else {
            System.out.println("El JComboBox 'comboHora' no está inicializado o no tiene elementos.");
        }

        if (comboUbicaciones != null && comboUbicaciones.getItemCount() > 0) {
            comboUbicaciones.setSelectedIndex(0);
            comboUbicaciones.repaint();
        } else {
            System.out.println("El JComboBox 'comboUbicaciones' no está inicializado o no tiene elementos.");
        }

        if (CampoComentario != null) {
            CampoComentario.setText("");
            CampoComentario.repaint();
        } else {
            System.out.println("El campo de texto 'CampoComentario' no está inicializado.");
        }

        if (comboMesa != null && comboMesa.getItemCount() > 0) {
            comboMesa.setSelectedIndex(0);
            comboMesa.repaint();
        } else {
            System.out.println("El JComboBox 'comboMesa' no está inicializado o no tiene elementos.");
        }

        if (comboCapacidad != null) {
            comboCapacidad.setSelectedIndex(0);
            comboCapacidad.repaint();
        } else {
            System.out.println("El JComboBox 'comboCapacidad' no está inicializado.");
        }
        
        idMesaSeleccionada = 1;
    }
    
    /**
     * Verifica si hay solapamiento entre dos servicios en base a sus fechas y horarios.
     *
     * @param fecha1     Fecha del primer servicio.
     * @param horaInicio1 Hora de inicio del primer servicio.
     * @param horaFin1   Hora de fin del primer servicio.
     * @param fecha2     Fecha del segundo servicio.
     * @param horaInicio2 Hora de inicio del segundo servicio.
     * @param horaFin2   Hora de fin del segundo servicio.
     * @return true si hay solapamiento entre los dos servicios; false en caso contrario.
     */
 	private boolean verificarSolapamiento(String fecha1, String horaInicio1, String horaFin1, String fecha2,
 			String horaInicio2, String horaFin2) {
 				
 	    if (!fecha1.equals(fecha2)) {
 	        return false;
 	    }
 	    	
 	    LocalTime inicio1 = LocalTime.parse(horaInicio1);
 	    LocalTime fin1 = LocalTime.parse(horaFin1);
 	    LocalTime inicio2 = LocalTime.parse(horaInicio2);
 	    LocalTime fin2 = LocalTime.parse(horaFin2);
 	 
 	    if (fin1.isBefore(inicio1)) {	        
 	        return (inicio1.isBefore(fin2) || fin2.equals(fin1)) || (inicio2.isBefore(fin1) || fin1.equals(fin2));
 	    } else {
 	        return (inicio1.isBefore(fin2) && fin1.isAfter(inicio2));
 	    }
 	}
    
 	/**
 	 * Verifica la disponibilidad de las mesas en una lista dada, retornando aquellas que no están solapadas 
 	 * con servicios existentes. 
 	 *
 	 * @param mesasAverificar Lista de mesas que se desea verificar para disponibilidad.
 	 * @return Lista de mesas disponibles que no tienen solapamientos con los servicios existentes.
 	 */
    public List<Mesa> verificarDisponibilidadMesas(List<Mesa> mesasAverificar) {
        List<Mesa> mesasDisponibles = new ArrayList<>();

        try {
            if (mesasAverificar.isEmpty()) {
                System.out.println("La lista de mesas a verificar está vacía.");
                return mesasDisponibles;
            }
            
            for (Mesa m : mesasAverificar) {
                Servicio servicioExistente = servicioControlador.buscarServicioPorId(m.getIdServicio());

                if (servicioExistente != null) {
                    System.out.println("Servicio existente: " + servicioExistente.getIdServicio()
                            + ", Fecha: " + servicioExistente.getFecha()
                            + ", HoraInicio: " + servicioExistente.getHoraInicio()
                            + ", HoraFin: " + servicioExistente.getHoraFin());
                    boolean solapamiento = verificarSolapamiento(
                            servicioExistente.getFecha(),
                            servicioExistente.getHoraInicio(),
                            servicioExistente.getHoraFin(),
                            servicio.getFecha(),
                            servicio.getHoraInicio(),
                            servicio.getHoraFin()
                    );
                    if (!solapamiento) {
                        mesasDisponibles.add(m);
                    } else {
                        System.out.println("Solapamiento en la mesa: " + m.getIdMesa());
                    }
                } else {
                    mesasDisponibles.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mesasDisponibles;
    }
    
}

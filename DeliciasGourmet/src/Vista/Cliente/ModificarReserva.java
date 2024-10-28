package Vista.Cliente;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import Controlador.MesaControlador;
import Controlador.ReservaControlador;
import Controlador.ServicioControlador;
import Modelo.Cliente.HistorialReserva;
import Modelo.Complementos.EnumEstado;
import Modelo.Complementos.Mesa;
import Modelo.Complementos.Reserva;
import Modelo.Complementos.Servicio;
/**
 * La clase ModificarReserva es un JFrame que permite a los usuarios 
 * modificar una reserva existente en la aplicación de gestión de reservas.
 * 
 * Esta clase proporciona una interfaz gráfica donde los usuarios pueden
 * cambiar la fecha, hora, ubicación, mesa y capacidad de una reserva ya 
 * existente. También incluye un campo para comentarios adicionales que 
 * el usuario puede desear agregar.
 * 
 * Además, esta clase utiliza controladores para gestionar las mesas, 
 * servicios y reservas, facilitando la interacción con la base de datos 
 * y garantizando que la información se mantenga actualizada.
 * 
 * Ejemplo de uso:
 * <pre>
 *   ModificarReserva modificarReserva = new ModificarReserva();
 *   modificarReserva.setVisible(true);
 * </pre>
 */
public class ModificarReserva extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Panel que contiene los componentes visuales para la modificación de reservas.
     */
    private JPanel Mapas;

    /**
     * Componente que permite seleccionar una fecha para la reserva.
     */
    private JDateChooser calendario2;

    /**
     * ComboBox que permite seleccionar la hora de la reserva.
     */
    private JComboBox<String> comboHora;

    /**
     * Botón que ejecuta la acción de modificar la reserva cuando se presiona.
     */
    JButton btnModificar;

    /**
     * Fecha actual que se utiliza para validar fechas de reserva.
     */
    private LocalDate hoy;

    /**
     * Fecha que representa un año en el futuro, utilizada para restricciones de fecha.
     */
    private LocalDate unAnoFuturo;

    /**
     * ComboBox que permite seleccionar la ubicación de la mesa.
     */
    private JComboBox<String> comboUbicaciones;

    /**
     * ComboBox que permite seleccionar la mesa específica para la reserva.
     */
    private JComboBox<String> comboMesa;

    /**
     * ComboBox que permite seleccionar la capacidad de la mesa.
     */
    private JComboBox<Integer> comboCapacidad;

    /**
     * Controlador que gestiona la lógica relacionada con las mesas.
     */
    private MesaControlador mesaControlador;

    /**
     * Controlador que gestiona la lógica relacionada con los servicios disponibles.
     */
    private ServicioControlador servicioControlador;

    /**
     * Cadena que almacena la ubicación seleccionada por el usuario.
     */
    private String SeleccionarUbicacion;

    /**
     * Capacidad seleccionada de la mesa, representada como un entero.
     */
    private int capacidadSeleccionada;

    /**
     * Fecha formateada como una cadena, utilizada para mostrar en el interfaz.
     */
    private String fechaFormateada;

    /**
     * Area de texto donde el usuario puede escribir comentarios adicionales sobre la reserva.
     */
    private JTextArea CampoComentario;

    /**
     * Arreglo de cadenas que contiene las ubicaciones disponibles para seleccionar.
     */
    private String[] ubicaciones;

    /**
     * ID de la mesa seleccionada, utilizado para identificarla en el sistema.
     */
    private int idMesaSeleccionada;

    /**
     * Cadena que representa la mesa seleccionada por el usuario.
     */
    private String seleccionMesa;

    /**
     * ID del servicio asociado a la reserva, utilizado para referencias en la base de datos.
     */
    private int idServicio;

    /**
     * Instancia de la clase Servicio que contiene información sobre el servicio relacionado.
     */
    private Servicio servicio;

    /**
     * Controlador que gestiona la lógica relacionada con las reservas.
     */
    private ReservaControlador reservaControlador;

    /**
     * Fecha y hora seleccionada para la reserva, representada como LocalDateTime.
     */
    LocalDateTime fechaSeleccionada;
    /**
     * Constructor de la clase ModificarReserva.
     *
     * Este constructor inicializa una nueva instancia de la clase ModificarReserva
     * utilizando un objeto HistorialReserva proporcionado. El objeto reserva contiene
     * información sobre la reserva que se desea modificar, lo que permite al usuario
     * realizar cambios específicos en los detalles de la reserva existente.
     *
     * @param reserva El objeto HistorialReserva que representa la reserva a modificar.
     */
    public ModificarReserva(HistorialReserva reserva) {
		reservaControlador = new ReservaControlador();
		servicioControlador = new ServicioControlador();
		
		System.out.println("Dato enviado: "+reserva.getIdReserva());
		System.out.println("Dato enviado: "+reserva.getUbicacion());
		System.out.println("Dato enviado: "+reserva.getFecha());
		System.out.println("Dato enviado: "+reserva.getHora());
		System.out.println("Dato enviado: "+reserva.getCapacidad());
		System.out.println("Dato enviado: "+reserva.getIdMesa());
		System.out.println("Dato enviado: "+reserva.getComentario());
		
		// Inicializa fechas de restricción
        hoy = LocalDate.now();
        unAnoFuturo = hoy.plusYears(1);
		
        // Configuracion del panel
		setBounds(100, 100, 992, 679);
		setLocationRelativeTo(null);
		setResizable(false);
        getContentPane().setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));
        setUndecorated(true);

        mesaControlador = new MesaControlador();

        // Panel Vertical
        JPanel pnlVertical = new JPanel();
        pnlVertical.setBorder(null);
        pnlVertical.setBackground(new Color(222, 184, 135));
        pnlVertical.setBounds(0, 0, 232, 679);
        getContentPane().add(pnlVertical);
        pnlVertical.setLayout(null);

        // Etiqueta de Ubicaciones
        JLabel lblUbicacion = new JLabel("Ubicacion");
        lblUbicacion.setHorizontalTextPosition(SwingConstants.CENTER);
        lblUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
        lblUbicacion.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblUbicacion.setBounds(16, 25, 200, 20);
        pnlVertical.add(lblUbicacion);

        // Desplegable Ubicaciones
        ubicaciones = new String[] {"COMEDOR PRINCIPAL", "TERRAZA", "BAR", "SALA PRIVADA", "PATIO"};
        comboUbicaciones = new JComboBox<>(ubicaciones);
        comboUbicaciones.setSelectedItem(reserva.getUbicacion());
        comboUbicaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SeleccionarUbicacion = (String) comboUbicaciones.getSelectedItem();
                cambioPanel(SeleccionarUbicacion);
                actualizarMesas();
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
        String fechaString = reserva.getFecha();
        if (fechaString != null && !fechaString.isEmpty()) {
        	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fechaReserva = LocalDate.parse(fechaString, formato);
            calendario2.setDate(java.sql.Date.valueOf(fechaReserva));
            Date fechaSeleccionadaDate = calendario2.getDate();
            ZonedDateTime zonedDateTime = fechaSeleccionadaDate.toInstant().atZone(ZoneId.systemDefault());
            LocalDateTime  fechaSeleccionada = zonedDateTime.toLocalDateTime(); 
            fechaFormateada = fechaSeleccionada.format(formato);
            
        } else {
            System.err.println("La fecha es nula o vacía.");
        }
          
        // Listener para cambios en el JDateChooser
        calendario2.getDateEditor().addPropertyChangeListener("date", evt -> {
            Date fechaSeleccionadaDate = calendario2.getDate();
            if (fechaSeleccionadaDate != null) {
                ZonedDateTime zonedDateTime = fechaSeleccionadaDate.toInstant().atZone(ZoneId.systemDefault());
                fechaSeleccionada= zonedDateTime.toLocalDateTime();
                actualizarHorasDisponibles(fechaSeleccionada);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaFormateada = fechaSeleccionada.format(formato);
                System.out.println("Fecha formateada actualizada: " + fechaFormateada);
            } else {
                System.err.println("La fecha seleccionada es nula.");
            }
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
        comboHora.addItem(reserva.getHora());
        comboHora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	actualizarMesas();
            }
        });
        
        comboHora.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
            	 String fechaString = reserva.getFecha();
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                 LocalDate fecha = LocalDate.parse(fechaString, formatter);
                 LocalDateTime horaLocalActual = LocalDateTime.now();
                 LocalDateTime fechaHora = fecha.atTime(horaLocalActual.getHour(), horaLocalActual.getMinute(), horaLocalActual.getSecond());
                 actualizarHorasDisponibles(fechaHora);
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
        comboCapacidad.setSelectedItem(reserva.getCapacidad());
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
                System.out.println("Capacidad Seleccionada"+capacidadSeleccionada);
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
        comboMesa.setSelectedItem(reserva.getIdMesa());
        comboMesa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboMesa.setForeground(Color.BLACK);
        comboMesa.setBackground(Color.WHITE);
        comboMesa.setBorder(null);
        comboMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        comboMesa.setBounds(41, 285, 150, 25);
        comboMesa.addItem("Mesa " + reserva.getIdMesa()); 
        pnlVertical.add(comboMesa);
        comboMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionMesa = (String) comboMesa.getSelectedItem();
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
        CampoComentario = new JTextArea(reserva.getComentario());
        CampoComentario.setFont(new Font("Roboto Light", Font.PLAIN, 13));
        CampoComentario.setForeground(Color.BLACK);
        CampoComentario.setBorder(null);
        CampoComentario.setBackground(Color.WHITE);
        CampoComentario.setBounds(11, 350, 210, 180);
        CampoComentario.setLineWrap(true);
        CampoComentario.setWrapStyleWord(true);
        pnlVertical.add(CampoComentario);

        /**
         * Crea un botón para modificar una reserva existente.
         *
         * Al hacer clic en este botón, se recopilan los datos de la reserva 
         * desde la interfaz y se actualizan en el sistema. Se verifica si 
         * han cambiado la fecha, la hora o la mesa seleccionada. Dependiendo 
         * de los cambios, se eliminan y crean mesas o se actualizan los 
         * servicios asociados. Si la reserva se actualiza correctamente, 
         * se muestra un mensaje de éxito; de lo contrario, se muestra un 
         * mensaje de error.
         *
         * Además, el botón cambia de color al pasar el ratón sobre él, 
         * mejorando la experiencia del usuario.
         */
        btnModificar = new JButton("MODIFICAR");
        btnModificar.setIcon(new ImageIcon(ModificarReserva.class.getResource("/Img/icono modificar.png")));
        btnModificar.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
            	 idMesaSeleccionada = obtenerIdMesa((String) comboMesa.getSelectedItem());
            	 
            	System.out.println("Datos Reserva");
                Reserva reservaN = new Reserva();
                reservaN.setFecha(fechaFormateada);
                System.out.println(reservaN.getFecha());
                reservaN.setHora((String) comboHora.getSelectedItem());
                System.out.println(reservaN.getHora());
                reservaN.setIdMesa(obtenerIdMesa((String) comboMesa.getSelectedItem()));
                System.out.println(reservaN.getIdMesa());
                reservaN.setComentario(CampoComentario.getText());
                System.out.println(reservaN.getComentario());
                reservaN.setDispocicionMesa(BuscarPath(ubicaciones, (String) comboUbicaciones.getSelectedItem()));
                System.out.println(reservaN.getDispocicionMesa());
                
               
                System.out.println("Datos Mesa");
                Mesa mesa = new Mesa();
                if (capacidadSeleccionada == 0) {
                    mesa.setCapacidad(mesaControlador.filtrarCapacidad(reservaN.getIdMesa()));
                } else {
                    mesa.setCapacidad(capacidadSeleccionada);
                }
                System.out.println(mesa.getCapacidad());
                mesa.setUbicacion((String) comboUbicaciones.getSelectedItem());
                System.out.println(mesa.getUbicacion());
                mesa.setEstado(EnumEstado.OCUPADA);
                System.out.println(mesa.getEstado());
                mesa.setIdMesa(idMesaSeleccionada);
                System.out.println(mesa.getIdMesa());

                Reserva reservaAux = Reserva.convertirAReserva(reserva);
                idServicio = servicioControlador.buscarServicioPorReserva(reservaAux);
                mesa.setIdServicio(idServicio);

               

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                DateTimeFormatter formatoH = DateTimeFormatter.ofPattern("HH:mm");
                LocalDate fechaReserva = LocalDate.parse(reserva.getFecha(), formato);
                LocalDate fechaReservaN = LocalDate.parse(reservaN.getFecha(), formato);

                String horaSeleccionada = (String) comboHora.getSelectedItem();
                String horaReserva = reserva.getHora().split(" - ")[0];
                String horaReservaN = horaSeleccionada.split(" - ")[0];
                LocalTime timeReserva = LocalTime.parse(horaReserva, formatoH);
                LocalTime timeReservaN = LocalTime.parse(horaReservaN, formatoH);

                boolean siCambioFecha = !fechaReserva.isEqual(fechaReservaN);
                boolean siCambioHorario = !timeReserva.equals(timeReservaN);
                boolean siCambioMesa = reserva.getIdMesa() != idMesaSeleccionada;

                if (siCambioFecha || siCambioHorario) {
                    
                    servicio = new Servicio();
                    servicio.setFecha(fechaFormateada);
                    servicio.setHoraInicio(horaSeleccionada.split(" - ")[0]);
                    servicio.setHoraFin(horaSeleccionada.split(" - ")[1]);
                    servicio.setEventoPrivado(0);

                    if (servicioControlador.verificarServicio(servicio)) {
                        idServicio = servicioControlador.buscarServicioPorReserva(reservaN);
                    } else {
                        idServicio = servicioControlador.crearServicio(servicio);
                    }

                    mesa.setIdServicio(idServicio);
                    reservaN.setIdServicio(idServicio);
                }

                // Verificar si solo ha cambiado la mesa
                if (siCambioMesa && siCambioFecha || siCambioHorario ) {
                    reservaAux = Reserva.convertirAReserva(reserva);
                    int idServicioReserva = servicioControlador.buscarServicioPorReserva(reservaAux);
                    if (mesaControlador.eliminarMesa(reserva.getIdMesa(), idServicioReserva)) {
                        System.out.println("Se eliminó la mesa con éxito.");
                    } else {
                        System.out.println("Ocurrió un error al eliminar la mesa.");
                    }

                    if (mesaControlador.crearMesa(mesa)) {
                        System.out.println("Mesa registrada con éxito.");
                    } else {
                        System.out.println("Ocurrió un error al registrar la mesa.");
                    }
                    
                }else if(siCambioMesa){
                	reservaAux = Reserva.convertirAReserva(reserva);
                    int idServicioReserva = servicioControlador.buscarServicioPorReserva(reservaAux);
                    if (mesaControlador.eliminarMesa(reserva.getIdMesa(), idServicioReserva)) {
                        System.out.println("Se eliminó la mesa con éxito.");
                    } else {
                        System.out.println("Ocurrió un error al eliminar la mesa.");
                    }

                    if (mesaControlador.crearMesa(mesa)) {
                        System.out.println("Mesa registrada con éxito.");
                    } else {
                        System.out.println("Ocurrió un error al registrar la mesa.");
                    }
                    reservaN.setIdServicio(idServicio);
                } else {
                    // Si no hay cambios en la mesa o en la hora, solo actualiza la reserva
                	idServicio = servicioControlador.buscarServicioPorReserva(reservaAux);
                    mesaControlador.actualizarIdServicio(mesa.getIdServicio(), idMesaSeleccionada, idServicio);
                    reservaN.setIdServicio(idServicio);
                }
                

                reservaN.asignarTemporada();
                if (reservaControlador.actualizarReserva(reserva.getIdReserva(), reservaN)) {
                    JOptionPane.showMessageDialog(ModificarReserva.this, "Reserva actualizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(ModificarReserva.this, "La reserva no se actualizó", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnModificar.setBackground(new Color(40, 180, 99));
                btnModificar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnModificar.setBackground(Color.WHITE);
                btnModificar.setForeground(Color.BLACK);
            }
        });
        btnModificar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnModificar.setBorder(null);
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setBackground(Color.WHITE);
        btnModificar.setFont(new Font("Roboto Light", Font.BOLD, 16));
        btnModificar.setBounds(41, 593, 150, 25);
        pnlVertical.add(btnModificar);
        
        /**
         * Crea un botón para cancelar la modificación de una reserva.
         *
         * Este botón permite al usuario cerrar la ventana actual sin guardar los 
         * cambios realizados. Al hacer clic en el botón, se cierra el 
         * JFrame de modificación. Además, se cambian los colores del botón 
         * al pasar el mouse sobre él, mejorando la experiencia del usuario.
         *
         * Se establece un ícono y se configuran las propiedades visuales del 
         * botón, como el texto, la fuente, el borde y el color de fondo.
         */
        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnCancelar.setBackground(new Color(255, 0, 0));
        		btnCancelar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnCancelar.setBackground(Color.WHITE);
            	btnCancelar.setForeground(Color.BLACK);
            }
        });
        btnCancelar.setIcon(new ImageIcon(ModificarReserva.class.getResource("/Img/icono cancelar.png")));
        
        btnCancelar.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(new Font("Roboto Light", Font.BOLD, 16));
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setBounds(41, 643, 150, 25);
        pnlVertical.add(btnCancelar);

        // Panel Mapas
        Mapas = new JPanel(new CardLayout());
        Mapas.setBackground(new Color(222, 184, 135));
        Mapas.setBounds(232, 0, 760, 679);
        getContentPane().add(Mapas);

        // Panel Comedor Principal
        JPanel panelComedor = new JPanel();
        panelComedor.setBackground(new Color(222, 184, 135));
        Mapas.add(panelComedor, "COMEDOR PRINCIPAL");

        // Imagen Comedor Principal
        JLabel ImagenComedor = new JLabel();
        ImagenComedor.setIcon(new ImageIcon(Reserva.class.getResource("/Img/COMEDOR PRINCIPAL.png")));
        ImagenComedor.setBounds(189, 0, 587, 473);
        panelComedor.add(ImagenComedor);

        // Panel Terraza
        JPanel panelTerraza = new JPanel();
        panelTerraza.setBackground(new Color(222, 184, 135));
        Mapas.add(panelTerraza, "TERRAZA");

        // Imagen Terraza
        JLabel ImagenTerraza = new JLabel();
        ImagenTerraza.setIcon(new ImageIcon(Reserva.class.getResource("/Img/TERRAZA.jpg")));
        ImagenTerraza.setBounds(-80, 0, 682, 455);
        panelTerraza.add(ImagenTerraza);

        // Panel Bar
        JPanel panelBar = new JPanel();
        panelBar.setBackground(new Color(222, 184, 135));
        Mapas.add(panelBar, "BAR");

        // Imagen Bar
        JLabel ImagenBar = new JLabel();
        ImagenBar.setIcon(new ImageIcon(Reserva.class.getResource("/Img/BAR.jpg")));
        ImagenBar.setBounds(189, 0, 587, 473);
        panelBar.add(ImagenBar);

        // Panel Sala Privada
        JPanel panelSalaPrivada = new JPanel();
        panelSalaPrivada.setBackground(new Color(222, 184, 135));
        Mapas.add(panelSalaPrivada, "SALA PRIVADA");

        // Imagen Sala Privada
        JLabel ImagenSalaPrivada = new JLabel();
        ImagenSalaPrivada.setIcon(new ImageIcon(Reserva.class.getResource("/Img/SALA PRIVADA.png")));
        ImagenSalaPrivada.setBounds(189, 0, 587, 473);
        panelSalaPrivada.add(ImagenSalaPrivada);

        // Panel Patio
        JPanel panelPatio = new JPanel();
        panelPatio.setBackground(new Color(222, 184, 135));
        Mapas.add(panelPatio, "PATIO");

        // Imagen Patio
        JLabel ImagenPatio = new JLabel();
        ImagenPatio.setIcon(new ImageIcon(Reserva.class.getResource("/Img/PATIO.png")));
        ImagenPatio.setBounds(189, 0, 587, 473);
        panelPatio.add(ImagenPatio);
        
        //Asegure que la pantalla modificar inicie con la ubicacion donde esta la mesa que eligio en la reserva.
        cambioPanel(reserva.getUbicacion());
        
    }

    /**
     * Cambia el panel visible dentro del contenedor de paneles.
     *
     * Este método utiliza un CardLayout para cambiar la vista actual al panel 
     * especificado. Es útil para gestionar múltiples vistas dentro de un mismo 
     * contenedor, permitiendo una navegación fluida entre ellas.
     *
     * @param panel El nombre del panel que se desea mostrar. Debe coincidir 
     *              con el identificador del panel en el CardLayout.
     */
    private void cambioPanel(String panel) {
        CardLayout cl = (CardLayout) (Mapas.getLayout());
        cl.show(Mapas, panel);
    }

    /**
     * Actualiza el JComboBox de horas disponibles según la fecha seleccionada.
     *
     * Este método verifica si la fecha seleccionada es igual a la fecha actual. 
     * Si es así, se comparan las horas disponibles con la hora actual y se añaden 
     * a comboHora aquellas que son futuras o iguales a la hora actual. Si no hay 
     * horas disponibles, se añade un mensaje indicando que no hay servicios disponibles 
     * para ese día. Si la fecha seleccionada no es hoy, se añaden todas las horas disponibles.
     *
     * @param fechaSeleccionada La fecha seleccionada por el usuario en formato LocalDateTime,
     *                          que se utiliza para determinar qué horas son válidas.
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
     * Busca y devuelve la ruta del mapa correspondiente a la ubicación seleccionada.
     *
     * Este método utiliza una estructura switch para determinar el archivo de imagen 
     * que representa el mapa de la mesa según la ubicación proporcionada. 
     * Si la ubicación seleccionada no coincide con ninguna de las opciones esperadas, 
     * se lanza una excepción IllegalArgumentException.
     * 
     * @param ubicaciones Un arreglo de cadenas que contiene las ubicaciones disponibles.
     * @param SeleccionarUbicacion La ubicación seleccionada por el usuario, la cual se utiliza 
     *                             para determinar el mapa correspondiente.
     * @return String La ruta del archivo de imagen del mapa correspondiente a la ubicación seleccionada.
     * @throws IllegalArgumentException Si la ubicación seleccionada no es reconocida.
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
     * Actualiza la lista de mesas disponibles en función de la ubicación seleccionada y 
     * la hora elegida. También elimina del JComboBox de mesas aquellas que están ocupadas 
     * por el servicio en la fecha y hora especificadas.
     * 
     * Este método obtiene la ubicación seleccionada del JComboBox de ubicaciones 
     * (comboUbicaciones) y busca las mesas disponibles mediante el controlador de mesas 
     * (mesaControlador). Luego, verifica cuáles de estas mesas están ocupadas por 
     * el servicio correspondiente a la fecha y hora seleccionadas, y las elimina 
     * del JComboBox de mesas (comboMesa).
     * 
     * @return List<Mesa> Una lista de objetos Mesa que representan las mesas disponibles 
     *                     en la ubicación seleccionada. 
     */
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
    
    /**
     * Filtra las mesas disponibles en función de la capacidad seleccionada y la ubicación seleccionada.
     * 
     * Este método actualiza el JComboBox de mesas (comboMesa) eliminando todas las mesas existentes 
     * y agregando solo aquellas que cumplen con la capacidad seleccionada. Si la capacidad seleccionada es 0, 
     * se incluirán todas las mesas de la ubicación seleccionada.
     * 
     * @param capacidadSeleccionada La capacidad deseada para filtrar las mesas. 
     *                              Si es 0, se mostrarán todas las mesas disponibles en la ubicación seleccionada.
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
     * Obtiene el ID de la mesa a partir de una cadena de texto que representa la selección de la mesa.
     * 
     * @param seleccionMesa La cadena que representa la mesa seleccionada, que debe contener el ID en la segunda posición después de un espacio.
     * @return El ID de la mesa como un entero, o -1 si el formato de la mesa es inválido o se produce un error.
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
}
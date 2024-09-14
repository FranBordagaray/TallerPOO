package Vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

import com.toedter.calendar.JCalendar;

import Controlador.MesaControlador;
import Modelo.Mesa;

public class Reserva extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel Mapas;
	private JCalendar calendario;
	private JComboBox<String> comboHora;
	private LocalDate hoy;
    private LocalDate unAnoFuturo;
    private JComboBox<String> comboUbicaciones;
    private JComboBox<String> comboMesa;
    private JComboBox <Integer> comboCapacidad;
    private MesaControlador mesaControlador;

	public Reserva() {
		
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
        lblUbicacion.setBounds(16, 15, 200, 20);
        pnlVertical.add(lblUbicacion);
        
		// Desplegable Ubicaciones
        String[] ubicaciones = {"COMEDOR PRINCIPAL", "TERRAZA", "BAR", "SALA PRIVADA", "PATIO"};
        comboUbicaciones = new JComboBox<>(ubicaciones);
        comboUbicaciones.setForeground(Color.BLACK);
        comboUbicaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboUbicaciones.setBackground(Color.WHITE);
        comboUbicaciones.setBorder(null);
        comboUbicaciones.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        comboUbicaciones.setToolTipText("");
        comboUbicaciones.setBounds(16, 35, 200, 25);
        pnlVertical.add(comboUbicaciones);
     
        comboUbicaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SeleccionarUbicacion = (String) comboUbicaciones.getSelectedItem();
                cambioPanel(SeleccionarUbicacion);
                actualizarMesas();
            }
        });
        
        // Etiqueta Fecha
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setBackground(Color.WHITE);
        lblFecha.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFecha.setBounds(16, 75, 200, 20);
        pnlVertical.add(lblFecha);
		
        // JCalendar para la fecha
        calendario = new JCalendar();
        calendario.getYearChooser().getSpinner().setForeground(Color.BLACK);
        calendario.getYearChooser().getSpinner().setFont(new Font("Roboto Light", Font.PLAIN, 11));
        calendario.getYearChooser().getSpinner().setBackground(Color.WHITE);
        calendario.getMonthChooser().getComboBox().setBackground(Color.WHITE);
        calendario.getMonthChooser().getComboBox().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        calendario.getMonthChooser().getComboBox().setFont(new Font("Roboto Light", Font.PLAIN, 11));
        calendario.getMonthChooser().getComboBox().setForeground(Color.BLACK);
        calendario.getDayChooser().getDayPanel().setForeground(Color.BLACK);
        calendario.getDayChooser().getDayPanel().setFont(new Font("Roboto Light", Font.PLAIN, 10));
        calendario.getDayChooser().getDayPanel().setBackground(Color.WHITE);
        calendario.getDayChooser().getDayPanel().setBorder(null);
        calendario.setBounds(11, 95, 210, 150);
        pnlVertical.add(calendario);
        
        calendario.setMinSelectableDate(java.sql.Date.valueOf(hoy));
        calendario.setMaxSelectableDate(java.sql.Date.valueOf(unAnoFuturo));
        calendario.getDayChooser().addPropertyChangeListener("day", evt -> {
            Date fechaSeleccionadaDate = calendario.getDate();
            ZonedDateTime zonedDateTime = fechaSeleccionadaDate.toInstant().atZone(ZoneId.systemDefault());
            LocalDateTime fechaSeleccionada = zonedDateTime.toLocalDateTime();
            actualizarHorasDisponibles(fechaSeleccionada);
        });
        
		// Etiqueta de Hora
		JLabel lblHora = new JLabel("Hora");
		lblHora.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.BLACK);
		lblHora.setBackground(Color.WHITE);
		lblHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblHora.setBounds(16, 260, 200, 20);
		pnlVertical.add(lblHora);
		
		// Desplegable Hora
		comboHora = new JComboBox<String>();
		comboHora.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboHora.setBorder(null);
		comboHora.setForeground(Color.BLACK);
		comboHora.setBackground(Color.WHITE);
		comboHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboHora.setBounds(41, 280, 150, 25);
		pnlVertical.add(comboHora);
		
		// Etiqueta de Capacidad
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapacidad.setForeground(Color.BLACK);
		lblCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblCapacidad.setBackground(Color.WHITE);
		lblCapacidad.setBounds(16, 320, 200, 20);
		pnlVertical.add(lblCapacidad);
		
		// Desplegable Capacidad
		Integer[] capacidades = {0,2,4,6};
		comboCapacidad = new JComboBox<>(capacidades);
		comboCapacidad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboCapacidad.setForeground(Color.BLACK);
		comboCapacidad.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboCapacidad.setBorder(null);
		comboCapacidad.setBackground(Color.WHITE);
		comboCapacidad.setBounds(41, 340, 150, 25);
		pnlVertical.add(comboCapacidad);
		
		comboCapacidad.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int capacidadSeleccionada = (int) comboCapacidad.getSelectedItem();
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
        lblMesa.setBounds(16, 380, 200, 20);
        pnlVertical.add(lblMesa);
        
        // Desplegable Mesa
        comboMesa = new JComboBox<>();
        comboMesa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboMesa.setForeground(Color.BLACK);
        comboMesa.setBackground(Color.WHITE);
        comboMesa.setBorder(null);
        comboMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        comboMesa.setBounds(41, 400, 150, 25);
        pnlVertical.add(comboMesa);
		
		// Etiqueta Comentario de la Reserva
		JLabel TituloComentario = new JLabel("Comentario");
		TituloComentario.setHorizontalTextPosition(SwingConstants.CENTER);
		TituloComentario.setHorizontalAlignment(SwingConstants.CENTER);
		TituloComentario.setBackground(Color.WHITE);
		TituloComentario.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		TituloComentario.setBounds(16, 440, 200, 20);
		pnlVertical.add(TituloComentario);
		
		// Campo de Texto Comentario de la Reserva
		JTextArea CampoComentario = new JTextArea();
		CampoComentario.setFont(new Font("Roboto Light", Font.PLAIN, 13));
		CampoComentario.setForeground(Color.BLACK);
		CampoComentario.setBorder(null);
		CampoComentario.setBackground(Color.WHITE);
		CampoComentario.setBounds(11, 460, 210, 150);
		CampoComentario.setLineWrap(true);
		CampoComentario.setWrapStyleWord(true);
		pnlVertical.add(CampoComentario);
		
		// Boton Siguiente Paso
		JButton btnSiguiente = new JButton("Siguiente");
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
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSiguiente.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setBorder(null);
		btnSiguiente.setForeground(Color.BLACK);
		btnSiguiente.setBackground(Color.WHITE);
		btnSiguiente.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnSiguiente.setBounds(41, 640, 150, 25);
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
        
        // Llama a la funcion actualizarMesas para actualizar filtros de capacidad
        actualizarMesas();
               
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
        String[] horasDisponibles = obtenerHorasDisponibles(fecha);

        if (fecha.equals(LocalDate.now())) {
            LocalTime ahora = LocalTime.now();
            for (String hora : horasDisponibles) {
                LocalTime horaDisponible = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
                if (horaDisponible.isAfter(ahora) || horaDisponible.equals(ahora)) {
                    comboHora.addItem(hora);
                }
            }
        } else {
            for (String hora : horasDisponibles) {
                comboHora.addItem(hora);
            }
        }
    }

    // Método para obtener las horas disponibles según la fecha
    private String[] obtenerHorasDisponibles(LocalDate fecha) {
        LocalDate hoy = LocalDate.now();
        LocalDate diciembre = LocalDate.of(hoy.getYear(), 12, 1);
        LocalDate marzo = LocalDate.of(hoy.getYear(), 3, 31);
        LocalDate junio = LocalDate.of(hoy.getYear(), 6, 1);
        LocalDate septiembre = LocalDate.of(hoy.getYear(), 9, 30);

        if ((fecha.isAfter(diciembre) && fecha.isBefore(marzo)) || fecha.isEqual(diciembre) || fecha.isEqual(marzo)) {
            return fecha.getDayOfWeek().getValue() <= 5
                ? new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}
                : new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00"};
        } else if ((fecha.isAfter(junio) && fecha.isBefore(septiembre)) || fecha.isEqual(junio) || fecha.isEqual(septiembre)) {
            return fecha.getDayOfWeek().getValue() <= 5
                ? new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}
                : new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        } else {
            return fecha.getDayOfWeek().getValue() <= 5
                ? new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:30"}
                : new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:30", "23:30"};
        }
    }
    
    // Funcion para actualizar por Mesas
    private void actualizarMesas() {
        String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();  
        if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
            try {
                List<Mesa> mesas = mesaControlador.buscarMesasPorUbicacion(ubicacionSeleccionada);
                comboMesa.removeAllItems();
                for (Mesa mesa : mesas) {
                	String item = "Mesa " + mesa.getIdMesa();
                    comboMesa.addItem(item);               
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar las mesas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Funcion para filtrar por Capacidad
    private void filtrarCapacidad(int capacidadSeleccionada) {
        comboMesa.removeAllItems();
        String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();
        if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
            try {
                List<Mesa> mesas = mesaControlador.buscarMesasPorUbicacion(ubicacionSeleccionada);
                for (Mesa mesa : mesas) {
                    if (capacidadSeleccionada == 0 || mesa.getCapacidad() == capacidadSeleccionada) {
                        String item = "Mesa " + mesa.getIdMesa();
                        comboMesa.addItem(item);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar las mesas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

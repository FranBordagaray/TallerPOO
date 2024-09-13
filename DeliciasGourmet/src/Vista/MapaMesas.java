package Vista;

import Modelo.Mesa;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;

import Controlador.MesaControlador;

import javax.swing.JButton;

public class MapaMesas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ContenedorMapaMesas;
	private JPanel Mapas;
	private JCalendar calendario;
	private JComboBox<String> comboHora;
	private LocalDate hoy;
    private LocalDate unAnoFuturo;
    private JComboBox<String> comboUbicaciones;
    private JComboBox<String> comboMesa;
    private MesaControlador mesaControlador;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapaMesas frame = new MapaMesas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MapaMesas() {
		
		mesaControlador = new MesaControlador();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 493);
		setResizable(false);
		ContenedorMapaMesas = new JPanel();
		ContenedorMapaMesas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContenedorMapaMesas);
		ContenedorMapaMesas.setLayout(null);
		
		// Inicializa fechas de restricción
        hoy = LocalDate.now();
        unAnoFuturo = hoy.plusYears(1);
		
		// Panel Superior 
		JPanel pnlVertical = new JPanel();
		pnlVertical.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), null));
		pnlVertical.setBackground(new Color(195, 155, 107));
		pnlVertical.setBounds(0, 0, 190, 473);
		ContenedorMapaMesas.add(pnlVertical);
		pnlVertical.setLayout(null);
		
		// Desplegable Ubicaciones
        String[] ubicaciones = {"COMEDOR PRINCIPAL", "TERRAZA", "BAR", "SALA PRIVADA", "PATIO"};
        comboUbicaciones = new JComboBox<>(ubicaciones);
        comboUbicaciones.setFont(new Font("Roboto Light", Font.BOLD, 10));
        comboUbicaciones.setToolTipText("");
        comboUbicaciones.setBounds(10, 38, 130, 21);
        pnlVertical.add(comboUbicaciones);
		
		 // Etiqueta de Ubicaciones
        JLabel lblUbicacion = new JLabel("Ubicacion");
        lblUbicacion.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblUbicacion.setBounds(10, 21, 55, 15);
        pnlVertical.add(lblUbicacion);
        
        // JCalendar para la fecha
        calendario = new JCalendar();
        calendario.setBounds(10, 83, 170, 150);
        pnlVertical.add(calendario);
        
        
     // Restringir las fechas en el JCalendar
        calendario.setMinSelectableDate(java.sql.Date.valueOf(hoy));
        calendario.setMaxSelectableDate(java.sql.Date.valueOf(unAnoFuturo));

        
     // Evento para actualizar las horas disponibles cuando se cambia la fecha
        calendario.getDayChooser().addPropertyChangeListener("day", evt -> {
            // Obtén la fecha del JCalendar
            Date fechaSeleccionadaDate = calendario.getDate();

            // Convierte la fecha a LocalDateTime usando el sistema por defecto
            ZonedDateTime zonedDateTime = fechaSeleccionadaDate.toInstant().atZone(ZoneId.systemDefault());
            LocalDateTime fechaSeleccionada = zonedDateTime.toLocalDateTime();

            // Actualiza las horas disponibles
            actualizarHorasDisponibles(fechaSeleccionada);
        });

        // Etiqueta Fecha
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblFecha.setBounds(10, 69, 45, 13);
        pnlVertical.add(lblFecha);
        
        
        // Etiqueta Mesa
        JLabel lblMesa = new JLabel("Mesas");
        lblMesa.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblMesa.setBounds(20, 300, 45, 21);
        pnlVertical.add(lblMesa);
        
        // ComboBox Mesa
        comboMesa = new JComboBox<>();
        comboMesa.setFont(new Font("Roboto Light", Font.BOLD, 10));
        comboMesa.setBounds(10, 324, 145, 21);
        pnlVertical.add(comboMesa);
		
		// Boton Seleccionar Mesa
		JButton SeleccionarMesa = new JButton("Seleccionar");
		SeleccionarMesa.setFont(new Font("Tahoma", Font.BOLD, 12));
		SeleccionarMesa.setBounds(69, 416, 111, 21);
		pnlVertical.add(SeleccionarMesa);
		
		// ComboBox Hora
		comboHora = new JComboBox<String>();
		comboHora.setFont(new Font("Roboto Light", Font.BOLD, 10));
		comboHora.setBounds(10, 266, 81, 21);
		pnlVertical.add(comboHora);
		
		// Etiqueta de Hora
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Roboto Light", Font.BOLD, 12));
		lblHora.setBounds(20, 243, 45, 13);
		pnlVertical.add(lblHora);
	
		// Panel Mapas
        Mapas = new JPanel(new CardLayout());
        Mapas.setBounds(189, 0, 682, 455);
        ContenedorMapaMesas.add(Mapas);
        
        // Panel Terraza
        JPanel panelTerraza = new JPanel();
        Mapas.add(panelTerraza, "TERRAZA");
        
        
        // Imagen Terraza
        JLabel ImagenTerraza = new JLabel();
        ImagenTerraza.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/TERRAZA.jpg")));
        ImagenTerraza.setBounds(0, 0, 682, 455);
        panelTerraza.add(ImagenTerraza);
        
        // Panel Comedor Principal
        JPanel panelComedor = new JPanel();
        Mapas.add(panelComedor, "COMEDOR PRINCIPAL");
        
        // Imagen Comedor Principal
        JLabel ImagenComedor = new JLabel();
        ImagenComedor.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/COMEDOR PRINCIPAL.png")));
        ImagenComedor.setBounds(189, 0, 587, 473);
        panelComedor.add(ImagenComedor);
        
        // Panel Barra
        JPanel panelBarra = new JPanel();
        Mapas.add(panelBarra, "BAR");
        
        // Imagen Barra
        JLabel ImagenBarra = new JLabel();
        ImagenBarra.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/BAR.jpg")));
        ImagenBarra.setBounds(189, 0, 587, 473);
        panelBarra.add(ImagenBarra);
        
        // Panel Sala Privada
        JPanel panelSalaPrivada = new JPanel();
        Mapas.add(panelSalaPrivada, "SALA PRIVADA");
        
        // Imagen Sala Privada
        JLabel ImagenSalaPrivada = new JLabel();
        ImagenSalaPrivada.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/SALA PRIVADA.png")));
        ImagenSalaPrivada.setBounds(189, 0, 587, 473);
        panelSalaPrivada.add(ImagenSalaPrivada);
        
        // Panel Patio
        JPanel panelPatio = new JPanel();
        Mapas.add(panelPatio, "PATIO");
        
        // Imagen Patio
        JLabel ImagenPatio = new JLabel();
        ImagenPatio.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/PATIO.png")));
        ImagenPatio.setBounds(189, 0, 587, 473);
        panelPatio.add(ImagenPatio);
        
        // Asegura que se inicie con el panel de "Comedor Principal"
        cambioPanel("COMEDOR PRINCIPAL");
        
        // Evento del Desplegable Ubicaciones
        comboUbicaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SeleccionarUbicacion = (String) comboUbicaciones.getSelectedItem();
                cambioPanel(SeleccionarUbicacion);
                actualizarMesas();
            }
        });
        
	}
	// Cambia el panel visible en el contenedor 'Mapas' basado en el nombre del panel seleccionado.
    private void cambioPanel(String panel) {
        CardLayout cl = (CardLayout) (Mapas.getLayout());
        cl.show(Mapas, panel);
    }
    
    private void actualizarHorasDisponibles(LocalDateTime fechaSeleccionada) {
        comboHora.removeAllItems(); // Limpiar el comboBox de horas

        LocalDate fecha = fechaSeleccionada.toLocalDate();
        int diaSemana = fechaSeleccionada.getDayOfWeek().getValue();

        // Determinar el horario según la estación del año y el día de la semana
        String[] horasDisponibles = obtenerHorasDisponibles(fecha);

        // Si la fecha seleccionada es hoy, solo mostrar las horas futuras
        if (fecha.equals(LocalDate.now())) {
            LocalTime ahora = LocalTime.now();

            for (String hora : horasDisponibles) {
                // Convertir la hora disponible a LocalTime en formato 24 horas
                LocalTime horaDisponible = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));

                // Mostrar solo las horas futuras
                if (horaDisponible.isAfter(ahora) || horaDisponible.equals(ahora)) {
                    comboHora.addItem(hora);
                }
            }
        } else {
            // Si es una fecha futura, mostrar todas las horas
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

        // Devolver horas en formato de 24 horas (HH:mm) para evitar problemas de formato
        if ((fecha.isAfter(diciembre) && fecha.isBefore(marzo)) || fecha.isEqual(diciembre) || fecha.isEqual(marzo)) {
            // Horario de verano
            return fecha.getDayOfWeek().getValue() <= 5
                ? new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}
                : new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00"};
        } else if ((fecha.isAfter(junio) && fecha.isBefore(septiembre)) || fecha.isEqual(junio) || fecha.isEqual(septiembre)) {
            // Horario de invierno
            return fecha.getDayOfWeek().getValue() <= 5
                ? new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}
                : new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        } else {
            // Horario de otras estaciones
            return fecha.getDayOfWeek().getValue() <= 5
                ? new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:30"}
                : new String[]{"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:30", "23:30"};
        }
    }
    
    private void actualizarMesas() {
        // Obtener la ubicación seleccionada
        String ubicacionSeleccionada = (String) comboUbicaciones.getSelectedItem();  
        
        if (ubicacionSeleccionada != null && !ubicacionSeleccionada.equals("Seleccione una ubicación")) {
            try {
                // Obtener mesas filtradas por ubicación
                List<Mesa> mesas = mesaControlador.buscarMesasPorUbicacion(ubicacionSeleccionada);
              
                // Limpiar el JComboBox de mesas
                comboMesa.removeAllItems();
                
                // Agregar mesas al JComboBox
                for (Mesa mesa : mesas) {
                	String item = "Mesa " + mesa.getIdMesa() + " - Capacidad: " + mesa.getCapacidad();
                    comboMesa.addItem(item);               
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al cargar las mesas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } 

}

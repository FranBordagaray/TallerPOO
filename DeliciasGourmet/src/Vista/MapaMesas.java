package Vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class MapaMesas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ContenedorMapaMesas;
	private JTable table;
	private JPanel Mapas;

	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 493);
		setResizable(false);
		ContenedorMapaMesas = new JPanel();
		ContenedorMapaMesas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContenedorMapaMesas);
		ContenedorMapaMesas.setLayout(null);
		
		
		JPanel pnlVertical = new JPanel();
		pnlVertical.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), null));
		pnlVertical.setBackground(new Color(195, 155, 107));
		pnlVertical.setBounds(0, 0, 190, 473);
		ContenedorMapaMesas.add(pnlVertical);
		pnlVertical.setLayout(null);
		
		// JComboBox Ubicaciones
        String[] ubicaciones = {"Comedor Principal", "Terraza", "Bar", "Sala Privada", "Patio"};
        JComboBox<String> comboUbicaciones = new JComboBox<>(ubicaciones);
        comboUbicaciones.setFont(new Font("Roboto Light", Font.BOLD, 10));
        comboUbicaciones.setToolTipText("");
        comboUbicaciones.setBounds(10, 38, 130, 21);
        pnlVertical.add(comboUbicaciones);
		
		 // Etiqueta de Ubicaciones
        JLabel lblUbicacion = new JLabel("Ubicacion");
        lblUbicacion.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblUbicacion.setBounds(10, 21, 55, 15);
        pnlVertical.add(lblUbicacion);
        
        // JComboBox Fecha
        JComboBox<String> comboFecha = new JComboBox<>();
        comboFecha.setFont(new Font("Roboto Light", Font.BOLD, 10));
        comboFecha.setBounds(10, 83, 81, 21);
        pnlVertical.add(comboFecha);
        
        // Etiqueta Fecha
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblFecha.setBounds(10, 69, 45, 13);
        pnlVertical.add(lblFecha);
        
        // JComboBox Hora
        JComboBox<String> comboHora = new JComboBox<>();
        comboHora.setFont(new Font("Roboto Light", Font.BOLD, 10));
        comboHora.setBounds(10, 128, 81, 21);
        pnlVertical.add(comboHora);
        
        // Etiqueta Hora
        JLabel lblHora = new JLabel("Hora");
        lblHora.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblHora.setBounds(10, 114, 45, 13);
        pnlVertical.add(lblHora);
        
        
        // Etiqueta Mesa
        JLabel lblMesa = new JLabel("Mesas");
        lblMesa.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblMesa.setBounds(10, 159, 45, 13);
        pnlVertical.add(lblMesa);
        
        // JComboBox Mesa
        String[] mesas = {"Mesa1", "Mesa2", "Mesa3", "Mesa5", "Mesa5" };
        JComboBox<String> comboMesa = new JComboBox<>(mesas);
        comboMesa.setFont(new Font("Roboto Light", Font.BOLD, 10));
        comboMesa.setBounds(10, 175, 81, 21);
        pnlVertical.add(comboMesa);
        
        // Formulario Mesas
        JScrollPane FormularioMesas  = new JScrollPane();
		FormularioMesas.setBorder(null);
		FormularioMesas.setBounds(10, 200, 170, 195);
		pnlVertical.add(FormularioMesas);

		String[] columna = { "Mesa", "Estado" };

		// Datos
		Object[][] datos = { 
				{ "Mesa 5","BLOQUEADA" },
				{ "Mesa 3", "LIBRE" }, 
				{ "Mesa 7", "OCUPADA" }, };

		DefaultTableModel modelo = new DefaultTableModel(datos, columna) {
			@Override
	        public boolean isCellEditable(int row, int column) {
	                return false; // Deshabilitar la edición de celdas
	        }
	        };
	

		JTable tabla = new JTable(modelo);
		tabla.setFont(new Font("Roboto Light", Font.PLAIN, 10));
		
        // Crear un renderer para cambiar la fuente de las columnas específicas
        DefaultTableCellRenderer estiloCelda = new DefaultTableCellRenderer();
        estiloCelda.setFont(new Font("Roboto Light", Font.PLAIN, 10)); // Cambiar a "Roboto Light" y tamaño 10
        
        // Aplicar el estiloCelda a las columnas "Mesa" y "Estado"
        tabla.getColumnModel().getColumn(0).setCellRenderer(estiloCelda); // Columna "Mesa"
        tabla.getColumnModel().getColumn(1).setCellRenderer(estiloCelda); // Columna "Estado

		FormularioMesas.setViewportView(tabla);
		
		//Boton Seleccionar Mesa
		JButton SeleccionarMesa = new JButton("Seleccionar");
		SeleccionarMesa.setFont(new Font("Tahoma", Font.BOLD, 12));
		SeleccionarMesa.setBounds(69, 416, 111, 21);
		pnlVertical.add(SeleccionarMesa);
	
		// Panel Mapas
        Mapas = new JPanel(new CardLayout());
        Mapas.setBounds(189, 0, 682, 455);
        ContenedorMapaMesas.add(Mapas);
        
        //Panel Terraza
        JPanel panelTerraza = new JPanel();
        Mapas.add(panelTerraza, "Terraza");
        
        
        //Imagen Terraza
        JLabel ImagenTerraza = new JLabel();
        ImagenTerraza.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/TERRAZA.jpg")));
        ImagenTerraza.setBounds(0, 0, 682, 455);
        panelTerraza.add(ImagenTerraza);
        
        // Panel Comedor Principal
        JPanel panelComedor = new JPanel();
        Mapas.add(panelComedor, "Comedor Principal");
        
        //Imagen Comedor Principal
        JLabel ImagenComedor = new JLabel();
        ImagenComedor.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/COMEDOR PRINCIPAL.png")));
        ImagenComedor.setBounds(189, 0, 587, 473);
        panelComedor.add(ImagenComedor);
        
        //Panel Barra
        JPanel panelBarra = new JPanel();
        Mapas.add(panelBarra, "Bar");
        
        //Imagen Barra
        JLabel ImagenBarra = new JLabel();
        ImagenBarra.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/BAR.jpg")));
        ImagenBarra.setBounds(189, 0, 587, 473);
        panelBarra.add(ImagenBarra);
        
        //Panel Sala Privada
        JPanel panelSalaPrivada = new JPanel();
        Mapas.add(panelSalaPrivada, "Sala Privada");
        
        //Imagen Sala Privada
        JLabel ImagenSalaPrivada = new JLabel();
        ImagenSalaPrivada.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/SALA PRIVADA.png")));
        ImagenSalaPrivada.setBounds(189, 0, 587, 473);
        panelSalaPrivada.add(ImagenSalaPrivada);
        
        //Panel Patio
        JPanel panelPatio = new JPanel();
        Mapas.add(panelPatio, "Patio");
        
        //Imagen Patio
        JLabel ImagenPatio = new JLabel();
        ImagenPatio.setIcon(new ImageIcon(MapaMesas.class.getResource("/Img/PATIO.png")));
        ImagenPatio.setBounds(189, 0, 587, 473);
        panelPatio.add(ImagenPatio);
        
        // Evento del Desplegable Ubicaciones
        comboUbicaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SeleccionarUbicacion = (String) comboUbicaciones.getSelectedItem();
                cambioPanel(SeleccionarUbicacion);
            }
        });
        
	}
	//Cambia el panel visible en el contenedor 'Mapas' basado en el nombre del panel seleccionado.
    private void cambioPanel(String panel) {
        CardLayout cl = (CardLayout) (Mapas.getLayout());
        cl.show(Mapas, panel);
    }
    
    //Metodo para obtener fecha y hora local
    public static String obtenerFechaHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ahora.format(formateador);
    }
}

package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;


	private JTable tabla;
	private DefaultTableModel modelo;
	private Object[][] datos; 
	private JComboBox<String> comboBoxEstado;
	private JComboBox<String> comboBoxMesa;
	public Historial() {
		setBorder(null);
		setBackground(new Color(222, 184, 135));
		setPreferredSize(new Dimension(992, 679));
		setLayout(null);

		String[] columna = { "Fecha", "Hora", "Estado", "Mesa", "Personas" };

		// Datos de tabla 
		datos = new Object[][] { 
			{ "15-03-2022", "12:00", "Reservado", "Mesa 5", 2 },
			{ "17-08-2023", "21:30", "Cancelado", "Mesa 3", 6 },
			{ "25-05-2024", "20:00", "Finalizado", "Mesa 7", 1 }, 
			
		};

	     modelo = new DefaultTableModel(datos, columna) {
	            @Override
	            public boolean isCellEditable(int fila, int columna) {
	                return false; 
	            }
	        };

	        tabla = new JTable(modelo);

	    //Panel de tabla
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 127, 951, 277);
		add(scrollPane);
		
		//Panel Superior
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(195, 155, 107));
		pnlHeader.setBounds(278, 0, 436, 62);
		add(pnlHeader);
		pnlHeader.setLayout(null);
		
		//Titulo Historial
		JLabel lblHistorial = new JLabel("HISTORIAL DE RESERVAS");
		lblHistorial.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblHistorial.setBounds(98, 19, 248, 24);
		pnlHeader.add(lblHistorial);
		
		// Filtro Estado
				JLabel lblFiltro1 = new JLabel("Filtrar estados:");
				lblFiltro1.setBounds(10, 102, 86, 14);
				add(lblFiltro1);

				// Selección de Filtro de Estados 
				String[] estados = { "Todos", "Reservado", "Cancelado", "Finalizado" };
				comboBoxEstado = new JComboBox<>(estados);
				comboBoxEstado.setBackground(Color.WHITE);
				comboBoxEstado.setBorder(null);
				comboBoxEstado.setBounds(92, 94, 137, 22);
				add(comboBoxEstado);

				// Filtro Mesas
				JLabel lblFiltro2 = new JLabel("Filtrar mesas:");
				lblFiltro2.setBounds(247, 102, 86, 14);
				add(lblFiltro2);

				// Selección de Filtro de Mesas
				String[] mesas = { "Todas", "Mesa 3", "Mesa 5", "Mesa 7" };
				comboBoxMesa = new JComboBox<>(mesas);
				comboBoxMesa.setBackground(Color.WHITE);
				comboBoxMesa.setBorder(null);
				comboBoxMesa.setBounds(323, 94, 137, 22);
				add(comboBoxMesa);
				
				// Opciones para los actionListeners
				ActionListener filtroListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String estadoSeleccionado = (String) comboBoxEstado.getSelectedItem();
						String mesaSeleccionada = (String) comboBoxMesa.getSelectedItem();
						filtrar(estadoSeleccionado, mesaSeleccionada);
					}
				};

				comboBoxEstado.addActionListener(filtroListener);
				comboBoxMesa.addActionListener(filtroListener);
			}

			// Método que filtra por estado y mesa
			private void filtrar(String estado, String mesa) {
				Object[][] datosFiltrados = filtrarDatos(datos, estado, mesa);
				modelo.setDataVector(datosFiltrados, new String[] { "Fecha", "Hora", "Estado", "Mesa", "Ocupantes" });
			}

			// Método que aplica los filtros
			private Object[][] filtrarDatos(Object[][] datos, String estado, String mesa) {
				return java.util.Arrays.stream(datos)
						.filter(fila -> (estado.equals("Todos") || fila[2].equals(estado)) &&
										(mesa.equals("Todas") || fila[3].equals(mesa)))
						.toArray(Object[][]::new);
			}
		}
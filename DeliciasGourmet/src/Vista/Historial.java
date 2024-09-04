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
		scrollPane.setBounds(10, 73, 951, 226);
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
		
		//Filtro Estado
		JLabel lblFiltro1 = new JLabel("Filtrar estados:");
		lblFiltro1.setBounds(10, 54, 86, 14);
		add(lblFiltro1);

		// Seleccion de Filtro de Estados 
		String[] estados = { "Todos", "Reservado", "Cancelado", "Finalizado" };
		JComboBox<String> comboBox = new JComboBox<>(estados);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setBounds(98, 50, 137, 22);
		add(comboBox);
		
		//Opciones para el actionListener
		comboBox.addActionListener(new ActionListener() {
			// fijarse si anda
			public void actionPerformed(ActionEvent e) {
				String estadoSeleccionado = (String) comboBox.getSelectedItem();
				filtrarEstado(estadoSeleccionado);
			}
		});
	}

	//Metodo que filtra por estado
	private void filtrarEstado(String estado) {
		if (estado.equals("Todos")) {
			modelo.setDataVector(datos, new String[] { "Fecha", "Hora", "Estado", "Mesa", "Ocupantes" });
		} else {
			Object[][] datosFiltrados = filtrarEstado(datos, estado);
			modelo.setDataVector(datosFiltrados, new String[] { "Fecha", "Hora", "Estado", "Mesa", "Ocupantes" });
		}
	}

	
	private Object[][] filtrarEstado(Object[][] datos, String estado) {
		return java.util.Arrays.stream(datos)
				.filter(fila -> fila[2].equals(estado))
				.toArray(Object[][]::new);
	}
}

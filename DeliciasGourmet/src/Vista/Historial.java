package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;


	private JTable tabla;
	private DefaultTableModel model;
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

		// Crear el modelo de la tabla
		model = new DefaultTableModel(datos, columna);
		tabla = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 73, 951, 226);
		add(scrollPane);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(195, 155, 107));
		pnlHeader.setBounds(278, 0, 436, 62);
		add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblHistorial = new JLabel("HISTORIAL DE RESERVAS");
		lblHistorial.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblHistorial.setBounds(98, 19, 240, 24);
		pnlHeader.add(lblHistorial);
		
		JLabel lblFiltro1 = new JLabel("Filtrar estados:");
		lblFiltro1.setBounds(10, 54, 80, 14);
		add(lblFiltro1);

		// seleccion de filtro de estados 
		String[] estados = { "Todos", "Reservado", "Cancelado", "Finalizado" };
		JComboBox<String> comboBox = new JComboBox<>(estados);
		comboBox.setBounds(85, 50, 137, 22);
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

	
	private void filtrarEstado(String estado) {
		if (estado.equals("Todos")) {
			model.setDataVector(datos, new String[] { "Fecha", "Hora", "Estado", "Mesa", "Ocupantes" });
		} else {
			Object[][] datosFiltrados = filtrarEstado(datos, estado);
			model.setDataVector(datosFiltrados, new String[] { "Fecha", "Hora", "Estado", "Mesa", "Ocupantes" });
		}
	}

	
	private Object[][] filtrarEstado(Object[][] datos, String estado) {
		return java.util.Arrays.stream(datos)
				.filter(fila -> fila[2].equals(estado))
				.toArray(Object[][]::new);
	}
}

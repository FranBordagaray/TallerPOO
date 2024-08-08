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

public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;

	public Historial() {
		setBorder(null);
		setBackground(new Color(222, 184, 135));
		setPreferredSize(new Dimension(992, 679));
		setLayout(null);

		String[] columna = { "Fecha", "Hora", "Estado", "Mesa", "Personas" };

		// Datos
		Object[][] datos = { { "15-03-2022", "12:00", "Reservado", "Mesa 5", 2 },
				{ "17-08-2023", "21:30", "Cancelado", "Mesa 3", 6 },
				{ "25-05-2024", "20:00", "Finalizado", "Mesa 7", 1 }, };

		DefaultTableModel model = new DefaultTableModel(datos, columna);

		JTable tabla = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(10, 73, 951, 226);
		add(scrollPane);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(195, 155, 107));
		pnlHeader.setBounds(278, 0, 436, 62);
		add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HISTORIAL DE RESERVAS");
		lblNewLabel.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblNewLabel.setBounds(98, 19, 240, 24);
		pnlHeader.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(200, 306, 95, 22);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar:");
		lblNewLabel_1.setBounds(166, 310, 46, 14);
		add(lblNewLabel_1);

	}
}

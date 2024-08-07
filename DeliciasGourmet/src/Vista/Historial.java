package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public Historial() {
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HISTORIAL DE RESERVAS");
		lblNewLabel.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblNewLabel.setBounds(351, 32, 207, 14);
		add(lblNewLabel);
		
		 String[] columna = {"Fecha", "Hora", "Estado", "Mesa", "Personas"};
	        
	        // Datos 
	        Object[][] datos = {
	            {"15-03-2022", "12:00", "Reservado", "Mesa 5", 2},
	            {"17-08-2023", "21:30", "Cancelado", "Mesa 3", 6},
	            {"25-05-2024", "20:00", "Finalizado", "Mesa 7", 1},
	        };
	        
	     
	        DefaultTableModel model = new DefaultTableModel(datos, columna);
	        
	      
	        JTable tabla= new JTable(model);
	        
	        
	        JScrollPane scrollPane = new JScrollPane(tabla);
	        scrollPane.setBounds(10, 73, 951, 226);
	        add(scrollPane);
		
	}
}

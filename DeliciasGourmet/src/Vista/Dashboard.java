package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Sesion;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	

	public Dashboard() {
		
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));
		
		//Panel de Bienvenio
		JPanel pnlBienvenido = new JPanel();
		pnlBienvenido.setBackground(new Color(195, 155, 107));
		pnlBienvenido.setBounds(0, 0, 484, 66);
		add(pnlBienvenido);
		pnlBienvenido.setLayout(null);
		
		//Label de Bienvenido
		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		lblBienvenido.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgHome.png")));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setForeground(Color.BLACK);
		lblBienvenido.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblBienvenido.setBounds(10, 9, 140, 48);
		pnlBienvenido.add(lblBienvenido);
		
		// Label Nombre de Usuario
		Sesion s1 = new Sesion();
		JLabel lblNombreUsuario = new JLabel(s1.getClienteActual().getNombre() +" "+ s1.getClienteActual().getApellido());
		lblNombreUsuario.setForeground(Color.BLACK);
		lblNombreUsuario.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblNombreUsuario.setBounds(155, 9, 319, 48);
		pnlBienvenido.add(lblNombreUsuario);
		
		//Panel de Notificaciones
		JPanel pnlNotificaciones = new JPanel();
		pnlNotificaciones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlNotificaciones.setBounds(498, 10, 430, 243);
		add(pnlNotificaciones);
		pnlNotificaciones.setLayout(null);
		
		// Panel Header Notificaciones
		JPanel pnlHeaderNot = new JPanel();
		pnlHeaderNot.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlHeaderNot.setBackground(Color.PINK);
		pnlHeaderNot.setBounds(0, 0, 430, 47);
		pnlNotificaciones.add(pnlHeaderNot);
		pnlHeaderNot.setLayout(null);
		
		//Label TituloNotificaciones
		JLabel lblTituloNotificaciones = new JLabel("Notificaciones");
		lblTituloNotificaciones.setBounds(155, 7, 119, 32);
		lblTituloNotificaciones.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgNotificacion.png")));
		pnlHeaderNot.add(lblTituloNotificaciones);
		lblTituloNotificaciones.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		
		//Panel Resumen Reservas
		JPanel pnlRumenReservas = new JPanel();
		pnlRumenReservas.setBorder(null);
		pnlRumenReservas.setBackground(new Color(255, 255, 255));
		pnlRumenReservas.setBounds(10, 107, 309, 394);
		add(pnlRumenReservas);
		pnlRumenReservas.setLayout(null);
		
		// Label de Tabla Resumen
		JLabel lblNewLabel = new JLabel("Mis Reservas");
		lblNewLabel.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblNewLabel.setBounds(109, 76, 99, 19);
		add(lblNewLabel);
		
		// ScrollPane Tabla Resumen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(0, 0, 309, 394);
		pnlRumenReservas.add(scrollPane);
		
		String[] columna = {"Mesa", "Fecha", "Hora", "Estado"};
	        
	        // Datos 
	        Object[][] datos = {
	            {"Mesa 5","15-03-2022", "12:00", "Reservado" },
	            {"Mesa 3","17-08-2023", "21:30", "Cancelado"},
	            {"Mesa 7","25-05-2024", "20:00", "Finalizado" },
	        };
	        
	     
	        DefaultTableModel model = new DefaultTableModel(datos, columna);
	        
	      
	    JTable tabla= new JTable(model);

		scrollPane.setViewportView(tabla);
		
		 //Panel Oferta Imagenes 
		ImgOfertas pnlImagenes = new ImgOfertas();
		JPanel pnlOfertas = new JPanel();
		pnlOfertas.setBounds(696, 299, 286, 370);
		add(pnlOfertas);
		pnlOfertas.add(pnlImagenes);
		
		
		
	}
}

package Vista.Cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.ReservaControlador;
import Modelo.HistorialReserva;
import Modelo.Sesion;

@SuppressWarnings("static-access")
public class Historial extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxEstado;
	private JComboBox<String> comboBoxMesa;
	private JTable tblBHistorial;
	private Sesion s;
	
	@SuppressWarnings("serial")
	public Historial() {
		s = new Sesion();
		// Configuracion del panel historial
		setBorder(null);
		setBackground(new Color(222, 184, 135));
		setPreferredSize(new Dimension(992, 679));
		setLayout(null);
		
		//Panel Superior
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(195, 155, 107));
		pnlHeader.setBounds(271, 0, 450, 62);
		add(pnlHeader);
		pnlHeader.setLayout(null);
		
		//Titulo Historial
		JLabel lblHistorial = new JLabel("HISTORIAL DE RESERVAS");
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblHistorial.setBounds(75, 18, 300, 25);
		pnlHeader.add(lblHistorial);
		
		// Filtro Estado
		JLabel lblFiltroEstados = new JLabel("Filtrar estados:");
		lblFiltroEstados.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroEstados.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblFiltroEstados.setBounds(0, 90, 150, 25);
		add(lblFiltroEstados);

		// Selección de Filtro de Estados 
		String[] estados = { "Todos", "Reservado", "Cancelado", "Finalizado" };
		comboBoxEstado = new JComboBox<>(estados);
		comboBoxEstado.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboBoxEstado.setBackground(Color.WHITE);
		comboBoxEstado.setBorder(null);
		comboBoxEstado.setBounds(150, 90, 100, 25);
		add(comboBoxEstado);

		// Filtro Mesas
		JLabel lblFiltroMesas = new JLabel("Filtrar mesas:");
		lblFiltroMesas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltroMesas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		lblFiltroMesas.setBounds(300, 90, 150, 25);
		add(lblFiltroMesas);

		// Selección de Filtro de Mesas
		String[] mesas = { "Todas", "Mesa 3", "Mesa 5", "Mesa 7" };
		comboBoxMesa = new JComboBox<>(mesas);
		comboBoxMesa.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		comboBoxMesa.setBackground(Color.WHITE);
		comboBoxMesa.setBorder(null);
		comboBoxMesa.setBounds(450, 90, 100, 25);
		add(comboBoxMesa);
		
		comboBoxMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesa = (String) comboBoxMesa.getSelectedItem();
                filtrarMesas(mesa);
            }
        });
		
		// scroll para utilizar la tabla
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 150, 960, 518);
		add(scrollPane);
		
		// Tabla para el historial personal de un cliente
		tblBHistorial = new JTable();
		tblBHistorial.setGridColor(Color.DARK_GRAY);
		tblBHistorial.setBackground(Color.WHITE);
		tblBHistorial.setBorder(null);
		tblBHistorial.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		tblBHistorial.setForeground(Color.BLACK);
		tblBHistorial.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"FECHA", "HORA", "MESA", "COMENSALES", "UBICACION", "COMENTARIO"
			}) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			});
		scrollPane.setViewportView(tblBHistorial);
		// Utiliza la funcion para llenar la tabla con historial de reservas
		cargarDatos(s.getClienteActual().getIdCliente());
		// Utiliza la funcion para llenar el combo con mesas unicamente reservadas por el cliente
		cargarComboMesas(s.getClienteActual().getIdCliente());
	}
	
	// Función para cargar tabla con datos almacenados en la base de datos
	private void cargarDatos(int idCliente) {
	    ReservaControlador controlador = new ReservaControlador();
	    List<HistorialReserva> historial;
		try {
			historial = controlador.obtenerHistorialPorCliente(idCliente);
			DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
		    model.setRowCount(0);

		    for (HistorialReserva reserva : historial) {
		        model.addRow(new Object[]{
		            reserva.getFecha(),
		            reserva.getHora(),
		            reserva.getIdMesa(),
		            reserva.getCapacidad(),
		            reserva.getUbicacion(),
		            reserva.getComentario()
		        });
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Función para cargar tabla con datos filtrados por mesas
	private void filtrarMesas(String filtro) {
	    ReservaControlador controlador = new ReservaControlador();
	    List<HistorialReserva> historial;
	    try {
	        historial = controlador.obtenerHistorialPorCliente(s.getClienteActual().getIdCliente());
	        DefaultTableModel model = (DefaultTableModel) tblBHistorial.getModel();
	        model.setRowCount(0);

	        if (filtro == null || filtro.equals("Todas")) {
	            for (HistorialReserva reserva : historial) {
	                model.addRow(new Object[]{
	                    reserva.getFecha(),
	                    reserva.getHora(),
	                    reserva.getIdMesa(),
	                    reserva.getCapacidad(),
	                    reserva.getUbicacion(),
	                    reserva.getComentario()
	                });
	            }
	        } else {
	            for (HistorialReserva reserva : historial) {
	                if (("Mesa " + reserva.getIdMesa()).equals(filtro)) {
	                    model.addRow(new Object[]{
	                        reserva.getFecha(),
	                        reserva.getHora(),
	                        reserva.getIdMesa(),
	                        reserva.getCapacidad(),
	                        reserva.getUbicacion(),
	                        reserva.getComentario()
	                    });
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Metodo para tener combobox mesas unicamente con mesas reservadas por el cliente
	private void cargarComboMesas(int idCliente) {
	    ReservaControlador controlador = new ReservaControlador();
	    try {
	        List<Integer> mesasReservadas = controlador.obtenerMesasReservadasPorCliente(idCliente);
	        comboBoxMesa.removeAllItems();

	        comboBoxMesa.addItem("Todas");
	        for (Integer idMesa : mesasReservadas) {
	            comboBoxMesa.addItem("Mesa " + idMesa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
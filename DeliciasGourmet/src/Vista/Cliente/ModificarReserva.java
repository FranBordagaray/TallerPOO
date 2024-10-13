package Vista.Cliente;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controlador.ReservaControlador;
import Modelo.Complementos.Reserva;

public class ModificarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUbicacion;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtCapacidad;
	private JTextField txtMesa;
	private JTextArea txtComentario;

	private ReservaControlador reservaControlador;
	private Reserva reserva; // Objeto para almacenar la reserva que se va a modificar

	public ModificarReserva(Reserva reserva) {
		this.reserva = reserva; // Inicializar la reserva con la que se va a modificar
		reservaControlador = new ReservaControlador();

		// Configuración de la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 562);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel Fondo Contenedor
		JPanel pnlContenedor = new JPanel();
		pnlContenedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlContenedor.setBorder(null);
		pnlContenedor.setBackground(new Color(222, 184, 135));
		pnlContenedor.setBounds(0, 0, 450, 562);
		contentPane.add(pnlContenedor);
		pnlContenedor.setLayout(null);

		// Etiqueta Detalle de Reserva
		JLabel lblDetalle = new JLabel("Modificar Reserva");
		lblDetalle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetalle.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalle.setFont(new Font("Roboto Light", Font.BOLD, 28));
		lblDetalle.setBounds(101, 10, 248, 33);
		pnlContenedor.add(lblDetalle);

		// Etiqueta Ubicacion
		JLabel lblUbicacion = new JLabel("UBICACION: ");
		lblUbicacion.setFont(new Font("Roboto Light", Font.BOLD, 11));
		lblUbicacion.setBounds(32, 70, 100, 20);
		pnlContenedor.add(lblUbicacion);

		// Campo de texto para la ubicación
		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(150, 70, 250, 25);
		txtUbicacion.setEditable(false); 
		pnlContenedor.add(txtUbicacion);

		// Etiqueta Fecha
		JLabel lblFecha = new JLabel("FECHA: ");
		lblFecha.setFont(new Font("Roboto Light", Font.BOLD, 11));
		lblFecha.setBounds(32, 110, 100, 20);
		pnlContenedor.add(lblFecha);

		// Campo de texto para la fecha
		txtFecha = new JTextField();
		txtFecha.setBounds(150, 110, 250, 25);
		pnlContenedor.add(txtFecha);

		// Etiqueta Hora
		JLabel lblHora = new JLabel("HORA: ");
		lblHora.setFont(new Font("Roboto Light", Font.BOLD, 11));
		lblHora.setBounds(32, 150, 100, 20);
		pnlContenedor.add(lblHora);

		// Campo de texto para la hora
		txtHora = new JTextField();
		txtHora.setBounds(150, 150, 250, 25);
		pnlContenedor.add(txtHora);

		// Etiqueta Capacidad
		JLabel lblCapacidad = new JLabel("CAPACIDAD: ");
		lblCapacidad.setFont(new Font("Roboto Light", Font.BOLD, 11));
		lblCapacidad.setBounds(32, 190, 100, 20);
		pnlContenedor.add(lblCapacidad);

		// Campo de texto para la capacidad
		txtCapacidad = new JTextField();
		txtCapacidad.setBounds(150, 190, 250, 25);
		txtCapacidad.setEditable(false);
		pnlContenedor.add(txtCapacidad);

		// Etiqueta Mesa
		JLabel lblMesa = new JLabel("MESA: ");
		lblMesa.setFont(new Font("Roboto Light", Font.BOLD, 11));
		lblMesa.setBounds(32, 230, 100, 20);
		pnlContenedor.add(lblMesa);

		// Campo de texto para la mesa
		txtMesa = new JTextField();
		txtMesa.setBounds(150, 230, 250, 25);
		txtMesa.setEditable(false); 
		pnlContenedor.add(txtMesa);

		// Etiqueta Comentario
		JLabel lblComentario = new JLabel("COMENTARIO: ");
		lblComentario.setFont(new Font("Roboto Light", Font.BOLD, 11));
		lblComentario.setBounds(32, 270, 100, 20);
		pnlContenedor.add(lblComentario);

		// Área de texto para el comentario
		txtComentario = new JTextArea();
		txtComentario.setBounds(150, 270, 250, 100);
		pnlContenedor.add(txtComentario);

		// Botón para confirmar la modificación
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(150, 400, 120, 30);
		pnlContenedor.add(btnModificar);

		// Cargar datos de la reserva en los campos
		cargarDatosReserva();

		// Acción del botón de modificar
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarReserva();
			}
		});
	}

	// Método para cargar los datos de la reserva en los campos de texto
	private void cargarDatosReserva() {
	
		txtFecha.setText(reserva.getFecha());
		txtHora.setText(reserva.getHora());
	
		txtMesa.setText(String.valueOf(reserva.getIdMesa()));
		txtComentario.setText(reserva.getComentario());
	}

	// Método para modificar la reserva
	private void modificarReserva() {
		// Actualizar los campos que se pueden modificar
		reserva.setFecha(txtFecha.getText());
		reserva.setHora(txtHora.getText());
		reserva.setComentario(txtComentario.getText());

		// Llamar al controlador para actualizar la reserva
		boolean exito = reservaControlador.actualizarComprobante(reserva.getIdReserva(), reserva.getIdComprobante());
		if (exito) {
			System.out.println("Reserva modificada con éxito!");
		} else {
			System.out.println("Error al modificar la reserva!");
		}
	}
}
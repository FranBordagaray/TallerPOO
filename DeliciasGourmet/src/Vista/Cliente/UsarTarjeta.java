package Vista.Cliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.ComprobanteControlador;
import Controlador.ReservaControlador;
import Controlador.TarjetaControlador;
import Modelo.Cliente.SesionCliente;
import Modelo.Cliente.Tarjeta;
import Modelo.Complementos.Reserva;
import Vista.Empleado.VistaReservaEmpleado;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

@SuppressWarnings({"rawtypes","unused","static-access", "unchecked"})
public class UsarTarjeta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTitular;
	private JTextField txtEmisor;
	private JTextField txtNroTarjeta;
	private JTextField txtCodVerificacion;
	
	private JComboBox cbxTarjetas;
	private JComboBox<String> comboBox;
	private SesionCliente s;
	private ReservaControlador reservaControlador;
	private ComprobanteControlador comprobanteControlador;
	private String numeroTarjetaSeleccionada;
	private boolean seleccion;
	private VistaReservaCliente vistaReserva;

	TarjetaControlador controlador = new TarjetaControlador();
	Tarjeta tarjeta = new Tarjeta();
	
	public UsarTarjeta(VistaReservaCliente vistaReserva) {

		this.vistaReserva = vistaReserva; 
		this.reservaControlador = new ReservaControlador();
		this.comprobanteControlador = new ComprobanteControlador();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBackground(new Color(222, 184, 135));
		getContentPane().setLayout(null);

		// Panel contenedor
		JPanel panelContenedor = new JPanel();
		panelContenedor.setBackground(new Color(222, 184, 135));
		panelContenedor.setBounds(0, 0, 500, 400);
		getContentPane().add(panelContenedor);
		panelContenedor.setLayout(null);

		// Etiqueta para mostrar leyenda en pantalla
		JLabel lblLeyenda = new JLabel("Por favor, ingrese una tarjeta válida para confirmar su reserva");
		lblLeyenda.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLeyenda.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLeyenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeyenda.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		lblLeyenda.setBounds(50, 40, 400, 40);
		panelContenedor.add(lblLeyenda);

		// Texto Titular
		txtTitular = new JTextField();
		txtTitular.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		txtTitular.setColumns(10);
		txtTitular.setBackground(new Color(255, 255, 255));
		txtTitular.setBounds(170, 154, 300, 30);
		txtTitular.setBorder(null);
		panelContenedor.add(txtTitular);

		// Texto Emisor
		txtEmisor = new JTextField();
		txtEmisor.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		txtEmisor.setColumns(10);
		txtEmisor.setBackground(new Color(255, 255, 255));
		txtEmisor.setBounds(170, 194, 300, 30);
		txtEmisor.setBorder(null);
		panelContenedor.add(txtEmisor);

		// Texto Tarjeta
		txtNroTarjeta = new JTextField();
		txtNroTarjeta.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		txtNroTarjeta.setColumns(10);
		txtNroTarjeta.setBackground(new Color(255, 255, 255));
		txtNroTarjeta.setBounds(170, 234, 300, 30);
		txtNroTarjeta.setBorder(null);
		panelContenedor.add(txtNroTarjeta);

		// Texto Codigo verificacion
		txtCodVerificacion = new JTextField();
		txtCodVerificacion.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		txtCodVerificacion.setColumns(10);
		txtCodVerificacion.setBackground(new Color(255, 255, 255));
		txtCodVerificacion.setBounds(170, 274, 200, 30);
		txtCodVerificacion.setBorder(null);
		panelContenedor.add(txtCodVerificacion);

		// Etiqueta Tarjeta
		JLabel lblTarjeta = new JLabel("INGRESAR TARJETA");
		lblTarjeta.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 16));
		lblTarjeta.setBounds(143, 10, 213, 30);
		panelContenedor.add(lblTarjeta);

		// Etiqueta Titular
		JLabel lblTitular = new JLabel("TITULAR");
		lblTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitular.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitular.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitular.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblTitular.setBounds(10, 154, 150, 30);
		panelContenedor.add(lblTitular);

		// Etiqueta Emisor
		JLabel lblEmisor = new JLabel("EMISOR");
		lblEmisor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEmisor.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmisor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblEmisor.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblEmisor.setBounds(10, 194, 150, 30);
		panelContenedor.add(lblEmisor);

		// Etiqueta Numero Tarjeta
		JLabel lblNroTarjeta = new JLabel("NRO TARJETA");
		lblNroTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNroTarjeta.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNroTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNroTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblNroTarjeta.setBounds(10, 234, 150, 30);
		panelContenedor.add(lblNroTarjeta);

		// Etiqueta Codigo Verificacion
		JLabel lblCodVerificacion = new JLabel("COD VERIFICACION");
		lblCodVerificacion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCodVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodVerificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCodVerificacion.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblCodVerificacion.setBounds(10, 274, 150, 30);
		panelContenedor.add(lblCodVerificacion);

		// Boton Confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(UsarTarjeta.class.getResource("/Img/icono verificado.png")));
		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirmar.setBackground(new Color(126, 211, 33));
				btnConfirmar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmar.setBackground(Color.WHITE);
				btnConfirmar.setForeground(Color.BLACK);
			}
		});
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (seleccion) {
		            if (numeroTarjetaSeleccionada.equals((String)txtCodVerificacion.getText())) {
		            	vistaReserva.habilitarBoton(true);
		            	JOptionPane.showMessageDialog(UsarTarjeta.this, "Tarjeta ingresada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		            }else {
		            	JOptionPane.showMessageDialog(UsarTarjeta.this, "El código de verificación no coincide con el número de tarjeta seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		        	 try {
			                if (verificarCampos()) {
			                    return;
			                } else {
			                    tarjeta.setTitular(txtTitular.getText());
			                    tarjeta.setEmisor(txtEmisor.getText());
			                    tarjeta.setNroTarjeta(txtNroTarjeta.getText());
			                    tarjeta.setCodVerificacion(Integer.parseInt(txtCodVerificacion.getText()));

			                    if (controlador.ingresarTarjeta(tarjeta)) {
			                        JOptionPane.showMessageDialog(UsarTarjeta.this, "Tarjeta ingresada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);		                        
			                        vistaReserva.habilitarBoton(true);
			                        dispose();
			                    } else {
			                        JOptionPane.showMessageDialog(UsarTarjeta.this, "Error al ingresar la tarjeta", "Error", JOptionPane.ERROR_MESSAGE);
			                    }
			                }
			            } catch (Exception e2) {
			                JOptionPane.showMessageDialog(UsarTarjeta.this, "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            }
		        }
		       
		    }
		});
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setBorder(null);
		btnConfirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirmar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnConfirmar.setBounds(282, 346, 150, 30);
		panelContenedor.add(btnConfirmar);

		// Boton para volver a la pantalla reservas
		JButton btnAtras = new JButton("Volver");
		btnAtras.setIcon(new ImageIcon(UsarTarjeta.class.getResource("/Img/icono de volver.png")));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaReservaCliente reserva = new VistaReservaCliente();
				reserva.setVisible(true);
				UsarTarjeta.this.dispose();
			}
		});
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(255, 0, 0));
				btnAtras.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.WHITE);
				btnAtras.setForeground(Color.BLACK);
			}
		});
		btnAtras.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnAtras.setBorder(null);
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setAlignmentX(0.5f);
		btnAtras.setBounds(66, 346, 150, 30);
		panelContenedor.add(btnAtras);

		s = new SesionCliente();
		int id = s.getClienteActual().getIdCliente();
		ArrayList<Reserva> reservas = reservaControlador.obtenerHistorialDeReservasCompleta(id);

		// Combo box de Tarjetas
		cbxTarjetas = new JComboBox<String>();
		cbxTarjetas.setBackground(Color.WHITE);
		cbxTarjetas.setBorder(null);
		cbxTarjetas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbxTarjetas.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		cargarNumerosDeTarjetaEnComboBox(cbxTarjetas, obtenerIdsComprobantesPorListaReservas(reservas));
		cbxTarjetas.setBounds(170, 110, 200, 30);
		panelContenedor.add(cbxTarjetas);

		// btn Cargar tarjeta
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBackground(Color.WHITE);
		btnCargar.setFont(new Font("Roboto Light", Font.PLAIN, 14));
		btnCargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCargar.setBorder(null);
		btnCargar.addActionListener(e -> {
			Tarjeta tarjetaSeleccionada = controlador
					.obtenerDatosTarjetaConNumTarjeta((String) cbxTarjetas.getSelectedItem());

			if (tarjetaSeleccionada != null) {
				txtTitular.setText(tarjetaSeleccionada.getTitular());
				txtEmisor.setText(tarjetaSeleccionada.getEmisor());
				txtNroTarjeta.setText(tarjetaSeleccionada.getNroTarjeta());
				txtCodVerificacion.setText("");

				numeroTarjetaSeleccionada = String.valueOf(tarjetaSeleccionada.getCodVerificacion());
				System.out.println(numeroTarjetaSeleccionada);

				seleccion = true;
			} else {
				System.out.println("No hay tarjeta seleccionada.");
			}
		});
		btnCargar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnCargar.setBackground(new Color(255, 0, 0));
        		btnCargar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnCargar.setBackground(Color.WHITE);
            	btnCargar.setForeground(Color.BLACK);
            }
        });
		btnCargar.setBounds(378, 110, 92, 30);
		panelContenedor.add(btnCargar);

	}

	// Funcion para verificar campos vacios
	private boolean verificarCampos() {
		String titular = txtTitular.getText();
		String emisor = txtEmisor.getText();
		String nroTarjeta = txtNroTarjeta.getText();
		String codVerificacion = txtCodVerificacion.getText();

		if (titular.isEmpty() || emisor.isEmpty() || nroTarjeta.isEmpty() || codVerificacion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.ERROR_MESSAGE);
			return true;
		} else if (nroTarjeta.length() != 16) {
			JOptionPane.showMessageDialog(this, "El numero de tarjeta, debe ser de 16 digitos!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return true;

		} else if (codVerificacion.length() != 3) {
			JOptionPane.showMessageDialog(this, "El codigo de verificacion debe ser de 3 digitos!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return true;

		}
		return false;
	}

	// Método para obtener los IDs de comprobantes de una lista de reservas
	public ArrayList<Integer> obtenerIdsComprobantesPorListaReservas(ArrayList<Reserva> listaReservas) {
		ArrayList<Integer> idsComprobantes = new ArrayList<>();

		for (Reserva reserva : listaReservas) {
			int idComprobante = comprobanteControlador.obteneridComprobante(reserva.getIdReserva());
			if (idComprobante != 0) {
				idsComprobantes.add(idComprobante);
			} else {
				System.out.println("No se encontró comprobante para la reserva ID: " + reserva.getIdReserva());
			}
		}

		return idsComprobantes;
	}

	// Método para cargar los números de tarjeta en un JComboBox desde una lista de
	// comprobantes
	public void cargarNumerosDeTarjetaEnComboBox(JComboBox<String> comboBox, ArrayList<Integer> idsComprobantes) {
		HashSet<String> numerosTarjetas = new HashSet<>();

		for (int comprobante : idsComprobantes) {
			Tarjeta tarjeta = controlador.obtenerTarjetaPorComprobante(comprobante);
			if (tarjeta != null) {
				try {
					String nroTarjeta = tarjeta.getNroTarjeta();
					numerosTarjetas.add(nroTarjeta);
				} catch (NumberFormatException e) {
					System.out.println("Error al convertir el número de tarjeta: " + tarjeta.getNroTarjeta());
				}
			}
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

		for (String nroTarjeta : numerosTarjetas) {
			model.addElement(nroTarjeta);
		}

		comboBox.setModel(model);
	}
}
package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controlador.EmpleadoControlador;
import Modelo.Empleado.Empleado;
import javax.swing.ImageIcon;

public class GestionEmpleados extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFiltrarApellido;
    private JTable tblEmpleados;
    private DefaultTableModel model;
    private JComboBox<String> roles;
    private EmpleadoControlador controlador;

    /**
     * Create the panel.
     */
    @SuppressWarnings("serial")
    public GestionEmpleados() {
    	
    	controlador = new EmpleadoControlador();
        // Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 154, 972, 514);
        add(scrollPane);
        
        // Modelo de tabla de empleados
        tblEmpleados = new JTable();
        tblEmpleados.setGridColor(Color.DARK_GRAY);
        tblEmpleados.setBackground(Color.WHITE);
        tblEmpleados.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        tblEmpleados.setBorder(null);
        tblEmpleados.setForeground(Color.BLACK);
        tblEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "N°","ROL", "NOMBRE", "APELLIDO", "DOMICILIO", "TELEFONO", "MAIL", "USUARIO", "ESTADO"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblEmpleados.setModel(model);
		
		TableColumnModel columnModel = tblEmpleados.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(30);
     	columnModel.getColumn(1).setPreferredWidth(90);
     	columnModel.getColumn(2).setPreferredWidth(105);
     	columnModel.getColumn(3).setPreferredWidth(105);
     	columnModel.getColumn(4).setPreferredWidth(130);
     	columnModel.getColumn(5).setPreferredWidth(135);
     	columnModel.getColumn(6).setPreferredWidth(225);
     	columnModel.getColumn(7).setPreferredWidth(90);
     	columnModel.getColumn(8).setPreferredWidth(90);
     	
     	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
     	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
     	tblEmpleados.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        scrollPane.setViewportView(tblEmpleados);
        cargarDatos();
        
        // Etiqueta pantalla gestion empleados
        JLabel lblGestionEmpleados = new JLabel("Gestion empleados");
        lblGestionEmpleados.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblGestionEmpleados.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblGestionEmpleados.setBounds(396, 10, 200, 30);
        add(lblGestionEmpleados);
        
        // Etiqueta y campo de texto de filtro por apellido
        JLabel lblBuscarEmpleado = new JLabel("Buscar por apellido");
        lblBuscarEmpleado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBuscarEmpleado.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblBuscarEmpleado.setBounds(10, 95, 150, 25);
        add(lblBuscarEmpleado);
        
        txtFiltrarApellido = new JTextField();
        txtFiltrarApellido.setFont(new Font("Roboto Light", Font.PLAIN, 15));
        txtFiltrarApellido.setBorder(null);
        txtFiltrarApellido.setBounds(152, 95, 150, 25);
        add(txtFiltrarApellido);
        txtFiltrarApellido.setColumns(10);
        
        // Boton para buscar por apellido
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setIcon(new ImageIcon(GestionEmpleados.class.getResource("/Img/icono de buscar.png")));
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarPorApellido(txtFiltrarApellido.getText());
        	}
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBuscar.setBackground(new Color(64, 224, 208));
                btnBuscar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBuscar.setBackground(Color.WHITE);
                btnBuscar.setForeground(Color.BLACK);
            }
        });
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscar.setBorder(null);
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnBuscar.setBounds(310, 95, 93, 25);
        add(btnBuscar);

        // Etiqueta y combobox de filtro por rol
        JLabel lblFiltrarPorRol = new JLabel("Filtrar rol");
        lblFiltrarPorRol.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFiltrarPorRol.setAlignmentX(0.5f);
        lblFiltrarPorRol.setBounds(10, 125, 70, 25);
        add(lblFiltrarPorRol);
        
        roles = new JComboBox<>();
        roles.setModel(new DefaultComboBoxModel<>(new String[] {"TODOS", "ADMIN", "RECEPCION", "MAITRE", "MESERO"}));
        roles.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        roles.setBorder(null);
        roles.setBackground(Color.WHITE);
        roles.setBounds(80, 125, 137, 25);
        add(roles);

        roles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rolSeleccionado = (String) roles.getSelectedItem();
                filtrarEstado(rolSeleccionado);
            }
        });
        
        // Boton para acceder a formulario de registro de empleados
        JButton btnAgregarEmpleado = new JButton("AGREGAR");
        btnAgregarEmpleado.setIcon(new ImageIcon(GestionEmpleados.class.getResource("/Img/icono empleado.png")));
        btnAgregarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistroEmpleado registro = new RegistroEmpleado();
                registro.setVisible(true);              
            }
        });
        btnAgregarEmpleado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAgregarEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAgregarEmpleado.setBackground(new Color(255, 0, 0));
                btnAgregarEmpleado.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAgregarEmpleado.setBackground(Color.WHITE);
                btnAgregarEmpleado.setForeground(Color.BLACK);
            }
        });
        btnAgregarEmpleado.setForeground(Color.BLACK);
        btnAgregarEmpleado.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnAgregarEmpleado.setBorder(null);
        btnAgregarEmpleado.setBackground(Color.WHITE);
        btnAgregarEmpleado.setAlignmentX(0.5f);
        btnAgregarEmpleado.setBounds(865, 95, 117, 25);
        add(btnAgregarEmpleado);
        
        // Botón para eliminar un empleado
        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setIcon(new ImageIcon(GestionEmpleados.class.getResource("/Img/icono de perfil.png")));
        btnModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int selectedRow = tblEmpleados.getSelectedRow();
                if (selectedRow != -1) {
                    int idEmpleado = (int) model.getValueAt(selectedRow, 0);
                    System.out.println("ID del empleado seleccionado: " + idEmpleado);

                    ModificarEmpleado modificar = new ModificarEmpleado(controlador.obtenerEmpleadoPorId(idEmpleado));
                    modificar.setVisible(true);
                } else {
                    System.out.println("Por favor, selecciona un empleado para modificar.");
                }
            }
        });
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnModificar.setBorder(null);
        btnModificar.setBackground(Color.WHITE);
        btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnModificar.setAlignmentX(0.5f);
        btnModificar.setBounds(736, 95, 119, 25);
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnModificar.setBackground(new Color(76, 175, 80));
            	btnModificar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnModificar.setBackground(Color.WHITE);
            	btnModificar.setForeground(Color.BLACK);
            }
        });
        add(btnModificar);
        
        // Boton para reestablecer la contraseña
        JButton btnResetearClave = new JButton("RESETEAR CLAVE");
        btnResetearClave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = tblEmpleados.getSelectedRow();
        		 if (selectedRow != -1) {
                     int idEmpleado = (int) model.getValueAt(selectedRow, 0);
                     System.out.println("ID del empleado seleccionado: " + idEmpleado);

                     ReestablecerClaveEmpleado resetClave = new ReestablecerClaveEmpleado(idEmpleado);
                     resetClave.setVisible(true);
                 } else {
                     System.out.println("Por favor, selecciona un empleado para modificar.");
                 }
        	}
        });
        btnResetearClave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnResetearClave.setBackground(new Color(76, 175, 80));
            	btnResetearClave.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnResetearClave.setBackground(Color.WHITE);
            	btnResetearClave.setForeground(Color.BLACK);
            }
        });
        btnResetearClave.setIcon(new ImageIcon(GestionEmpleados.class.getResource("/Img/icono modificar.png")));
        btnResetearClave.setForeground(Color.BLACK);
        btnResetearClave.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnResetearClave.setBorder(null);
        btnResetearClave.setBackground(Color.WHITE);
        btnResetearClave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnResetearClave.setAlignmentX(0.5f);
        btnResetearClave.setBounds(526, 95, 200, 25);
        add(btnResetearClave);
        
        JButton btnActualizarTabla = new JButton("ACTUALIZAR TABLA");
        btnActualizarTabla.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cargarDatos();
        	}
        });
        btnActualizarTabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActualizarTabla.setBackground(new Color(255, 0, 0));
				btnActualizarTabla.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnActualizarTabla.setForeground(Color.BLACK);
				btnActualizarTabla.setBackground(Color.WHITE);
			}
		});
        btnActualizarTabla.setIcon(new ImageIcon(GestionEmpleados.class.getResource("/Img/icono verificado.png")));
        btnActualizarTabla.setForeground(Color.BLACK);
        btnActualizarTabla.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnActualizarTabla.setBorder(null);
        btnActualizarTabla.setBackground(Color.WHITE);
        btnActualizarTabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizarTabla.setAlignmentX(0.5f);
        btnActualizarTabla.setBounds(227, 125, 200, 25);
        add(btnActualizarTabla);
        
    }
    
    // Funcion para cargar tabla con datos almacenados en base de datos 
    public void cargarDatos() {
        EmpleadoControlador controlador = new EmpleadoControlador();
        List<Empleado> empleados = controlador.obtenerEmpleados();

        model.setRowCount(0);

        for (Empleado empleado : empleados) {
            model.addRow(new Object[]{
            	empleado.getIdEmpleado(),
                empleado.getRol().name(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDomicilio(),
                empleado.getTelefono(),
                empleado.getEmail(),
                empleado.getUsuario(),
                empleado.getEstado()
            });
        }
    }
    
    // Funcion para filtrar los empleados segun su rol
    private void filtrarEstado(String estado) {
        model.setRowCount(0);
        EmpleadoControlador controlador = new EmpleadoControlador();
        List<Empleado> empleados = controlador.obtenerEmpleados();

        for (Empleado empleado : empleados) {
            if (estado.equals("TODOS") || empleado.getRol().name().equals(estado)) {
                model.addRow(new Object[]{
                	empleado.getIdEmpleado(),
                    empleado.getRol().name(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDomicilio(),
                    empleado.getTelefono(),
                    empleado.getEmail(),
                    empleado.getUsuario(),
                    empleado.getEstado()
                });
            }
        }
    }
    
    // Funcion para buscar empleados por apellido
    private void buscarPorApellido(String apellido) {
        model.setRowCount(0);
        
        EmpleadoControlador controlador = new EmpleadoControlador();
        List<Empleado> empleados = controlador.obtenerEmpleados();

        for (Empleado empleado : empleados) {
            if (empleado.getApellido().toLowerCase().contains(apellido.toLowerCase())) {
                model.addRow(new Object[]{
                	empleado.getIdEmpleado(),
                    empleado.getRol().name(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDomicilio(),
                    empleado.getTelefono(),
                    empleado.getEmail(),
                    empleado.getUsuario(),
                    empleado.getEstado()
                });
            }
        }
    }
}
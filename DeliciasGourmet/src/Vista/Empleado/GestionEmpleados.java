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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controlador.EmpleadoControlador;
import Modelo.Empleado;

public class GestionEmpleados extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFiltrarApellido;
    private JTable tblEmpleados;
    private DefaultTableModel model;
    private JComboBox<String> roles;

    /**
     * Create the panel.
     */
    @SuppressWarnings("serial")
    public GestionEmpleados() {
        // Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBounds(10, 154, 972, 514);
        add(scrollPane);
        
        // Modelo de tabla de empleados
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ROL", "NOMBRE", "APELLIDO", "DOMICILIO", "TELEFONO", "MAIL", "USUARIO", "CONTRASEÑA"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblEmpleados = new JTable();
        tblEmpleados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblEmpleados.setModel(model);
        tblEmpleados.setBorder(null);
        tblEmpleados.setCellSelectionEnabled(true);
        tblEmpleados.setFont(new Font("Roboto Light", Font.PLAIN, 16));
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
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarPorApellido(txtFiltrarApellido.getText());
        	}
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBuscar.setBackground(new Color(255, 0, 0));
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
        btnAgregarEmpleado.setBounds(889, 95, 93, 25);
        add(btnAgregarEmpleado);
        
        // Botón para eliminar un empleado
        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblEmpleados.getSelectedRow(); 
                if (selectedRow != -1) { 
                    String usuario = (String) model.getValueAt(selectedRow, 6); 
                    EmpleadoControlador controlador = new EmpleadoControlador();
                    boolean eliminado = controlador.eliminarEmpleado(usuario); 

                    if (eliminado) {
                        cargarDatos(); 
                    } else {                      
                        System.out.println("Error al eliminar el empleado.");
                    }
                } else {                   
                    System.out.println("Por favor, selecciona un empleado para eliminar.");
                }
            }
        });
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnEliminar.setBorder(null);
        btnEliminar.setBackground(Color.WHITE);
        btnEliminar.setAlignmentX(0.5f);
        btnEliminar.setBounds(776, 95, 93, 25);
        add(btnEliminar);
        
    }
    
    // Funcion para cargar tabla con datos almacenados en base de datos 
    private void cargarDatos() {
        EmpleadoControlador controlador = new EmpleadoControlador();
        List<Empleado> empleados = controlador.obtenerEmpleados();

        model.setRowCount(0);

        for (Empleado empleado : empleados) {
            model.addRow(new Object[]{
                empleado.getRol().name(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDomicilio(),
                empleado.getTelefono(),
                empleado.getEmail(),
                empleado.getUsuario(),
                empleado.getContrasenia()
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
                    empleado.getRol().name(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDomicilio(),
                    empleado.getTelefono(),
                    empleado.getEmail(),
                    empleado.getUsuario(),
                    empleado.getContrasenia()
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
                    empleado.getRol().name(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDomicilio(),
                    empleado.getTelefono(),
                    empleado.getEmail(),
                    empleado.getUsuario(),
                    empleado.getContrasenia()
                });
            }
        }
    }
}
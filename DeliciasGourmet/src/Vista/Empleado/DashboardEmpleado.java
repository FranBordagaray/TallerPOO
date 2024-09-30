package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Modelo.SesionEmpleado;
import Vista.Cliente.Dashboard;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;

public class DashboardEmpleado extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel lblFechaHora;
    private JTable table;
    private JTextField txtBuscarCliente;

    @SuppressWarnings({ "static-access", "serial" })
    public DashboardEmpleado() {
        // Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));

        // Panel de Bienvenida
        JPanel pnlBienvenido = new JPanel();
        pnlBienvenido.setBackground(new Color(195, 155, 107));
        pnlBienvenido.setBounds(0, 0, 992, 50);
        add(pnlBienvenido);
        pnlBienvenido.setLayout(null);

        // Etiqueta de Bienvenida
        JLabel lblBienvenido = new JLabel("Bienvenido, ");
        lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenido.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/ImgHome.png")));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenido.setForeground(Color.BLACK);
        lblBienvenido.setFont(new Font("Roboto Light", Font.BOLD, 16));
        lblBienvenido.setBounds(0, 0, 170, 50);
        pnlBienvenido.add(lblBienvenido);

        // Etiqueta Nombre de Usuario
        SesionEmpleado s1 = new SesionEmpleado();
        JLabel lblNombreUsuario = new JLabel(s1.getEmpleadoActual().getNombre() + " "
                + s1.getEmpleadoActual().getApellido() + " - " + s1.getEmpleadoActual().getRol());
        lblNombreUsuario.setForeground(Color.BLACK);
        lblNombreUsuario.setFont(new Font("Roboto Light", Font.BOLD, 16));
        lblNombreUsuario.setBounds(170, 0, 330, 50);
        pnlBienvenido.add(lblNombreUsuario);

        // Etiqueta fecha y hora
        lblFechaHora = new JLabel();
        lblFechaHora.setBorder(null);
        lblFechaHora.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaHora.setForeground(Color.BLACK);
        lblFechaHora.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblFechaHora.setBounds(792, 12, 200, 25);
        pnlBienvenido.add(lblFechaHora);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 369, 972, 300);
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Cliente", "Ubicacion", "Mesa", "Hora", "Evento especial"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        scrollPane.setViewportView(table);

        // Etiqueta y campo de texto para buscar reservas de clientes
        JLabel lblBuscarCliente = new JLabel("BUSCAR CLIENTE");
        lblBuscarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBuscarCliente.setForeground(Color.BLACK);
        lblBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        lblBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscarCliente.setBounds(150, 333, 200, 25);
        add(lblBuscarCliente);

        txtBuscarCliente = new JTextField();
        txtBuscarCliente.setForeground(Color.BLACK);
        txtBuscarCliente.setBackground(Color.WHITE);
        txtBuscarCliente.setBorder(null);
        txtBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
        txtBuscarCliente.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        txtBuscarCliente.setBounds(350, 333, 200, 25);
        add(txtBuscarCliente);
        txtBuscarCliente.setColumns(10);

        // Boton para buscar reservas de clientes
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscar.setBorder(null);
        btnBuscar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnBuscar.setBounds(560, 333, 150, 25);
        add(btnBuscar);

        // Iniciar el Timer para actualizar la fecha y hora
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                actualizarFechaHora();
            }
        }, 0, 1000);
    }

    // Método para actualizar la fecha y hora
    private void actualizarFechaHora() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaHoraFormateada = fechaHoraActual.format(formato);
        SwingUtilities.invokeLater(() -> lblFechaHora.setText(fechaHoraFormateada));
    }
}
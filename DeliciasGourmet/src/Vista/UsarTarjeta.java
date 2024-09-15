package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class UsarTarjeta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtTitular;
    private JTextField txtEmisor;
    private JTextField txtNroTarjeta;
    private JTextField txtCodVerificacion;

    public UsarTarjeta() {
    	
    
        // Configuración del JFrame
        setTitle("Ingresar Tarjeta");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(222, 184, 135));

        // Panel contenedor
        JPanel panelContenedor = new JPanel();
        panelContenedor.setBackground(new Color(222, 184, 135));
        panelContenedor.setBounds(0, 0, 450, 300);
        panelContenedor.setLayout(null);
        add(panelContenedor);

        // Texto Titular
        txtTitular = new JTextField();
        txtTitular.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtTitular.setColumns(10);
        txtTitular.setBackground(Color.WHITE);
        txtTitular.setBounds(168, 50, 213, 25);
        txtTitular.setBorder(null);
        panelContenedor.add(txtTitular);

        // Texto Emisor
        txtEmisor = new JTextField();
        txtEmisor.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtEmisor.setColumns(10);
        txtEmisor.setBackground(Color.WHITE);
        txtEmisor.setBounds(168, 90, 213, 25);
        txtEmisor.setBorder(null);
        panelContenedor.add(txtEmisor);

        // Texto Tarjeta
        txtNroTarjeta = new JTextField();
        txtNroTarjeta.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtNroTarjeta.setColumns(10);
        txtNroTarjeta.setBackground(Color.WHITE);
        txtNroTarjeta.setBounds(168, 130, 213, 25);
        txtNroTarjeta.setBorder(null);
        panelContenedor.add(txtNroTarjeta);

        // Texto Codigo verificacion
        txtCodVerificacion = new JTextField();
        txtCodVerificacion.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtCodVerificacion.setColumns(10);
        txtCodVerificacion.setBackground(Color.WHITE);
        txtCodVerificacion.setBounds(168, 170, 100, 25);
        txtCodVerificacion.setBorder(null);
        panelContenedor.add(txtCodVerificacion);

        // Etiqueta Tarjeta
        JLabel lblTarjeta = new JLabel("TARJETA");
        lblTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 16));
        lblTarjeta.setBounds(179, 10, 91, 14);
        panelContenedor.add(lblTarjeta);

        // Etiqueta Titular
        JLabel lblTitular = new JLabel("TITULAR");
        lblTitular.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblTitular.setBounds(44, 50, 120, 25);
        panelContenedor.add(lblTitular);

        // Etiqueta Emisor
        JLabel lblEmisor = new JLabel("EMISOR");
        lblEmisor.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblEmisor.setBounds(44, 90, 120, 25);
        panelContenedor.add(lblEmisor);
        
        // Etiqueta Numero Tarjeta
        JLabel lblNroTarjeta = new JLabel("NRO TARJETA");
        lblNroTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblNroTarjeta.setBounds(44, 130, 120, 25);
        panelContenedor.add(lblNroTarjeta);
        
        // Etiqueta Codigo Verificacion
        JLabel lblCodVerificacion = new JLabel("COD VERIFICACION");
        lblCodVerificacion.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblCodVerificacion.setBounds(44, 170, 120, 25);
        panelContenedor.add(lblCodVerificacion);
        
        // Boton Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setIcon(new ImageIcon(UsarTarjeta.class.getResource("/Img/icono de guardar.png")));
        btnGuardar.setFont(new Font("Roboto Light", Font.BOLD, 16));
        btnGuardar.setBounds(168, 217, 101, 23);
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelContenedor.add(btnGuardar);

        // Evento boton guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guarda tarjeta
                JOptionPane.showMessageDialog(UsarTarjeta.this, "Tarjeta ingresada con éxito", "Ingresar tarjeta",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        // Evento boton guardar para cambios de color
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnGuardar.setBackground(Color.RED);
                btnGuardar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnGuardar.setBackground(Color.WHITE);
                btnGuardar.setForeground(Color.BLACK);
            }
        });
   }
}
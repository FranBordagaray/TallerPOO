package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class UsarTarjeta extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtTitular;
    private JTextField txtEmisor;
    private JTextField txtNroTarjeta;
    private JTextField txtCodVerificacion;

    /**
     * Create the panel.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    UsarTarjeta usarTarjeta = new UsarTarjeta();
                    frame.setContentPane(usarTarjeta);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100, 100, 450, 300);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UsarTarjeta() {
        setBackground(new Color(222, 184, 135));
        setLayout(null);
        

        JPanel panelContenedor = new JPanel();
        panelContenedor.setBackground(new Color(222, 184, 135));
        panelContenedor.setBounds(0, 0, 450, 300);
        add(panelContenedor);
        panelContenedor.setLayout(null);

        txtTitular = new JTextField();
        txtTitular.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtTitular.setColumns(10);
        txtTitular.setBackground(new Color(255, 255, 255));
        txtTitular.setBounds(168, 50, 213, 25);
        txtTitular.setBorder(null);
        panelContenedor.add(txtTitular);

        txtEmisor = new JTextField();
        txtEmisor.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtEmisor.setColumns(10);
        txtEmisor.setBackground(new Color(255, 255, 255));
        txtEmisor.setBounds(168, 90, 213, 25);
        txtEmisor.setBorder(null);
        panelContenedor.add(txtEmisor);

        txtNroTarjeta = new JTextField();
        txtNroTarjeta.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtNroTarjeta.setColumns(10);
        txtNroTarjeta.setBackground(new Color(255, 255, 255));
        txtNroTarjeta.setBounds(168, 130, 213, 25);
        txtNroTarjeta.setBorder(null);
        panelContenedor.add(txtNroTarjeta);

        txtCodVerificacion = new JTextField();
        txtCodVerificacion.setFont(new Font("Roboto Light", Font.PLAIN, 12));
        txtCodVerificacion.setColumns(10);
        txtCodVerificacion.setBackground(new Color(255, 255, 255));
        txtCodVerificacion.setBounds(168, 170, 100, 25);
       txtCodVerificacion.setBorder(null);
        panelContenedor.add(txtCodVerificacion);
        
        JLabel lblTarjeta = new JLabel("TARJETA");
        lblTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 16));
        lblTarjeta.setBounds(179, 10, 91, 14);
        panelContenedor.add(lblTarjeta);
        
        JLabel lblTitular = new JLabel("TITULAR");
        lblTitular.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitular.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblTitular.setBounds(44, 50, 120, 25);
        panelContenedor.add(lblTitular);
        
        JLabel lblEmisor = new JLabel("EMISOR");
        lblEmisor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEmisor.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblEmisor.setBounds(44, 90, 120, 25);
        panelContenedor.add(lblEmisor);
        
        JLabel lblNroTarjeta = new JLabel("NRO TARJETA");
        lblNroTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNroTarjeta.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblNroTarjeta.setBounds(44, 130, 120, 25);
        panelContenedor.add(lblNroTarjeta);
        
        JLabel lblCodVerificacion = new JLabel("COD VERIFICACION");
        lblCodVerificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCodVerificacion.setFont(new Font("Roboto Light", Font.BOLD, 12));
        lblCodVerificacion.setBounds(44, 170, 120, 25);
        panelContenedor.add(lblCodVerificacion);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Guarda tarjeta 
				JOptionPane.showMessageDialog(UsarTarjeta.this, "Tarjeta ingresada con exito", "Ingresar tarjeta", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		});

        btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGuardar.setBackground(new Color(255, 0, 0));
				btnGuardar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBackground(Color.WHITE);
				btnGuardar.setForeground(Color.BLACK);
			}
		});
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBorder(null);
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnGuardar.setBounds(180, 217, 89, 23);
        panelContenedor.add(btnGuardar);
        
    }
}


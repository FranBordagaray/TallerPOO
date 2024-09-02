package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PerfilEmpleado extends JPanel {
    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtContrasenia;
    private JTextField txtDomicilio;
    private JTextField txtEmail;
    private JTextField txtTelefono;

    private static final long serialVersionUID = 1L;
    
    /**
     * Create the panel.
     */
    public PerfilEmpleado() {
        // Configuración del panel
        setLayout(null);
        setPreferredSize(new Dimension(992, 679));
        setBackground(new Color(222, 184, 135));

        // Etiqueta de perfil
        JLabel lblRegistro = new JLabel("PERFIL");
        lblRegistro.setFont(new Font("Roboto Light", Font.BOLD, 32));
        lblRegistro.setBounds(433, 20, 200, 40);
        add(lblRegistro);

        // Etiqueta y campo de texto de nombre
        JLabel lblNombre = new JLabel("NOMBRE: ");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblNombre.setAlignmentX(1.0f);
        lblNombre.setBounds(35, 170, 150, 30);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtNombre.setColumns(10);
        txtNombre.setBounds(185, 170, 250, 30);
        add(txtNombre);

        // Etiqueta y campo de texto de apellido
        JLabel lblApellido = new JLabel("APELLIDO: ");
        lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
        lblApellido.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblApellido.setAlignmentX(1.0f);
        lblApellido.setBounds(535, 170, 150, 30);
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtApellido.setColumns(10);
        txtApellido.setBounds(685, 170, 250, 30);
        add(txtApellido);

        // Etiqueta y campo de texto de domicilio
        JLabel lblDomicilio = new JLabel("DOMICILIO: ");
        lblDomicilio.setHorizontalAlignment(SwingConstants.CENTER);
        lblDomicilio.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblDomicilio.setAlignmentX(1.0f);
        lblDomicilio.setBounds(35, 250, 150, 30);
        add(lblDomicilio);

        txtDomicilio = new JTextField();
        txtDomicilio.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtDomicilio.setColumns(10);
        txtDomicilio.setBounds(185, 250, 250, 30);
        add(txtDomicilio);

        // Etiqueta y campo de texto de teléfono
        JLabel lblTelefono = new JLabel("TELÉFONO: ");
        lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
        lblTelefono.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblTelefono.setAlignmentX(1.0f);
        lblTelefono.setBounds(535, 250, 150, 30);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtTelefono.setColumns(10);
        txtTelefono.setBounds(685, 250, 250, 30);
        add(txtTelefono);

        // Etiqueta y campo de texto de email
        JLabel lblEmail = new JLabel("EMAIL: ");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblEmail.setAlignmentX(1.0f);
        lblEmail.setBounds(35, 330, 150, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtEmail.setColumns(10);
        txtEmail.setBounds(185, 330, 250, 30);
        add(txtEmail);

        // Etiqueta y campo de texto de usuario
        JLabel lblUsuario = new JLabel("USUARIO: ");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblUsuario.setAlignmentX(1.0f);
        lblUsuario.setBounds(535, 330, 150, 30);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtUsuario.setBounds(685, 330, 250, 30);
        add(txtUsuario);
        txtUsuario.setColumns(10);

        // Etiqueta y campo de texto de contraseña
        JLabel lblContrasenia = new JLabel("CONTRASEÑA:");
        lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
        lblContrasenia.setFont(new Font("Roboto Light", Font.BOLD, 22));
        lblContrasenia.setAlignmentX(0.5f);
        lblContrasenia.setBounds(185, 410, 200, 30);
        add(lblContrasenia);

        txtContrasenia = new JTextField();
        txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        txtContrasenia.setColumns(10);
        txtContrasenia.setBounds(385, 410, 250, 30);
        add(txtContrasenia);
        
     // Boton para actualizar informacion de contacto
     		JButton btnGuardar = new JButton("Guardar Cambios");
     		btnGuardar.setIcon(new ImageIcon(ActualizarPerfil.class.getResource("/Img/ImgGuardar.jpg")));
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
     		btnGuardar.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     					}
     				});
     		btnGuardar.setForeground(Color.BLACK);
     		btnGuardar.setFont(new Font("Roboto Light", Font.BOLD, 16));
     		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     		btnGuardar.setBorder(null);
     		btnGuardar.setBackground(Color.WHITE);
     		btnGuardar.setAlignmentX(0.5f);
     		btnGuardar.setBounds(371, 601, 250, 30);
     		add(btnGuardar);
    }
}
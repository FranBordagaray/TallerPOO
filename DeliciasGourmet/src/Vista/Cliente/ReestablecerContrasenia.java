package Vista.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controlador.ClienteControlador;
import Modelo.Cliente.Cliente;

import java.awt.Cursor;
import java.awt.Component;
import javax.swing.ImageIcon;


/**
 * Clase que representa un diálogo para el restablecimiento de contraseña.
 *
 * Esta clase extiende JDialog y proporciona una interfaz para que los usuarios
 * ingresen su correo electrónico y reciban instrucciones para restablecer su
 * contraseña. La ventana incluye campos para la entrada del correo electrónico,
 * así como botones para confirmar el restablecimiento o cancelar la operación.
 *
 * El diálogo se puede invocar desde otras partes de la aplicación, permitiendo
 * un acceso fácil al proceso de recuperación de contraseña.
 */
@SuppressWarnings("unused")
public class ReestablecerContrasenia extends JDialog {
    
    private static final long serialVersionUID = 1L;
    /**
     * Panel que contiene los componentes del diálogo para restablecer la contraseña.
     */
    private final JPanel contentPanel = new JPanel();

    /**
     * Campo de texto para ingresar la nueva contraseña.
     */
    private JTextField txtContrasenia;

    /**
     * Campo de texto para repetir la nueva contrasenia, utilizado para la confirmación.
     */
    private JTextField txtRepetirContrasenia;

    /**
     * Controlador que gestiona la lógica de cliente, incluyendo la actualización de contraseñas.
     */
    private ClienteControlador controlador;

    /**
     * Correo electrónico del cliente para el cual se restablecerá la contraseña.
     */
    private String email;

    /**
     * Constructor para crear una instancia de ReestablecerContrasenia.
     *
     * Este constructor inicializa el objeto con el correo electrónico proporcionado,
     * que se utilizará para enviar un enlace de restablecimiento de contraseña.
     *
     * @param email El correo electrónico del usuario que solicita el restablecimiento
     *              de la contraseña.
     */
    public ReestablecerContrasenia(String email) {
        this.email = email;
        controlador = new ClienteControlador();
        //Configuracion del dialog
		setResizable(false);
        setBounds(1, 1, 600, 400);
        setUndecorated(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(195, 155, 107));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        // Intrucciones de pantalla
        JLabel lblIntrucciones = new JLabel("Por favor, ingrese su nueva contraseña en los siguiente campos");
        lblIntrucciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblIntrucciones.setForeground(Color.BLACK);
        lblIntrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblIntrucciones.setFont(new Font("Roboto Light", Font.BOLD, 19));
        lblIntrucciones.setBounds(0, 27, 600, 30);
        contentPanel.add(lblIntrucciones);
        
        // Etiqueta y campo de texto de contraseña nueva
        JLabel lblContrasenia = new JLabel("CONTRASEÑA NUEVA");
        lblContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
        lblContrasenia.setForeground(Color.BLACK);
        lblContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
        lblContrasenia.setBounds(100, 97, 400, 30);
        contentPanel.add(lblContrasenia);
        
        txtContrasenia = new JTextField();
        txtContrasenia.setBorder(null);
        txtContrasenia.setBackground(Color.WHITE);
        txtContrasenia.setForeground(Color.BLACK);
        txtContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
        txtContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        txtContrasenia.setBounds(100, 138, 400, 30);
        contentPanel.add(txtContrasenia);
        txtContrasenia.setColumns(10);
        
        // Etiqueta y campo de texto de repeticion de contraseña
        JLabel lblRepetirContrasenia = new JLabel("REPETIR CONTRASEÑA NUEVA");
        lblRepetirContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
        lblRepetirContrasenia.setForeground(Color.BLACK);
        lblRepetirContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 20));
        lblRepetirContrasenia.setBounds(100, 192, 400, 30);
        contentPanel.add(lblRepetirContrasenia);
        
        txtRepetirContrasenia = new JTextField();
        txtRepetirContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
        txtRepetirContrasenia.setForeground(Color.BLACK);
        txtRepetirContrasenia.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        txtRepetirContrasenia.setColumns(10);
        txtRepetirContrasenia.setBorder(null);
        txtRepetirContrasenia.setBackground(Color.WHITE);
        txtRepetirContrasenia.setBounds(100, 233, 400, 30);
        contentPanel.add(txtRepetirContrasenia);
        
        /**
         * Crea un botón para actualizar la contraseña del cliente.
         * Al hacer clic en el botón, se verifica que los campos de contraseña no estén vacíos
         * y que cumplan con los requisitos de longitud y coincidencia. Si las validaciones son correctas,
         * se intenta actualizar la contraseña del cliente a través del controlador.
         * Si la actualización es exitosa, se muestra un mensaje de éxito y se abre la ventana de login;
         * de lo contrario, se muestra un mensaje de error.
         */
        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setIcon(new ImageIcon(ReestablecerContrasenia.class.getResource("/Img/icono verificado.png")));
        btnActualizar.setBackground(Color.WHITE);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (verificarCampos()) {
                        return;
                    } else {
                        String contraseniaNueva = txtContrasenia.getText();
                        Cliente cliente = new Cliente();
                        String codigoNuevo = cliente.generarCodigoRecuperacion();
                        if (controlador.recuperarContrasena(email, contraseniaNueva, codigoNuevo)) {
                            JOptionPane.showMessageDialog(ReestablecerContrasenia.this, "Contraseña actualizada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            LoginCliente login = new LoginCliente();
                            login.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(ReestablecerContrasenia.this, "Error al actualizar la contraseña!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(ReestablecerContrasenia.this, "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActualizar.setBackground(new Color(255, 0, 0));
				btnActualizar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnActualizar.setBackground(Color.WHITE);
				btnActualizar.setForeground(Color.BLACK);
			}
		});
        btnActualizar.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        btnActualizar.setBounds(175, 306, 250, 30);
        contentPanel.add(btnActualizar);
    }
    
    /**
     * Verifica si los campos de contraseña están completos y cumplen con los requisitos.
     * 
     * @return true si hay campos vacíos, las contraseñas no coinciden, 
     *         o la contraseña es menor de 8 caracteres; de lo contrario, false.
     */
    private boolean verificarCampos() {
        String contrasenia = txtContrasenia.getText();
        String repetir = txtRepetirContrasenia.getText();
        if (contrasenia.isEmpty() || repetir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return true;
        } else if (!contrasenia.equals(repetir)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        } else if (contrasenia.length() < 8) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
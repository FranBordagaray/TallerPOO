package Vista.Empleado;

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

import Controlador.EmpleadoControlador;

import java.awt.Cursor;
import java.awt.Component;
import javax.swing.ImageIcon;

/**
 * Clase que representa un diálogo para reestablecer la contraseña de un empleado en el sistema.
 * Permite al usuario ingresar una nueva contraseña y confirmarla.
 */
public class ReestablecerClaveEmpleado extends JDialog {

    private static final long serialVersionUID = 1L; // Identificador único de la clase
    private final JPanel contentPanel = new JPanel(); // Panel principal del diálogo
    private JTextField txtContrasenia; // Campo de texto para la nueva contraseña
    private JTextField txtRepetirContrasenia; // Campo de texto para repetir la nueva contraseña
    private EmpleadoControlador controlador; // Controlador para la gestión de empleados

    /**
     * Constructor de la clase ReestablecerClaveEmpleado.
     * Inicializa el diálogo con campos para ingresar y confirmar la nueva contraseña del empleado.
     *
     * @param idEmpleado El ID del empleado cuya contraseña se desea reestablecer.
     */
    public ReestablecerClaveEmpleado(int idEmpleado) {
        controlador = new EmpleadoControlador();
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
        
        // Boton para actualizar la contraseña
        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setIcon(new ImageIcon(ReestablecerClaveEmpleado.class.getResource("/Img/icono verificado.png")));
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
                        System.out.println("id recibido: " + idEmpleado);
                        if (controlador.actualizarContrasenia(contraseniaNueva, idEmpleado)) {
                            JOptionPane.showMessageDialog(ReestablecerClaveEmpleado.this, "Contraseña actualizada exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(ReestablecerClaveEmpleado.this, "Error al actualizar la contraseña!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(ReestablecerClaveEmpleado.this, "Error inesperado: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    
    // Funcion para verificar campos vacios
    private boolean verificarCampos() {
    	 /**
         * Verifica si los campos de contraseña están completos y válidos.
         *
         * Este método comprueba si los campos de contraseña y repetir contraseña
         * están llenos. Además, verifica que ambas contraseñas coincidan y que la
         * longitud de la contraseña sea al menos de 8 caracteres. Si hay algún 
         * problema, se muestra un mensaje de advertencia o error correspondiente.
         *
         * @return boolean `true` si hay un error en los campos (vacíos, no coinciden o corta), 
         *                 `false` si todos los campos son válidos.
         */
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
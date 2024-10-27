package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JSeparator;

public class ConfirmacionDeCancelacion extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean confirmacion = false;

	public ConfirmacionDeCancelacion() {
		setModal(true); // Hace que la ventana sea modal
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 360, 135);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblTitulo = new JLabel("CONFIRMAR CANCELACIÓN");
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 21));
		lblTitulo.setBounds(39, 10, 280, 34);
		contentPane.add(lblTitulo);

		// Boton para enviar correo de recuperación
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmacion = false;
				dispose();
			}

		});
		btnCancelar.setIcon(new ImageIcon(ConfirmacionDeCancelacion.class.getResource("/Img/icono cancelar.png")));

		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(new Color(255, 0, 0));
				btnCancelar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setForeground(Color.BLACK);
			}
		});
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setAlignmentX(0.5f);
		btnCancelar.setBounds(23, 84, 116, 30);
		contentPane.add(btnCancelar);

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmacion = false;
				setVisible(false);
			}
		});

		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCerrar.setBackground(new Color(255, 0, 0));
				btnCerrar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCerrar.setForeground(Color.BLACK);
				btnCerrar.setBackground(new Color(195, 155, 107));
			}
		});
		btnCerrar.setBackground(new Color(195, 155, 107));
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.setBorder(null);
		btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCerrar.setFont(new Font("Roboto Light", Font.BOLD, 16));
		btnCerrar.setBounds(329, 0, 31, 22);
		contentPane.add(btnCerrar);

		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmacion = true;
				dispose();
			}
		});
		btnConfirmar.setIcon(new ImageIcon(ConfirmacionDeCancelacion.class.getResource("/Img/icono verificado.png")));
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnConfirmar.setBorder(null);
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setAlignmentX(0.5f);
		btnConfirmar.setBounds(222, 84, 116, 30);
		contentPane.add(btnConfirmar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 54, 328, 2);
		contentPane.add(separator);

	}

	// Método para mostrar la ventana y retornar el resultado de la confirmación
	public boolean mostrarConfirmacion() {
		setVisible(true);
		return confirmacion;
	}
}

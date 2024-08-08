package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Dashboard() {
		setLayout(null);
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));
		
		JPanel pnlBienvenido = new JPanel();
		pnlBienvenido.setBounds(10, 10, 478, 84);
		add(pnlBienvenido);
		
		JPanel pnlNotificaciones = new JPanel();
		pnlNotificaciones.setBounds(498, 10, 484, 386);
		add(pnlNotificaciones);
		
		JLabel lblNewLabel = new JLabel("Notificaciones");
		pnlNotificaciones.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 104, 273, 418);
		add(panel);
	}
}

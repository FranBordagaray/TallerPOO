package Vista.Cliente;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class OpcionesMenus extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public OpcionesMenus() {
		setPreferredSize(new Dimension(992, 679));
		setBackground(new Color(222, 184, 135));
		setLayout(null);
		
		// Separador vertical
		JSeparator separadorVertical = new JSeparator();
		separadorVertical.setOrientation(SwingConstants.VERTICAL);
		separadorVertical.setForeground(Color.BLACK);
		separadorVertical.setBackground(Color.BLACK);
		separadorVertical.setBounds(495, 2, 2, 675);
		add(separadorVertical);
		
		// Separador horizontal
		JSeparator separadorHorizontal = new JSeparator();
		separadorHorizontal.setForeground(Color.BLACK);
		separadorHorizontal.setBackground(Color.BLACK);
		separadorHorizontal.setBounds(1, 40, 990, 2);
		add(separadorHorizontal);
		
		// Etiqueta de título para la sección de entradas
		JLabel lblEntradas = new JLabel("ENTRADAS");
		lblEntradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradas.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblEntradas.setBounds(0, 10, 495, 30);
		add(lblEntradas);
		
		
		// FETUCCHINI A LA HUANCAINA
		// Imagen de "Fetucchini a la Huancaína"
		JLabel imgFetucchinniHuancaina = new JLabel("");
		imgFetucchinniHuancaina.setHorizontalTextPosition(SwingConstants.CENTER);
		imgFetucchinniHuancaina.setHorizontalAlignment(SwingConstants.CENTER);
		imgFetucchinniHuancaina.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/fetucchini-a-la-huancaina-con-lomo-saltado.jpg")));
		imgFetucchinniHuancaina.setBounds(1, 60, 120, 90);
		add(imgFetucchinniHuancaina);
		
		// Etiqueta de texto para "Fetucchini a la Huancaína"
		JLabel lblFetucchinniHuancaina = new JLabel("FETUCCHINI A LA HUANCAINA CON LOMO SALTADO");
		lblFetucchinniHuancaina.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblFetucchinniHuancaina.setHorizontalAlignment(SwingConstants.CENTER);
		lblFetucchinniHuancaina.setBounds(120, 60, 377, 25);
		add(lblFetucchinniHuancaina);
		
		// Descripción de "Fetucchini a la Huancaína"
		JTextArea txtDescripcionFetucchiniHuancaina = new JTextArea();
		txtDescripcionFetucchiniHuancaina.setEditable(false);
		txtDescripcionFetucchiniHuancaina.setBorder(null);
		txtDescripcionFetucchiniHuancaina.setWrapStyleWord(true);
		txtDescripcionFetucchiniHuancaina.setForeground(Color.BLACK);
		txtDescripcionFetucchiniHuancaina.setBackground(new Color(222, 184, 135));
		txtDescripcionFetucchiniHuancaina.setText("Fetucchini, queso andino y salsa de pimientos tiernos, filet mignon cortado en cubitos con salsa de soja de tomate y cebolla morada");
		txtDescripcionFetucchiniHuancaina.setFont(new Font("Roboto Light", Font.ITALIC, 13));
		txtDescripcionFetucchiniHuancaina.setBounds(131, 85, 366, 65);
		txtDescripcionFetucchiniHuancaina.setLineWrap(true);
		add(txtDescripcionFetucchiniHuancaina);
		
		
		// FETUCCHINNI CON PULPO
		// Imagen de "Fetucchini con Pulpo"
		JLabel imgFetucchiniPulpo = new JLabel("");
		imgFetucchiniPulpo.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/DSC08032.jpg")));
		imgFetucchiniPulpo.setHorizontalTextPosition(SwingConstants.CENTER);
		imgFetucchiniPulpo.setHorizontalAlignment(SwingConstants.CENTER);
		imgFetucchiniPulpo.setBounds(1, 188, 120, 90);
		add(imgFetucchiniPulpo);
		
		// Etiqueta de texto para "Fetucchini con Pulpo"
		JLabel lblFetucchinniPulpo = new JLabel("FETUCCHINI CON PULPO (PIEUVRE) ANTICUCHERO");
		lblFetucchinniPulpo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFetucchinniPulpo.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblFetucchinniPulpo.setBounds(120, 188, 377, 25);
		add(lblFetucchinniPulpo);
		
		// Descripción de "Fetucchini con Pulpo"
		JTextArea txtDescripcionFetucchiniPulpo = new JTextArea();
		txtDescripcionFetucchiniPulpo.setEditable(false);
		txtDescripcionFetucchiniPulpo.setWrapStyleWord(true);
		txtDescripcionFetucchiniPulpo.setText("Fetucchini, salsa de queso andino y pimiento tierno acompañado de pulpo a la parrilla con salsa de especias peruanas");
		txtDescripcionFetucchiniPulpo.setLineWrap(true);
		txtDescripcionFetucchiniPulpo.setForeground(Color.BLACK);
		txtDescripcionFetucchiniPulpo.setFont(new Font("Roboto Light", Font.ITALIC, 13));
		txtDescripcionFetucchiniPulpo.setBorder(null);
		txtDescripcionFetucchiniPulpo.setBackground(new Color(222, 184, 135));
		txtDescripcionFetucchiniPulpo.setBounds(130, 213, 367, 54);
		add(txtDescripcionFetucchiniPulpo);
		
		
		// ARROZ CON MARISCOS
		// Imagen de "Arroz con Mariscos"
		JLabel imgArrozMariscos = new JLabel("");
		imgArrozMariscos.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/arroz-con-mariscos.jpg")));
		imgArrozMariscos.setHorizontalTextPosition(SwingConstants.CENTER);
		imgArrozMariscos.setHorizontalAlignment(SwingConstants.CENTER);
		imgArrozMariscos.setBounds(1, 316, 120, 90);
		add(imgArrozMariscos);
		
		// Etiqueta de texto para "Arroz con Mariscos"
		JLabel lblArrozMariscos = new JLabel("ARROZ CON MARISCOS");
		lblArrozMariscos.setHorizontalAlignment(SwingConstants.CENTER);
		lblArrozMariscos.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblArrozMariscos.setBounds(120, 316, 377, 25);
		add(lblArrozMariscos);
		
		// Descripción de "Arroz con Mariscos"
		JTextArea txtDescripcionArrozMariscos = new JTextArea();
		txtDescripcionArrozMariscos.setEditable(false);
		txtDescripcionArrozMariscos.setWrapStyleWord(true);
		txtDescripcionArrozMariscos.setText("Arroz de mariscos y salsa de la casa");
		txtDescripcionArrozMariscos.setLineWrap(true);
		txtDescripcionArrozMariscos.setForeground(Color.BLACK);
		txtDescripcionArrozMariscos.setFont(new Font("Roboto Light", Font.ITALIC, 13));
		txtDescripcionArrozMariscos.setBorder(null);
		txtDescripcionArrozMariscos.setBackground(new Color(222, 184, 135));
		txtDescripcionArrozMariscos.setBounds(130, 341, 367, 45);
		add(txtDescripcionArrozMariscos);
		
		
		// CHURRASCO AAA+
		// Imagen churrasco
		JLabel ImgTacosCamarones = new JLabel("");
		ImgTacosCamarones.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/tacos-crevette-tempura.jpg")));
		ImgTacosCamarones.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgTacosCamarones.setHorizontalAlignment(SwingConstants.CENTER);
		ImgTacosCamarones.setBounds(1, 572, 120, 90);
		add(ImgTacosCamarones);
		
		// Etiquete churrasco
		JLabel lblChurrasco = new JLabel("CHURRASCO AAA+ 12oz.");
		lblChurrasco.setHorizontalAlignment(SwingConstants.CENTER);
		lblChurrasco.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblChurrasco.setBounds(120, 444, 377, 25);
		add(lblChurrasco);
		
		// Descripcion de churrasco
		JTextArea txtDescripcionChurrasco = new JTextArea();
		txtDescripcionChurrasco.setEditable(false);
		txtDescripcionChurrasco.setWrapStyleWord(true);
		txtDescripcionChurrasco.setText("Filete a la parrilla con salsa chimichurri acompañado de papas fritas y ensalada");
		txtDescripcionChurrasco.setLineWrap(true);
		txtDescripcionChurrasco.setForeground(Color.BLACK);
		txtDescripcionChurrasco.setFont(new Font("Roboto Light", Font.ITALIC, 13));
		txtDescripcionChurrasco.setBorder(null);
		txtDescripcionChurrasco.setBackground(new Color(222, 184, 135));
		txtDescripcionChurrasco.setBounds(130, 469, 367, 45);
		add(txtDescripcionChurrasco);
		
		// TACOS DE TEMPURA DE CAMARONES
		// Imagen tacos de tempura
		JLabel ImgChurrasco = new JLabel("");
		ImgChurrasco.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/T-bonne-ahumado.jpg")));
		ImgChurrasco.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgChurrasco.setHorizontalAlignment(SwingConstants.CENTER);
		ImgChurrasco.setBounds(1, 444, 120, 90);
		add(ImgChurrasco);
		
		// Etiqueta "tacos de tempura de camarones"
		JLabel lblTacosCamarones = new JLabel("TACOS DE TEMPURA DE CAMARONES (x2) ");
		lblTacosCamarones.setHorizontalAlignment(SwingConstants.CENTER);
		lblTacosCamarones.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblTacosCamarones.setBounds(120, 572, 377, 25);
		add(lblTacosCamarones);
		
		// Descripcion tacos de camarones
		JTextArea txtTacosCamarones = new JTextArea();
		txtTacosCamarones.setEditable(false);
		txtTacosCamarones.setWrapStyleWord(true);
		txtTacosCamarones.setText("Tortillas de camarones marinados y empanizados en panko");
		txtTacosCamarones.setLineWrap(true);
		txtTacosCamarones.setForeground(Color.BLACK);
		txtTacosCamarones.setFont(new Font("Roboto Light", Font.ITALIC, 13));
		txtTacosCamarones.setBorder(null);
		txtTacosCamarones.setBackground(new Color(222, 184, 135));
		txtTacosCamarones.setBounds(130, 597, 367, 45);
		add(txtTacosCamarones);
		
		
		// Etiqueta de título para la sección de BEBIDAS
		JLabel lblBebidas = new JLabel("BEBIDAS");
		lblBebidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblBebidas.setFont(new Font("Roboto Light", Font.BOLD, 20));
		lblBebidas.setBounds(495, 10, 495, 30);
		add(lblBebidas);
		
		// MARGARITA CLASICA
		// Imagen margarita clasica
		JLabel ImgMargarita = new JLabel("");
		ImgMargarita.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/margarita_clasica.png")));
		ImgMargarita.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgMargarita.setHorizontalAlignment(SwingConstants.CENTER);
		ImgMargarita.setBounds(505, 60, 120, 90);
		add(ImgMargarita);
		
		// Etiqueta margarita clasica
		JLabel lblMargaritaClasica = new JLabel("MARGARITA CLASICA");
		lblMargaritaClasica.setHorizontalAlignment(SwingConstants.CENTER);
		lblMargaritaClasica.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblMargaritaClasica.setBounds(614, 90, 377, 25);
		add(lblMargaritaClasica);
		
		// PINTA
		// Imagen pinta
		JLabel ImgPinta = new JLabel("");
		ImgPinta.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/pinta.png")));
		ImgPinta.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgPinta.setHorizontalAlignment(SwingConstants.CENTER);
		ImgPinta.setBounds(505, 188, 120, 90);
		add(ImgPinta);
		
		// Etiqueta pinta
		JLabel lblPinta = new JLabel("PINTA");
		lblPinta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPinta.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblPinta.setBounds(614, 211, 377, 25);
		add(lblPinta);
		
		// SANGRIA
		// Imagen sangria
		JLabel ImgSangria = new JLabel("");
		ImgSangria.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/sangria.png")));
		ImgSangria.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgSangria.setHorizontalAlignment(SwingConstants.CENTER);
		ImgSangria.setBounds(505, 316, 120, 90);
		add(ImgSangria);
		
		// Etiqueta sangria
		JLabel lblSangria = new JLabel("JARRA DE SANGRIA");
		lblSangria.setHorizontalAlignment(SwingConstants.CENTER);
		lblSangria.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblSangria.setBounds(615, 340, 377, 25);
		add(lblSangria);
		
		// PIÑA COLADA
		// Imagen piña colada
		JLabel ImgPinaColada = new JLabel("");
		ImgPinaColada.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/pina_colada.png")));
		ImgPinaColada.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgPinaColada.setHorizontalAlignment(SwingConstants.CENTER);
		ImgPinaColada.setBounds(505, 444, 120, 90);
		add(ImgPinaColada);
		
		// Etiqueta piña colada
		JLabel lblPinaColada = new JLabel("PIÑA COLADA");
		lblPinaColada.setHorizontalAlignment(SwingConstants.CENTER);
		lblPinaColada.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblPinaColada.setBounds(614, 472, 377, 25);
		add(lblPinaColada);
		
		// JUGOS
		// Imagen jugos
		JLabel ImgJugos = new JLabel("");
		ImgJugos.setIcon(new ImageIcon(OpcionesMenus.class.getResource("/Img/Jugos.png")));
		ImgJugos.setHorizontalTextPosition(SwingConstants.CENTER);
		ImgJugos.setHorizontalAlignment(SwingConstants.CENTER);
		ImgJugos.setBounds(505, 572, 120, 90);
		add(ImgJugos);
		
		// Etiqueta vaso de jugo
		JLabel lblJugos = new JLabel("VASO DE JUGO");
		lblJugos.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugos.setFont(new Font("Roboto Light", Font.BOLD, 14));
		lblJugos.setBounds(614, 600, 377, 25);
		add(lblJugos);
		
	}
}

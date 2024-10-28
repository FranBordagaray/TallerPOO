package Vista.Cliente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * La clase {@code ImgOfertas} extiende {@link JPanel} y se encarga de mostrar
 * imágenes de ofertas en un panel gráfico. Esta clase contiene un conjunto
 * de rutas de imágenes que se utilizan para presentar diferentes ofertas 
 * en la interfaz de usuario.
 */
public class ImgOfertas extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Etiqueta que muestra la imagen actual de la oferta.
	 */
	private JLabel ImgImagen;

	/**
	 * Rutas de las imágenes de las ofertas disponibles.
	 */
	private String[] ImgPath = {
	    "..\\DeliciasGourmet\\src\\Img\\ImgOferta1.png",
	    "..\\DeliciasGourmet\\src\\Img\\ImgOferta2.png",
	    "..\\DeliciasGourmet\\src\\Img\\ImgOferta3.png",
	    "..\\DeliciasGourmet\\src\\Img\\ImgOferta4.png",
	    "..\\DeliciasGourmet\\src\\Img\\ImgOferta5.png"
	};

	/**
	 * Indice que rastrea cuál imagen se está mostrando actualmente.
	 */
	private int Indice = 0;
	
	
	/**
	 * Constructor de la clase {@code ImgOfertas}. Configura el panel para mostrar 
	 * imágenes de ofertas, estableciendo su tamaño y diseño. 
	 * 
	 * <p>
	 * Inicializa un {@link JLabel} para mostrar la imagen actual y utiliza un 
	 * {@link Timer} que cambia la imagen cada 3 segundos. Al iniciar, llama a 
	 * {@link #actualizarImagen()} para mostrar la primera imagen de la lista.
	 * </p>
	 */
	public ImgOfertas() {
		// Configuracion de pantalla
		setSize(286, 370);
		setLayout(new BorderLayout());

		ImgImagen = new JLabel();
		add(ImgImagen, BorderLayout.CENTER);

		Timer timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Indice = (Indice + 1) % ImgPath.length;
				actualizarImagen();
			}
		});
		timer.start();
		actualizarImagen();
	}

	/**
	 * Actualiza la imagen mostrada en el componente `ImgImagen`.
	 *
	 * Este método establece un nuevo ícono en el componente `ImgImagen` utilizando la ruta 
	 * de la imagen almacenada en el arreglo `ImgPath` en la posición indicada por 
	 * `Indice`. Se crea un objeto `ImageIcon` a partir de la ruta de la imagen y se 
	 * asigna a `ImgImagen` para actualizar visualmente el componente con la nueva imagen.
	 */
	private void actualizarImagen() {
		ImageIcon icon = new ImageIcon(ImgPath[Indice]);
		ImgImagen.setIcon(icon);
	}
}
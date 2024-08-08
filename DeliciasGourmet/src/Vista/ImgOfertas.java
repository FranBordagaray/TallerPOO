package Vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ImgOfertas extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel ImgImagen;
    private String[] ImgPath = {
            "..\\DeliciasGourmet\\src\\ImgOferta1.png",
            "..\\DeliciasGourmet\\src\\ImgOferta2.png",
            "..\\DeliciasGourmet\\src\\ImgOferta3.png",
            "..\\DeliciasGourmet\\src\\ImgOferta4.png",
            "..\\DeliciasGourmet\\src\\ImgOferta5.png"
    };
    private int Indice = 0;

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

    private void actualizarImagen() {
        ImageIcon icon = new ImageIcon(ImgPath[Indice]);
        ImgImagen.setIcon(icon);
    }
    
}
package Vista.Empleado;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controlador.ReservaControlador;
import Modelo.Empleado.Reportes;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ReportePorTemporada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ReservaControlador controlador;

	public ReportePorTemporada() {
		controlador = new ReservaControlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 500, 400);
		setLocationRelativeTo(null);

		// Creación del panel
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLACK));
		contentPane.setBackground(new Color(195, 155, 107));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiqueta para el título de la ventana
		JLabel lblTitulo = new JLabel("CONCURRENCIA POR TEMPORTADA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Roboto Light", Font.BOLD, 21));
		lblTitulo.setBounds(0, 32, 500, 22);
		contentPane.add(lblTitulo);

		// Boton x para cerrar el frame
		JButton btnCerrar = new JButton("X");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePorTemporada.this.setVisible(false);
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
		btnCerrar.setBounds(454, 1, 45, 30);
		contentPane.add(btnCerrar);
		
		// Boton para temporada primavera
		JButton btnPrimavera = new JButton("PRIMAVERA");
		btnPrimavera.setIcon(null);
		btnPrimavera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReportePorTemporada("PRIMAVERA");
			}
		});
		btnPrimavera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPrimavera.setBackground(new Color(144, 238, 144));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPrimavera.setBackground(Color.WHITE);
			}
		});
		btnPrimavera.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPrimavera.setForeground(Color.BLACK);
		btnPrimavera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrimavera.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnPrimavera.setBorder(null);
		btnPrimavera.setBackground(Color.WHITE);
		btnPrimavera.setAlignmentX(0.5f);
		btnPrimavera.setBounds(125, 86, 250, 30);
		getContentPane().add(btnPrimavera);
		
		// Boton para temporada verano
        JButton btnVerano = new JButton("VERANO");
        btnVerano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReportePorTemporada("VERANO");
			}
		});
        btnVerano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnVerano.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnVerano.setBackground(new Color(255, 255, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVerano.setBackground(Color.WHITE);
            }
        });
        btnVerano.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVerano.setForeground(Color.BLACK);
        btnVerano.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVerano.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnVerano.setBorder(null);
        btnVerano.setBackground(Color.WHITE);
        btnVerano.setAlignmentX(0.5f);
        btnVerano.setBounds(125, 148, 250, 30);
        getContentPane().add(btnVerano);
		
		// Boton para temporada otoño
        JButton btnOtonio = new JButton("OTOÑO");
        btnOtonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReportePorTemporada("OTOÑO");
			}
		});
        btnOtonio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnOtonio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnOtonio.setBackground(new Color(139, 69, 19));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnOtonio.setBackground(Color.WHITE);
            }
        });
        btnOtonio.setHorizontalTextPosition(SwingConstants.CENTER);
        btnOtonio.setForeground(Color.BLACK);
        btnOtonio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnOtonio.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnOtonio.setBorder(null);
        btnOtonio.setBackground(Color.WHITE);
        btnOtonio.setAlignmentX(0.5f);
        btnOtonio.setBounds(125, 210, 250, 30);
        getContentPane().add(btnOtonio);
		
		// Boton para temporada invierno
        JButton btnInvierno = new JButton("INVIERNO");
        btnInvierno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReportePorTemporada("INVIERNO");
			}
		});
        btnInvierno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnInvierno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnInvierno.setBackground(new Color(173, 216, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInvierno.setBackground(Color.WHITE);
            }
        });
        btnInvierno.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInvierno.setForeground(Color.BLACK);
        btnInvierno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnInvierno.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        btnInvierno.setBorder(null);
        btnInvierno.setBackground(Color.WHITE);
        btnInvierno.setAlignmentX(0.5f);
        btnInvierno.setBounds(125, 272, 250, 30);
        getContentPane().add(btnInvierno);
		
		JButton btnTodas = new JButton("TODAS LAS TEMPORADAS");
		btnTodas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTodas.setForeground(Color.BLACK);
		btnTodas.setFont(new Font("Roboto Light", Font.PLAIN, 16));
		btnTodas.setBorder(null);
		btnTodas.setBackground(Color.WHITE);
		btnTodas.setAlignmentX(0.5f);
		btnTodas.setBounds(125, 334, 250, 30);
		contentPane.add(btnTodas);
	}

	public static boolean esCorreoValido(String correo) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(correo).matches();
	}
	
    // Método para generar los reportes por temporada
    @SuppressWarnings("unused")
    private void generarReportePorTemporada(String temporada) {
        List<Reportes> reporteReservas = controlador.obtenerHistorialComensalesPorTemporada(temporada);

        Document documento = new Document();
        String ruta = "src\\ReportesPDF\\Concurrencia_" + temporada.toLowerCase() + ".pdf";
        File archivo = new File(ruta);
        
        if (archivo.exists()) {
            String nuevoNombre = "Concurrencia_" + temporada.toLowerCase() + "_" + System.currentTimeMillis() + ".pdf";
            ruta = "src\\ReportesPDF\\" + nuevoNombre;
        }

        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
            documento.open();
            documento.add(new Paragraph("Reporte de Reservas por Temporada", FontFactory.getFont("Roboto Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 16, Font.BOLD)));
            documento.add(new Paragraph("Temporada: " + temporada));
            documento.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.addCell("Total de reservas");
            table.addCell("Cantidad de comensales");

            for (Reportes reserva : reporteReservas) {
                table.addCell(String.valueOf(reserva.getTotalReservas()));
                table.addCell(String.valueOf(reserva.getTotalCapacidad()));
            }

            documento.add(table);
            JOptionPane.showMessageDialog(null, "PDF generado con éxito en: " + ruta);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + ex.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }

}
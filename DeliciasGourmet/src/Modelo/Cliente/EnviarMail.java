package Modelo.Cliente;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarMail {
	
	public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        // Mail y clave de aplicacion del restaurant
        final String remitente = "deliciasgourmet.recuperar@gmail.com";
        final String password = "ygjc cwvz xigu rxhk";

        // Configura el host para enviar correos
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", remitente);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");
        
        // Crea una sesión de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        // Crea el mensaje con sus campos necesarios
        try {
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport.send(mensaje);
            System.out.println("Correo enviado con éxito!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
	//Metodo enviarCorreo modificado para que tambien envie un pdf(Comprobante)
	public static void enviarCorreoComprobante(String destinatario, String asunto, String cuerpo, String ruta) {
	    // Mail y clave de aplicación del restaurante
	    final String remitente = "deliciasgourmet.recuperar@gmail.com";
	    final String password = "ygjc cwvz xigu rxhk";

	    // Configura el host para enviar correos
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.smtp.port", "587");
	    props.setProperty("mail.smtp.user", remitente);
	    props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
	    props.setProperty("mail.smtp.auth", "true");

	    // Crear una sesión con autenticación
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(remitente, password);
	        }
	    });

	    try {
	        // Crear un objeto MimeMessage
	        MimeMessage mensaje = new MimeMessage(session);
	        mensaje.setFrom(new InternetAddress(remitente));
	        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
	        mensaje.setSubject(asunto);

	        // Crear la parte del cuerpo del correo
	        MimeBodyPart bodyPart = new MimeBodyPart();
	        bodyPart.setText(cuerpo);

	        // Crear la parte del archivo adjunto
	        MimeBodyPart adjuntoPart = new MimeBodyPart();
	        FileDataSource source = new FileDataSource(ruta);
	        adjuntoPart.setDataHandler(new DataHandler(source));
	        adjuntoPart.setFileName(new File(ruta).getName());

	        // Crear un objeto Multipart para combinar las partes
	        MimeMultipart multipart = new MimeMultipart();
	        multipart.addBodyPart(bodyPart);  // Añadir la parte del cuerpo
	        multipart.addBodyPart(adjuntoPart);  // Añadir la parte del archivo adjunto

	        // Establecer el contenido del mensaje como multipart
	        mensaje.setContent(multipart);

	        // Enviar el mensaje
	        Transport.send(mensaje);
	        System.out.println("Correo enviado con éxito!");
	    } catch (MessagingException e) {
	        e.printStackTrace();  // Imprimir el error para ayudar en la depuración
	        throw new RuntimeException(e);
	    }
	}
}
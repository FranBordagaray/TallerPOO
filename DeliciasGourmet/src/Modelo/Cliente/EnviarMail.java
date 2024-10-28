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

/**
 * Clase que gestiona el envío de correos electrónicos desde el sistema de Delicias Gourmet.
 * Permite enviar correos simples y correos con archivos adjuntos como comprobantes en formato PDF.
 */
public class EnviarMail {

    /**
     * Envía un correo electrónico sin archivos adjuntos.
     *
     * @param destinatario Dirección de correo electrónico del destinatario.
     * @param asunto       Asunto del correo electrónico.
     * @param cuerpo       Contenido del cuerpo del correo electrónico.
     */
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        // Mail y clave de aplicación del restaurante
        final String remitente = "deliciasgourmet.recuperar@gmail.com";
        final String password = "ygjc cwvz xigu rxhk";

        // Configuración del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", remitente);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");

        // Crea una sesión con autenticación
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        // Envío del mensaje
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

    /**
     * Envía un correo electrónico con un archivo adjunto, como un comprobante en formato PDF.
     *
     * @param destinatario Dirección de correo electrónico del destinatario.
     * @param asunto       Asunto del correo electrónico.
     * @param cuerpo       Contenido del cuerpo del correo electrónico.
     * @param ruta         Ruta del archivo adjunto a enviar.
     */
    public static void enviarCorreoComprobante(String destinatario, String asunto, String cuerpo, String ruta) {
        // Mail y clave de aplicación del restaurante
        final String remitente = "deliciasgourmet.recuperar@gmail.com";
        final String password = "ygjc cwvz xigu rxhk";

        // Configuración del servidor de correo
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

        // Creación y envío del mensaje con archivo adjunto
        try {
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);

            // Crea la parte del cuerpo del correo
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(cuerpo);

            // Crea la parte del archivo adjunto
            MimeBodyPart adjuntoPart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(ruta);
            adjuntoPart.setDataHandler(new DataHandler(source));
            adjuntoPart.setFileName(new File(ruta).getName());

            // Combina las partes del mensaje
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(adjuntoPart);

            // Establece el contenido del mensaje como multipart
            mensaje.setContent(multipart);

            // Envía el mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado con éxito!");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
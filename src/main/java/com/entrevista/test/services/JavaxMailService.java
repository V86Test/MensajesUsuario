package com.entrevista.test.services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
//@Named
public class JavaxMailService implements EmailService {
    
    @Value("${mail.serverPort}")
    private String serverPort;
    
    @Value("${mail.serverHost}")
    private String serverHost;
    
    @Value("${mail.user}")
    private String mailUser;
    
    @Value("${mail.password}")
    private String mailPassword;
    
    @Value("${mail.from}")
    private String from;
    
    private static final Logger LOGGER = Logger.getLogger(DefaultEmailService.class.getName());
    
    
    @Override
    public void sendMail(String receiverMail, String subject, String message) {

        try {
            final Properties mailServerProperties = getPropertiesMail();
            final Session mailSession = getMailSession(mailServerProperties);
            
            javax.mail.internet.MimeMessage generateMailMessage;
            generateMailMessage = new MimeMessage(mailSession);
            //Estableciendo el destino (TO)
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));
            
            //Estableciendo el titulo del mensaje (subject)
            generateMailMessage.setSubject(subject);
            
            // Estableciendo el contenido del correo electronico enriquesido(HTML)
            
            String bodyEmail = message;
            generateMailMessage.setContent(bodyEmail, "text/html");
            
            //Finalmente  enviamos el correo
            javax.mail.Transport.send(generateMailMessage);
            
        } catch (MessagingException ex) {
            LOGGER.log(Level.WARNING, "An error has occurred while sending the mail", ex);

//C            Logger.getLogger(JavaxMailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Properties getPropertiesMail() {
        
        Properties mailServerProperties = System.getProperties();
//        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.host", serverHost);
        mailServerProperties.put("mail.smtp.socketFactory.port", serverPort);
        mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailServerProperties.put("mail.smtp.auth", "true");
//        mailServerProperties.put("mail.smtp.port", "465");
        mailServerProperties.put("mail.smtp.port", serverPort);
        
        return mailServerProperties;
    }

    private Session getMailSession(final Properties mailServerProperties) {
        return Session.getDefaultInstance(
                mailServerProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,mailPassword);
                    }
                });
    }

}

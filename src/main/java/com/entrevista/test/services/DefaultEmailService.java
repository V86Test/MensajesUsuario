package com.entrevista.test.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Named
public class DefaultEmailService implements EmailService{
    
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
    public void sendMail(final String receiverMail, final String subject, final String message) {
        
        try {
            Email email = new SimpleEmail();
            email.setHostName(serverHost);
            email.setSmtpPort(Integer.parseInt(serverPort));
            email.setAuthenticator(new DefaultAuthenticator(mailUser, mailPassword));
//            email.setSSLOnConnect(true);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(receiverMail);
            email.send();
            
            
        } catch (EmailException ex) {
            LOGGER.log(Level.WARNING, "An error has occurred while sending the mail", ex);
        }
    }
}

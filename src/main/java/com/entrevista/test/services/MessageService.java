package com.entrevista.test.services;

import com.entrevista.test.entities.Message;
import java.util.Collection;

/**
 * Message service
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public interface MessageService {
    
    /**
     * Add a message to DDBB
     * @param message
     * @param userIdentificator 
     */
    void addMessage(String message, String userIdentificator);

    /**
     * Get All Messages 
     * @return All Messages 
     */
    public Collection<Message> getAllMessagesInfo();

    /**
     * Get All Messages  from a user
     * @param identificator of a user
     * @return  Get All Messages from a user
     */
    public Collection<Message> getAllMessagesInfo(String identificator);

}

package com.entrevista.test.services;

import com.entrevista.test.entities.Message;
import com.entrevista.test.entities.User;
import com.entrevista.test.repository.MessageRepository;

import com.google.common.collect.Lists;


import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.util.StringUtils;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Named
public class DefaultMessageService implements MessageService{
    
    private static final Logger LOGGER = Logger.getLogger(DefaultMessageService.class.getName());
    
    @Inject
    private MessageRepository messageRepository;
    
    @Inject
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMessage(final String message,final String userIdentificator) {
        
        //Validation
        if (StringUtils.isEmpty(message)) {
            LOGGER.log(Level.WARNING, "Error trying to add a message with a empty message");
            return;
        }
        
        if (StringUtils.isEmpty(userIdentificator)) {
            LOGGER.log(Level.WARNING, "Error trying to add a message with a empty Identificator");
            return;
        }
        
        final User user = userService.getUserByIdentificator(userIdentificator);
        if (user == null) {
            LOGGER.log(Level.WARNING, "Error trying to add a message with a identifier no existent for a user");
            return;
        }
        
        final Message messageToSave = new Message(user,message);
        messageRepository.save(messageToSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Message> getAllMessagesInfo(final String identificator) {
        return messageRepository.getMessagesByIdentificator(identificator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Message> getAllMessagesInfo() {
        return Lists.newArrayList(messageRepository.findAll());
    }

}

package com.entrevista.test.resources;

import com.entrevista.test.entities.Message;
import com.entrevista.test.services.MessageService;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@RestController
@RequestMapping("/Message")
public class MessageResource {
    
    @Inject
    private MessageService messageService;
    
    @RequestMapping(value = "addMessage", method = RequestMethod.POST)
    public @ResponseBody void regiserUser(@RequestBody String literal, @RequestBody String identificator){
        messageService.addMessage(literal, identificator);
    }
    
    @RequestMapping(value = "messageInfo", method = RequestMethod.GET)
    public @ResponseBody Collection<Message> getAllMessagesInfo(){
        return messageService.getAllMessagesInfo();
    }
    
    @RequestMapping(value = "messageInfoIdentificator", method = RequestMethod.GET)
    public @ResponseBody Collection<Message> getAllMessagesInfo(@PathVariable String identificator){
        return messageService.getAllMessagesInfo(identificator);
    }    
}

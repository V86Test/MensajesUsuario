package com.entrevista.test.integration;

import com.entrevista.test.config.Context;
import com.entrevista.test.entities.Message;
import com.entrevista.test.services.MessageService;
import com.entrevista.test.services.UserService;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@ContextConfiguration(classes={Context.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageServiceTest {
    
    private static final String TEST_IDENTIFICATION  = "testIdentification";
    private static final String TEST_IDENTIFICATION_2  = "testIdentification2";
    
    private static final String TEST_MAIL  = "testMail@testMail.com";
    
    @Inject
    private MessageService messageService;
    
    @Inject
    private UserService userservice;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        loadUserExistingUser();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void AddMessageHappyFlow() {
        messageService.addMessage("Message", TEST_IDENTIFICATION);
    }
    
    @Test
    public void AddMessageWithoutIdentificationNull() {
        messageService.addMessage("Message", null);
    }
    
    @Test
    public void AddMessageWithoutIdentificationEmpty() {
        messageService.addMessage("Message", "");
    }

    @Test
    public void getAllMessages() {
        messageService.addMessage("Message", TEST_IDENTIFICATION);
        messageService.addMessage("Message", TEST_IDENTIFICATION);
        messageService.addMessage("Message", TEST_IDENTIFICATION);

        Collection<Message> messages = messageService.getAllMessagesInfo();
        
        assertTrue(messages.size() == 3);
    }
    
    @Test
    public void getAllMessagesByIdentificaro() {
        messageService.addMessage("Message", TEST_IDENTIFICATION);
        messageService.addMessage("Message", TEST_IDENTIFICATION_2);
        messageService.addMessage("Message", TEST_IDENTIFICATION);

        Collection<Message> messages = messageService.getAllMessagesInfo(TEST_IDENTIFICATION);
        assertTrue(messages.size() == 2);
    }

    private void loadUserExistingUser() {
        userservice.registerUser("NameUser", TEST_MAIL, TEST_IDENTIFICATION);
    }

}

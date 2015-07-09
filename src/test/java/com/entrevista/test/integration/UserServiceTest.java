package com.entrevista.test.integration;

import com.entrevista.test.config.Context;
import com.entrevista.test.services.UserService;
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

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@ContextConfiguration(classes={Context.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest {
    
    private static final String TEST_IDENTIFICATION  = "testIdentification";
    
    private static final String TEST_MAIL  = "testMail@testMail.com";
    
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
    
    /**
     * Test for create a user Basic
     */
    @Test
    public void createUserHappyFlow() {
        userservice.registerUser("NameUser", "vmso86@gmail.com", "1");
    }
    
    /**
     * Test without Identification
     */
    @Test
    public void createUserWithoutIdentification() {
        userservice.registerUser("NameUser", "withoutIdentification@gmail.com", null);
        
    }
    
    /**
     * Test with Identification repeated
     */
    @Test
    public void createUserWithIdentificationRepeated() {
        userservice.registerUser("NameUser", "IdentificationRepeated@gmail.com", TEST_IDENTIFICATION);
    }
    
    /**
     * Test without mail 
     */
    @Test
    public void createUserWithoutMail() {

        userservice.registerUser("NameUser",null, "2");
    }
    
    /**
     * Test with mail repeated
     */
    @Test
    public void createUserWithMailRepeated() {
        userservice.registerUser("NameUser", TEST_MAIL, "3");
    }

    private void loadUserExistingUser() {
        userservice.registerUser("NameUser", TEST_MAIL, TEST_IDENTIFICATION);
    }

}

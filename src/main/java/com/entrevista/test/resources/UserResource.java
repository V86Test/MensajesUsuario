package com.entrevista.test.resources;

import com.entrevista.test.entities.User;
import com.entrevista.test.services.UserService;
import java.util.Collection;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@RestController
@RequestMapping("/User")
public class UserResource {
    
    @Inject
    private UserService userService;
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void regiserUser(@PathVariable String name, @PathVariable String mail, @PathVariable String identificator){
        userService.registerUser(name, mail, identificator);
    }
    
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Collection<User> regiserUser(){
        return userService.getAllUser();
    }
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testt(){
        return "test";
    }

}

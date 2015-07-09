package com.entrevista.test.services;

import com.entrevista.test.entities.User;
import java.util.Collection;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public interface UserService {
    /**
     * Try register a user
     * @param name
     * @param mail
     * @param identificator 
     */
    void registerUser(String name, String mail, String identificator);
    
    /**
     * 
     * @param identificator
     * @return true if the user exit in the DataBase
     */
    boolean existsUserByIdentificator(String identificator);

    /**
     * Get all user of system
     * @return All user of system
     */
    public Collection<User> getAllUser();

    /**
     * Search a user by user identificator 
     * @param userIdentificator
     * @return the user of the identificator or null if no exists
     */
    public User getUserByIdentificator(String userIdentificator);

}

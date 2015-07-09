package com.entrevista.test.repository;

import com.entrevista.test.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("from User u where u.identificator= :identificator")
    public List<User> getUserByIdentificator(@Param("identificator") String identificator);
    
    @Query("from User u where u.mail= :mail")    
    public List<User> getUserByMail(@Param("mail") String mail);

}

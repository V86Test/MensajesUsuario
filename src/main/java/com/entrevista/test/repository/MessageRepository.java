package com.entrevista.test.repository;

import com.entrevista.test.entities.Message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Generic Crud Repository
 * 
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public interface MessageRepository extends CrudRepository<Message, Long>{
    
    @Query("from Message m where m.user.identificator= :identificator")
    public Collection<Message> getMessagesByIdentificator(@Param("identificator") String identificator);

}

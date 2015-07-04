package com.entrevista.test.repository.generic;

import com.entrevista.test.entities.Message;

import org.springframework.data.repository.CrudRepository;

/**
 * Generic Crud Repository
 * 
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public interface MessageRepositoryGeneric extends CrudRepository<Message, Long>{

}

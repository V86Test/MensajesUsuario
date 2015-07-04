package com.entrevista.test.repository.generic;

import com.entrevista.test.entities.User;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
public interface UserRepositoryGeneric extends CrudRepository<User, Long> {

}

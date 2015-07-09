package com.entrevista.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity for message
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "mess_seq", allocationSize = 1)
public class Message extends EntityBase{
    
    @ManyToOne( fetch = FetchType.EAGER)
    private User user;
    
    @Column(unique = false, nullable = false)
    private String literal;

    public Message() {
    }

    public Message(User user, String literal) {
        this.user = user;
        this.literal = literal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }
    
}

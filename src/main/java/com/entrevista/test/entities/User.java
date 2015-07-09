package com.entrevista.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * Entity for user
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "user_seq", allocationSize = 1)
public class User extends EntityBase{
    
    @Column(unique = false, nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String mail;
    
    @Column(unique = true, nullable = false)
    private String identificator;

    public User() {
    }

    public User(String name, String mail, String identificator) {
        this.name = name;
        this.mail = mail;
        this.identificator = identificator;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    @Override
    public int hashCode() {
        return getIdentificator().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        
        boolean result = false;
        if (other instanceof User) {
            User otherUser = (User) other;
            return (otherUser.identificator!=null && otherUser.identificator.equals(other));
        }
        return result;
    }
}

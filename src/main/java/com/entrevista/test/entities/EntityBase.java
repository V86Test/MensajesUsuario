package com.entrevista.test.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * SuperClass as base for other entities
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com)
 */
@MappedSuperclass
public abstract class EntityBase implements Serializable{

    /**
     * Id of entity
     */
    @Id
    @GeneratedValue(generator = "default_gen")
    @Column(nullable = false, unique = true)
    protected long id;

    /**
     * Creation Date
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    /**
     * Last date of modification
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modificationDate;

    @PrePersist
    void createdAt() {
        this.creationDate = this.modificationDate = new Date();
    }

    @PreUpdate
    private void updatedAt() {
        this.modificationDate = new Date();
    }

    /**
     * Get the id of entity
     *
     * @return the id of entity
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of entity. Always will be set automatically by the
     * ORM
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the creation date.
     *
     * @return
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set the creation date. Always will be set automatically by the ORM
     *
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Get the modification date
     * @return 
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * Set the modification date. Always will be set automatically by the ORM
     * 
     * @param modificationDate 
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}

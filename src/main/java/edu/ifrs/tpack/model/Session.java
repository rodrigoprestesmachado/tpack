/**
 */
package edu.ifrs.tpack.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * 
 */
@Data
@Entity
public class Session {

    /**
    * 
    */
    @Id
    @GeneratedValue
    private long id;

    /**
    * 
    */
    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions;

}
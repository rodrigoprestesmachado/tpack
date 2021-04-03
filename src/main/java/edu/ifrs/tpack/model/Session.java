package edu.ifrs.tpack.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
/**
* 
*
* @author Rodrigo Maria Morgão
*/
public class Session {

    @Id
    @GeneratedValue
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    private long id;

    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    private String title;

    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    private List<Question> questions;

}
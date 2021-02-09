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
 * Comentário da classe Session
 */
public class Session {

    @Id
    @GeneratedValue
    /**
     * Comentário 
     */
    private long id;

     /**
     * Comentário 
     */
    private String title;

    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
     /**
     * Comentário 
     */
    private List<Question> questions;

}

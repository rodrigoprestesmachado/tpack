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
     * Comentando atributo
     */
    private long id;

    /**
     * Comentando atributo
     */
    private String title;

    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    /**
     * Comentando atributo
     */
    private List<Question> questions;

}
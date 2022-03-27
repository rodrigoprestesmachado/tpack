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
 * @author Rodrigo Prestes Machado
 */
@Data
@Entity
public class Session {

    /**
     * descricao de atributo
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * descricao de atributo
     */
    private String title;

    /**
     * descricao de atributo
     */
    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions;

}
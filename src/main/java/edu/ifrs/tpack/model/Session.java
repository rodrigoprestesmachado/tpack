/**
 * Comentário com menos linhas
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
 * Comentário de classe
 */
@Data
@Entity
public class Session {

    /**
     * Comentário de atributo
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Comentário de atributo
     */
    private String title;

    /**
     * Comentário de atributo
     */
    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions;

}
/**
* @author rodrigo
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

@Data
@Entity
/**
* Comentário sobre a classe
*/
public class Session {

    @Id
    @GeneratedValue
    /**
     * Comentário sobre o atributo
     */
    private long id;

    /**
     * Comentário sobre o atributo
     */
    private String title;

    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    /**
     * Comentário sobre o atributo
     */
    private List<Question> questions;

}
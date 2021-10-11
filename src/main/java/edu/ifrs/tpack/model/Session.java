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
 * @description da classe aqui
 */
public class Session {

    @Id
    @GeneratedValue
    /**
     * valor de Id
     */
    private long id;
    /**
     * valor de title
     */
    private String title;
    /**
     * valor de questions
     */
    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions;

}
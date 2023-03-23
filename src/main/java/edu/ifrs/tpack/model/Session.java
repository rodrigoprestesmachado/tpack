/**
 * @License
 * Copyright 2020 TPACK XS Application
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
 * @description public class Session
 */
@Data
@Entity
public class Session {

    /**
    * @description private long id
    */
    @Id
    @GeneratedValue
    private long id;

    /**
    * @description private String title
    */
    private String title;

    /**
    * @description private List<Question> questions
    */
    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions;

}
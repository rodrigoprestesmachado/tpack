/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
*
* @author Rodrigo Prestes Machado
*/
@Data
@Entity
public class Session {

    /**
    *
    *
    * long id
    */
    @Id
    @GeneratedValue
    private long id;

    /**
    *
    *
    * string title
    */
    private String title;

    /**
    *
    *
    * questions
    */
    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Question> questions;

}
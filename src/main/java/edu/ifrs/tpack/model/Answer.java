/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package edu.ifrs.tpack.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
*
*
* @author Rodrigo Prestes Machado
*/
@Entity
public class Answer {

    /**
    *
    *
    * Long id
    */
    @Id
    @GeneratedValue
    private long id;

    /**
    *
    *
    * Class Subject
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subject subject;

    /**
    *
    *
    * Class Question
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Question question;

    /**
    *
    *
    * Class Choice
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Choice choice;

    /**
    *
    *
    * String answer
    */
    private String answer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @JsonbTransient
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

}
/**
 * @License
 * Copyright 2020 TPACK XS Application
 */
package edu.ifrs.tpack.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @description public class Answer
 */
@Entity
public class Answer {
    /**
    * @description GeneratedValue
    */
    @Id
    @GeneratedValue
    private long id;

    /**
    * @description ManyToOne(cascade = CascadeType.PERSIST)
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subject subject;

    /**
    * @description ManyToOne(cascade = CascadeType.PERSIST)
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Question question;

    /**
    * @description ManyToOne(cascade = CascadeType.PERSIST
    */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Choice choice;

    /**
    * @description String answer
    */
    private String answer;

    /**
    * @description long getId() 
    */
    public long getId() {
        return id;
    }

    /**
    * @description void setId(long id)
    */
    public void setId(long id) {
        this.id = id;
    }

    /**
    * @description Subject getSubject()
    */
    public Subject getSubject() {
        return subject;
    }

    /**
    * @description void setSubject(Subject subject)
    */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
    * @description Question getQuestion()
    */
    public Question getQuestion() {
        return question;
    }

    /**
    * @description void setQuestion(Question question)
    */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
    * @description String getAnswer() 
    */
    @JsonbTransient
    public String getAnswer() {
        return answer;
    }

    /**
    * @description void setAnswer(String answer)
    */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
    * @description Choice getChoice()
    */
    public Choice getChoice() {
        return choice;
    }

    /**
    * @description void setChoice(Choice choice)
    */
    public void setChoice(Choice choice) {
        this.choice = choice;
    }

}
/**
 * @License
 * Copyright 2020 TPACK XS Application
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package edu.ifrs.tpack.model;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

/**
 * Question Entity
 */
@Entity
public class Question {

    /**
     * the identifier
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * the text of question
     */
    @Column(name = "TEXT", length = 1500)
    private String text;

    /**
     * one explanation about the question
     */
    private String note;

    /**
     * identify the type of the question
     */
    private QuestionType type;

    /**
     * indicates the number os levels in multilevel questions
     */
    @Transient
    private byte levels;

    /**
     * the session related
     */
    @ManyToOne
    @JoinColumn(name = "SESSION_ID", nullable = false)
    private Session session;

    /**
     * the list of choices related
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Choice> choices;

    /**
     * the list of answers related
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Answer> answers;

    /**
     * @decription Calculates the levels for multilevel questions
     */
    @PostLoad
    public void calculateLevels() {
        if (this.type == QuestionType.MULTILEVEL) {
            for (Choice choice : choices) {
                this.levels = choice.getLevel() > this.levels ? choice.getLevel() : this.levels;
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public byte getLevels() {
        return levels;
    }

    public void setLevels(byte levels) {
        this.levels = levels;
    }

    @JsonbTransient
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
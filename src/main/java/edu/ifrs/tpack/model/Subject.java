/**
 * @License
 * Copyright 2020 TPACK XS Application
 */
package edu.ifrs.tpack.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @description public class Subject
 */
@Data
@Entity
public class Subject {

    /**
 * @description pprivate long id
 */
    @Id
    @GeneratedValue
    @JsonbTransient
    private long id;

    /**
 * @description pprivate String token
 */
    private String token;

/**
 * @description private Timestamp moment
 */
    private Timestamp moment;

/**
 * @description private List<Answer> answers
 */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Answer> answers;

    /**
     * Construtor
     */
    public Subject() {
        Calendar calendar = Calendar.getInstance();
        this.moment = new Timestamp(calendar.getTimeInMillis());
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Adds a answer to a subject
     * 
     * @param answer Answer object
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

}
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

@Data
@Entity
/*
    Comentário sobre a classe
*/
public class Subject {

    @Id
    @GeneratedValue
    @JsonbTransient
    /*
        Comentário sobre o atributo
    */
    private long id;

    /*
        Comentário sobre o atributo
    */
    private String token;

    /*
        Comentário sobre o atributo
    */
    private Timestamp moment;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    /*
        Comentário sobre o atributo
    */
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
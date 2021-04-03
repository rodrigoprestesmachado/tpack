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
/**
* 
*
* @author Rodrigo Maria Morgão
*/
public class Subject {

    @Id
    @GeneratedValue
    @JsonbTransient
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    private long id;

    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    private String token;

    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    private Timestamp moment;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
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
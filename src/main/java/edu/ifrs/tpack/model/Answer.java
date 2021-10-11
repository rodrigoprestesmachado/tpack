package edu.ifrs.tpack.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
/**
 * @description da classe aqui
 */
public class Answer {

    @Id
    @GeneratedValue
    /**
     * valor de ID
     */
    private long id;
/**
valor de subject
 */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subject subject;
/**
valor de question
 */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Question question;
/**
valor de choice
 */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Choice choice;

    /**
    valor de answer
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
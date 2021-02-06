/**
* @author rodrigo
*/
package edu.ifrs.tpack.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
/**
* Comentário sobre a classe
*/
public class Answer {

    @Id
    @GeneratedValue
    /**
     * Comentário sobre o atributo
     */
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    /**
     * Comentário sobre o atributo
     */
    private Subject subject;

    @ManyToOne(cascade = CascadeType.PERSIST)
    /**
     * Comentário sobre o atributo
     */
    private Question question;

    @ManyToOne(cascade = CascadeType.PERSIST)
    /**
     * Comentário sobre o atributo
     */
    private Choice choice;

    /**
     * Comentário sobre o atributo
     */
    private String answer;

    /**
     * Comentário sobre o método
     */
    public long getId() {
        return id;
    }

    /**
     * Comentário sobre o método
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Comentário sobre o método
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Comentário sobre o método
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Comentário sobre o método
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Comentário sobre o método
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    @JsonbTransient
    /**
     * Comentário sobre o método
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Comentário sobre o método
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Comentário sobre o método
     */
    public Choice getChoice() {
        return choice;
    }

    /**
     * Comentário sobre o método
     */
    public void setChoice(Choice choice) {
        this.choice = choice;
    }

}
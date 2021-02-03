/**
 * Comentário com menos linhas
 */
package edu.ifrs.tpack.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Comentário de classe
 */
@Entity
public class Answer {

    /**
     * Comentário de atributo
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Comentário de atributo
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subject subject;

    /**
     * Comentário de atributo
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Question question;

    /**
     * Comentário de atributo
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Choice choice;

    /**
     * Comentário de atributo
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
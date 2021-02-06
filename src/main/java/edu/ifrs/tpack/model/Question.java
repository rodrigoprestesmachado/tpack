/**
* @author rodrigo
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

@Entity
/*
    Comentário sobre a classe
*/
public class Question {

    @Id
    @GeneratedValue
    /**
     * Comentário sobre o atributo
     */
    private long id;

    @Column(name = "TEXT", length = 1500)
    /**
     * Comentário sobre o atributo
     */
    private String text;

    /* one explanation about the question */
    private String note;

    /* identify the type of the question */
    private QuestionType type;

    @Transient
    /* indicates the number os levels in multilevel questions */
    private byte levels;

    @ManyToOne
    @JoinColumn(name = "SESSION_ID", nullable = false)
    /**
     * Comentário sobre o atributo
     */
    private Session session;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    /**
     * Comentário sobre o atributo
     */
    private List<Choice> choices;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
    /**
     * Comentário sobre o atributo
     */
    private List<Answer> answers;

    /**
     * Calculates the levels for multilevel questions
     */
    @PostLoad
    private void calculateLevels() {
        if (this.type == QuestionType.MULTILEVEL) {
            for (Choice choice : choices) {
                this.levels = choice.getLevel() > this.levels ? choice.getLevel() : this.levels;
            }
        }
    }

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
    public String getText() {
        return text;
    }

    /**
     * Comentário sobre o método
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Comentário sobre o método
     */
    public String getNote() {
        return note;
    }

    /**
     * Comentário sobre o método
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Comentário sobre o método
     */
    public QuestionType getType() {
        return type;
    }

    /**
     * Comentário sobre o método
     */
    public void setType(QuestionType type) {
        this.type = type;
    }

    /**
     * Comentário sobre o método
     */
    public byte getLevels() {
        return levels;
    }

    /**
     * Comentário sobre o método
     */
    public void setLevels(byte levels) {
        this.levels = levels;
    }

    @JsonbTransient
    /**
     * Comentário sobre o método
     */
    public Session getSession() {
        return session;
    }

    /**
     * Comentário sobre o método
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Comentário sobre o método
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Comentário sobre o método
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Comentário sobre o método
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Comentário sobre o método
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
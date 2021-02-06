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
import javax.persistence.Transient;

@Entity
/**
 * Comentário da classe Question
 */
public class Question {

    @Id
    @GeneratedValue
    /**
     * Comentando atributo
     */
    private long id;

    @Column(name = "TEXT", length = 1500)
    /**
     * Comentando atributo
     */
    private String text;

    /* one explanation about the question */
    /**
     * Comentando atributo
     */
    private String note;

    /* identify the type of the question */
    /**
     * Comentando atributo
     */
    private QuestionType type;

    /* indicates the number os levels in multilevel questions */
    /**
     * Comentando atributo
     */
    @Transient
    private byte levels;

    @ManyToOne
    @JoinColumn(name = "SESSION_ID", nullable = false)
    /**
     * Comentando atributo
     */
    private Session session;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    /**
     * Comentando atributo
     */
    private List<Choice> choices;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
    /**
     * Comentando atributo
     */
    private List<Answer> answers;

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
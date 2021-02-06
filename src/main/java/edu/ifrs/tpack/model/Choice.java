/**
* @author rodrigo
*/
package edu.ifrs.tpack.model;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
/*
    Comentário sobre a classe
*/
public class Choice {

    @Id
    @GeneratedValue
    /*
        Comentário sobre o atributo
    */
    private long id;

    /*
        Comentário sobre o atributo
    */
    private String text;

    @ManyToOne
    /*
        Comentário sobre o atributo
    */
    private Question question;

    @OneToMany(mappedBy = "choice", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
    /*
        Comentário sobre o atributo
    */
    private List<Answer> answers;

    /* used just for multilevel questions */
    private byte level;

    /* jumps for this level when user choose this choice */
    private byte nextLevel;

    /*
        Comentário sobre o método
    */
    public long getId() {
        return id;
    }

    /*
        Comentário sobre o método
    */
    public void setId(long id) {
        this.id = id;
    }

    /*
        Comentário sobre o método
    */
    public String getText() {
        return text;
    }

    /*
        Comentário sobre o método
    */
    public void setText(String text) {
        this.text = text;
    }

    @JsonbTransient
    /*
        Comentário sobre o método
    */
    public Question getQuestion() {
        return question;
    }

    /*
        Comentário sobre o método
    */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /*
        Comentário sobre o método
    */
    public List<Answer> getAnswers() {
        return answers;
    }

    /*
        Comentário sobre o método
    */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /*
        Comentário sobre o método
    */
    public byte getLevel() {
        return level;
    }

    /*
        Comentário sobre o método
    */
    public void setLevel(byte level) {
        this.level = level;
    }

    /*
        Comentário sobre o método
    */
    public byte getNextLevel() {
        return nextLevel;
    }

    /*
        Comentário sobre o método
    */
    public void setNextLevel(byte nextLevel) {
        this.nextLevel = nextLevel;
    }

}

/**
 * @License
 * Copyright 2020 TPACK XS Application
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 */
/**
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
/**
 * Teste com comentário.
 */
public class Question {

    @Id
    @GeneratedValue
    /**
     * Teste com comentário.
     */
    private long id;

    @Column(name = "TEXT", length = 1500)
    /**
     * Teste com comentário.
     */
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

    @ManyToOne
    @JoinColumn(name = "SESSION_ID", nullable = false)
    /**
     * Teste com comentário.
     */
    private Session session;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    /**
     * Teste com comentário.
     */
    private List<Choice> choices;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
    /**
     * Teste com comentário.
     */
    private List<Answer> answers;

    /**
     * Calculates the levels for multilevel questions
     */
    @PostLoad
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void calculateLevels() {
        if (this.type == QuestionType.MULTILEVEL) {
            for (Choice choice : choices) {
                this.levels = choice.getLevel() > this.levels ? choice.getLevel() : this.levels;
            }
        }
    }

    /**
     * Teste com comentário.
     */ 
    public long getId() {
        return id;
    }

    /**
     * Teste com comentário.
     */ 
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Teste com comentário.
     */ 
    public String getText() {
        return text;
    }

    /**
     * Teste com comentário.
     */ 
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Teste com comentário.
     */ 
    public String getNote() {
        return note;
    }

    /**
     * Teste com comentário.
     */ 
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Teste com comentário.
     */ 
    public QuestionType getType() {
        return type;
    }

    /**
     * Teste com comentário.
     */ 
    public void setType(QuestionType type) {
        this.type = type;
    }

    /**
     * Teste com comentário.
     */ 
    public byte getLevels() {
        return levels;
    }

    /**
     * Teste com comentário.
     */ 
    public void setLevels(byte levels) {
        this.levels = levels;
    }

    @JsonbTransient
    /**
     * Teste com comentário.
     */ 
    public Session getSession() {
        return session;
    }

    /**
     * Teste com comentário.
     */ 
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Teste com comentário.
     */ 
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Teste com comentário.
     */ 
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Teste com comentário.
     */ 
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * Teste com comentário.
     */ 
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
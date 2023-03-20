/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
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

/**
 * 
 * @tested
 */
@Entity
public class Question {

/**
 * 
 * @tested
 */
    @Id
    @GeneratedValue
    private long id;

/**
 * 
 * @tested
 */
    @Column(name = "TEXT", length = 1500)
    private String text;

/**
 * 
 * @tested
 */
    /* one explanation about the question */
    private String note;

/**
 * 
 * @tested
 */
    /* identify the type of the question */
    private QuestionType type;

/**
 * 
 * @tested
 */
    /* indicates the number os levels in multilevel questions */
    @Transient
    private byte levels;

/**
 * 
 * @tested
 */
    @ManyToOne
    @JoinColumn(name = "SESSION_ID", nullable = false)
    private Session session;

/**
 * 
 * @tested
 */
    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Choice> choices;

/**
 * 
 * @tested
 */
    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
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
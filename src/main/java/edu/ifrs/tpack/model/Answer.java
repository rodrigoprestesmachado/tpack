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

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
/**
 * Teste com comentário.
 */ 
public class Answer {

    @Id
    @GeneratedValue
    /**
     * Teste com comentário.
     */
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    /**
     * Teste com comentário.
     */
    private Subject subject;

    @ManyToOne(cascade = CascadeType.PERSIST)
    /**
     * Teste com comentário.
     */
    private Question question;

    @ManyToOne(cascade = CascadeType.PERSIST)
    /**
     * Teste com comentário.
     */
    private Choice choice;

    /**
     * Teste com comentário.
     */
    private String answer;

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
    public Subject getSubject() {
        return subject;
    }

    /**
     * Teste com comentário.
     */ 
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Teste com comentário.
     */ 
    public Question getQuestion() {
        return question;
    }

    /**
     * Teste com comentário.
     */ 
    public void setQuestion(Question question) {
        this.question = question;
    }

    @JsonbTransient
    /**
     * Teste com comentário.
     */ 
    public String getAnswer() {
        return answer;
    }

    /**
     * Teste com comentário.
     */ 
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Teste com comentário.
     */ 
    public Choice getChoice() {
        return choice;
    }

    /**
     * Teste com comentário.
     */ 
    public void setChoice(Choice choice) {
        this.choice = choice;
    }

}
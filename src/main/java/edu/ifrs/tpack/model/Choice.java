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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Choice {

    @Id
    @GeneratedValue
    private long id;

    private String text;

    @ManyToOne
    private Question question;

    @OneToMany(mappedBy = "choice", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Answer> answers;

    /* used just for multilevel questions */
    private byte level;

    /* jumps for this level when user choose this choice */
    private byte nextLevel;

    /**
     * 
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     */
    @JsonbTransient
    public Question getQuestion() {
        return question;
    }
    /**
     * 
     * @param question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
    /**
     * 
     * @return
     */
    public List<Answer> getAnswers() {
        return answers;
    }
    /**
     * 
     * @param answers
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    /**
     * 
     * @return
     */
    public byte getLevel() {
        return level;
    }
    /**
     * 
     * @param level
     */
    public void setLevel(byte level) {
        this.level = level;
    }
    /**
     * 
     * @return
     */
    public byte getNextLevel() {
        return nextLevel;
    }
    /**
     * 
     * @param nextLevel
     */
    public void setNextLevel(byte nextLevel) {
        this.nextLevel = nextLevel;
    }

}

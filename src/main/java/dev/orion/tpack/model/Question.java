/**
 * @License Copyright 2022 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package dev.orion.tpack.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "QUESTION")
public class Question extends PanacheEntity {

  @Column(name = "TEXT", length = 1500)
  private String text;

  /* one explanation about the question */
  private String note;

  /* identify the type of the question */
  private QuestionType type;

  /* indicates the number os levels in multilevel questions */
  @Transient
  private byte levels;

  @ManyToOne
  @JoinColumn(name = "PAGE_ID", nullable = false)
  @JsonBackReference
  private Page page;

  @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<Choice> choices = new ArrayList<>();

  // @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
  // private List<Answer> answers;

  /**
   * Calculates the levels for multilevel questions
   */
  @PostLoad
  protected void calculateLevels() {
    if (this.type == QuestionType.MULTILEVEL) {
      for (Choice choice : choices) {
        this.levels = choice.getLevel() > this.levels ? choice.getLevel() : this.levels;
      }
    }
  }
}
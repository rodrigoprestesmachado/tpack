/**
 * @License
 * Copyright 2022 TPACK XS Application
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
package dev.orion.tpack.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "ANSWER")
public class Answer extends PanacheEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subject subject;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Question question;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Choice choice;

    private String answer;

}
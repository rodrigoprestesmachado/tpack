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
package dev.rpmhub.tpack.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "SUBJECT")
public class Subject extends PanacheEntity {

    private String token;

    private Timestamp moment;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Subject() {
        Calendar calendar = Calendar.getInstance();
        this.moment = new Timestamp(calendar.getTimeInMillis());
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Adds a answer to a subject
     *
     * @param answer Answer object
     */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

}
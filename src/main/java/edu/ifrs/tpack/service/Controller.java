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
package edu.ifrs.tpack.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.ifrs.tpack.model.Answer;
import edu.ifrs.tpack.model.Choice;
import edu.ifrs.tpack.model.Question;
import edu.ifrs.tpack.model.QuestionType;
import edu.ifrs.tpack.model.Session;
import edu.ifrs.tpack.model.Subject;

/**
 * Implements the API of the Tpack XS App
 */
@Path("/api")
@RequestScoped
public class Controller extends BaseController {

    /**
     * Stores all answers in the data base
     *
     * @param jsonString The answers in json
     * @return A Subject object
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Subject save(final String jsonString) {
        String errorMessage = null;

        // converts the json to a map
        final Jsonb jsonb = JsonbBuilder.create();
        Map<String, String> map = jsonb.fromJson(jsonString, HashMap.class);

        Subject subject = new Subject();
        try {
            // stores the subject
            daoSubject.create(subject);
            // stores all answers
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    final Question question = this.daoQuestion.find(Long.parseLong(entry.getKey()));
                    // handles multiple choices questions
                    if (question.getType() == QuestionType.MULTIPLE || question.getType() == QuestionType.MULTILEVEL
                            || question.getType() == QuestionType.UNIQUE) {
                        String[] strAnswer = entry.getValue().split(",");
                        for (String id : strAnswer) {
                            Choice choice = this.daoChoice.find(Long.parseLong(id));
                            this.createAnswer(subject, question, choice, entry.getValue());
                        }
                    } else {
                        this.createAnswer(subject, question, null, entry.getValue());
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (final Exception e) {
            errorMessage = "The database is out of service ";
            throw new WebApplicationException(errorMessage, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return subject;
    }

    /**
     * Creates and stores an answer object
     *
     * @param subject  The subject
     * @param question The question object
     * @param choice   Choice object for multiple selection question
     * @param data     Represents the answer in string format
     */
    private void createAnswer(Subject subject, Question question, Choice choice, String data) {
        final Answer answer = new Answer();
        answer.setSubject(subject);
        answer.setQuestion(question);
        answer.setAnswer(data);
        if (choice != null){
            answer.setChoice(choice);}
        daoAnswer.create(answer);
    }

    /**
     * Returns the sessions and related questions
     *
     * @return A list of sessions with related questions
     */
    @GET
    @Path("/getSessions")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Session> getSessions() {
        String errorMessage = null;
        try {
            return daoSession.read();
        } catch (final Exception e) {
            errorMessage = "The database is out of service ";
            throw new WebApplicationException(errorMessage, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/getFirstSession")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Session getFirstSession() {
        return daoSession.getFirstSession();
    }

    @GET
    @Path("/getSession/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Session getSession(@PathParam("id") final long id) {
        return daoSession.find(id);
    }

}

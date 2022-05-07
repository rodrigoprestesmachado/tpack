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
package dev.rpmhub.tpack.endpoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import dev.rpmhub.tpack.model.Answer;
import dev.rpmhub.tpack.model.Choice;
import dev.rpmhub.tpack.model.Page;
import dev.rpmhub.tpack.model.Question;
import dev.rpmhub.tpack.model.QuestionType;
import dev.rpmhub.tpack.model.Subject;

/**
 * Implements the API of the Tpack XS App
 */
@Path("/tpack")
@Transactional
public class TpackService {

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
    public Subject save(final String jsonString) {
        String errorMessage = null;

        // converts the json to a map
        final Jsonb jsonb = JsonbBuilder.create();
        Map<String, String> map = jsonb.fromJson(jsonString, HashMap.class);

        Subject subject = new Subject();
        try {
            // stores the subject
            subject.persist();
            // stores all answers
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                try {
                    final Question question = Question.findById(Long.parseLong(entry.getKey()));
                    // handles multiple choices questions
                    if (question.getType() == QuestionType.MULTIPLE || question.getType() == QuestionType.MULTILEVEL
                            || question.getType() == QuestionType.UNIQUE) {
                        String[] strAnswer = entry.getValue().split(",");
                        for (String id : strAnswer) {
                            Choice choice = Choice.findById(Long.parseLong(id));
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
        if (choice != null)
            answer.setChoice(choice);
        answer.persist();
    }

    /**
     * Returns the pages and related questions
     *
     * @return A list of pages with related questions
     */
    @GET
    @Path("/getPages")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Page> getPage() {
        String errorMessage = null;
        try {
            return Page.listAll();
        } catch (final Exception e) {
            errorMessage = "The database is out of service ";
            throw new WebApplicationException(errorMessage, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/getPage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Page getPage(@PathParam("id") final long id) {
        return Page.findById(id);
    }

    @GET
    @Path("/getFirstPage")
    @Produces(MediaType.APPLICATION_JSON)
    public Page getFirstPage() {
        return Page.findAll().firstResult();
    }

}
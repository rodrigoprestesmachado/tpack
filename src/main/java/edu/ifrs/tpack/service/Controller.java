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
import javax.inject.Inject;
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

import edu.ifrs.tpack.data.DAOAnswer;
import edu.ifrs.tpack.data.DAOQuestion;
import edu.ifrs.tpack.data.DAOSession;
import edu.ifrs.tpack.data.DAOSubject;
import edu.ifrs.tpack.model.Answer;
import edu.ifrs.tpack.model.Question;
import edu.ifrs.tpack.model.Session;
import edu.ifrs.tpack.model.Subject;

/**
 * Implements the API of the Tpack XS App
 */
@Path("/api")
@RequestScoped
public class Controller {

    @Inject
    private DAOSession daoSession;

    @Inject
    private DAOSubject daoSubject;

    @Inject
    private DAOQuestion daoQuestion;

    @Inject
    private DAOAnswer daoAnswer;

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
                final Answer answer = new Answer();
                try {
                    final Question question = this.daoQuestion.find(Long.parseLong(entry.getKey()));
                    answer.setQuestion(question);
                    answer.setAnswer(entry.getValue());
                    answer.setSubject(subject);
                } catch (NumberFormatException e) {
                    continue;
                }
                daoAnswer.create(answer);
            }
        } catch (final Exception e) {
            errorMessage = "The database is out of service ";
            throw new WebApplicationException(errorMessage, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return subject;
    }

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
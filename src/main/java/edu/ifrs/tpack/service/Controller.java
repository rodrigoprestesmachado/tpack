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

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.ifrs.tpack.data.DAOSession;
import edu.ifrs.tpack.model.Session;

/**
 * Implements the API of the Tpack XS App
 */
@Path("/api")
@RequestScoped
public class Controller {

    @Inject
    private DAOSession daoSession;

    @GET
    @Path("/getSessions")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Session> getSessions() {
        return daoSession.read();
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
    public Session getSession(@PathParam("id") long id) {
        return daoSession.find(id);
    }

}
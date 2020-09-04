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
package edu.ifrs.tpack.data;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.ifrs.tpack.model.Session;

public class DAOSession extends Repository<Session> {

    /**
     * Returns the first session object/register
     * 
     * @return A Session object
     */
    public Session getFirstSession() {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
        final Root<Session> root = criteria.from(Session.class);
        // Order by id
        criteria.orderBy(builder.asc(root.get("id")));
        // Get the first register
        Query query = em.createQuery(criteria).setMaxResults(1);
        return (Session) query.getResultList().get(0);
    }

}
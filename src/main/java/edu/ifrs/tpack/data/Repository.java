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

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Repository<T> {

    @PersistenceContext(name = "TpackDS")
    protected EntityManager entityManager;

    public T create(final T obj) {
        this.entityManager.persist(obj);
        return obj;
    }

    /**
     * Returns all objetcs/registers from the base
     * 
     * @return A List of T objects
     */
    public List<T> read() {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        final Root<T> root = criteria.from(this.genericClass());
        criteria.select(root);
        return entityManager.createQuery(criteria).getResultList();
    }

    /**
     * Updates the T object
     * 
     * @param The T object
     */
    public void update(final T obj) {
        this.entityManager.merge(obj);
    }

    /**
     *  Deletes the object in the data base
     * 
     * @param The id of the object
     */
    public void delete(final Long id) {
        final T obj = entityManager.find(this.genericClass(), id);
        this.entityManager.remove(obj);
    }

    /**
     * Finds a specific object by id
     * 
     * @param The id of teh object
     * @return The T object
     */
    public T find(final Long id) {
        return entityManager.find(this.genericClass(), id);
    }

    /**
     * Finds the value by colum 
     * @param column
     * @param value
     * @return
     */
    public T find(final String column, final String value) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        final Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where(builder.equal(root.get(column), value));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    private Class<T> genericClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package edu.ifrs.tpack.data;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * CLASSE Repository
 */
public class Repository<T> {

    @PersistenceContext(name = "TpackDS")
    /** CAMPO em */
    protected EntityManager em; 

    /** CONSTRUTOR */
    public T create(final T obj) {
        this.em.persist(obj); 
        return obj;
    }

    /**
     * Returns all objetcs/registers from the base
     * 
     * @return A List of T objects
     */
    public List<T> read() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        final Root<T> root = criteria.from(this.genericClass());
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * Updates the T object
     * 
     * @param The T object
     */
    public void update(T obj) {
        this.em.merge(obj);
    }

    /**
     *  Deletes the object in the data base
     * 
     * @param The id of the object
     */
    public void delete(Long id) {
        T obj = em.find(this.genericClass(), id);
        this.em.remove(obj);
    }

    /**
     * Finds a specific object by id
     * 
     * @param The id of teh object
     * @return The T object
     */
    public T find(Long id) {
        return em.find(this.genericClass(), id);
    }

    /**
     * Finds the value by colum 
     * @param column
     * @param value
     * @return
     */
    public T find(String column, String value) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<T> criteria = builder.createQuery(this.genericClass());
        Root<T> root = criteria.from(this.genericClass());
        criteria.select(root).where(builder.equal(root.get(column), value));
        return em.createQuery(criteria).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    private Class<T> genericClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
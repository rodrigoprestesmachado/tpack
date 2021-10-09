/**
 * @License
 * Copyright 2020 TPACK XS Application
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package edu.ifrs.tpack.service;

import javax.inject.Inject;

import edu.ifrs.tpack.data.DAOAnswer;
import edu.ifrs.tpack.data.DAOChoice;
import edu.ifrs.tpack.data.DAOQuestion;
import edu.ifrs.tpack.data.DAOSession;
import edu.ifrs.tpack.data.DAOSubject;

/**
 * Base to build controllers
 */
public class BaseController {

    /**
     * DAO of session
     */
    @Inject
    protected DAOSession daoSession;

    /**
     * DAO of subject
     */
    @Inject
    protected DAOSubject daoSubject;

    /**
     * DAO of question
     */
    @Inject
    protected DAOQuestion daoQuestion;

    /**
     * DAO of answer
     */
    @Inject
    protected DAOAnswer daoAnswer;

    /**
     * DAO of choice
     */
    @Inject
    protected DAOChoice daoChoice;
}

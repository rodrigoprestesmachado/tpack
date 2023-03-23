/**
 * @License
 * Copyright 2020 TPACK XS Application
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
    * @description protected DAOSession daoSession
    */
    @Inject
    protected DAOSession daoSession;

    /**
     * @description protected DAOSubject daoSubject
    */
    @Inject
    protected DAOSubject daoSubject;

    /**
     * @description protected DAOSubject daoSubject
    */
    @Inject
    protected DAOQuestion daoQuestion;

    /**
    * @description protected DAOAnswer daoAnswer
    */
    @Inject
    protected DAOAnswer daoAnswer;

    /**
    * @description protected DAOChoice daoChoice
    */
    @Inject
    protected DAOChoice daoChoice;
}

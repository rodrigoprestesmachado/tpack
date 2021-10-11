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
    @Inject
    /**
     * valor injetado de DAOSession
     */
    protected DAOSession daoSession;
    @Inject
    /**
     * valor injetado de DAOSession
     */
    protected DAOSubject daoSubject;
    @Inject
    /**
     * valor injetado de DAOSession
     */
    protected DAOQuestion daoQuestion;
    @Inject
    /**
     * valor injetado de DAOSession
     */
    protected DAOAnswer daoAnswer;
    @Inject
    /**
     * valor injetado de DAOSession
     */
    protected DAOChoice daoChoice;
}

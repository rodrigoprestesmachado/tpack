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
     * Comentando atributo
     */
    protected DAOSession daoSession;
    @Inject
    /**
     * Comentando atributo
     */
    protected DAOSubject daoSubject;
    @Inject
    /**
     * Comentando atributo
     */
    protected DAOQuestion daoQuestion;
    @Inject
    /**
     * Comentando atributo
     */
    protected DAOAnswer daoAnswer;
    @Inject
    /**
     * Comentando atributo
     */
    protected DAOChoice daoChoice;
}

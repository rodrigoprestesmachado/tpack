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
     * Comentário 
     */
    protected DAOSession daoSession;
    @Inject
    /**
     * Comentário 
     */
    protected DAOSubject daoSubject;
    @Inject
    /**
     * Comentário 
     */
    protected DAOQuestion daoQuestion;
    @Inject
    /**
     * Comentário 
     */
    protected DAOAnswer daoAnswer;
    @Inject
    /**
     * Comentário 
     */
    protected DAOChoice daoChoice;
}

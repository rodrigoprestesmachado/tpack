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
     * descricao de atributo
     */
    @Inject
    protected DAOSession daoSession;
    
    /**
     * descricao de atributo
     */
    @Inject
    protected DAOSubject daoSubject;
    
    /**
     * descricao de atributo
     */
    @Inject
    protected DAOQuestion daoQuestion;
    
    /**
     * descricao de atributo
     */
    @Inject
    protected DAOAnswer daoAnswer;
    
    /**
     * descricao de atributo
     */
    @Inject
    protected DAOChoice daoChoice;
}

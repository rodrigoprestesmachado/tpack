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
/**
* 
*
* @author Rodrigo Maria Morgão
*/
public class BaseController {
    @Inject
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    protected DAOSession daoSession;
    @Inject
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    protected DAOSubject daoSubject;
    @Inject
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    protected DAOQuestion daoQuestion;
    @Inject
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    protected DAOAnswer daoAnswer;
    @Inject
    /**
    * 
    *
    * @author Rodrigo Maria Morgão
    */
    protected DAOChoice daoChoice;
}

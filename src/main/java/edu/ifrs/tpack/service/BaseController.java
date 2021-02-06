/**
* @author rodrigo
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
    @Inject
    /*
        Comentário sobre o atributo
    */
    protected DAOSession daoSession;
    @Inject
    /*
        Comentário sobre o atributo
    */
    protected DAOSubject daoSubject;
    @Inject
    /*
        Comentário sobre o atributo
    */
    protected DAOQuestion daoQuestion;
    @Inject
    /*
        Comentário sobre o atributo
    */
    protected DAOAnswer daoAnswer;
    @Inject
    /*
        Comentário sobre o atributo
    */
    protected DAOChoice daoChoice;
}

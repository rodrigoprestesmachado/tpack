/**
 * @License
 * Copyright 2020 TPACK XS Application
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
    @Inject
    protected DAOSession daoSession;
    @Inject
    protected DAOSubject daoSubject;
    @Inject
    protected DAOQuestion daoQuestion;
    @Inject
    protected DAOAnswer daoAnswer;
    @Inject
    protected DAOChoice daoChoice;
}

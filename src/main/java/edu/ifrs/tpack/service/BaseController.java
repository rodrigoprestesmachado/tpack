/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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
    /** CAMPO daoSession */
    protected DAOSession daoSession;
    @Inject
    /** CAMPO daoSubject */
    protected DAOSubject daoSubject;
    @Inject
    /** CAMPO daoQuestion */
    protected DAOQuestion daoQuestion;
    @Inject
    /** CAMPO daoAnswer */
    protected DAOAnswer daoAnswer;
    @Inject
    /** CAMPO daoChoice */
    protected DAOChoice daoChoice;
}

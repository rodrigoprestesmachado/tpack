/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
    *
    *
    * comment for pmd
    */
    @Inject
    protected DAOSession daoSession;
    /**
    *
    *
    * comment for pmd
    */
    @Inject
    protected DAOSubject daoSubject;
    /**
    *
    *
    * comment for pmd
    */
    @Inject
    protected DAOQuestion daoQuestion;
    /**
    *
    *
    * comment for pmd
    */
    @Inject
    protected DAOAnswer daoAnswer;
    /**
    *
    *
    * comment for pmd
    */
    @Inject
    protected DAOChoice daoChoice;
}

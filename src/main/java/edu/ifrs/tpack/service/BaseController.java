/**
 * @License
 * Copyright 2020 TPACK XS Application
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * 
 * @Tested
 */
public class BaseController {

    /**
     * 
     * @tested
     */
    @Inject
    protected DAOSession daoSession;

    /**
     * 
     * @tested
     */
    @Inject
    protected DAOSubject daoSubject;

    /**
     * 
     * @tested
     */
    @Inject
    protected DAOQuestion daoQuestion;

    /**
     * 
     * @tested
     */
    @Inject
    protected DAOAnswer daoAnswer;

    /**
     * 
     * @tested
     */
    @Inject
    protected DAOChoice daoChoice;
}

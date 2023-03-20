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
package edu.ifrs.tpack.model;

/**
 * Indicates the question type available
 */
public enum QuestionType {

    BINARY(0), SCALE(1), MULTIPLE(2), UNIQUE(3), YEAR(4), AGE(5), REGION(6), LABEL(7), SAVE(8), MULTILEVEL(9),
    TEXTAREA(10);

/**
 * 
 * @tested
 */
    private int value;

    QuestionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
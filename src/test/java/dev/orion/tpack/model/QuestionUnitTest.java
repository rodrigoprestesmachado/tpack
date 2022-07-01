package dev.orion.tpack.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class QuestionUnitTest {

  @Test
  void testNotMultiLevelType() {
    final Question question = new Question();
    question.setType(QuestionType.UNIQUE);
    question.setLevels((byte) 0);
    question.calculateLevels();

    // Levels doesn't change
    Assertions.assertEquals(question.getLevels(), (byte) 0);
  }

  @Test
  void testMultiLevelTypeNoChoices() {
    final Question question = new Question();
    question.setType(QuestionType.MULTILEVEL);
    question.setLevels((byte) 0);
    question.calculateLevels();

    // Levels doesn't change
    Assertions.assertEquals(question.getLevels(), (byte) 0);
  }

  @Test
  void testMultiLevelTypeWithChoice() {
    final Question question = new Question();
    question.setType(QuestionType.MULTILEVEL);
    question.setLevels((byte) 0);

    final Choice choice = new Choice();
    choice.setLevel((byte) 1);
    question.setChoices(Collections.singletonList(choice));
    question.calculateLevels();

    // Should be equal to the choice level
    Assertions.assertEquals(question.getLevels(), (byte) 1);
  }

  @Test
  void testMultiLevelTypeWithMoreChoices() {
    final Question question = new Question();
    question.setType(QuestionType.MULTILEVEL);
    question.setLevels((byte) 0);

    final Choice choice1 = new Choice();
    choice1.setLevel((byte) 1);
    final Choice choice2 = new Choice();
    choice2.setLevel((byte) 2);
    question.setChoices(Arrays.asList(choice1, choice2));

    question.calculateLevels();

    // Should be equal to the choice level 2
    Assertions.assertEquals(question.getLevels(), (byte) 2);
  }

  @Test
  void testMultiLevelTypeWithChoiceNoChange() {
    final Question question = new Question();
    question.setType(QuestionType.MULTILEVEL);
    question.setLevels((byte) 1);

    final Choice choice = new Choice();
    choice.setLevel((byte) 0);
    question.setChoices(Collections.singletonList(choice));
    question.calculateLevels();

    // Should be equal to the original level 1
    Assertions.assertEquals(question.getLevels(), (byte) 1);
  }
}
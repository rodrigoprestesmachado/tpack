package dev.orion.tpack;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import dev.orion.tpack.endpoints.TpackService;
import dev.orion.tpack.model.Question;

@QuarkusTest
class TPackServiceUnitTest {
  @Inject
  TpackService service;

  @Test
  @Disabled("Panache mock not working.")
  public void testSave() {
    // https://quarkus.io/guides/hibernate-orm-panache#mocking
    final Question question = new Question();
    question.setNote("note");

    PanacheMock.mock(Question.class);
    Mockito.when(Question.findById(1)).thenReturn(question);

    // must add other mocks when findById works
    // service.save("{\"1\":\"response\"}");
  }
}
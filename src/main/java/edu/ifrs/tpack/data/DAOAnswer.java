package edu.ifrs.tpack.data;
import edu.ifrs.tpack.model.Answer;


public class DAOAnswer extends Repository<Answer> {
/**
 *
 */
private String answer;


public DAOAnswer(String answer) {
      this.answer = answer;
   }

public String getAnswer() {
    return answer;
}

public void setAnswer(String answer) {
    this.answer = answer;
}
}
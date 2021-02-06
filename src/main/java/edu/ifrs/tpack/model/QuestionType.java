package edu.ifrs.tpack.model;

/**
 * Indicates the question type available
 */
public enum QuestionType {

    BINARY(0), SCALE(1), MULTIPLE(2), UNIQUE(3), YEAR(4), AGE(5), REGION(6), LABEL(7), SAVE(8), MULTILEVEL(9),
    TEXTAREA(10);

    private int value;

    /*
        Comentário sobre o método
    */
    QuestionType(int value) {
        this.value = value;
    }

    /*
        Comentário sobre o método
    */
    public int getValue() {
        return value;
    }
}
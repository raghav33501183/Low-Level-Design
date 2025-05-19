package models;

import java.util.List;

public class QuestionFactory {
    public static Question createQuestion(String type, String questionText, List<String> options) {
        if (type.equalsIgnoreCase("text")) {
            return new TextQuestion(questionText);
        } else if (type.equalsIgnoreCase("mcq")) {
            return new MCQQuestion(questionText, options);
        } else {
            throw new IllegalArgumentException("Unsupported question type: " + type);
        }
    }
}

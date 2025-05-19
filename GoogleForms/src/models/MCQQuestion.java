package models;

import java.util.List;

public class MCQQuestion extends Question {
    private final List<String> options;

    public MCQQuestion(String questionText, List<String> options) {
        super(questionText);
        this.options = options;
    }

    @Override
    public void answer(String response) {
        if (options.contains(response)) {
            this.answer = response;
        } else {
            throw new IllegalArgumentException("Invalid option selected.");
        }
    }

    public List<String> getOptions() {
        return options;
    }
}

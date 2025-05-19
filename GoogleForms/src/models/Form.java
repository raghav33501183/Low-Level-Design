package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Form {
    private String formId;
    private String title;
    private List<Question> questions;

    public Form(String title) {
        this.formId = UUID.randomUUID().toString();
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getFormId() {
        return formId;
    }

    public String getTitle() {
        return title;
    }
}

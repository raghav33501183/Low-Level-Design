package models;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String formId;
    private List<String> answers;

    public Response(String formId) {
        this.formId = formId;
        this.answers = new ArrayList<>();
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getFormId() {
        return formId;
    }
}

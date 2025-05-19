package models;

public class Question {
    protected String questionText;
    protected QuestionType type;
    protected String answer;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getType() {
        return type.name();
    }

    public String getAnswer() {
        return answer;
    }

    public void answer(String response) {
        this.answer = response;
    }
}

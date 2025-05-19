package models;

import java.util.ArrayList;
import java.util.List;

public class Question extends  AbstractPost{
    private List<Answer> answers = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public Question(String content, User author) {
        super(content, author);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Tag> getTags() {
        return tags;
    }
}

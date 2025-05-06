package models;

public class Publisher {
    private final Topic topic;

    public Publisher(Topic topic) {
        this.topic = topic;
    }

    public void publish(String content) {
        topic.publish(new Message(content));
    }
}

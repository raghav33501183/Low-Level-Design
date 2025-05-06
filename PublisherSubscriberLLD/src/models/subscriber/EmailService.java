package models.subscriber;

import models.Message;

public class EmailService implements Subscriber {
    private final String id;

    public EmailService(String id) {
        this.id = id;
    }

    @Override
    public void consume(Message message) {
        System.out.println("[EmailService] Received: " + message.getContent());
    }

    @Override
    public String getId() {
        return id;
    }
}


package models.subscriber;

import models.Message;

public class SmsService implements Subscriber {
    private final String id;

    public SmsService(String id) {
        this.id = id;
    }

    @Override
    public void consume(Message message) {
        System.out.println("[SMSService] Received: " + message.getContent());
    }

    @Override
    public String getId() {
        return id;
    }
}
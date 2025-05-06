package models.subscriber;

import models.Message;

public interface Subscriber {
    void consume(Message message);
    String getId();
}


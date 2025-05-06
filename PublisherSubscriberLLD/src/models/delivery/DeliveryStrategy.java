package models.delivery;

import models.Message;
import models.subscriber.Subscriber;

import java.util.List;

public interface DeliveryStrategy {
    void deliver(List<Subscriber> subscribers, Message message);
}

package models.delivery;

import models.Message;
import models.subscriber.Subscriber;

import java.util.List;

public class SyncDeliveryStrategy implements DeliveryStrategy {
    @Override
    public void deliver(List<Subscriber> subscribers, Message message) {
        for (Subscriber s : subscribers) {
            s.consume(message);
        }
    }
}


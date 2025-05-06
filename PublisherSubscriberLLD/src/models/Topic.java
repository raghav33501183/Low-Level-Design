package models;

import models.delivery.DeliveryStrategy;
import models.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private final DeliveryStrategy deliveryStrategy;

    public Topic(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println(subscriber.getId() + " subscribed.");
    }

    public void unsubscribe(String subscriberId) {
        subscribers.removeIf(s -> s.getId().equals(subscriberId));
        System.out.println("Unsubscribed: " + subscriberId);
    }

    public void publish(Message message) {
        System.out.println("Publishing: " + message.getContent());
        deliveryStrategy.deliver(subscribers, message);
    }
}


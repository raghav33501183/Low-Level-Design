import models.Publisher;
import models.Topic;
import models.delivery.SyncDeliveryStrategy;
import models.subscriber.Subscriber;
import models.subscriber.SubscriberFactory;

public class Main {
    public static void main(String[] args) {
        Topic topic = new Topic(new SyncDeliveryStrategy());
        Publisher publisher = new Publisher(topic);

        Subscriber email = SubscriberFactory.getSubscriber("EMAIL", "email-1");
        Subscriber sms = SubscriberFactory.getSubscriber("SMS", "sms-1");

        topic.subscribe(email);
        topic.subscribe(sms);

        publisher.publish("User Registered");
        topic.unsubscribe("sms-1");
        publisher.publish("User Verified");
    }
}
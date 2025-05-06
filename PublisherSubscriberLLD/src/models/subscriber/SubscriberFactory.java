package models.subscriber;

public class SubscriberFactory {

    public static Subscriber getSubscriber(String type, String id) {
        switch (type) {
            case "EMAIL":
                return new EmailService(id);

            case "SMS":
                return new SmsService(id);

            default:
                throw new RuntimeException("No such subscriber type exists");
        }
    }
}

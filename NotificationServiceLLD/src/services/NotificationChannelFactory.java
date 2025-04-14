package services;

public class NotificationChannelFactory {
    public static NotificationChannel getChannel(String type) {
        switch (type) {
            case "EMAIL":
                return new EmailChannel();
            case "SMS":
                return new SMSChannel();
            default:
                throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
    }
}

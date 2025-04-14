package services;

import models.Notification;

public class NotificationDispatcher {
    private static NotificationDispatcher instance;

    private NotificationDispatcher() {}

    public static NotificationDispatcher getInstance() {
        if (instance == null) {
            instance = new NotificationDispatcher();
        }
        return instance;
    }

    public void dispatch(Notification notification, String type) {
        NotificationChannel channel = NotificationChannelFactory.getChannel(type);
        NotificationChannel retryingChannel = new RetryDecorator(channel);
        retryingChannel.send(notification);
    }
}

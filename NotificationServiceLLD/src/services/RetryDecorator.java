package services;

import models.Notification;

public class RetryDecorator implements NotificationChannel {
    private NotificationChannel channel;

    public RetryDecorator(NotificationChannel channel) {
        this.channel = channel;
    }

    public void send(Notification notification) {
        try {
            channel.send(notification);
        } catch (Exception e) {
            System.out.println("Retrying notification to " + notification.getReceiver());
            channel.send(notification);
        }
    }
}

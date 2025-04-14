package services;

import models.Notification;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("Sending Email to " + notification.getReceiver() + ": " + notification.getMessage());
    }
}

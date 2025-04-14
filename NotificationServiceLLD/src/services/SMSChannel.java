package services;

import models.Notification;

public class SMSChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("Sending SMS to " + notification.getReceiver() + ": " + notification.getMessage());
    }
}

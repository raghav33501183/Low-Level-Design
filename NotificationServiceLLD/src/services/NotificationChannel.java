package services;

import models.Notification;

public interface NotificationChannel {
    void send(Notification notification);
}

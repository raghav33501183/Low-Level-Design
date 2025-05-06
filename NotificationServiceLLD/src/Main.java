import models.Notification;
import services.NotificationDispatcher;

public class Main {
    public static void main(String[] args) {
        Notification notification = new Notification("user@example.com", "Welcome to the platform!");
        NotificationDispatcher dispatcher = NotificationDispatcher.getInstance();
        dispatcher.dispatch(notification, "EMAIL");
        dispatcher.dispatch(notification, "SMS");
    }
}
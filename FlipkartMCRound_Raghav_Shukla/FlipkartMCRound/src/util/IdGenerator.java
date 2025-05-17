package util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(1000);

    public static String generateBookId(String author) {
        String[] names = author.trim().split(" ");
        String lastName = names[names.length - 1];
        String prefix = lastName.length() >= 3 ? lastName.substring(0, 3).toUpperCase() : lastName.toUpperCase();
        return prefix + counter.getAndIncrement();
    }

    public static String generateUserId() {
        return "U" + counter.getAndIncrement();
    }
}

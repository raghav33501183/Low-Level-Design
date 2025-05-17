package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String userId;
    private final String name;
    private final Map<String, Long> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new HashMap<>();
    }

    public void borrowBook(String bookId) {
        borrowedBooks.put(bookId, System.currentTimeMillis());
    }

    public Long returnBook(String bookId) {
        return borrowedBooks.remove(bookId);
    }

    public boolean hasBorrowed(String bookId) {
        return borrowedBooks.containsKey(bookId);
    }

    public Map<String, Long> getBorrowedBooks() {
        return Collections.unmodifiableMap(borrowedBooks);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

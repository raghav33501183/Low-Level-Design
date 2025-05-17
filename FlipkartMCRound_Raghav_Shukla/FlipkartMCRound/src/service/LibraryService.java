package service;

import exceptions.ValidationException;
import model.Book;
import model.User;
import util.IdGenerator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LibraryService {
    private final Map<String, Book> bookCatalog = new ConcurrentHashMap<>();
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> auditUserBooks = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> auditBookUsers = new ConcurrentHashMap<>();

    public synchronized String addBook(String title, String author, int copies) {
        String bookId = IdGenerator.generateBookId(author);
        Book book = new Book(bookId, title, author, copies);
        bookCatalog.put(bookId, book);
        System.out.println("Added book: " + title + " with ID: " + bookId);
        return bookId;
    }

    public synchronized String registerUser(String name) {
        String userId = IdGenerator.generateUserId();
        User user = new User(userId, name);
        users.put(userId, user);
        auditUserBooks.put(userId, new HashSet<>());
        System.out.println("Registered user: " + name + " with ID: " + user.getUserId());
        return userId;
    }

    public synchronized boolean unregisterUser(String userId) {
        User user = users.get(userId);
        if (user == null) {
            return false;
        }

        if (!user.getBorrowedBooks().isEmpty()) {
            System.out.println("Cannot unregister: user has borrowed books.");
            return false;
        }

        for (Book book : bookCatalog.values()) {
            synchronized (book) {
                List<String> queue = book.getReservationQueue();
                if (queue.contains(userId)) {
                    Queue<String> updatedQueue = new LinkedList<>();
                    for (String id : queue) {
                        if (!id.equals(userId)) {
                            updatedQueue.add(id);
                        }
                    }

                    book.clearAndSetReservationQueue(updatedQueue);
                }
            }
        }

        auditUserBooks.remove(userId);
        for (Set<String> userSet : auditBookUsers.values()) {
            userSet.remove(userId);
        }

        users.remove(userId);
        return true;
    }

    public synchronized boolean borrowBook(String userId, String bookId) {
        User user = users.get(userId);
        Book book = bookCatalog.get(bookId);

        if (user == null || book == null) {
            return false;
        }

        if (user.hasBorrowed(bookId)) {
            System.out.println("User: " + user.getName() + " has already borrowed the book: " + book.getTitle());
            return false;
        }

        if (book.isAvailable()) {
            if (book.borrow(userId)) {
                user.borrowBook(bookId);
                auditUserBooks.get(userId).add(bookId);
                auditBookUsers.computeIfAbsent(bookId, k -> new HashSet<>()).add(userId);
                return true;
            }
        }

        book.addToReservationQueue(userId);
        return false;
    }

    public synchronized int returnBook(String userId, String bookId) {
        User user = users.get(userId);
        Book book = bookCatalog.get(bookId);

        if (user == null) {
            throw new ValidationException("User does not exist with ID: " + userId);
        }

        if (book == null) {
            throw new ValidationException("Book does not exist with ID: " + bookId);
        }

        if (!user.hasBorrowed(bookId)) {
            throw new ValidationException("User: " + user.getName() + " hasn't borrowed the book: " + book.getTitle());
        }

        long borrowTime = user.returnBook(bookId);
        int fine = FineStrategyFactory.getFineStrategy().calculateFine(borrowTime);
        book.returnBook();
        return fine;
    }

    public synchronized Set<String> getUsersByBook(String bookId) {
        return auditBookUsers.getOrDefault(bookId, Collections.emptySet());
    }

    public synchronized Set<String> getBooksByUser(String userId) {
        return auditUserBooks.getOrDefault(userId, Collections.emptySet());
    }

    public Map<String, Book> getBookCatalog() {
        return bookCatalog;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}

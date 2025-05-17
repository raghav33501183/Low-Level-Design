package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Book {
    private final String bookId;
    private final String title;
    private final String author;
    private int totalCopies;
    private int borrowedCopies;
    private final Queue<String> reservationQueue;

    public Book(String bookId, String title, String author, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = copies;
        this.borrowedCopies = 0;
        this.reservationQueue = new LinkedList<>();
    }

    public synchronized boolean isAvailable() {
        return borrowedCopies < totalCopies;
    }

    public synchronized boolean borrow(String userId) {
        if (isAvailable() && (reservationQueue.isEmpty() || reservationQueue.peek().equals(userId))) {
            borrowedCopies++;
            reservationQueue.poll();
            return true;
        }

        return false;
    }

    public synchronized void returnBook() {
        borrowedCopies--;
    }

    public synchronized void addToReservationQueue(String userId) {
        if (!reservationQueue.contains(userId)) {
            reservationQueue.add(userId);
        }
    }

    public synchronized void clearAndSetReservationQueue(Queue<String> newQueue) {
        reservationQueue.clear();
        reservationQueue.addAll(newQueue);
    }

    public synchronized boolean isFirstInQueue(String userId) {
        return userId.equals(reservationQueue.peek());
    }

    public synchronized List<String> getReservationQueue() {
        return new ArrayList<>(reservationQueue);
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public void increaseCopies(int count) {
        totalCopies += count;
    }
}

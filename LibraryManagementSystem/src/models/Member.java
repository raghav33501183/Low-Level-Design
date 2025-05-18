package models;

import services.DefaultFineStrategy;
import services.FineStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends User {
    String memberId;
    List<BookItem> issuedBooks = new ArrayList<>();
    static final int MAX_BOOKS_ALLOWED = 5;
    static final int BORROW_DAYS_LIMIT = 14;

    public Member(String name, String email, Account account, String memberId) {
        super(name, email, account);
        this.memberId = memberId;
    }

    public boolean issueBook(BookItem item) {
        if (issuedBooks.size() >= MAX_BOOKS_ALLOWED || item.status != BookStatus.AVAILABLE) return false;
        item.status = BookStatus.ISSUED;
        item.issueDate = new Date();
        issuedBooks.add(item);
        return true;
    }

    public boolean returnBook(BookItem item) {
        if (!issuedBooks.contains(item)) return false;
        item.status = BookStatus.AVAILABLE;
        Date currentDate = new Date();
        long daysElapsed = (currentDate.getTime() - item.issueDate.getTime()) / (1000 * 60 * 60 * 24);

        if (daysElapsed > BORROW_DAYS_LIMIT) {
            FineStrategy fineStrategy = new DefaultFineStrategy();
            double fine = fineStrategy.calculateFine(daysElapsed - BORROW_DAYS_LIMIT);
            System.out.println("Book is returned late. Fine to be paid: $" + fine);
        } else {
            System.out.println("Book returned on time. No fine.");
        }

        issuedBooks.remove(item);
        return true;
    }
}

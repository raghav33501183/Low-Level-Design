import models.*;
import services.SearchFactory;
import services.SearchStrategy;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        // Librarian adds books
        Librarian librarian = new Librarian("Alice", "alice@lib.com", new Account("alice", "pass"));
        Book book1 = new Book("123", "Design Patterns", List.of("Gamma", "Helm"), "Pearson");
        BookItem item1 = new BookItem("BC101", book1, new Rack(1, "A1"));
        BookItem item2 = new BookItem("BC102", book1, new Rack(1, "A2"));
        librarian.addBookToCatalog(catalog, book1);
        librarian.addBookItem(book1, item1);
        librarian.addBookItem(book1, item2);

        // Member searches and borrows book
        Member member = new Member("Bob", "bob@reader.com", new Account("bob", "pass"), "M001");
        SearchStrategy strategy = SearchFactory.getStrategy("title");
        List<Book> foundBooks = strategy.search(catalog, "Design Patterns");

        if (!foundBooks.isEmpty()) {
            Book book = foundBooks.get(0);
            Optional<BookItem> availableCopy = book.copies.stream()
                    .filter(copy -> copy.status == BookStatus.AVAILABLE)
                    .findFirst();

            if (availableCopy.isPresent()) {
                if (member.issueBook(availableCopy.get())) {
                    System.out.println(member.name + " successfully borrowed the book: " + book.title);

                    // Simulate a real-time delay to mimic borrowing time
                    try {
                        Thread.sleep(2000); // Simulate 2 seconds delay for demo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    BookItem borrowedCopy = availableCopy.get();
                    // artificially subtract 16 days worth of milliseconds to simulate overdue return
                    borrowedCopy.issueDate = new Date(System.currentTimeMillis() - 16L * 24 * 60 * 60 * 1000);
                    member.returnBook(borrowedCopy);
                } else {
                    System.out.println("Could not borrow the book.");
                }
            } else {
                System.out.println("No copies available for " + book.title);
            }
        }
    }
}
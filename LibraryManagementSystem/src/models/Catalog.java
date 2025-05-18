package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    private Map<String, List<Book>> titleIndex = new HashMap<>();
    private Map<String, List<Book>> authorIndex = new HashMap<>();
    private Map<String, Book> isbnIndex = new HashMap<>();

    public void addBook(Book book) {
        titleIndex.computeIfAbsent(book.title, k -> new ArrayList<>()).add(book);
        for (String author : book.authors) {
            authorIndex.computeIfAbsent(author, k -> new ArrayList<>()).add(book);
        }
        isbnIndex.put(book.isbn, book);
    }

    public List<Book> searchBooksByTitle(String title) {
        return titleIndex.getOrDefault(title, new ArrayList<>());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return authorIndex.getOrDefault(author, new ArrayList<>());
    }

    public List<Book> searchBooksByIsbn(String isbn) {
        Book book = isbnIndex.get(isbn);
        return book != null ? List.of(book) : new ArrayList<>();
    }
}

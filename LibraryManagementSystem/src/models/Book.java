package models;

import java.util.ArrayList;
import java.util.List;

public class Book {
    public String isbn;
    public String title;
    public List<String> authors;
    public String publisher;
    public List<BookItem> copies = new ArrayList<>();

    public Book(String isbn, String title, List<String> authors, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
    }
}

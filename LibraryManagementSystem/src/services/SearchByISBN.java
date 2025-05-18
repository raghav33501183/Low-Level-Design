package services;

import models.Book;
import models.Catalog;

import java.util.List;

public class SearchByISBN implements SearchStrategy {
    public List<Book> search(Catalog catalog, String isbn) {
        return catalog.searchBooksByIsbn(isbn);
    }
}

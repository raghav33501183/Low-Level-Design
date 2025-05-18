package services;

import models.Book;
import models.Catalog;

import java.util.List;

public class SearchByTitle implements SearchStrategy {
    public List<Book> search(Catalog catalog, String title) {
        return catalog.searchBooksByTitle(title);
    }
}

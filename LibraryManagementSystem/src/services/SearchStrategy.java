package services;

import models.Book;
import models.Catalog;

import java.util.List;

public interface SearchStrategy {
    List<Book> search(Catalog catalog, String query);
}

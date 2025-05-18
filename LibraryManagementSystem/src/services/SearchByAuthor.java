package services;

import models.Book;
import models.Catalog;

import java.util.List;

public class SearchByAuthor implements SearchStrategy{
    public List<Book> search(Catalog catalog, String author) {
        return catalog.searchBooksByAuthor(author) ;
    }
}

package models;

public class Librarian extends User {
    public Librarian(String name, String email, Account account) {
        super(name, email, account);
    }

    public void addBookToCatalog(Catalog catalog, Book book) {
        catalog.addBook(book);
    }

    public void addBookItem(Book book, BookItem item) {
        book.copies.add(item);
    }
}

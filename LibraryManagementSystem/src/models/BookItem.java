package models;

import java.util.Date;

public class BookItem {
    public String barcode;
    public BookStatus status;
    public Date addedDate;
    public Rack rack;
    public Book book;
    public Date issueDate;

    public BookItem(String barcode, Book book, Rack rack) {
        this.barcode = barcode;
        this.book = book;
        this.status = BookStatus.AVAILABLE;
        this.addedDate = new Date();
        this.rack = rack;
    }
}

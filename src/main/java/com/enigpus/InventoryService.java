package com.enigpus;

import java.io.FileNotFoundException;

import com.enigpus.model.BookModel;

public interface InventoryService {
    void addBook(BookModel book);
    void searchBookByTitle(String title);
    BookModel searchBookById(String id);
    void deleteBook(Integer id) throws IndexOutOfBoundsException, FileNotFoundException;
    void listBooks();
    void EditBook(String id, BookModel book);
}

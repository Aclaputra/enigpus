package com.enigpus;

import com.enigpus.model.BookModel;

public interface InventoryService {
    void addBook(BookModel book);

    void searchBookByTitle(String title);

    BookModel searchBookById(String id);

    void deleteBook(String id);

    void listBooks();

    void EditBook(String id, BookModel book);

}

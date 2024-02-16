package com.enigpus;

import java.util.List;

import com.enigpus.model.BookModel;

public interface InventoryService {
    void addBook(BookModel book);

    BookModel searchBookByTitle(String title);

    BookModel searchBookById(int id);

    Integer searchBookIdByCode(String code);

    void deleteBook(int id);

    List<BookModel> getAllBook();

    void EditBook(String id, BookModel book);

}

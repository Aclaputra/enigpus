package com.enigpus.service;

import java.io.FileNotFoundException;

import com.enigpus.model.BookModel;

public interface InventoryService {
    void addBook(BookModel book);
    void searchBookByTitle(String title);
    BookModel searchBookById(Integer id);
    void deleteBook(Integer id) throws IndexOutOfBoundsException, FileNotFoundException;
    void getAllBook();
    void EditBook(Integer id, BookModel book) throws FileNotFoundException;
}

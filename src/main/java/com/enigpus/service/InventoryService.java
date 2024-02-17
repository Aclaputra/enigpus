package com.enigpus.service;

import java.io.FileNotFoundException;

import com.enigpus.model.BookModel;

public interface InventoryService {
    void addBook(BookModel book) throws Exception;
    void searchBookByTitle(String title) throws Exception;
    BookModel searchBookById(Integer id) throws Exception;
    void deleteBook(Integer id) throws IndexOutOfBoundsException, FileNotFoundException, Exception ;
    void getAllBook() throws IndexOutOfBoundsException, Exception;
    void editBook(Integer id, BookModel book) throws FileNotFoundException, IndexOutOfBoundsException, Exception;
}

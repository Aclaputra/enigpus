package com.enigpus.service.impl;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.enigpus.Constant;
import com.enigpus.model.BookModel;
import com.enigpus.service.InventoryService;
import com.enigpus.util.Helper;

public class InventoryServiceImpl implements InventoryService {
    private static List<BookModel> memoryBooks = new ArrayList<>();

    public static void clearMemory() {
        memoryBooks.clear();
    }
    
    private void appendMemoryToDatabase(boolean isAppend) {
        Helper.appendToCSV(memoryBooks, Constant.BOOKS_PATH, isAppend);
    }

    public void memoryToDatabase() throws FileNotFoundException {
        if (!(memoryBooks.isEmpty()) ) {
            appendMemoryToDatabase(true);
        } else {
            System.out.println("cannot add book. there's no book in the memory or database");
        }
    }

    /*
     * Get Data From CSV Database Resource to Memory for hIndexing
     * @param filepath
     */
    private void transferDBDataToMemory(String filepath) {
        List<List<String>> books = Helper.convertFromCSV(filepath);
        books.remove(0);
        memoryBooks.clear();
        for (List<String> book : books) {
            String code = book.get(0);
            String title = book.get(1);
            String type = book.get(2);
            String publicationYear = book.get(3);

            BookModel bookModel = new BookModel(code, title, type, publicationYear);
            if (type.equals("novel")) {
                String author = book.get(4);
                bookModel.setAuthor(author);
            }
            memoryBooks.add(bookModel);
        }
    }

    @Override
    public void addBook(BookModel book) throws Exception{
        memoryBooks.add(book);
    }

    @Override
    public BookModel searchBookById(Integer id) throws Exception {  
        transferDBDataToMemory(Constant.BOOKS_PATH);

        return memoryBooks.get(id-1);
    }

    @Override
    public void searchBookByTitle(String title) throws Exception {
        List<List<String>> books = Helper.convertFromCSV(Constant.BOOKS_PATH);
        books.remove(0);
        for (List<String> book : books) {
            if (book.get(1).toLowerCase().equals(title.toLowerCase())) {
                System.out.printf("""
                    %n
                    === book found ===
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s,
                    Author: %s
                    %n
                """,book.get(0),book.get(1),book.get(2),book.get(3),book.get(4));
                break;
            }
        }
    }

    @Override
    public void getAllBook() throws IndexOutOfBoundsException, Exception {
        transferDBDataToMemory(Constant.BOOKS_PATH);
        for (int i=0; i<memoryBooks.size(); i++) {
            System.out.printf("""
                    ============================
                    Id: %s,
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s,
                    Author: %s
                    %n
                """,i+1,memoryBooks.get(i).getCode(),memoryBooks.get(i).getTitle(),memoryBooks.get(i).getType(),memoryBooks.get(i).getPublicationYear(),memoryBooks.get(i).getAuthor());
        }
        clearMemory();
    }

    @Override
    public void deleteBook(Integer id) throws IndexOutOfBoundsException, FileNotFoundException, Exception {
        if (memoryBooks.isEmpty()) {
            transferDBDataToMemory(Constant.BOOKS_PATH);
            if (memoryBooks.isEmpty()) {
                System.out.println("Please Insert Your Books First.");
                return;
            }
        }

        BookModel book = memoryBooks.get(id-1);
        if (memoryBooks.contains(book)) {
            System.out.printf("""
                        %n
                        == Deleting Book..
                        Id: %s,
                        Code: %s,
                        Title: %s,
                        Type: %s,
                        Publication Year: %s,
                        Author: %s
                        %n
                    """,id,book.getCode(),book.getTitle(),book.getType(),book.getPublicationYear(),book.getAuthor());
            memoryBooks.remove(book);
            System.out.println("Book removed from memory.");
            appendMemoryToDatabase(false);
            System.out.println("Database updated.");
        } else {
            System.out.println("Cannot find book in the inventory.");
        }
    }

    @Override
    public void editBook(Integer id, BookModel book) throws FileNotFoundException, IndexOutOfBoundsException, Exception {
        if (memoryBooks.isEmpty()) {
            transferDBDataToMemory(Constant.BOOKS_PATH);
            if (memoryBooks.isEmpty()) {
                System.out.println("Please Insert Your Books First.");
                return;
            }
        }
        System.out.printf("""
                    %n
                    == Editing Book to..
                    Id: %s,
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s,
                    Author: %s
                    %n
                """,id,book.getCode(),book.getTitle(),book.getType(),book.getPublicationYear(),book.getAuthor());
        deleteBook(id);
        memoryBooks.clear();
        memoryBooks.add(0, book);
        System.out.printf("""
                    %n
                    == Book Editted to :
                    Id: %s,
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s,
                    Author: %s
                    %n
                """,id,memoryBooks.get(0).getCode(),memoryBooks.get(0).getTitle(),memoryBooks.get(0).getType(),memoryBooks.get(0).getPublicationYear(),memoryBooks.get(0).getAuthor());

        System.out.println("Book successfully edited in the memory.");
        appendMemoryToDatabase(true);
        System.out.println("Database updated.");
    }
    
}

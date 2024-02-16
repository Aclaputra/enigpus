package com.enigpus;

import java.util.ArrayList;
import java.util.List;

import com.enigpus.model.BookModel;
import com.enigpus.util.Helper;

public class InventoryServiceImpl implements InventoryService {
    private List<BookModel> tempBooks = new ArrayList<>();

    @Override
    public void addBook(BookModel book) {
        tempBooks.add(book);
    }

    public void listBookToCSV() {
        if (tempBooks.size() > 0) {
            Helper.convertToCSV(tempBooks, "src/main/java/com/enigpus/files/books.csv");
        } else {
            System.out.println("cannot add book. there's no book in the list");
        }
    }

    @Override
    public void searchBookByTitle(String title) {
        List<List<String>> books = Helper.convertFromCSV("src/main/java/com/enigpus/files/books.csv");
        books.remove(0);
        for (List<String> book : books) {
            if (book.get(1).toLowerCase().equals(title.toLowerCase())) {
                System.out.printf("""
                    === book found ===
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s
                    \n""",book.get(0),book.get(1),book.get(2),book.get(3));
                break;
            }
        }
    }

    @Override
    public BookModel searchBookById(String id) {  
        List<List<String>> books = Helper.convertFromCSV("src/main/java/com/enigpus/files/books.csv");
        books.remove(0);

        for (List<String> book : books) {
            if (book.get(0).toLowerCase().equals(id.toLowerCase())) {
                System.out.printf("""
                    === book found ===
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s
                    \n""",book.get(0),book.get(1),book.get(2),book.get(3));

                BookModel foundBook = new BookModel(book.get(0),book.get(1),book.get(2),book.get(3));
                return foundBook;
            }
        }

        return new BookModel("", "", "");
    }

    @Override
    public void deleteBook(String id) {
        List<List<String>> books = Helper.convertFromCSV("src/main/java/com/enigpus/files/books.csv");
        books.remove(0);

        for (List<String> book : books) {
            if (book.get(0).toLowerCase().equals(id.toLowerCase())) {
                System.out.println("data deleted");
            }
        }
    }

    @Override
    public void listBooks() {
        List<List<String>> books = Helper.convertFromCSV("src/main/java/com/enigpus/files/books.csv");
        books.remove(0);
        for (List<String> book : books) {
            System.out.printf("""
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s
                    \n""",book.get(0),book.get(1),book.get(2),book.get(3));
        }
    }

    public Integer searchBookIdByCode(String code) {
        List<List<String>> books = Helper.convertFromCSV("src/main/java/com/enigpus/files/books.csv");
        books.remove(0);

        for (List<String> book : books) {
            String type, title, publication;
            title = book.get(1);
            type = book.get(2);
            publication = book.get(3);
            BookModel bookToAppend = new BookModel(code, title, type, publication);
            tempBooks.add(0, bookToAppend);

            if (book.get(0).toLowerCase().equals(code.toLowerCase())) {
                System.out.printf("""
                    === book found ===
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s
                    \n""",book.get(0),book.get(1),book.get(2),book.get(3));
                break;
            }
        }

        return 0;
    }

    @Override
    public void EditBook(String id, BookModel bookModel) {
        List<List<String>> books = Helper.convertFromCSV("src/main/java/com/enigpus/files/books.csv");
        books.remove(0);

        for (List<String> book : books) {
            if (book.get(0).toLowerCase().equals(id.toLowerCase())) {
                System.out.println("data edited");
            }
        }
    }
    
}

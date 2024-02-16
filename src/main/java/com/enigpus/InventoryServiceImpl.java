package com.enigpus;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.enigpus.model.BookModel;
import com.enigpus.util.Helper;

public class InventoryServiceImpl implements InventoryService {
    private static List<BookModel> memoryBooks = new ArrayList<>();

    @Override
    public void addBook(BookModel book) {
        memoryBooks.add(book);
    }

    public static void clearMemory() {
        memoryBooks.clear();
    }
    
    public void appendMemoryToDatabase() {
        Helper.appendToCSV(memoryBooks, Constant.BOOKS_PATH);
    }

    public void memoryToDatabase() throws FileNotFoundException {
        if (!(memoryBooks.isEmpty())) {
            appendMemoryToDatabase();
        } else {
            System.out.println("cannot add book. there's no book in the memory or database");
        }
    }

    @Override
    public void searchBookByTitle(String title) {
        List<List<String>> books = Helper.convertFromCSV(Constant.BOOKS_PATH);
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
        List<List<String>> books = Helper.convertFromCSV(Constant.BOOKS_PATH);
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
    public void deleteBook(Integer id) throws IndexOutOfBoundsException, FileNotFoundException {
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
                        == Deleting Book..
                        Id: %s,
                        Code: %s,
                        Title: %s,
                        Type: %s,
                        Publication Year: %s
                        \n""",id,book.getCode(),book.getTitle(),book.getType(),memoryBooks.get(id-1).getPublicationYear());
            memoryBooks.remove(book);
            System.out.println("Book removed from memory.");
            memoryToDatabase();
            System.out.println("Database updated.");
        } else {
            System.out.println("Cannot find book in the inventory.");
        }
    }

    @Override
    public void listBooks() throws IndexOutOfBoundsException {
        List<List<String>> books = Helper.convertFromCSV(Constant.BOOKS_PATH);
        books.remove(0);
        for (int i=0; i<books.size(); i++) {
            System.out.printf("""
                    Id: %s,
                    Code: %s,
                    Title: %s,
                    Type: %s,
                    Publication Year: %s
                    \n""",i+1,books.get(i).get(0),books.get(i).get(1),books.get(i).get(2),books.get(i).get(3));
        }
    }

    @Override
    public void EditBook(String id, BookModel bookModel) {
        List<List<String>> books = Helper.convertFromCSV(Constant.BOOKS_PATH);
        books.remove(0);

        for (List<String> book : books) {
            if (book.get(0).toLowerCase().equals(id.toLowerCase())) {
                System.out.println("data edited");
            }
        }
    }

    /**
     * Get Data From CSV Database Resource to Memory for Indexing
     * @param filepath
     */
    public void transferDBDataToMemory(String filepath) {
        List<List<String>> books = Helper.convertFromCSV(filepath);
        books.remove(0);
        memoryBooks.clear();
        for (List<String> book : books) {
            String code = book.get(0);
            String title = book.get(1);
            String type = book.get(2);
            String publicationYear = book.get(3);

            BookModel bookModel = new BookModel(code, title, type, publicationYear);
            memoryBooks.add(bookModel);
        }
    }
    
}

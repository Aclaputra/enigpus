package com.enigpus;

import java.util.Scanner;

import com.enigpus.model.BookModel;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryServiceImpl inventoryService = new InventoryServiceImpl();


        boolean exit = false;
        while (!exit) {
            System.out.println("""
            --------------------------------------
            Main Menu
            --------------------------------------
            1. Add Book
            2. Edit Book
            3. Remove Book
            4. List Book
            5. Search Book By Title
            6. Exit
            Insert menu number (1-6):    
            """);
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("== Add Book ==");
                    String title, type, publicationYear;

                    System.out.println("Insert type");
                    type = sc.nextLine();
                    System.out.println("Insert title");
                    title = sc.nextLine();
                    System.out.println("publication year");
                    publicationYear = sc.nextLine();

                    BookModel book = new BookModel(title, type, publicationYear);
                    inventoryService.addBook(book);
                    break;
                case "2":
                    System.out.println("== Edit Book ==");
                    System.out.println("Insert Book Code: ");
                    String id = sc.nextLine();
                    String title2, type2, publicationYear2;
                    BookModel gotBook = inventoryService.searchBookById(inventoryService.searchBookIdByCode(id));

                    System.out.println("Insert title: ");
                    title2 = sc.nextLine().trim();
                    if (title2.equals("") || title2.equals(null))
                        title2 = gotBook.getTitle();
                    System.out.println("Insert harga");
                    publicationYear2 = sc.nextLine().trim();
                    if (publicationYear2.equals("") || publicationYear2.equals(null)) 
                        publicationYear2 = gotBook.getPublicationYear();

                    System.out.println("Insert type");
                    type2 = sc.nextLine().trim();
                    if (type2.equals("") || type2.equals(null)) {
                        type2 = gotBook.getType();
                    }

                    gotBook.setTitle(title2);
                    gotBook.setPublicationYear(publicationYear2);
                    gotBook.setType(type2);

                    inventoryService.EditBook(id, gotBook);

                    break;
                case "3":
                    System.out.println("== Remove Book ==");
                    System.out.println("Insert id product");
                    String hapusId = sc.nextLine();
                    inventoryService.deleteBook(inventoryService.searchBookIdByCode(hapusId));
                    break;
                case "4":
                    System.out.println("== List Book ==");
                    inventoryService.getAllBook();
                    break;
                case "5":
                    System.out.println("== Search Book By Title ==");
                    System.out.println("Insert value: ");
                    String titleToFound = sc.nextLine();

                    inventoryService.searchBookByTitle(titleToFound);
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("option cannot be found, please input existing option.");
            }

        }

    }
}
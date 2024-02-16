package com.enigpus;

import java.io.FileNotFoundException;
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
                    boolean exitAdd = false;
                    while(!exitAdd) {
                        String title, type, publicationYear;
                    
                        System.out.println("Insert type");
                        type = sc.nextLine();
                        System.out.println("Insert title");
                        title = sc.nextLine();
                        System.out.println("publication year");
                        publicationYear = sc.nextLine();

                        BookModel book = new BookModel(title, type, publicationYear);
                        inventoryService.addBook(book);

                        System.out.println("""
                            == Add another Book ? ==
                            press Y / yes to continue, press N / no back to menu.
                                """);
                        
                        String isAddBook = sc.nextLine();
                        String res = isAddBook.toLowerCase();
                        if (res.equals("y") || res.equals("yes")) {
                            continue;
                        } else if (res.equals("n") || res.equals("no")) {
                            exitAdd = true;
                        } else {
                            System.out.println("command not found. back to menu.");
                            exitAdd = true;
                        }
                    }

                    try {
                        inventoryService.memoryToDatabase();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error Message: " + e.getMessage());
                    }
                    InventoryServiceImpl.clearMemory();
                    break;
                case "2":
                    System.out.println("== Edit Book ==");
                    System.out.println("Insert Book Code: ");
                    String id = sc.nextLine();
                    String title2, type2, publicationYear2;
                    BookModel gotBook = inventoryService.searchBookById(id);

                    System.out.println("Insert title: ");
                    title2 = sc.nextLine().trim();
                    if (title2.equals("") || title2.equals(null))
                        title2 = gotBook.getTitle();
                    System.out.println("Insert publication year");
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
                    Integer deleteid = sc.nextInt();
                    try {
                        inventoryService.deleteBook(deleteid);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.printf("Error Message: " + e.getMessage());
                    } catch(FileNotFoundException e) {
                        System.out.printf("Error Message: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("== List Book ==");
                    try {
                        inventoryService.listBooks();
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("inventory on memory or database is empty.");
                    }
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
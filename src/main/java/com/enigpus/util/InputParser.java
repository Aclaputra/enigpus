package com.enigpus.util;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.enigpus.model.BookModel;
import com.enigpus.service.impl.InventoryServiceImpl;

interface InputParserInterface {
    public static Scanner sc = new Scanner(System.in);
    public static InventoryServiceImpl inventoryService = new InventoryServiceImpl();
}

public class InputParser implements InputParserInterface {
    private static void drawWelcomePopUp() {
        System.out.println("""
            Welcome to
            ███████╗███╗░░██╗██╗░██████╗░██████╗░██╗░░░██╗░██████╗
            ██╔════╝████╗░██║██║██╔════╝░██╔══██╗██║░░░██║██╔════╝
            █████╗░░██╔██╗██║██║██║░░██╗░██████╔╝██║░░░██║╚█████╗░
            ██╔══╝░░██║╚████║██║██║░░╚██╗██╔═══╝░██║░░░██║░╚═══██╗
            ███████╗██║░╚███║██║╚██████╔╝██║░░░░░╚██████╔╝██████╔╝
            ╚══════╝╚═╝░░╚══╝╚═╝░╚═════╝░╚═╝░░░░░░╚═════╝░╚═════╝░
            Made by Muhammad Acla https://github.com/Aclaputra 🎩
                """);
    }

    public static void closeScanner() {
        sc.close();
    }

    public static void beginScanning() {
        drawWelcomePopUp();
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
                    
                    try {
                        addBookCLI();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error Message: " + e.getMessage());
                    } catch(Exception e) {
                        System.out.println("Error Message: " + e.getMessage());
                    }

                    try {
                        inventoryService.memoryToDatabase();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error Message: " + e.getMessage());
                    } catch(Exception e) {
                        System.out.println("Error Message: " + e.getMessage());
                    }

                    InventoryServiceImpl.clearMemory();
                    System.out.println("Memory cleared");
                    break;
                case "2":
                    System.out.println("== Edit Book ==");
                    editBookCLI();

                    break;
                case "3":
                    System.out.println("== Remove Book ==");
                    System.out.println("Insert id product");
                    Integer deleteid = sc.nextInt();

                    try {
                        inventoryService.deleteBook(deleteid);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("Error Message: " + e.getMessage());
                    } catch(FileNotFoundException e) {
                        System.out.println("Error Message: " + e.getMessage());
                    } catch(Exception e) {
                        System.out.println("Error Message: " + e.getMessage());
                    }

                    break;
                case "4":
                    System.out.println("== List Book ==");
                    try {
                        drawWelcomePopUp();
                        inventoryService.getAllBook();
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("Database or Memory Inventory still empty. Please insert a Book");
                    } catch(Exception e) {
                        System.out.println("Error Message: " + e.getMessage());
                    } 

                    break;
                case "5":
                    System.out.println("== Search Book By Title ==");
                    System.out.println("Insert value: ");
                    String titleToFound = sc.nextLine();
                    if (!Validation.isValidLength(titleToFound, 3, 30)) {
                        System.out.println("Please Input a Valid String between 3 to 30");
                        break;
                    }
                    try {
                        inventoryService.searchBookByTitle(titleToFound);
                    } catch(IndexOutOfBoundsException e) {
                        System.out.println("Error Message: " + e.getMessage());
                    } catch(Exception e) {
                        System.out.println("Error Message: " + e.getMessage());
                    }
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("option cannot be found, please input existing option.");
            }
        }
    }
    
    public static String inputPeriodeHelper() throws Exception {
        boolean correctPeriode = false;
        String publicationPeriode = "";
        while(!correctPeriode) {
            System.out.println("""
                        === list months ===
                        1. January
                        2. February
                        3. March
                        4. April
                        5. Mei
                        6. June
                        7. July
                        8. August
                        9. September
                        10. October
                        11. November
                        12. December
                        (select by inputing based on id above) :
                    """);
            String inputPeriode = sc.nextLine();
            int convertedPeriode = Integer.parseInt(inputPeriode);
            switch (convertedPeriode) {
                case 1:
                    publicationPeriode = "january";
                    correctPeriode = true;
                    break;
                case 2:
                    publicationPeriode = "february";
                    correctPeriode = true;
                    break;
                case 3:
                    publicationPeriode = "march";
                    correctPeriode = true;
                    break;
                case 4:
                    publicationPeriode = "april";
                    correctPeriode = true;
                    break;
                case 5:
                    publicationPeriode = "may";
                    correctPeriode = true;
                    break;
                case 6:
                    publicationPeriode = "juny";
                    correctPeriode = true;
                    break;
                case 7:
                    publicationPeriode = "july";
                    correctPeriode = true;
                    break;
                case 8:
                    publicationPeriode = "august";
                    correctPeriode = true;
                    break;
                case 9:
                    publicationPeriode = "september";
                    correctPeriode = true;
                    break;
                case 10:
                    publicationPeriode = "oktober";
                    correctPeriode = true;
                    break;
                case 11:
                    publicationPeriode = "november";
                    correctPeriode = true;
                    break;
                case 12:
                    publicationPeriode = "december";
                    correctPeriode = true;
                    break;
            
                default:
                    System.out.println("Cannot found id in the list. please select the correct id.");
                    break;
            }
        }
        return publicationPeriode;
    }

    public static void addBookCLI() {
        boolean exitAdd = false;
        while(!exitAdd) {
            String title, type, publicationYear;
        
            System.out.println("Insert type (majalah | novel)");
            type = sc.nextLine();
            if (!(type.equals("majalah") || type.equals("novel"))) {
                System.out.println("Please input (majalah or novel)");
                break;
            }
            if (!Validation.isValidLength(type, 3, 30)) {
                System.out.println("Please Input a Valid String between 3 to 30");
                break;
            }
                
            System.out.println("Insert title");
            title = sc.nextLine();
            if (!Validation.isValidLength(title, 3, 30)) {
                System.out.println("Please Input a Valid String between 3 to 30");
                break;
            }

            System.out.println("publication year");
            publicationYear = sc.nextLine();
            if (!Validation.isValidLength(publicationYear, 4, 4)) {
                System.out.println("Please Input a Valid 4 String for Year. Ex: 2024");
                break;
            }

            BookModel book = new BookModel(title, type, publicationYear);
            if (type.equals("novel")) {
                System.out.println("You are inserting Novel, please Insert Author name below");
                String author = sc.nextLine();
                book.setAuthor(author);
            } else if (type.equals("majalah")) {
                System.out.println("You are inserting Majalah, please Insert Publicaton Periode Ex: 3");
                try {
                    book.setPublicationPeriode(inputPeriodeHelper());
                } catch(Exception e) {
                    System.out.println("Please input the correct input. Error: " + e.getMessage());
                }
            }
            try {
                inventoryService.addBook(book);
            } catch(Exception e) {
                System.out.println("Error message: " + e.getMessage());
            }

            System.out.println("""
                == Add another Book ? ==
                press Y / yes or press N / no for back to menu.
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
    }

    public static void editBookCLI() {
        System.out.println("Insert Book Id: ");
        int id = Integer.parseInt(sc.nextLine());
        String title2, type2, publicationYear2, title2Trim, type2Trim, publicationYear2Trim;
        
        try {
            BookModel gotBook = inventoryService.searchBookById(id);

            System.out.println("Insert title: ");
            title2 = sc.nextLine();
            if (!Validation.isValidLength(title2, 3, 30)) {
                System.out.println("Please Input a Valid String between 3 to 30");
                return;
            }

            System.out.println("Insert publication year");
            publicationYear2 = sc.nextLine();
            if (!Validation.isValidLength(publicationYear2, 4, 4)) {
                System.out.println("Please Input a Valid 4 String for Year. Ex: 2024");
                return;
            }

            System.out.println("Insert type");
            type2 = sc.nextLine();
            if (!Validation.isValidLength(type2, 3, 30)) {
                System.out.println("Please Input a Valid String between 3 to 30");
                return;
            }

            title2Trim = title2.trim();
            if (title2Trim.equals("") || title2Trim.equals(null))
                title2 = gotBook.getTitle();
            publicationYear2Trim = publicationYear2.trim();
            if (publicationYear2Trim.equals("") || publicationYear2Trim.equals(null))
                publicationYear2 = gotBook.getPublicationYear();
            type2Trim = type2.trim();
            if (type2Trim.equals("") || type2Trim.equals(null)) 
                type2 = gotBook.getType();

            gotBook.setTitle(title2);
            gotBook.setPublicationYear(publicationYear2);
            gotBook.setType(type2);

            try {
                inventoryService.editBook(id, gotBook);
            } catch (FileNotFoundException e) {
                System.out.println("Error message: " + e.getMessage());
            } catch(Exception e) {
                System.out.println("Error Message: " + e.getMessage());
            }
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Internal Server Error: " + e.getMessage());
        } catch(Exception e) {
            System.out.println("Error Message: " + e.getMessage());
        } 
    }
}
package com.enigpus.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.enigpus.model.BookModel;

/**
 * rule : format  YYYY-A-xxxxx untuk novel, dan YYYY-B-xxxxx untuk majalah
 */
public class Helper {
    public String generateCodeBook(String year, String type) {
        String bookTypeCode = "";
        UUID code = UUID.randomUUID();

        if (type.equals("novel")) 
            bookTypeCode = "A";
        else if (type.equals("majalah"))
            bookTypeCode = "B";

        return String.format("%s-%s-%s", year.toUpperCase(), bookTypeCode, code);
    }

    public static void convertToCSV(List<BookModel> books, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("code,title,type,publication_year\n");

            for (BookModel object : books) {
                BookModel book = (BookModel) object;
                writer.write(book.getCode() + "," + book.getTitle() + "," + book.getType() + "," + book.getPublicationYear() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error Message: " + e.getMessage());
        }
    }

    public static List<List<String>> convertFromCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch(IOException e) {
            System.out.println("Error Message: " + e.getMessage());
        }

        return records;
    }

}

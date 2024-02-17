package com.enigpus.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.enigpus.Constant;
import com.enigpus.model.BookModel;

interface HelperInterface {
    String generateCodeBook(String year, String type);
}
/**
 * rule : format  YYYY-A-xxxxx untuk novel, dan YYYY-B-xxxxx untuk majalah
 */
public class Helper implements HelperInterface{
    @Override
    public String generateCodeBook(String year, String type) {
        String bookTypeCode = "";
        UUID code = UUID.randomUUID();

        if (type.toLowerCase().equals("novel")) 
            bookTypeCode = "A";
        else if (type.toLowerCase().equals("majalah"))
            bookTypeCode = "B";

        return String.format("%s-%s-%s", year.toUpperCase(), bookTypeCode, code);
    }

    public static void writeCSVData(FileWriter writer, List<BookModel> books, String filePath) throws IOException {
        for (BookModel object : books) {
            BookModel book = (BookModel) object;
            writer.append(
                book.getCode() + "," + 
                book.getTitle() + "," + 
                book.getType() + "," + 
                book.getPublicationYear() + "," + 
                book.getAuthor() + "," +
                book.getPublicationPeriode() +
                "\n");
        } 
    }

    public static void appendToCSV(List<BookModel> books, String filePath, boolean isAppend) {
        List<List<String>> databaseBooks = Helper.convertFromCSV(Constant.BOOKS_PATH);
        int dataExist = 1;
        try (FileWriter writer = new FileWriter(filePath, isAppend)) {
            if (databaseBooks.size() < dataExist || !isAppend)
                writer.append("code,title,type,publication_year,author,publication_periode\n");
            writeCSVData(writer, books, filePath);
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

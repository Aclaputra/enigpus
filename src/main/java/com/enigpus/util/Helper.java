package com.enigpus.util;

import java.util.UUID;

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
}

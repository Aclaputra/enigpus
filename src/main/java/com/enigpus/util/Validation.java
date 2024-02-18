package com.enigpus.util;

import java.time.Year;

public class Validation {
    public static boolean isValidLength(String str, int min, int max) {
        if (str.length() < min || str.length() > max)
            return false;
        return true;
    } 
    
    public static Boolean isValidYear(String str) {
        Integer stringToInt = Integer.parseInt(str);
        if (stringToInt >= 1000 && stringToInt <= currentYear()) 
            return true;
        return false;
    }

    public static Integer currentYear() {
        return Integer.parseInt(Year.now().toString());
    }
}

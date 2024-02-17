package com.enigpus.util;

public class Validation {
    public static boolean isValidLength(String str, int min, int max) {
        if (str.length() < min || str.length() > max)
            return false;
        return true;
    } 
}

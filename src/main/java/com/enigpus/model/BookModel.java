package com.enigpus.model;


import com.enigpus.Book;
import com.enigpus.util.Helper;

/**
 * rule : format  YYYY-A-xxxxx untuk novel, dan YYYY-B-xxxxx untuk majalah
 */
public class BookModel extends Book{
    private String code;
    private String title;
    private String type;
    private String publicationYear;
        
    private Helper helper = new Helper();
    
    public BookModel(String title, String type, String publicationYear) {
        this.code = helper.generateCodeBook(publicationYear, type);
        this.title = title;
        this.type = type;
        this.publicationYear = publicationYear;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    
}

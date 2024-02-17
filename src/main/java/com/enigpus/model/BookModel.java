package com.enigpus.model;


import com.enigpus.util.Helper;

/**
 * rule : format  YYYY-A-xxxxx untuk novel, dan YYYY-B-xxxxx untuk majalah
 */
public class BookModel extends Book {
    private String code;
    private String title;
    private String type;
    private String publicationYear;
        
    private Helper helper = new Helper();
    
    public BookModel(String code, String title, String type, String publicationYear) {
        this.code = code;
        this.title = title;
        this.type = type;
        this.publicationYear = publicationYear;
    }

    public BookModel(String title, String type, String publicationYear) {
        this("",title,type,publicationYear);
        this.code = helper.generateCodeBook(publicationYear, type);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getPublicationYear() {
        return publicationYear;
    }

    @Override
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    
}

package com.enigpus.model;

public abstract class Book {
    public abstract String getTitle();
    public abstract String getCode();
    public abstract String getType();
    public abstract String getPublicationYear();
    public abstract void setTitle(String title);
    public abstract void setCode(String code);
    public abstract void setType(String type);
    public abstract void setPublicationYear(String publicationYear);
}

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
    public abstract void setAuthor(String author);
    public abstract String getAuthor();
    public abstract String getPublicationPeriode();
    public abstract void setPublicationPeriode(String publicationPeriode);
}

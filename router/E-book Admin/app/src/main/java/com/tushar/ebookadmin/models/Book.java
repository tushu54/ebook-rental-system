package com.pradum.ebookadmin.models;

import java.io.Serializable;

public class Book implements Serializable {
    private String _id;
    private String title;
    private String author;
    private String category_id;
    private String description;
    private String language;
    private String date_of_publication;
    private String price;
    private String image_url;
    private String pdf_url;

    public Book(String _id, String title, String author, String category_id, String description,
                String language, String date_of_publication, String price, String image_url, String pdf_url) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.category_id = category_id;
        this.description = description;
        this.language = language;
        this.date_of_publication = date_of_publication;
        this.price = price;
        this.image_url = image_url;
        this.pdf_url = pdf_url;
    }
    public Book( String title, String author, String category_id, String description,
                String language, String date_of_publication, String price, String image_url, String pdf_url) {
        this.title = title;
        this.author = author;
        this.category_id = category_id;
        this.description = description;
        this.language = language;
        this.date_of_publication = date_of_publication;
        this.price = price;
        this.image_url = image_url;
        this.pdf_url = pdf_url;
    }

    // Getters and Setters
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryId() {
        return category_id;
    }

    public void setCategoryId(String categoryId) {
        this.category_id = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDateOfPublication() {
        return date_of_publication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.date_of_publication = dateOfPublication;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public String getPdfUrl() {
        return pdf_url;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdf_url = pdfUrl;
    }

}

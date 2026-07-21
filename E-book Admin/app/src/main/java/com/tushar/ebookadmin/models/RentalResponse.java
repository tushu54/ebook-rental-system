package com.pradum.ebookadmin.models;

public class RentalResponse {
    public Book ebook;
    public Admin user;
    public Transaction payment;
    public String expiry_date;
    public String rental_date;

    public RentalResponse(Book ebook, Transaction payment, String expiry_date, String rental_date,Admin user) {
        this.ebook = ebook;
        this.payment = payment;
        this.expiry_date = expiry_date;
        this.rental_date = rental_date;
        this.user = user;
    }

    public Book getEbook() {
        return ebook;
    }

    public void setEbook(Book ebook) {
        this.ebook = ebook;
    }

    public Transaction getPayment() {
        return payment;
    }

    public void setPayment(Transaction payment) {
        this.payment = payment;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getRental_date() {
        return rental_date;
    }

    public void setRental_date(String rental_date) {
        this.rental_date = rental_date;
    }

    public Admin getUser() {
        return user;
    }

    public void setUser(Admin user) {
        this.user = user;
    }
}

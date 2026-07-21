package com.pradum.ebookadmin.models;

public class Transaction {
    private String _id;
    private String transaction_id;
    private String amount;
    private String payment_date;
    private boolean verifed;

    public Transaction(String transaction_id, String amount, String payment_date, boolean verifed) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.verifed = verifed;
    }
    public Transaction(String transaction_id, String amount, String payment_date) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.payment_date = payment_date;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public String getTransactionId() {
        return transaction_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return payment_date;
    }

    public boolean isVerified() {
        return verifed;
    }

    public void setVerifed(boolean verifed) {
        this.verifed = verifed;
    }
}

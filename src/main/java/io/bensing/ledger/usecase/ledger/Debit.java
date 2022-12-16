package io.bensing.ledger.usecase.ledger;

public class Debit {

    private String description;
    private long transactionId;
    private long accountNumber;
    private double amount;
    private long dateAndTime;

    public Debit(String description, long transactionId, long accountNumber, double amount, long dateAndTime) {
        this.description = description;
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.dateAndTime = dateAndTime;
    }

    public String Description() {
        return this.description;
    }

    public long TransactionId() {
        return this.transactionId;
    }

    public long AccountNumber() {
        return this.accountNumber;
    }

    public double Amount() {
        return this.amount;
    }

    public long DateAndTime() {
        return this.dateAndTime;
    }
}

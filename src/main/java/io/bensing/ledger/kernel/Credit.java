package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;

public class Credit {

    private String description;
    private Id transactionId;
    private long accountNumber;
    private double amount;
    private long dateAndTime;

    public Credit(String description, Id transactionId, long accountNumber, double amount, long dateAndTime) {
        this.description = description;
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.dateAndTime = dateAndTime;
    }

    public String Description() {
        return this.description;
    }

    public Id TransactionId() {
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

package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;

public class Credit {

    private String description;
    private Id transactionId;
    private Account account;
    private double amount;
    private long dateAndTime;

    public Credit(String description, Id transactionId, Account account, double amount, long dateAndTime) {
        this.description = description;
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
        this.dateAndTime = dateAndTime;
    }

    public String Description() {
        return this.description;
    }

    public Id TransactionId() {
        return this.transactionId;
    }

    public Account Account() {
        return this.account;
    }

    public double Amount() {
        return this.amount;
    }

    public long DateAndTime() {
        return this.dateAndTime;
    }
}

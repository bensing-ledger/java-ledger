package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;

public class Credit implements Comparable<Credit> {

    private final String description;
    private final Id transactionId;
    private final Account account;
    private final double amount;
    private final long dateAndTime;

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

    @Override
    public boolean Equals(Credit compareCredit) {
        var desc = this.Description().equals(compareCredit.Description());
        var transId = this.TransactionId().Equals(compareCredit.TransactionId());
        var acct = this.Account().Equals(compareCredit.Account());
        var amt = this.Amount() == compareCredit.Amount();
        var dateTime = this.DateAndTime() == compareCredit.DateAndTime();
        return desc && transId && acct && amt && dateTime;
    }
}

package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;

public class Debit implements Comparable<Debit> {

    private String description;
    private Id transactionId;
    private Account account;
    private double amount;
    private long dateAndTime;

    // TODO - add a validation object to this to validate the incoming arguments are valid.

    public Debit(String description, Id transactionId, Account account, double amount, long dateAndTime) {
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

    public boolean Equals(Debit compareDebit) {
        var desc = this.Description().equals(compareDebit.Description());
        var transId = this.TransactionId().Equals(compareDebit.TransactionId());
        var acct = this.Account().Equals(compareDebit.Account());
        var amt = this.Amount() == compareDebit.Amount();
        var dateTime = this.DateAndTime() == compareDebit.DateAndTime();
        return desc && transId && acct && amt && dateTime;
    }
}

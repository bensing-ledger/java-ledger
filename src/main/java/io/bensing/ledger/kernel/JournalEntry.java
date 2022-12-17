package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.ValueObject;

public class JournalEntry {

    private final String description;
    private final Credit credit;
    private final Debit debit;
    private Id transactionId;

    // TODO - add a validation object to this to validate the incoming arguments are valid.
    public JournalEntry(String description, Id transId, long dateAndTime, double amount, Account creditAcct, Account debitAcct) {
        this.description = description;
        this.transactionId = transId;
        this.credit = new Credit(this.description, this.transactionId, creditAcct, amount, dateAndTime);
        this.debit = new Debit(this.description, this.transactionId, debitAcct, amount, dateAndTime);
    }

    // TODO - add a validation to the Credt & Debit
    public boolean IsValid() {
        return true;
    }

    public String Description() {
        return this.description;
    }

    public Id TransactionId() {
        return this.transactionId;
    }

    public Credit Credit() {
        return this.credit;
    }
    public Debit Debit() {
        return this.debit;
    }
}

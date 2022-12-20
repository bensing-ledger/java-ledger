package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;

public class Debit implements Comparable<Debit> {

    private final JournalEntryDescription description;
    private final JournalEntryId journalEntryId;
    private final Account account;
    private final double amount;
    private final long dateAndTime;

    // TODO - add a validation object to this to validate the incoming arguments are valid.

    public Debit(JournalEntryDescription description, JournalEntryId journalEntryId, Account account, double amount, long dateAndTime) {
        this.description = description;
        this.journalEntryId = journalEntryId;
        this.account = account;
        this.amount = amount;
        this.dateAndTime = dateAndTime;
    }

    public JournalEntryDescription Description() {
        return this.description;
    }

    public JournalEntryId EntryId() {
        return this.journalEntryId;
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
        var desc = this.Description().Equals(compareDebit.Description());
        var transId = this.EntryId().Equals(compareDebit.EntryId());
        var acct = this.Account().Equals(compareDebit.Account());
        var amt = this.Amount() == compareDebit.Amount();
        var dateTime = this.DateAndTime() == compareDebit.DateAndTime();
        return desc && transId && acct && amt && dateTime;
    }
}

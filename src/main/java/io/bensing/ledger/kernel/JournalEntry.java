package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Validatable;

import java.util.ArrayList;

public class JournalEntry implements Validatable {

    private final JournalEntryDescription description;
    private final JournalEntryId journalEntryId;
    private final JournalEntryDate journalEntryDate;

    private final Account creditAccount;
    private final Account debitAccount;
    private final Amount amount;

    private Validation validation;

    public JournalEntry(String description, Id entryId, long dateAndTime, double amount, Account creditAcct, Account debitAcct) {
        this.description = new JournalEntryDescription(description);
        this.journalEntryId = new JournalEntryId(entryId);
        this.amount = new Amount(amount);
        this.journalEntryDate = new JournalEntryDate(dateAndTime);
        this.creditAccount = creditAcct;
        this.debitAccount = debitAcct;
        this.validate();
    }

    public JournalEntryDescription Description() {
        return this.description;
    }
    public JournalEntryId EntryId() {
        return this.journalEntryId;
    }
    public JournalEntryDate Date() { return this.journalEntryDate; }
    public Credit Credit() {
        if (this.IsInvalid()) { return new Credit(this.creditAccount,new Amount(0)); }
        return new Credit(this.creditAccount, this.amount);
    }
    public Debit Debit() {
        if (this.IsInvalid()) { return new Debit(this.debitAccount,new Amount(0)); }
        return new Debit(this.debitAccount, this.amount);
    }

    public boolean IsValid() {
        return this.validation.IsValid();
    }
    public boolean IsInvalid() {
        return this.validation.IsInvalid();
    }
    public ArrayList<String> ValidationMessages() {
        return this.validation.ValidationMessages();
    }

    private void validate() {
        this.validation = new Validation();
        validateDescription(this.validation);
        validateId(this.validation);
        validateAmount(this.validation);
        validateDate(this.validation);
        validateCreditAccount(this.validation);
        validateDebitAccount(this.validation);
    }
    private void validateDescription(Validation validation) {
        if (this.description.IsValid()){ return; }
        var message = "The journal entry description is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.description.ValidationMessages());
    }
    private void validateId(Validation validation) {
        if (this.journalEntryId.IsValid() ) { return; }
        var message = "The journal entry id is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.journalEntryId.ValidationMessages());
    }
    private void validateAmount(Validation validation) {
        if (this.amount.IsValid()) { return; }
        var message = "The journal entry amount is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.amount.ValidationMessages());

    }
    private void validateDate(Validation validation) {
        if (this.journalEntryDate.IsValid()) { return; }
        var message = "The journal entry date is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.journalEntryDate.ValidationMessages());
    }
    private void validateCreditAccount(Validation validation) {
        if (this.creditAccount.IsValid()) { return; }
        var message = "The journal credit account is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.creditAccount.ValidationMessages());
    }
    private void validateDebitAccount(Validation validation) {
        if (this.debitAccount.IsValid()) { return; }
        var message = "The journal debit account is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.debitAccount.ValidationMessages());
    }
}

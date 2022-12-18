package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.values.Content;

import java.util.ArrayList;

public class Credit implements Comparable<Credit>, Validatable {

    private final Content description;
    private final Id transactionId;
    private final Account account;
    private final double amount;
    private final long dateAndTime;
    private Validation validation;

    public Credit(String description, Id transactionId, Account account, double amount, long dateAndTime) {
        this.description = new Content(description);
        this.transactionId = transactionId;     // TODO - Add Validation - Credit - transactionId
        this.account = account;
        this.amount = amount;                   // TODO - Add Validation - Credit - amount
        this.dateAndTime = dateAndTime;         // TODO - Add Validation - Credit - dateAndTime
        this.validate();
    }

    public String Description() {
        return this.description.Value();
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

    public boolean Equals(Credit compareCredit) {
        var desc = this.Description().equals(compareCredit.Description());
        var transId = this.TransactionId().Equals(compareCredit.TransactionId());
        var acct = this.Account().Equals(compareCredit.Account());
        var amt = this.Amount() == compareCredit.Amount();
        var dateTime = this.DateAndTime() == compareCredit.DateAndTime();
        return desc && transId && acct && amt && dateTime;
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
        validateAccount(this.validation);
        validateDescription(this.validation);
    }

    // TODO - LEFT OFF HERE - Update validations to reflect the new kernel version
    private void validateDescription(Validation validation) {
        if (this.description.Value().length() != 0){ return; }
        validation.AddMessage("Please provide a credit description.");
    }
    private void validateAccount(Validation validation) {
        if (this.account.IsValid()) { return; }
        var message = "The account number is invalid.";
        validation.AddMessage(message);
        validation.IncludeMessagesFrom(this.account.ValidationMessages());
    }
}

package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.ValueObject;
import io.bensing.kernel.interfaces.Validatable;

import java.util.ArrayList;

public class Account implements ValueObject<Long>, Comparable<Account>, Validatable {

    private final Id accountNumber;
    private AccountType accountType;
    private Validation validation;

    public Account(long accountNumber, AccountType accountType) {
        this(accountNumber);
        this.accountType = accountType;
    }

    // TODO - find all usages and update to the new constructor
    public Account(long accountNumber) {
        this.accountNumber = new Id(accountNumber);
        this.validate();
    }

    public Long Value() {
        return this.accountNumber.Value();
    }
    public Id Number() {
        return this.accountNumber;
    }
    public AccountType Type() { return this.accountType; }
    public boolean Equals(Account compareAccount) {
        var sameNumber = this.accountNumber.Equals(compareAccount.Number());
        var sameType = this.accountType.Equals(compareAccount.Type());
        return sameNumber & sameType;
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
        if (this.accountNumber.Value() <= 0) {
            this.validation.AddMessage("The account number must be greater than 0.");
        }
    }

}



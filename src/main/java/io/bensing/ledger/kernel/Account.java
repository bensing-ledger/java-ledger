package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.ValueObject;
import io.bensing.kernel.interfaces.Validatable;

import java.util.ArrayList;

// TODO - LEFT OFF HERE - Update validations to reflect the new kernel version
public class Account implements ValueObject<Long>, Comparable<Account>, Validatable {

    private final Id accountNumber;
    private Validation validation;

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
    public boolean Equals(Account compareAccount) {
        return this.accountNumber.Equals(compareAccount.Number());
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



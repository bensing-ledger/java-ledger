package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.ValueObject;
import io.bensing.kernel.interfaces.Validatable;

public class Account implements ValueObject<Long>, Comparable<Account>, Validatable {

    private final Id accountNumber;
    private Validation validation;

    public Account(long accountNumber) {
        this.validate(accountNumber);
        this.accountNumber = new Id(accountNumber);
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
        return this.validation.isValid();
    }
    public String ValidationMessage() {
        return this.validation.getValidationMessage();
    }

    private void validate(long accountNumber) {
        var validation = new Validation();
        if (accountNumber > 0){
            validation.setAsValid();
        } else {
            validation.setAsInvalid("The account number must be greater than 0.");
        }
        this.validation = validation;
    }

}



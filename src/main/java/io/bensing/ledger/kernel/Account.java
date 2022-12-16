package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.ValueObject;

public class Account implements ValueObject<Long> {
    private Id accountNumber;

    public Account(long accountNumber) {
        this.accountNumber = new Id(accountNumber);
    }

    public Long Value() {
        return this.accountNumber.Value();
    }
}



package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.ValueObject;

public class Account implements ValueObject<Long>, Comparable<Account> {
    private Id accountNumber;

    public Account(long accountNumber) {
        this.accountNumber = new Id(accountNumber);
    }

    public Long Value() {
        return this.accountNumber.Value();
    }
    public Id Number() {
        return this.accountNumber;
    }

    @Override
    public boolean Equals(Account compareAccount) {
        return this.accountNumber.Equals(compareAccount.Number());
    }
}



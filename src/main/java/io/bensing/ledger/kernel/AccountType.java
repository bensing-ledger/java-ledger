package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.ValueObject;

public enum AccountType implements ValueObject<String>, Comparable<AccountType> {
    Asset("asset"),
    Liability("liability"),
    Revenue("revenue"),
    Expense("expense"),
    Equity("equity");

    private final String value;

    private AccountType(String value) {
        this.value = value;
    }

    @Override
    public String Value() {
        return this.value;
    }

    @Override
    public boolean Equals(AccountType accountType) {
        return this.value.equals(accountType.Value());
    }
}

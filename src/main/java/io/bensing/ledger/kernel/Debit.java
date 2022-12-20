package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;

import java.util.Objects;

public class Debit implements Comparable<Debit> {

    private final Account account;
    private final Amount amount;

    public Debit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
    }

    public long Account() {
        return this.account.Value();
    }

    public double Amount() {
        return this.amount.Value();
    }

    public boolean Equals(Debit debit) {
        var sameAccount = Objects.equals(debit.Account(), this.Account());
        var sameAmount = Objects.equals(debit.Amount(), this.Amount());
        return sameAccount && sameAmount;
    }
}

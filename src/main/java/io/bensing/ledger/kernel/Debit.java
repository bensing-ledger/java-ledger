package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;

import java.util.Objects;

public class Debit implements Comparable<Debit> {

    private final Account account;
    private final Amount amount;
    // TODO - Add the logic to determine if the amount returned is signed/unsigned based upon the account type (need new object AccountType[Asset, Liability, ect..]
    public Debit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account Account() {
        return this.account;
    }
    public Amount Amount() {
        return this.amount;
    }
    public boolean Equals(Debit debit) {
        var sameAccount = this.account.Equals(debit.Account());
        var sameAmount = this.amount.Equals(debit.Amount());
        return sameAccount && sameAmount;
    }
}

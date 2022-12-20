package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;

import java.util.Objects;

public class Credit implements Comparable<Credit> {

    private final Account account;
    private final Amount amount;
    // TODO - Add the logic to determine if the amount returned is signed/unsigned based upon the account type (need new object AccountType[Asset, Liability, ect..]
    public Credit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
    }

    public long Account() {
        return this.account.Value();
    }
    public double Amount() {
        return this.amount.Value();
    }
    public boolean Equals(Credit credit) {
        var sameAccount = Objects.equals(credit.Account(), this.Account());
        var sameAmount = Objects.equals(credit.Amount(), this.Amount());
        return sameAccount && sameAmount;
    }
}

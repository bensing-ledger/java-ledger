package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;

public class Credit implements Comparable<Credit> {

    private final Account account;
    private final Amount amount;
    // TODO - Add the logic to determine if the amount returned is signed/unsigned based upon the account type (need new object AccountType[Asset, Liability, ect..]
    public Credit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account Account() {
        return this.account;
    }
    public Amount Amount() {
        return this.amount;
    }
    public boolean Equals(Credit credit) {
        var sameAccount = this.Account().Equals(credit.Account());
        var sameAmount = this.amount.Equals(credit.Amount());
        return sameAccount && sameAmount;
    }
}

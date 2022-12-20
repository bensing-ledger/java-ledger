package io.bensing.ledger.kernel;

import io.bensing.kernel.interfaces.Comparable;

import java.util.Objects;

public class Credit implements Comparable<Credit> {

    private final Account account;
    private final Amount amount;

    // TODO - Update the Credit and Debit to tak in the Account and Amount.
    // TODO - Credit - Add an Account Type (Liability, Asset, ect..) to the account object.  The debit and credit will use this info to sign, or unsign, the amount.
    public Credit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
    }

    public Long Account() {
        return this.account.Value();
    }
    public Double Amount() {
        return this.amount.Value();
    }


    @Override
    public boolean Equals(Credit credit) {
        var sameAccount = Objects.equals(credit.Account(), this.Account());
        var sameAmount = Objects.equals(credit.Amount(), this.Amount());
        return sameAccount && sameAmount;
    }
}

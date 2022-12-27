package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;

import java.util.ArrayList;
import java.util.Objects;

public class Debit implements ValueObject<Double>, Comparable<Debit>, Validatable {

    private final Account account;
    private final Amount amount;

    private double value = 0;

    private Validation validation;

    public Debit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
        this.validate();
        this.determineValue();
    }

    public long AccountNumber() { return this.account.Value(); }
    public Double Value() { return this.value; }
    public boolean Equals(Debit debit) {
        var sameAccount = this.AccountNumber() == debit.AccountNumber();
        var sameAmount = Objects.equals(this.Value(), debit.Value());
        return sameAccount && sameAmount;
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
        this.validateAccount(this.validation);
        this.validateAmount(this.validation);
    }
    private void validateAccount(Validation validation) {
        if (this.account.IsValid()) { return; }
        this.validation.AddMessage("The account is invalid.");
        this.validation.IncludeMessagesFrom(this.account.ValidationMessages());
    }
    private void validateAmount(Validation validation) {
        if (amount.IsValid()) { return; }
        this.validation.AddMessage("The amount is invalid.");
        this.validation.IncludeMessagesFrom(amount.ValidationMessages());
    }
    private void determineValue() {
        if (this.IsInvalid()) { return; }
        if (this.account.Type().Equals(AccountType.Liability) ||
                this.account.Type().Equals(AccountType.Revenue) ||
                this.account.Type().Equals(AccountType.Equity)) {
            this.value = -1 * this.amount.Value();
            return;
        }
        this.value = this.amount.Value();
    }


}

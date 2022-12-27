package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;

import java.util.ArrayList;
import java.util.Objects;

public class Credit implements ValueObject<Double>, Comparable<Credit>, Validatable {

    private final Account account;
    private final Amount amount;
    private double value = 0;
    private Validation validation;

    public Credit(Account account, Amount amount) {
        this.account = account;
        this.amount = amount;
        validate();
        determineValue();
    }

    public long AccountNumber() {
        return this.account.Value();
    }
    public Double Value() {
        return this.value;
    }
    public boolean Equals(Credit credit) {
        var sameAccount = this.AccountNumber() == credit.AccountNumber();
        var sameAmount = Objects.equals(this.Value(), credit.Value());
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
        validateAccount(this.validation);
        validateAmount(this.validation);
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
        if (this.account.Type().Equals(AccountType.Asset) ||
            this.account.Type().Equals(AccountType.Expense)) {
                this.value = -1 * this.amount.Value();
                return;
        }
        this.value = this.amount.Value();
    }

}

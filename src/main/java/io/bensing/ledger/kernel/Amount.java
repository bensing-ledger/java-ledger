package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;

import java.util.ArrayList;

public class Amount implements ValueObject<Double>, Validatable, Comparable<Amount> {

    private final double amount;
    private Validation validation;

    public Amount(double amount) {
        this.amount = amount;
        this.validate();
    }

    private void validate() {
        this.validation = new Validation();
        if (this.amount <= 0) {
            this.validation.AddMessage("The amount must be greater than 0.");
        }
    }


    public Double Value() {
        return this.amount;
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
    public boolean Equals(Amount amount) {
        return this.amount == amount.Value();
    }
}


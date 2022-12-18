package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;
import io.bensing.kernel.values.Content;

import java.util.ArrayList;

public class JournalEntryDescription implements ValueObject<String>, Validatable {

    private final Content description;
    private Validation validation;

    public JournalEntryDescription(String description) {
        this.description = new Content(description);
        this.validate();
    }

    private void validate() {
        this.validation = new Validation();
        if (this.description.Value().length() > 0){ return; }
        this.validation.AddMessage("The journal entry description cannot be empty.");
    }

    @Override
    public boolean IsValid() {
        return this.validation.IsValid();
    }
    @Override
    public boolean IsInvalid() {
        return this.validation.IsInvalid();
    }
    @Override
    public ArrayList<String> ValidationMessages() {
        return this.validation.ValidationMessages();
    }

    @Override
    public String Value() {
        return this.description.Value();
    }
}

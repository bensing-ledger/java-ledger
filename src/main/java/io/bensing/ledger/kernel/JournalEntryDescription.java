package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;
import io.bensing.kernel.values.Content;

import java.util.ArrayList;

public class JournalEntryDescription implements ValueObject<String>, Validatable, Comparable<JournalEntryDescription> {

    private final Content description;
    private Validation validation;

    public JournalEntryDescription(String description) {
        this.description = new Content(description);
        this.validate();
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


    public String Value() {
        return this.description.Value();
    }

    public boolean Equals(JournalEntryDescription description) {
        var compare = new Content(description.Value());
        return this.description.Equals(compare);
    }

    private void validate() {
        this.validation = new Validation();
        if (this.description.Value().length() > 0){ return; }
        this.validation.AddMessage("The journal entry description cannot be empty.");
    }

}

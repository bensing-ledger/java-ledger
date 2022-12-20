package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.identity.Id;
import io.bensing.kernel.interfaces.Comparable;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;

import java.util.ArrayList;

public class JournalEntryId implements ValueObject<Long>, Validatable, Comparable<JournalEntryId> {

    private final Id id;
    private Validation validation;

    public JournalEntryId(long id) {
        this.id = new Id(id);
        this.validate();
    }

    public JournalEntryId(Id id) {
        this.id = id;
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

    public Long Value() {
        return this.id.Value();
    }

    private void validate() {
        this.validation = new Validation();
        if (this.id.Value() <= 0) {
            this.validation.AddMessage("The Journal Entry Id must be greater than zero (0).");
        }
    }

    public boolean Equals(JournalEntryId journalEntryId) {
        return this.id.Equals(new Id(journalEntryId.Value()));
    }
}

package io.bensing.ledger.kernel;

import io.bensing.kernel.Validation;
import io.bensing.kernel.interfaces.Validatable;
import io.bensing.kernel.interfaces.ValueObject;

import java.time.Instant;
import java.util.ArrayList;

public class JournalEntryDate implements ValueObject<Long>, Validatable {

    private final long date;
    private final Validation validation;

    /**
     * Constructs a JournalEntryDate object and sets the date to now (the current date and time).
     */
    public JournalEntryDate() {
        this(Instant.now().toEpochMilli());
    }

    /**
     * Constructs a JournalEntryDate object from a date and time your provide.
     *
     * @param dateTimeEpoch The date and time as an Epoch Milliseconds
     */
    public JournalEntryDate(long dateTimeEpoch) {
        this.date = dateTimeEpoch;
        this.validation = new Validation();
        validate(this.validation);
    }

    /**
     * Returns the date and time
     * @return Date and time in Epoch Millisecond
     */
    public Long Value() {
        return this.date;
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

    private void validate(Validation validation) {
        if (this.date > 0) { return; }
        this.validation.AddMessage("The journal entry date must be greater than zero (0).");
    }

}

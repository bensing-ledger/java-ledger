package io.bensing.ledger.kernel;

import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.time.Instant;

@DisplayName("Journal Entry Date - Positive & Negative")
public class JournalEntryDateTests {

    @SmallTest()
    @DisplayName("Create a Journal Entry Date with no input date.")
    public void CreateJournalEntryDate() {
        var date = new JournalEntryDate();

        Assertions.assertTrue(date.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(date.IsInvalid(), "Expected IsInvalid() to be false.");
        long currentDateTime = Instant.now().toEpochMilli();
        long dateTimeDelta = currentDateTime - date.Value();
        long fiveSeconds = 5000;
        Assertions.assertTrue(dateTimeDelta < fiveSeconds, "Expected the date value to be within 5 seconds of the current time.");
    }

    @SmallTest()
    @DisplayName("Create a Journal Entry Date passing a specified date")
    public void CreateJournalEntryDateWithPassedDate() {
        var date = new JournalEntryDate(12345L);

        var expectedValue = 12345L;
        Assertions.assertTrue(date.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(date.IsInvalid(), "Expected IsInvalid() to be false.");
        Assertions.assertEquals(expectedValue, date.Value(), "The expected journal date value was not returned." );

    }

    @SmallTest()
    @DisplayName("Fail Journal Entry Date when passed a zero argument.")
    public void FailJournalEntryDateWithZeroArgument() {
        var date = new JournalEntryDate(0L);

        Assertions.assertFalse(date.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(date.IsInvalid(), "Expected IsInvalid() to be true.");
        var expectedMessage = "The journal entry date must be greater than zero (0).";
        Assertions.assertTrue(date.ValidationMessages().contains(expectedMessage),
                "The expected invalid journal entry date message was not provided.");
    }

    @SmallTest()
    @DisplayName("Fail Journal Entry Date when passed a negative argument.")
    public void FailJournalEntryDateWithNegativeArgument() {
        var date = new JournalEntryDate(-1L);

        Assertions.assertFalse(date.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(date.IsInvalid(), "Expected IsInvalid() to be true.");
        var expectedMessage = "The journal entry date must be greater than zero (0).";
        Assertions.assertTrue(date.ValidationMessages().contains(expectedMessage),
                "The expected invalid journal entry date message was not provided.");
    }

}

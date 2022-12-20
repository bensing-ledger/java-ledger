package io.bensing.ledger.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class JournalEntryDescriptionTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Journal Entry Description")
    public void CreateJournalEntryDescription() {

        var description = new JournalEntryDescription("Some journal entry");

        Assertions.assertTrue(description.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(description.IsInvalid(), "Expected IsInvalid to be false.");
        Assertions.assertEquals("Some journal entry", description.Value(),
                "The expected value was not returned.");
    }

    @Test
    @Tag("Small")
    @DisplayName("Expect an invalid Journal Entry Description when an empty argument is provided ")
    public void EmptyJournalEntryDescription() {

        var description = new JournalEntryDescription("");

        Assertions.assertFalse(description.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(description.IsInvalid(), "Expected IsInvalid to be true.");
        Assertions.assertTrue(description.ValidationMessages().contains("The journal entry description cannot be empty."),
                "The expected validation message was not provided.");
        Assertions.assertEquals("", description.Value(),
                "The expected value was not returned.");
    }

    @Test
    @Tag("Small")
    @DisplayName("Two (2) same journal entry descriptions must equal each other.")
    public void TwoSameJournalEntryDescriptionsEqual() {
        var desc1 = new JournalEntryDescription("Some Description");
        var desc2 = new JournalEntryDescription("Some Description");

        Assertions.assertTrue(desc1.Equals(desc2));
    }

    @Test
    @Tag("Small")
    @DisplayName("Two (2) different journal entry descriptions must NOT EQUAL each other.")
    public void TwoDifferentJournalEntryDescriptionsDoNotEqual() {
        var desc1 = new JournalEntryDescription("Some Description");
        var desc2 = new JournalEntryDescription("Some Different Description");

        Assertions.assertFalse(desc1.Equals(desc2));
    }
}

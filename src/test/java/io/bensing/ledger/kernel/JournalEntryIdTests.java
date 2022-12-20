package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class JournalEntryIdTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a journal entry id.")
    public void CreateJournalEntryId() {

        var id1 = new JournalEntryId(1L);

        Assertions.assertEquals(1L, id1.Value(),
                "The expected .Value() was not returned with constructor taking the long argument");
        Assertions.assertTrue(id1.IsValid(), "Expected the IsValid to true.");
        Assertions.assertFalse(id1.IsInvalid(), "Expected the IsInvalid to false.");

        var id2 = new JournalEntryId(new Id(2L));

        Assertions.assertEquals(2L, id2.Value(),
                "The expected .Value() was not returned with constructor taking the Id argument");
        Assertions.assertTrue(id2.IsValid(), "Expected the IsValid to true.");
        Assertions.assertFalse(id2.IsInvalid(), "Expected the IsInvalid to false.");
    }

    @Test
    @Tag("Small")
    @DisplayName("A bad journal entry id is invalid.")
    public void CreateBadJournalEntryId() {

        var id = new JournalEntryId(0L);

        Assertions.assertFalse(id.IsValid(), "Expected the IsValid to false.");
        Assertions.assertTrue(id.IsInvalid(), "Expected the IsInvalid to true.");
        Assertions.assertTrue(id.ValidationMessages().contains("The Journal Entry Id must be greater than zero (0)."),
                "The expected validation message was not found.");
    }

    @Test
    @DisplayName("Two (2) of the same Journal Entry Id's should EQUAL each other.")
    public void TwoSameJournalEntryIdsEqual() {

        var id1 = new JournalEntryId(1L);
        var id2 = new JournalEntryId(1L);

        Assertions.assertTrue(id1.Equals(id2), "Excepted both of the Journal Entry IDs to equal each other.");

    }

    @Test
    @DisplayName("Two (2) different Journal Entry Id's should NOT EQUAL each other.")
    public void TwoDifferentJournalEntryIdsDoNotEqual() {

        var id1 = new JournalEntryId(1L);
        var id2 = new JournalEntryId(2L);

        Assertions.assertFalse(id1.Equals(id2), "Did not expect the journal entry ids to equal each other.");
    }
}

package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DebitTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Debit")
    public void CreateDebit() {
        var description = new JournalEntryDescription("Cash deposit into account 1234");
        var transactionId = new JournalEntryId(1234234534);
        var account = new Account(1234);
        double amount = 25.00;
        long dateAndTime = 223423453;

        var debit = new Debit(description, transactionId, account, amount, dateAndTime);

        Assertions.assertEquals("Cash deposit into account 1234", debit.Description().Value());
        Assertions.assertEquals(1234234534, debit.EntryId().Value());
        Assertions.assertEquals(1234, debit.Account().Value());
        Assertions.assertEquals(25.00, debit.Amount());
        Assertions.assertEquals(223423453, debit.DateAndTime());
    }

    @Test
    @Tag("Small")
    @DisplayName("Two same debits equal each other.")
    public void TwoDebitsEqual() {

        var debit1 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1234234534), new Account(1234), 25.00, 1L);
        var debit2 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1234234534), new Account(1234), 25.00, 1L);

        Assertions.assertTrue(debit1.Equals(debit2));

    }

    @Test
    @Tag("Small")
    @DisplayName("Two different debits do not equal each other.")
    public void TwoDifferentDebitsDoNotEqual() {

        var debit1 = new Debit(new JournalEntryDescription("Message 1"),
                new JournalEntryId(1L), new Account(1234), 25.00, 1L);
        var debit2 = new Debit(new JournalEntryDescription("Message 2"),
                new JournalEntryId(1L), new Account(1234), 25.00, 1L);

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debits descriptions should not equate");

        debit1 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(1234), 25.00, 1L);
        debit2 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(2L), new Account(1234), 25.00, 1L);

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debits transaction ids should not equate");

        debit2 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(4L), 25.00, 1L);
        debit1 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(3L), 25.00, 1L);

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit account numbers should not equate");

        debit1 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(3L), 30.00, 1L);
        debit2 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(3L), 25.00, 1L);

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit amounts should not equate");

        debit1 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(3L), 25.00, 5L);
        debit2 = new Debit(new JournalEntryDescription("Transfer into account 1234"),
                new JournalEntryId(1L), new Account(3L), 25.00, 6L);

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit date & times should not equate");

    }
}

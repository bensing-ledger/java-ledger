package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class JournalEntryTests {

    @Test
    @Tag("small")
    @DisplayName("Create a Journal Entry")
    public void CreateJournalEntry(){

        double amount = 20.00;
        var transId = new Id(1L);
        var dateAndTime = 22L;
        var creditAcct = new Account(21L);
        var debitAcct = new Account(31L);

        var journalEntry = new JournalEntry("New Transaction", transId, dateAndTime, amount, creditAcct, debitAcct);

        var expectedCredit = new Credit("New Transaction", transId, creditAcct, amount, dateAndTime);
        var expectedDebit = new Debit("New Transaction", transId, debitAcct, amount, dateAndTime);

        Assertions.assertTrue(journalEntry.IsValid(),
                "The journal entry is supposed to be valid, and it is not valid.");
        Assertions.assertEquals("New Transaction", journalEntry.Description(),
                "The journal entry transaction description did not match the expected transaction description");
        Assertions.assertEquals(1L, journalEntry.TransactionId().Value(), "" +
                "The journal entry transaction id did not match the expected transaction id.");
        Assertions.assertTrue(journalEntry.Credit().Equals(expectedCredit),
                "The journal entry credit did not match the expected credit.");
        Assertions.assertTrue(journalEntry.Debit().Equals(expectedDebit),
                "The journal entry debit did not match the expected debit.");

    }
}

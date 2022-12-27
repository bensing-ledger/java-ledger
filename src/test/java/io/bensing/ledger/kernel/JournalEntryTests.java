package io.bensing.ledger.kernel;

import io.bensing.kernel.identity.Id;
import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Journal Entry - Positive & Negative")
public class JournalEntryTests {

    @SmallTest
    @DisplayName("Create a Journal Entry, move $20 from one asset account to another.")
    public void CreateJournalEntry(){

        var description = "New Transaction";
        var entryId = new Id(1L);
        var dateAndTime = 22L;
        double amount = 20.00;
        var creditAcct = new Account(21L, AccountType.Asset);
        var debitAcct = new Account(31L, AccountType.Asset);

        var entry = new JournalEntry(description, entryId, dateAndTime, amount, creditAcct, debitAcct);

        Assertions.assertTrue(entry.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(entry.IsInvalid(), "Expected IsInvalid() to be false.");
        Assertions.assertEquals(0, entry.ValidationMessages().size(),
                "Expected validation messages to have no (0) messages.");

        Assertions.assertEquals("New Transaction", entry.Description().Value(),
                "The journal entry transaction description did not match the expected transaction description");
        Assertions.assertEquals(1L, entry.EntryId().Value(), "" +
                "The journal entry transaction id did not match the expected transaction id.");
        Assertions.assertEquals(22L, entry.Date().Value(),
                "The journal entry date did not match the expected date.");

        Assertions.assertTrue(entry.Credit().IsValid(), "Expected the credit to be valid, but it is Invalid.");
        Assertions.assertEquals(-20.00, entry.Credit().Value(), "The expected credit value was not returned.");
        Assertions.assertEquals(21L, entry.Credit().AccountNumber(),
                "The journal entry credit account number did not match the expected account number.");

        Assertions.assertTrue(entry.Debit().IsValid(), "Expected the debit to be valid, but it is Invalid.");
        Assertions.assertEquals(20.00, entry.Debit().Value(), "The expected debit value was not returned.");
        Assertions.assertEquals(31L, entry.Debit().AccountNumber(),
                "The journal entry debit account number did not match the expected account number.");

    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with an bad description")
    public void MakeInvalid_FromInvalidDescription() {

        var invalidDescription = "";
        var entryId = new Id(1234234534);
        var creditAccount = new Account(1L, AccountType.Asset);
        var debitAccount = new Account(1L, AccountType.Asset);
        var amount = 25.00;
        long dateAndTime = 223423453;

        var entry = new JournalEntry(invalidDescription, entryId, dateAndTime, amount, creditAccount, debitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(entry.ValidationMessages().contains("The journal entry description is invalid."),
                "The expected invalid journal entry description message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with a bad entry Id.")
    public void MakeInvalid_FromInvalidEntryId() {

        var description = "A good description";
        var badEntryId = new Id(0L);
        var creditAccount = new Account(1L, AccountType.Asset);
        var debitAccount = new Account(2L, AccountType.Asset);
        var amount = 25.00;
        long dateAndTime = 223423453;

        var entry = new JournalEntry(description, badEntryId, dateAndTime, amount, creditAccount, debitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(entry.ValidationMessages().contains("The journal entry id is invalid."),
                "The expected invalid journal entry id message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with a negative amount.")
    public void MakeInvalid_FromNegativeAmount() {

        var description = "A good description";
        var badAmount = -10.00;
        var entryId = new Id(1L);
        var creditAccount = new Account(1L, AccountType.Asset);
        var debitAccount = new Account(2L, AccountType.Asset);

        long dateAndTime = 223423453;

        var entry = new JournalEntry(description, entryId, dateAndTime, badAmount, creditAccount, debitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(entry.ValidationMessages().contains("The journal entry amount is invalid."),
                "The expected invalid journal entry amount message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with a zero amount.")
    public void MakeInvalid_FromZeroAmount() {

        var description = "A good description";
        var badAmount = 00.00;
        var entryId = new Id(1L);
        var creditAccount = new Account(1L, AccountType.Asset);
        var debitAccount = new Account(2L, AccountType.Asset);

        long dateAndTime = 223423453;

        var entry = new JournalEntry(description, entryId, dateAndTime, badAmount, creditAccount, debitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(entry.ValidationMessages().contains("The journal entry amount is invalid."),
                "The expected invalid journal entry amount message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with a bad date.")
    public void MakeInvalid_FromZeroDate() {

        var description = "A good description";
        var amount = 25.00;
        var entryId = new Id(1L);
        var creditAccount = new Account(1L, AccountType.Asset);
        var debitAccount = new Account(2L, AccountType.Asset);

        long badDate = 0L;

        var entry = new JournalEntry(description, entryId, badDate, amount, creditAccount, debitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(entry.ValidationMessages().contains("The journal entry date is invalid."),
                "The expected invalid journal entry date message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with a bad credit account.")
    public void MakeInvalid_FromBadCreditAccount() {

        var description = "A good description";
        var amount = 00.00;
        var entryId = new Id(1L);
        var badCreditAccount = new Account(0L, AccountType.Asset);
        var debitAccount = new Account(2L, AccountType.Asset);

        long dateAndTime = 223423453;

        var entry = new JournalEntry(description, entryId, dateAndTime, amount, badCreditAccount, debitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        var expectedMessage = "The journal credit account is invalid.";
        Assertions.assertTrue(entry.ValidationMessages().contains(expectedMessage),
                "The expected invalid journal credit account message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

    @SmallTest
    @DisplayName("Create an invalid journal entry with a bad debit account.")
    public void MakeInvalid_FromBadDebitAccount() {

        var description = "A good description";
        var amount = 00.00;
        var entryId = new Id(1L);
        var creditAccount = new Account(1L, AccountType.Asset);
        var badDebitAccount = new Account(0L, AccountType.Asset);

        long dateAndTime = 223423453;

        var entry = new JournalEntry(description, entryId, dateAndTime, amount, creditAccount, badDebitAccount);

        Assertions.assertTrue(entry.IsInvalid(), "Expected IsInvalid() to be true.");
        Assertions.assertFalse(entry.IsValid(), "Expected IsValid() to be false.");
        var expectedMessage = "The journal debit account is invalid.";
        Assertions.assertTrue(entry.ValidationMessages().contains(expectedMessage),
                "The expected invalid journal debit account message was not provided.");

        Assertions.assertTrue(entry.Credit().IsInvalid(), "Expected the credit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Credit().Value(), "Expected the credit value to be zero (0).");
        Assertions.assertTrue(entry.Debit().IsInvalid(), "Expected the debit to be invalid, but it is valid.");
        Assertions.assertEquals(0, entry.Debit().Value(), "Expected the debit value to be zero (0).");
    }

}

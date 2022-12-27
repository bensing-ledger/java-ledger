package io.bensing.ledger.kernel;

import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Debit - Positive & Negative")
public class DebitTests {

    @SmallTest
    @DisplayName("Create a Debit")
    public void CreateDebit() {
        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.AccountNumber(), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertEquals(25.00, debit.Value(), "The expected value was NOT EQUAL to the debit value.");
        Assertions.assertTrue(debit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(debit.IsInvalid(), "Expected IsValid() to be false.");

    }

    @SmallTest
    @DisplayName("Create the debit for a asset account type.")
    public void CreateAssetDebit() {
        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.AccountNumber(), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertEquals(25.00, debit.Value(), "The expected value was NOT EQUAL to the debit value.");
        Assertions.assertTrue(debit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(debit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the debit for a liability  type.")
    public void CreateLiabilityDebit() {
        var account = new Account(1234, AccountType.Liability);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.AccountNumber(), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertEquals(-25.00, debit.Value(), "The expected value was NOT EQUAL to the debit value.");
        Assertions.assertTrue(debit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(debit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the debit for a revenue type.")
    public void CreateRevenueDebit() {
        var account = new Account(1234, AccountType.Revenue);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.AccountNumber(), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertEquals(-25.00, debit.Value(), "The expected value was NOT EQUAL to the debit value.");
        Assertions.assertTrue(debit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(debit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the debit for a expense type.")
    public void CreateExpenseDebit() {
        var account = new Account(1234, AccountType.Expense);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.AccountNumber(), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertEquals(25.00, debit.Value(), "The expected value was NOT EQUAL to the debit value.");
        Assertions.assertTrue(debit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(debit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the debit for a equity type.")
    public void CreateEquityDebit() {
        var account = new Account(1234, AccountType.Equity);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.AccountNumber(), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertEquals(-25.00, debit.Value(), "The expected value was NOT EQUAL to the debit value.");
        Assertions.assertTrue(debit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(debit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create an invalid debit from an invalid amount.")
    public void CreateInvalidDebitFromInvalidAmount() {

        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(-25.00);

        var debit = new Debit(account, amount);

        Assertions.assertFalse(debit.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(debit.IsInvalid(), "Expected IsInvalid() to be true.");
        var expectedMessage = "The amount is invalid.";
        Assertions.assertTrue(debit.ValidationMessages().contains(expectedMessage),
                "The expected invalid debit amount message was not provided.");
        Assertions.assertTrue(debit.ValidationMessages().size() >= 2,
                "Expected at least two validation messages.");

        Assertions.assertEquals(00.00, debit.Value(), "The debit value was not the expected value.");
        Assertions.assertEquals(1234, debit.AccountNumber(), "The debit account was not the expected account.");

    }

    @SmallTest
    @DisplayName("Create an invalid debit from an invalid account.")
    public void CreateInvalidDebitFromInvalidAccount() {

        var account = new Account(0L, AccountType.Asset);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertFalse(debit.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(debit.IsInvalid(), "Expected IsInvalid() to be true.");
        var expectedMessage = "The account is invalid.";
        Assertions.assertTrue(debit.ValidationMessages().contains(expectedMessage),
                "The expected invalid debit account message was not provided.");
        Assertions.assertTrue(debit.ValidationMessages().size() >= 2,
                "Expected at least two validation messages.");

        Assertions.assertEquals(00.00, debit.Value(), "The debit value was not the expected value.");
        Assertions.assertEquals(0, debit.AccountNumber(), "The debit account was not the expected account.");

    }

    @SmallTest
    @DisplayName("Two same debits equal each other.")
    public void TwoDebitsEqual() {

        var debit1 = new Debit(new Account(1234, AccountType.Asset), new Amount(25.00));
        var debit2 = new Debit(new Account(1234, AccountType.Asset), new Amount(25.00));

        Assertions.assertTrue(debit1.Equals(debit2), "Expected the first debit to be equal to the second debit, by they are not equal.");

    }

    @SmallTest
    @DisplayName("Two different debits do not equal each other.")
    public void TwoDifferentDebitsDoNotEqual() {

        var debit2 = new Debit(new Account(4L, AccountType.Asset), new Amount(25.00));
        var debit1 = new Debit(new Account(3L, AccountType.Liability), new Amount(25.00));

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit accounts should not equate");

        debit1 = new Debit(new Account(3L, AccountType.Asset), new Amount(30.00));
        debit2 = new Debit(new Account(3L, AccountType.Asset), new Amount(25.00));

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit amounts should not equate");

    }
}

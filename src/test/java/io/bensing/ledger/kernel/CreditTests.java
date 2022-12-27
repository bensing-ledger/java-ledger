package io.bensing.ledger.kernel;

import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Credit - Positive & Negative")
public class CreditTests {

    @SmallTest
    @DisplayName("Create the credit for an asset account type.")
    public void CreateAssetCredit() {

        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertEquals(-25.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(1234, credit.AccountNumber(), "The credit account was not the expected account.");
        Assertions.assertTrue(credit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(credit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the credit for an liability account type.")
    public void CreateLiabilityCredit() {

        var account = new Account(1234, AccountType.Liability);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertEquals(25.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(1234, credit.AccountNumber(), "The credit account was not the expected account.");
        Assertions.assertTrue(credit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(credit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the credit for a revenue account type.")
    public void CreateRevenueCredit() {

        var account = new Account(1234, AccountType.Revenue);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertEquals(25.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(1234, credit.AccountNumber(), "The credit account was not the expected account.");
        Assertions.assertTrue(credit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(credit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the credit for an expense account type.")
    public void CreateExpenseCredit() {

        var account = new Account(1234, AccountType.Expense);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertEquals(-25.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(1234, credit.AccountNumber(), "The credit account was not the expected account.");
        Assertions.assertTrue(credit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(credit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create the credit for an equity account type.")
    public void CreateEquityCredit() {

        var account = new Account(1234, AccountType.Equity);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertEquals(25.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(1234, credit.AccountNumber(), "The credit account was not the expected account.");
        Assertions.assertTrue(credit.IsValid(), "Expected IsValid() to be true.");
        Assertions.assertFalse(credit.IsInvalid(), "Expected IsValid() to be false.");
    }

    @SmallTest
    @DisplayName("Create an invalid credit from an invalid amount.")
    public void CreateInvalidCreditFromInvalidAmount() {

        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(-25.00);

        var credit = new Credit(account, amount);

        Assertions.assertFalse(credit.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(credit.IsInvalid(), "Expected IsInvalid() to be true.");
        var expectedMessage = "The amount is invalid.";
        Assertions.assertTrue(credit.ValidationMessages().contains(expectedMessage),
                "The expected invalid credit amount message was not provided.");
        Assertions.assertTrue(credit.ValidationMessages().size() >= 2,
                "Expected at least two validation messages.");

        Assertions.assertEquals(00.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(1234, credit.AccountNumber(), "The credit account was not the expected account.");

    }

    @SmallTest
    @DisplayName("Create an invalid credit from an invalid account.")
    public void CreateInvalidCreditFromInvalidAccount() {

        var account = new Account(0, AccountType.Asset);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        Assertions.assertFalse(credit.IsValid(), "Expected IsValid() to be false.");
        Assertions.assertTrue(credit.IsInvalid(), "Expected IsInvalid() to be true.");
        var expectedMessage = "The account is invalid.";
        Assertions.assertTrue(credit.ValidationMessages().contains(expectedMessage),
                "The expected invalid credit account message was not provided.");
        Assertions.assertTrue(credit.ValidationMessages().size() >= 2,
                "Expected at least two validation messages.");

        Assertions.assertEquals(00.00, credit.Value(), "The credit value was not the expected value.");
        Assertions.assertEquals(0, credit.AccountNumber(), "The credit account was not the expected account.");

    }

    @SmallTest
    @DisplayName("Two same credits equal each other.")
    public void TwoCreditsEqual() {

        var credit1 = new Credit(new Account(1234, AccountType.Asset), new Amount(25.00));
        var credit2 = new Credit(new Account(1234, AccountType.Asset), new Amount(25.00));

        Assertions.assertTrue(credit1.Equals(credit2), "Expected both credits to equal each other, but they did not.");

    }

    @SmallTest
    @DisplayName("Two different credits do not equal each other.")
    public void TwoDifferentCreditsDoNotEqual() {

        var credit1 = new Credit(new Account(3L, AccountType.Asset), new Amount(25.00));
        var credit2 = new Credit(new Account(4L, AccountType.Liability), new Amount(25.00));

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit accounts should not equate");

        credit1 = new Credit(new Account(3L, AccountType.Asset), new Amount(35.00));
        credit2 = new Credit(new Account(3L, AccountType.Asset), new Amount(25.00));

        Assertions.assertFalse(credit1.Equals(credit2), "The two different credit amounts should not equate");

    }


}

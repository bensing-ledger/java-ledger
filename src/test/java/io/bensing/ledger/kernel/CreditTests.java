package io.bensing.ledger.kernel;

import io.bensing.ledger.jupiter.SmallTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Credit - Positive & Negative")
public class CreditTests {

    @SmallTest
    @DisplayName("Create a Credit")
    public void CreateCredit() {

        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(25.00);

        var credit = new Credit(account, amount);

        var expectedAccount = new Account(1234, AccountType.Asset);
        var expectedAmount = new Amount(25.00);
        Assertions.assertTrue(credit.Account().Equals(expectedAccount), "The credit account was not the expected account.");
        Assertions.assertTrue(credit.Amount().Equals(expectedAmount), "The credit amount was not the expected amount.");

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

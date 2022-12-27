package io.bensing.ledger.kernel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Debit - Positive & Negative")
public class DebitTests {

    @Test
    @Tag("Small")
    @DisplayName("Create a Debit")
    public void CreateDebit() {
        var account = new Account(1234, AccountType.Asset);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        var expectedAccount = new Account(1234, AccountType.Asset);
        var expectedAmount  = new Amount(25.00);
        Assertions.assertTrue(expectedAccount.Equals(debit.Account()), "The expected account was NOT EQUAL to the debit Account");
        Assertions.assertTrue(expectedAmount.Equals(debit.Amount()), "The expected amount was NOT EQUAL to the debit Amount.");

    }

    @Test
    @Tag("Small")
    @DisplayName("Two same debits equal each other.")
    public void TwoDebitsEqual() {

        var debit1 = new Debit(new Account(1234, AccountType.Asset), new Amount(25.00));
        var debit2 = new Debit(new Account(1234, AccountType.Asset), new Amount(25.00));

        Assertions.assertTrue(debit1.Equals(debit2), "Expected the first debit to be equal to the second debit, by they are not equal.");

    }

    @Test
    @Tag("Small")
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

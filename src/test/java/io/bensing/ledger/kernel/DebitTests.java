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
        var account = new Account(1234);
        var amount = new Amount(25.00);

        var debit = new Debit(account, amount);

        Assertions.assertEquals(1234, debit.Account());
        Assertions.assertEquals(25.00, debit.Amount());

    }

    @Test
    @Tag("Small")
    @DisplayName("Two same debits equal each other.")
    public void TwoDebitsEqual() {

        var debit1 = new Debit(new Account(1234), new Amount(25.00));
        var debit2 = new Debit(new Account(1234), new Amount(25.00));

        Assertions.assertTrue(debit1.Equals(debit2));

    }

    @Test
    @Tag("Small")
    @DisplayName("Two different debits do not equal each other.")
    public void TwoDifferentDebitsDoNotEqual() {

        var debit2 = new Debit(new Account(4L), new Amount(25.00));
        var debit1 = new Debit(new Account(3L), new Amount(25.00));

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit account numbers should not equate");

        debit1 = new Debit(new Account(3L), new Amount(30.00));
        debit2 = new Debit(new Account(3L), new Amount(25.00));

        Assertions.assertFalse(debit1.Equals(debit2), "The two different debit amounts should not equate");

    }
}
